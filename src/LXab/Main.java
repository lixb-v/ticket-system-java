package src.LXab;

import src.LXab.controller.CinemaController;
import src.LXab.utils.ShutdownHook;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CinemaController myCinemaController = new CinemaController();
        // 根据id查找影院
//        myCinemaController.queryCinemaById(2);


        // 查找所有影院
//        myCinemaController.queryAllCinema();

        //  新增一条数据
//        myCinemaController.addChiema("飞扬影院", "正佳广场", "12：00", "03：00");

        // 根据id删除影院
//        myCinemaController.deleteChiemaById(16);

        // 修改
//        myCinemaController.updateChiemaById(16, "新飞扬影院", "正佳广场", "9：00", "3：00");




        // 注册程序关闭hook
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());

    }

    /**
     * 对比传统数据库连接方式和连接池连接方式的时间差异
     * */
    public void diffStatmentTime() {
        CinemaController myCinemaController = new CinemaController();

        int statementTime = 1000; // 执行次数

        System.out.println("传统方式查询数据库并计算时间开始");
        long s1 = System.currentTimeMillis(); // 获取开始时间
        myCinemaController.queryCinemaByIdOld(16, statementTime);
        long e1 = System.currentTimeMillis(); // 获取结束时间
        System.out.println("传统方式查询数据库并计算时间结束");
        System.out.println("传统方式查询数据" + statementTime + "次。耗时：" + (e1 - s1));


        System.out.println("连接池方式查询多次数据库并计算时间开始");
        long s2 = System.currentTimeMillis(); // 开始时间
        myCinemaController.queryCinemaByIdNew(16, statementTime);
        long e2 = System.currentTimeMillis(); // 结束时间
        System.out.println("连接池方式查询多次数据库并计算时间结束");
        System.out.println("数据库连接方式查询数据" + statementTime + "次。耗时：" + (e2 - s2));
    }
}
