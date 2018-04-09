package demo.thread;

import demo.hero.Hero;

/**
 * 死锁场景模拟
 * @author suxin
 *
 */
public class DeadLockDemo {
	public static void main(String[] args) {
		DeadLockDemo test = new DeadLockDemo();
		test.chapter1();
	}
	
	/**
	 * 死锁模拟
	 */
	public void chapter1() {
		final Hero ahri = new Hero();
        ahri.name = "九尾妖狐";
        final Hero annie = new Hero();
        annie.name = "安妮";
        final Hero jack = new Hero();
        annie.name = "冰霜杰克";
        
         
        Thread t1 = new Thread(){
            public void run(){
                //占有九尾妖狐
                synchronized (ahri) {
                    System.out.println("t1 已占有九尾妖狐");
                    try {
                        //停顿1000毫秒，另一个线程有足够的时间占有安妮
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                     
                    System.out.println("t1 试图占有安妮");
                    System.out.println("t1 等待中 。。。。");
                    synchronized (annie) {
                        System.out.println("do something");
                    }
                }
            }
        };
        t1.start();
        
        Thread t2 = new Thread(){
            public void run(){
                //占有安妮
                synchronized (annie) {
                    System.out.println("t2 已占有安妮");
                    try {
                         
                        //停顿1000秒，另一个线程有足够的时间占有暂用九尾妖狐
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("t2 试图占有jack");
                    System.out.println("t2 等待中 。。。。");
                    synchronized (jack) {
                        System.out.println("do something");
                    }
                }  
                 
            }
        };
        t2.start();
        
        Thread t3 = new Thread(){
            public void run(){
                //占有安妮
                synchronized (jack) {
                    System.out.println("t3 已占有jack");
                    try {
                         
                        //停顿1000秒，另一个线程有足够的时间占有暂用九尾妖狐
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("t3 试图占有安妮");
                    System.out.println("t3 等待中 。。。。");
                    synchronized (ahri) {
                        System.out.println("do something");
                    }
                }  
                 
            }
        };
        t3.start();
	}
}
