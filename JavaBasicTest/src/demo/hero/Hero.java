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
    
    public Hero() {
    	  
    }
  
  //回血
    public void recover(){
        hp=hp+1;
    }
     
    //掉血
    public void hurt(){
        hp=hp-1;
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
