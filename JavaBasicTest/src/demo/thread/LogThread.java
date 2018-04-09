package demo.thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * 书写日志的多线程类
 * @author suxin
 *
 */
public class LogThread extends Thread{
	private LinkedList<String> passwordLib;
	
	public LogThread(LinkedList<String> passwordLib) {//初始话方法，将当前的容器传入
		this.passwordLib = passwordLib;
	}

	@Override
	public void run() {//每次执行这个方法取出一个值，如果列表不是空的话
		while(true) {//守护进程内部一般一直都有一个恒循环来不断检测状态，类似轮询
			while(passwordLib.isEmpty()) {//如果发现列表是空的则暂停一秒
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			File fo = new File("C:\\QMDownload\\SoftMgr\\comments1.txt");
			try {
				FileWriter fw = new FileWriter(fo,true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("当前生成密码是："+passwordLib.removeLast());
				pw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
