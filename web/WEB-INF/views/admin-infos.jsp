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
<body class="d-flex flex-column h-100">
<div class="wrapper flex-shrink-0">
    <c:import url="inc/choose-navbar.jsp" />
    <div class="container h-100 align-items-center">
        <div class="container col-md-offset-1 col-md-10">
            <h2>Utilisateurs</h2>
            <hr />
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
        </div>
        <div class="container col-md-offset-1 col-md-10">
            <h2 class="mt-5">Roles</h2>
            <hr />
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
        <div class="container col-md-offset-1 col-md-10">
            <h2 class="mt-5">Spots</h2>
            <hr />
            <div class="panel panel-info">
                <div class="panel-body">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th>Id</th>
                            <th>Nom</th>
                            <th>Ville</th>
                            <th>Région</th>
                        </tr>
                        <c:forEach var="spot" items="${spots}" >
                            <tr>
                                <td>${spot.id}</td>
                                <td>${spot.name}</td>
                                <td>${spot.city}</td>
                                <td>${spot.county}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <div class="container col-md-offset-1 col-md-10">
            <h2 class="mt-5">Cotations</h2>
            <hr />
            <div class="panel panel-info">
                <div class="panel-body">
                    <table class="table table-striped table-bordered">
                        <tr>
                            <th>Id</th>
                            <th>Cotation</th>
                            <th>Niveau</th>
                        </tr>
                        <c:forEach var="cot" items="${grades}" >
                            <tr>
                                <td>${cot.id}</td>
                                <td>${cot.name}</td>
                                <td>${cot.rating.name}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
