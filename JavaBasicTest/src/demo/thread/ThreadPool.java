package demo.thread;

import java.util.LinkedList;

/**
 * 线程池demo
 * @author suxin
 *
 */
public class ThreadPool {
	// 线程池大小
    int threadPoolSize;
  
    // 任务容器
    LinkedList<Runnable> tasks = new LinkedList<Runnable>();
  
    // 试图消费任务的线程
  
    public ThreadPool() {
        threadPoolSize = 10;
  
        // 启动10个任务消费者线程
        synchronized (tasks) {
            for (int i = 0; i < threadPoolSize; i++) {
                new TaskConsumeThread("任务消费者线程 " + i).start();
            }
        }
    }
  
    public void add(Runnable r) {
        synchronized (tasks) {
            tasks.add(r);
            // 唤醒等待的任务消费者线程
            tasks.notifyAll();
        }
    }
  
    //内部类，方便获取tasks任务列表
    class TaskConsumeThread extends Thread {
        public TaskConsumeThread(String name) {
            super(name);
        }
  
        Runnable task;
  
        public void run() {
            System.out.println("启动： " + this.getName());
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
                    // 允许添加任务的线程可以继续添加任务
                    tasks.notifyAll();
  
                }
                System.out.println(this.getName() + " 获取到任务，并执行");
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
                    System.out.println("执行任务");
                    //任务可能是打印一句话
                    //可能是访问文件
                    //可能是做排序
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
