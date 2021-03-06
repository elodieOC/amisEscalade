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
<main role="main" class="flex-shrink-0 mt-5  col-md-12">
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

        <h1 class="d-inline-block col-md-8">Voie ${route.name}
            <span class="text-muted ml-1 small">(Ajouté par:
                <c:choose>
                    <c:when test="${empty route.user.username}">
                        utilisateur supprimé
                    </c:when>
                    <c:otherwise>
                        ${route.user.username}
                    </c:otherwise>
                </c:choose>)
            </span>
        </h1>
        <input type="button" value="Ajouter une Longueur"
               onclick="window.location.href='${route.id}/ajout-longueur'; return false;"
               class="btn btn-lg btn-primary ml-4 ml-sm-0 mb-5 mb-sm-0" />
        <br />
        <h3 class="col-md-8">Spot <a href="<c:url value="/spots/${spot.id}" />">${spot.name}</a>,
            Secteur <a href="<c:url value="/spots/${spot.id}/sector/${sector.id}" />">${sector.name}</a>,
            <span>${spot.county}, ${spot.city}</span></h3>
        <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
        <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

        <c:if test="${userRole eq '1' || userRole eq '2' || userId eq route.user.id && userId ne null}">
            <div class="d-inline-block col-md-8 mt-3">
                <p class="text-muted small">Vous êtes administrateur ou vous avez ajouté cette voie. Vous pouvez l'éditer ou la supprimer.</p>
            </div>
            <!-- construct an "update" link with spot id -->
            <c:url var="updateLink" value="/spots/${spot.id}/sector/${sector.id}/route/${route.id}/editer" />
            <!-- construct an "delete" link with spot id -->
            <c:url var="deleteLink" value="/spots/${spot.id}/sector/${sector.id}/route/${route.id}/delete" />
            <!-- display the update link -->
            <input type="button" value="Editer"
                   onclick="window.location.href='${updateLink}'; return false;"
                   class="btn btn-secondary ml-4 ml-sm-0 mb-5 mb-sm-0" />
            <!-- display the delete link -->
            <input type="button" value="Supprimer"
                   onclick="window.location.href='${deleteLink}'; return false;"
                   class="btn btn-secondary ml-4 ml-sm-0 mb-5 mb-sm-0" />
        </c:if>

        <c:if test="${!empty route.image}" >
            <div class="mt-5 col-md-8">
                <div class="img offset-md-1">
                    <img src='data:image/jpg;base64,${route.base64}' class="img-fluid d-block"/>
                </div>
            </div>
        </c:if>

        <div class="mt-5">
            <div class="mb-5">
                <h2 class="d-inline-block col-md-8 mb-5">Informations</h2>
                <c:choose>
                    <c:when test="${empty route.lengths}">
                        <p class="col-md-8">Il n'y a pas encore de secteur ajouté pour ce spot
                    </c:when>
                    <c:otherwise>
                        <ul class="list-unstyled">
                            <c:forEach var="length" items="${route.lengths}">
                                    <li class="ml-4 mb-4"><strong>Longueur ${route.lengths.indexOf(length)+1}:  </strong>
                                        <span class="route-li">cotation ${length.grade.name} (${length.grade.rating.name}),
                                                <c:choose>
                                                    <c:when test="${length.bolts ne null}">${length.bolts} spits,</c:when>
                                                    <c:otherwise>nombre de spits inconnu,</c:otherwise>
                                                </c:choose>
                                            <c:choose>
                                                <c:when test="${length.height ne null}">${length.height}m</c:when>
                                                <c:otherwise>hauteur inconnue</c:otherwise>
                                            </c:choose></span>

                                    <c:if test="${userRole eq '1' ||userRole eq '2' || userId eq length.user.id && userId ne null}">
                                            <!-- construct an "update" link with spot id -->
                                            <c:url var="updateLink" value="/spots/${spot.id}/sector/${sector.id}/route/${route.id}/length/${length.id}/editer" />
                                            <!-- construct an "delete" link with spot id -->
                                            <c:url var="deleteLink" value="/spots/${spot.id}/sector/${sector.id}/route/${route.id}/length/${length.id}/delete" />
                                            <!-- display the update link -->
                                            <input type="button" value="Editer"
                                                   onclick="window.location.href='${updateLink}'; return false;"
                                                   class="btn btn-secondary ml-sm-5 mb-5 mb-sm-0" />
                                            <!-- display the delete link -->
                                            <input type="button" value="Supprimer"
                                                   onclick="window.location.href='${deleteLink}'; return false;"
                                                   class="btn btn-secondary ml-4 ml-sm-0 mb-5 mb-sm-0" />
                                        </li>
                                    </c:if>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</main>
<c:import url="inc/footer.jsp"/>
</body>
</html>
