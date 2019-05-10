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

<body class="d-flex flex-column h-100">
<c:import url="inc/choose-navbar.jsp" />
<div class="wrapper flex-shrink-0">
<div class="container d-flex h-100 align-items-center">
    <div class="col-md-offset-1 col-md-10">
        <h2 class="mb-5">Liste des Spots</h2>
        <hr />

        <input type="button" value="Ajouter Spot"
               onclick="window.location.href='ajoutSpot'; return false;"
               class="btn btn-primary" />
        <br/><br/>
        <div class="panel panel-info">
            <div class="panel-body">
                <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
                <c:set var="userId" value="${sessionScope['loggedInUserId']}" />
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>Nom</th>
                        <th>Région</th>
                        <th>Ville</th>
                        <th>Ajouté par</th>
                        <c:if test="${userRole eq '1' }">
                            <th>Action</th>
                        </c:if>
                    </tr>

                    <!-- loop over and print our spots -->
                    <c:forEach var="spot" items="${spots}">
                        <!-- construct an "view" link with spot id -->
                        <c:url var="viewLink" value="/spots/${spot.id}" />
                        <!-- construct an "update" link with spot id -->
                        <c:url var="updateLink" value="/spots/${spot.id}/updateFormSpot" />
                        <!-- construct an "delete" link with spot id -->
                        <c:url var="deleteLink" value="/spots/${spot.id}/delete" />

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
                            <c:if test="${userRole eq '1' || userId eq spot.user.id}">
                                <td>
                                    <!-- display the update link --> <a href="${updateLink}">Editer</a>
                                    | <a href="${deleteLink}"
                                         onclick="if (!(confirm('Are you sure you want to delete this spot?'))) return false">Supprimer</a>
                                </td>
                            </c:if>

                        </tr>

                    </c:forEach>

                </table>

            </div>
        </div>
    </div>

</div>
</div>
</body>

<c:import url="inc/footer.jsp"/>
</html>
