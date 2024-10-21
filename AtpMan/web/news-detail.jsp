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
    <!-- Initialize TinyMCE -->
    <script src="https://cdn.tiny.cloud/1/n0b2uh23r0ya9qhhy07odsf6v4qhzjpn6aoav7c4rzx6ocd4/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>

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


        <!-- Comment Section Start -->
        <div class="row d-flex justify-content-center mt-100 mb-100">
            <div class="col-md-10">
                <div class="card">
                    <div class="card-body text-center">
                        <h4 class="card-title">Latest Comments</h4>
                    </div>
                    <div class="comment-widgets">
                        <c:if test="${not empty comments}">
                            <c:forEach var="comment" items="${comments}">
                                <div class="d-flex flex-row comment-row m-t-0 mt-3">
                                    <div class="p-2">
                                        <img src="data:image/png;base64,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" alt="user" width="50" class="rounded-circle"> 
                                    </div>
                                    <div class="comment-text w-100">
                                        <h6 class="font-medium">
                                            <c:choose>
                                                <c:when test="${not empty comment.customerName}">
                                                    ${comment.customerName}
                                                </c:when>
                                                <c:when test="${not empty comment.staffName}">
                                                    ${comment.staffName}
                                                </c:when>
                                                <c:otherwise>
                                                    Anonymous
                                                </c:otherwise>
                                            </c:choose>
                                        </h6>
                                        <span class="m-b-15 d-block">${comment.commentText}</span>
                                        <div class="comment-footer">
                                            <span class="text-muted float-right">
                                                <fmt:formatDate value="${comment.commentDate}" pattern="dd-MM-yyyy HH:mm" />
                                            </span>

                                            <c:set var="userRole" value="${sessionScope.userRole}" />
                                            <c:if test="${userRole == 'customer'}">
                                                <c:set var="sessionCustomer" value="${sessionScope.customer}" />
                                                <c:if test="${not empty sessionCustomer}"> <!-- Check if the user is logged in -->
                                                    <c:choose>
                                                        <c:when test="${ comment.customerID == sessionCustomer.customerID}">
                                                            <!-- Buttons for the customer who made the comment -->
                                                            <button type="button" class="btn btn-cyan btn-sm">Edit</button>
                                                            <button type="button" class="btn btn-danger btn-sm">Delete</button>
                                                        </c:when>

                                                    </c:choose>
                                                </c:if>
                                            </c:if>

                                            <c:if test="${userRole == 'staff'}">
                                                <c:set var="sessionStaff" value="${sessionScope.staff}" />
                                                <c:if test="${not empty sessionStaff}"> <!-- Check if the user is logged in -->
                                                    <c:choose>
                                                        <c:when test="${sessionStaff.staffID != null && comment.staffID == sessionStaff.staffID}">
                                                            <!-- Buttons for the customer who made the comment -->
                                                            <button type="button" class="btn btn-cyan btn-sm">Edit</button>
                                                            <button type="button" class="btn btn-danger btn-sm">Delete</button>
                                                        </c:when>
                                                        <c:when test="${userRole == 'staff'}">
                                                            <!-- Staff can delete comments made by others -->
                                                            <button type="button" class="btn btn-danger btn-sm">Delete</button>
                                                        </c:when>
                                                    </c:choose>
                                                </c:if>  
                                            </c:if>


                                        </div>
                                    </div>
                                </div> <!-- Comment Row -->
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty comments}">
                            <p class="text-center">No comments available for this news item.</p>
                        </c:if>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title">Post a Comment:</h4>
                        <c:if test="${not empty userRole}">
                            <form action="NewsDetail" method="post">
                                <input type="hidden" name="newsID" value="${param.id}"/>

                                <c:if test="${userRole == 'customer'}">
                                    <div class="form-group">
                                        <input type="hidden" name="customerID" value="${sessionCustomer.customerID}"/>
                                    </div>
                                </c:if>

                                <c:if test="${userRole == 'staff'}">
                                    <div class="form-group">
                                        <input type="hidden" name="staffID" value="${sessionStaff.staffID}"/>
                                    </div>
                                </c:if>

                                <div class="form-group">
                                    <label for="commentText">Comment Text:</label>
                                    <textarea id="commentText" name="commentText" class="form-control" rows="10" ></textarea>

                                </div>
                               
                                <button type="submit" class="btn btn-primary">Submit Comment</button>
                            </form>
                        </c:if>
                    </div>
                </div> <!-- Card -->
            </div>
        </div>
        <!-- Comment Section End -->



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
        <script>
            tinymce.init({
                selector: '#commentText',
                height: 300,
                plugins: 'lists link image code',
                toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright | bullist numlist outdent indent | link image',
            });
        </script>
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
            $(document).ready(function () {
                $('#news-content img').each(function () {
                    $(this).attr({
                        'data-bs-toggle': 'modal',
                        'data-bs-target': '#mediaModal'
                    });
                });
            });
        </script>
    </body>

</html>
