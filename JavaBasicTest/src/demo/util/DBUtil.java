package demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 一般带有数据库连接的程序都会有这么一个类，用来放数据库用户名密码，数据库名，编码格式等等
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
    static{//静态初始化块 代码初始化块，相当于在类的每个构造方法的第一行执行
        //第一次使用类的时候，装入类，初始化静态数据区，执行类的静态初始化块
    	//创建类的对象时，调用构造方法：1、调用父类构造 2、初始化实例数据域，执行实例初始化代码块 3、执行构造方法方法体
    	
    	try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
   
    /**
     * 获取数据库连接，返回数据库连接
     * @return 数据库连接
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
