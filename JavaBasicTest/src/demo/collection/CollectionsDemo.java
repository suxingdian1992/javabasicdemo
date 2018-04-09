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
 * 集合工具类操作demo
 * @author suxin
 *
 */
public class CollectionsDemo {
	public static void main(String[] args) {
		//初始化集合numbers
        List<Integer> numbers = new ArrayList<>();
         
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
         
        System.out.println("集合中的数据:");
        System.out.println(numbers);
         
        Collections.reverse(numbers);
         
        System.out.println("翻转后集合中的数据:");
        System.out.println(numbers);
        
        Collections.shuffle(numbers);
        System.out.println("混淆后集合中的数据:");
        System.out.println(numbers);
        
        Collections.sort(numbers);
        System.out.println("排序后集合中的数据:");
        System.out.println(numbers);
        
        Collections.swap(numbers,0,5);
        System.out.println("交换0和5下标的数据后，集合中的数据:");
        System.out.println(numbers);
        
        Collections.rotate(numbers,2);
        System.out.println("把集合向右滚动2个单位，标的数据后，集合中的数据:");
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
	 * 让treeset中的排序变成倒序
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
				// 默认会按照当前所选的排序项目从小到大排序，就是按照正序排列，如果调换返回值的正负则可以调换顺序
				if(o1.price>o2.price) {
					return 1;
				}else if(o1.price<o2.price){
					return -1;
				}else {
					return 0;
				}
			}
		};
		//此处会如果没有在item中没有实现排序的接口，所以报错，因为treeset不知道应该按照什么样子的规则排序
		TreeSet<Item> itemTreeSetsBefore = new TreeSet<Item>();
		for (int i = 0; i < items.size(); i++) {
			itemTreeSetsBefore.add(items.get(i));
		}
		System.out.println("自定义排序之前（在item中实现了默认排序接口）：”"+itemTreeSetsBefore);
		Collections.sort(items, comItem);//通过自定义排序索引实现直接对list排序
		//使用匿名类进行的变更，有了以下这一行之后可以不用写匿名类
		Collections.sort(items, (o1,o2)->o1.price - o2.price);//通过自定义排序索引实现直接对list排序
		
		
		System.out.println("排序之后的list："+items);
		TreeSet<Item> itemTreeSets = new TreeSet<Item>(comItem);
		for (int i = 0; i < items.size(); i++) {
			itemTreeSets.add(items.get(i));
		}
		System.out.println("自定义排序之后：”"+itemTreeSets);
	}
	
	/**
	 * 章节六，集合比较，比较之后才是正确顺序
	 */
	public void chapter6() {
        List<Hero> heros = new ArrayList<Hero>();
            
        for (int i = 0; i < 10; i++) {
            //通过随机值实例化hero的hp和damage
        	Hero h = new Hero("hero "+ i);
        	h.hp=(float)Math.random()*100;
            heros.add(h);
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);
            
        //直接调用sort会出现编译错误，因为Hero有各种属性
        //到底按照哪种属性进行比较，Collections也不知道，不确定，所以没法排
        //Collections.sort(heros);
            
        //引入Comparator，指定比较的算法，内部类直接实现比较方案
        Comparator<Hero> c = new Comparator<Hero>() {
            @Override
            public int compare(Hero h1, Hero h2) {
                //按照hp进行排序，自定义比较规则
                if(h1.hp>=h2.hp)
                    return 1;  //正数表示h1比h2要大
                else
                    return -1;
            }
        };
        Collections.sort(heros,c);//参数1：待比较的对象，参数2：比较器
        System.out.println("按照血量排序后的集合：");
        System.out.println(heros);
	}
	
	/**
	 * 在中间插入数据
	 */
	public void chapter5() {
		List<Integer> l;
		l = new ArrayList<>();
		insertMiddele(l, "ArrayList");

		l = new LinkedList<>();
		insertMiddele(l, "LinkedList");//定位序号的时间远远长过arraylist
	}
	private static void insertMiddele(List<Integer> l, String type) {
        int total = 100 * 1000;
        final int number = 5;
        //初始化
        for (int i = 0; i < total; i++) {
            l.add(number);
        }
         
        long start = System.currentTimeMillis();
 
        for (int i = 0; i < total; i++) {
             l.add(50,number+1);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%s总长度是%d，在中间插入数据 重复%d遍，总共耗时 %d 毫秒 %n", type,l.size(),total/2, end - start);
        System.out.println();
    }
	/**
	 * 比较arraylist和linkedlist在末尾插入数据的性能，两者在末尾插入的性能基本一致
	 */
	public void chapter4() {
		List<Integer> l;
		l = new ArrayList<>();
		insertlast(l, "ArrayList");

		l = new LinkedList<>();
		insertlast(l, "LinkedList");//定位序号的时间远远长过arraylist
	}
	private static void insertlast(List<Integer> l, String type) {
        int total = 100 * 1000;
        final int number = 5;
        //初始化
        for (int i = 0; i < total; i++) {
            l.add(number);
        }
         
        long start = System.currentTimeMillis();
 
        for (int i = 0; i < total; i++) {
             l.add(number+1);
        }
        long end = System.currentTimeMillis();
        System.out.printf("%s总长度是%d，在末尾插入数据 重复%d遍，总共耗时 %d 毫秒 %n", type,total,total, end - start);
        System.out.println();
    }
	/**
	 * 比较arraylist和linkedlist的定位数据性能
	 */
	public void chapter3() {
		List<Integer> l;
		l = new ArrayList<>();
		modify(l, "ArrayList");

		l = new LinkedList<>();
		modify(l, "LinkedList");//定位序号的时间远远长过arraylist
	}
	
	private static void modify(List<Integer> l, String type) {
        int total = 100 * 1000;
        int index = total/2;
        final int number = 5;
        //初始化
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
        System.out.printf("%s总长度是%d，定位到第%d个数据，取出来，加1，再放回去%n 重复%d遍，总共耗时 %d 毫秒 %n", type,total, index,total, end - start);
        System.out.println();
    }
	
	/**
	 * 比较arraylist和linkedlist的插入性能
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
        System.out.printf("在%s 最前面插入%d条数据，总共耗时 %d 毫秒 %n", type, total, end - start);
    }
	
	/**
	 * 对一个list进行1000000次混淆操作，看看前三位出现314的概率是多少
	 */
	public void chapter1() {
		//初始化集合numbers
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
		System.out.println("出现的几率是"+(count/1000000.0f));
	}
}
