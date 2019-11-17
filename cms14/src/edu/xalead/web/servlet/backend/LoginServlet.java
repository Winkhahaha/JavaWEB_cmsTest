package edu.xalead.web.servlet.backend;

import com.mysql.jdbc.Driver;
import edu.xalead.web.servlet.backend.edu.xalead.dao.DB;
import edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

@WebServlet(name = "LoginServlet",urlPatterns = {"/backend/servlet/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
//                Statement st;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            //获取表单提交的数据
            String user = request.getParameter("user");
            String pwd = request.getParameter("pwd");
            String chknumber = request.getParameter("chknumber");
            //校验验证码
            String chkcode = (String)request.getSession().getAttribute("checkcode");
            if(chknumber.toUpperCase().equals(chkcode.toUpperCase())){
                //校验登陆帐号
                String sql = "select * from t_user where user = ?" ;



                conn = DB.getConnection();
                prst = conn.prepareStatement(sql);
                prst.setString(1,"admin");
                rs = prst.executeQuery();
                if(rs.next()){
                   int id = rs.getInt("uid");
                    String user1 = rs.getString("user");
                    String pwd1 = rs.getString("pwd");
                    if(user1.equals(user) ){
                        //校验密码
                        if(pwd1.equals(pwd)){
                            //创建令牌
                            User u = new User();
                            u.setName(user);
                            u.setLoginTime(new Date());
                            request.getSession().setAttribute(User.LOGIN_USER,u);

                            request.getRequestDispatcher("/backend/main.jsp").forward(request,response);
                        }else{
                            request.setAttribute("errors","密码名输入错误！");
                            //转回登陆页面
                            request.getRequestDispatcher("/backend/login.jsp").forward(request,response);
                        }
                    }else{
                        request.setAttribute("errors","用户名输入错误！");
                        //转回登陆页面
                        request.getRequestDispatcher("/backend/login.jsp").forward(request,response);
                    }
                }
            }else{
                request.setAttribute("errors","验证码输入错误！");

                //转回登陆页面
                request.getRequestDispatcher("/backend/login.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection(conn);
        }
    }
}
