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
    <title>Voie ${route.name} - Ajouter une longueur</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="col-md-offset-1 col-lg-6 mx-auto">
            <h2 class="mb-5">Voie ${route.name} - Ajouter une longueur</h2>
            <hr />
            <form:form action="saveLength" cssClass="form-horizontal"  method="post" modelAttribute="lengthForm">

                <form:errors path="height" cssClass="error"/>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text span-large-9"> Hauteur en m</span>
                    </div>
                    <form:input path="height" cssClass="form-control p-4" placeholder="Hauteur" type="text" />
                </div><!-- form-group// -->

                <form:errors path="bolts" cssClass="error"/>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text span-large-9"> Spits </span>
                    </div>
                    <form:input path="bolts" cssClass="form-control p-4" placeholder="Spits"  type="text"  />
                </div><!-- form-group// -->

                <form:errors path="grade" cssClass="error"/>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text span-large-9">Cotation</span>
                    </div>
                    <form:select  path="grade" cssClass="form-control" >
                        <form:option value="" selected="">Cotation</form:option>
                        <c:forEach var="cot" items="${grades}">
                            <form:option value="${cot.id}">${cot.name}</form:option>
                        </c:forEach>
                    </form:select>
                </div> <!-- form-group end.// -->

                <div class="row mb-2">
                    <button class="btn btn-lg btn-primary mb-2 ml-4" type="submit">Ajouter</button>
                </div>
            </form:form>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
