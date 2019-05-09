<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${ empty sessionScope }">
        <%@ include file="navs/navbar.jsp"%>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${sessionScope['loggedInUserRole'] eq '1'}">
                <%@ include file="navs/navbar_admin.jsp"%>
            </c:when>
            <c:otherwise>
                <%@ include file="navs/navbar_connected.jsp"%>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>