package demo.filetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ����demo��ע�⣬���Ĳ��������ڻ�������Ҫע��
 * 
 * @author suxin
 *
 */
public class SteamDemo {
	public static void main(String[] args) {
		// ʲô����(Stream)��������һϵ�е�����
		// ע������ļ���������InputStream �ļ��������OutputStream
		// �ļ����������ǳ����ȡ�ļ�����
		// �ļ�����������������ֽ��������࣬�ֽ����������������ǳ�����

		try {
			File f = new File("C:\\QMDownload\\SoftMgr\\demo1.txt");
			// ���������ļ���������
			FileInputStream fis = new FileInputStream(f);
			// ͨ��������������Ϳ��԰����ݴ�Ӳ�̣���ȡ��Java�������������Ҳ���Ƕ�ȡ���ڴ���

			byte[] all = new byte[(int) f.length()];
			// ���ֽ�������ʽ��ȡ�ļ���������
			fis.read(all);
			for (byte b : all) {
				// ��ӡ������65 66
				System.out.println(b);
			}
			fis.close();

			FileOutputStream fos = new FileOutputStream(f);
			// ͬ����ͨ��������ʽҲ���Խ����ļ������
			byte data[] = { 88, 89 };// ���ļ���ͨ���ֽ���д��X��Y
			fos.write(data);
			fos.close();

			// ��ϰ1
			/* �Զ����Ŀ¼�������ļ�Ȼ��д������ */
			File fDemo = new File("C:\\QMDownload\\SoftMgr\\sxd\\xdsu\\sss\\demo1.txt");
			System.out.println(fDemo.getParentFile().getName());
			fDemo.getParentFile().mkdirs();// �鿴�ϼ�Ŀ¼�Ƿ���ڣ�ע�⣬ĩβ�����һ��Ҳ�ᱻ��Ϊ���ļ���Ŷ,�������ﴴ����Ŀ¼�������Ϣ
			fDemo.createNewFile();// ����Ŀ¼֮���ٴ����ļ�

			FileOutputStream fosDemo = new FileOutputStream(fDemo);
			// ͬ����ͨ��������ʽҲ���Խ����ļ������
			byte data1[] = { 88, 89 };// ���ļ���ͨ���ֽ���д��X��Y
			fosDemo.write(data1);
			fosDemo.close();

			SteamDemo sd = new SteamDemo();
			String dir = "C:\\QMDownload\\SoftMgr\\20160801_163801 (2).jpg";// ������Ҫ�ֽ��·��
			int filesize = sd.devideFile(dir);
			String departDir = "C:\\QMDownload\\SoftMgr\\DepartFileResult";//����ļ��洢·��
			sd.restoreFile(departDir,filesize);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ����ļ�������100kΪ��λ�����Ϊ������ļ�
	 * 
	 * @param dir
	 *            ������������ļ�·��
	 * @throws IOException
	 */
	public int devideFile(String dir) throws IOException {
		String departDir = "C:\\QMDownload\\SoftMgr\\DepartFileResult";// ����ļ��洢·��
		File inFile = new File(dir);
		FileInputStream fis = new FileInputStream(inFile);
		int fileSize = (int) inFile.length();
		byte[] all = new byte[fileSize];// �½�һ������Ϊ�ļ����ȵ�����
		// ���ֽ�������ʽ��ȡ�ļ��������ݣ����ҰѶ�Ӧ��ascii��ֵд���ֽ�����
		fis.read(all);
		// �ļ��������
		File fDep = new File(departDir);
		fDep.mkdirs();// �ȴ��������ļ���·��

		System.out.println((int) inFile.length());
		// �ļ������ֳɵ�Ƭ�Σ�����100kb��һ����λ
		int unit = 100 * 1024;
		int depNums = ((int) inFile.length() / unit) + 1;
		int start = 0;//�ļ�Ƭ�����
		int end = 0;//�ļ�Ƭ���յ�
		for (int i = 0; i < depNums; i++) {
			File ftemp = new File(departDir + "\\part-" + i + ".jpg");// ��ֲ���
			ftemp.createNewFile();// ������ǰ�ļ�
			// �����ļ����������l
			FileOutputStream fos = new FileOutputStream(ftemp);
			
			int j = 0;
			for (j = 0; j < unit; j++) {
				if ((i * unit) + j >= fileSize) {
					break;
				}
				/* one[j]=all[(i*unit)+j]; */
				//fos.write(all[(i * unit) + j]);// ���ļ���д�뵱ǰ����
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
	 * ��ȡĿ¼�µ������ļ����ϲ�
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
		fDemo.getParentFile().mkdirs();// �鿴�ϼ�Ŀ¼�Ƿ���ڣ�ע�⣬ĩβ�����һ��Ҳ�ᱻ��Ϊ���ļ���Ŷ,�������ﴴ����Ŀ¼�������Ϣ
		fDemo.createNewFile();// ����Ŀ¼֮���ٴ����ļ�

		FileOutputStream fosDemo = new FileOutputStream(fDemo);
		// ͬ����ͨ��������ʽҲ���Խ����ļ������
		fosDemo.write(all);
		fosDemo.close();
		
		
	}
}
