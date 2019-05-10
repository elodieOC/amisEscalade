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
<c:import url="inc/choose-navbar.jsp" />
<div class="wrapper flex-shrink-0">
    <div class="container d-flex h-100 align-items-center">
        <div class="col-md-offset-1 col-md-10">
            <h2 class="mb-5">Spot: ${spot.name}</h2>
            <hr />
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
                            <c:if test="${userRole eq '1' || userId eq spot.user.id}">
                                <th>Action</th>
                            </c:if>
                        </tr>

                        <!-- construct an "update" link with spot id -->
                        <c:url var="updateLink" value="/spots/${spot.id}/updateFormSpot" />
                        <!-- construct an "delete" link with spot id -->
                        <c:url var="deleteLink" value="/spots/${spot.id}/delete" />

                        <tr>
                            <td>${spot.name}</td>
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
                                    <!-- display the update link -->
                                    <a href="${updateLink}">Editer</a>
                                    |
                                    <!-- display the delete link -->
                                    <a href="${deleteLink}"
                                       onclick="if (!(confirm('Are you sure you want to delete this spot?'))) return false">Supprimer</a>
                                </td>
                            </c:if>
                        </tr>

                    </table>
                </div>
            </div>
            <div class="container comment">
                <h2 class="mt-5 d-inline-block">Commentaires</h2>
                <input id="add-comment" type="button" value="Ajouter Commentaire"
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
                                <div class="col-md-8 col-lg-12">
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
