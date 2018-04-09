package demo.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import demo.hero.APHero;
import demo.hero.Hero;
import demo.hero.Item;
import demo.hero.LOL;

/**
 * 泛型测试，比如list，不指定泛型的话就可以装入各种类型的对象，
 * 指定了泛型则只能放指定类型的对象
 * 泛型基本都是要指定的，防止转换的时候出现问题
 * @author suxin
 */
public class GenericDemo {
	public static void main(String[] args) {

		// 对于不使用泛型的容器，可以往里面放英雄，也可以往里面放物品
		List heros = new ArrayList();

		heros.add(new Hero("盖伦"));

		// 本来用于存放英雄的容器，现在也可以存放物品了
		heros.add(new Item("冰杖",123));

		// 对象转型会出现问题
		Hero h1 = (Hero) heros.get(0);
		System.out.println(h1.name);
		// 尤其是在容器里放的对象太多的时候，就记不清楚哪个位置放的是哪种类型的对象了
		// Hero h2= (Hero) heros.get(1);

		// 引入泛型Generic
		// 声明容器的时候，就指定了这种容器，只能放Hero，放其他的就会出错
		List<Hero> genericheros = new ArrayList<Hero>();
		genericheros.add(new Hero("盖伦"));
		// 如果不是Hero类型，根本就放不进去
		// genericheros.add(new Item("冰杖"));

		// 除此之外，还能存放Hero的子类
		genericheros.add(new APHero());

		// 并且在取出数据的时候，不需要再进行转型了，因为里面肯定是放的Hero或者其子类
		Hero h = genericheros.get(0);
		
		GenericDemo test = new GenericDemo();
		test.chapter3();

	}
	
	/**
	 * 练习，想办法让arraylist只能放入hero和item对象
	 */
	public void chapter1() {
		List<LOL> list = new ArrayList<LOL>();
		
		//设计一个两者的共有父类即可
		list.add(new Hero("变身"));
		list.add(new Item("冰杖",123));
	}
	
	/**
	 * 遍历集合
	 */
	public void chapter2() {
		List<LOL> heros = new ArrayList<LOL>();

		// 放5个Hero进入容器
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero name " + i));
        }
 
        // 第一种遍历 for循环
        System.out.println("--------常规 for 循环-------");
        for (int i = 0; i < heros.size(); i++) {
            Hero h = (Hero) heros.get(i);
            System.out.println(h);
        }
        
        //第二种增强for
        System.out.println("--------增强形式的for循环");
        for (LOL lol : heros) {
        	Hero h = (Hero)lol;
			System.out.println(h.name);
		}
        
        //第三种迭代器遍历
        System.out.println("--------使用while的iterator-------");
        Iterator<LOL> it= heros.iterator();
        //从最开始的位置判断"下一个"位置是否有数据
        //如果有就通过next取出来，并且把指针向下移动
        //直达"下一个"位置没有数据
        while(it.hasNext()){
            Hero h = (Hero) it.next();//注意，hasnext不会移动指针，只是查看下面还有没有元素，但是next会向后移动一次指针
            System.out.println(h);
        }
        //迭代器的for写法
        System.out.println("--------使用for的iterator-------");
        for (Iterator<LOL> iterator = heros.iterator(); iterator.hasNext();) {
            Hero hero = (Hero) iterator.next();
            System.out.println(hero);
        }
 
	}
	
	//作业：
	/**
	 * 首先初始化一个Hero集合，里面放100个Hero对象，名称分别是从 hero 0 hero 1 hero 2 ... hero 99.
	 * 通过遍历的手段，删除掉名字编号是8的倍数的对象
	 */
	public void chapter3() {
		List<LOL> heros = new ArrayList<LOL>();

		// 放5个Hero进入容器
        for (int i = 0; i < 100; i++) {
            heros.add(new Hero("hero name " + i));
        }
        
        List<Integer> iList = new ArrayList<Integer>();
        //for写法去除元素
        for (int i = 0; i < heros.size(); i++) {
        	Hero h = (Hero) heros.get(i);//注意，hasnext不会移动指针，只是查看下面还有没有元素，但是next会向后移动一次指针
            String[] strs = h.name.split(" ");
            if(Integer.parseInt(strs[2])%8==0) {
            	heros.remove(i);
            	i--;
            }
		}
        
        
        Iterator<LOL> it = heros.iterator();
        while(it.hasNext()){
            Hero h = (Hero) it.next();//注意，hasnext不会移动指针，只是查看下面还有没有元素，但是next会向后移动一次指针
            System.out.println(h);
        }
	}
}

/**
 * 支持泛型的类
 * @author suxin
 * @param <T>
 */
class MyStackGeneric<T> {
	   
    LinkedList<T> values = new LinkedList<T>();
       
    public void push(T t) {
        values.addLast(t);
    }
   
    public T pull() {
        return values.removeLast();
    }
   
    public T peek() {
        return values.getLast();
    }
       
    public static void main(String[] args) {
        //在声明这个Stack的时候，使用泛型<Hero>就表示该Stack只能放Hero
    	MyStackGeneric<Hero> heroStack = new MyStackGeneric<>();
        heroStack.push(new Hero());
        //不能放Item
        //heroStack.push(new Item());
         
        //在声明这个Stack的时候，使用泛型<Item>就表示该Stack只能放Item
        MyStackGeneric<Item> itemStack = new MyStackGeneric<>();
        itemStack.push(new Item());
        //不能放Hero
        //itemStack.push(new Hero());
    }
   
}
