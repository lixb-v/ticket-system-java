package LXab.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)  {
        try {
            res.getWriter().println("我是个demo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Test() {

    }
}
