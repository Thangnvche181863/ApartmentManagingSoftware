<%-- 
    Document   : news-detail
    Created on : Sep 21, 2024, 5:55:35 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <style>
        #carouselExampleIndicators .carousel-item img {
            max-height: 500px; /* Adjust the maximum height as needed */
            width: auto;
            margin: auto;
        }

        /* Styling for the gallery images */
        .gallery-image {
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .gallery-image:hover {
            transform: scale(1.1);
        }

        .modal-content img, .modal-content video {
            max-width: 100%;
            height: auto;
        }
    </style>

    <head>
        <meta charset="utf-8">
        <title>AptMan - News</title>
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
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>

        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar & Hero Start -->
        <div class="container-fluid nav-bar px-0 px-lg-4 py-lg-0">
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
                                <a href="home.jsp" class="nav-item nav-link">Home</a>
                                <a href="News" class="nav-item nav-link">News</a>
                                <a href="service.jsp" class="nav-item nav-link">Services</a>
                                <a href="feedback.jsp" class="nav-item nav-link">Feedback</a>
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
                                <a href="login.jsp" class="btn btn-primary">
                                    <i class="bi bi-box-arrow-in-right"></i> Login
                                </a>

                                <a href="register.jsp" class="btn btn-primary">
                                    <i class="fab fa-slack me-2"></i> Sign Up
                                </a>
                            </div>

                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Navbar & Hero End -->

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

        <!-- News Detail Section Start -->
        <div class="container my-5">
            <div class="row">
                <div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
                    <div class="card">
                        <div class="card-body">
                            <!-- News Image Thumbnail -->
                            <div class="text-center mb-4">
                                <img src="${news.newsImg}" class="img-fluid rounded" alt="News Image" style="max-height: 400px;">
                            </div>

                            <!-- News Title -->
                            <h2 class="card-title">${news.newsTitle}</h2>

                            <!-- News Metadata (Post Date, Category) -->
                            <div class="text-muted mb-3">
                                <small>
                                    <strong>Posted on:</strong>
                                    <fmt:formatDate value="${news.postDate}" pattern="EEEE dd/MM/yyyy HH:mm" />
                                    <br>
                                    <strong>Category:</strong>
                                    <a href="#" class="text-primary">${news.newsCategoryName}</a> 
                                    <br>
                                    <strong>Posted by: </strong>
                                    <a>${news.staffName}</a>
                                </small> 
                            </div>

                            <!-- News Content -->
                            <div id="news-content">
                                <c:out value="${news.newsContent}" escapeXml="false" />
                            </div>


                            <!-- Back Button -->
                            <a href="News" class="btn btn-primary">Back to News List</a>
                            <!-- Modal Trigger -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#mediaModal">
                                View Media
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- News Detail Section End -->

        <!-- Modal to display media -->
        <div class="modal fade" id="mediaModal" tabindex="-1" aria-labelledby="mediaModalLabel" aria-hidden="true">
            <div class="modal-dialog custom-modal-size"> <!-- Custom class for size -->
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="mediaModalLabel">Media Gallery</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="carousel-container" style="width: 100%; height: 500px;">
                            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" style="height: 100%;">
                                <div class="carousel-inner" style="height: 100%;">
                                    ${mediaTags}
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <style>
            .custom-modal-size {
                max-width: 800px; /* Set your desired width */
                width: 100%; /* Full width on small screens */
            }

            .carousel-inner {
                height: 100%; /* Keep this if you want full height */
            }

            .carousel-item {
                height: 100%;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .carousel-item img,
            .carousel-item video {
                width: auto;
                height: 100%;
                object-fit: contain;
            }
        </style>     


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary btn-lg-square rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   




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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    // Using jQuery to add modal trigger attributes to images in the news content
    $(document).ready(function() {
        $('#news-content img').each(function() {
            $(this).attr({
                'data-bs-toggle': 'modal',
                'data-bs-target': '#mediaModal'
            });
        });
    });
</script>
    </body>

</html>
