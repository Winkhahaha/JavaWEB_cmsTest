<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/common.jsp"%>
<pg:pager url="${param.url}"
          items="${pg.total}"
          maxPageItems="${pg.pageNumber}"
          export="currentPageNumber=pageNumber">
    <pg:param name="method" value="${param.method}"/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong>${pg.total}</strong> 条记录，当前第<strong>${pg.currentPage}</strong> 页，共 <strong>${pg.totalPage}</strong> 页</span></div></td>
            <td width="67%" align=right vAlign="center" noWrap>
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
            </td>
        </tr>
    </table>
</pg:pager>
