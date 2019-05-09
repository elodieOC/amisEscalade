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
    <title>Inscription</title>

    <c:import url="inc/headContent.jsp"/>

</head>
<body class="d-flex flex-column h-100">
<c:choose>
    <c:when test="${ empty sessionScope }">
        <c:import url="inc/navbar.jsp" />
    </c:when>
    <c:otherwise>
        <c:import url="inc/navbar_connected.jsp" />
    </c:otherwise>
</c:choose>
<div class="wrapper flex-shrink-0">
    <div class="container align-items-center">
        <div class="col-lg-6 mx-auto">
            <div class="card bg-light p-4 ">
                <h2 class="mt-3 mb-3 text-center">Création de compte</h2>

                <form:form action="saveUser" cssClass="form-horizontal"  method="post" modelAttribute="user">
                    <!-- need to associate this data with user id -->
                    <form:hidden path="id" />
                    <p>
                        <a href="" class="btn btn-block btn-facebook"> <i class="fab fa-facebook-f"></i>   via facebook</a>
                    </p>
                    <p class="divider-text">
                        <span class="bg-light">OU</span>
                    </p>

                    <form:errors path="username" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                        </div>
                        <form:input path="username" cssClass="form-control p-4" placeholder="Pseudo" type="text" />
                    </div> <!-- form-group// -->


                    <form:errors path="email" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                        </div>
                        <form:input path="email" cssClass="form-control p-4" placeholder="Email" type="email" />
                    </div><!-- form-group end.// -->

                    <form:errors path="password" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                        </div>
                        <form:input path="password" cssClass="form-control p-4" placeholder="Mot de passe" type="password" />
                    </div> <!-- form-group// -->

                    <form:errors path="passwordConfirm" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                        </div>
                        <form:input path="passwordConfirm" cssClass="form-control p-4"  placeholder="Confirmation mot de passe" type="password" />
                    </div> <!-- form-group// -->

                    <form:errors path="memberOrNot" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="far fa-address-card"></i> </span>
                        </div>
                        <form:select  path="memberOrNot" cssClass="form-control" >
                            <form:option value="" selected="">Etes vous membre de l'association?</form:option>
                            <c:forEach var="role" items="${roles}">
                                <form:option value="${role.id}">${role.roleName}</form:option>
                            </c:forEach>
                        </form:select>
                    </div> <!-- form-group end.// -->


                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Créer le compte  </button>
                    </div> <!-- form-group// -->
                    <p class="text-center">Vous avez déjà un compte? <a href="<c:out value="./login" /> ">Connexion</a> </p>
                </form:form>
            </div>
        </div><!-- card.// -->
    </div>
    <!--container end.//-->
</div>
</body>

<c:import url="inc/footer.jsp"/>
</html>
