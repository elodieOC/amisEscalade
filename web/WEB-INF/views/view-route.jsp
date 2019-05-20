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
    <title>Spot ${spot.name} - Secteur ${sector.name} - Voie ${route.name}</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <h1 class="d-inline-block col-md-8">Voie ${route.name}:
            <span class="text-muted ml-5 small">(Ajouté par: <c:choose>
                <c:when test="${empty route.user.username}">
                    utilisateur supprimé
                </c:when>
                <c:otherwise>
                    ${route.user.username}
                </c:otherwise>
            </c:choose>)</span></h1>
        <input type="button" value="Ajouter une Longueur"
               onclick="window.location.href='${route.id}/ajoutLongueur'; return false;"
               class="btn btn-primary" />
        <br />
        <h4 class="col-md-8">Spot <a href="<c:url value="/spots/spot/${spot.id}" />">${spot.name}</a>,
            Secteur <a href="<c:url value="/spots/spot/${spot.id}/sector/${sector.id}" />">${sector.name}</a>,
            ${spot.county}, ${spot.city}</h4>
        <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
        <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

        <c:if test="${userRole eq '1' || userId eq route.user.id && userId ne null}">
            <div class="d-inline-block col-md-8 mt-3">
                <p class="text-muted small">Vous êtes administrateur ou vous avez ajouté cette voie. Vous pouvez l'éditer ou la supprimer.</p>
            </div>
            <!-- construct an "update" link with spot id -->
            <c:url var="updateLink" value="/spots/spot/${spot.id}/sector/${sector.id}/route/${route.id}/updateFormRoute" />
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
        </c:if>

        <div class="panel panel-info mt-5">
            <h2 class="d-inline-block col-md-8 mb-5">Informations</h2>
            <div class="panel-body mb-5">
                <c:choose>
                    <c:when test="${empty route.lengths}">
                        <p class="col-md-8">Il n'y a pas encore de secteur ajouté pour ce spot
                    </c:when>
                    <c:otherwise>
                        <table class="mb-5">
                            <c:forEach var="length" items="${route.lengths}">
                                <tr>
                                    <td class="col-md-10 mx-auto"><strong>Longueur ${route.lengths.indexOf(length)+1}:  </strong>
                                        cotation ${length.grade.name} (${length.grade.rating.name}), ${length.bolts} spits, hauteur ${length.height}m
                                    </td>
                                    <c:if test="${userRole eq '1' || userId eq spot.user.id && userId ne null}">
                                        <td class="col-lg-2 mx-auto">
                                            <!-- construct an "update" link with spot id -->
                                            <c:url var="updateLink" value="/spots/spot/${spot.id}/sector/${sector.id}/route/${route.id}/length/${length.id}/updateFormLength" />
                                            <!-- construct an "delete" link with spot id -->
                                            <c:url var="deleteLink" value="/spots/spot/${spot.id}/sector/${sector.id}/route/${route.id}/length/${length.id}/deleteLength" />
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
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</main>
<c:import url="inc/footer.jsp"/>
</body>
</html>
