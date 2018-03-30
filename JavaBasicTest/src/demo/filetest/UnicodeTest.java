package demo.filetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * ���ֱ����ʽ��������
 * @author suxin
 *
 */
public class UnicodeTest {
	public static void main(String[] args) {
		String str = "��";
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
	 * ��ʹ��utf-8���뱣���ļ���ʱ��Ĭ�ϻ��ڿ�ͷ���������ֽڵ�bom��ʶ����
	 * ���ɼ��ַ������·�������ȥ����ע��۲����ļ��Ĵ�С
	 */
	public static void findBom() {
		File f = new File("C:\\QMDownload\\SoftMgr\\utf8.txt");
		File fc = new File("C:\\QMDownload\\SoftMgr\\utf8-bom.txt");
		try {
			FileInputStream fis = new FileInputStream(f);
			byte[] b = new byte[(int)f.length()];
			fis.read(b);
			System.out.println("Bom��ʶ��Ϊ��");
			int flg=0;
			for (byte c : b) {
				if(flg>2) {
					break;
				}
				int i = c&0x000000ff;
				System.out.print(Integer.toHexString(i)+" ");
				flg++;
			}
			//ȥ��bom������ȥ���ֽ������ǰ��λ
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
	
	//�ҳ���ǰʮ�����ƶ�Ӧ�ĺ���
	public static String findChinesInUtf8(byte[] b) throws IOException {
		for (byte c : b) {
			int i = c&0x000000ff;//ȡʮ�����Ƶ������λ
			System.out.println(Integer.toHexString(i));
		}
		
		String str = new String(b,"UTF-8");
		return str;
	}
	
	/**
	 * �鿴�����ڲ�ͬ�����ʽ�µ�ʮ������ֵ
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
			System.out.printf("�ַ���\"%s\" �ڱ��뷽ʽ%s�µ�ʮ������ֵ�ǣ�\n",str,encode);
			byte[] bs = str.getBytes(encode);//���ձ����ʽ���ֵ���ֽ�������
			for (byte b : bs) {
				int i = b&0xff;//��ʮ�������е�ȫ1��λ�����ת��Ϊʮ������
				System.out.print(Integer.toHexString(i)+"\t");
			}
			System.out.println("\n\n");
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
