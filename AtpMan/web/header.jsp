<%-- 
    Document   : header
    Created on : Sep 19, 2024, 10:10:22 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <style>
    .search-container {
        display: inline-block;
    }

    .search-dropdown {
        left: calc(64%); /* Shifted ?px to the right */
        transform: translateX(-50%);
        top: calc(100% + 5px);
        z-index: 1000;
        animation: fadeIn 0.2s ease-in-out;
        white-space: nowrap;
    }

    @keyframes fadeIn {
        from {
            opacity: 0;
            transform: translate(-50%, -10px);
        }
        to {
            opacity: 1;
            transform: translate(-50%, 0);
        }
    }

    /* Adjusted arrow position */
    .search-dropdown::before {
        content: '';
        position: absolute;
        top: -8px;
        left: calc(45%); /* Adjusted arrow position to align with button */
        transform: translateX(-50%);
        border-left: 8px solid transparent;
        border-right: 8px solid transparent;
        border-bottom: 8px solid white;
    }
</style>

<!-- Add this JavaScript before the closing body tag -->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const searchBtn = document.getElementById('searchBtn');
        const searchDropdown = document.getElementById('searchDropdown');
        
        // Toggle dropdown when clicking search button
        searchBtn.addEventListener('click', function(e) {
            e.stopPropagation();
            searchDropdown.style.display = searchDropdown.style.display === 'none' ? 'block' : 'none';
            
            // Focus the input when opening dropdown
            if (searchDropdown.style.display === 'block') {
                searchDropdown.querySelector('input').focus();
            }
        });
        
        // Close dropdown when clicking outside
        document.addEventListener('click', function(e) {
            if (!searchDropdown.contains(e.target) && e.target !== searchBtn) {
                searchDropdown.style.display = 'none';
            }
        });
        
        // Prevent dropdown from closing when clicking inside it
        searchDropdown.addEventListener('click', function(e) {
            e.stopPropagation();
        });
    });
</script>
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
                <a href="homepageGuest" class="navbar-brand p-0">
                    <h1 class="text-primary mb-0"><i class="fab fa-slack me-2"></i> APTMANAGE</h1>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav mx-0 mx-lg-auto">
                        <a href="homepageGuest" class="nav-item nav-link <%= request.getRequestURI().contains("home.jsp") ? "active" : "" %>">Trang Chủ</a>
                        <a href="News" class="nav-item nav-link <%= request.getRequestURI().endsWith("/News") || request.getRequestURI().contains("/News") ? "active" : "" %>">Tin Tức</a>
                        <a href="serviceintro" class="nav-item nav-link <%= request.getRequestURI().contains("serviceintro") ? "active" : "" %>">Dịch Vụ</a>
                 <!--   dont touch-->
                        <button id="searchBtn" class="btn-search btn btn-primary btn-md-square rounded-circle flex-shrink-0">
                            <i class="fas fa-search"></i>
                        </button>
                        <div id="searchDropdown" class="search-dropdown position-absolute" style="display: none;">
                            <form action="SearchNewsGuest" method="get" class="p-2 bg-white rounded shadow">
                                <div class="input-group">
                                    <input type="search" name="search" class="form-control" placeholder="Search..." style="width: 200px;">
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </div>
                            </form>
                        </div>
                   <!--    dont touch-->
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
                                <i class="bi bi-box-arrow-in-right"></i>Đăng nhập
                            </a>
                        </c:if>

                        <c:if test="${sessionScope.user != null}">
                            <a class="btn btn-primary">
                                <i class="fa-solid fa-user"></i> ${sessionScope.user.name}
                            </a>    
                        </c:if>

                        <c:if test="${sessionScope.user ==null}">

                            <!--                            <a href="register.jsp" class="btn btn-primary">
                                                            <i class="fab fa-slack me-2"></i> Sign up
                                                        </a>-->
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
