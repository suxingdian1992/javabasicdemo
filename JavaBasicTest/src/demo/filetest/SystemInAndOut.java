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
 * ϵͳ�������������̨��
 * @author suxin
 *
 */
public class SystemInAndOut {
	public static void main(String[] args) {
		//singleWord();
		//multiWord();
		//�Զ�������
		autoCreateClass();
		
	}
	
	/**
	 * �Զ�������
	 */
	public static void autoCreateClass() {
		File f = new File("C:\\QMDownload\\SoftMgr\\autoCreateClass.txt");
		File fo = new File("C:\\QMDownload\\SoftMgr\\autoCreateClass1.txt");
		Scanner sc = new Scanner(System.in);
		System.out.println("����������");
		String className = sc.nextLine();
		System.out.println("��������������");
		String proType = sc.nextLine();
		System.out.println("������������");
		String proName = sc.nextLine();

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
		String[] strArray = line.split(mark);// �ȴ��滻������
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
	 * �������ʶ�ȡ
	 */
	public static void singleWord() {
		try {
			//������ȡ
			InputStream is = System.in;
			int i = is.read();//ֻ�ܶ�ȡһ����ĸ
			System.out.println(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ������ʶ�ȡ
	 */
	public static void multiWord() {
		Scanner s = new Scanner(System.in);//ʹ��scanner�������ж�ȡ
        
        while(true){
            String line = s.nextLine();
            System.out.println(line);
        }
	}
}
