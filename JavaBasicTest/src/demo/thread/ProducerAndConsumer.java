package demo.thread;

/**
 * 消费者和生产者模式，产生随机字符插入到当前栈中，然后弹出，使用多线程实现，要有线程安全
 * @author suxin
 *
 */
public class ProducerAndConsumer {
	public static void main(String[] args) {
		MyStackThread demoStack = new MyStackThread();
		demoStack.push('c');
		demoStack.push('c');
		demoStack.push('c');
		demoStack.push('c');
		demoStack.push('c');
		System.out.println(demoStack.length());
		//两个生产者
		for (int i = 0; i < 2; i++) {
			new Producer(demoStack).start();
		}
		//三个消费者
		for (int i = 0; i < 3; i++) {
			new Consumer(demoStack).start();
		}
	}
}

class Producer extends Thread{
	public MyStackThread myStack;
	
	public Producer(MyStackThread myStack) {
		this.myStack = myStack;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (myStack) {
				if(myStack.length()>20) {
					try {
						myStack.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					String str="";
					for (int i = 'A'; i <= 'Z'; i++) {
						str += (char)i;
					}
					int index = (int) (Math.random()*(str.length()));
					System.out.println("当前压入的元素是:"+str.charAt(index)+" 长度是:"+myStack.length());
					myStack.push(str.charAt(index));
					myStack.notifyAll();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
	}
}

class Consumer extends Thread{
	public MyStackThread myStack;
	
	public Consumer(MyStackThread myStack) {
		this.myStack = myStack;
	}
	@Override
	public void run() {
		while(true) {
			synchronized (myStack) {
				if(myStack.length()==0) {
					try {
						myStack.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("当前弹出的元素是:"+myStack.pull()+" 长度是:"+myStack.length());
					myStack.notifyAll();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
}