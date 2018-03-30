package demo.filetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 对象流的简单操作
 * @author suxin
 *
 */
public class ObjectSteam {
	public static void main(String[] args) {
		Hero h = new Hero();
		h.hp=123;
		h.name="Su";
		
		// 准备一个文件来保存这个对象
		File f = new File("C:\\QMDownload\\SoftMgr\\hero.txt");

		try {
			// 创建对象输出流
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// 创建对象输入流
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);

			//使用对象流输出输出对象到文件中
			oos.writeObject(h);
			Hero h2 = (Hero) ois.readObject();
			System.out.println(h2.name);
			System.out.println(h2.hp);

			objectList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 序列化数组
	 * @throws IOException 
	 */
	public static void objectList() throws IOException {
		
		//准备对象数组
		List<Hero> hList = new ArrayList<Hero>();
		for (int i = 0; i < 10; i++) {
			Hero h = new Hero();
			h.hp=i;
			h.name="sxd"+i;
			hList.add(h);
		}
		
		// 准备一个文件来保存这个对象
		File f = new File("C:\\QMDownload\\SoftMgr\\hero.txt");
		
		// 创建对象输出流
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// 创建对象输入流
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		for (Hero hero : hList) {
			oos.writeObject(hero);//向文件中写入对象数组
		}
		int i =0;
		while(i<hList.size()) {
			try {
				Hero h;
				h = (Hero)ois.readObject();
				System.out.println(h.name);
				System.out.println(h.hp);
				i++;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
