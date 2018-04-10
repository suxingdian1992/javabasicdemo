package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接池demo，一个简单的连接池，在连接池中完成连接初始化
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
        //这里恰恰不能使用try-with-resource的方式，因为这些连接都需要是"活"的，不要被自动关闭了
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
        while (cs.isEmpty()) {//当连接池的连接用尽的时候等待，等到连接池中有可用的连接时回复
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

