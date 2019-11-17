<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/4/21
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--items:数据总记录数;maxPageItems:每页最大显示数;--%>
<pg:pager url="/TestTaglib.jsp" items="1000" maxPageItems="20" export="currentPageNumber=pageNumber">
<pg:first>
    <a href="${pageUrl}">首页</a>
</pg:first>

<pg:prev>
    <a href="${pageUrl}">上页</a>
</pg:prev>

    <pg:pages>

    <c:if test="${currentPageNumber eq pageNumber}">
        ${pageNumber}
    </c:if>
    <c:if test="${currentPageNumber != pageNumber}">
        <a href="${pageUrl}">${pageNumber}</a>
    </c:if>

    </pg:pages>

<pg:next>
    <a href="${pageUrl}">下页</a>
</pg:next>

<pg:last>
    <a href="${pageUrl}">尾页</a>
</pg:last>
</pg:pager>
</body>
</html>
<%--设置数字页码--%>