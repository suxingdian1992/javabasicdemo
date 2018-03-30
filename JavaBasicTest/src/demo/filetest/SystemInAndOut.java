package demo.filetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 系统输入输出（控制台）
 * @author suxin
 *
 */
public class SystemInAndOut {
	public static void main(String[] args) {
		//singleWord();
		//multiWord();
		//自动创建类
		autoCreateClass();
		
	}
	
	/**
	 * 自动创建类
	 */
	public static void autoCreateClass() {
		File f = new File("C:\\QMDownload\\SoftMgr\\autoCreateClass.txt");
		File fo = new File("C:\\QMDownload\\SoftMgr\\autoCreateClass1.txt");
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入类名");
		String className = sc.nextLine();
		System.out.println("请输入属性类型");
		String proType = sc.nextLine();
		System.out.println("请输入属性名");
		String proName = sc.nextLine();

		try {
			// 创建文件字符流
			// 缓存流必须建立在一个存在的流的基础上
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			FileWriter fw = new FileWriter(fo);
			PrintWriter pw = new PrintWriter(fw);
			while (true) {
				// 一次读一行
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String result = "";
				result = replaceMark("@class@",className, line);
				result = replaceMark("@type@",proType, result);
				result = replaceMark("@property@",proName, result);
				result = replaceMark("@Uproperty@",proName.substring(0, 1).toUpperCase() + proName.substring(1), result);
				System.out.println(result);
				pw.println(result);
			}
			pw.close();
			br.close();
			fw.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String replaceMark(String mark,String content,String line) {
		String[] strArray = line.split(mark);// 等待替换的数组
		int i = 0;
		String result = "";
		while (i < strArray.length) {
			if (i == 0&&strArray.length!=1) {
				result = strArray[i] + content;
			} else if (i == strArray.length - 1) {
				result = result + strArray[i];
			} else {
				result = result  + strArray[i]+ content;
			}
			i++;
		}
		return result;
	}
	
	/**
	 * 单个单词读取
	 */
	public static void singleWord() {
		try {
			//单个读取
			InputStream is = System.in;
			int i = is.read();//只能读取一个字母
			System.out.println(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 多个单词读取
	 */
	public static void multiWord() {
		Scanner s = new Scanner(System.in);//使用scanner可以逐行读取
        
        while(true){
            String line = s.nextLine();
            System.out.println(line);
        }
	}
}
