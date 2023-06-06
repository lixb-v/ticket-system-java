package LXab.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @deprecated servlet基础类，用来分发请求
 *
 * */
public class BasicsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 统一设置字符编码
        req.setCharacterEncoding("UTF-8");

        // 获取当前请求的action字段
        String action = req.getParameter("action");

        if(action != null) {
            try {
                // 获取当前的Class
                Class curClass = this.getClass();
                // 获取方法
                Method method = curClass.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
                method.invoke(this, req, res);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
