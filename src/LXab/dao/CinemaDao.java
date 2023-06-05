package LXab.dao;

import LXab.db.DbCoon;
import LXab.db.DbClose;
import LXab.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;

import LXab.entity.Cinema;

/**
 * ticket_cinema 表相关操作
 * */
public class CinemaDao extends Thread {

    /**
     * 查询出所有电影院（连接池方式）
     * */
    public List<Cinema> queryAllAinema() {
        Connection coon = ConnectionPool.getConnection();
        List<Cinema> cinemaList = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM ticket_cinema";
            stmt = coon.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Cinema cinema = new Cinema();
                cinema.setId(res.getInt("id"));
                cinema.setName(res.getString("name"));
                cinema.setAddress(res.getString("address"));
                cinema.setOpeningTime(res.getString("opening_time"));
                cinema.setShutdownTime(res.getString("shutdown_time"));
                cinemaList.add(cinema);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.closePrepareStatement(stmt);
            ConnectionPool.retrunConnection(coon);
        }
        return cinemaList;

    }


    /**
     * 根据id查询电影院(连接池方式)
     * */

    public Cinema queryCinemaById(int id) {
        Connection coon = ConnectionPool.getConnection();
        Cinema myCinema = new Cinema();
        PreparedStatement stemt = null;
        try {
            String sql = "SELECT * FROM ticket_cinema WHERE id = ?";
            stemt = coon.prepareStatement(sql);
            stemt.setInt(1, id);

            //  向数据库插入信息
            ResultSet res = stemt.executeQuery();
            while (res.next()) {
                myCinema.setId(res.getInt("id"));
                myCinema.setName(res.getString("name"));
                myCinema.setAddress(res.getString("address"));
                myCinema.setOpeningTime(res.getString("opening_time"));
                myCinema.setShutdownTime(res.getString("shutdown_time"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.closePrepareStatement(stemt);
            ConnectionPool.retrunConnection(coon);
        }
        return myCinema;
    }

    /**
     * 根据id查询电影院(连接池方式并模拟查询时间)
     * */

    public Cinema queryCinemaByIdNew(int id) {
        Connection coon = ConnectionPool.getConnection();
        Cinema myCinema = new Cinema();
        PreparedStatement stemt = null;
        try {
            String sql = "SELECT * FROM ticket_cinema WHERE id = ?";
            stemt = coon.prepareStatement(sql);
            stemt.setInt(1, id);

            // 模拟查询时间
            Thread.sleep(10);

            ResultSet res = stemt.executeQuery();
            while (res.next()) {
                myCinema.setId(res.getInt("id"));
                myCinema.setName(res.getString("name"));
                myCinema.setAddress(res.getString("address"));
                myCinema.setOpeningTime(res.getString("opening_time"));
                myCinema.setShutdownTime(res.getString("shutdown_time"));
            }

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.closePrepareStatement(stemt);
            ConnectionPool.retrunConnection(coon);
        }
        return myCinema;
    }

    /**
     * 根据id查询电影院(传统连接方式并模拟查询时间)
     * */
    public Cinema queryCinemaByIdOld(int id) {
        Connection coon = DbCoon.getCoon();
        Cinema myCinema = new Cinema();
        PreparedStatement stemt = null;
        try {
            String sql = "SELECT * FROM ticket_cinema WHERE id = ?";
            stemt = coon.prepareStatement(sql);
            stemt.setInt(1, id);
            // 模拟1s查询时间
            Thread.sleep(10);

            ResultSet res = stemt.executeQuery();
            while (res.next()) {
                myCinema.setId(res.getInt("id"));
                myCinema.setName(res.getString("name"));
                myCinema.setAddress(res.getString("address"));
                myCinema.setOpeningTime(res.getString("opening_time"));
                myCinema.setShutdownTime(res.getString("shutdown_time"));
            }

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(coon, stemt);
        }
        return myCinema;
    }

    /**
     * 新增电影院
     * */
    public void addChinema(Cinema cinema) throws SQLException{
        Connection coon = DbCoon.getCoon();
        PreparedStatement pstmt = null;
        try {
            coon.setAutoCommit(false);

            String sql = "INSERT INTO ticket_cinema (name, address, opening_time, shutdown_time) VALUES(?, ?, ?, ?)";
            pstmt = coon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, cinema.getName());
            pstmt.setString(2, cinema.getAddress());
            pstmt.setString(3, cinema.getOpeningTime());
            pstmt.setString(4, cinema.getShutDownTime());

            int affectedRows = pstmt.executeUpdate();

            coon.commit();

            if(affectedRows == 0) {
                System.out.println("新增电影院信息失败");
            }
            ResultSet rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                int id = rs.getInt(1);
                System.out.println(String.format("新增成功, id: %d", id));
            }
        } catch (SQLException e) {
            // 事务-回滚
            coon.rollback();
            e.printStackTrace();
        } finally {
            // 事务-设置自动提交
            coon.setAutoCommit(true);
            DbClose.close(coon, pstmt);
        }
    }

    /**
     * 根据id删除影院
     * */
    public void deleteById(int id) throws SQLException{
        Connection coon = DbCoon.getCoon();
        PreparedStatement pstemt = null;
        try {
            coon.setAutoCommit(false);
            String sql = "DELETE FROM ticket_cinema WHERE id = ?";
            pstemt = coon.prepareStatement(sql);

            pstemt.setInt(1, id);

            pstemt.execute();

            coon.commit();

            System.out.println("影院删除成功");

        } catch (SQLException e) {
            // 事务-回滚
            coon.rollback();
            e.printStackTrace();
        } finally {
            // 事务-设置自动提交
            coon.setAutoCommit(true);
            DbClose.close(coon, pstemt);

        }
    }

    /**
     * 根据id更新数据
     * */
    public Boolean updateCinemaById(Cinema updateCinema) throws SQLException {
        Boolean bool = false;
        Connection coon = DbCoon.getCoon();
        PreparedStatement pstmt = null;
        try {
            coon.setAutoCommit(false);
            String sql = "UPDATE ticket_cinema SET name=?, address=?, opening_time=?, shutdown_time=? WHERE id=?";

            pstmt = coon.prepareStatement(sql);
            pstmt.setString(1, updateCinema.getName());
            pstmt.setString(2, updateCinema.getAddress());
            pstmt.setString(3, updateCinema.getOpeningTime());
            pstmt.setString(4, updateCinema.getShutDownTime());
            pstmt.setInt(5, updateCinema.getId());

            int affectedRows = pstmt.executeUpdate();
            coon.commit();
            if(affectedRows == 0) {
                System.out.println("编辑电影失败");
            } else {
                bool = true;
            }
        } catch (SQLException e) {
            coon.rollback();
            e.printStackTrace();
        } finally {
            coon.setAutoCommit(true);
            DbClose.close(coon, pstmt);
        }

        return bool;
    }

}
