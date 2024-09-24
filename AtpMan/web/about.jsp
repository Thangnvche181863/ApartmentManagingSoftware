<%-- 
    Document   : newjsp
    Created on : Sep 11, 2024, 9:39:14 PM
    Author     : ADMIN
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

        <!-- News Carousel -->
<div class="row justify-content-center">
    <div class="col-md-10">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <c:forEach var="news" items="${newsList}" varStatus="status">
                    <li data-target="#carouselExampleIndicators" data-slide-to="${status.index}" class="${status.first ? 'active' : ''}"></li>
                </c:forEach>
            </ol>
            <div class="carousel-inner">
                <c:forEach var="banner" items="${newsBanner}" varStatus="status">
                    <div class="carousel-item ${status.first ? 'active' : ''}">
                        <a href="<c:out value='NewsDetail?id=${banner.newsID}'/>">   
                            <img class="d-block w-100" src="<c:out value='${banner.newsImg}'/>" alt="Slide ${status.index + 1}">
                            <div class="carousel-caption d-none d-md-block">
                                <h5 style="color:#FFFF00;"><c:out value='${banner.newsTitle}'/></h5>
                                <p style="color:#FFFF00;"><c:out value='${banner.newsContent}'/></p>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
</div>
<!-- News Carousel End -->


        <!-- Recent News Section Start -->
        <section    class="section bg-light py-5">
            <div class="container">
                <div class="row">
                    <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                        <div class="page-wrapper">
                            <div class="blog-top clearfix">
                                <h4 class="pull-left">Recent News <a href="#"><i class="fa fa-rss"></i></a></h4>
                            </div><!-- end blog-top -->

                            <div class="blog-list clearfix">
                                <!-- Loop through the news list -->
                                <c:forEach  items="${news}" var="newsItem">
                                    <div class="blog-box row">
                                        <div class="col-md-4">
                                            <div class="post-media">
                                                <a href="NewsDetail?id=${newsItem.newsID}" title="">
                                                    <img src="${newsItem.newsImg}" alt="" class="img-fluid">
                                                    <div class="hovereffect"></div>
                                                </a>
                                            </div><!-- end media -->
                                        </div><!-- end col -->
                                        
                                        <div class="blog-meta big-meta col-md-8">
                                            <h4><a href="NewsDetail?id=${newsItem.newsID}" title="">
                                                    ${newsItem.newsTitle}
                                                </a></h4>
                                            <p>${newsItem.newsContent}</p>
                                            <small class="firstsmall"><a class="bg-orange" href="#" title="">${newsItem.newsCategoryName} - </a></small>
                                            <small>
                                                <fmt:formatDate value="${newsItem.postDate}" pattern="EEEE dd/MM/yyyy HH:mm" />
                                            </small>
                                            <small>  by ${newsItem.staffName}</small>
                                        </div><!-- end meta -->
                                    </div><!-- end blog-box -->

                                    <hr class="invis">
                                </c:forEach>
                            </div><!-- end blog-list -->
                        </div><!-- end page-wrapper -->

                        <!-- Pagination -->
                        <div class="row">
                            <div class="col-md-12">
                                <nav aria-label="Page navigation">
                                    <ul class="pagination justify-content-start">
                                        <c:if test="${currentPage > 1}">
                                            <li class="page-item">
                                                <a class="page-link" href="News?page=${currentPage - 1}">Previous</a>
                                            </li>
                                        </c:if>

                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                <a class="page-link" href="News?page=${i}">${i}</a>
                                            </li>
                                        </c:forEach>

                                        <c:if test="${currentPage < totalPages}">
                                            <li class="page-item">
                                                <a class="page-link" href="News?page=${currentPage + 1}">Next</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </div><!-- end col -->
                        </div><!-- end row -->
                    </div><!-- end col -->
                </div><!-- end row -->
            </div><!-- end container -->
        </section>
        <!-- Recent News Section End -->





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
    </body>

</html>
