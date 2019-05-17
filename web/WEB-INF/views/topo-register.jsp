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
    <title>Ajouter Topo</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <div class="col-lg-6 mx-auto">
            <br />
            <br />
            <br />
            <div class="card bg-light p-4 ">
                <h2 class="mt-3 mb-3 text-center">Ajouter Topo</h2>
                <form:form action="saveTopo" cssClass="form-horizontal"  method="post" modelAttribute="topo">

                    <!-- need to associate this data with topo id -->
                    <form:hidden path="id" />

                    <form:errors path="name" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> Titre</span>
                        </div>
                        <form:input path="name" cssClass="form-control p-4" placeholder="Titre du Topo" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="city" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> Ville </span>
                        </div>
                        <form:input path="city" cssClass="form-control p-4" placeholder="Ville du Topo" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="county" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Région </span>
                        </div>
                        <form:input path="county" cssClass="form-control p-4" placeholder="Région du Topo" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="country" cssClass="error"/>
                    <div class="form-group input-group ">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Pays </span>
                        </div>
                        <form:input path="country" cssClass="form-control p-4" placeholder="Pays du Topo" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="dateRelease" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Date de parution </span>
                        </div>
                        <form:input path="dateRelease" cssClass="form-control p-4" placeholder="jj/mm/aaaa" type="text" />
                    </div> <!-- form-group// -->

                    <form:errors path="available" cssClass="error"/>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Disponibilité</span>
                        </div>
                        <form:select  path="available" cssClass="form-control" >
                            <form:option value="" selected="">Disponible</form:option>
                                <form:option value="oui">Oui</form:option>
                                <form:option value="non">Non</form:option>
                        </form:select>
                    </div> <!-- form-group end.// -->

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

