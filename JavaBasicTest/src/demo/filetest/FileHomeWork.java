package demo.filetest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * �ļ�������ҵ�������ļ��������ļ��У������ļ�������
 * @author suxin
 *
 */
public class FileHomeWork {
	public static void main(String[] args) throws IOException {
        copyFile("C:\\QMDownload\\SoftMgr\\beforecopy.txt", "C:\\QMDownload\\SoftMgr\\copyone.jpg");
        copyFolder(new File("C:\\QMDownload\\SoftMgr"), new File("C:\\QMDownload\\SoftMgrNew"));
        System.out.println("�ļ��п�����ɡ�");
        search(new File("C:\\QMDownload\\SoftMgr"),"import");
        System.out.println("������ɡ�");
    }
    /** �����ļ�
     * @param srcFile Դ�ļ�
     * @param destFile  Ŀ���ļ�
     * @throws IOException �׳��쳣
     */
    public static void copyFile(String srcFile, String destFile) throws IOException{
        File src = new File(srcFile);
        File dest = new File(destFile);
		if (src.exists() && src.isFile()) {
			if (dest.exists()) {
				dest.createNewFile();
			}
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));

			byte[] bys = new byte[1024];
			int len = 0;
			while ((len = bis.read(bys)) != -1) {
				bos.write(bys, 0, len);
			}

			bos.close();
			bis.close();
			System.out.println("�ļ�������ɡ�");
		} else {
			System.out.println("�ļ�������");
		}
    }
    /**
     * �����ļ���
     * @param srcFolder Ҫ�������ļ���
     * @param destFolder Ŀ�ĵ��ļ���
     * @throws IOException
     */
    public static void copyFolder(File srcFolder, File destFolder) throws IOException{
        File newDir = new File(destFolder,srcFolder.getName());
        if(!destFolder.exists()) {
            destFolder.mkdir();
        }
        if(!newDir.exists()) {
            newDir.mkdir();
        }
        //����Դ�ļ��У�
        File[] files = srcFolder.listFiles();
        for(File listfile : files) {
            if(listfile.isFile()) {
                FileInputStream fis = new FileInputStream(listfile);
                File newf = new File(newDir,listfile.getName());
                FileOutputStream fos = new FileOutputStream(newf);
                
                //�ٶȻ���
                /*while((len=fis.read())!=-1) {
                 * fos.write(len);
                }*/
                //˲����ɣ�����Ϊ�ڱ��أ����ǻ��ǽ����ô��л���Ļ�����
                /*byte[] by = new byte[1024*10];
                while((len=fis.read(by))!=-1) {
                    fos.write(by, 0, len);
                }
                fis.close();
                fos.close();*/
                //���е��ļ�������ʹ���ֽ������ж�д
                BufferedInputStream bis = new BufferedInputStream(fis);
    			BufferedOutputStream bos = new BufferedOutputStream(fos);
    			int len = 0;
    			byte[] bys = new byte[1024];
    			while ((len = bis.read(bys)) != -1) {
    				bos.write(bys, 0, len);
    			}

    			bos.close();
    			bis.close();
                
                
                //�����������ļ��У���������ĵ���ͼƬ���򲻿���ע�⣬�������ַ����������ֽ������ᵼ���ļ��𻵣�ֻ�������������ı���ͼ���ĵ�������
                /*BufferedReader br = new BufferedReader(new FileReader(listfile));
                File newfile = new File(newDir,listfile.getName());
                BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));
                String str="";
                while((str=br.readLine())!=null) {
                    bw.write(str);
                }
                br.close();
                bw.close();*/
            }else {
                copyFolder(listfile, newDir);
            }
        }  
    }
    /**
     *���Ұ����ַ������ĵ���
     * @param folder Ҫ���ҵ��ļ���
     * @param search ���ҵ��ĵ����������ַ���
     * @throws IOException �׳��쳣
     */
    public static void search(File folder, String search) throws IOException {
        if(folder.exists()) {
			File[] files = folder.listFiles();
			for (File listfile : files) {
				System.out.println(listfile.getName());
				if (listfile.isFile()) {
					// �˴����ҵ�ʱ����ö��߳̽��в���
					Thread th1 = new Thread() {
						@Override
						public void run() {
							try {
								FileReader fr = new FileReader(listfile);
								BufferedReader br = new BufferedReader(fr);
								while (true) {
									// һ�ζ�һ�У�ÿ�δӵ�ǰ���бȶ�
									String line = br.readLine();
									if (null == line)
										break;
									if (line.contains(search)) {
										System.out.println("�ҵ���Ŀ���ַ���" + search + ",���ļ���" + listfile.getAbsolutePath());
									}
								}
								fr.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					};
					th1.start();
				} else {
					search(listfile, search);// �ݹ�����ļ�����main�����ļ���
				}

			}
		} else
			System.out.println("�ļ�����");
	}
}
