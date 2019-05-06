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
    <title>Connexion</title>

    <c:import url="inc/headContent.jsp"/>

</head>
<body>
<c:if test="${ empty sessionScope }">
    <c:import url="inc/navbar.jsp" />
</c:if>
<c:if test="${ !empty sessionScope }">
    <c:import url="inc/navbar_connected.jsp" />
</c:if>
<div class="wrapper">
<div class="container align-items-center">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Connexion</h2>
        <div class="panel panel-info">
            <div class="panel-body">
                <form:form action="logUser" cssClass="form-horizontal"  method="post" modelAttribute="user">

                    <div class="form-group">
                        <label for="username" class="col-md-4 control-label">Pseudo</label>
                        <form:errors path="username" cssClass="error"/>
                        <div class="col-md-8">
                            <form:input path="username" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-4 control-label">Mot de passe</label> <form:errors path="password" cssClass="error"/>
                        <div class="col-md-8">
                            <form:input type="password" path="password" cssClass="form-control" />
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
</div>
</body>

<c:import url="inc/footer.jsp"/>
</html>
