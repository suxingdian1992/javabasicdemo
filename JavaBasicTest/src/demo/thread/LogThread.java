package demo.thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * ��д��־�Ķ��߳���
 * @author suxin
 *
 */
public class LogThread extends Thread{
	private LinkedList<String> passwordLib;
	
	public LogThread(LinkedList<String> passwordLib) {//��ʼ������������ǰ����������
		this.passwordLib = passwordLib;
	}

	@Override
	public void run() {//ÿ��ִ���������ȡ��һ��ֵ������б��ǿյĻ�
		while(true) {//�ػ������ڲ�һ��һֱ����һ����ѭ�������ϼ��״̬��������ѯ
			while(passwordLib.isEmpty()) {//��������б��ǿյ�����ͣһ��
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
				pw.println("��ǰ���������ǣ�"+passwordLib.removeLast());
				pw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
