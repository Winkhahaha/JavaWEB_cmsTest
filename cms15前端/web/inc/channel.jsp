<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/1
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/common.jsp"%>
<div class="index_topic">
    <img src="images/jiantou.gif" style="float:left">
    <span style="margin-top:8px;float:left;FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #852b2b; FONT-FAMILY: 宋体;">${c.name}</span>
    <a href="#"><img src="images/more_gray.gif" style="float:right;border:0px"></a>
    <c:forEach items="${h}" var="tt">
        <div class="index_topic_list">
            <img src="images/h_article.gif" >
            <a href="#">${tt[1]}</a>
            <span>[2010-07-18]</span>
        </div>
    </c:forEach>
</div>
