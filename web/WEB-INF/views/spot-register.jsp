<%--
  Created by IntelliJ IDEA.
  User: elodie
  Date: 03/05/2019
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Ajouter Spot</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="col-lg-6 mx-auto">
            <br />
            <br />
            <br />
            <div class="card bg-light p-4 ">
                <h2 class="mt-3 mb-3 text-center">Ajouter Spot</h2>
                <form:form action="saveSpot" cssClass="form-horizontal"  method="post" modelAttribute="spot">

                    <!-- need to associate this data with spot id -->
                    <form:hidden path="id" />

                    <form:errors path="name" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text span-large-5"> Nom</span>
                        </div>
                        <form:input path="name" cssClass="form-control p-4" placeholder="Nom du Spot" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="city" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text span-large-5"> Ville </span>
                        </div>
                        <form:input path="city" cssClass="form-control p-4" placeholder="Ville du Spot" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="county" cssClass="error"/>
                    <div class="form-group input-group mb-5">
                        <div class="input-group-prepend">
                            <span class="input-group-text span-large-5">Région </span>
                        </div>
                        <form:input path="county" cssClass="form-control p-4" placeholder="Région du Spot" type="text" />
                    </div> <!-- form-group// -->

                    <c:set var="userRole" value="${sessionScope['loggedInUserRole']}" />
                    <c:set var="userId" value="${sessionScope['loggedInUserId']}" />

                    <c:if test="${userRole eq '1' || userRole eq '2' && userId ne null}">
                        <form:errors path="tagged" cssClass="error" />
                        <div class="form-group input-group mb-5">
                            <form:label path="tagged" class="text-muted"> Tag "Officiel Les amis de l’escalade"?</form:label>
                            <form:checkbox  path="tagged" cssClass="ml-3 p-2" value="${spot.tagged}" />
                        </div> <!-- form-group// -->
                    </c:if>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Envoyer  </button>
                    </div> <!-- form-group// -->

                </form:form>
            </div>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>

