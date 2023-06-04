package src.LXab.utils;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {
    static ConnectionPool connectionPool = new ConnectionPool(10);
    private int size;
    List<Connection> cList = new ArrayList<Connection>();

    public ConnectionPool(int size) {
        this.size = size;
        this.init();
    }

    public void init () {
        String user = "root";
        String password = "mySql-LXab";
        String url = "jdbc:mysql://localhost:3306/ticket-system?characterEncoding=UTF-8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            int i = 0;
            while (i < this.size) {
                Connection coon = DriverManager.getConnection(url, user, password);
                this.cList.add(coon);
                i++;
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection getConnectionExample() {
        if(this.cList.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Connection coon = this.cList.remove(0);
        return coon;
    }

    public synchronized void returnConnectionExample(Connection coon) {
        this.cList.add(coon);
        this.notifyAll();
    }

    public synchronized void closeeConnectionExample() {
        if(this.cList.size() < this.size) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int i = 0;
            Connection coon = null;
            while ((coon = this.cList.remove(0)) != null) {
                try {
                    coon.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }
    public static Connection getConnection() {
        return ConnectionPool.connectionPool.getConnectionExample();
    }

    public static void retrunConnection(Connection coon) {
        ConnectionPool.connectionPool.returnConnectionExample(coon);
    }


    /**
     * 关闭所有Connection
     * */
    public static void closeConnection() {
        ConnectionPool.connectionPool.closeeConnectionExample();
        System.out.println("关闭所有数据库连接成功");
    }

    /**
     * 关闭PreparedStatement
     * */
    public static void closePrepareStatement(PreparedStatement pstmt) {
        if(pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
