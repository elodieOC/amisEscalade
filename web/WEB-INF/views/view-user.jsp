<%--
  Created by IntelliJ IDEA.
  User: elodie
  Date: 17/04/2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Mon Profil</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5 col-md-12">
    <div class="container col-md-10 mt-5 offset-2">
    <div class="col-md-10">
        <h2 class="d-inline-block mb-5 mt-5 col-md-10">Bienvenu-e ${user.username}</h2>
        <input type="button" value="Editer Mon Profil"
               onclick="window.location.href='editer'; return false;"
               class="btn btn-primary" />
        <div class="panel panel-info">
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                    <tr>
                        <th>Pseudo</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                    </tr>
                </table>

               <table class="table table-striped table-bordered">
                   <thead class="thead-dark">
                    <tr>
                        <th>Spots</th>
                    </tr>
                   </thead>
                   <c:if test="${ empty spots }">
                       <tr><td>Vous n'avez pas encore ajouté de spot</td></tr>
                   </c:if>
                   <c:if test="${ !empty spots }">
                    <c:forEach var="spot" items="${spots}" >
                        <tr> <td>${spot.name}</td></tr>
                    </c:forEach>
                   </c:if>
                </table>

               <table class="table table-striped table-bordered">
                   <thead class="thead-dark">
                   <tr><th colspan="7">Topos</th></tr>
                    <tr>
                        <th>Titre</th>
                        <th>Ville</th>
                        <th>Région</th>
                        <th>Pays</th>
                        <th>Description</th>
                        <th>Date de parution</th>
                        <th>Disponible</th>
                    </tr>
                   </thead>
                   <c:choose>
                       <c:when test="${empty topos}"><tr><td colspan="7">Vous n'avez pas encore ajouté de topo</td></tr></c:when>
                       <c:otherwise>
                           <c:forEach var="topo" items="${topos}" >
                               <tr>
                                   <td>${topo.name}</td>
                                   <td>${topo.city}</td>
                                   <td>${topo.county}</td>
                                   <td>${topo.country}</td>
                                   <td>${topo.description}</td>
                                   <td>${topo.dateRelease}</td>
                                   <td>${topo.available}</td>
                               </tr>
                           </c:forEach>
                       </c:otherwise>
                   </c:choose>
                </table>
            </div>
            <input type="button" value="Supprimer Mon Compte"
                   onclick="window.location.href='delete'; return false;"
                   class="btn btn-secondary" />
        </div>
    </div>
</div>
</main>
</body>

<c:import url="inc/footer.jsp"/>
</html>
