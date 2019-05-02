<%--
  Created by IntelliJ IDEA.
  User: elodie
  Date: 17/04/2019
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registration Form</title>

    <c:import url="inc/headContent.jsp"/>

</head>
<body>
<c:import url="inc/navbar.jsp" />
<div class="container d-flex h-100 align-items-center">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Ajouter utilisateur</h2>
        <div class="panel panel-info">
            <div class="panel-body">
                <form:form action="saveUser" cssClass="form-horizontal"  method="post" modelAttribute="user">

                    <!-- need to associate this data with user id -->
                    <form:hidden path="id" />

                    <div class="form-group">
                        <label for="username" class="col-md-6 control-label">Pseudo</label>
                        <form:errors path="username" cssClass="error"/>
                        <div class="col-md-9">
                            <form:input path="username" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-md-6 control-label">Email</label> <form:errors path="email" cssClass="error"/>
                        <div class="col-md-9">
                            <form:input path="email" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-6 control-label">Mot de passe</label> <form:errors path="password" cssClass="error"/>
                        <div class="col-md-9">
                            <form:input type="password" path="password" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="passwordConfirm" class="col-md-6 control-label">Confirmation mot de passe</label> <form:errors path="passwordConfirm" cssClass="error"/>
                        <div class="col-md-9">
                            <form:input type="password" path="passwordConfirm" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6">Etes vous Membre de l'association?</div>
                        <div class="col-md-9">
                        <form:errors path="memberOrNot" cssClass="error"/>
                        </div>
                        <div class="col-md-6">
                            <form:radiobutton path="memberOrNot" value="yes" />Oui
                            <form:radiobutton path="memberOrNot" value="no" />Non
                        </div>

                    </div>
                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <form:button cssClass="btn btn-primary">Envoyer</form:button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
