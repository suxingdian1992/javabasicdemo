package demo.thread;

import java.util.LinkedList;

public class CreatePassWordThread extends Thread{
	private LinkedList<String> passwordLib;
	private String password;
	
	
	@Override
	public void run() {
		// ������λ����
		String pool = "";
		for (short i = '0'; i <= '9'; i++) {
			pool += (char) i;
		}
		for (short i = 'a'; i <= 'z'; i++) {
			pool += (char) i;
		}
		for (short i = 'A'; i <= 'Z'; i++) {
			pool += (char) i;
		}
		char cs[] = new char[3];
		outloop: 
		for (int i = 0; i < pool.length(); i++) {
			cs[0] = pool.charAt(i);
			for (int j = 0; j < pool.length(); j++) {
				cs[1] = pool.charAt(j);
				for (int k = 0; k < pool.length(); k++) {
					cs[2] = pool.charAt(k);
					passwordLib.addLast(String.valueOf(cs));
					if (password.equals(String.valueOf(cs))) {
						System.out.println("��ǰƥ��ɹ��������ǣ�" + String.valueOf(cs));
						// break outloop;
					}

				}
			}
		}
	}
	/**
	 * ���췽��
	 * @param passwordLib ��������
	 * @param password ��ȷ����
	 */
	public CreatePassWordThread(LinkedList<String> passwordLib,String password) {
		this.password = password;//Ϊ��ȷ���븳ֵ
		this.passwordLib = passwordLib;//Ϊ����������ֵ
	}
}
