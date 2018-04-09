package demo.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import demo.hero.Hero;
import demo.hero.Item;

/**
 * ���Ϲ��������demo
 * @author suxin
 *
 */
public class CollectionsDemo {
	public static void main(String[] args) {
		//��ʼ������numbers
        List<Integer> numbers = new ArrayList<>();
         
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
         
        System.out.println("�����е�����:");
        System.out.println(numbers);
         
        Collections.reverse(numbers);
         
        System.out.println("��ת�󼯺��е�����:");
        System.out.println(numbers);
        
        Collections.shuffle(numbers);
        System.out.println("�����󼯺��е�����:");
        System.out.println(numbers);
        
        Collections.sort(numbers);
        System.out.println("����󼯺��е�����:");
        System.out.println(numbers);
        
        Collections.swap(numbers,0,5);
        System.out.println("����0��5�±�����ݺ󣬼����е�����:");
        System.out.println(numbers);
        
        Collections.rotate(numbers,2);
        System.out.println("�Ѽ������ҹ���2����λ��������ݺ󣬼����е�����:");
        System.out.println(numbers);
        
        CollectionsDemo test = new CollectionsDemo();
        test.chapter1();
        //test.chapter2();
        //test.chapter3();
        //test.chapter4();
        //test.chapter5();
        //test.chapter6();
        test.chapter7();
	}
	
	/**
	 * ��treeset�е������ɵ���
	 */
	public void chapter7() {
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < 10; i++) {
			int x = (int)(Math.random()*(100-10+1)+10);
			Item item = new Item("item"+x,x);
			items.add(item);
		}
		
