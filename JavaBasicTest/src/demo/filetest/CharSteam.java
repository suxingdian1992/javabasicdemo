package demo.filetest;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * �ַ�������˵��
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
			//�ļ����ܽ���
			File encodingFile = new File(dir);
			File encodedFile = new File(expdir);
			File decodeFile = new File(dedir);
			cs.chapter3(encodingFile, encodedFile);//����
			cs.chapter4(encodedFile, decodeFile);//����
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * �����ļ�
	 * @param encodingFile
	 * @param encodedFile
	 * @throws IOException
	 */
	public void chapter4(File encodedFile, File decodeFile) throws IOException {
		// ��ȡ����ǰ���ļ�
		FileReader fr = new FileReader(encodedFile);
		char[] all = new char[(int) encodedFile.length()];// �����ļ���С���ӷ�������������
		fr.read(all);

		// ���ļ����н���
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
		//д���������
		FileWriter fw = new FileWriter(decodeFile);
		fw.write(all);
		fr.close();
		fw.close();
	}
	
	/**
	 * �����ƶ���ѭ����������ĸ
	 * @param c
	 * @return
	 */
	public char moveToLeft(char c) {
		int i = (int) c;
		if (c != 'a' && c != 'A' && c!='0') {
			c = (char) (i - 1);//�����ƶ�
		} else if (c == 'a') {
			c = 'z';//ѭ���ƶ�
		} else if (c == 'A') {
			c = 'Z';// ѭ���ƶ�
		} else if (c == '0') {
			c = '9';
		}
		return c;
	}
	
	/**
	 * �Դ�����ļ����м��ܣ��������������encodedfile�� 
	 * @param encodingFile ����ǰ�ļ�
	 * @param encodedFile ���ܺ���ļ�
	 * @throws IOException
	 */
	public void chapter3(File encodingFile, File encodedFile) throws IOException {
		// ��ȡ����ǰ���ļ�
		FileReader fr = new FileReader(encodingFile);
		char[] all = new char[(int) encodingFile.length()];// �����ļ���С���ӷ�������������
		fr.read(all);

		// ���ļ����м���
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
		//д���������
		FileWriter fw = new FileWriter(encodedFile);
		fw.write(all);
		fr.close();
		fw.close();
	}
	
	/**
	 * �����ƶ���ѭ����������ĸ
	 * @param c
	 * @return
	 */
	public char moveToRight(char c) {
		int i = (int) c;
		if (c != 'z' && c != 'Z' && c!='9') {
			c = (char) (i + 1);//�����ƶ�
		} else if (c == 'z') {
			c = 'a';//ѭ���ƶ�
		} else if (c == 'Z') {
			c = 'A';// ѭ���ƶ�
		} else if (c == '9') {
			c = '0';
		}
		return c;
	}

	/**
	 * �ַ�������ȡ�ļ�����
	 * @param dir ����Ĳ���·��
	 * @throws IOException
	 */
	public void chapter1(String dir) throws IOException {
		File f = new File(dir);
		// ���������ļ���reader�ַ�����ȡ��
		FileReader fr = new FileReader(f);
		char[] all = new char[(int) f.length()];// �����ļ���С���ӷ�������������

		fr.read(all);
		for (char c : all) {
			System.out.println(c);
		}
		fr.close();
	}

	/**
	 * �ַ�������д���ļ�
	 * @param dir
	 * @throws IOException
	 */
	public void chapter2(String dir) throws IOException {
		// ׼���ļ�lol2.txt
		File f = new File(dir);
		// ���������ļ���Writer
		FileWriter fr = new FileWriter(f);
		// ���ַ�������ʽ������д�뵽�ļ���
		String data = "abcdefg1234567890��������ʲô����";
		char[] cs = data.toCharArray();
		fr.write(cs);
		fr.close();
	}

}
