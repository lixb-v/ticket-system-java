package LXab.servlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import LXab.utils.BasicsServlet;
import LXab.dao.CinemaDao;
import LXab.entity.Cinema;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;



public class CinemaServlet extends BasicsServlet {

    /**
     * 根据id查询电影院信息
     * */
    public void queryCinemaById(HttpServletRequest req, HttpServletResponse res) {
        String StrId = req.getParameter("id");
        int id = Integer.parseInt(StrId);

        // 获取对象
        Cinema resCinema = new CinemaDao().queryCinemaById(id);


        // 设置状态码
        res.setStatus(200);
        // 设置响应格式
//        res.setContentType("text/html; charset=UTF-8");
        res.setContentType("application/json; charset=UTF-8");

        // 把对象转为JSON格式
        String jsonOnject = JSONObject.toJSONString(resCinema);
        try {
            res.getWriter().println(jsonOnject);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询所有电影院
     * */
    public void queryAllChinema(HttpServletRequest req, HttpServletResponse res) {
        List<Cinema> allCinema = new CinemaDao().queryAllAinema();

        // 设置响应状态吗
        res.setStatus(200);

        // 设置响应格式
        res.setContentType("application/json; charset=UTF-8");
        // 把对象转为JSON格式
        String jsonOnject = JSONObject.toJSONString(allCinema);
        try {
            res.getWriter().println(jsonOnject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id及参数编辑数据
     * */
    public void updateChinemaById(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("application/json; charset=UTF-8");

        Boolean bool = false;
        try {
            Cinema cinemaData = JSON.parseObject(req.getInputStream(), Cinema.class);
            bool = new CinemaDao().updateCinemaById(cinemaData);
            if(bool) {
                res.setStatus(200);
                res.getWriter().println("编辑成功");
            } else {
                res.setStatus(500);
                res.getWriter().println("编辑失败");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id删除数据
     * */
    public void deleteChinemaById(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("application/json; charset=UTF-8");
        Boolean bool = false;
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            bool  = new CinemaDao().deleteById(id);
            if(bool) {
                res.getWriter().println("删除成功");
            } else  {
                res.getWriter().println("删除失败");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增电影院
     * */
    public void addChinema(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("application/json; charset=UTF-8");
        Boolean bool = false;
        try {
            Cinema newChinema = JSON.parseObject(req.getInputStream(), Cinema.class);
            bool = new CinemaDao().addChinema(newChinema);
            if(bool) {
                res.getWriter().println("新增成功");
            } else {
                res.getWriter().println("新增失败");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
