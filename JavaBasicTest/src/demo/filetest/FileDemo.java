package demo.filetest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * �ļ�����������Ľ�����javaio�ļ��
 * 
 * @author suxin
 *
 */
public class FileDemo {
	public static void main(String[] args) {
		FileDemo fd = new FileDemo();
		/*
		 * fd.chapter1(); fd.chapter2(); try { fd.chapter3(); } catch (IOException e) {
		 * System.out.println("�ļ��쳣����"); e.printStackTrace(); }
		 */
		fd.chapter5();
	}

	long max = 0;// ����ļ���С
	String maxName = "";// ����ļ�����
	long min = Long.MAX_VALUE;// ��С�ļ���С
	String minName = "";// ��С�ļ�����

	/**
	 * ����һ��Ŀ¼�µ������ļ��������ļ��У���ѡ�����ĺ���С��
	 */
	public void chapter5() {
		String dir = "C:\\Users\\suxin\\Documents\\StarCraft II";
		//File f = new File(dir);
		findChildFile(dir);
		
		System.out.println("����ļ�Ϊ��" + maxName + "  ��С�ǣ�" + max);
		System.out.println("��С�ļ�Ϊ��" + minName + "  ��С�ǣ�" + min);
	}

	/**
	 * Ȼ��ͨ���ݹ�һ����Ѱ��Ŀ¼�µ������ļ������ļ��У��ļ��Ļ�����бȽϣ��ļ�·���Ļ�������ݹ��ѯ
	 * @param dir ������Ҫ��ѯ���ļ���·��
	 * 
	 */
	public void findChildFile(String dir) {
		File f = new File(dir);
		// ���ַ����������ʽ�����ص�ǰ�ļ����µ������ļ������������ļ������ļ��У�
		String[] str = f.list();

		for (String filename : str) {
			File ftemp = new File(dir + "\\" + filename);
			// �Ƿ����ļ������ļ��У�
			System.out.println("�ж��Ƿ����ļ�" + ftemp.getName() + "��" + ftemp.isFile());
			if (ftemp.isFile()) {
				if (max <= ftemp.length()) {
					max = ftemp.length();
					maxName = ftemp.getName();
				}
				if (min >= ftemp.length()) {
					min = ftemp.length();
					minName = ftemp.getName();
				}
			}
			else {
				findChildFile(dir + "\\" + filename);//�ݹ飬һ�δ���·���ٴβ��ң�ֱ����Χ�ļ���ѭ�����
			}
		}
	}

	/**
	 * ����һ��Ŀ¼�µ������ļ����������ļ��У���ѡ�����ĺ���С��
	 */
	public void chapter4() {
		String dir = "C:\\Users\\suxin\\Documents\\StarCraft II";
		File f = new File("C:\\Users\\suxin\\Documents\\StarCraft II");

		// ���ַ����������ʽ�����ص�ǰ�ļ����µ������ļ������������ļ������ļ��У�
		String[] str = f.list();

		for (String filename : str) {
			File ftemp = new File(dir + "\\" + filename);
			// �Ƿ����ļ������ļ��У�
			System.out.println("�ж��Ƿ����ļ�" + ftemp.getName() + "��" + ftemp.isFile());
			if (ftemp.isFile()) {
				if (max <= ftemp.length()) {
					max = ftemp.length();
					maxName = ftemp.getName();
				}
				if (min >= ftemp.length()) {
					min = ftemp.length();
					minName = ftemp.getName();
				}
			}
		}

		System.out.println("����ļ�Ϊ��" + maxName + "  ��С�ǣ�" + max);
		System.out.println("��С�ļ�Ϊ��" + minName + "  ��С�ǣ�" + min);
	}

