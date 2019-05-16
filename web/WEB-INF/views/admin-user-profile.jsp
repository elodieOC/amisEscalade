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
    <title>Profil de ${user.username}</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="col-md-offset-2 col-md-7">
            <h2 class="mb-5">Profil de l'utilisateur</h2>
            <div class="panel panel-info">
                <div class="panel-body">
                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">
                        <tr>
                            <th>Pseudo</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <!-- construct an "update" link with user id -->
                        <c:url var="updateLink" value="/admin/user/${user.id}/updateForm" />

                        <!-- construct an "delete" link with user id -->
                        <c:url var="deleteLink" value="/admin/user/${user.id}/delete" />

                        <tr>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.userRole.roleName}</td>


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
                            <th>Spots</th>
                        </thead>
                        <c:if test="${ empty spots }">
                            <tr><td>Cet utilisateur n'a pas encore ajouté de spot</td></tr>
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
