package demo.thread;

/**
 * 集合之间的区别（线程安全与非线程安全）
 * 
 * @author suxin
 *
 */
public class DiffrenceInCollections {
	public static void main(String[] args) {
		//Hashtable<K, V>是线程安全的，内部所有的方法都是有synchronized标注
		//HashMap<K, V>是非线程安全的，内部所有的方法都不是通过synchronized标注的
		//StringBuffer 是线程安全的
		//StringBuilder 是非线程安全的
		//Vector<E>是线程安全的
		//ArrayList<E>是非线程安全的
		
		//作业，使用
		//Collections.synchronizedCollection(c)将一个非线程安全的集合转换为线程安全的集合，此处用我们自己的mystackdemo进行
	}
}
