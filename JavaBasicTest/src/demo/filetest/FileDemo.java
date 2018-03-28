package demo.filetest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件输入输出流的解析，javaio的简介
 * 
 * @author suxin
 *
 */
public class FileDemo {
	public static void main(String[] args) {
		FileDemo fd = new FileDemo();
		/*
		 * fd.chapter1(); fd.chapter2(); try { fd.chapter3(); } catch (IOException e) {
		 * System.out.println("文件异常捕获"); e.printStackTrace(); }
		 */
		fd.chapter5();
	}

	long max = 0;// 最大文件大小
	String maxName = "";// 最大文件名称
	long min = Long.MAX_VALUE;// 最小文件大小
	String minName = "";// 最小文件名称

	/**
	 * 遍历一个目录下的所有文件（含子文件夹）并选出最大的和最小的
	 */
	public void chapter5() {
		String dir = "C:\\Users\\suxin\\Documents\\StarCraft II";
		//File f = new File(dir);
		findChildFile(dir);
		
		System.out.println("最大文件为：" + maxName + "  大小是：" + max);
		System.out.println("最小文件为：" + minName + "  大小是：" + min);
	}

	/**
	 * 然后通过递归一次搜寻根目录下的所有文件或者文件夹，文件的话则进行比较，文件路径的话则继续递归查询
	 * @param dir 传入需要查询的文件夹路径
	 * 
	 */
	public void findChildFile(String dir) {
		File f = new File(dir);
		// 以字符串数组的形式，返回当前文件夹下的所有文件（不包含子文件及子文件夹）
		String[] str = f.list();

		for (String filename : str) {
			File ftemp = new File(dir + "\\" + filename);
			// 是否是文件（非文件夹）
			System.out.println("判断是否是文件" + ftemp.getName() + "：" + ftemp.isFile());
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
				findChildFile(dir + "\\" + filename);//递归，一次传入路径再次查找，直到外围文件夹循环完成
			}
		}
	}

	/**
	 * 遍历一个目录下的所有文件（不含子文件夹）并选出最大的和最小的
	 */
	public void chapter4() {
		String dir = "C:\\Users\\suxin\\Documents\\StarCraft II";
		File f = new File("C:\\Users\\suxin\\Documents\\StarCraft II");

		// 以字符串数组的形式，返回当前文件夹下的所有文件（不包含子文件及子文件夹）
		String[] str = f.list();

		for (String filename : str) {
			File ftemp = new File(dir + "\\" + filename);
			// 是否是文件（非文件夹）
			System.out.println("判断是否是文件" + ftemp.getName() + "：" + ftemp.isFile());
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

		System.out.println("最大文件为：" + maxName + "  大小是：" + max);
		System.out.println("最小文件为：" + minName + "  大小是：" + min);
	}

	/**
	 * 文件常用方法3
	 * 
	 * @throws IOException
	 */
	public void chapter3() throws IOException {
		File f = new File("C:\\QMDownload\\SoftMgr\\demo1.txt");

		// 以字符串数组的形式，返回当前文件夹下的所有文件（不包含子文件及子文件夹）
		String[] str = f.list();

		// 以文件数组的形式，返回当前文件夹下的所有文件（不包含子文件及子文件夹）
		File[] fs = f.listFiles();

		// 以字符串形式返回获取所在文件夹
		f.getParent();

		// 以文件形式返回获取所在文件夹
		f.getParentFile();
		// 创建文件夹，如果父文件夹skin不存在，创建就无效
		f.mkdir();

		// 创建文件夹，如果父文件夹skin不存在，就会创建父文件夹
		f.mkdirs();

		// 创建一个空文件,如果父文件夹skin不存在，就会抛出异常
		f.createNewFile();
		// 所以创建一个空文件之前，通常都会创建父目录
		f.getParentFile().mkdirs();

		// 列出所有的盘符c: d: e: 等等
		f.listRoots();

		// 刪除文件
		f.delete();

		// JVM结束的时候，刪除文件，常用于临时文件的删除
		f.deleteOnExit();
	}

	/**
	 * 文件常用方法1
	 */
	public void chapter2() {
		File f = new File("C:\\QMDownload\\SoftMgr\\demo.txt");// 注意，文件路径的分隔符使用\或者/都可以
		System.out.println("当前文件是：" + f);
		// 文件是否存在
		System.out.println("判断是否存在：" + f.exists());

		// 是否是文件夹
		System.out.println("判断是否是文件夹：" + f.isDirectory());

		// 是否是文件（非文件夹）
		System.out.println("判断是否是文件：" + f.isFile());

		// 文件长度
		System.out.println("获取文件的长度：" + f.length());

		// 文件最后修改时间
		long time = f.lastModified();
		Date d = new Date(time);
		System.out.println("获取文件的最后修改时间：" + d);
		// 设置文件修改时间为1970.1.1 08:00:00
		String strDate = "1970/1/1 08:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			d = sdf.parse(strDate);
		} catch (ParseException e) {
			System.out.println("格式转换错误");
			e.printStackTrace();
		}
		// f.setLastModified(d.getTime());
		f.setLastModified(0);

		// 文件重命名
		// File f2 =new File("C:\\QMDownload\\SoftMgr\\dota.txt");
		// f.renameTo(f2);
		// System.out.println("把demo.txt改名成了DOTA.exe");

		System.out.println("注意： 需要在C:\\QMDownload\\SoftMgr\\确实存在一个demo.txt,\r\n才可以看到对应的文件长度、修改时间等信息");
	}

	/**
	 * 章节1基础文件路径操作
	 */
	public void chapter1() {
		// 绝对路径
		File f1 = new File("C:/QMDownload/SoftMgr");
		System.out.println("f1的绝对路径：" + f1.getAbsolutePath());
		// 相对路径,相对于工作目录，如果在eclipse中，就是项目目录
		File f2 = new File("LOL.exe");
		System.out.println("f2的绝对路径：" + f2.getAbsolutePath());

		// 把f1作为父目录创建文件对象
		File f3 = new File(f1, "LOL.exe");

		System.out.println("f3的绝对路径：" + f3.getAbsolutePath());
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
