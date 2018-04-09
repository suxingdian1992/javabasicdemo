package demo.thread;

import demo.hero.Hero;

/**
 * ���߳�Ч����ʾ
 * @author suxin
 *
 */
public class TestThread {
	public static void main(String[] args) {
		Hero gareen = new Hero();
        gareen.name = "����";
        gareen.hp = 616;
        gareen.damage = 10;
 
        Hero teemo = new Hero();
        teemo.name = "��Ī";
        teemo.hp = 300;
        teemo.damage = 10;
         
        Hero bh = new Hero();
        bh.name = "�ͽ�����";
        bh.hp = 500;
        bh.damage = 10;
         
        Hero leesin = new Hero();
        leesin.name = "äɮ";
        leesin.hp = 455;
        leesin.damage = 10;
        
        
        
        //����һ����ʹ�������ڲ���ֱ����дThread���run�������ɣ��ɼ���FileHomeWork.java
        
        TestThread test = new TestThread();
        //��ͣ��ǰ�߳�
        //test.chapter1();//�����ǲ�һ�����̣߳�����ͳ��ʱ����Ժ������������д���
        //test.chapter2(gareen,teemo,bh,leesin);
        
        Battle battle1 = new Battle(gareen, teemo,1);
		new Thread(battle1).start();
		Battle battle2 = new Battle(bh, leesin,2);
		new Thread(battle2).start();
	}
	
	public void chapter2(Hero...h) {
		// �̳�Thread������ز���
		KillThread killThread1 = new KillThread(h[0], h[1]);
		killThread1.start();
		/*try {
			killThread1.join();// ע�⣬����ǰ�������뵽�����߳�֮������ڸò���ִ�����֮��Ż���н������Ĳ���
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		KillThread killThread2 = new KillThread(h[2], h[3]);
		killThread2.start();
		// ʹ��Runnable�ӿڽ�����ز���
		/*Battle battle1 = new Battle(h[0], h[1]);
		new Thread(battle1).start();
		Battle battle2 = new Battle(h[2], h[3]);
		new Thread(battle2).start();*/
		// ����һ����ʹ�������ڲ���ֱ����дThread���run�������ɣ��ɼ���FileHomeWork.java
		
		killThread1.setPriority(Thread.MAX_PRIORITY);
		killThread2.setPriority(Thread.MIN_PRIORITY);
	}
	
	/**
	 * sleep������ͣ��ǰ�̣߳����Ǹ��߳̿��ܱ���ֹ��������Ҫ�жϵ��쳣����
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
                    System.out.printf("�Ѿ�����LOL %d ��%n", seconds++);
                }              
            }
        };
        t1.setDaemon(true);//�ػ��̣߳��������̶߳�������ʱ������߳�Ҳ����
        t1.start();
	}
}
