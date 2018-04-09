package demo.thread;

import demo.hero.Hero;

/**
 * 线程交互的相关知识
 * @author suxin
 *
 */
public class ThreadCommunicate {
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		final  Hero gareen = new Hero();
		gareen.name = "盖伦";
		gareen.hp = 616;

		Thread[] th1 = new Thread[20];
		Thread[] th2 = new Thread[50];
		for (int i = 0; i < 20; i++) {
			th1[i] = new Thread() {
				public void run() {
					while (true) {
						gareen.recover1();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						 
					}

				}
			};
			th1[i].start();
		}
		for (int i = 0; i < 50; i++) {
			th2[i] = new Thread() {
				public void run() {
					while (true) {
						gareen.hurt();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						 
					}

				}
			};
			th2[i].start();
		}
	};
             
}
