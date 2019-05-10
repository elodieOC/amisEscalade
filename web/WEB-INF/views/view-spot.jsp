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

<body class="d-flex flex-column h-100">
<div class="container h-100 align-items-center">
    <c:import url="inc/choose-navbar.jsp" />
    <div class="wrapper flex-shrink-0">
        <div class="offset-1 col-md-10">
            <h2 class="d-inline-block col-md-8">${spot.name}, ${spot.county}, ${spot.city}
                <span class="text-muted ml-5 small">(Ajouté par: <c:choose>
                    <c:when test="${empty spot.user.username}">
                        utilisateur supprimé
                    </c:when>
                    <c:otherwise>
                        ${spot.user.username}
                    </c:otherwise>
                </c:choose>)</span></h2>
            <input type="button" value="Ajouter un Secteur"
                   onclick="window.location.href='${spot.id}/ajoutSecteur'; return false;"
                   class="btn btn-primary" />
            <input type="button" value="Ajouter une Voie"
                   onclick="window.location.href='${spot.id}/${sector.id}/ajoutVoie'; return false;"
                   class="btn btn-primary" />

            <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
            <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

            <c:if test="${userRole eq '1' || userId eq spot.user.id}">
            <div class="d-inline-block col-md-8 mt-3">
                <p class="text-muted small">Vous êtes administrateur ou vous avez ajouté ce spot. Vous pouvez l'éditer ou le supprimer.</p>
            </div>
            <!-- construct an "update" link with spot id -->
            <c:url var="updateLink" value="/spots/${spot.id}/updateFormSpot" />
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

            <hr />
            <br/><br/>
            <div class="panel panel-info">
                <div class="panel-body mt-2 mb-5">
                    <h3 class="mb-5">Les Secteurs</h3>
                    
                    <c:choose>
                        <c:when test="${empty sectors}">
                            <p>Il n'y a pas encore de secteur ajouté pour ce spot</p>
                        </c:when>
                        <c:otherwise>
                            <ul class="list-unstyled">
                            <c:forEach var="sector" items="${sectors}">

                                <!-- construct an "view" link with sector id -->
                                <c:url var="viewLink" value="/spots/${spot.id}/${sector.id}" />

                                <li><a href="${viewLink}">${sector.name}</a>
                                    <span class="ml-5 text-muted small">(Ajouté par: ${sector.user.username})</span>
                                </li>
                            </c:forEach>

                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <h2 class="mt-5 d-inline-block col-md-8">Commentaires</h2>
            <input type="button" value="Ajouter Commentaire"
                   onclick="window.location.href='${spot.id}/commenter'; return false;"
                   class="btn btn-primary" />
            <hr />
            <br/>
            <c:choose>
                <c:when test="${ empty comments }" >
                    <p>Il n'y a pas encore de commentaire sur ce spot</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="comment" items="${comments}">
                        <div class="row">
                            <div class="offset-1 col-md-6 col-lg-10">
                                <div class="panel panel-default comment">
                                    <div class="panel-heading py-2 pb-3">
                                        <strong>
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
                                        <c:if test="${userRole eq '1' ||userRole eq '2' || userId eq spot.user.id}">
                                                <span class="comment-btn"><input type="button" value="Editer"
                                                                                 onclick="window.location.href='${spot.id}/${comment.id}/updateFormComment'; return false;"
                                                                                 class="btn btn-primary" />
                                                <input type="button" value="Supprimer"
                                                       onclick="window.location.href='deleteComment?spotId=${spot.id}&commentId=${comment.id}'; return false;"
                                                       class="btn btn-primary" />
                                                </span>
                                        </c:if>
                                    </div>
                                    <div class="panel-body py-2">
                                        <p style="white-space: pre-line"> <c:out value="${comment.content}"/></p>
                                    </div><!-- /panel-body -->
                                </div><!-- /panel panel-default -->
                            </div><!-- /col-md-8 col-lg-12 -->
                        </div><!-- /row comment-->
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</div>
</body>
<c:import url="inc/footer.jsp"/>
</html>
