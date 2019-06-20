<%--
  Created by IntelliJ IDEA.
  User: elodie
  Date: 13/02/2019
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Contact</title>
    <c:import url="inc/headContent.jsp"/>
</head>
<c:import url="inc/choose-navbar.jsp" />
<main role="main" class="flex-shrink-0 mt-5">
    <div class="container col-md-10 mt-5">
        <section id="signup" class="signup-section mt-5">
            <div class="row">
                <div class="col-md-8 col-lg-6 mx-auto text-center mt-5">
                    <i class="far fa-paper-plane fa-2x mb-2 "></i>
                    <h2 class="mb-5">Inscrivez-vous à la newsletter!</h2>
                    <c:if test="${success == 1}">
                        <div class="mt-5 alert alert-success" role="alert">
                            Vous avez été inscrit à la newsletter!
                        </div>
                    </c:if>
                    <c:if test="${warning == 1}">
                        <div class="mt-5 alert alert-info" role="alert">
                            Vous êtes déjà inscrit à la newsletter!
                        </div>
                    </c:if>
                    <form:form action="news" cssClass="form-horizontal"  method="post" modelAttribute="suscriber">
                        <!-- need to associate this data with suscriber id -->
                        <form:hidden path="id" />
                        <form:errors path="email"  cssClass="error"/>
                        <div class="input-group">
                            <form:input type="email" path="email" class="form-control" placeholder="Adresse email..." />
                            <button type="submit" class="btn btn-primary mx-auto">Inscription</button>
                        </div>
                    </form:form>

                </div>
            </div>
        </section>
        <!-- Contact Section -->
        <section class="contact-section mt-5">
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-4 mb-3 mb-md-0 mt-5">
                        <div class="card shadow py-4 h-100 bg-dark text-white">
                            <div class="card-body text-center">
                                <i class="fas fa-map-marked-alt text-primary mb-2"></i>
                                <h4 class="text-uppercase m-0">Address</h4>
                                <hr class="my-4">
                                <div class="small text-white-50">4923 Market Street, Orlando FL</div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 mb-3 mb-md-0 mt-5">
                        <div class="card shadow py-4 h-100 bg-dark text-white">
                            <div class="card-body text-center">
                                <i class="fas fa-envelope text-primary mb-2"></i>
                                <h4 class="text-uppercase m-0">Email</h4>
                                <hr class="my-4">
                                <div class="small text-white-50">
                                    <a href="#">hello@yourdomain.com</a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 mb-3 mb-md-0 mt-5">
                        <div class="card shadow py-4 h-100 bg-dark text-white">
                            <div class="card-body text-center">
                                <i class="fas fa-mobile-alt text-primary mb-2"></i>
                                <h4 class="text-uppercase m-0">Phone</h4>
                                <hr class="my-4">
                                <div class="small text-white-50">+1 (555) 902-8832</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <!-- social icons -->
            <div class="container mt-5 mb-5 mt-5">
                <div class="row">
                    <div class="col-md-8 col-lg-6 mx-auto text-center h-50 mt-5">
                        <div class="social d-flex justify-content-center">
                            <a href="#" class="mx-5">
                                <i class="fab fa-twitter"></i>
                            </a>
                            <a href="#" class="mx-5">
                                <i class="fab fa-facebook-f"></i>
                            </a>
                            <a href="#" class="mx-5">
                                <i class="fab fa-instagram"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</main>
</body>

<c:import url="inc/footer.jsp"/>
</html>
