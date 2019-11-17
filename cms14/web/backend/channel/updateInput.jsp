<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>CMS 后台管理工作平台</title>
    <style type="text/css">
        <!--
        body {
            margin-left: 3px;
            margin-top: 0px;
            margin-right: 3px;
            margin-bottom: 0px;
        }

        .STYLE1 {
            color: #e1e2e3;
            font-size: 12px;
        }

        .STYLE6 {
            color: #000000;
            font-size: 12;
        }

        .STYLE10 {
            color: #000000;
            font-size: 12px;
        }

        .STYLE19 {
            color: #344b50;
            font-size: 12px;
        }

        .STYLE21 {
            font-size: 12px;
            color: #3b6375;
        }

        .STYLE22 {
            font-size: 12px;
            color: #295568;
        }

        A:active, A:visited, A:link {
            COLOR: #0629FD;
            TEXT-DECORATION: none
        }

        A:hover {
            COLOR: #FF6600;
            TEXT-DECORATION: none
        }

        A.relatelink:active, A.relatelink:visited, A.relatelink:link {
            color: white;
            TEXT-DECORATION: none
        }

        A.relatelink:hover {
            COLOR: #FF6600;
            TEXT-DECORATION: none
        }

        td {
            font-size: 12px;
            color: #003366;
            height: 24px
        }

        .STYLE1 a {
            COLOR: white;
        }

        .STYLE1 A:active, .STYLE1 A:visited, .STYLE1 A:link {
            COLOR: white;
            TEXT-DECORATION: none
        }

        .STYLE1 a:hover {
            COLOR: red;
        }

        -->
    </style>
</head>
<body>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30">
            <table width="80%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="24" bgcolor="#353c44">
                        <table width="80%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td>
                                    <table width="80%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="6%" height="19" valign="bottom">
                                                <div align="center"><img src="images/tb.gif" width="14" height="14"/>
                                                </div>
                                            </td>
                                            <td width="94%" valign="bottom"><span class="STYLE1"> 添加文章</span></td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <div align="right"><span class="STYLE1">

             </span><span class="STYLE1"> &nbsp;</span></div>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <form action="/backend/ChannelServlet" method="post">
                <input type="hidden" name="id" value="${c.id}">
                <input type="hidden" name="method" value="update"/>
                <table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
                    <tr>
                        <td width="4%" height="20" bgcolor="d3eaef" class="STYLE10">
                            <div align="center">
                                更新频道信息
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div align="center">
                                名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称 : <input type="text" size="40"
                                                                                name="title" value="${c.name}"/><br/>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div align="center">
                                描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述 : <textarea name="content" rows="10"
                                                                                   cols="42">${c.description}</textarea>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div align="center">
                                <input type="submit" size="40" value="保存"/>
                                <input type="reset" size="40" value="重置"/>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
    <tr>
        <td height="30">
        </td>
    </tr>
</table>
</body>
</html>

