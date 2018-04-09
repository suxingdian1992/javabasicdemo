package demo.thread;

import demo.hero.Hero;

/**
 * 多线程执行方法2，实现Runnable接口，复写其中的run方法
 * @author suxin
 *
 */
public class Battle implements Runnable {
	private Hero h1;
    private Hero h2;
    private int i;
   
 
    public Battle(Hero h1, Hero h2,int i){
        this.h1 = h1;
        this.h2 = h2;
        this.i = i;
    }
    
	@Override
	public void run() {
		while(!h2.isDead()){
            h1.attackHero(h2);
            KillThread.method1(i);
            KillThread.method2(i);
        }
	}

}
