<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  Topo: elodie
  Date: 17/04/2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Topo ${topo.name}</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5 col-md-12">
    <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
    <c:set var="userId" value="${sessionScope['loggedInUserId']}" />
    <div class="container col-md-10 mt-5 offset-md-2">
        <h1 class="d-inline-block col-md-8">${topo.name}
            <span class="text-muted ml-3 small">(Ajouté par:  ${topo.user.username}, Disponible:
                <c:choose>
                    <c:when test="${topo.available}">Oui</c:when>
                    <c:otherwise>Non</c:otherwise>
                </c:choose>)</span></h1>
                <c:choose>
                     <c:when test="${topo.available}">
                         <input type="button" value="Faire une demande de Réservation"
                         onclick="window.location.href='${topo.id}/book'; return false;"
                         class="btn btn-primary ml-4 ml-sm-0 mb-5 mb-sm-0" />
                     </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${userId ne null && userId eq topo.user.id}">
                                <input type="button" value="Rendre le Topo disponible à nouveau"
                               onclick="window.location.href='${topo.id}/make-available'; return false;"
                               class="btn btn-success ml-4 ml-sm-0 mb-5 mb-sm-0" />
                            </c:when>
                        <c:otherwise>
                            <input type="button" value="Topo déjà réservé" class="btn btn-success"/>
                        </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>

        <c:if test="${userRole eq '1' ||userRole eq '2' || userId eq topo.user.id && userId ne null}">
            <div class="d-inline-block col-md-8 mt-3">
                <p class="text-muted small">Vous êtes administrateur ou vous avez ajouté ce topo. Vous pouvez l'éditer ou le supprimer.</p>
            </div>
            <!-- construct an "update" link with topo id -->
            <c:url var="updateLink" value="/topos/${topo.id}/editer" />
            <!-- construct an "delete" link with topo id -->
            <c:url var="deleteLink" value="/topos/${topo.id}/delete" />
            <!-- display the update link -->
            <input type="button" value="Editer"
                   onclick="window.location.href='${updateLink}'; return false;"
                   class="btn btn-secondary ml-4 ml-sm-0 mb-5 mb-sm-0" />
            <!-- display the delete link -->
            <input type="button" value="Supprimer"
                   onclick="window.location.href='${deleteLink}'; return false;"
                   class="btn btn-secondary ml-4 ml-sm-0 mb-5 mb-sm-0" />
        </c:if>

        <div class="mt-5 col-md-8">
            <div class="mb-5">
                <h3 class="mb-5"><strong>Informations</strong></h3>
                <ul class="list-unstyled mt-5 text-left">
                    <li class="mb-4"><div class="d-inline-block col-3 mr-5"><strong>Pays:</strong></div>${topo.country}</li>
                    <li class="mb-4"><div class="d-inline-block col-3 mr-5"><strong>Région:</strong></div>${topo.county}</li>
                    <li class="mb-4"><div class="d-inline-block col-3 mr-5"><strong>Ville: </strong></div>${topo.city}</li>
                    <li class="mb-4"><div class="d-inline-block col-3 mr-5"><strong>Date de Parution:</strong></div>${topo.dateRelease}</li>
                    <li class="mb-4" style="white-space: pre-line;"><div class="d-inline-block col-3 mr-4 align-top"><strong>Description: </strong></div><div class="d-inline-block col-md-6"> ${topo.description}</div></li>
                </ul>
            </div>
        </div>

        <c:if test="${!empty topo.image}" >
            <div class="mt-5 mb-5 col-md-8">
                <div class="img offset-md-1">
                    <img src='data:image/jpg;base64,${topo.base64}' class="img-fluid d-block"/>
                </div>
            </div>
        </c:if>
    </div>
</main>
<c:import url="inc/footer.jsp"/>
</body>
</html>
