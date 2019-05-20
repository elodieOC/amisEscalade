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
    <title>Réinitialisation Mot de Passe</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="col-lg-6 mx-auto">
            <br />
            <br />
            <br />
            <c:if test="${success == 1}">
                <div class="alert alert-success" role="alert">
                    Un nouveau mot de passe vous a été envoyé <a href="<c:url value="/user/login" /> " class="alert-link">Connectez vous ici</a>.
                </div>
            </c:if>

            <div class="card bg-light p-4">
                <h2 class="mt-3 mb-5 text-center">Réinitialisation Mot de Passe</h2>

                <form:form action="resetPassword" cssClass="form-horizontal"  method="post" modelAttribute="user">

                    <form:errors path="email" cssClass="error"/>
                    <div class="form-group input-group mb-5">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                        </div>
                        <form:input path="email" cssClass="form-control p-4" placeholder="Email" type="text" />
                    </div> <!-- form-group// -->

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Envoyer  </button>
                    </div> <!-- form-group// -->
                    <p class="text-center">Vous n'avez pas de compte? <a href="<c:url value="/user/register" /> ">Inscription</a> </p>
                </form:form>
            </div>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
