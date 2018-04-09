package demo.thread;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import demo.collection.MyStack;
import demo.hero.Hero;

public class MyStackThread implements MyStack{
	//��LinkedListͨ�� Collections.synchronizedListת������һ���̰߳�ȫ��List
    List<Hero> heros = (List<Hero>) Collections.synchronizedList(new LinkedList<Hero>());
   
    //����Ҫ��push�ϼ�synchronized���η�
    //��Ȼ����߳̿���ͬʱ����push���������ǵ���heros.add������ʱ��
    //ͬһʱ�䣬ֻ��һ���߳̿��Խ���
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
            System.out.println("ѹ�� hero:" + h);
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
                    System.out.println("���� hero" + h);
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
