package demo.thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
 
class MyStack_syn {
    List<Character> l = Collections.synchronizedList(new ArrayList<Character>());
    public synchronized void  push(char h) {
        if(l.size()>=20) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            l.add(h);
            System.out.println("压入"+h+" 个数"+l.size());
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            this.notify();
        }
    }
 
    public synchronized char pull() {
        if(l.size()>0) {
            char h = l.get(l.size()-1);
            l.remove(l.size()-1);
            System.out.println("弹出"+h+"  个数"+l.size());
            this.notify();
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return h;
        }
        else {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             
        }
        return 0;
    }
 
    public  char peek() {
        return l.get(l.size()-1);
    }
    public static void main(String[] arg) {
        Random r = new Random();
        MyStack_syn syn = new MyStack_syn();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while(true)
                syn.push((char)(65+r.nextInt(26)));
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while(true) {
                syn.pull();
                }
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                while(true) {
                syn.pull();
                }
            }
        };
      Thread t4 = new Thread() {
          @Override
          public void run() {
              while(true)
              syn.push((char)(65+r.nextInt(26)));
          }
      };
      Thread t5 = new Thread() {
          @Override
          public void run() {
              while(true) {
              syn.pull();
              }
          }
      };
        syn.push('a');
        t1.start();
        t2.start();
        t3.start();
      t4.start();
      t5.start();
    }

}
     
 
     
