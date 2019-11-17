package edu.xalead.web.servlet.backend;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从客户取method参数的值
        String method = request.getParameter("method");

        //根据method调用对应的方法
        Class c = this.getClass();
        try {
            Method m = c.getMethod(method,
                    HttpServletRequest.class,
                    HttpServletResponse.class);
            m.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
