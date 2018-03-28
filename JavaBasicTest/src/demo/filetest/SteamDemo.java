package demo.filetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 流的demo，注意，流的操作是属于基础，需要注意
 * 
 * @author suxin
 *
 */
public class SteamDemo {
	public static void main(String[] args) {
		// 什么是流(Stream)，流就是一系列的数据
		// 注意理解文件输入流：InputStream 文件输出流：OutputStream
		// 文件输入流就是程序读取文件的流
		// 文件的输入输出流都是字节流的子类，字节流的输出输出流都是抽象类

		try {
			File f = new File("C:\\QMDownload\\SoftMgr\\demo1.txt");
			// 创建基于文件的输入流
			FileInputStream fis = new FileInputStream(f);
			// 通过这个输入流，就可以把数据从硬盘，读取到Java的虚拟机中来，也就是读取到内存中

			byte[] all = new byte[(int) f.length()];
			// 以字节流的形式读取文件所有内容
			fis.read(all);
			for (byte b : all) {
				// 打印出来是65 66
				System.out.println(b);
			}
			fis.close();

			FileOutputStream fos = new FileOutputStream(f);
			// 同样，通过这种形式也可以建立文件输出流
			byte data[] = { 88, 89 };// 像文件中通过字节流写入X和Y
			fos.write(data);
			fos.close();

			// 练习1
			/* 自动检测目录并创建文件然后写入数据 */
			File fDemo = new File("C:\\QMDownload\\SoftMgr\\sxd\\xdsu\\sss\\demo1.txt");
			System.out.println(fDemo.getParentFile().getName());
			fDemo.getParentFile().mkdirs();// 查看上级目录是否存在，注意，末尾的最后一项也会被认为是文件夹哦,所以这里创建父目录的相关信息
			fDemo.createNewFile();// 创建目录之后再创建文件

			FileOutputStream fosDemo = new FileOutputStream(fDemo);
			// 同样，通过这种形式也可以建立文件输出流
			byte data1[] = { 88, 89 };// 像文件中通过字节流写入X和Y
			fosDemo.write(data1);
			fosDemo.close();

			SteamDemo sd = new SteamDemo();
			String dir = "C:\\QMDownload\\SoftMgr\\20160801_163801 (2).jpg";// 传入需要分解的路径
			int filesize = sd.devideFile(dir);
			String departDir = "C:\\QMDownload\\SoftMgr\\DepartFileResult";//拆分文件存储路径
			sd.restoreFile(departDir,filesize);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 拆分文件，按照100k为单位，拆分为多个子文件
	 * 
	 * @param dir
	 *            传入待操作的文件路径
	 * @throws IOException
	 */
	public int devideFile(String dir) throws IOException {
		String departDir = "C:\\QMDownload\\SoftMgr\\DepartFileResult";// 拆分文件存储路径
		File inFile = new File(dir);
		FileInputStream fis = new FileInputStream(inFile);
		int fileSize = (int) inFile.length();
		byte[] all = new byte[fileSize];// 新建一个长度为文件长度的数组
		// 以字节流的形式读取文件所有内容，并且把对应的ascii码值写入字节数组
		fis.read(all);
		// 文件拆分区块
		File fDep = new File(departDir);
		fDep.mkdirs();// 先创建区分文件的路径

		System.out.println((int) inFile.length());
		// 文件被划分成的片段，按照100kb是一个单位
		int unit = 100 * 1024;
		int depNums = ((int) inFile.length() / unit) + 1;
		int start = 0;//文件片段起点
		int end = 0;//文件片段终点
		for (int i = 0; i < depNums; i++) {
			File ftemp = new File(departDir + "\\part-" + i + ".jpg");// 拆分部分
			ftemp.createNewFile();// 创建当前文件
			// 打开向文件操作的输出l
			FileOutputStream fos = new FileOutputStream(ftemp);
			
			int j = 0;
			for (j = 0; j < unit; j++) {
				if ((i * unit) + j >= fileSize) {
					break;
				}
				/* one[j]=all[(i*unit)+j]; */
				//fos.write(all[(i * unit) + j]);// 向文件中写入当前区段
			}
			byte[] one = new byte[j];
			start = i*unit;
			end = j;
			System.arraycopy(all, start, one, 0, end);
			System.out.println((i * unit) + j);
			fos.write(one);
			// System.arraycopy(all, i*unit, one, 0, unit);
			fos.close();
		}
		fis.close();
		return fileSize;
	}

	/**
	 * 读取目录下的所有文件，合并
	 * @param dir
	 * @throws IOException 
	 */
	public void restoreFile(String dir,int sourceSize) throws IOException {
		File inFile = new File(dir);
		String[] strFileName = inFile.list();
		byte[] all = new byte[sourceSize];
		int end = 0;
		int start = 0;
		for (String fileName : strFileName) {
			File fileUnit = new File(dir+"\\"+fileName);
			FileInputStream fis = new FileInputStream(fileUnit);
			byte[] unitx = new byte[(int)fileUnit.length()];
			fis.read(unitx);
			for (int i = 0; i < unitx.length; i++) {
				all[i+start]=unitx[i];
			}
			start+=unitx.length;
			fis.close();
		}
		File fDemo = new File("C:\\QMDownload\\SoftMgr\\sxd\\xdsu\\sss\\restore1.jpg");
		fDemo.getParentFile().mkdirs();// 查看上级目录是否存在，注意，末尾的最后一项也会被认为是文件夹哦,所以这里创建父目录的相关信息
		fDemo.createNewFile();// 创建目录之后再创建文件

		FileOutputStream fosDemo = new FileOutputStream(fDemo);
		// 同样，通过这种形式也可以建立文件输出流
		fosDemo.write(all);
		fosDemo.close();
		
		
	}
}
