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


    </style>
    <style>
        .carousel-item {
            display: flex; /* Use flexbox for centering */
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            height: 100%; /* Fill the modal body height */
        }

        .carousel-item img,
        .carousel-item video {
            max-width: 100%; /* Ensure it doesn't exceed the modal width */
            max-height: 100%; /* Ensure it doesn't exceed the modal height */
            width: auto; /* Auto width */
            height: auto; /* Auto height */
            object-fit: contain; /* Maintain aspect ratio without cropping */
        }

        /* Optional: Style for the modal to prevent resizing */
        .modal-lg {
            max-width: 800px; /* Set a maximum width for the modal */
            width: 100%; /* Full width for smaller screens */
        }
    </style>
    <style>
        /* Style for images */
        .media-content img {
            max-width: 100%;
            max-height: 100%;
            width: auto;
            height: auto;
            object-fit: contain;
        }
        /* Style for videos */
        .media-content video {
            max-width: 100%;
            max-height: 100%;
            width: auto;
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
                                    <strong>Thời gian:</strong>
                                    <fmt:formatDate value="${news.postDate}" pattern="EEEE dd/MM/yyyy HH:mm" />
                                    <br>
                                    <strong>Loại:</strong>
                                    <a href="#" class="text-primary">${news.newsCategoryName}</a> 
                                    <br>
                                    <strong>Tác giả: </strong>
                                    <a>${news.staffName}</a>
                                </small> 
                            </div>

                            <!-- News Content -->
                            <div id="news-content">
                                <c:out value="${news.newsContent}" escapeXml="false" />
                            </div>


                            <!-- Back Button -->
                            <a href="News" class="btn btn-primary">Danh sách tin tức</a>
                            <!-- Modal Trigger -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#mediaModal">
                                Xem ảnh
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
                        <h4 class="card-title">Bình luận mới nhất</h4>
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
                                                            <a href="CommentDelete?id=${comment.commentID}" class="btn btn-danger btn-sm" 
                                                               onclick="return confirmDelete();">Delete</a>
                                                            <a href="ReportComment?id=${comment.commentID}" class="btn btn-outline-danger btn-sm" 
                                                               onclick="return confirmReport();">Report</a>

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
                                                            <a href="CommentDelete?id=${comment.commentID}" class="btn btn-danger btn-sm" 
                                                               onclick="return confirmDelete();">Delete</a>
                                                            <a href="ReportComment?id=${comment.commentID}" class="btn btn-outline-danger btn-sm" 
                                                               onclick="return confirmReport();"  >Report</a>

                                                        </c:when>
                                                        <c:when test="${userRole == 'staff'}">
                                                            <!-- Staff can delete comments made by others -->
                                                            <a href="CommentDelete?id=${comment.commentID}" class="btn btn-danger btn-sm" 
                                                               onclick="return confirmDelete();">Delete</a>
                                                            <a href="ReportComment?id=${comment.commentID}" class="btn btn-outline-danger btn-sm" 
                                                               onclick="return confirmReport();"  >Report</a>
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
                        <h4 class="card-title">Đăng bình luận:</h4>
                        <c:if test="${not empty userRole}">
                            <form action="NewsDetail" method="post">
                                <input type="hidden" name="newsID" value="${param.id}"/>

                                <c:if test="${userRole == 'customer'}">
                                    <div class="form-group">
                                        <input type="hidden" name="customerID" value="${customer.customerID}"/>
                                    </div>
                                </c:if> 

                                <c:if test="${userRole == 'staff'}">
                                    <div class="form-group">
                                        <input type="hidden" name="staffID" value="${staff.staffID}"/>
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
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="mediaModalLabel">Media Gallery</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" style="height: 500px;">
                        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" style="height: 100%;">
                            <div class="carousel-inner" style="height: 100%;">
                                <c:forEach var="mediaData" items="${mediaDataList}" varStatus="status">
                                    <div class="carousel-item ${status.first ? 'active' : ''}" style="height: 100%;">
                                        <div class="media-content" style="text-align: center; height: 90%; display: flex; align-items: center; justify-content: center;">
                                            <!-- Wrap the mediaTag in a div and apply styles -->
                                            <div style="max-width: 100%; height: 100%; display: flex; align-items: center;">
                                                ${mediaData.mediaTag}

                                            </div>
                                        </div>
                                        <c:if test="${not empty mediaData.altText}">
                                            <div class="media-text" style="color: black; text-align: center; margin-top: 10px;">
                                                <p>${mediaData.altText}</p>
                                            </div>
                                        </c:if>
                                    </div>
                                </c:forEach>
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
        <script>
            function confirmDelete() {
                return confirm("Are you sure you want to delete this comment?");
            }
        </script>

        <script>
            function confirmReport() {
                return confirm("Are you sure you want to report this comment?");
            }
        </script>
        <script>
            // Function to retrieve the value of a query parameter
            function getQueryParam(name) {
                const urlParams = new URLSearchParams(window.location.search);
                return urlParams.get(name);
            }

            // Check if 'ReportMessage' parameter exists and show alert if it does
            function showReportMessage() {
                const reportMessage = getQueryParam('ReportMessage');
                if (reportMessage) {
                    alert(decodeURIComponent(reportMessage));
                }
            }

            // Run the function when the page loads
            window.onload = showReportMessage;
        </script>
    </body>

</html>
