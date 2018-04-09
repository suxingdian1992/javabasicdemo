package demo.thread;

import demo.hero.Hero;

/**
 * ��ʾ�������⣬ע�⣺
 * ʲô���̰߳�ȫ��
 * ���һ���࣬�䷽��������synchronized���εģ���ô����ͽ����̰߳�ȫ����
 * @author suxin
 *
 */
public class ConcurrencyDemo {
	 public static void main(String[] args) {
	 	final Object someObject = new Object();//�κ��߳���Ҫ����gareen������Ҫ��ռ�ö���someobject�Դ���ʵ��һ��ֻ��һ���̲߳���garren
        final Hero gareen = new Hero();
        gareen.name = "����";
        gareen.hp = 10000;
           
        System.out.printf("���׵ĳ�ʼѪ���� %.0f%n", gareen.hp);
           
        //���߳�ͬ������ָ���Ƕ���߳�ͬʱ�޸�һ�����ݵ�ʱ�򣬵��µ�����
           
        //���������10000��Ѫ�������ڻ����ͬʱ�ֱ��Է����Ӣ�۹���
           
        //��JAVA��������ʾ�������ж���߳��ڼ��ٸ��׵�hp
        //ͬʱ���ж���߳��ڻָ����׵�hp
           
        //n���߳����Ӹ��׵�hp
           
        int n = 10000;
   
        Thread[] addThreads = new Thread[n];
        Thread[] reduceThreads = new Thread[n];
           
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){
                	synchronized (someObject) {//synchronized�ؼ��������ʾ��Ҫ�������²��������н��̶���Ҫ��ռ�ö���someObject��һ����ռ����ֻ�еȴ�
                		gareen.recover();
                		
					}
                    try {
                    	Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            addThreads[i] = t;
               
        }
           
        //n���̼߳��ٸ��׵�hp
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(){
                public void run(){
                	synchronized (someObject) {
                		gareen.hurt();
					}
                    try {
                    	Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            reduceThreads[i] = t;
        }
           
        //�ȴ����������߳̽���
        for (Thread t : addThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //�ȴ����м����߳̽���
        for (Thread t : reduceThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
           
        //����ִ�е�����������Ӻͼ����̶߳�������
           
        //���Ӻͼ����̵߳�������һ���ģ�ÿ�ζ����ӣ�����1.
        //��ô�����̶߳������󣬸��׵�hpӦ�û��ǳ�ʼֵ
           
        //������ʵ�Ϲ۲쵽���ǣ��������м��λᷢ�ֽ����ͬ��
                   
        System.out.printf("%d�������̺߳�%d�������߳̽�����%n���׵�Ѫ������� %.0f%n", n,n,gareen.hp);
        
        //����ԭ��
        /*1. ���������߳��Ƚ��룬�õ���hp��10000
        2. ������������
        3. ���������������ʱ�򣬻�û�����ü��޸�hp��ֵ�������߳�����
        4. �����̵߳õ���hp��ֵҲ��10000
        5. �����߳̽��м�������
        6. �����߳�����������õ�ֵ10001���������ֵ����hp
        7. �����߳�Ҳ����������õ�ֵ9999���������ֵ����hp������ֵ����9999
		��Ȼ�����������̸߳���������һ�Σ�������������ԭֵ10000������ȴ�õ���һ��9999
		���ʱ���ֵ9999��һ�������ֵ����ҵ�����ֽ���������*/
        
        //��Q˼·��
		/*������˼·�ǣ� �������̷߳���hp�ڼ䣬�����̲߳����Է���hp
		1. �����̻߳�ȡ��hp��ֵ������������
		2. �������ڼ䣬�����߳���ͼ����ȡhp��ֵ�����ǲ�������
		3. �����߳�������������ɹ��޸�hp��ֵΪ10001
		4. �����̣߳��������߳�����󣬲��ܷ���hp��ֵ����10001
		5. �����߳����㣬���õ��µ�ֵ10000*/
    }
}
