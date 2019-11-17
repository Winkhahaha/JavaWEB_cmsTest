package edu.xalead.web.servlet.backend.edu.xalead;

import edu.xalead.web.servlet.backend.edu.xalead.dao.DB;
import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.Article;
import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.Channel;
import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.Page;
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

@WebServlet(name = "ChannelServlet", urlPatterns = "/backend/ChannelServlet")
public class ChannelServlet extends HttpServlet {
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
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        //String user = ((User) request.getSession().getAttribute(User.LOGIN_USER)).getName();

        Connection conn = null;
        PreparedStatement prst = null;

        conn = DB.getConnection();
        String sql = "insert into t_channel(c_name,description) values(?,?)";

        try {
            prst = conn.prepareStatement(sql);
            prst.setString(1, name);
            prst.setString(2, description);



            int count = prst.executeUpdate();

            if (count > 0) {
                //转到成功页面
                request.getRequestDispatcher("/backend/channel/add_success.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page pg = new Page();

        String offsetstr = request.getParameter("pager.offset");
        if (offsetstr == null)
            pg.setOffset(0);
        else
            pg.setOffset(Integer.parseInt(offsetstr));

        pg.setPageNumber(5);

        String sql = "select * from t_channel limit ?,?";

        PreparedStatement prst = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = DB.getConnection();
            prst = conn.prepareStatement(sql);
            prst.setInt(1, pg.getOffset());
            prst.setInt(2, pg.getPageNumber());
            rs = prst.executeQuery(); //得到连线集
            //转成离线集
            ArrayList<ArrayList<String>> table = DB.convertResultSet2ArrayList(rs);
            //带着离线集转到列表页面显示
            pg.setData(table);

            String sqlCount="select count(*) from t_channel";
            //查询总记录数
            prst=conn.prepareStatement(sqlCount);
            rs=prst.executeQuery();
            rs.next();
            int count =rs.getInt(1);
            pg.setTotal(count);

            request.setAttribute("pg", pg);
            request.getRequestDispatcher("/backend/channel/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先把表单数据拿到
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Connection conn = null;
        PreparedStatement prst = null;
        //ResultSet rs=null;
        conn = DB.getConnection();
        String sql = "update t_channel set c_name=?,description=? where c_id=?";
        try {

            prst = conn.prepareStatement(sql);
            prst.setString(1, name);
            prst.setString(2, description);

            prst.setInt(3, Integer.parseInt(id));
            prst.executeUpdate();
            request.getRequestDispatcher("/backend/channel/update_success.jsp").forward(request, response);
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
        String sql = "select * from t_channel where c_id=? ";
        try {

            prst = conn.prepareStatement(sql);
            prst.setInt(1, Integer.parseInt(id));
            rs = prst.executeQuery();
            rs.next();
            Channel c = new Channel();
            c.setId(rs.getInt("c_id"));
            c.setName(rs.getString("c_name"));
            c.setDescription(rs.getString("description"));


            request.setAttribute("c", c);
            request.getRequestDispatcher("/backend/channel/updateInput.jsp").forward(request, response);
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
        String sql = "delete from t_channel where c_id=?";
        int count = 0;//记录删除的记录数
        try {
            for (String id : ids) {
                prst = conn.prepareStatement(sql);
                prst.setInt(1, Integer.parseInt(id));
                prst.executeUpdate();
                count++;
            }

            request.getSession().setAttribute("count", count);
            request.getRequestDispatcher("/backend/channel/del_success.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }
}
