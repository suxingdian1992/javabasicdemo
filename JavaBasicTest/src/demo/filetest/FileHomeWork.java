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
 * 文件操作作业，复制文件，复制文件夹，查找文件中内容
 * @author suxin
 *
 */
public class FileHomeWork {
	public static void main(String[] args) throws IOException {
        copyFile("C:\\QMDownload\\SoftMgr\\beforecopy.txt", "C:\\QMDownload\\SoftMgr\\copyone.jpg");
        copyFolder(new File("C:\\QMDownload\\SoftMgr"), new File("C:\\QMDownload\\SoftMgrNew"));
        System.out.println("文件夹拷贝完成。");
        search(new File("C:\\QMDownload\\SoftMgr"),"import");
        System.out.println("查找完成。");
    }
    /** 拷贝文件
     * @param srcFile 源文件
     * @param destFile  目标文件
     * @throws IOException 抛出异常
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
			System.out.println("文件拷贝完成。");
		} else {
			System.out.println("文件名错误。");
		}
    }
    /**
     * 拷贝文件夹
     * @param srcFolder 要拷贝的文件夹
     * @param destFolder 目的地文件夹
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
        //遍历源文件夹；
        File[] files = srcFolder.listFiles();
        for(File listfile : files) {
            if(listfile.isFile()) {
                FileInputStream fis = new FileInputStream(listfile);
                File newf = new File(newDir,listfile.getName());
                FileOutputStream fos = new FileOutputStream(newf);
                
                //速度缓慢
                /*while((len=fis.read())!=-1) {
                 * fos.write(len);
                }*/
                //瞬间完成，是因为在本地，但是还是建议用带有缓存的缓冲流
                /*byte[] by = new byte[1024*10];
                while((len=fis.read(by))!=-1) {
                    fos.write(by, 0, len);
                }
                fis.close();
                fos.close();*/
                //所有的文件都可以使用字节流进行读写
                BufferedInputStream bis = new BufferedInputStream(fis);
    			BufferedOutputStream bos = new BufferedOutputStream(fos);
    			int len = 0;
    			byte[] bys = new byte[1024];
    			while ((len = bis.read(bys)) != -1) {
    				bos.write(bys, 0, len);
    			}

    			bos.close();
    			bis.close();
                
                
                //缓存流拷贝文件夹，拷贝后的文档，图片都打不开。注意，不能用字符流来操作字节流，会导致文件损坏，只能用来操作纯文本，图像文档不可以
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
     *查找包含字符串的文档。
     * @param folder 要查找的文件夹
     * @param search 查找的文档所包含的字符串
     * @throws IOException 抛出异常
     */
    public static void search(File folder, String search) throws IOException {
        if(folder.exists()) {
			File[] files = folder.listFiles();
			for (File listfile : files) {
				System.out.println(listfile.getName());
				if (listfile.isFile()) {
					// 此处查找的时候采用多线程进行查找
					Thread th1 = new Thread() {
						@Override
						public void run() {
							try {
								FileReader fr = new FileReader(listfile);
								BufferedReader br = new BufferedReader(fr);
								while (true) {
									// 一次读一行，每次从当前行中比对
									String line = br.readLine();
									if (null == line)
										break;
									if (line.contains(search)) {
										System.out.println("找到子目标字符串" + search + ",在文件：" + listfile.getAbsolutePath());
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
					search(listfile, search);// 递归查找文件夹李main的子文件夹
				}

			}
		} else
			System.out.println("文件错误。");
	}
}
