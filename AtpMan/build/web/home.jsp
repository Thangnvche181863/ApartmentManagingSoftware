<%-- 
    Document   : Home
    Created on : Sep 11, 2024, 10:32:13 PM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <head>
        <meta charset="utf-8">
        <title>AptManage - Apartment Management </title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Inter:slnt,wght@-10..0,100..900&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link rel="stylesheet" href="lib/animate/animate.min.css"/>
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            .blog-item {
                height: 100%;
                display: flex;
                flex-direction: column;
                background: #fff;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                margin-bottom: 30px;
            }

            .blog-img {
                position: relative;
                padding-top: 66.67%; /* 3:2 aspect ratio */
                overflow: hidden;
            }

            .blog-img img {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                object-fit: cover;
            }

            .blog-content {
                flex: 1;
                display: flex;
                flex-direction: column;
                height: 100%;
            }

            .blog-content h4 {
                display: -webkit-box;
                -webkit-line-clamp: 2;
                -webkit-box-orient: vertical;
                overflow: hidden;
                min-height: 48px;
            }

            .blog-content p {
                display: -webkit-box;
                -webkit-line-clamp: 3;
                -webkit-box-orient: vertical;
                overflow: hidden;
                flex: 1;
            }
        </style>
        <style>
            .team-item {
                display: flex;
                flex-direction: column;
                height: 100%;
                background-color: #fff; /* Adjust as needed */
                border-radius: 0.25rem;
            }

            .team-title {
                flex: 1;
            }

            .team-img img {
                width: 100%;
                height: 300px;
                object-fit: cover;
            }
        </style>
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>

            <!-- Modal Search Start -->
            <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-fullscreen">
                    <div class="modal-content rounded-0">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body d-flex align-items-center bg-primary">
                            <div class="input-group w-75 mx-auto d-flex">
                                <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                                <span id="search-icon-1" class="btn bg-light border nput-group-text p-3"><i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal Search End -->







            <!-- Carousel Start -->
            <div class="header-carousel owl-carousel">
            <c:forEach items="${banner}" var="banner" varStatus="status">
                <div class="header-carousel-item bg-primary">
                    <div class="carousel-caption">
                        <div class="container">
                            <div class="row g-4 align-items-center">
                                <div class="col-lg-7 animated fadeInLeft">
                                    <div class="text-sm-center text-md-start">
                                        <h4 class="text-white text-uppercase fw-bold mb-4">Welcome To AtpMan</h4>
                                        <h1 class="display-1 text-white mb-4">${banner.newsTitle}</h1>
                                        <p class="mb-5 fs-5">${banner.description}</p>
                                    </div>
                                </div>
                                <div class="col-lg-5 animated fadeInRight">
                                    <div class="carousel-img" style="object-fit: cover;">
                                        <img src="${banner.newsImg}" class="img-fluid w-100" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- Carousel End -->






        <!-- Feature Start -->
        <div class="container-fluid feature bg-light py-5">
            <div class="container py-5">
                <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.2s" style="max-width: 800px;">
                    <h4 class="text-primary">Our Features</h4>
                    <h1 class="display-8 mb-4">Providing comprehensive solutions</h1>
                    <p class="mb-0">Free for residents. No complicated installation required. Optimized to be user-friendly for all users. Fast, superior information transmission capabilities, Easy community connection.
                    </p>
                </div>
                <!-- feature for management -->
                <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.2s" style="max-width: 800px;">
                    <h1 class="display-8 mb-4">For management</h1>
                </div>
                <div class="row g-4">
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.2s">
                        <div class="feature-item p-4 pt-0" style="height: 360px;">
                            <div class="feature-icon p-4 mb-4">
                                <i class="far fa-handshake fa-3x"></i>
                            </div>
                            <h5 class="mb-4">Information Management</h5>
                            <p class="mb-4">General information about the apartment, apartment status, staff
                            </p>
                            <br>
                            <a class="btn btn-primary rounded-pill py-2 px-4 " href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.4s">
                        <div class="feature-item p-4 pt-0" style="height: 360px;">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-dollar-sign fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Cost Management</h4>
                            <p class="mb-4">Include basic fee and able to create more specific fee, detailed information on cost types
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.6s">
                        <div class="feature-item p-4 pt-0" style="height: 360px;">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-bullseye fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Flexible Plans</h4>
                            <p class="mb-4">Create tasks and assign tasks to employees, sending support easily
                            </p>
                            <br>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.8s">
                        <div class="feature-item p-4 pt-0" style="height: 360px;">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-headphones fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Manage Feedback</h4>
                            <p class="mb-4">Manage citizen requests and complaints and process requests quickly through task assignment
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                </div>
                <br>
                <!-- feature for residents -->
                <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.2s" style="max-width: 800px;">
                    <h1 class="display-8 mb-4">For residents</h1>
                </div>
                <div class="row g-4">
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.2s">
                        <div class="feature-item p-4 pt-0" style="height: 360px;">
                            <div class="feature-icon p-4 mb-4">
                                <i class="far fa-handshake fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Updated News</h4>
                            <p class="mb-4">Update the latest information about announcements, services, management, and events of the apartment building
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.4s">
                        <div class="feature-item p-4 pt-0" style="height: 360px;">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-dollar-sign fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Cost Lookup</h4>
                            <p class="mb-4">View monthly expenses, service charges, incidental charges, and other fees
                            </p>
                            <br>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.6s">
                        <div class="feature-item p-4 pt-0" style="height: 360px;">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-bullseye fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Sign up for services and amenities</h4>
                            <p class="mb-4">Register for external services, advanced services, use premium utilities
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.8s">
                        <div class="feature-item p-4 pt-0" style="height: 360px;">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-headphones fa-3x"></i>
                            </div>
                            <h4 class="mb-4">24/7 Fast Support</h4>
                            <p class="mb-4">Submit support/complaints, get support in a short time, clear information and costs
                            </p>
                            <br>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Feature End -->






        <!-- Team Start -->
        <div class="container-fluid team pb-5">
            <div class="container pb-5">
                <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.2s" style="max-width: 800px;">
                    <h4 class="text-primary">Our Team</h4>
                    <h1 class="display-4 mb-4">Meet Our Expert Team Members</h1>
                    <p class="mb-0"></p>
                </div>
                <div class="row g-4">
                    <c:forEach items="${teamMembers}" var="mem">
                        <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.2s">
                            <div class="team-item h-100">
                                <div class="team-img position-relative" style="height: 300px;">
                                    <img src="${mem.newsImg}" class="img-fluid rounded-top w-100 h-100" alt="" style="object-fit: cover;">
                                    <div class="team-icon">
                                        <a class="btn btn-primary btn-sm-square rounded-pill mb-2" href="NewsDetail?id=${mem.newsID}">
                                            <i class="fas fa-question-circle"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="team-title p-4">
                                    <h4 class="mb-0">${mem.newsTitle}</h4>
                                    <p class="mb-0">${mem.description}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>


            </div>
        </div>
        <!-- Team End -->

        <!-- New category Start -->
        <div class="container-fluid blog py-5">
            <div class="container py-5">
                <c:forEach items="${categories}" var="category">
                    <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.2s" style="max-width: 800px;">
                        <h1 class="display-4 mb-4">${category.name}</h1>
                        <p class="mb-0">${category.description}</p>
                    </div>
                    <div class="row g-4 justify-content-center">
                        <c:forEach items="${category.newsList}" var="newsItem">
                            <div class="col-lg-6 col-xl-4 wow fadeInUp" data-wow-delay="0.2s">
                                <div class="blog-item">
                                    <div class="blog-img">
                                        <img src="${newsItem.newsImg}" class="img-fluid rounded-top" alt="">
                                    </div>
                                    <div class="blog-content p-4">
                                        <div class="blog-comment d-flex justify-content-between mb-3">
                                            <div class="small"><span class="fa fa-user text-primary"></span> ${newsItem.staffName}</div>
                                            <div class="small">
                                                <span class="fa fa-calendar text-primary"></span> 
                                                <fmt:formatDate value="${newsItem.postDate}" pattern="dd-MM-yyy HH:mm:ss" />
                                            </div>
                                        </div>
                                        <a href="NewsDetail?id=${newsItem.newsID}" class="h4 d-inline-block mb-3">${newsItem.newsTitle}</a>
                                        <p class="mb-3">${newsItem.description}</p>
                                        <a href="NewsDetail?id=${newsItem.newsID}" class="btn p-0 mt-auto">Read More <i class="fa fa-arrow-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- New category End -->


        <!-- Footer Start -->
        <jsp:include page="footer.jsp"/>
        <!-- Footer End -->



        <!-- Back to Top -->
        <a href="#" class="btn btn-primary btn-lg-square rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   


        <!-- JavaScript Libraries -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/counterup/counterup.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>


        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>
