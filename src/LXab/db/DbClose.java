package src.LXab.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DbClose {
    public static void close(Connection coon, PreparedStatement s) {
        try {
            if(coon != null) {
                coon.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(s != null) {
                s.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
//        System.out.println("数据库关闭成功");
    }
}
