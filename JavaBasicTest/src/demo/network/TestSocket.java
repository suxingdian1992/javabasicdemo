package demo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestSocket {
	public static void main(String[] args) throws IOException, InterruptedException {
        
		//获取本机ip
		/*InetAddress host = InetAddress.getLocalHost();
        String ip =host.getHostAddress();
        System.out.println("本机ip地址：" + ip);*/
        
        //使用java进行ping操作
        Process p = Runtime.getRuntime().exec("ping " + "172.16.35.1");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        System.out.println("本次指令返回的消息是：");
        while ((line = br.readLine()) != null) {
            if (line.length() != 0)
                sb.append(line + "\r\n");
            System.out.println(line);
        }
        //System.out.println(sb.toString());
    }
}
