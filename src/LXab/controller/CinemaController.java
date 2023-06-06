package LXab.controller;

import java.util.List;
import java.sql.SQLException;

import LXab.dao.CinemaDao;
import LXab.entity.Cinema;
public class CinemaController {

    /**
     * 查询所有电影院信息
     * */
    public void queryAllCinema() {
        List<Cinema> cinemaList = new CinemaDao().queryAllAinema();
        // 打印
        for(Cinema item : cinemaList) {
            System.out.println(item.toString());
        }
    }

    /**
     * 根据id查询电影院
     * */

    public Cinema queryCinemaById(int id) {
        Cinema myCinema = new CinemaDao().queryCinemaById(id);
        return myCinema;
    }

    /**
     * 根据id查询电影院-用线程查询以及以传统方式连接数据库
     * */

    public void queryCinemaByIdOld(int id, int time) {
        for(int i = 0; i < time; i++) {
            Cinema myCinema = new CinemaDao().queryCinemaByIdOld(id);
//            System.out.println(myCinema.toString());
        }
    }

    /**
     * 根据id查询电影院-用线程查询以及以连接池方式连接数据库
     * */

    public void queryCinemaByIdNew(int id, int time) {
        for (int i = 0; i < time; i++) {
            Cinema myCinema = new CinemaDao().queryCinemaByIdNew(id);
//            System.out.println(myCinema.toString());
        }
    }

    /**
     * 新增一条电影院数据
     * */
    public void addChiema(String name, String address, String openingTime, String shutdownTime) {
        Cinema newCinema = new Cinema();

        newCinema.setName(name);
        newCinema.setAddress(address);
        newCinema.setOpeningTime(openingTime);
        newCinema.setShutDownTime(shutdownTime);

        try {
            new CinemaDao().addChinema(newCinema);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id删除影院
     * */
    public void deleteChiemaById(int id) {
        try {
            new CinemaDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id编辑电影
     * **/

    public void updateChiemaById(int id, String name, String address, String openingTime, String shutdownTime) {
        Cinema updateCinema = new Cinema();

        updateCinema.setId(id);
        updateCinema.setName(name);
        updateCinema.setAddress(address);
        updateCinema.setOpeningTime(openingTime);
        updateCinema.setShutDownTime(shutdownTime);
        try {
            new CinemaDao().updateCinemaById(updateCinema);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
