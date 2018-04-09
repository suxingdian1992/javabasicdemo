package demo.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

/**
 * hashsetDemo，hashset的简单操作，
 * hashset中不允许有重复元素，且元素排列顺序为乱序，不同jvm看到的顺序不一样哦
 * @author suxin
 *
 */
public class HashSetDemo {
	public static void main(String[] args) {
        HashSet<Integer> numbers = new HashSet<Integer>();
 
        numbers.add(9);
        numbers.add(5);
        numbers.add(1);
 
        // Set中的元素排列，不是按照插入顺序
        System.out.println(numbers);
 
        //遍历hashset
        for (Iterator<Integer> iterator = numbers.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			System.out.println(integer);
		}
        
      //或者采用增强型for循环
        for (Integer i : numbers) {
            System.out.println(i);
        }
        
        HashSetDemo test = new HashSetDemo();
        test.chapter1();
        test.chapter2();
        
        
        //其他的set
        /*HashSet： 无序
        LinkedHashSet： 按照插入顺序
        TreeSet： 从小到大排序 */
        
        HashSet<Integer> numberSet1 =new HashSet<Integer>();
        //HashSet中的数据不是按照插入顺序存放
        numberSet1.add(88);
        numberSet1.add(8);
        numberSet1.add(888);
          
        System.out.println(numberSet1);
          
        LinkedHashSet<Integer> numberSet2 =new LinkedHashSet<Integer>();
        //LinkedHashSet中的数据是按照插入顺序存放
        numberSet2.add(88);
        numberSet2.add(8);
        numberSet2.add(888);
          
        System.out.println(numberSet2);
        TreeSet<Integer> numberSet3 =new TreeSet<Integer>();
        //TreeSet 中的数据是进行了排序的
        numberSet3.add(88);
        numberSet3.add(8);
        numberSet3.add(888);
          
        System.out.println(numberSet3);
        
        test.chapter3();
    }
	
	/**
	 * 利用linkedHashSet即不重复又有顺序的特性显示Math.PI中所有的数字
	 */
	public void chapter3() {
		Double pi = Math.PI;
		char[] piChar = pi.toString().toCharArray();
		LinkedHashSet<Character> piLinkedHashSet =new LinkedHashSet<Character>();
		
		for (char c : piChar) {
			if(c!='.') {
				piLinkedHashSet.add(c);	
			}
		}
		System.out.println(piLinkedHashSet);
	}
	
	/**
	 * 产生五千个不重复的随机四位数
	 */
	public void chapter2() {
		Random rd =new Random();
		HashSet<Integer> strSet = new HashSet<Integer>();
		while(strSet.size()<5000) {
			strSet.add(rd.nextInt(9999-1000+1)+1000);
		}
		System.out.println(strSet);
	}
	
	/**
	 * 找到重复的字符
	 */
	public void chapter1() {
		String[] strs = new String[1000];
		HashSet<String> strSet = new HashSet<String>();
		//初始化字符串数组，存储一千个随机字符串
		for (int i = 0; i < strs.length; i++) {
			strs[i] = randomString(2);
		}
		
		//使用字符串数组操作
		Map<String,Integer> resMap = new HashMap<String,Integer>();//存放结果的list
		int count = 0;
		long start = System.currentTimeMillis();
		for (int i = 0; i < strs.length; i++) {
			count=0;
			for (int j = 0; j < strs.length; j++) {
				if(strs[i].equals(strs[j])) {
					count++;
				}
				if(count>1) {
					resMap.put(strs[i], count);
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("直接使用字符串数组查找的结果："+resMap);
		System.out.println("耗时："+(end-start));
		
		//hashset key值唯一，所以当插不进去的时候说明有重复的
		resMap.clear();
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			if(!strSet.add(strs[i])) {
				if(resMap.get(strs[i])!=null) {
					count = resMap.get(strs[i]);
					resMap.put(strs[i], count+1);
				}else {
					resMap.put(strs[i], 2);//重复的插入两个
				}
			}
		}
		end = System.currentTimeMillis();
		System.out.println("hashset查找的结果："+resMap);
		System.out.println("耗时："+(end-start));
		
	}
	
	/**
	 * 在ascii范围的字符内生成长度是2的随机字符串
	 * @param length
	 * @return
	 */
	private static String randomString(int length) {
        String pool = "";
        for (short i = '0'; i <= '9'; i++) {
            pool += (char) i;
        }
        for (short i = 'a'; i <= 'z'; i++) {
            pool += (char) i;
        }
        for (short i = 'A'; i <= 'Z'; i++) {
            pool += (char) i;
        }
        char cs[] = new char[length];
        for (int i = 0; i < cs.length; i++) {
            int index = (int) (Math.random() * pool.length());
            cs[i] = pool.charAt(index);
        }
        String result = new String(cs);
        return result;
    }
}
