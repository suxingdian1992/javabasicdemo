package demo.network.homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	public static void main(String[] args) {
        try {
 
            ServerSocket ss = new ServerSocket(8888);
 
            System.out.println("�����ڶ˿ں�:8888");
            Socket s = ss.accept();
 
            //����������Ϣ�߳�
            new SendThread(s,"������").start();
            //����������Ϣ�߳�
            new RecieveThread(s,"������").start();
 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
