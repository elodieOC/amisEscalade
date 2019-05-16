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
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <h1 class="d-inline-block col-md-8">${sector.name}:
            <span class="small">${spot.name}, ${spot.county}, ${spot.city}</span>
            <span class="text-muted ml-5 small">(Ajouté par: <c:choose>
                <c:when test="${empty sector.user.username}">
                    utilisateur supprimé
                </c:when>
                <c:otherwise>
                    ${sector.user.username}
                </c:otherwise>
            </c:choose>)</span></h1>
        <input type="button" value="Ajouter une Voie"
               onclick="window.location.href='${sector.id}/ajoutVoie'; return false;"
               class="btn btn-primary" />

        <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
        <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

        <c:if test="${userRole eq '1' || userId eq spot.user.id}">
            <div class="d-inline-block col-md-8 mt-3">
                <p class="text-muted small">Vous êtes administrateur ou vous avez ajouté ce secteur. Vous pouvez l'éditer ou le supprimer.</p>
            </div>
            <!-- construct an "update" link with spot id -->
            <c:url var="updateLink" value="/spots/spot/${spot.id}/sector/${sector.id}/updateFormSector" />
            <!-- construct an "delete" link with spot id -->
            <c:url var="deleteLink" value="/spots/spot/${spot.id}/sector/${sector.id}/deleteSector" />
            <!-- display the update link -->
            <input type="button" value="Editer"
                   onclick="window.location.href='${updateLink}'; return false;"
                   class="btn btn-secondary" />
            <!-- display the delete link -->
            <input type="button" value="Supprimer"
                   onclick="window.location.href='${deleteLink}'; return false;"
                   class="btn btn-secondary" />
        </c:if>

        <div class="panel panel-info mt-5">
            <div class="panel-body mb-5">
                <table class="mb-5 table table-borderless">
                    <thead>
                    <th>Informations</th>
                    </thead>
                    <tr>
                        <td class="col-lg-2 mx-auto">Situation:</td><td class="col-lg-7" style="white-space: pre-line;">${sector.location}</td>
                    </tr>
                    <tr>
                        <td class="col-lg-2 mx-auto">Accès:</td><td class="col-lg-7" style="white-space: pre-line;">${sector.access}</td>
                    </tr>
                </table>
                <c:if test="${!empty sector.routes}">
                    <table class="table table-borderless mb-5">
                        <thead>
                        <th>Voies</th>
                        </thead>
                        <c:forEach var="route" items="${sector.routes}">
                            <!-- construct an "view" link with spot id -->
                            <c:url var="viewLink" value="/spots/spot/${spot.id}/sector/${sector.id}/route/${route.id}" />
                            <tr>
                                <td class="col-lg-2 mx-auto"><a href="${viewLink}"><strong>${sector.routes.indexOf(route)+1}:  </strong>  ${route.name}</a></td><td class="col-lg-2"> <input type="button" value="Ajouter une Longueur" class="btn btn-primary" /></td>
                                <c:if test="${userRole eq '1' || userId eq spot.user.id}">
                                    <td class="col-lg-2 mx-auto">
                                        <!-- construct an "update" link with spot id -->
                                        <c:url var="updateLink" value="/spots/spot/${spot.id}/sector/${sector.id}/route/${route.id}/updateRoute" />
                                        <!-- construct an "delete" link with spot id -->
                                        <c:url var="deleteLink" value="/spots/spot/${spot.id}/sector/${sector.id}/route/${route.id}/deleteRoute" />
                                        <!-- display the update link -->
                                        <input type="button" value="Editer"
                                               onclick="window.location.href='${updateLink}'; return false;"
                                               class="btn btn-secondary" />
                                        <!-- display the delete link -->
                                        <input type="button" value="Supprimer"
                                               onclick="window.location.href='${deleteLink}'; return false;"
                                               class="btn btn-secondary" />
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
</main>
</div>
<c:import url="inc/footer.jsp"/>
</body>
</html>
