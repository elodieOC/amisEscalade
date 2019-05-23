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
    <title>Edition de compte</title>

    <c:import url="inc/headContent.jsp"/>

</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="col-lg-6 mx-auto">
            <div class="card bg-light p-4 ">
                <h2 class="mt-3 mb-3">Edition de compte</h2>

                <form:form action="update" cssClass="form-horizontal"  method="post" modelAttribute="user">
                    <!-- need to associate this data with user id -->
                    <form:hidden path="id" />

                                       <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                        </div>
                        <form:input path="username" cssClass="form-control p-4" placeholder="Pseudo" type="text" readonly="true"/>
                    </div> <!-- form-group// -->

                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                        </div>
                        <form:input path="email" cssClass="form-control p-4" placeholder="Email" type="email" readonly="true"/>
                    </div><!-- form-group end.// -->

                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                        </div>
                        <form:input path="password" cssClass="form-control p-4"  type="password" readonly="true"/>
                    </div> <!-- form-group// -->

                    <form:errors path="passwordConfirm" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                        </div>
                        <form:input path="passwordConfirm" cssClass="form-control p-4" value="${user.password}" type="password" readonly="true"/>
                    </div> <!-- form-group// -->

                    <form:errors path="memberOrNot" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="far fa-address-card"></i> </span>
                        </div>
                        <form:select  path="memberOrNot" cssClass="form-control" >
                            <form:option value="" selected="">Editer le role utilisateur:</form:option>
                            <c:forEach var="role" items="${roles}">
                            <form:option value="${role.id}">${role.roleName}</form:option>
                            </c:forEach>
                        </form:select>
                    </div> <!-- form-group end.// -->

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Editer le compte  </button>
                    </div> <!-- form-group// -->
                </form:form>
            </div>
        </div><!-- card.// -->
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
