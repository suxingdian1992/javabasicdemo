package demo.thread;

import demo.hero.Hero;

/**
 * 演示并发问题，注意：
 * 什么叫线程安全：
 * 如果一个类，其方法都是有synchronized修饰的，那么该类就叫做线程安全的类
 * @author suxin
 *
 */
public class ConcurrencyDemo {
	 public static void main(String[] args) {
	 	final Object someObject = new Object();//任何线程想要操作gareen都必须要先占用对象someobject以此来实现一次只能一个线程操作garren
        final Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 10000;
           
        System.out.printf("盖伦的初始血量是 %.0f%n", gareen.hp);
           
        //多线程同步问题指的是多个线程同时修改一个数据的时候，导致的问题
           
        //假设盖伦有10000滴血，并且在基地里，同时又被对方多个英雄攻击
           
        //用JAVA代码来表示，就是有多个线程在减少盖伦的hp
        //同时又有多个线程在恢复盖伦的hp
           
        //n个线程增加盖伦的hp
           
        int n = 10000;
   
        Thread[] addThreads = new Thread[n];
        Thread[] reduceThreads = new Thread[n];
           
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){
                	synchronized (someObject) {//synchronized关键字区块表示想要进行以下操作的所有进程都需要先占用对象someObject，一旦被占用则只有等待
                		gareen.recover();
                		
					}
                    try {
                    	Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            addThreads[i] = t;
               
        }
           
        //n个线程减少盖伦的hp
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){
                	synchronized (someObject) {
                		gareen.hurt();
					}
                    try {
                    	Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            reduceThreads[i] = t;
        }
           
        //等待所有增加线程结束
        for (Thread t : addThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //等待所有减少线程结束
        for (Thread t : reduceThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
           
        //代码执行到这里，所有增加和减少线程都结束了
           
        //增加和减少线程的数量是一样的，每次都增加，减少1.
        //那么所有线程都结束后，盖伦的hp应该还是初始值
           
        //但是事实上观察到的是：（多运行几次会发现结果不同）
                   
        System.out.printf("%d个增加线程和%d个减少线程结束后%n盖伦的血量变成了 %.0f%n", n,n,gareen.hp);
        
        //分析原因
        /*1. 假设增加线程先进入，得到的hp是10000
        2. 进行增加运算
        3. 正在做增加运算的时候，还没有来得及修改hp的值，减少线程来了
        4. 减少线程得到的hp的值也是10000
        5. 减少线程进行减少运算
        6. 增加线程运算结束，得到值10001，并把这个值赋予hp
        7. 减少线程也运算结束，得到值9999，并把这个值赋予hp，最后的值就是9999
		虽然经历了两个线程各自增减了一次，本来期望还是原值10000，但是却得到了一个9999
		这个时候的值9999是一个错误的值，在业务上又叫做脏数据*/
        
        //解決思路：
		/*总体解决思路是： 在增加线程访问hp期间，其他线程不可以访问hp
		1. 增加线程获取到hp的值，并进行运算
		2. 在运算期间，减少线程试图来获取hp的值，但是不被允许
		3. 增加线程运算结束，并成功修改hp的值为10001
		4. 减少线程，在增加线程做完后，才能访问hp的值，即10001
		5. 减少线程运算，并得到新的值10000*/
    }
}
