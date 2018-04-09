package demo.thread;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import demo.collection.MyStack;
import demo.hero.Hero;

public class MyStackThread implements MyStack{
	//把LinkedList通过 Collections.synchronizedList转换成了一个线程安全的List
    List<Hero> heros = (List<Hero>) Collections.synchronizedList(new LinkedList<Hero>());
   
    //不需要在push上加synchronized修饰符
    //虽然多个线程可以同时进入push方法，但是调用heros.add方法的时候
    //同一时间，只有一个线程可以进入
    public void push(Hero h) {
        heros.add(h);
    }
      
    public Hero pull() {
    	if(heros.size()==0) {
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	System.out.println(heros.size()-1);
        return heros.remove(heros.size()-1);
    }
      
    public Hero peek() {
    	
        return heros.get(heros.size()-1);
    }
     
    @Override
    public String toString() {
        return "MyStack [list=" + heros + "]";
    }

    public static void main(String[] args) {
        int n = 1000;
        MyStackThread heroStack = new MyStackThread();
        Thread[] threads = new Thread[n];
        Thread[] threads1 = new Thread[n];
        for (int i = 0; i < n; i++) {
            final Hero h = new Hero("hero name " + i);
            System.out.println("压入 hero:" + h);
            Thread thread = new Thread(){
                public void run() {
                    heroStack.push(h);
                };
            };
            thread.start();
            threads[i] = thread;
        }
        for (int i = 0; i < n; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    Hero h = heroStack.pull();
                    System.out.println("弹出 hero" + h);
                };
            };
            thread.start();
            threads1[i] = thread;
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (Thread thread : threads1) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
         
        System.out.println(heroStack);
    }
}
