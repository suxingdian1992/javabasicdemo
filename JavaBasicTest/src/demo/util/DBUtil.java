package demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * һ��������ݿ����ӵĳ��򶼻�����ôһ���࣬���������ݿ��û������룬���ݿ����������ʽ�ȵ�
 * @author suxin
 *
 */
public class DBUtil {
	static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "sxdtest";
    static String encoding = "UTF-8";
    static String loginName = "xdsu";
    static String password = "161210318sxd";
    static{//��̬��ʼ���� �����ʼ���飬�൱�������ÿ�����췽���ĵ�һ��ִ��
        //��һ��ʹ�����ʱ��װ���࣬��ʼ����̬��������ִ����ľ�̬��ʼ����
    	//������Ķ���ʱ�����ù��췽����1�����ø��๹�� 2����ʼ��ʵ��������ִ��ʵ����ʼ������� 3��ִ�й��췽��������
    	
    	try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
   
    /**
     * ��ȡ���ݿ����ӣ��������ݿ�����
     * @return ���ݿ�����
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }
    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
    }
}
