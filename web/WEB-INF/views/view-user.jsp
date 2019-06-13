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
    <div class="container col-md-10 mt-5 offset-md-2">
        <div class="col-md-10">
            <h2 class="d-inline-block mb-sm-5 mt-5 col-md-10">Bienvenu-e ${user.username}</h2>
            <input type="button" value="Editer Mon Profil"
                   onclick="window.location.href='${user.id}/editer'; return false;"
                   class="btn btn-primary ml-4 ml-sm-0 mb-5 mb-sm-0" />

            <div class="form-group input-group col-10 col-sm-8">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                </div>
                <input  class="form-control p-4" placeholder="${user.username}" type="text" readonly="true"/>
            </div> <!-- form-group// -->

            <div class="form-group input-group col-10 col-sm-8">
                <div class="input-group-prepend">
                    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                </div>
                <input  class="form-control p-4" placeholder="${user.email}" type="text" readonly="true"/>
            </div> <!-- form-group// -->


            <h3 class="ml-4 mt-5 mb-5">Mes Spots</h3>
            <c:choose>
                <c:when test="${empty spots}">
                   <p>Vous n'avez pas encore ajouté de spots
                </c:when>
                <c:otherwise>
                    <div class="card-deck mb-5 mx-auto">
                        <!-- loop over and print our spots -->
                        <c:forEach var="spot" items="${spots}">
                            <!-- construct an "view" link with spot id -->
                            <c:url var="viewLink" value="/spots/${spot.id}" />
                            <!-- construct an "update" link with spot id -->
                            <c:url var="updateLink" value="/spots/${spot.id}/editer" />
                            <!-- construct an "delete" link with spot id -->
                            <c:url var="deleteLink" value="/spots/${spot.id}/delete" />
                            <div class="card shadow mb-5 d-inline-block">
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
                                </div>
                                <div class="card-footer">
                                    <input type="button" value="Editer"
                                           onclick="window.location.href='${updateLink}'; return false;"
                                           class="btn btn-primary " />
                                    <!-- display the delete link -->
                                    <input type="button" value="Supprimer"
                                           onclick="window.location.href='${deleteLink}'; return false;"
                                           class="btn btn-secondary " />
                                </div>
                            </div>
                        </c:forEach>
                    </div> <!--End of card-deck -->
                </c:otherwise>
            </c:choose>

            <h3 class="ml-4 mt-5 mb-5">Mes Topos</h3>
            <c:choose>
                <c:when test="${empty topos}">
                   <p class="ml-4 mb-5">Vous n'avez pas encore ajouté de topos
                </c:when>
                <c:otherwise>
                    <div class="card-deck mb-5 mx-auto">
                        <!-- loop over and print our spots -->
                        <c:forEach var="topo" items="${topos}">
                            <!-- construct an "view" link with spot id -->
                            <c:url var="viewLink" value="/topos/${topo.id}" />
                            <!-- construct an "update" link with topo id -->
                            <c:url var="updateLink" value="/topos/${topo.id}/editer" />
                            <!-- construct an "delete" link with topo id -->
                            <c:url var="deleteLink" value="/topos/${topo.id}/delete" />
                            <div class="card shadow mb-5 d-inline-block">
                                <a href="${viewLink}" class="text-decoration-none">
                                    <h4 class="card-header">${topo.name}</h4>
                                </a>
                                <div class="card-body">
                                    <h5 class="card-subtitle mb-3 text-muted">${topo.county}, ${topo.city}</h5>
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
                                </div>
                                <div class="card-footer">
                                    <input type="button" value="Editer"
                                           onclick="window.location.href='${updateLink}'; return false;"
                                           class="btn btn-primary " />
                                    <!-- display the delete link -->
                                    <input type="button" value="Supprimer"
                                           onclick="window.location.href='${deleteLink}'; return false;"
                                           class="btn btn-secondary" />
                                </div>
                            </div>
                        </c:forEach>
                    </div> <!--End of card-deck -->
                </c:otherwise>
            </c:choose>


        </div>
        <input type="button" value="Supprimer Mon Compte"
               onclick="window.location.href='delete'; return false;"
               class="btn btn-danger ml-5 mb-5 mt-5" />
    </div>
    </div>
    </div>
</main>
</body>

<c:import url="inc/footer.jsp"/>
</html>
