<%-- 
    Document   : feedback
    Created on : Sep 13, 2024, 3:47:05 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>


    <head>
        <meta charset="utf-8">
        <title>LifeSure - Life Insurance Website Template</title>
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

        <!-- Topbar Start -->
        <div class="container-fluid topbar px-0 px-lg-4 bg-light py-2 d-none d-lg-block">
            <div class="container">
                <div class="row gx-0 align-items-center">
                    <div class="col-lg-8 text-center text-lg-start mb-lg-0">
                        <div class="d-flex flex-wrap">
                            <div class="border-end border-primary pe-3">
                                <a href="#" class="text-muted small"><i class="fas fa-map-marker-alt text-primary me-2"></i>Find A Location</a>
                            </div>
                            <div class="ps-3">
                                <a href="mailto:example@gmail.com" class="text-muted small"><i class="fas fa-envelope text-primary me-2"></i>example@gmail.com</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 text-center text-lg-end">
                        <div class="d-flex justify-content-end">
                            <div class="d-flex border-end border-primary pe-3">
                                <a class="btn p-0 text-primary me-3" href="#"><i class="fab fa-facebook-f"></i></a>
                                <a class="btn p-0 text-primary me-3" href="#"><i class="fab fa-twitter"></i></a>
                                <a class="btn p-0 text-primary me-3" href="#"><i class="fab fa-instagram"></i></a>
                                <a class="btn p-0 text-primary me-0" href="#"><i class="fab fa-linkedin-in"></i></a>
                            </div>
                            <div class="dropdown ms-3">
                                <a href="#" class="dropdown-toggle text-dark" data-bs-toggle="dropdown"><small><i class="fas fa-globe-europe text-primary me-2"></i> English</small></a>
                                <div class="dropdown-menu rounded">
                                    <a href="#" class="dropdown-item">English</a>
                                    <a href="#" class="dropdown-item">Bangla</a>
                                    <a href="#" class="dropdown-item">French</a>
                                    <a href="#" class="dropdown-item">Spanish</a>
                                    <a href="#" class="dropdown-item">Arabic</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->

        <!-- Navbar & Hero Start -->
        <div class="container-fluid nav-bar px-0 px-lg-4 py-lg-0">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-light"> 
                    <a href="#" class="navbar-brand p-0">
                        <h1 class="text-primary mb-0"><i class="fab fa-slack me-2"></i> AptMan</h1>
                        <!-- <img src="img/logo.png" alt="Logo"> -->
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <div class="navbar-nav mx-0 mx-lg-auto">
                            <a href="index.html" class="nav-item nav-link">Home</a>
                            <a href="about.html" class="nav-item nav-link">About</a>
                            <a href="service.html" class="nav-item nav-link">Services</a>
                            <a href="blog.html" class="nav-item nav-link">Blog</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link" data-bs-toggle="dropdown">
                                    <span class="dropdown-toggle">Pages</span>
                                </a>
                                <div class="dropdown-menu">
                                    <a href="feature.html" class="dropdown-item">Our Features</a>
                                    <a href="team.html" class="dropdown-item">Our team</a>
                                    <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                                    <a href="FAQ.html" class="dropdown-item">FAQs</a>
                                    <a href="404.html" class="dropdown-item">404 Page</a>
                                </div>
                            </div>
                            <a href="contact.html" class="nav-item nav-link active">Feedback</a>
                            <div class="nav-btn px-3">
                                <button class="btn-search btn btn-primary btn-md-square rounded-circle flex-shrink-0" data-bs-toggle="modal" data-bs-target="#searchModal"><i class="fas fa-search"></i></button>
                                <a href="#" class="btn btn-primary rounded-pill py-2 px-4 ms-3 flex-shrink-0"> Get a Quote</a>
                            </div>
                        </div>
                    </div>
                    <div class="d-none d-xl-flex flex-shrink-0 ps-4">
                        <a href="#" class="btn btn-light btn-lg-square rounded-circle position-relative wow tada" data-wow-delay=".9s">
                            <i class="fa fa-sign-in-alt"></i>
                            <div class="position-absolute" style="top: 7px; right: 12px;">
                                <span><i class="fa fa-comment-dots text-secondary"></i></span>
                            </div>
                        </a>
                        <div class="d-flex flex-column ms-4 justify-content-center">
                            <span>Login</span>
                        </div>
                    </div>
                </nav>
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
        <!-- Modal Search End -->


        <!-- Header Start -->
        <div class="container-fluid bg-breadcrumb">
            <div class="container text-center py-5" style="max-width: 900px;">
                <h4 class="text-white display-4 mb-4 wow fadeInDown" data-wow-delay="0.1s">Feedback to us</h4>
                <ol class="breadcrumb d-flex justify-content-center mb-0 wow fadeInDown" data-wow-delay="0.3s">
                    <li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Pages</a></li>
                    <li class="breadcrumb-item active text-primary">Feedback</li>
                </ol>    
            </div>
        </div>
        <!-- Header End -->


        <!-- Contact Start -->
        <div class="container-fluid contact bg-light py-5">
            <div class="container py-5">
                <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.2s" style="max-width: 800px;">
                    <h4 class="text-primary">Feedback to us</h4>
                    <h1 class="display-4 mb-4">If you have any service feedback, send it here.</h1>
                    <div class="contact-icon text-primary mb-4">
                        <i class="fas fa-smile fa-2x"></i>
                    </div>
                </div>
                <div class="row g-5">
                    <div class="col-xl-6 wow fadeInLeft" data-wow-delay="0.2s">
                        <div class="contact-img d-flex justify-content-center" >
                            <div class="contact-img-inner">
                                <img src="https://www.chieflearningofficer.com/wp-content/uploads/2023/11/AdobeStock_480329143.jpeg" class="img-fluid w-100"  alt="Image">
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-6 wow fadeInRight" data-wow-delay="0.4s">
                        <div>
                            <h4 class="text-primary">Send Your Feedback</h4>

                            <form>
                                <div class="row g-3">
                                    <div class="col-lg-12 col-xl-6">
                                        <div class="form-floating">
                                            <input type="text" class="form-control border-0" id="name" placeholder="Your Name">
                                            <label for="name">Your Name</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-xl-6">
                                        <div class="form-floating">
                                            <input type="email" class="form-control border-0" id="email" placeholder="Your Email">
                                            <label for="email">Your Email</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-xl-6">
                                        <div class="form-floating">
                                            <input type="phone" class="form-control border-0" id="phone" placeholder="Phone">
                                            <label for="phone">Your Phone</label>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-xl-6">
                                        <div class="form-floating">
                                            <input type="text" class="form-control border-0" id="project" placeholder="Project">
                                            <label for="project">Your Apartment</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="col-12">
                                            <div class="col-12">
                                                <div class="col-12">
                                                    <div class="form-floating">
                                                        <div class="d-flex">
                                                            <div class="form-check form-check-inline me-3">
                                                                <input type="checkbox" class="form-check-input" name="Service" id="service" value="1">
                                                                <label class="form-check-label" for="service">Services</label>
                                                            </div>
                                                            <div class="form-check form-check-inline me-3">
                                                                <input type="checkbox" class="form-check-input" name="Order" id="order" value="1">
                                                                <label class="form-check-label" for="order">Orders</label>
                                                            </div>
                                                            <div class="form-check form-check-inline">
                                                                <input type="checkbox" class="form-check-input" name="Other" id="other" value="1">
                                                                <label class="form-check-label" for="other">Other</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <textarea class="form-control border-0" placeholder="Leave a message here" id="message" style="height: 120px"></textarea>
                                        <label for="message">Your feedback here</label>
                                    </div>

                                </div>
                                <div class="col-12">
                                    <button class="btn btn-primary w-100 py-3">Send Feedback</button>
                                </div>
                        </div>
                        </form>
                    </div>
                    <br><!-- comment -->    
                    <br><!-- comment -->

                    <div class="col-12">
                        <div>
                            <div class="row g-4">
                                <div class="col-md-6 col-lg-3 wow fadeInUp" data-wow-delay="0.2s">
                                    <div class="contact-add-item">
                                        <div class="contact-icon text-primary mb-4">
                                            <i class="fas fa-map-marker-alt fa-2x"></i>
                                        </div>
                                        <div>
                                            <h4>Address</h4>
                                            <p class="mb-0">FPT University</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3 wow fadeInUp" data-wow-delay="0.4s">
                                    <div class="contact-add-item">
                                        <div class="contact-icon text-primary mb-4">
                                            <i class="fas fa-envelope fa-2x"></i>
                                        </div>
                                        <div>
                                            <h4>Mail Us</h4>
                                            <p class="mb-0">AptMan@gmail.com</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3 wow fadeInUp" data-wow-delay="0.6s">
                                    <div class="contact-add-item">
                                        <div class="contact-icon text-primary mb-4">
                                            <i class="fa fa-phone-alt fa-2x"></i>
                                        </div>
                                        <div>
                                            <h4>Telephone</h4>
                                            <p class="mb-0">(+084)34 430 7266</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3 wow fadeInUp" data-wow-delay="0.8s">
                                    <div class="contact-add-item">
                                        <div class="contact-icon text-primary mb-4">
                                            <i class="fab fa-facebook-f fa-2x"></i>
                                        </div>
                                        <div>
                                            <h4>
                                                My Admin Pages
                                                <a href="https://www.facebook.com/tuannghia.le.9849/"></a>
                                            </h4>
                                            <p class="mb-0"><a href="https://www.facebook.com/tuannghia.le.9849/">Here bro</a></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 wow fadeInUp" data-wow-delay="0.2s">
                        <div class="rounded">
                            <iframe class="rounded w-100" 
                                    style="height: 400px;" 
                                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.5063419425237!2d105.52271427486198!3d21.01241668063284!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBGUFQgSMOgIE7hu5lp!5e0!3m2!1svi!2s!4v1726222641040!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade">                                  

                            </iframe>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- Contact End -->


     <jsp:include page="footer.jsp"></jsp:include>

        <!-- Copyright Start -->

               
                


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
</body>
</html>
