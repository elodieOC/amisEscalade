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
    <title>Editer une voie</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="col-md-offset-1 col-md-10">
            <h2 class="mb-5">Editer une voie</h2>
            <form:form action="updateRoute" cssClass="form-horizontal"  method="post" modelAttribute="route">
                <form:hidden path="id" />

                <form:errors path="name" cssClass="error"/>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> Nom </span>
                    </div>
                    <form:input path="name" cssClass="form-control p-4" placeholder="Nom" type="text" />
                </div><!-- form-group// -->


                <div class="row mb-2">
                    <button class="btn btn-lg btn-primary mb-2 ml-4" type="submit">Editer</button>
                </div>
            </form:form>
            </div>
        </div>
    </main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
