package demo.thread;

import java.util.LinkedList;
import java.util.List;

import demo.collection.MyStringBufferArray;

/**
 * 多线程作业
 * @author suxin
 *
 */
public class ThreadHomeWork {
	
	public static void main(String[] args) {
		ThreadHomeWork test = new ThreadHomeWork();
		test.chapter2();
		//test.chapter1();//波动拳3次
	}
	
	/**
	 * 章节2，作业2，随机生成三位的密码，然后用多线程进行匹配，并用一个进程来打印日志
	 */
	public void chapter2() {
		MyStringBufferArray ma = new MyStringBufferArray();
		String password = ma.randomString(3);// 随机生成长度3的字符串作为密码
		System.out.println("当前的密码是：" + password);

		LinkedList<String> passwordLib = new LinkedList<String>();// 密码库,密码容器
		// 遍历三位密码
		Thread th1 = new CreatePassWordThread(passwordLib, password);
		Thread th2 = new LogThread(passwordLib);
		//th2.setDaemon(true);//日志类设定为守护线程
		th2.start();
		th1.start();
		
	}
	
	
	
	/**
	 * 章节1，作业1，每隔一秒发一次波动拳，连发三次之后充能五秒
	 */
	public void chapter1() {
		attack();
	}
	public static void attack() {//发波动拳一次
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("发出波动拳" + (i+1) + "次");
				}
				reNewEnergy();//进行充能
			}
		};
		t1.start();
		try {
			t1.join();//加入到主线程中，当前线程执行结束再继续
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void reNewEnergy() {//充能五秒
		Thread t1 = new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("充能五秒");
					Thread.sleep(5000);
					attack();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();
		try {
			t1.join();//加入到主线程中，当前线程执行结束再继续
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
