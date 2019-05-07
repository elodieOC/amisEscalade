<%--
  Created by IntelliJ IDEA.
  User: elodie
  Date: 13/02/2019
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body id="page-top">
<!-- Navigation -->

<nav class="navbar navbar-expand-lg bg-dark navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="<c:url value="/home" />">La montagne, ça vous gagne!</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item ml-4">
                    <a class="nav-link js-scroll-trigger" href="<c:url value="/about" />" >A Propos</a>
                </li>
                <li class="nav-item ml-4"><a class="nav-link" href="<c:url value="#" />">Les Topos</a></li>
                <li class="nav-item ml-4"><a class="nav-link" href="<c:url value="/spots/list" />">Les Spots</a></li>
                <li class="nav-item ml-4">
                    <a class="nav-link js-scroll-trigger" href="<c:url value="/contact" />">Contact</a>
                </li>
                <li class="nav-item ml-4"><a class="nav-link js-scroll-trigger" href="<c:url value="/user/profile/" />"><span class="glyphicon glyphicon-user"></span> Mon profil</a></li>
                <li class="nav-item ml-4 mt-2">
                    <form class="form-inline" action="/action_page.php">
                        <input class="form-control mr-sm-2" type="text" placeholder="Recherche">
                        <button class="btn btn-primary" type="submit">Recherche</button>
                    </form>
                </li>
                <li class="nav-item ml-4">
                    <a class="nav-link js-scroll-trigger " href="<c:url value="/user/logout" />">
                        <span class="glyphicon glyphicon-off"></span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container nav-below"></div>
<!-- Header -->
