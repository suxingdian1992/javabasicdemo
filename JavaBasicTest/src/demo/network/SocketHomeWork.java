package demo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SocketHomeWork {
	/**
	 * �ҵ���ǰ���ε�ȫ������ip
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//��ȡ����ip
		InetAddress host = InetAddress.getLocalHost();
        String localip =host.getHostAddress();
        System.out.println("����ip��ַ��" + localip);
        
        String ip = localip.substring(0, localip.lastIndexOf('.')+1);
        for (int i = 1; i < 3; i++) {
			String ipx = ip+i;
			ping(ipx);
		}
	}
	
	public static boolean ping(String ipx) throws IOException {
		System.out.println("ping " + ipx);
		Process p = Runtime.getRuntime().exec("ping " + ipx);
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        System.out.println("����ָ��ص���Ϣ�ǣ�");
        while ((line = br.readLine()) != null) {
            if (line.length() != 0)
                sb.append(line + "\r\n");
        }
        System.out.println(sb);
		return false;
	}
}
