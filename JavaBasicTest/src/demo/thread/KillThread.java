package demo.thread;

import demo.hero.Hero;

/**
 * 多线程第一种方式，集成Thread类，并在run中执行要做的操作
 * @author suxin
 *
 */
public class KillThread extends Thread{
	private Hero h1;
    private Hero h2;
 
    public KillThread(Hero h1, Hero h2){
        this.h1 = h1;
        this.h2 = h2;
    }
 
    public synchronized static void method1(int i) {
		System.out.println("静态方法synchronized:"+i);
	}
    
    public static void method2(int i) {
		System.out.println("静态方法2:"+i);
	}
    
    public void run(){
        while(!h2.isDead()){
            h1.attackHero(h2);
        }
    }
}
