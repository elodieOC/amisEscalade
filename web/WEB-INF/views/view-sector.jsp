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
                   onclick="window.location.href='${sector.id}/ajoutVoie'; return false;"
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
                <c:url var="deleteLink" value="/spots/${spot.id}/${sector.id}/deleteSector" />
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

            <div class="panel panel-info">
                <div class="panel-body mt-2 mb-5">
                    <h3 class="mb-5">Informations</h3>

                    <div class="row">
                        <h4 class="col-lg-1 mx-auto">Situation:</h4>
                        <p class="col-lg-10" style="white-space: pre-line;">${sector.location}</p>
                    </div>
                    <div class="row">
                        <h4 class="col-lg-1 mx-auto">Accès:</h4>
                        <p class="col-lg-10" style="white-space: pre-line">${sector.access}</p>
                    </div>

                    <div class="row">
                        <h4 class="col-lg-1 mx-auto">Voies</h4>
                        <c:choose>
                        <c:when test="${empty sector.routes}">
                            <p class="col-lg-10">Il n'y a pas encore de voie ajoutée</p>
                        </c:when>
                        <c:otherwise>
                        <p class="col-lg-10"> ${sector.routes.size()}
                            </c:otherwise>
                            </c:choose>
                    </div>

                    <c:choose>
                        <c:when test="${empty sector.routes}">
                            <div class="row ml-5">
                                <p class="col-lg-10"></p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="row">
                                <h4 class="mt-5 ml-5">Infos voies:</h4>
                            </div>
                            <hr class="small-hr ml-3"/>
                            <br/>
                            <c:forEach var="route" items="${sector.routes}">
                                <div class="row ml-5">
                                    <p><strong>${sector.routes.indexOf(route)+1}:  </strong> ${route.grade.rating.name}, ${route.name}, cotation ${route.grade.name}, hauteur ${route.height}, ${route.bolts} spits</>
                                    <br/>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<c:import url="inc/footer.jsp"/>
</html>
