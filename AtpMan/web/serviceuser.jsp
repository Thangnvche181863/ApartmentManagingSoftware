<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>LifeSure - Life Insurance Website Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <meta content="" name="keywords" />
        <meta content="" name="description" />
        <!-- Google Web Fonts -->
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

        <!--  >
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap JS (requires Popper.js as well) -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>


    </head>

    <body>

        <jsp:include page="header.jsp"></jsp:include>

            <!-- Modal Search Start -->

            <!-- Modal Search End -->

            <!-- Header Start -->
            <div class="container-fluid carousel slide" id="carouselExampleInterval" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="bg-breadcrumb carousel-item first active" data-bs-interval="5000">
                        <div class="container text-center py-5" style="max-width: 900px">
                            <h4
                                class="text-white display-4 mb-4 wow fadeInDown"
                                data-wow-delay="0.2s"
                                >
                                Dịch vụ
                            </h4>
                            <ol
                                class="breadcrumb d-flex justify-content-center mb-0 wow fadeInDown"
                                data-wow-delay="0.3s"
                                >
                                <li class="breadcrumb-item"><a href="home.jsp">Nhà</a></li>
                                <li class="breadcrumb-item"><a href="#">Quản lí</a></li>
                                <li class="breadcrumb-item active text-primary">Dịch vụ</li>
                            </ol>
                        </div>
                    </div>
                    <div class="bg-breadcrumb carousel-item second" data-bs-interval="5000">
                        <div class="container text-center py-5" style="max-width: 900px">
                            <h4
                                class="text-white display-4 mb-4 wow fadeInDown"
                                data-wow-delay="0.2s"
                                >
                                Chúc mừng giáng sinh
                            </h4>
                            <ol
                                class="breadcrumb d-flex justify-content-center mb-0 wow fadeInDown"
                                data-wow-delay="0.3s"
                                >
                                <li class="breadcrumb-item"><a href="home.jsp">Home</a></li>
                                <li class="breadcrumb-item"><a href="#">Manage</a></li>
                                <li class="breadcrumb-item active text-primary">Service</li>
                            </ol>
                        </div>
                    </div>
                    <div class="bg-breadcrumb carousel-item third" data-bs-interval="5000">
                        <div class="container text-center py-5" style="max-width: 900px">
                            <h4
                                class="text-white display-4 mb-4 wow fadeInDown"
                                data-wow-delay="0.2s"
                                >
                                Ảnh hưởng bởi bão
                            </h4>
                            <ol
                                class="breadcrumb d-flex justify-content-center mb-0 wow fadeInDown"
                                data-wow-delay="0.3s"
                                >
                                <li class="breadcrumb-item"><a href="home.jsp">Home</a></li>
                                <li class="breadcrumb-item"><a href="#">Manage</a></li>
                                <li class="breadcrumb-item active text-primary">Service</li>
                            </ol>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
            <!-- Header End -->




            <!-- Service Start -->
