/*
用户表

user 用户名
pwd 密码
createTime
updateTime
deleteTime
*/
CREATE DATABASE cms;
USE cms;
CREATE TABLE t_user(
  uid INT PRIMARY KEY AUTO_INCREMENT,
  user VARCHAR(50) NOT NULL,
  pwd VARCHAR(50) NOT NULL
);

insert into t_user(user,pwd) values("admin","admin");