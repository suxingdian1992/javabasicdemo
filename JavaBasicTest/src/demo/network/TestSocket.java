package demo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestSocket {
	public static void main(String[] args) throws IOException, InterruptedException {
        
		//��ȡ����ip
		/*InetAddress host = InetAddress.getLocalHost();
        String ip =host.getHostAddress();
        System.out.println("����ip��ַ��" + ip);*/
        
        //ʹ��java����ping����
        Process p = Runtime.getRuntime().exec("ping " + "172.16.35.1");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        System.out.println("����ָ��ص���Ϣ�ǣ�");
        while ((line = br.readLine()) != null) {
            if (line.length() != 0)
                sb.append(line + "\r\n");
            System.out.println(line);
        }
        //System.out.println(sb.toString());
    }
}
