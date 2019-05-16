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
    <title>Spot ${spot.name} - Ajouter un commentaire</title>
    <c:import url="inc/headContent.jsp"/>
</head>

<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="col-md-offset-1 col-md-10">
            <h2 class="mb-5">Spot ${spot.name} - Ajouter un commentaire</h2>
            <hr />
            <form:form action="saveComment" cssClass="form-horizontal"  method="post" modelAttribute="comment">
                <form:hidden path="id" />

                <form:errors path="content" cssClass="error"/>
                <div class="form-group">
                    <label for="content">Votre commentaire:</label>
                    <form:textarea path="content" cssClass="form-control p-4" placeholder="Commentaire" rows="3" />
                </div>
                <div class="row mb-2">
                    <button class="btn btn-lg btn-primary mb-2 ml-4" type="submit">Ajouter</button>
                </div>
            </form:form>
            </div>
        </div>
    </main>
</div>



</body>

<c:import url="inc/footer.jsp"/>
</html>
