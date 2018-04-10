package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * ���ӳ�demo��һ���򵥵����ӳأ������ӳ���������ӳ�ʼ��
 * @author suxin
 *
 */
public class ConnectionPoolDemo {
	List<Connection> cs = new ArrayList<Connection>();
	  
    int size;
  
    public ConnectionPoolDemo(int size) {
        this.size = size;
        init();
    }
  
    public void init() {
        //����ǡǡ����ʹ��try-with-resource�ķ�ʽ����Ϊ��Щ���Ӷ���Ҫ��"��"�ģ���Ҫ���Զ��ر���
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < size; i++) {
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sxdtest?characterEncoding=UTF-8", "xdsu", "161210318sxd");
                cs.add(c);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    public synchronized Connection getConnection() {
        while (cs.isEmpty()) {//�����ӳص������þ���ʱ��ȴ����ȵ����ӳ����п��õ�����ʱ�ظ�
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Connection c = cs.remove(0);
        return c;
    }
  
    public synchronized void returnConnection(Connection c) {
        cs.add(c);
        this.notifyAll();
    }
}

