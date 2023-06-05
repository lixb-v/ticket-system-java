package LXab.servlet;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import LXab.dao.CinemaDao;
import com.alibaba.fastjson2.JSONObject;

import LXab.controller.CinemaController;
import LXab.entity.Cinema;


public class CinemaServlet extends HttpServlet {

    /**
     * 根据id查询电影院信息
     * */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
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
}
