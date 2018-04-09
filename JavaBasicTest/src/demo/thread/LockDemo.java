package demo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �����󣬺�synchronizedһ���ǽ���߳�ռ������Ĺ���
 * ǰ����ռ�ý���֮����ͷţ�������Ҫָ���ͷŷ����ſ����ͷ�
 * @author suxin
 *
 */
public class LockDemo {
	public static String now() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
 
    public static void log(String msg) {
        System.out.printf("%s %s %s %n", now() , Thread.currentThread().getName() , msg);
    }
 
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();//����������ʵ���ֶ��ͷź�ռ��
 
        Thread t1 = new Thread() {
            public void run() {
                try {
                    log("�߳�����");
                    log("��ͼռ�ж���lock");
 
                    lock.lock();
 
                    log("ռ�ж���lock");
                    log("����5���ҵ�����");
                    Thread.sleep(5000);
 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log("�ͷŶ���lock");
                    lock.unlock();
                }
                log("�߳̽���");
            }
        };
        t1.setName("t1");
        t1.start();
        try {
            //����t1��2�룬֮��ῴ���߳�2�������ǻ���Ҫ�ȴ��߳�1�ͷ�lock����֮��ſ��Լ���
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Thread t2 = new Thread() {
 
            public void run() {
                try {
                    log("�߳�����");
                    log("��ͼռ�ж���lock");
 
                    lock.lock();
 
                    log("ռ�ж���lock");
                    log("����5���ҵ�����");
                    Thread.sleep(5000);
 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log("�ͷŶ���lock");
                    lock.unlock();
                }
                log("�߳̽���");
            }
        };
        t2.setName("t2");
        t2.start();
    }
}
