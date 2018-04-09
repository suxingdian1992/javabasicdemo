package demo.thread;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MyStackThread {

	// 把LinkedList通过 Collections.synchronizedList转换成了一个线程安全的List
	static List<Character> heros = (List<Character>) Collections.synchronizedList(new LinkedList<Character>());

	public int length() {
		return heros.size();
	}

	// 不需要在push上加synchronized修饰符
	// 虽然多个线程可以同时进入push方法，但是调用heros.add方法的时候
	// 同一时间，只有一个线程可以进入
	public void push(Character h) {
		heros.add(h);
	}

	public Character pull() {
		return heros.remove(heros.size() - 1);
	}

	public Character peek() {
		return heros.get(heros.size() - 1);
	}

	@Override
	public String toString() {
		return "MyStack [list=" + heros + "]";
	}
}
