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
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">


        <div class="panel panel-info mt-5">
            <div class="panel-body">
                <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
                <c:set var="userId" value="${sessionScope['loggedInUserId']}" />
                <div class="panel-heading">
                    <h1 class="panel-title d-inline-block mr-5">Liste des Spots</h1>
                    <input type="button" value="Ajouter Spot"
                           onclick="window.location.href='ajout-spot'; return false;"
                           class="btn btn-primary" />

                    <table class="table mt-3">
                        <thead class="thead-light">
                        <tr>
                            <th colspan="6">Recherche</th>
                        </tr>
                        <tr>
                            <form:form action="recherche"  method="post" modelAttribute="searchForm">

                                <td>
                                    <div class="form-group input-group">
                                        <form:input id="searchName" path="name" cssClass="form-control p-2" placeholder="Chercher par nom" type="text" />
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group input-group">
                                        <form:input path="county" cssClass="form-control p-2" placeholder="Chercher par région" type="text" />
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group input-group">
                                        <form:input path="city" cssClass="form-control p-2" placeholder="Chercher par ville" type="text" />
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group input-group">
                                        <form:input path="nbrSector" cssClass="form-control p-2" placeholder="Chercher par nbr de secteurs"  type="text" />
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group input-group">
                                        <form:input path="username" cssClass="form-control p-2" placeholder="Chercher par utilisateur"  type="text" />
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group input-group">
                                        <form:select  path="gradeMin" cssClass="form-control" >
                                            <form:option value="" selected="">Chercher par cotation min</form:option>
                                            <c:forEach var="cot" items="${grades}">
                                                <form:option value="${cot.id}">${cot.name}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div> <!-- form-group end.// -->

                                </td>
                                <td>
                                    <div class="form-group input-group">
                                        <form:select  path="gradeMax" cssClass="form-control" >
                                            <form:option value="" selected="">Chercher par cotation max</form:option>
                                            <c:forEach var="cot" items="${grades}">
                                                <form:option value="${cot.id}">${cot.name}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </div> <!-- form-group end.// -->

                                </td>
                                <td>
                                    <div class="form-group input-group">
                                        <button class="search-btn" type="submit"><i class="fa fa-search"></i></button>
                                    </div>
                                </td>
                            </form:form>
                        </tr>
                    </table>

                    <table  class="table table-striped table-bordered">
                        <thead class="thead-light">
                        <tr>
                            <th>Nom</th>
                            <th>Région</th>
                            <th>Ville</th>
                            <th>Secteurs</th>
                            <th>Cotation Min</th>
                            <th>Cotation max</th>
                            <th>Ajouté par</th>
                        </tr>

                        </thead>
                        <!-- loop over and print our spots -->
                        <c:forEach var="spot" items="${spots}">
                            <!-- construct an "view" link with spot id -->
                            <c:url var="viewLink" value="/spots/${spot.id}" />
                            <tbody>
                            <tr>
                                <!-- display the view link -->
                                <td><a href="${viewLink}">${spot.name}</a></td>
                                <td>${spot.county}</td>
                                <td>${spot.city}</td>
                                <td>${spot.sectors.size()}</td>
                                <td>${spot.gradeMin}</td>
                                <td>${spot.gradeMax}</td>
                                <c:choose>
                                <c:when test="${empty spot.user.username}">
                                    <td>utilisateur supprimé</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${spot.user.username}</td>
                                </c:otherwise>
                            </c:choose>
                            </tr></tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
