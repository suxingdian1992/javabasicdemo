package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {
	public static void main(String[] args) {
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	
	    try (
				Connection c = DriverManager.getConnection(
						"jdbc:mysql://127.0.0.1:3306/sxdtest?characterEncoding=UTF-8", "xdsu", "161210318sxd");
	        Statement s = c.createStatement();             
	    )
	    {
	    	//����
	        String sql = "insert into hero values(null," + "'��Ī'" + "," + 313.0f + "," + 50 + ")";
	        s.execute(sql);
	        
	        //ɾ��
	        sql = "delete from hero where id = 5";
	        s.execute(sql);
	        
	        //�޸�
	        sql = "update hero set name = 'name 5' where id = 3";
            s.execute(sql);
            
            //��ѯ
            sql = "select * from hero";
            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");// ����ʹ���ֶ���
                String name = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
            }
            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
            // rs.close();
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
}
