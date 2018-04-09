package demo.thread;

/**
 * �����ߺ�������ģʽ����������ַ����뵽��ǰջ�У�Ȼ�󵯳���ʹ�ö��߳�ʵ�֣�Ҫ���̰߳�ȫ
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
		//����������
		for (int i = 0; i < 2; i++) {
			new Producer(demoStack).start();
		}
		//����������
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
					System.out.println("��ǰѹ���Ԫ����:"+str.charAt(index)+" ������:"+myStack.length());
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
					System.out.println("��ǰ������Ԫ����:"+myStack.pull()+" ������:"+myStack.length());
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