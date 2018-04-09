package demo.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.synth.SynthSeparatorUI;

import demo.hero.Hero;

/**
 * ����hashmap��hashcodeԭ����ֵ��Լ���������
 * @author suxin
 *
 */
public class MyHashDemo {

	public static void main(String[] args) {
		Random rd = new Random();
		for (int i = 0; i < 100; i++) {
			String str = randomString(rd.nextInt(10-2+1)+2);//������ɳ�����2��10֮����ַ���
			System.out.println(myHashCode(str));
		}
		//�Զ���hashmap����
		MyHashMap myhashmap = new MyHashMap();
		myhashmap.put("sxd", "233");
		myhashmap.put("sxd", "2333");
		myhashmap.put("sxd", "23333");
		
		List<Entry> resultList =  myhashmap.get("sxd");
		for (Entry entry : resultList) {
			System.out.println(entry);
		}
		
		//�Ƚϲ�������
		
		List<Hero> list = new ArrayList<>();
		MyHashMap myhashmapdemo = new MyHashMap();
		for (int i = 0; i < 5000000; i++) {
			int heroNo = (int)(Math.random()*(9999-1000+1)+1000);
			list.add(new Hero("hero-"+heroNo));
			myhashmapdemo.put("hero-"+heroNo, new Hero("hero-"+heroNo));
		}
		//ֱ�ӱ���arraylist
		long start = System.currentTimeMillis();
		int count =0;
		for (Hero hero : list) {
			if(hero.name.equals("hero-5555")) {
				count++;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("ֱ��ѭ��arraylist�õ���"+count+"��Ԫ�أ���ʱ��"+(end-start)+"ms");
		//ʹ��myhashmap����
		start = System.currentTimeMillis();
		List<Entry> enHeroList = myhashmapdemo.get("hero-5555");
		end = System.currentTimeMillis();
		System.out.println("ѭ��myhashmap�õ���"+enHeroList.size()+"��Ԫ�أ���ʱ��"+(end-start)+"ms");
		
				
	}
	 
	/**
	 * �Զ����hashcode���ɷ���
	 * @param str
	 * @return
	 */
	public static int myHashCode(String str) {
		char[] cArray = str.toCharArray();
		int result = 0;
		for (int i = 0; i < cArray.length; i++) {
			result+=(int)cArray[i];
		}
		result = result*23;
		if(result>1999) {
			result = result % 2000;
		}
		if(result<0) {
			result = Math.abs(result);
		}
		return result;
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

class MyHashMap implements IHashMap{//�Զ���ʵ�ֵ�hashmap

	private Object[] mhm = new Object[2000];//�ڲ���ż�ֵ�Ե���ǧ����������
	
	public MyHashMap() {//��ʼ������
		/*��ʼ����ʱ��Ϊ��ǰ���������ÿһ������һ��linkedList<entry>����*/
		for (Object object : mhm) {
			object = new LinkedList<Entry>();
		}
	}
	@Override
	public void put(String key, Object object) {
		// TODO Auto-generated method stub
		int code = MyHashDemo.myHashCode(key);
		if(mhm[code]==null) {//�����ǰ�ڵ���û��ֵΪ�գ���Ϊ��ǰ�ڵ��²���һ��entry
			LinkedList<Entry> linkedList = new LinkedList<Entry>();
			linkedList.add(new Entry(key, object));
			mhm[code] = linkedList;
		}else {//�����ǰ�ڵ㲻Ϊ����ȡ����ǰ�ڵ��linkedlist��Ȼ�����һ���µ�entry��Ȼ��Ϊ��ǰ�ڵ����¸�ֵΪ�µ�linkedlist
			LinkedList<Entry> linkedList = (LinkedList<Entry>) mhm[code];
			linkedList.add(new Entry(key, object));
			mhm[code]=linkedList;
		}
	}

	@Override
	public List<Entry> get(String key) {
		// TODO Auto-generated method stub
		int code = MyHashDemo.myHashCode(key);
		if(mhm[code]==null) {
			return null;
		}else {
			List<Entry> listResult = new ArrayList<Entry>();
			LinkedList<Entry> linkedList = (LinkedList<Entry>) mhm[code];
			for (Entry entry : linkedList) {
				if(entry.key.equals(key)) {
					listResult.add(entry);
				}
			}
			return listResult;
		}
	}
	
}
//����myhashmap��put������get����
interface IHashMap {
    public void put(String key,Object object);
    public Object get(String key);
}

class Entry{//��ֵ��
	//����ֵ
	public Object key;
	public Object value;
	
	public Entry(Object key,Object value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	//��д��������������ǰ�ڵ�
	public String toString() {
		return "[key="+key+",value="+value+"]";
	}
}