<!--             <div class="container-fluid service py-5">
                <div class="container py-5">
                    <div
                        class="text-center mx-auto pb-5 wow fadeInUp"
                        data-wow-delay="0.2s"
                        style="max-width: 800px"
                        >
                        <h4 class="text-primary">Căn hộ của chúng tôi có gì ?</h4>
                    </div>




                   <div class="row g-4 justify-content-center">
                        <div
                            class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp"
                            data-wow-delay="0.2s"
                            >
                            <div class="service-item">
                                <div class="service-img">
                                    <img
                                        src="img/blog-1.png"
                                        class="img-fluid rounded-top w-100"
                                        alt=""
                                        />
                                    <div class="service-icon p-3">
                                        <i class="fa fa-car fa-2x"></i>
                                    </div>
                                </div>
                                <div class="service-content p-4">
                                    <div class="service-content-inner">
                                        <a href="#" class="d-inline-block h4 mb-4">Car Service</a>
                                        <p class="mb-4">
                                            Our parking service is secured by smart monitoring equipment to make you satisfied and safe.
                                        </p>
                                        <a class="btn btn-primary rounded-pill py-2 px-4" href="#"
                                           >Read More</a
                                        >
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div
                            class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp"
                            data-wow-delay="0.4s"
                            >
                            <div class="service-item">
                                <div class="service-img">
                                    <img
                                        src="img/blog-2.png"
                                        class="img-fluid rounded-top w-100"
                                        alt=""
                                        />
                                    <div class="service-icon p-3">
                                        <i class="bi bi-gear-fill"></i>
                                    </div>
                                </div>
                                <div class="service-content p-4">
                                    <div class="service-content-inner">
                                        <a href="#" class="d-inline-block h4 mb-4"
                                           >Finance manager</a
                                        >
                                        <p class="mb-4">
                                            Apartment management services as well as monthly payment of additional fees.
                                        </p>
                                        <a class="btn btn-primary rounded-pill py-2 px-4" href="#"
                                           >Read More</a
                                        >
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div
                            class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp"
                            data-wow-delay="0.6s"
                            >
                            <div class="service-item">
                                <div class="service-img">
                                    <img
                                        src="img/blog-3.png"
                                        class="img-fluid rounded-top w-100"
                                        alt=""
                                        />
                                    <div class="service-icon p-3">
                                        <i class="bi bi-shield-fill"></i>
                                    </div>
                                </div>
                                <div class="service-content p-4">
                                    <div class="service-content-inner">
                                        <a href="#" class="d-inline-block h4 mb-4">Security Service</a>
                                        <p class="mb-4">
                                            We always have supervisors on duty 24/7 to ensure security and order
                                        </p>
                                        <a class="btn btn-primary rounded-pill py-2 px-4" href="#"
                                           >Read More</a
                                        >
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div
                            class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp"
                            data-wow-delay="0.8s"
                            >
                            <div class="service-item">
                                <div class="service-img">
                                    <img
                                        src="img/blog-4.png"
                                        class="img-fluid rounded-top w-100"
                                        alt=""
                                        />
                                    <div class="service-icon p-3">
                                        <i class="fa fa-home fa-2x"></i>
                                    </div>
                                </div>
                                <div class="service-content p-4">
                                    <div class="service-content-inner">
                                        <a href="#" class="d-inline-block h4 mb-4">Environment Services</a>
                                        <p class="mb-4">
                                            With a team of environmental workers working daily will help maintain a clean campus
                                        </p>
                                        <a class="btn btn-primary rounded-pill py-2 px-4" href="#"
                                           >Read More</a
                                        >
                                    </div>
                                </div>
                            </div>
                        </div>




                        <div class="col-12 text-center wow fadeInUp" data-wow-delay="0.2s">
                            <a class="btn btn-primary rounded-pill py-3 px-5" href="#navigate"
                               >More Services</a
                            >
                        </div>
                    </div>
                </div>-->





                <div class="container-fluid feature bg-light pb-5" id="navigate">
                    <div class="container py-5">
                        <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.2s" style="max-width: 800px;">
                            <h2 class="text-primary">Giới thiệu dịch vụ</h2>
<!--                            <p class="mb-0">Extension services are services provided by third parties, by reputable and experienced service providers. We guarantee that you will be satisfied when using the service.
                            </p>-->
                        </div>



                        <!--part 1-->
                        <div class="row g-4">
                        <c:forEach items="${list}" var="ls">
                            <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.2s">
                                <div class="feature-item p-4 pt-0">
                                    <div class="feature-icon p-4 mb-4">
                                        <i class="${ls.icon}" style="font-size:  2rem;"></i>
                                    </div>
                                    <h4 class="mb-4 ">${ls.name}</h4>
                                    <p class="mb-4 short-text" id="textContent">${ls.description}
                                    </p>
                                    <!--toggleBtn-->
                                    <a class="btn btn-primary rounded-pill py-2 px-4 " href="serviceuserdetail?id=${ls.serviceId}">Chi tiết</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
<!--                <div class="col-12 text-center wow fadeInUp" data-wow-delay="0.2s">
                    <a class="btn btn-primary rounded-pill py-3 px-5" href="#navigate1"
                       >More Services</a
                    >
                </div>-->
            </div>
        <!--</div>-->
        <!-- Service End -->

        <!-- Testimonial Start -->
<!--        <div class="container-fluid feature1 py-5"  id="navigate1">
            <div class="container py-5">
                <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.2s" style="max-width: 800px;">
                    <h1 class="display-4 mb-4">Unregistered Service</h1>
                    <p class="mb-0">Below are the services that you have not registered for, you can refer to them for more information.
                    </p>
                </div>



                part 1
                <div class="row g-4">
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp " data-wow-delay="0.2s">
                        <div class="feature-item1 p-4 pt-0 bg-light">
                            <div class="feature-icon1 p-4 mb-4">
                                <i class="bi bi-building-add" style="font-size:  2rem; "></i>
                            </div>
                            <h4 class="mb-4">Public Facilities</h4>
                            <p class="mb-4">Services for using swimming pool, gym, park, soccer field, multi-purpose house...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>-->
        <!-- Testimonial End -->



        <!-- Footer Start -->
        <jsp:include page="footer.jsp"/>
        <!-- Footer End -->

        <!-- Copyright End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-primary btn-lg-square rounded-circle back-to-top"
           ><i class="fa fa-arrow-up"></i
            ></a>

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
