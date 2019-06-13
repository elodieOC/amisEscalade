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
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="col-lg-6 mx-auto">
            <div class="card shadow bg-light p-4 mt-5 mb-5">
                <h2 class="mt-3 mb-5 text-center">Spot ${spot.name} - Ajouter un secteur</h2>
                <form:form action="add-sector" cssClass="form-horizontal"  method="post" modelAttribute="sector" enctype="multipart/form-data">
                    <form:hidden path="id" />
                    <form:errors path="name" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> Nom</span>
                        </div>
                        <form:input path="name" cssClass="form-control p-4" placeholder="Nom du Secteur" type="text" />
                    </div> <!-- form-group// -->

                    <div class="mb-2 mt-2"><strong>Informations:</strong></div>

                    <form:errors path="location" cssClass="error"/>
                    <div class="form-group">
                        <label for="location">Situation:</label>
                        <form:textarea path="location" cssClass="form-control p-4" placeholder="Situation" rows="2" />
                    </div><!-- form-group// -->

                    <form:errors path="access" cssClass="error"/>
                    <div class="form-group">
                        <label for="access">Accès:</label>
                        <form:textarea path="access" cssClass="form-control p-4" placeholder="Accès" rows="2" />
                    </div><!-- form-group// -->

                    <form:errors path="imageFile" cssClass="error" />
                    <div class="form-group input-group mb-5">
                        <form:label path="imageFile" cssClass="text-muted mr-3">Ajouter une image: </form:label>
                        <form:input path="imageFile"  type="file" name="file" />
                    </div> <!-- form-group// -->

                    <div class="row mb-2">
                        <button class="btn btn-lg btn-primary mb-2 ml-4" type="submit">Ajouter</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
