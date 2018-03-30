package demo.collection;

import java.util.LinkedList;

import demo.hero.Hero;

/**
 * ʹ��˫��������ʵ���Ƚ����
 * @author suxin
 *
 */
public class MyStackDemo implements MyStack {
	//�����б�
	LinkedList<Hero> myStack = new LinkedList<Hero>();

	@Override
	public void push(Hero h) {
		myStack.addLast(h);//�������һλ
	}

	@Override
	public Hero pull() {
		return myStack.pollLast();//�������һ��
	}

	@Override
	public Hero peek() {
		return myStack.peekLast();//�������һ��
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
