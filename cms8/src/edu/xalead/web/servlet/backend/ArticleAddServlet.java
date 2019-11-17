package edu.xalead.web.servlet.backend;

import edu.xalead.web.servlet.backend.edu.xalead.dao.DB;
import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "ArticleAddServlet",urlPatterns = {"/backend/ArticleAddServlet"})
public class ArticleAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String title = request.getParameter("title");
        String content =request.getParameter("content");
        String source =request.getParameter("source");
        String user = ((User) request.getSession().getAttribute(User.LOGIN_USER)).getName();

        Connection conn=null;
        PreparedStatement prst =null;

        conn= DB.getConnection();
        String sql = "insert into t_article(title,content,source,createtime,author) values(?,?,?,now(),?)";

        try {
            prst=conn.prepareStatement(sql);
            prst.setString(1,title);
            prst.setString(2,content);
            prst.setString(3,source);
            prst.setString(4,user);

            prst.executeUpdate();

            request.getRequestDispatcher("/backend/article/add_success.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }

    }

}
