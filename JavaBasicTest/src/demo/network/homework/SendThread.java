package demo.network.homework;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
 
public class SendThread extends Thread{
 
	private String name;
    private Socket s;
 
    public SendThread(Socket s,String name){
        this.s = s;
        this.name = name;
    }
    public void run(){
        try {
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
 
            while(true){
                Scanner sc = new Scanner(System.in);
                String str = sc.next();
                dos.writeUTF(str);
                System.out.println(name + " ·¢³ö:" + str);
                if(str.equals("Exit")) {
                	sc.close();
                	dos.close();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
     
}
