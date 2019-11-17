package edu.xalead.web.servlet.backend;

import edu.xalead.web.servlet.backend.edu.xalead.dao.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "ArticleListServlet",urlPatterns = "/backend/ArticleListServlet")
public class ArticleListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sql = "select * from t_article";

        PreparedStatement prst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DB.getConnection();
            prst = conn.prepareStatement(sql);
            rs = prst.executeQuery(); //得到连线集
            //转成离线集
            ArrayList<ArrayList<String>> table = DB.convertResultSet2ArrayList(rs);
            //带着离线集转到列表页面显示
            request.setAttribute("list",table);
            request.getRequestDispatcher("/backend/article/list.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }
}
