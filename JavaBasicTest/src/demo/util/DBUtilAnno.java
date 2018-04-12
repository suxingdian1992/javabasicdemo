package demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import demo.anno.JDBCConfig;

/**
 * 使用注解的DBUtil
 * @author suxin
 *
 */
@JDBCConfig(ip = "127.0.0.1", database = "sxdtest", encoding = "UTF-8", loginName = "xdsu", password = "161210318sxd")//为当前自定义注解加入值
public class DBUtilAnno {
	static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    public static Connection getConnection() throws SQLException, NoSuchMethodException, SecurityException {
        JDBCConfig config = DBUtilAnno.class.getAnnotation(JDBCConfig.class);//从当前类中通过反射取得注解的对象，然后获取注解对象的值完成初始化
 
        String ip = config.ip();//注解解析，从注解中取出值
        int port = config.port();
        String database = config.database();
        String encoding = config.encoding();
        String loginName = config.loginName();
        String password = config.password();
 
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s", ip, port, database, encoding);
        return DriverManager.getConnection(url, loginName, password);
    }
     
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, SQLException {
        Connection c = getConnection();
        System.out.println(c);
    }
}
