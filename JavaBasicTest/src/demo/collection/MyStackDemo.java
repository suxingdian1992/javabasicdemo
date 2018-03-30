package demo.collection;

import java.util.LinkedList;

import demo.hero.Hero;

/**
 * 使用双向链表来实现先进后出
 * @author suxin
 *
 */
public class MyStackDemo implements MyStack {
	//连接列表
	LinkedList<Hero> myStack = new LinkedList<Hero>();

	@Override
	public void push(Hero h) {
		myStack.addLast(h);//放在最后一位
	}

	@Override
	public Hero pull() {
		return myStack.pollLast();//弹出最后一个
	}

	@Override
	public Hero peek() {
		return myStack.peekLast();//弹出最后一个
	}

	public static void main(String[] args) {
		MyStackDemo ms = new MyStackDemo();
		ms.push(new Hero("sxd1"));
		ms.push(new Hero("sxd2"));
		ms.push(new Hero("sxd3"));
		
		System.out.println(ms.peek().name);
		System.out.println(ms.pull().name);
		System.out.println(ms.peek().name);
	}
}
