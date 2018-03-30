package demo.filetest;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流操作说明
 * @author suxin
 *
 */
public class CharSteam {
	public static void main(String[] args) {
		String dir = "C:\\QMDownload\\SoftMgr\\demo.txt";
		String expdir = "C:\\QMDownload\\SoftMgr\\demo1.txt";
		String dedir = "C:\\QMDownload\\SoftMgr\\demo2.txt";
		try {
			CharSteam cs = new CharSteam();
			cs.chapter1(dir);
			cs.chapter2(dir);
			//文件加密解密
			File encodingFile = new File(dir);
			File encodedFile = new File(expdir);
			File decodeFile = new File(dedir);
			cs.chapter3(encodingFile, encodedFile);//加密
			cs.chapter4(encodedFile, decodeFile);//解密
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 解密文件
	 * @param encodingFile
	 * @param encodedFile
	 * @throws IOException
	 */
	public void chapter4(File encodedFile, File decodeFile) throws IOException {
		// 读取解码前的文件
		FileReader fr = new FileReader(encodedFile);
		char[] all = new char[(int) encodedFile.length()];// 创建文件大小的子非鱼三个月左右
		fr.read(all);

		// 对文件进行解密
		for (int i = 0; i < all.length; i++) {
			System.out.println(all[i]);
			if(all[i]>='0'&&all[i]<='9') {
				all[i]=moveToLeft(all[i]);
			}else if(all[i]>='a'&&all[i]<='z') {
				all[i]=moveToLeft(all[i]);
			}else if(all[i]>='A'&&all[i]<='Z') {
				all[i]=moveToLeft(all[i]);
			}
		}
		//写入解密内容
		FileWriter fw = new FileWriter(decodeFile);
		fw.write(all);
		fr.close();
		fw.close();
	}
	
	/**
	 * 向左移动（循环）处理字母
	 * @param c
	 * @return
	 */
	public char moveToLeft(char c) {
		int i = (int) c;
		if (c != 'a' && c != 'A' && c!='0') {
			c = (char) (i - 1);//向右移动
		} else if (c == 'a') {
			c = 'z';//循环移动
		} else if (c == 'A') {
			c = 'Z';// 循环移动
		} else if (c == '0') {
			c = '9';
		}
		return c;
	}
	
	/**
	 * 对传入的文件进行加密，加密内容输出到encodedfile中 
	 * @param encodingFile 加密前文件
	 * @param encodedFile 加密后的文件
	 * @throws IOException
	 */
	public void chapter3(File encodingFile, File encodedFile) throws IOException {
		// 读取解码前的文件
		FileReader fr = new FileReader(encodingFile);
		char[] all = new char[(int) encodingFile.length()];// 创建文件大小的子非鱼三个月左右
		fr.read(all);

		// 对文件进行加密
		for (int i = 0; i < all.length; i++) {
			System.out.println(all[i]);
			if(all[i]>='0'&&all[i]<='9') {
				all[i]=moveToRight(all[i]);
			}else if(all[i]>='a'&&all[i]<='z') {
				all[i]=moveToRight(all[i]);
			}else if(all[i]>='A'&&all[i]<='Z') {
				all[i]=moveToRight(all[i]);
			}
		}
		//写入加密内容
		FileWriter fw = new FileWriter(encodedFile);
		fw.write(all);
		fr.close();
		fw.close();
	}
	
	/**
	 * 向右移动（循环）处理字母
	 * @param c
	 * @return
	 */
	public char moveToRight(char c) {
		int i = (int) c;
		if (c != 'z' && c != 'Z' && c!='9') {
			c = (char) (i + 1);//向右移动
		} else if (c == 'z') {
			c = 'a';//循环移动
		} else if (c == 'Z') {
			c = 'A';// 循环移动
		} else if (c == '9') {
			c = '0';
		}
		return c;
	}

	/**
	 * 字符流来读取文件内容
	 * @param dir 传入的操作路径
	 * @throws IOException
	 */
	public void chapter1(String dir) throws IOException {
		File f = new File(dir);
		// 创建基于文件的reader字符流读取器
		FileReader fr = new FileReader(f);
		char[] all = new char[(int) f.length()];// 创建文件大小的子非鱼三个月左右

		fr.read(all);
		for (char c : all) {
			System.out.println(c);
		}
		fr.close();
	}

	/**
	 * 字符流操作写入文件
	 * @param dir
	 * @throws IOException
	 */
	public void chapter2(String dir) throws IOException {
		// 准备文件lol2.txt
		File f = new File(dir);
		// 创建基于文件的Writer
		FileWriter fr = new FileWriter(f);
		// 以字符流的形式把数据写入到文件中
		String data = "abcdefg1234567890哈哈哈，什么东西";
		char[] cs = data.toCharArray();
		fr.write(cs);
		fr.close();
	}

}
