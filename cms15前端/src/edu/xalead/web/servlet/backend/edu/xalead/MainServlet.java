package edu.xalead.web.servlet.backend.edu.xalead;

import edu.xalead.web.servlet.backend.BaseServlet;
import edu.xalead.web.servlet.backend.edu.xalead.dao.DB;
import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.Article;
import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.Channel;
import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "MainServlet",urlPatterns = {"/mainServlet"})
public class MainServlet extends BaseServlet {
    public void nav(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询频道
        Connection conn= DB.getConnection();
        PreparedStatement prst=null;
        String sql="select * from t_channel";
        ResultSet rs=null;

        try {
            prst=conn.prepareStatement(sql);
            rs =prst.executeQuery();

            ArrayList<ArrayList<String>> nav = DB.convertResultSet2ArrayList(rs);
            request.getSession().setAttribute("nav",nav);
            request.getRequestDispatcher("/inc/nav.jsp").include(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }

    public void headline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询频道
        Connection conn= DB.getConnection();
        PreparedStatement prst=null;
        String sql="select * from t_article where headline=true limit 0,2";
        ResultSet rs=null;

        try {
            prst=conn.prepareStatement(sql);
            rs =prst.executeQuery();

            ArrayList<ArrayList<String>> h = DB.convertResultSet2ArrayList(rs);
            request.getSession().setAttribute("h",h);
            request.getRequestDispatcher("/inc/headline.jsp").include(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }

    public void chn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询频道
        int channelId = Integer.parseInt(request.getParameter("channelId"));
        Connection conn= DB.getConnection();
        PreparedStatement prst=null;
        String sql="select * from t_article where c_id = ? limit 0,9";
        String sql1="select * from t_channel where c_id = ?";
        ResultSet rs=null;

        try {
            prst=conn.prepareStatement(sql);
            prst.setInt(1,channelId);
            rs =prst.executeQuery();

            ArrayList<ArrayList<String>> h = DB.convertResultSet2ArrayList(rs);
            request.setAttribute("h",h);

           prst=conn.prepareStatement(sql1);
           prst.setInt(1,channelId);
           rs=prst.executeQuery();

            Channel c = new Channel();
            rs.next();
            c.setId(rs.getInt(1));
            c.setName(rs.getString(2));
            c.setDescription(rs.getString(3));

            request.setAttribute("c",c);
            request.getRequestDispatcher("/inc/channel.jsp").include(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }

    public void topic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询频道
        Page pg = new Page();

        String offsetstr = request.getParameter("pager.offset");
        if (offsetstr == null)
            pg.setOffset(0);
        else
            pg.setOffset(Integer.parseInt(offsetstr));

        pg.setPageNumber(5);



        int channelId = Integer.parseInt(request.getParameter("channelId"));
        Connection conn= DB.getConnection();
        PreparedStatement prst=null;
        String sql="select * from t_article where c_id = ? limit ?,?";
        String total="select count(*) from t_article where c_id = ?";
        ResultSet rs=null;

        try {
            prst=conn.prepareStatement(sql);
            prst.setInt(1,channelId);
            prst.setInt(2,pg.getOffset());
            prst.setInt(3,pg.getPageNumber());
            rs =prst.executeQuery();

            ArrayList<ArrayList<String>> h = DB.convertResultSet2ArrayList(rs);
            pg.setData(h);

            prst=conn.prepareStatement(total);
            prst.setInt(1,channelId);
            rs=prst.executeQuery();
            rs.next();
            pg.setTotal(rs.getInt(1));

            request.setAttribute("pg",pg);

            request.getRequestDispatcher("/topic.jsp").include(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询频道

        int channelId = Integer.parseInt(request.getParameter("channelId"));
        Connection conn= DB.getConnection();
        PreparedStatement prst=null;
        String sql="select * from t_article where c_id = ?";
        ResultSet rs=null;

        try {
            prst=conn.prepareStatement(sql);
            prst.setInt(1,channelId);

            rs =prst.executeQuery();
            rs.next();
            Article a = new Article();
            a.setId(rs.getInt("a_id"));
            a.setTitle(rs.getString("title"));
            a.setContent(rs.getString("content"));
            a.setAuthor(rs.getString("author"));
            a.setCreateTime(rs.getTime("createTime"));
            a.setSource(rs.getString("source"));
            a.setChannelId(rs.getInt("c_id"));


            request.setAttribute("a",a);

            request.getRequestDispatcher("/article.jsp").include(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection(conn);
        }
    }
}
