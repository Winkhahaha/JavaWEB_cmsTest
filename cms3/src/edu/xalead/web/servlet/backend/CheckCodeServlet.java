package edu.xalead.web.servlet.backend;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet(name = "CheckCodeServlet",urlPatterns = {"/backend/images/checkcode.png"})
public class CheckCodeServlet extends HttpServlet {
    private String code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    //产生随机序列数字
    private char genericCheckCode(){
        int count = code.length();
        int randomIndex = new Random().nextInt(count);//得到随机的一个位置
        return code.charAt(randomIndex);
    }

    //产生生成颜色的随机数
    private int genericNum(){
        return new Random().nextInt(200);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer checkcode = new StringBuffer();
        //随机生成验证码
        for(int i = 0 ; i < 4 ; i++){
            checkcode.append(genericCheckCode());
        }
        String ccstr = checkcode.toString();//得到验证码

        //把验证码存起来，用于验证
        request.getSession().setAttribute("checkcode",ccstr);

        //先在内存中生成图片
        BufferedImage img = new BufferedImage(44,20, BufferedImage.TYPE_INT_RGB);

        //创建画笔
        Graphics2D g2d = (Graphics2D) img.getGraphics();//从图片缓存对象中取出画笔对象（这个对象就是修改缓存区的象素数据(背景框)的对象）

        //填充白色背景
        g2d.setColor(new Color(255,255,255));//画笔设置为白色
        g2d.fillRect(0,0,44,20);//从设置坐标开始填充白色背景

        //开始画验证码
        for(int i = 0 ; i < ccstr.length() ; i++){
            g2d.setColor(new Color(genericNum(),genericNum(),genericNum()));//随机生成画笔颜色
            String c = ccstr.substring(i,i+1);
            g2d.drawString(c,11 * i,16);
        }

        //把图片缓存转换某种图片格式（jpg,gif,png）写到客户端
        response.setContentType("image/jpeg");
        OutputStream output = response.getOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
        encoder.encode(img);
    }
}
