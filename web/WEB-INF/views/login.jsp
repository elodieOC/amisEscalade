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
<c:import url="inc/choose-navbar.jsp" />
<script src="<c:url value="/resources/js/facebook-login.js" />"></script>
    <main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10">
        <div class="col-lg-6 mx-auto">
            <br />
            <div class="card mt-5 shadow-lg bg-light p-4">
                <form:form action="log-user" cssClass="form-horizontal"  method="post" modelAttribute="user">
                    <p><div id="fb-root"></div>
                    <script async defer crossorigin="anonymous" src="https://connect.facebook.net/fr_FR/sdk.js#xfbml=1&version=v3.3&appId=451914258939804&autoLogAppEvents=1"></script>
                    <div class="fb-login-button btn btn-block" data-width="" data-size="large" data-button-type="continue_with" data-auto-logout-link="false" data-use-continue-as="false"></div>
                    </p>
                    <p class="divider-text">
                        <span class="bg-light">OU</span>
                    </p>

                    <h2 class="mt-3 mb-3 text-center">Connexion</h2>
                    <form:errors path="username" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                        </div>
                        <form:input path="username" cssClass="form-control p-4" placeholder="Pseudo" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="password" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                        </div>
                        <form:input path="password" cssClass="form-control p-4" placeholder="Mot de passe" type="password" />
                    </div> <!-- form-group// -->

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Connexion  </button>
                    </div> <!-- form-group// -->
                    <a href="<c:url value="/user/mot-de-passe-reset" /> "><p class="text-center">Mot de passe oublié?</p></a>
                    <p class="text-center">Vous n'avez pas de compte? <a href="<c:url value="/user/inscription" /> ">Inscription</a> </p>
                </form:form>
            </div>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
