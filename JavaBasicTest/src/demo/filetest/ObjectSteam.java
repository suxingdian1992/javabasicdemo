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
 * �������ļ򵥲���
 * @author suxin
 *
 */
public class ObjectSteam {
	public static void main(String[] args) {
		Hero h = new Hero();
		h.hp=123;
		h.name="Su";
		
		// ׼��һ���ļ��������������
		File f = new File("C:\\QMDownload\\SoftMgr\\hero.txt");

		try {
			// �������������
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// ��������������
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);

			//ʹ�ö����������������ļ���
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
	 * ���л�����
	 * @throws IOException 
	 */
	public static void objectList() throws IOException {
		
		//׼����������
		List<Hero> hList = new ArrayList<Hero>();
		for (int i = 0; i < 10; i++) {
			Hero h = new Hero();
			h.hp=i;
			h.name="sxd"+i;
			hList.add(h);
		}
		
		// ׼��һ���ļ��������������
		File f = new File("C:\\QMDownload\\SoftMgr\\hero.txt");
		
		// �������������
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// ��������������
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		for (Hero hero : hList) {
			oos.writeObject(hero);//���ļ���д���������
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
