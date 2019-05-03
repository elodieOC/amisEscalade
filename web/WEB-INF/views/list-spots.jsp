<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  Spot: elodie
  Date: 17/04/2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<body>
<c:if test="${ empty sessionScope }">
    <c:import url="inc/navbar.jsp" />
</c:if>
<c:if test="${ !empty sessionScope }">
    <c:import url="inc/navbar_connected.jsp" />
</c:if>
<div class="container d-flex h-100 align-items-center">
    <div class="col-md-offset-1 col-md-10">
        <h2>Liste des Spots</h2>
        <hr />

        <input type="button" value="Ajouter Spot"
               onclick="window.location.href='ajoutSpot'; return false;"
               class="btn btn-primary" />
        <br/><br/>
        <div class="panel panel-info">
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>Nom</th>
                        <th>Région</th>
                        <th>Ville</th>
                        <th>Ajouté par</th>
                        <th>Action</th>
                    </tr>

                    <!-- loop over and print our spots -->
                    <c:forEach var="spot" items="${spots}">
                        <!-- construct an "update" link with spot id -->
                        <c:url var="updateLink" value="/spots/updateForm">
                            <c:param name="userId" value="${spot.id}" />
                        </c:url>
                        <!-- construct an "delete" link with spot id -->
                        <c:url var="deleteLink" value="/spots/delete">
                            <c:param name="spotId" value="${spot.id}" />
                        </c:url>

                        <tr>
                            <td>${spot.name}</td>
                            <td>${spot.county}</td>
                            <td>${spot.city}</td>
                            <td>${spot.user.username}</td>

                            <td>
                                <!-- display the update link --> <a href="${updateLink}">Update</a>
                                | <a href="${deleteLink}"
                                     onclick="if (!(confirm('Are you sure you want to delete this spot?'))) return false">Delete</a>
                            </td>

                        </tr>

                    </c:forEach>

                </table>

            </div>
        </div>
    </div>

</div>
</body>
</html>
