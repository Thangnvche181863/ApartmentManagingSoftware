<%-- 
    Document   : searchnewsGuest
    Created on : Oct 30, 2024, 5:53:02 PM
    Author     : PC
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











            <!-- New category Start -->
            <div class="container-fluid blog py-5">
                <div class="container py-5">
                    <div class="row g-4 justify-content-center">
                        <h1>Search Results</h1>
                    <c:if test="${not empty message}">
                        <p>${message}</p> 
                    </c:if>
                    <c:forEach items="${news}" var="newsItem">
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
            </div>
        </div>


        <!-- Pagination -->
        <div class="row justify-content-center">
            <div class="col-md-12">
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center"> <!-- Changed to justify-content-center -->
                       
                     
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="SearchNewsGuest?page=${currentPage - 1}&search=${param.search}">Previous</a>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="SearchNewsGuest?page=${i}&search=${param.search}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="SearchNewsGuest?page=${currentPage + 1}&search=${param.search}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>

        <!-- New category End -->
      
        <!-- Footer Start -->
        <jsp:include page="footer.jsp"/>
        <!-- Footer End -->



        <!-- Back to Top -->
        <!--        <a href="#" class="btn btn-primary btn-lg-square rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>   -->


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
