<%--
  Created by IntelliJ IDEA.
  User: elodie
  Date: 02/05/2019
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Infos</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<body>
<c:import url="inc/choose-navbar.jsp" />
<div class="container">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center mb-5">Utilisateurs</h2>
        <div class="panel panel-info">
            <div class="panel-body">
                <table class="table table-striped table-bordered">

                    <tr>
                        <th>Pseudo</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Action</th>
                    </tr>


                    <c:forEach var="tempUser" items="${users}">

                    <!-- construct an "view" link with user id -->
                    <c:url var="viewLink" value="/admin/user/${tempUser.id}/profile" />

                        <tr>
                            <td>${tempUser.username}</td>
                            <td>${tempUser.email}</td>
                            <td>${tempUser.userRole.roleName}</td>
                            <td>
                                <!-- display the view link -->
                                  <a href="${viewLink}">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <h2 class="text-center mt-5 mb-5">Roles</h2>
        <div class="panel panel-info">
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>Id</th>
                        <th>Nom</th>
                    </tr>
                    <c:forEach var="userRole" items="${roles}" >
                        <tr>
                            <td>${userRole.id}</td>
                            <td>${userRole.roleName}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
