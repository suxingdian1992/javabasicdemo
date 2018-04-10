package demo.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * �������߳�ʵ�ֿͻ��˺ͷ����
 * @author suxin
 *
 */
public class ComunicateThreadDemo {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8888);
			ServerSocket ssc = new ServerSocket(8887);
			Socket s = new Socket("127.0.0.1", 8888);
			Socket sc = new Socket("127.0.0.1", 8887);
			Thread thServer = new Server(ss,sc);
			Thread thClient = new Client(ssc,s);
			thServer.start();
			thClient.start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Server extends Thread{
	ServerSocket ss;
	Socket sc;
	String server = "Server";
    public Server(ServerSocket ss , Socket sc) {
		this.ss = ss;
		this.sc = sc;
	}

	@Override
	public void run() {
		Receive r1 = new Receive();
    	r1.receive(ss,server);
	}
}

class Client extends Thread{
	Socket s;
	ServerSocket ssc;
	String client = "Client";
    public Client(ServerSocket ssc,Socket s) {
		this.s = s;
		this.ssc = ssc;
	}

	@Override
	public void run() {
    	Send s1 = new Send();
    	s1.send(s, client);
	}
}

class Send{
	public synchronized void send(Socket s,String name) {
		try {
    		//���ӵ�������8888�˿�
            //System.out.println("�ͻ�������:"+s);
			// ���ʹ�����
			OutputStream os = s.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			// os.write(123);

			while(true) {
				// ���������װ��DataOutputStream��
				Scanner sc = new Scanner(System.in);
				String str = sc.nextLine();
				System.out.println(name+"����:"+str);
				// ʹ��writeUTF�����ַ���
				dos.writeUTF(str);
				if(str.equals("Exit")) {
					dos.close();
					s.close();
					break;
				}
			}
			
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

class Receive{
	public synchronized void receive(ServerSocket ss,String name) {
		try {
			// ��8888�˿��ϼ��������Ƿ��������������
			System.out.println("�����ڶ˿ں�:8888");
			Socket s = ss.accept();
			System.out.println("����˽��յ������ӹ���" + s);

			while(true) {
				// ���ܴ�����
				InputStream is = s.getInputStream();
				// System.out.println(bs);

				// ����������װ��DataInputStream
				DataInputStream dis = new DataInputStream(is);
				// ʹ��readUTF��ȡ�ַ���
				String msg = dis.readUTF();
				System.out.println(name+"�յ�:" + msg);
				if(msg.equals("Exit")) {
					dis.close();
					s.close();
					ss.close();
					System.out.println("��⵽Exit�������");
					break;
				}
			}
			
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}