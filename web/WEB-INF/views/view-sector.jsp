<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  Spot: elodie
  Date: 17/04/2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Spot ${spot.name} - Secteur ${sector.name}</title>
    <c:import url="inc/headContent.jsp"/>
</head>

<body class="d-flex flex-column h-100">
<div class="container h-100 align-items-center">
    <c:import url="inc/choose-navbar.jsp" />
    <div class="wrapper flex-shrink-0">
        <div class="offset-1 col-md-10">
            <h2 class="d-inline-block col-md-8">${sector.name}:
                <span class="small">${spot.name}, ${spot.county}, ${spot.city}</span>
                <span class="text-muted ml-5 small">(Ajouté par: <c:choose>
                    <c:when test="${empty sector.user.username}">
                        utilisateur supprimé
                    </c:when>
                    <c:otherwise>
                        ${sector.user.username}
                    </c:otherwise>
                </c:choose>)</span></h2>
            <input type="button" value="Ajouter une Voie"
                   onclick="window.location.href='${spot.id}/${sector.id}/ajoutVoie'; return false;"
                   class="btn btn-primary" />

            <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
            <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

            <c:if test="${userRole eq '1' || userId eq spot.user.id}">
                <div class="d-inline-block col-md-8 mt-3">
                    <p class="text-muted small">Vous êtes administrateur ou vous avez ajouté ce secteur. Vous pouvez l'éditer ou le supprimer.</p>
                </div>
                <!-- construct an "update" link with spot id -->
                <c:url var="updateLink" value="/spots/${spot.id}/${sector.id}/updateFormSector" />
                <!-- construct an "delete" link with spot id -->
                <c:url var="deleteLink" value="/spots/${spot.id}/${sector.id}/delete" />
                <!-- display the update link -->
                <input type="button" value="Editer"
                       onclick="window.location.href='${updateLink}'; return false;"
                       class="btn btn-secondary" />
                <!-- display the delete link -->
                <input type="button" value="Supprimer"
                       onclick="window.location.href='${deleteLink}'; return false;"
                       class="btn btn-secondary" />
            </c:if>

            <hr />
            <br/><br/>

<%--            <div class="panel panel-info">
                <div class="panel-body mt-2 mb-5">
                    <h3 class="mb-5">Les Voies</h3>

                    <c:choose>
                        <c:when test="${empty routes}">
                            <p>Il n'y a pas encore de voies ajouté pour ce spot</p>
                        </c:when>
                        <c:otherwise>
                            <ul class="list-unstyled">
                                <c:forEach var="route" items="${routes}">
                                    <li>${route.name}
                                        <span class="ml-5 text-muted small">(Ajouté par: ${route.user.username})</span>
                                    </li>
                                </c:forEach>

                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>--%>

            <div class="panel panel-info">
                <div class="panel-body mt-2 mb-5">
                    <h3 class="mb-5">Informations</h3>
                    <p style="white-space: pre-line">${sector.info}</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<c:import url="inc/footer.jsp"/>
</html>
