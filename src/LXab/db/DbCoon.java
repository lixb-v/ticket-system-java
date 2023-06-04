package src.LXab.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
public final class DbCoon {
    public static Connection getCoon() {
        Connection coon = null;
        String user = "root";
        String password = "mySql-LXab";
        String url = "jdbc:mysql://localhost:3306/ticket-system?characterEncoding=UTF-8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            coon = DriverManager.getConnection(url, user, password);
//            System.out.println("数据库连接成功");
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return coon;
    }
}
