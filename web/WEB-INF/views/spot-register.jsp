<%--
  Created by IntelliJ IDEA.
  User: elodie
  Date: 03/05/2019
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Ajouter Spot</title>
    <c:import url="inc/headContent.jsp"/>

</head>
<body class="d-flex flex-column h-100">
<c:import url="inc/choose-navbar.jsp" />
<div class="wrapper flex-shrink-0">
<div class="container d-flex h-100 align-items-center">
    <div class="col-md-offset-2 col-md-7">
        <h2 class="text-center">Ajouter Spot</h2>
        <div class="panel panel-info">
            <div class="panel-body">
                <form:form action="saveSpot" cssClass="form-horizontal"  method="post" modelAttribute="spot">

                    <!-- need to associate this data with spot id -->
                    <form:hidden path="id" />

                    <div class="form-group">
                        <label for="name" class="col-md-6 control-label">Nom</label>
                        <form:errors path="name" cssClass="error"/>
                        <div class="col-md-9">
                            <form:input path="name" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="city" class="col-md-6 control-label">Ville</label> <form:errors path="city" cssClass="error"/>
                        <div class="col-md-9">
                            <form:input path="city" cssClass="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="county" class="col-md-6 control-label">Région</label> <form:errors path="county" cssClass="error"/>
                        <div class="col-md-9">
                            <form:input type="county" path="county" cssClass="form-control" />
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

