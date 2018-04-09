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
 * hashmapdemo�����hashmap�Ļ��������������˽�hash�㷨�Լ���;
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
	 * ��ֵ��ת
	 */
	public void chapter2() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("hero1", "�ٺ�");
		map.put("hero1", "�ٺ�2");
		map.put("hero3", "�ٺ�3");
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
	 * �Ƚ�ʹ��hashmap�Ͳ�ʹ��hashmap�Ĳ�ѯ��ʱ
	 */
	public void chapter1() {
		Random heroNo = new Random();
		List<Hero> heroList = new ArrayList<Hero>();
		HashMap<String, List<Hero>> heroMap = new HashMap<String, List<Hero>>();//��ͬ���ֵķ���һ��������
		for (int i = 0; i < 3000000; i++) {
			int rd = heroNo.nextInt(10000-1000+1)+1000;//ȡ��(10000,1000]֮�������
			heroList.add(new Hero("Hero-"+rd));
			//��ʼ��map����ͬ���ֵ�Ӣ�۶������һ��list��
			List<Hero> temp = heroMap.get("Hero-"+rd);
			if(temp==null) {
				temp = new ArrayList<Hero>();
				heroMap.put("Hero-"+rd, temp);
			}
			temp.add(new Hero("Hero-"+rd));
		}
		long start = System.currentTimeMillis();
		//ֱ��ͨ��for�ҵ�
		int i = 0;
		for (Hero hero : heroList) {
			if(hero.name.equals("Hero-5555")) {
				i++;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("forѭ���ҵ�"+i+"��Ԫ�غ�ʱ��"+(end-start)+"s");
		//ʹ��map������ֱ�ӻ�ȡkeyֵ��list
		start = System.currentTimeMillis();
		heroList = heroMap.get("Hero-5555");//hash
		end = System.currentTimeMillis();
		System.out.println("ֱ��ͨ��map�ҵ�"+heroList.size()+"��Ԫ�غ�ʱ��"+(end-start)+"s");
	}
}
