package demo.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * eclipse�Ŀ���̨����ͬ�����ж��console
 * @author suxin
 *
 */
public class Server1 {
	public static void main(String[] args) {
        try {
 
            ServerSocket ss = new ServerSocket(8888);
 
            System.out.println("�����ڶ˿ں�:8888");
            Socket s = ss.accept();
 
            InputStream is = s.getInputStream();
 
            //����������װ��DataInputStream
            DataInputStream dis = new DataInputStream(is);
            //ʹ��readUTF��ȡ�ַ���
            String msg = dis.readUTF();
            System.out.println(msg);
            dis.close();
            s.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}
