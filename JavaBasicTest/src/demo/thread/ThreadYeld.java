package demo.thread;
  
import demo.hero.*;
  
public class ThreadYeld {
  
    public static void main(String[] args) {
          
        final Hero gareen = new Hero();
        gareen.name = "����1";
        gareen.hp = 61600;
        gareen.damage = 1;
  
        final Hero teemo = new Hero();
        teemo.name = "��Ī1";
        teemo.hp = 450;
        teemo.damage = 1;
          
        final Hero bh = new Hero();
        bh.name = "�ͽ�����2";
        bh.hp = 50000;
        bh.damage = 1;
          
        final Hero leesin = new Hero();
        leesin.name = "äɮ2";
        leesin.hp = 450;
        leesin.damage = 1;
        
        final Hero tx = new Hero();
        tx.name = "����ʿ3";
        tx.hp = 50000;
        tx.damage = 1;
          
        final Hero rt = new Hero();
        rt.name = "����ʦ3";
        rt.hp = 450;
        rt.damage = 1;
          
        Thread t1= new Thread(){
            public void run(){
 
                while(!teemo.isDead()){
                    gareen.attackHero(teemo);
                }              
            }
        };
          
        Thread t2= new Thread(){
            public void run(){
                while(!leesin.isDead()){
                    //��ʱ��ͣ��ʹ��t1����ռ��CPU��Դ
                    Thread.yield();
                     
                    bh.attackHero(leesin);
                }              
            }
        };
        
        Thread t3= new Thread(){
            public void run(){
                while(!rt.isDead()){
                    tx.attackHero(rt);
                }              
            }
        };
         
        t1.setPriority(2);
        t2.setPriority(5);
        t3.setPriority(5);
        t1.start();
        t2.start();
        t3.start();
          
    }
}