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
    <title>Editer Topo</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="col-lg-6 mx-auto">
            <div class="card bg-light p-4 ">
                <h2 class="mt-3 mb-3 ">Editer Topo</h2>
                <form:form action="update" cssClass="form-horizontal"  method="post" modelAttribute="topo">

                    <!-- need to associate this data with topo id -->
                    <form:hidden path="id" />

                    <form:errors path="name" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text span-large-9"> Titre</span>
                        </div>
                        <form:input path="name" cssClass="form-control p-4" placeholder="Titre du Topo" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="city" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text span-large-9"> Ville </span>
                        </div>
                        <form:input path="city" cssClass="form-control p-4" placeholder="Ville du Topo" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="county" cssClass="error"/>
                    <div class="form-group input-group ">
                        <div class="input-group-prepend">
                            <span class="input-group-text span-large-9">Région </span>
                        </div>
                        <form:input path="county" cssClass="form-control p-4" placeholder="Région du Topo" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="country" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text span-large-9">Pays </span>
                        </div>
                        <form:input path="country" cssClass="form-control p-4" placeholder="Pays du Topo" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="dateRelease" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text span-large-9">Date de parution </span>
                        </div>
                        <form:input path="dateRelease" cssClass="form-control p-4" placeholder="jj/mm/aaaa" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="available" cssClass="error"/>
                    <div class="form-group input-group mb-5">
                        <form:label path="available" class="text-muted"> Topo disponible au prêt?</form:label>
                        <form:checkbox  path="available" cssClass="ml-3 p-2" value="${topo.available}" />
                    </div> <!-- form-group// -->

                    <form:errors path="description" cssClass="error"/>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <form:textarea path="description" cssClass="form-control p-4" placeholder="Description" rows="2" />
                    </div><!-- form-group// -->

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block"> Envoyer  </button>
                    </div> <!-- form-group// -->

                </form:form>
            </div>
        </div>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>

