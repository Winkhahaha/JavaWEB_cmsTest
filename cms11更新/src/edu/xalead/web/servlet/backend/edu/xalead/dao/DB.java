package edu.xalead.web.servlet.backend.edu.xalead.dao;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    public static Connection getConnection(){
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/cms";
        String username = "root";
        String password = "root";

        Connection conn = null;

        try {
            Class.forName(driverClass);//加载数据库驱动
            conn = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ArrayList<ArrayList<String>> convertResultSet2ArrayList(ResultSet rs){
        ResultSetMetaData meta = null;
        ArrayList<ArrayList<String>> table = null;
        try {
            meta = rs.getMetaData();
            //转成离线集
            table = new ArrayList<>();
            while(rs.next()){
                ArrayList<String> row = new ArrayList<>();  //行
                for(int i = 1 ; i <= meta.getColumnCount(); i++){   //每列
                    row.add(rs.getString(i));
                }
                table.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static void closeConnection(Connection conn){
        try {
            if(conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
