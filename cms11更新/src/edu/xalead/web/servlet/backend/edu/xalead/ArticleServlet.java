package edu.xalead.web.servlet.backend.edu.xalead;

import edu.xalead.web.servlet.backend.edu.xalead.dao.DB;
import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.Article;
import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "ArticleServlet", urlPatterns = "/backend/ArticleServlet")
public class ArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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
            m.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//首先把表单数据拿到
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String source = request.getParameter("source");
        String user = ((User) request.getSession().getAttribute(User.LOGIN_USER)).getName();

        Connection conn = null;
        PreparedStatement prst = null;

        conn = DB.getConnection();
        String sql = "insert into t_article(title,content,source,createTime,author) values(?,?,?,now(),?)";

        try {
            prst = conn.prepareStatement(sql);
            prst.setString(1, title);
            prst.setString(2, content);
            prst.setString(3, source);
            prst.setString(4, user);

            int count = prst.executeUpdate();

            if (count > 0) {
                //转到成功页面
                request.getRequestDispatcher("/backend/article/add_success.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            request.setAttribute("list", table);
            request.getRequestDispatcher("/backend/article/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先把表单数据拿到
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String source = request.getParameter("source");

        Connection conn = null;
        PreparedStatement prst = null;
        //ResultSet rs=null;
        conn = DB.getConnection();
        String sql = "update t_article set title=?,source=?,content=? where a_id=?";
        try {

            prst = conn.prepareStatement(sql);
            prst.setString(1, title);
            prst.setString(2, source);
            prst.setString(3, content);
            prst.setInt(4, Integer.parseInt(id));
            prst.executeUpdate();
            request.getRequestDispatcher("/backend/article/update_success.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    public void updateInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先把表单数据拿到
        String id = request.getParameter("id");


        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        conn = DB.getConnection();
        String sql = "select * from t_article where a_id=? ";
        try {

            prst = conn.prepareStatement(sql);
            prst.setInt(1, Integer.parseInt(id));
            rs = prst.executeQuery();
            rs.next();
            Article a = new Article();
            a.setId(rs.getInt("a_id"));
            a.setTitle(rs.getString("title"));
            a.setContent(rs.getString("content"));
            a.setAuthor(rs.getString("author"));
            a.setCreateTime(rs.getTime("createTime"));
            a.setSource(rs.getString("source"));

            request.setAttribute("a", a);
            request.getRequestDispatcher("/backend/article/updateInput.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先把表单数据拿到
        String ids[] = request.getParameterValues("id");

        Connection conn = null;
        PreparedStatement prst = null;

        conn = DB.getConnection();
        String sql = "delete from t_article where a_id=?";
        int count = 0;//记录删除的记录数
        try {
            for (String id : ids) {
                prst = conn.prepareStatement(sql);
                prst.setInt(1, Integer.parseInt(id));
                prst.executeUpdate();
                count++;
            }

            request.getSession().setAttribute("count", count);
            request.getRequestDispatcher("/backend/article/del_success.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }
}
