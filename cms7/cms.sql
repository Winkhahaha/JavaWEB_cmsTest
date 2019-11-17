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

CREATE TABLE t_channel(
  c_id INT PRIMARY KEY AUTO_INCREMENT,
  c_name VARCHAR(255),
  description VARCHAR(2000)
);

CREATE TABLE t_article(
  a_id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255),
  content VARCHAR(2000),
  author VARCHAR(255),
  createTime DATE,
  source VARCHAR(255),
  headline BOOLEAN,
  topic BOOLEAN,
  c_id INT
);

ALTER TABLE t_article
ADD CONSTRAINT fk_article_channel
FOREIGN KEY (c_id)
REFERENCES t_channel(c_id)

INSERT INTO `cms`.`t_article` (`a_id`, `title`, `content`, `author`, `source`, `headline`, `topic`) VALUES ('1', '华为', '枯要要要要要在在在', '在在在在', 'www.sohu.com', 'true', 'false');