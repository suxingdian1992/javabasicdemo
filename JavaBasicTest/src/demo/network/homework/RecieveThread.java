package demo.network.homework;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
 
public class RecieveThread extends Thread {
	private String name;
    private Socket s;
 
    public RecieveThread(Socket s,String name) {
        this.s = s;
        this.name = name;
    }
 
    public void run() {
        try {
            InputStream is = s.getInputStream();
 
            DataInputStream dis = new DataInputStream(is);
            while (true) {
				String msg = dis.readUTF();
				System.out.println(name + "  ’µΩ:" + msg);
                if(msg.equals("Exit")) {
                	is.close();
                	dis.close();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
    }
 
}
