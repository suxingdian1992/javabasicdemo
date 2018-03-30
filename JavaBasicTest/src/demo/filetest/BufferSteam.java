package demo.filetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ʹ��BufferReader��PrintWriter�����л������Ķ�д
 * @author suxin
 *
 */
public class BufferSteam {
	public static void main(String[] args) {
		BufferSteam test = new BufferSteam();
		//��������ζ�ȡ�ļ�
		test.bufferRead();
		test.printWrite();
		//test.flush();
		test.removeComments();
	}

	/**
	 * �Ƴ�ע��
	 */
	public void removeComments() {
		File f = new File("C:\\QMDownload\\SoftMgr\\comments.txt");
		File fo = new File("C:\\QMDownload\\SoftMgr\\comments1.txt");
		try {
			// �����ļ��ַ���
			// ���������뽨����һ�����ڵ����Ļ�����
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			FileWriter fw = new FileWriter(fo);
			PrintWriter pw = new PrintWriter(fw);
			while (true) {
				// һ�ζ�һ��
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
	 * ʹ�û�������ȡ�ļ������Ϣ
	 */
	public void bufferRead() {
		File f = new File("C:\\QMDownload\\SoftMgr\\demo.txt");
		try {
			// �����ļ��ַ���
			// ���������뽨����һ�����ڵ����Ļ�����
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				// һ�ζ�һ��
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
	 * ʹ�û��������ļ���д������
	 */
	public void printWrite() {
		// ���ļ�lol2.txt��д���������
        File f = new File("C:\\QMDownload\\SoftMgr\\demo2.txt");
          
        try (
                // �����ļ��ַ���
                FileWriter fw = new FileWriter(f);
                // ���������뽨����һ�����ڵ����Ļ�����            
        		BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(fw);              
        		
        ) {
        	bw.write("123123");//ע�⣬�˴�BufferedWriter��ִ��˳����PrintWriter֮��
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
	 * ���ۻ����Ƿ��Ѿ����ˣ�����д��
	 */
	public void flush() {
		//���ļ���д���������
        File f =new File("C:\\QMDownload\\SoftMgr\\demo2.txt");
        //�����ļ��ַ���
        //���������뽨����һ�����ڵ����Ļ�����
        try(FileWriter fr = new FileWriter(f);PrintWriter pw = new PrintWriter(fr);) {
			pw.println("garen kill teemo");
			// ǿ�ưѻ����е�����д��Ӳ�̣����ۻ����Ƿ�����
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
