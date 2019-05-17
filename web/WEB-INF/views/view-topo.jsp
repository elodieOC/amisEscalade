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
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <h1 class="d-inline-block col-md-8">${topo.name}
            <span class="text-muted ml-5 small">(Ajouté par:  ${topo.user.username}, Disponible: ${topo.available})</span></h1>
        <c:if test="${topo.available eq 'oui'}">
            <input type="button" value="Faire une demande de Réservation"
                   onclick="window.location.href='${topo.id}/reserver'; return false;"
                   class="btn btn-primary" />
        </c:if>


        <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
        <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

        <c:if test="${userRole eq '1' || userId eq topo.user.id && userId ne null}">
            <div class="d-inline-block col-md-8 mt-3">
                <p class="text-muted small">Vous êtes administrateur ou vous avez ajouté ce topo. Vous pouvez l'éditer ou le supprimer.</p>
            </div>
            <!-- construct an "update" link with topo id -->
            <c:url var="updateLink" value="/topos/topo/${topo.id}/updateFormTopo" />
            <!-- construct an "delete" link with topo id -->
            <c:url var="deleteLink" value="/topos/topo/${topo.id}/delete" />
            <!-- display the update link -->
            <input type="button" value="Editer"
                   onclick="window.location.href='${updateLink}'; return false;"
                   class="btn btn-secondary" />
            <!-- display the delete link -->
            <input type="button" value="Supprimer"
                   onclick="window.location.href='${deleteLink}'; return false;"
                   class="btn btn-secondary" />
        </c:if>

        <div class="panel panel-info mt-5">
            <h2 class="d-inline-block col-md-8 mb-5">Informations</h2>
            <div class="panel-body mb-5">
                <table class="mb-5">
                    <tr>
                        <td class="col-lg-2">Pays: </td><td class="col-lg-7">${topo.country}</td>
                    </tr>
                    <tr>
                        <td class="col-lg-2">Région: </td><td class="col-lg-7">${topo.county}</td>
                    </tr>
                    <tr>
                        <td class="col-lg-2">Ville: </td><td class="col-lg-7">${topo.city}</td>
                    </tr>
                    <tr>
                        <td class="col-lg-2">Date de Parution: </td><td class="col-lg-7">${topo.dateRelease}</td>
                    </tr>
                    <tr>
                        <td class="col-lg-2">Description: </td><td class="col-lg-7" style="white-space: pre-line;">${topo.description}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</main>
<c:import url="inc/footer.jsp"/>
</body>
</html>
