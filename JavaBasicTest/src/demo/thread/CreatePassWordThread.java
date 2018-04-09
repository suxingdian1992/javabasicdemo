package demo.thread;

import java.util.LinkedList;

public class CreatePassWordThread extends Thread{
	private LinkedList<String> passwordLib;
	private String password;
	
	
	@Override
	public void run() {
		// 遍历三位密码
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
						System.out.println("当前匹配成功的密码是：" + String.valueOf(cs));
						// break outloop;
					}

				}
			}
		}
	}
	/**
	 * 构造方法
	 * @param passwordLib 密码容器
	 * @param password 正确密码
	 */
	public CreatePassWordThread(LinkedList<String> passwordLib,String password) {
		this.password = password;//为正确密码赋值
		this.passwordLib = passwordLib;//为密码容器传值
	}
}
