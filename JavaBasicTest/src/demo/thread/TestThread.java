package demo.thread;

import demo.hero.Hero;

/**
 * 多线程效果演示
 * @author suxin
 *
 */
public class TestThread {
	public static void main(String[] args) {
		Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 10;
 
        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 10;
         
        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 10;
         
        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 10;
        
        
        
        //还有一种是使用匿名内部类直接重写Thread类的run方法即可，可见类FileHomeWork.java
        
        TestThread test = new TestThread();
        //暂停当前线程
        //test.chapter1();//由于是不一样的线程，所以统计时间可以和上述操作并行处理
        //test.chapter2(gareen,teemo,bh,leesin);
        
        Battle battle1 = new Battle(gareen, teemo,1);
		new Thread(battle1).start();
		Battle battle2 = new Battle(bh, leesin,2);
		new Thread(battle2).start();
	}
	
	public void chapter2(Hero...h) {
		// 继承Thread进行相关操作
		KillThread killThread1 = new KillThread(h[0], h[1]);
		killThread1.start();
		/*try {
			killThread1.join();// 注意，将当前操作加入到了主线程之后则会在该操作执行完毕之后才会进行接下来的操作
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		KillThread killThread2 = new KillThread(h[2], h[3]);
		killThread2.start();
		// 使用Runnable接口进行相关操作
		/*Battle battle1 = new Battle(h[0], h[1]);
		new Thread(battle1).start();
		Battle battle2 = new Battle(h[2], h[3]);
		new Thread(battle2).start();*/
		// 还有一种是使用匿名内部类直接重写Thread类的run方法即可，可见类FileHomeWork.java
		
		killThread1.setPriority(Thread.MAX_PRIORITY);
		killThread2.setPriority(Thread.MIN_PRIORITY);
	}
	
	/**
	 * sleep方法暂停当前线程，但是该线程可能被终止，所以需要中断的异常处理
	 */
	public void chapter1() {
		Thread t1= new Thread(){
            public void run(){
                int seconds =0;
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.printf("已经玩了LOL %d 秒%n", seconds++);
                }              
            }
        };
        t1.setDaemon(true);//守护线程，当所有线程都结束的时候这个线程也结束
        t1.start();
	}
}
