<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/main.css">
<title>欢迎访问领航致远JAVA联盟【频道名称】</title>
</head>
<body>
<!-- 网站的logo，其它背景，首页横幅广告等等 -->
<div id="top">
	<img src="images/logo.gif" class="logo" title="领航致远JAVA联盟">
	<div class="bg">
	</div>
</div>
<!-- 首页的导航条 -->
<div id="nav">
	<div id="menu">
		<a href="_index.jsp">首页</a>
		<a href="#">Java SE</a>
		<a href="#">Java EE</a>
		<a href="#">JBPM</a>
		<a href="#">Android</a>
		<a href="#">OpenSources</a>
		<a href="#">分析与设计</a>
		<a href="#">论坛</a>
	</div>
</div>
<!-- 首页中间 -->
<div id="mid">
	<!-- 首页左边 -->
	<div id="left" style="text-align:left">
		首页 -&gt; JBPM
		<hr>
		<c:forEach items="${pg.data}" var="t">


<!-- 文章标题及简介 -->
<div class="h2title">${t[1]}</div>
<div CLASS="atime">
作者：${t[3]}
|来源: ${t[5]}
|创建时间: ${t[4]}
</div>
<c:out value="${fn:substring(t[2],0,20)}"/>
<br/>
<a href="/mainServlet?method=detail&channelId=${t[8]}">【阅读全文】</a>
		</c:forEach>
	<%--分页组件--%>
		<jsp:include page="common/page.jsp">
			<jsp:param name="url" value="/mainServlet"></jsp:param>
			<jsp:param name="method" value="topic"></jsp:param>
		</jsp:include>
	</div>
	<!-- 首页右边 -->
	<div class="right">
		<div class="right_topic_1">
			本站搜索
		</div>
		<div class="right_topic_2">
		</div>
		<div class="right_topic_3">
			<form>
			&nbsp;&nbsp;<input type="text">
			<input type="submit" value="搜索"> <br/>
			<input type="radio" name="type" value="baidu">百度
			<input type="radio" name="type" value="google">谷歌
			<input type="radio" name="type" value="local">本站
			</form>
		</div>
	</div>
	
	<div class="right" >
		<div class="right_topic_1">
			最新发表
		</div>
		<div class="right_topic_2">
			<a href="#"><img src="images/more_red.gif" style="float:right;margin-top:10px;border:0px"></a>
		</div>
		<div class="right_topic_3">
			· <a href="#">概念模型 - 读《分析模式》之一</a>
		</div>	
	</div>
	
	<div style="clear:both"></div>
</div>
<!-- 首页下部，版权信息等等 -->
<div id="bottom">
	<hr style="border:1px dashed gray; height:1px;width:500px"> 
	<a href="#">关于本站</a> | 
	<a href="#">版权声明</a> | 
	<a href="#">联系方式</a> |
	<a href="#">在线留言</a>
	<br>
	&copy;www.topxp.org, all rights reserved.
	<strong><a href="http://www.miibeian.gov.cn">京ICP备06046340号</a></strong>
</div>
</body>
</html>