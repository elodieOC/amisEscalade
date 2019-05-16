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
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5" >
        <header class="masthead">
            <div class="container d-flex align-items-center mt-5">
                <div class="mx-auto text-center mt-5">
                    <div class="mt-5">
                        <img class="mt-5" src="<c:url value="/resources/img/logo.JPG" />" alt="logo amis de l'escalade">
                    </div>
                    <h2 class="text-white-50 mx-auto mt-2 mb-5">Venez partager et échanger sur l'escalade avec la communauté.</h2>
                    <a href="<c:out value="about.jsp" />" class="btn btn-secondary js-scroll-trigger">A propos de nous</a>
                </div>
            </div>
        </header>
    </div>
</main>
</body>
<c:import url="inc/footer.jsp"/>
</html>
