package demo.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HelloJDBC {
	public static void main(String[] args) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "insert into hero values(null,?,?,?)";
		try (
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sxdtest?characterEncoding=UTF-8", "xdsu", "161210318sxd");
				PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        )
        {
			ps.setString(1, "盖伦");
            ps.setFloat(2, 616);
            ps.setInt(3, 100);
   
            // 执行插入语句
            ps.execute();
   
            // 在执行完插入语句后，MySQL会为新插入的数据分配一个自增长id
            // JDBC通过getGeneratedKeys获取该id
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println(id);
            }	
            // 查看数据库层面的元数据
            // 即数据库服务器版本，驱动版本，都有哪些数据库等等
  
            DatabaseMetaData dbmd = c.getMetaData();
  
            // 获取数据库服务器产品名称
            System.out.println("数据库产品名称:\t"+dbmd.getDatabaseProductName());
            // 获取数据库服务器产品版本号
            System.out.println("数据库产品版本:\t"+dbmd.getDatabaseProductVersion());
            // 获取数据库服务器用作类别和表名之间的分隔符 如test.user
            System.out.println("数据库和表分隔符:\t"+dbmd.getCatalogSeparator());
            // 获取驱动版本
            System.out.println("驱动版本:\t"+dbmd.getDriverVersion());
  
            System.out.println("可用的数据库列表：");
            // 获取数据库名称
            ResultSet rs1 = dbmd.getCatalogs();
            
            while (rs1.next()) {
                System.out.println("数据库名称:\t"+rs1.getString(1));
            }
  
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
