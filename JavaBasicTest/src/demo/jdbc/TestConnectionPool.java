package demo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnectionPool {
    public static void main(String[] args) {
    	ConnectionPoolDemo cp = new ConnectionPoolDemo(3);
        for (int i = 0; i < 100; i++) {
            new WorkingThread("working thread" + i, cp).start();
        }
    }
}
   
class WorkingThread extends Thread {
    private ConnectionPoolDemo cp;
   
    public WorkingThread(String name, ConnectionPoolDemo cp) {
        super(name);
        this.cp = cp;
    }
   
    public void run() {
        Connection c = cp.getConnection();//从连接池取得连接
        System.out.println(this.getName()+ ":\t 获取了一根连接，并开始工作"  );
        try (Statement st = c.createStatement()){
             
            //模拟时耗１秒的数据库ＳＱＬ语句
            Thread.sleep(1000);
            st.execute("select * from hero");
            ResultSet rs = st.getResultSet();
            rs.next();
            System.out.println(rs.getString("name"));
   
        } catch (SQLException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        cp.returnConnection(c);//结束之后归还连接而不是关闭，cp代表初始话的连接池
    }
}
