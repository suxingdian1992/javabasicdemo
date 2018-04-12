package demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import demo.anno.JDBCConfig;

/**
 * ʹ��ע���DBUtil
 * @author suxin
 *
 */
@JDBCConfig(ip = "127.0.0.1", database = "sxdtest", encoding = "UTF-8", loginName = "xdsu", password = "161210318sxd")//Ϊ��ǰ�Զ���ע�����ֵ
public class DBUtilAnno {
	static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    public static Connection getConnection() throws SQLException, NoSuchMethodException, SecurityException {
        JDBCConfig config = DBUtilAnno.class.getAnnotation(JDBCConfig.class);//�ӵ�ǰ����ͨ������ȡ��ע��Ķ���Ȼ���ȡע������ֵ��ɳ�ʼ��
 
        String ip = config.ip();//ע���������ע����ȡ��ֵ
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
