<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  Topo: elodie
  Date: 17/04/2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Topos</title>
    <c:import url="inc/headContent.jsp"/>
</head>

<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="panel panel-info mt-5">
            <div class="panel-body">
                <div class="panel-heading">
                    <h1 class="panel-title mr-5 mb-5 d-inline-block ">Liste des Topos</h1>
                    <input type="button" value="Ajouter Topo"
                           onclick="window.location.href='ajout-topo'; return false;"
                           class="btn btn-primary" />

                    <div class="panel panel-info mt-5">
                        <div class="panel-body">
                            <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
                            <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

                            <div class="card-deck mb-5 mx-auto">
                                <!-- loop over and print our spots -->
                                <c:forEach var="topo" items="${topos}">
                                    <!-- construct an "view" link with spot id -->
                                    <c:url var="viewLink" value="/topos/${topo.id}" />
                                    <div class="card mb-5 d-inline-block">
                                        <a href="${viewLink}" class="text-decoration-none">
                                            <h4 class="card-header">${topo.name}, ${topo.county}, ${topo.city}</h4>
                                        </a>
                                        <div class="card-body">

                                            <h5 class="card-subtitle mb-3 text-muted">Disponible:
                                                <c:choose>
                                                    <c:when test="${topo.available}">Oui,</c:when>
                                                    <c:otherwise>Non,</c:otherwise>
                                                </c:choose>Date de parution: ${topo.dateRelease}</h5>
                                            <a href="${viewLink}" class="text-decoration-none">
                                                <div class="thumbnail">
                                                    <c:choose>
                                                        <c:when test="${empty topo.image}">
                                                            <img src="<c:url value="/resources/img/noimage-thumbnail.png" />" class="card-img-top img-thumbnail">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src='data:image/jpg;base64,${topo.base64}' class="card-img-top img-thumbnail">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </a>
                                            <div class="card-text mt-5">
                                                    ${topo.description}
                                            </div>
                                        </div>
                                        <div class="card-footer text-muted small">
                                            Ajouté par:
                                            <c:choose>
                                                <c:when test="${empty topo.user.username}">
                                                    utilisateur supprimé
                                                </c:when>
                                                <c:otherwise>
                                                    ${topo.user.username}
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div> <!--End of card-deck -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
