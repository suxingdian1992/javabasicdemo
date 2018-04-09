package demo.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.synth.SynthSeparatorUI;

import demo.hero.Hero;

/**
 * 讲解hashmap和hashcode原理和字典以及分析性能
 * @author suxin
 *
 */
public class MyHashDemo {

	public static void main(String[] args) {
		Random rd = new Random();
		for (int i = 0; i < 100; i++) {
			String str = randomString(rd.nextInt(10-2+1)+2);//随机生成长度在2到10之间的字符串
			System.out.println(myHashCode(str));
		}
		//自定义hashmap测试
		MyHashMap myhashmap = new MyHashMap();
		myhashmap.put("sxd", "233");
		myhashmap.put("sxd", "2333");
		myhashmap.put("sxd", "23333");
		
		List<Entry> resultList =  myhashmap.get("sxd");
		for (Entry entry : resultList) {
			System.out.println(entry);
		}
		
		//比较查找性能
		
		List<Hero> list = new ArrayList<>();
		MyHashMap myhashmapdemo = new MyHashMap();
		for (int i = 0; i < 5000000; i++) {
			int heroNo = (int)(Math.random()*(9999-1000+1)+1000);
			list.add(new Hero("hero-"+heroNo));
			myhashmapdemo.put("hero-"+heroNo, new Hero("hero-"+heroNo));
		}
		//直接遍历arraylist
		long start = System.currentTimeMillis();
		int count =0;
		for (Hero hero : list) {
			if(hero.name.equals("hero-5555")) {
				count++;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("直接循环arraylist得到："+count+"个元素，耗时："+(end-start)+"ms");
		//使用myhashmap查找
		start = System.currentTimeMillis();
		List<Entry> enHeroList = myhashmapdemo.get("hero-5555");
		end = System.currentTimeMillis();
		System.out.println("循环myhashmap得到："+enHeroList.size()+"个元素，耗时："+(end-start)+"ms");
		
				
	}
	 
	/**
	 * 自定义的hashcode生成方法
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

class MyHashMap implements IHashMap{//自定义实现的hashmap

	private Object[] mhm = new Object[2000];//内部存放键值对的两千个对象数组
	
	public MyHashMap() {//初始化方法
		/*初始化的时候为当前对象数组的每一个放入一个linkedList<entry>对象*/
		for (Object object : mhm) {
			object = new LinkedList<Entry>();
		}
	}
	@Override
	public void put(String key, Object object) {
		// TODO Auto-generated method stub
		int code = MyHashDemo.myHashCode(key);
		if(mhm[code]==null) {//如果当前节点中没有值为空，则为当前节点新插入一个entry
			LinkedList<Entry> linkedList = new LinkedList<Entry>();
			linkedList.add(new Entry(key, object));
			mhm[code] = linkedList;
		}else {//如果当前节点不为空则取出当前节点的linkedlist，然后添加一个新的entry，然后为当前节点重新赋值为新的linkedlist
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
//定义myhashmap的put方法和get方法
interface IHashMap {
    public void put(String key,Object object);
    public Object get(String key);
}

class Entry{//键值对
	//键和值
	public Object key;
	public Object value;
	
	public Entry(Object key,Object value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	//重写输出方法，输出当前节点
	public String toString() {
		return "[key="+key+",value="+value+"]";
	}
}
