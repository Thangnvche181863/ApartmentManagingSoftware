<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Chi Tiết Dịch Vụ</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="keywords" />
        <meta content="" name="description" />
        
        <!-- Google Web Fonts -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"><!-- comment -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Inter:slnt,wght@-10..0,100..900&display=swap"
            rel="stylesheet"
            />

        <!-- Icon Font Stylesheet -->
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
            />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
            rel="stylesheet"
            />

        <!-- Libraries Stylesheet -->
        <link rel="stylesheet" href="lib/animate/animate.min.css" />
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet" />
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet" />

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet" />
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <!-- Header Start -->
        <div class="container-fluid carousel slide" id="carouselExampleInterval" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="bg-breadcrumb carousel-item first active" data-bs-interval="5000">
                    <div class="container text-center py-5" style="max-width: 900px">
                        <h4 class="text-white display-4 mb-4 wow fadeInDown" data-wow-delay="0.2s">
                            Chi Tiết Dịch Vụ
                        </h4>
                        <ol class="breadcrumb d-flex justify-content-center mb-0 wow fadeInDown" data-wow-delay="0.3s">
                            <li class="breadcrumb-item"><a href="home.jsp">Nhà</a></li>
                            <li class="breadcrumb-item"><a href="#">Quản lí</a></li>
                            <li class="breadcrumb-item active text-primary">Chi Tiết Dịch Vụ</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header End -->

        <!-- Service Detail Section Start -->
        <div class="container-fluid py-5">
            <div class="container">
                <div class="row g-5">
                    <!-- Service Image -->
                    <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.2s">
                        <img class="img-fluid rounded" src="${service.img}" alt="${service.name}" style="height: 500px;width: 600px" />
                    </div>

                    <!-- Service Details -->
                    <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.3s">
                        <div class="d-flex justify-content-between">
                            <h2 class="mb-4 text-primary">${service.name}</h2>
                            <a href="serviceintro"><button class="btn-primary btn" style="height: 40px">Quay trở lại</button></a>
                        </div>
                        <p class="mb-4"><strong><span style="color: #22EE5B">Loại dịch vụ:  </span></strong> ${service.type}</p>
                        <p class="mb-4"  style="color: #D80000"><strong><span style="color: #22EE5B">Giá:  </span></strong><fmt:formatNumber  value="${service.fee}" /> VND</p>
                        <div class="feature-icon p-4 mb-4">
                            <i class="${service.icon}" style="font-size: 3rem; color: #015DC5"></i>
                        </div>
                        <p class="mb-4 " style="color: black">${service.description}</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Service Detail Section End -->

        <!-- Footer Start -->
        <jsp:include page="footer.jsp"/>
        <!-- Footer End -->

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
