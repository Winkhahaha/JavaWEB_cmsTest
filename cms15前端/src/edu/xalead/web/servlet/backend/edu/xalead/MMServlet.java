package edu.xalead.web.servlet.backend.edu.xalead;

import edu.xalead.web.servlet.backend.edu.xalead.dao.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "MMServlet",urlPatterns = {"/index.jsp"})
public class MMServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        Connection conn= DB.getConnection();
        PreparedStatement prst=null;
        String sql="select * from t_channel limit 0,4";
        ResultSet rs=null;

        try {
            prst=conn.prepareStatement(sql);
            rs =prst.executeQuery();

            ArrayList<ArrayList<String>> t = DB.convertResultSet2ArrayList(rs);
            request.getSession().setAttribute("t",t);
            request.getRequestDispatcher("/_index.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }
}
