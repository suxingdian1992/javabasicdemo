package demo.thread;

import java.util.LinkedList;
import java.util.List;

import demo.collection.MyStringBufferArray;

/**
 * ���߳���ҵ
 * @author suxin
 *
 */
public class ThreadHomeWork {
	
	public static void main(String[] args) {
		ThreadHomeWork test = new ThreadHomeWork();
		test.chapter2();
		//test.chapter1();//����ȭ3��
	}
	
	/**
	 * �½�2����ҵ2�����������λ�����룬Ȼ���ö��߳̽���ƥ�䣬����һ����������ӡ��־
	 */
	public void chapter2() {
		MyStringBufferArray ma = new MyStringBufferArray();
		String password = ma.randomString(3);// ������ɳ���3���ַ�����Ϊ����
		System.out.println("��ǰ�������ǣ�" + password);

		LinkedList<String> passwordLib = new LinkedList<String>();// �����,��������
		// ������λ����
		Thread th1 = new CreatePassWordThread(passwordLib, password);
		Thread th2 = new LogThread(passwordLib);
		//th2.setDaemon(true);//��־���趨Ϊ�ػ��߳�
		th2.start();
		th1.start();
		
	}
	
	
	
	/**
	 * �½�1����ҵ1��ÿ��һ�뷢һ�β���ȭ����������֮���������
	 */
	public void chapter1() {
		attack();
	}
	public static void attack() {//������ȭһ��
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("��������ȭ" + (i+1) + "��");
				}
				reNewEnergy();//���г���
			}
		};
		t1.start();
		try {
			t1.join();//���뵽���߳��У���ǰ�߳�ִ�н����ټ���
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void reNewEnergy() {//��������
		Thread t1 = new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("��������");
					Thread.sleep(5000);
					attack();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();
		try {
			t1.join();//���뵽���߳��У���ǰ�߳�ִ�н����ټ���
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
