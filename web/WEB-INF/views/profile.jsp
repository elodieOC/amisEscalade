<%--
  Created by IntelliJ IDEA.
  User: elodie
  Date: 17/04/2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<body>
<c:import url="inc/navbar_connected.jsp" />
<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Profil</h2>
        <div class="panel panel-info">
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>Pseudo</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>

                    <!-- construct an "update" link with user id -->
                    <c:url var="updateLink" value="/user/updateForm">
                        <c:param name="id" value="${user.id}" />
                    </c:url>

                    <!-- construct an "delete" link with user id -->
                    <c:url var="deleteLink" value="/user/delete">
                        <c:param name="id" value="${user.id}" />
                    </c:url>

                    <tr>
                        <td>${user.username}</td>
                        <td>${user.email}</td>


                        <td>
                            <!-- display the update link --> <a href="${updateLink}">Update</a>
                            | <a href="${deleteLink}"
                                 onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
                        </td>

                    </tr>

                </table>
              <%--  <table class="table table-striped table-bordered">
                    <tr>
                        <th>Spots</th>
                    </tr>
                    <c:forEach var="spot" items="${spots}" >
                        <tr> <td>${spot.name}</td></tr>
                    </c:forEach>
                    <tr><td>${spots}</td></tr>
                </table>--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
