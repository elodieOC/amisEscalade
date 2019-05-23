<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  Spot: elodie
  Date: 17/04/2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Spots</title>
    <c:import url="inc/headContent.jsp"/>
</head>

<c:import url="inc/choose-navbar.jsp" />
    <main role="main" class="flex-shrink-0 mt-5">
        <div class="container col-md-10 mt-5">
            <h1 class="mb-5 d-inline-block col-md-8">Liste des Spots</h1>

            <input type="button" value="Ajouter Spot"
                   onclick="window.location.href='ajout-spot'; return false;"
                   class="btn btn-primary" />

            <div class="panel panel-info mt-5">
                <div class="panel-body">
                    <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
                    <c:set var="userId" value="${sessionScope['loggedInUserId']}" />
                    <table class="table table-striped table-bordered">
                        <thead class="thead-light"><tr>
                            <th>Nom</th>
                            <th>Région</th>
                            <th>Ville</th>
                            <th>Ajouté par</th>
                        </tr>
                        </thead>
                        <!-- loop over and print our spots -->
                        <c:forEach var="spot" items="${spots}">
                            <!-- construct an "view" link with spot id -->
                            <c:url var="viewLink" value="/spots/${spot.id}" />

                            <tr>
                                <!-- display the view link -->
                                <td><a href="${viewLink}">${spot.name}</a></td>
                                <td>${spot.county}</td>
                                <td>${spot.city}</td>
                                <c:choose>
                                    <c:when test="${empty spot.user.username}">
                                        <td>utilisateur supprimé</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${spot.user.username}</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>

                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