	/**
	 * �ļ����÷���3
	 * 
	 * @throws IOException
	 */
	public void chapter3() throws IOException {
		File f = new File("C:\\QMDownload\\SoftMgr\\demo1.txt");

		// ���ַ����������ʽ�����ص�ǰ�ļ����µ������ļ������������ļ������ļ��У�
		String[] str = f.list();

		// ���ļ��������ʽ�����ص�ǰ�ļ����µ������ļ������������ļ������ļ��У�
		File[] fs = f.listFiles();

		// ���ַ�����ʽ���ػ�ȡ�����ļ���
		f.getParent();

		// ���ļ���ʽ���ػ�ȡ�����ļ���
		f.getParentFile();
		// �����ļ��У�������ļ���skin�����ڣ���������Ч
		f.mkdir();

		// �����ļ��У�������ļ���skin�����ڣ��ͻᴴ�����ļ���
		f.mkdirs();

		// ����һ�����ļ�,������ļ���skin�����ڣ��ͻ��׳��쳣
		f.createNewFile();
		// ���Դ���һ�����ļ�֮ǰ��ͨ�����ᴴ����Ŀ¼
		f.getParentFile().mkdirs();

		// �г����е��̷�c: d: e: �ȵ�
		f.listRoots();

		// �h���ļ�
		f.delete();

		// JVM������ʱ�򣬄h���ļ�����������ʱ�ļ���ɾ��
		f.deleteOnExit();
	}

	/**
	 * �ļ����÷���1
	 */
	public void chapter2() {
		File f = new File("C:\\QMDownload\\SoftMgr\\demo.txt");// ע�⣬�ļ�·���ķָ���ʹ��\����/������
		System.out.println("��ǰ�ļ��ǣ�" + f);
		// �ļ��Ƿ����
		System.out.println("�ж��Ƿ���ڣ�" + f.exists());

		// �Ƿ����ļ���
		System.out.println("�ж��Ƿ����ļ��У�" + f.isDirectory());

		// �Ƿ����ļ������ļ��У�
		System.out.println("�ж��Ƿ����ļ���" + f.isFile());

		// �ļ�����
		System.out.println("��ȡ�ļ��ĳ��ȣ�" + f.length());

		// �ļ�����޸�ʱ��
		long time = f.lastModified();
		Date d = new Date(time);
		System.out.println("��ȡ�ļ�������޸�ʱ�䣺" + d);
		// �����ļ��޸�ʱ��Ϊ1970.1.1 08:00:00
		String strDate = "1970/1/1 08:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			d = sdf.parse(strDate);
		} catch (ParseException e) {
			System.out.println("��ʽת������");
			e.printStackTrace();
		}
		// f.setLastModified(d.getTime());
		f.setLastModified(0);

		// �ļ�������
		// File f2 =new File("C:\\QMDownload\\SoftMgr\\dota.txt");
		// f.renameTo(f2);
		// System.out.println("��demo.txt��������DOTA.exe");

		System.out.println("ע�⣺ ��Ҫ��C:\\QMDownload\\SoftMgr\\ȷʵ����һ��demo.txt,\r\n�ſ��Կ�����Ӧ���ļ����ȡ��޸�ʱ�����Ϣ");
	}

	/**
	 * �½�1�����ļ�·������
	 */
	public void chapter1() {
		// ����·��
		File f1 = new File("C:/QMDownload/SoftMgr");
		System.out.println("f1�ľ���·����" + f1.getAbsolutePath());
		// ���·��,����ڹ���Ŀ¼�������eclipse�У�������ĿĿ¼
		File f2 = new File("LOL.exe");
		System.out.println("f2�ľ���·����" + f2.getAbsolutePath());

		// ��f1��Ϊ��Ŀ¼�����ļ�����
		File f3 = new File(f1, "LOL.exe");

		System.out.println("f3�ľ���·����" + f3.getAbsolutePath());
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	public String getMaxName() {
		return maxName;
	}

	public void setMaxName(String maxName) {
		this.maxName = maxName;
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public String getMinName() {
		return minName;
	}

	public void setMinName(String minName) {
		this.minName = minName;
	}

}
