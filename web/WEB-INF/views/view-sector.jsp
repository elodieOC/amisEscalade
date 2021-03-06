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
<main role="main" class="flex-shrink-0 mt-5 col-md-12">
    <div class="container col-md-10 mt-5 offset-md-2">
        <div class="mt-5 mb-5 alert alert-info col-md-10 " role="alert">
            <div class="rating-list-header">
                <button class="navbar-toggler " type="button" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="glyphicon glyphicon-question-sign"></i>
                </button>
                <strong>Les niveaux et cotations</strong>
            </div>
            <ul class="list-unstyled ratinglist">
                <c:forEach var="rating" items="${ratings}" >
                    <li><strong>Niveau: </strong>${rating.name} (Cotations: ${rating.gradeList})</li>
                </c:forEach>
            </ul>
        </div>

        <h1 class="d-inline-block col-md-8">Secteur ${sector.name}
            <span class="text-muted ml-1 small">(Ajouté par:
                <c:choose>
                    <c:when test="${empty sector.user.username}">
                        utilisateur supprimé
                    </c:when>
                    <c:otherwise>
                        ${sector.user.username}
                    </c:otherwise>
                </c:choose>)
            </span>
        </h1>
        <input type="button" value="Ajouter une Voie"
               onclick="window.location.href='${sector.id}/ajout-voie'; return false;"
               class="btn btn-lg btn-primary ml-4 ml-sm-0 mb-5 mb-sm-0" />
        <br />
        <h3 class="col-md-8"><a href="<c:url value="/spots/${spot.id}" />">Spot ${spot.name}</a>, ${spot.county}, ${spot.city}</h3>
        <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
        <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

        <c:if test="${userRole eq '1' ||userRole eq '2' || userId eq sector.user.id && userId ne null}">
            <div class="d-inline-block col-md-8 mt-3">
                <p class="text-muted small">Vous êtes administrateur ou vous avez ajouté ce secteur. Vous pouvez l'éditer ou le supprimer.</p>
            </div>
            <!-- construct an "update" link with spot id -->
            <c:url var="updateLink" value="/spots/${spot.id}/sector/${sector.id}/editer" />
            <!-- construct an "delete" link with spot id -->
            <c:url var="deleteLink" value="/spots/${spot.id}/sector/${sector.id}/delete" />
            <!-- display the update link -->
            <input type="button" value="Editer"
                   onclick="window.location.href='${updateLink}'; return false;"
                   class="btn btn-secondary ml-4 ml-sm-0 mb-5 mb-sm-0" />
            <!-- display the delete link -->
            <input type="button" value="Supprimer"
                   onclick="window.location.href='${deleteLink}'; return false;"
                   class="btn btn-secondary ml-4 ml-sm-0 mb-5 mb-sm-0" />
        </c:if>


        <c:if test="${!empty sector.image}" >
            <div class="mt-5 col-md-8">
                <div class="img offset-md-1">
                    <img src='data:image/jpg;base64,${sector.base64}' class="img-fluid d-block"/>
                </div>
            </div>
        </c:if>

        <div class="mt-5 col-md-10">
            <div class="mb-5">
                <h3><strong>Informations</strong></h3>
                <ul class="list-unstyled mt-5">
                    <li><strong>Situation:</strong></li>
                    <li style="white-space: pre-line;">${sector.location}</li>

                    <li class="mt-5"><strong>Accès:</strong></li>
                    <li style="white-space: pre-line;">${sector.access}</li>
                </ul>
                <c:if test="${!empty sector.routes}">
                <h3 class="mt-5"><strong>Voies</strong></h3>
                <ul class="list-unstyled">
                        <c:forEach var="route" items="${routes}">
                            <!-- construct an "view" link with spot id -->
                            <c:url var="viewLink" value="/spots/${spot.id}/sector/${sector.id}/route/${route.id}" />
                                <li><a href="${viewLink}"><strong>${sector.routes.indexOf(route)+1}:  </strong>  ${route.name}</a>
                                    <c:if test="${!empty route.lengths}">
                                        <c:choose>
                                            <c:when test="${route.lengths.size() > 1}"><c:set var="lengthString" value="longueurs" /></c:when>
                                            <c:otherwise> <c:set var="lengthString" value="longueur" /></c:otherwise>
                                        </c:choose>
                                        <span class="text-muted sector-small">(${route.lengths.size()} ${lengthString}, cotation min: ${route.gradeMin}, cotation max: ${route.gradeMax})</span>
                                    </c:if>
                                </li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </div>
    </div>
</main>
</div>
<c:import url="inc/footer.jsp"/>
</body>
</html>
