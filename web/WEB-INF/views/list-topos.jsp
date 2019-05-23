<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  Topo: elodie
  Date: 17/04/2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Topos</title>
    <c:import url="inc/headContent.jsp"/>
</head>

<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <h1 class="mb-5 d-inline-block col-md-8">Liste des Topos</h1>

        <input type="button" value="Ajouter Topo"
               onclick="window.location.href='ajout-topo'; return false;"
               class="btn btn-primary" />

        <div class="panel panel-info mt-5">
            <div class="panel-body">
                <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
                <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

                <table class="table table-striped table-bordered">
                    <thead class="thead-light"><tr>
                        <th>Titre</th>
                        <th>Ville</th>
                        <th>Région</th>
                        <th>Pays</th>
                        <th>Date de parution</th>
                        <th>Ajouté par</th>
                        <th>Disponible</th>
                    </tr>
                    </thead>
                    <c:choose>
                        <c:when test="${empty topos}">
                            <tr><td colspan="6">Aucun topo n'a encore été ajouté</td></tr>
                        </c:when>
                        <c:otherwise>
                            <!-- loop over and print our topos -->
                            <c:forEach var="topo" items="${topos}">
                                <!-- construct an "view" link with topo id -->
                                <c:url var="viewLink" value="/topos/${topo.id}" />

                                <tr>
                                    <!-- display the view link -->
                                    <td><a href="${viewLink}">${topo.name}</a></td>
                                    <td>${topo.city}</td>
                                    <td>${topo.county}</td>
                                    <td>${topo.country}</td>
                                    <td>${topo.dateRelease}</td>
                                     <td>${topo.user.username}</td>
                                     <td> <c:choose>
                                         <c:when test="${topo.available}">Oui</c:when>
                                         <c:otherwise>Non</c:otherwise>
                                         </c:choose>
                                     </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
