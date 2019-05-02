<%--
  Created by IntelliJ IDEA.
  User: elodie
  Date: 02/05/2019
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Home</title>

  <c:import url="inc/headContent.jsp"/>
</head>
  <c:import url="inc/navbar.jsp" />

<header class="masthead">
  <div class="container d-flex h-100 align-items-center">
    <div class="mx-auto text-center">
      <h1 class="mx-auto my-0 text-uppercase">Les amis de l'escalade!</h1>
      <h2 class="text-white-50 mx-auto mt-2 mb-5">Venez partager et échanger sur l'escalade avec la communauté.</h2>
      <a href="<c:out value="about.jsp" />" class="btn btn-primary js-scroll-trigger">A propos de nous</a>
    </div>
  </div>
</header>
<c:import url="inc/footer.jsp"/>



</body>
</html>
