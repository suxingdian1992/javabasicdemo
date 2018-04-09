package demo.thread;

import java.util.LinkedList;

/**
 * �̳߳�demo
 * @author suxin
 *
 */
public class ThreadPool {
	// �̳߳ش�С
    int threadPoolSize;
  
    // ��������
    LinkedList<Runnable> tasks = new LinkedList<Runnable>();
  
    // ��ͼ����������߳�
  
    public ThreadPool() {
        threadPoolSize = 10;
  
        // ����10�������������߳�
        synchronized (tasks) {
            for (int i = 0; i < threadPoolSize; i++) {
                new TaskConsumeThread("�����������߳� " + i).start();
            }
        }
    }
  
    public void add(Runnable r) {
        synchronized (tasks) {
            tasks.add(r);
            // ���ѵȴ��������������߳�
            tasks.notifyAll();
        }
    }
  
    //�ڲ��࣬�����ȡtasks�����б�
    class TaskConsumeThread extends Thread {
        public TaskConsumeThread(String name) {
            super(name);
        }
  
        Runnable task;
  
        public void run() {
            System.out.println("������ " + this.getName());
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    task = tasks.removeLast();
                    // �������������߳̿��Լ����������
                    tasks.notifyAll();
  
                }
                System.out.println(this.getName() + " ��ȡ�����񣬲�ִ��");
                task.run();
            }
        }
    }
    
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        int sleep = 1000;
        for (int i = 0; i < 500; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("ִ������");
                    //��������Ǵ�ӡһ�仰
                    //�����Ƿ����ļ�
                    //������������
                }
            };
             
            pool.add(task);
            
            try {
            	sleep = sleep>100?sleep-100:sleep;
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
  
    }
}
