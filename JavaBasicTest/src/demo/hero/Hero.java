package demo.hero;

import java.io.Serializable;

/**
 * Ӣ�۵Ĺ�����
 * @author suxin
 *
 */
public class Hero extends LOL implements Serializable,Comparable<Hero>{
	/**
	 * ��ʾ��ǰ�����İ汾����������������ԣ���һ�����޸�����汾��
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
        System.out.printf("%s ��Ѫ1��,����Ѫ��%s��Ѫ����%.0f%n", name, name, hp);
        // ֪ͨ��Щ�ȴ���this�����ϵ��̣߳������ѹ����ˣ����20�У��ȴ��ŵļ�Ѫ�̣߳����ѹ���
        this.notify();
    }
    
    public synchronized void recover1() {
        
        if(hp > 1000) {
    		try {
    			//������Ҫռ�����������̣߳����ﲻ��������
				this.wait();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else {
    		hp = hp + 1;
    		this.notify();
            System.out.printf("%s ��Ѫ1��,����Ѫ��%s��Ѫ����%.0f%n", name, name, hp);
    	}
    }
 
    public synchronized void hurt() {
        if (hp < 0) {
            try {
                // ��ռ��this�ļ�Ѫ�̣߳���ʱ�ͷŶ�this��ռ�У����ȴ�
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
        	hp = hp - 1;
            System.out.printf("%s ��Ѫ1��,����Ѫ��%s��Ѫ����%.0f%n", name, name, hp);
            this.notify();//����Ѫ��֮����߼�Ѫ���̵�ǰ������Լ�������
        }
    }
    
    // ����һ����ʼ��name�Ĺ��췽��
    public Hero(String name) {
  
        this.name = name;
    }
  
    //��ʼ��name��hp��damage�Ĺ��췽��
    public Hero(String name, float hp, int damage) {
		super();
		this.name = name;
		this.hp = hp;
		this.damage = damage;
	}

	// ��дtoString����
    public String toString() {
        return "[Hero:"+name+"="+hp+" damage:"+damage+"]";
    }

	@Override
	public int compareTo(Hero o) {
		if(damage<o.damage) {
			return 1;//������֤���ʱ��˵��this<o
		}else {
			return -1;
		}
	}
	
	public void attackHero(Hero h) {
		//����ͣʱ��ȥ���������̸߳��Իᾡ��ȥռ��CPU��Դ
        //�̵߳����ȼ�Ч���ſ��Կ��ó���
        /*try { 
            //Ϊ�˱�ʾ������Ҫʱ�䣬ÿ�ι�����ͣ1000���룬Ϊ�˶��߳���ʾ
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        h.hp-=damage;
        System.out.format("%s ���ڹ��� %s, %s��Ѫ����� %.0f%n",name,h.name,h.name,h.hp);
        
        if(h.isDead())
            System.out.println(h.name +"���ˣ�");
    }
 
    public boolean isDead() {
        return 0>=hp?true:false;
    }
}
