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
    <title>Spot ${spot.name} - Ajouter un secteur</title>
    <c:import url="inc/headContent.jsp"/>
</head>

<body class="d-flex flex-column h-100">
<c:import url="inc/choose-navbar.jsp" />
<div class="wrapper flex-shrink-0">
    <div class="container d-flex h-100 align-items-center">
        <div class="col-md-offset-1 col-md-10">
            <h2 class="mb-5">Spot ${spot.name} - Ajouter un secteur</h2>
            <hr />
            <form:form action="saveSector" cssClass="form-horizontal"  method="post" modelAttribute="sector">
                <form:hidden path="id" />

                <form:errors path="name" cssClass="error"/>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> Nom</span>
                    </div>
                    <form:input path="name" cssClass="form-control p-4" placeholder="Nom du Secteur" type="text" />
                </div> <!-- form-group// -->
                
                <form:errors path="info" cssClass="error"/>
                <div class="form-group">
                    <label for="info">Description du secteur:</label>
                    <form:textarea path="info" cssClass="form-control p-4" placeholder="Description" rows="3" />
                </div><!-- form-group// -->
                
                <div class="row mb-2">
                    <button class="btn btn-lg btn-primary mb-2 ml-4" type="submit">Ajouter</button>
                </div>
            </form:form>
            </div>
        </div>
    </div>
</div>



</body>

<c:import url="inc/footer.jsp"/>
</html>
