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
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="mb-5 mt-5">Bienvenu-e ${user.username}</h2>
        <div class="panel panel-info">
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                    <tr>
                        <th>Pseudo</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <!-- construct an "update" link with user id -->
                    <c:url var="updateLink" value="/user/updateForm">
                        <c:param name="id" value="${user.id}" />
                    </c:url>

                    <!-- construct an "delete" link with user id -->
                    <c:url var="deleteLink" value="/user/delete">
                        <c:param name="id" value="${user.id}" />
                    </c:url>

                    <tr>
                        <td>${user.username}</td>
                        <td>${user.email}</td>


                        <td>
                            <!-- display the update link --> <a href="${updateLink}">Update</a>
                            | <a href="${deleteLink}"
                                 onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
                        </td>

                    </tr>

                </table>
               <%--LAZY EXCEPTION--%>
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
            </div>
        </div>
    </div>
</div>
</main>
</body>

<c:import url="inc/footer.jsp"/>
</html>