		Comparator<Item> comItem = new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				// Ĭ�ϻᰴ�յ�ǰ��ѡ��������Ŀ��С�������򣬾��ǰ����������У������������ֵ����������Ե���˳��
				if(o1.price>o2.price) {
					return 1;
				}else if(o1.price<o2.price){
					return -1;
				}else {
					return 0;
				}
			}
		};
		//�˴������û����item��û��ʵ������Ľӿڣ����Ա�����Ϊtreeset��֪��Ӧ�ð���ʲô���ӵĹ�������
		TreeSet<Item> itemTreeSetsBefore = new TreeSet<Item>();
		for (int i = 0; i < items.size(); i++) {
			itemTreeSetsBefore.add(items.get(i));
		}
		System.out.println("�Զ�������֮ǰ����item��ʵ����Ĭ������ӿڣ�����"+itemTreeSetsBefore);
		Collections.sort(items, comItem);//ͨ���Զ�����������ʵ��ֱ�Ӷ�list����
		//ʹ����������еı��������������һ��֮����Բ���д������
		Collections.sort(items, (o1,o2)->o1.price - o2.price);//ͨ���Զ�����������ʵ��ֱ�Ӷ�list����
		
		
		System.out.println("����֮���list��"+items);
		TreeSet<Item> itemTreeSets = new TreeSet<Item>(comItem);
		for (int i = 0; i < items.size(); i++) {
			itemTreeSets.add(items.get(i));
		}
		System.out.println("�Զ�������֮�󣺡�"+itemTreeSets);
	}
	
	/**
	 * �½��������ϱȽϣ��Ƚ�֮�������ȷ˳��
	 */
	public void chapter6() {
        List<Hero> heros = new ArrayList<Hero>();
            
        for (int i = 0; i < 10; i++) {
            //ͨ�����ֵʵ����hero��hp��damage
        	Hero h = new Hero("hero "+ i);
        	h.hp=(float)Math.random()*100;
            heros.add(h);
        }
        System.out.println("��ʼ����ļ��ϣ�");
        System.out.println(heros);
            
        //ֱ�ӵ���sort����ֱ��������ΪHero�и�������
        //���װ����������Խ��бȽϣ�CollectionsҲ��֪������ȷ��������û����
        //Collections.sort(heros);
            
        //����Comparator��ָ���Ƚϵ��㷨���ڲ���ֱ��ʵ�ֱȽϷ���
        Comparator<Hero> c = new Comparator<Hero>() {
            @Override
            public int compare(Hero h1, Hero h2) {
                //����hp���������Զ���ȽϹ���
                if(h1.hp>=h2.hp)
                    return 1;  //������ʾh1��h2Ҫ��
                else
                    return -1;
            }
        };
        Collections.sort(heros,c);//����1�����ȽϵĶ��󣬲���2���Ƚ���
        System.out.println("����Ѫ�������ļ��ϣ�");
        System.out.println(heros);
	}
	
	/**
	 * ���м��������
	 */
	public void chapter5() {
		List<Integer> l;
		l = new ArrayList<>();
		insertMiddele(l, "ArrayList");

		l = new LinkedList<>();
		insertMiddele(l, "LinkedList");//��λ��ŵ�ʱ��ԶԶ����arraylist
	}
	private static void insertMiddele(List<Integer> l, String type) {
        int total = 100 * 1000;
        final int number = 5;
        //��ʼ��
        for (int i = 0; i < total; i++) {
            l.add(number);
        }
         
        long start = System.currentTimeMillis();
 
        for (int i = 0; i < total; i++) {
             l.add(50,number+1);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%s�ܳ�����%d�����м�������� �ظ�%d�飬�ܹ���ʱ %d ���� %n", type,l.size(),total/2, end - start);
        System.out.println();
    }
	/**
	 * �Ƚ�arraylist��linkedlist��ĩβ�������ݵ����ܣ�������ĩβ��������ܻ���һ��
	 */
	public void chapter4() {
		List<Integer> l;
		l = new ArrayList<>();
		insertlast(l, "ArrayList");

		l = new LinkedList<>();
		insertlast(l, "LinkedList");//��λ��ŵ�ʱ��ԶԶ����arraylist
	}
	private static void insertlast(List<Integer> l, String type) {
        int total = 100 * 1000;
        final int number = 5;
        //��ʼ��
        for (int i = 0; i < total; i++) {
            l.add(number);
        }
         
        long start = System.currentTimeMillis();
 
        for (int i = 0; i < total; i++) {
             l.add(number+1);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%s�ܳ�����%d����ĩβ�������� �ظ�%d�飬�ܹ���ʱ %d ���� %n", type,total,total, end - start);
        System.out.println();
    }
	/**
	 * �Ƚ�arraylist��linkedlist�Ķ�λ��������
	 */
	public void chapter3() {
		List<Integer> l;
		l = new ArrayList<>();
		modify(l, "ArrayList");

		l = new LinkedList<>();
		modify(l, "LinkedList");//��λ��ŵ�ʱ��ԶԶ����arraylist
	}
	
	private static void modify(List<Integer> l, String type) {
        int total = 100 * 1000;
        int index = total/2;
        final int number = 5;
        //��ʼ��
        for (int i = 0; i < total; i++) {
            l.add(number);
        }
         
        long start = System.currentTimeMillis();
 
        for (int i = 0; i < total; i++) {
             int n = l.get(index);
             n++;
             l.set(index, n);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%s�ܳ�����%d����λ����%d�����ݣ�ȡ��������1���ٷŻ�ȥ%n �ظ�%d�飬�ܹ���ʱ %d ���� %n", type,total, index,total, end - start);
        System.out.println();
    }
	
	/**
	 * �Ƚ�arraylist��linkedlist�Ĳ�������
	 */
	public void chapter2() {
		List<Integer> l;
        l = new ArrayList<>();
        insertFirst(l, "ArrayList");
 
        l = new LinkedList<>();
        insertFirst(l, "LinkedList");
	}
	
	private static void insertFirst(List<Integer> l, String type) {
        int total = 1000 * 100;
        final int number = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            l.add(0, number);
        }
        long end = System.currentTimeMillis();
        System.out.printf("��%s ��ǰ�����%d�����ݣ��ܹ���ʱ %d ���� %n", type, total, end - start);
    }
	
	/**
	 * ��һ��list����1000000�λ�������������ǰ��λ����314�ĸ����Ƕ���
	 */
	public void chapter1() {
		//��ʼ������numbers
        List<Integer> numbers = new ArrayList<>();
        Random rd = new Random();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
		int count = 0;
		for (int i = 0; i < 1000000; i++) {
			Collections.shuffle(numbers);
			if (numbers.get(0) == 3 && numbers.get(1) == 1 && numbers.get(2) == 4) {
				count++;
			}
		}
		System.out.println("���ֵļ�����"+(count/1000000.0f));
	}
}
