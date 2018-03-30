package demo.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import demo.hero.Hero;

/**
 * ���У��Ƚ��ȳ�����
 * @author suxin
 *
 */
public class QueueDemo {
	public static void main(String[] args) {
        //��ArrayListһ����LinkedListҲʵ����List�ӿ�
        List ll =new LinkedList<Hero>();
          
        //����ͬ����LinkedList��ʵ����Deque��������ʵ����Queue����ӿ�
        //Queue����FIFO �Ƚ��ȳ��Ķ���
        Queue<Hero> q= new LinkedList<Hero>();
          
        //���ڶ��е������
        System.out.print("��ʼ�����У�\t");
        q.offer(new Hero("Hero1"));
        q.offer(new Hero("Hero2"));
        q.offer(new Hero("Hero3"));
        q.offer(new Hero("Hero4"));
          
        System.out.println(q);
        System.out.print("�ѵ�һ��Ԫ��ȡpoll()����:\t");
        //ȡ����һ��Hero��FIFO �Ƚ��ȳ�
        Hero h = q.poll();
        System.out.println(h);
        System.out.print("ȡ����һ��Ԫ��֮��Ķ���:\t");
        System.out.println(q);
          
        //�ѵ�һ���ó�����һ�������ǲ�ȡ����
        h=q.peek();
        System.out.print("�鿴peek()��һ��Ԫ��:\t");
        System.out.println(h);
        System.out.print("�鿴�����ᵼ�µ�һ��Ԫ�ر�ȡ����:\t");
        System.out.println(q);
          
    }
}
