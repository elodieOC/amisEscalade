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
    <title>Spot ${spot.name}</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5 col-md-12">
    <div class="container col-md-10 mt-5 offset-2">
        <h1 class="d-inline-block col-md-8">${spot.name}, ${spot.county}, ${spot.city}
            <span class="text-muted ml-3 small">(Ajouté par:
                <c:choose>
                     <c:when test="${empty spot.user.username}">
                         utilisateur supprimé
                    </c:when>
                    <c:otherwise>
                        ${spot.user.username}
                    </c:otherwise>
                </c:choose>)
            </span>
        </h1>
        <input type="button" value="Ajouter un Secteur"
               onclick="window.location.href='${spot.id}/ajout-secteur'; return false;"
               class="btn btn-primary" />
        <c:if test="${spot.tagged}">
            <div class="col-md-8"><h3 class="official-tag">"Officiel Les amis de l’escalade" </h3></div>
        </c:if>

        <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
        <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

        <c:if test="${userRole eq '1' || userId eq spot.user.id && userId ne null}">
            <div class="d-inline-block col-md-8 mt-3">
                <p class="text-muted small">Vous êtes administrateur ou vous avez ajouté ce spot. Vous pouvez l'éditer ou le supprimer.</p>
            </div>
            <!-- construct an "update" link with spot id -->
            <c:url var="updateLink" value="/spots/${spot.id}/editer" />
            <!-- construct an "delete" link with spot id -->
            <c:url var="deleteLink" value="/spots/${spot.id}/delete" />
            <!-- display the update link -->
            <input type="button" value="Editer"
                   onclick="window.location.href='${updateLink}'; return false;"
                   class="btn btn-secondary" />
            <!-- display the delete link -->
            <input type="button" value="Supprimer"
                   onclick="window.location.href='${deleteLink}'; return false;"
                   class="btn btn-secondary" />
        </c:if>

        <c:if test="${!empty spot.image}" >
        <div class="panel panel-info mt-5 col-md-8">
            <div class="img offset-1">
                <img src='data:image/jpg;base64,${spot.base64}' class="img-fluid d-block"/>
            </div>
        </div>
        </c:if>

        <div class="panel panel-info mt-5">
            <h2 class="d-inline-block col-md-8 mb-5">Les Secteurs</h2>
            <div class="panel-body mb-5">
                <c:choose>
                <c:when test="${empty sectors}">
                <p class="col-md-8">Il n'y a pas encore de secteur ajouté pour ce spot
                    </c:when>
                    <c:otherwise>
                <table class="mb-5">
                    <c:forEach var="sector" items="${sectors}">
                        <!-- construct an "view" link with spot id -->
                        <c:url var="viewLink" value="/spots/${spot.id}/sector/${sector.id}" />
                        <tr>
                            <td class="col-lg-2 mx-auto"><a href="${viewLink}">${sector.name}</a></td><td class="col-lg-7"><span class="ml-5 text-muted small">(Ajouté par: ${sector.user.username})</span></td>
                        </tr>
                    </c:forEach>
                    </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </div>
        <div class="panel panel-info mt-5">
            <h2 class="mt-5 mb-5 d-inline-block col-md-8">Commentaires</h2>
            <input type="button" value="Ajouter Commentaire"
                   onclick="window.location.href='${spot.id}/commenter'; return false;"
                   class="btn btn-primary" />

            <div class="panel-body mb-5">
                <c:choose>
                    <c:when test="${ empty comments }" >
                        <p class="col-md-8">Il n'y a pas encore de commentaire sur ce spot</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="comment" items="${comments}">
                            <table class="table mb-5 mt-5 ml-3 col-md-6 col-lg-10 comment">
                                <thead class="thead-dark">
                                <th class="text-white"><strong>
                                    <c:choose>
                                        <c:when test="${empty spot.user.username}">
                                            utilisateur supprimé
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="${comment.user.username}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </strong>
                                    <span class="text-muted ml-2">posté le <c:out value="${comment.date}"/></span>
                                    <c:if test="${userRole eq '1' ||userRole eq '2' || userId eq spot.user.id && userId ne null}">
                                                <span class="comment-btn"><input type="button" value="Editer"
                                                                                 onclick="window.location.href='${spot.id}/comment/${comment.id}/editer'; return false;"
                                                                                 class="btn btn-primary" />
                                                <input type="button" value="Supprimer"
                                                       onclick="window.location.href='${spot.id}/comment/${comment.id}/delete'; return false;"
                                                       class="btn btn-primary" />
                                                </span>
                                    </c:if></th>
                                </thead>
                                <tr><td class="comment-content"> <c:out value="${comment.content}"/></td></tr>
                            </table>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</main>
<c:import url="inc/footer.jsp"/>
</body>
</html>
