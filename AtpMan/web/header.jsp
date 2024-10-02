<%-- 
    Document   : header
    Created on : Sep 19, 2024, 10:10:22 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <!-- Spinner Start -->
    <div
        id="spinner"
        class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center"
        >
        <div
            class="spinner-border text-primary"
            style="width: 3rem; height: 3rem"
            role="status"
            >
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->

    <!-- Navbar & Hero Start -->
    <div class="container-fluid nav-bar px-0 px-lg-4 py-lg-0">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a href="#" class="navbar-brand p-0">
                    <h1 class="text-primary mb-0"><i class="fab fa-slack me-2"></i> APTMANAGE</h1>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav mx-0 mx-lg-auto">
                        <a href="home.jsp" class="nav-item nav-link <%= request.getRequestURI().contains("home.jsp") ? "active" : "" %>">Trang chủ</a>
                        <a href="about.jsp" class="nav-item nav-link <%= request.getRequestURI().contains("about.jsp") ? "active" : "" %>">Giới thiệu</a>
                        <a href="serviceintro" class="nav-item nav-link <%= request.getRequestURI().contains("serviceuser.jsp") || request.getRequestURI().endsWith("serviceintro") ? "active" : "" %>">Dịch vụ</a>
                        <a href="feedback.jsp" class="nav-item nav-link <%= request.getRequestURI().contains("feedback.jsp") ? "active" : "" %>">Nhận xét</a>
                    </div>
                </div>
                <div class="d-none d-xl-flex flex-shrink-0 ps-4">
                    <a href="#" class="btn btn-light btn-lg-square rounded-circle position-relative wow tada"
                       data-wow-delay=".9s">
                        <i class="fa fa-phone-alt fa-2x"></i>
                        <div class="position-absolute" style="top: 7px; right: 12px;">
                            <span><i class="fa fa-comment-dots text-secondary"></i></span>
                        </div>
                    </a>
                    <div class="d-flex justify-content-start align-items-center ms-3 gap-3">
                        
                        <c:if test="${sessionScope.user == null}">
                            <a href="userlogin" class="btn btn-primary">
                                <i class="bi bi-box-arrow-in-right"></i>Login
                            </a>
                        </c:if>

                       
                        <c:if test="${sessionScope.user != null}">
                            <a class="btn btn-primary">
                                <i class="fa-solid fa-user"></i> ${sessionScope.user.name}
                            </a>
                        </c:if>

                        <c:if test="${sessionScope.user ==null}">
                         
                            <a href="register.jsp" class="btn btn-primary">
                                <i class="fab fa-slack me-2"></i> Sign up
                            </a>
                        </c:if>

                        <c:if test="${sessionScope.user !=null}">

                            <a href="logout" class="btn btn-primary">
                                <i class="fab fa-slack me-2"></i> Logout
                            </a>
                        </c:if>
                    </div>



                </div>
            </nav>
        </div>
    </div>
    <!-- Navbar & Hero End -->
