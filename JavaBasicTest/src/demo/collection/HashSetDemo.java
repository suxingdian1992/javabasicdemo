package demo.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

/**
 * hashsetDemo��hashset�ļ򵥲�����
 * hashset�в��������ظ�Ԫ�أ���Ԫ������˳��Ϊ���򣬲�ͬjvm������˳��һ��Ŷ
 * @author suxin
 *
 */
public class HashSetDemo {
	public static void main(String[] args) {
        HashSet<Integer> numbers = new HashSet<Integer>();
 
        numbers.add(9);
        numbers.add(5);
        numbers.add(1);
 
        // Set�е�Ԫ�����У����ǰ��ղ���˳��
        System.out.println(numbers);
 
        //����hashset
        for (Iterator<Integer> iterator = numbers.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			System.out.println(integer);
		}
        
      //���߲�����ǿ��forѭ��
        for (Integer i : numbers) {
            System.out.println(i);
        }
        
        HashSetDemo test = new HashSetDemo();
        test.chapter1();
        test.chapter2();
        
        
        //������set
        /*HashSet�� ����
        LinkedHashSet�� ���ղ���˳��
        TreeSet�� ��С�������� */
        
        HashSet<Integer> numberSet1 =new HashSet<Integer>();
        //HashSet�е����ݲ��ǰ��ղ���˳����
        numberSet1.add(88);
        numberSet1.add(8);
        numberSet1.add(888);
          
        System.out.println(numberSet1);
          
        LinkedHashSet<Integer> numberSet2 =new LinkedHashSet<Integer>();
        //LinkedHashSet�е������ǰ��ղ���˳����
        numberSet2.add(88);
        numberSet2.add(8);
        numberSet2.add(888);
          
        System.out.println(numberSet2);
        TreeSet<Integer> numberSet3 =new TreeSet<Integer>();
        //TreeSet �е������ǽ����������
        numberSet3.add(88);
        numberSet3.add(8);
        numberSet3.add(888);
          
        System.out.println(numberSet3);
        
        test.chapter3();
    }
	
	/**
	 * ����linkedHashSet�����ظ�����˳���������ʾMath.PI�����е�����
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
	 * ������ǧ�����ظ��������λ��
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
	 * �ҵ��ظ����ַ�
	 */
	public void chapter1() {
		String[] strs = new String[1000];
		HashSet<String> strSet = new HashSet<String>();
		//��ʼ���ַ������飬�洢һǧ������ַ���
		for (int i = 0; i < strs.length; i++) {
			strs[i] = randomString(2);
		}
		
		//ʹ���ַ����������
		Map<String,Integer> resMap = new HashMap<String,Integer>();//��Ž����list
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
		System.out.println("ֱ��ʹ���ַ���������ҵĽ����"+resMap);
		System.out.println("��ʱ��"+(end-start));
		
		//hashset keyֵΨһ�����Ե��岻��ȥ��ʱ��˵�����ظ���
		resMap.clear();
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			if(!strSet.add(strs[i])) {
				if(resMap.get(strs[i])!=null) {
					count = resMap.get(strs[i]);
					resMap.put(strs[i], count+1);
				}else {
					resMap.put(strs[i], 2);//�ظ��Ĳ�������
				}
			}
		}
		end = System.currentTimeMillis();
		System.out.println("hashset���ҵĽ����"+resMap);
		System.out.println("��ʱ��"+(end-start));
		
	}
	
	/**
	 * ��ascii��Χ���ַ������ɳ�����2������ַ���
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
