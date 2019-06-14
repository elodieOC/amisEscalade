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
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="container col-md-offset-1 col-md-10">
            <div class="card shadow mb-5 bg-light">
                <div class="card-header">
                    <h3>Utilisateurs</h3>
                </div>
                <div class="card-body">
                    <div class="card-deck mx-auto">
                        <c:forEach var="tempUser" items="${users}">
                            <div class="card shadow mb-3 bg-light">
                                <div class="card-body">
                                    <ul class="list-unstyled">

                                        <!-- construct an "view" link with user id -->
                                        <c:url var="viewLink" value="/admin/user/${tempUser.id}/profile" />
                                        <li><strong>Pseudo: </strong>${tempUser.username}</li>
                                        <li><strong>Email: </strong>${tempUser.email}</li>
                                        <li><strong>Role: </strong>${tempUser.userRole.roleName}</li>
                                        <li>
                                            <!-- display the view link -->
                                            <a href="${viewLink}">Profil</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="card shadow mb-5 bg-light">
                <div class="card-header">
                    <h3>Spots</h3>
                </div>
                <div class="card-body">
                    <div class="card-deck mx-auto">
                        <c:forEach var="spot" items="${spots}" >
                            <div class="card shadow mb-3 bg-light">
                                <div class="card-body">
                                    <ul class="list-unstyled">
                                        <li><strong>Nom: </strong>${spot.name}</li>
                                        <li><strong>Ville: </strong>${spot.city}</li>
                                        <li><strong>Région: </strong>${spot.county}</li>
                                        <li><strong>Grimpeur: </strong>${spot.user.username}</li>
                                    </ul>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="card shadow mb-5 bg-light">
                <div class="card-header">
                    <h3>Topos</h3>
                </div>
                <div class="card-body">
                    <div class="card-deck mx-auto">
                        <c:forEach var="topo" items="${topos}" >
                            <div class="card shadow mb-3 bg-light">
                                <div class="card-body">
                                    <ul class="list-unstyled">
                                        <li><strong>Nom: </strong>${topo.name}</li>
                                        <li><strong>Ville: </strong>${topo.city}</li>
                                        <li><strong>Région: </strong>${topo.county}</li>
                                        <li><strong>Grimpeur: </strong>${topo.user.username}</li>
                                    </ul>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
