package demo.hero;

import java.io.Serializable;

/**
 * 英雄的共性类
 * @author suxin
 *
 */
public class Hero extends LOL implements Serializable,Comparable<Hero>{
	/**
	 * 表示当前这个类的版本，如果新设置了属性，就一个该修改这个版本号
	 */
	private static final long serialVersionUID = 1L;
	public String name;
    public float hp;
    public int damage;
    
    public static String copyRight;
    static {
    	copyRight = "LOL Tencent";
    	System.out.println(copyRight);
    }
    
    public Hero() {
    	  
    }
  
    public synchronized void recover() {
        hp = hp + 1;
        System.out.printf("%s 回血1点,增加血后，%s的血量是%.0f%n", name, name, hp);
        // 通知那些等待在this对象上的线程，可以醒过来了，如第20行，等待着的减血线程，苏醒过来
        this.notify();
    }
    
    public synchronized void recover1() {
        
        if(hp > 1000) {
    		try {
    			//告诉想要占用这个对象的线程，这里不可以用了
				this.wait();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {
    		hp = hp + 1;
    		this.notify();
            System.out.printf("%s 回血1点,增加血后，%s的血量是%.0f%n", name, name, hp);
    	}
    }
 
    public synchronized void hurt() {
        if (hp < 0) {
            try {
                // 让占有this的减血线程，暂时释放对this的占有，并等待
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
        	hp = hp - 1;
            System.out.printf("%s 减血1点,减少血后，%s的血量是%.0f%n", name, name, hp);
            this.notify();//降低血量之后告诉加血进程当前对象可以继续操作
        }
    }
    
    // 增加一个初始化name的构造方法
    public Hero(String name) {
  
        this.name = name;
    }
  
    //初始化name，hp，damage的构造方法
    public Hero(String name, float hp, int damage) {
		super();
		this.name = name;
		this.hp = hp;
		this.damage = damage;
	}

	// 重写toString方法
    public String toString() {
        return "[Hero:"+name+"="+hp+" damage:"+damage+"]";
    }

	@Override
	public int compareTo(Hero o) {
		if(damage<o.damage) {
			return 1;//当返回证书的时候说明this<o
		}else {
			return -1;
		}
	}
	
	public void attackHero(Hero h) {
		//把暂停时间去掉，多条线程各自会尽力去占有CPU资源
        //线程的优先级效果才可以看得出来
        /*try { 
            //为了表示攻击需要时间，每次攻击暂停1000毫秒，为了多线程演示
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        h.hp-=damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
        
        if(h.isDead())
            System.out.println(h.name +"死了！");
    }
 
    public boolean isDead() {
        return 0>=hp?true:false;
    }
}
