package edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo;

import java.util.Date;

public class User {
    public static final String LOGIN_USER = "login_user";
    private Date loginTime;
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getLoginTime() {
        return loginTime;
    }
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
