<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/5/1
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<c:forEach items="${nav}" var="n">


<a href="/mainServlet?method=topic&channelId=${n[0]}">${n[1]}</a>

</c:forEach>
