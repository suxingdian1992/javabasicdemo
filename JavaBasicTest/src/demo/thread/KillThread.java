package demo.thread;

import demo.hero.Hero;

/**
 * ���̵߳�һ�ַ�ʽ������Thread�࣬����run��ִ��Ҫ���Ĳ���
 * @author suxin
 *
 */
public class KillThread extends Thread{
	private Hero h1;
    private Hero h2;
 
    public KillThread(Hero h1, Hero h2){
        this.h1 = h1;
        this.h2 = h2;
    }
 
    public synchronized static void method1(int i) {
		System.out.println("��̬����synchronized:"+i);
	}
    
    public static void method2(int i) {
		System.out.println("��̬����2:"+i);
	}
    
    public void run(){
        while(!h2.isDead()){
            h1.attackHero(h2);
        }
    }
}
