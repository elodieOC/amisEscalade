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
    <title>Liste des Spots</title>
    <c:import url="inc/headContent.jsp"/>
    <script src="<c:url value="/resources/js/search-toggle.js" />"></script>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="mt-5">
            <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
            <c:set var="userId" value="${sessionScope['loggedInUserId']}" />
            <h1 class="d-inline-block mr-5">Liste des Spots</h1>
            <input type="button" value="Ajouter Spot"
                   onclick="window.location.href='ajout-spot'; return false;"
                   class="btn btn-primary" />


            <div class="mt-5 alert alert-info" role="alert">
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

            <div class="mt-5 mb-5 col-12 card shadow bg-light">
                <div class="card-header" id="search-header">
                    <button class="navbar-toggler " type="button" aria-expanded="false" aria-label="Toggle navigation">
                        <i class="fas fa-bars"></i>
                    </button>
                    <strong>Recherche</strong>
                </div>
                <div class="card-body">
                    <ul class="list-unstyled" id="search-list">
                        <form:form action="recherche"  method="post" modelAttribute="searchForm">
                            <li>
                                <div class="form-group input-group">
                                    <form:input id="searchName" path="name" cssClass="form-control p-2" placeholder="Par nom" type="text" />
                                </div>
                            </li>
                            <li>
                                <div class="form-group input-group">
                                    <form:input path="county" cssClass="form-control p-2" placeholder="Par région" type="text" />
                                </div>
                            </li>
                            <li>
                                <div class="form-group input-group">
                                    <form:input path="city" cssClass="form-control p-2" placeholder="Par ville" type="text" />
                                </div>
                            </li>
                            <li>
                                <div class="form-group input-group">
                                    <form:input path="nbrSector" cssClass="form-control p-2" placeholder="Par nbr de secteurs"  type="text" />
                                </div>
                            </li>
                            <li>
                                <div class="form-group input-group">
                                    <form:input path="username" cssClass="form-control p-2" placeholder="Par utilisateur"  type="text" />
                                </div>
                            </li>
                            <li>
                                <div class="form-group input-group">
                                    <form:select  path="gradeMin" cssClass="form-control" >
                                        <form:option value="" selected="">Par cotation min</form:option>
                                        <c:forEach var="cot" items="${grades}">
                                            <form:option value="${cot.id}">${cot.name}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div> <!-- form-group end.// -->

                            </li>
                            <li>
                                <div class="form-group input-group">
                                    <form:select  path="gradeMax" cssClass="form-control" >
                                        <form:option value="" selected="">Par cotation max</form:option>
                                        <c:forEach var="cot" items="${grades}">
                                            <form:option value="${cot.id}">${cot.name}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div> <!-- form-group end.// -->

                            </li>
                            <li>
                                <div class="form-group input-group">
                                    <button class="search-btn" type="submit"><i class="fa fa-search"></i></button>
                                </div>
                            </li>

                        </form:form>
                    </ul>
                </div>
            </div>

            <div class="card-deck mb-5 mx-auto">
                <!-- loop over and print our spots -->
                <c:forEach var="spot" items="${spots}">
                    <!-- construct an "view" link with spot id -->
                    <c:url var="viewLink" value="/spots/${spot.id}" />
                    <div class="card shadow mb-5 d-inline-block bg-light">
                        <a href="${viewLink}" class="text-decoration-none">
                            <h4 class="card-header">${spot.name}</h4>
                        </a>
                        <div class="card-body">

                            <h5 class="card-subtitle mb-3 text-muted">${spot.county}, ${spot.city}</h5>
                            <a href="${viewLink}" class="text-decoration-none">
                                <div class="thumbnail">
                                    <c:choose>
                                        <c:when test="${empty spot.image}">
                                            <img src="<c:url value="/resources/img/noimage-thumbnail.png" />" class="card-img-top img-thumbnail">
                                        </c:when>
                                        <c:otherwise>
                                            <img src='data:image/jpg;base64,${spot.base64}' class="card-img-top img-thumbnail">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </a>
                            <div class="card-text mt-5">
                                <ul class="list-unstyled">
                                    <li>Secteurs: ${spot.sectors.size()}</li>
                                    <li>Cot min: ${spot.gradeMin}</li>
                                    <li>Cot max: ${spot.gradeMax}</li>
                                </ul>
                            </div>
                        </div>
                        <div class="card-footer text-muted small">
                            Ajouté par:
                            <c:choose>
                                <c:when test="${empty spot.user.username}">
                                    utilisateur supprimé
                                </c:when>
                                <c:otherwise>
                                    ${spot.user.username}
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </div> <!--End of card-deck -->
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
