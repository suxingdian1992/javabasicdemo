package demo.filetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 使用BufferReader和PrintWriter来进行缓存流的读写
 * @author suxin
 *
 */
public class BufferSteam {
	public static void main(String[] args) {
		BufferSteam test = new BufferSteam();
		//缓存流如何读取文件
		test.bufferRead();
		test.printWrite();
		//test.flush();
		test.removeComments();
	}

	/**
	 * 移除注释
	 */
	public void removeComments() {
		File f = new File("C:\\QMDownload\\SoftMgr\\comments.txt");
		File fo = new File("C:\\QMDownload\\SoftMgr\\comments1.txt");
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
				if (null == line)
					break;
				if(!line.startsWith("//"))
					pw.println(line);
				System.out.println(line);
			}
			br.close();
			fr.close();
			fw.close();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用缓存流读取文件里的信息
	 */
	public void bufferRead() {
		File f = new File("C:\\QMDownload\\SoftMgr\\demo.txt");
		try {
			// 创建文件字符流
			// 缓存流必须建立在一个存在的流的基础上
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				// 一次读一行
				String line = br.readLine();
				if (null == line)
					break;
				System.out.println(line);
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 使用缓存流向文件中写入数据
	 */
	public void printWrite() {
		// 向文件lol2.txt中写入三行语句
        File f = new File("C:\\QMDownload\\SoftMgr\\demo2.txt");
          
        try (
                // 创建文件字符流
                FileWriter fw = new FileWriter(f);
                // 缓存流必须建立在一个存在的流的基础上            
        		BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(fw);              
        		
        ) {
        	bw.write("123123");//注意，此处BufferedWriter的执行顺序在PrintWriter之后
            pw.println("garen kill teemo");
            pw.println("teemo revive after 1 minutes");
            pw.println("teemo try to garen, but killed again");
            bw.close();
            pw.close();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	/**
	 * 无论缓存是否已经满了，立刻写入
	 */
	public void flush() {
		//向文件中写入三行语句
        File f =new File("C:\\QMDownload\\SoftMgr\\demo2.txt");
        //创建文件字符流
        //缓存流必须建立在一个存在的流的基础上
        try(FileWriter fr = new FileWriter(f);PrintWriter pw = new PrintWriter(fr);) {
			pw.println("garen kill teemo");
			// 强制把缓存中的数据写入硬盘，无论缓存是否已满
			pw.flush();
			pw.println("teemo revive after 1 minutes");
			pw.flush();
			pw.println("teemo try to garen, but killed again");
			pw.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
