package demo.filetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 各种编码格式的输出结果
 * @author suxin
 *
 */
public class UnicodeTest {
	public static void main(String[] args) {
		String str = "苏";
		showCode(str);
		
		String s = "E5B18C";
        int len = s.length() / 2;
        byte[] b = new byte[len];
         
        for(int i = 0; i < len; i++){
            b[i] = (byte)Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
        }
        try {
			String result = findChinesInUtf8(b);
			System.out.println(result);
			
			findBom();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
	}

	/**
	 * 当使用utf-8编码保存文件的时候默认会在开头加入三个字节的bom标识符，
	 * 不可见字符，以下方法将其去除，注意观察结果文件的大小
	 */
	public static void findBom() {
		File f = new File("C:\\QMDownload\\SoftMgr\\utf8.txt");
		File fc = new File("C:\\QMDownload\\SoftMgr\\utf8-bom.txt");
		try {
			FileInputStream fis = new FileInputStream(f);
			byte[] b = new byte[(int)f.length()];
			fis.read(b);
			System.out.println("Bom标识符为：");
			int flg=0;
			for (byte c : b) {
				if(flg>2) {
					break;
				}
				int i = c&0x000000ff;
				System.out.print(Integer.toHexString(i)+" ");
				flg++;
			}
			//去除bom，就是去除字节数组的前三位
			byte[] bc = new byte[(int)f.length()-3];
			System.arraycopy(b, 3, bc, 0, (int)f.length()-3);
			FileOutputStream fos = new FileOutputStream(fc);
			fos.write(bc);
			fis.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//找出当前十六进制对应的汉字
	public static String findChinesInUtf8(byte[] b) throws IOException {
		for (byte c : b) {
			int i = c&0x000000ff;//取十六进制的最后两位
			System.out.println(Integer.toHexString(i));
		}
		
		String str = new String(b,"UTF-8");
		return str;
	}
	
	/**
	 * 查看汉字在不同编码格式下的十六进制值
	 * @param str
	 */
	private static void showCode(String str) {
		String[] encodes = { "BIG5", "GBK", "GB2312", "UTF-8", "UTF-16", "UTF-32" };
		for (String encode : encodes) {
			showCode(str, encode);
		}
	}

	private static void showCode(String str, String encode) {
		try {
			System.out.printf("字符：\"%s\" 在编码方式%s下的十六进制值是：\n",str,encode);
			byte[] bs = str.getBytes(encode);//按照编码格式输出值到字节数组中
			for (byte b : bs) {
				int i = b&0xff;//和十六进制中的全1做位与操作转换为十六进制
				System.out.print(Integer.toHexString(i)+"\t");
			}
			System.out.println("\n\n");
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
