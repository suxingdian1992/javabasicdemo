package demo.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import demo.hero.Hero;

/**
 * hashmapdemo，详解hashmap的基本操作，需求了解hash算法以及用途
 * @author suxin
 *
 */
public class HashMapDemo {
	public static void main(String[] args) {
		HashMapDemo test = new HashMapDemo();
		test.chapter1();
		test.chapter2();
	}
	
	/**
	 * 键值反转
	 */
	public void chapter2() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hero1", "嘿嘿");
		map.put("hero1", "嘿嘿2");
		map.put("hero3", "嘿嘿3");
		System.out.println(map);
		HashMap<String ,String >newMap = new HashMap<>();
        for (String key : map.keySet()) {
            newMap.put(map.get(key),key);
        }
        map.clear();
        map.putAll(newMap);
        System.out.println(map);
		
	}
	
	/**
	 * 比较使用hashmap和不使用hashmap的查询耗时
	 */
	public void chapter1() {
		Random heroNo = new Random();
		List<Hero> heroList = new ArrayList<Hero>();
		HashMap<String, List<Hero>> heroMap = new HashMap<String, List<Hero>>();//相同名字的放在一個集合中
		for (int i = 0; i < 3000000; i++) {
			int rd = heroNo.nextInt(10000-1000+1)+1000;//取到(10000,1000]之间的整数
			heroList.add(new Hero("Hero-"+rd));
			//初始化map，相同名字的英雄对象放在一个list里
			List<Hero> temp = heroMap.get("Hero-"+rd);
			if(temp==null) {
				temp = new ArrayList<Hero>();
				heroMap.put("Hero-"+rd, temp);
			}
			temp.add(new Hero("Hero-"+rd));
		}
		long start = System.currentTimeMillis();
		//直接通过for找到
		int i = 0;
		for (Hero hero : heroList) {
			if(hero.name.equals("Hero-5555")) {
				i++;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("for循环找到"+i+"个元素耗时："+(end-start)+"s");
		//使用map集合来直接获取key值的list
		start = System.currentTimeMillis();
		heroList = heroMap.get("Hero-5555");//hash
		end = System.currentTimeMillis();
		System.out.println("直接通过map找到"+heroList.size()+"个元素耗时："+(end-start)+"s");
	}
}
