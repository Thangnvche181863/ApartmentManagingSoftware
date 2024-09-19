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
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
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

    <!-- Modal Search Start -->
    <div
      class="modal fade"
      id="searchModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-fullscreen">
        <div class="modal-content rounded-0">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              Search by keyword
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body d-flex align-items-center bg-primary">
            <div class="input-group w-75 mx-auto d-flex">
              <input
                type="search"
                class="form-control p-3"
                placeholder="keywords"
                aria-describedby="search-icon-1"
              />
              <span
                id="search-icon-1"
                class="btn bg-light border nput-group-text p-3"
                ><i class="fa fa-search"></i
              ></span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal Search End -->

    <!-- Header Start -->
    <div class="container-fluid bg-breadcrumb">
      <div class="container text-center py-5" style="max-width: 900px">
        <h4
          class="text-white display-4 mb-4 wow fadeInDown"
          data-wow-delay="0.1s"
        >
          Our Services
        </h4>
        <ol
          class="breadcrumb d-flex justify-content-center mb-0 wow fadeInDown"
          data-wow-delay="0.3s"
        >
          <li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
          <li class="breadcrumb-item"><a href="#">Manage</a></li>
          <li class="breadcrumb-item active text-primary">Service</li>
        </ol>
      </div>
    </div>
    <!-- Header End -->

    <!-- Service Start -->
    <div class="container-fluid service py-5">
      <div class="container py-5">
        <div
          class="text-center mx-auto pb-5 wow fadeInUp"
          data-wow-delay="0.2s"
          style="max-width: 800px"
        >
          <h4 class="text-primary">Our Services</h4>
          <h1 class="display-4 mb-4">We Provide Best Services</h1>
          <p class="mb-0">
            The following service are provide by the building management and
            related parties. We believe that will provide you with the best
            services. Thank you for followign our service.
          </p>
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
                    >Repair Services</a
                  >
                  <p class="mb-4">
                    With repair services, we are always ready to serve you promptly to ensure convenience and efficiency.
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
      </div>
        <div class="container-fluid feature bg-light py-5" id="navigate">
            <div class="container py-5">
                <div class="text-center mx-auto pb-5 wow fadeInUp" data-wow-delay="0.2s" style="max-width: 800px;">
                    <h1 class="display-4 mb-4">Extension Services</h1>
                    <p class="mb-0">Extension services are services provided by third parties, by reputable and experienced service providers. We guarantee that you will be satisfied when using the service.
                    </p>
                </div>
                <!--part 1-->
                <div class="row g-4">
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.2s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="far fa-handshake fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Trusted Company</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.4s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-dollar-sign fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Anytime Money Back</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.6s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-bullseye fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Flexible Plans</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.8s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-headphones fa-3x"></i>
                            </div>
                            <h4 class="mb-4">24/7 Fast Support</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                </div>
                
                <!--part 2-->
                <div class="row g-4 mt-3">
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.2s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="far fa-handshake fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Trusted Company</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.4s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-dollar-sign fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Anytime Money Back</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.6s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-bullseye fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Flexible Plans</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.8s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-headphones fa-3x"></i>
                            </div>
                            <h4 class="mb-4">24/7 Fast Support</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                </div>
                
                <!--part 3-->
                <div class="row g-4 mt-3">
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.2s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="far fa-handshake fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Trusted Company</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.4s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-dollar-sign fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Anytime Money Back</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.6s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-bullseye fa-3x"></i>
                            </div>
                            <h4 class="mb-4">Flexible Plans</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-3 wow fadeInUp" data-wow-delay="0.8s">
                        <div class="feature-item p-4 pt-0">
                            <div class="feature-icon p-4 mb-4">
                                <i class="fa fa-headphones fa-3x"></i>
                            </div>
                            <h4 class="mb-4">24/7 Fast Support</h4>
                            <p class="mb-4">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ea hic laborum odit pariatur...
                            </p>
                            <a class="btn btn-primary rounded-pill py-2 px-4" href="#">Learn More</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Service End -->

    <!-- Testimonial Start -->
    <div class="container-fluid testimonial pb-5">
      <div class="container pb-5">
        <div
          class="text-center mx-auto pb-5 wow fadeInUp"
          data-wow-delay="0.2s"
          style="max-width: 800px"
        >
          <h4 class="text-primary">Testimonial</h4>
          <h1 class="display-4 mb-4">What Our Customers Are Saying</h1>
          <p class="mb-0">
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Tenetur
            adipisci facilis cupiditate recusandae aperiam temporibus corporis
            itaque quis facere, numquam, ad culpa deserunt sint dolorem autem
            obcaecati, ipsam mollitia hic.
          </p>
        </div>
        <div
          class="owl-carousel testimonial-carousel wow fadeInUp"
          data-wow-delay="0.2s"
        >
          <div class="testimonial-item bg-light rounded">
            <div class="row g-0">
              <div class="col-4 col-lg-4 col-xl-3">
                <div class="h-100">
                  <img
                    src="img/testimonial-1.jpg"
                    class="img-fluid h-100 rounded"
                    style="object-fit: cover"
                    alt=""
                  />
                </div>
              </div>
              <div class="col-8 col-lg-8 col-xl-9">
                <div class="d-flex flex-column my-auto text-start p-4">
                  <h4 class="text-dark mb-0">Client Name</h4>
                  <p class="mb-3">Profession</p>
                  <div class="d-flex text-primary mb-3">
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                  </div>
                  <p class="mb-0">
                    Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    Enim error molestiae aut modi corrupti fugit eaque rem nulla
                    incidunt temporibus quisquam,
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div class="testimonial-item bg-light rounded">
            <div class="row g-0">
              <div class="col-4 col-lg-4 col-xl-3">
                <div class="h-100">
                  <img
                    src="img/testimonial-2.jpg"
                    class="img-fluid h-100 rounded"
                    style="object-fit: cover"
                    alt=""
                  />
                </div>
              </div>
              <div class="col-8 col-lg-8 col-xl-9">
                <div class="d-flex flex-column my-auto text-start p-4">
                  <h4 class="text-dark mb-0">Client Name</h4>
                  <p class="mb-3">Profession</p>
                  <div class="d-flex text-primary mb-3">
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star text-body"></i>
                  </div>
                  <p class="mb-0">
                    Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    Enim error molestiae aut modi corrupti fugit eaque rem nulla
                    incidunt temporibus quisquam,
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div class="testimonial-item bg-light rounded">
            <div class="row g-0">
              <div class="col-4 col-lg-4 col-xl-3">
                <div class="h-100">
                  <img
                    src="img/testimonial-3.jpg"
                    class="img-fluid h-100 rounded"
                    style="object-fit: cover"
                    alt=""
                  />
                </div>
              </div>
              <div class="col-8 col-lg-8 col-xl-9">
                <div class="d-flex flex-column my-auto text-start p-4">
                  <h4 class="text-dark mb-0">Client Name</h4>
                  <p class="mb-3">Profession</p>
                  <div class="d-flex text-primary mb-3">
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star"></i>
                    <i class="fas fa-star text-body"></i>
                    <i class="fas fa-star text-body"></i>
                  </div>
                  <p class="mb-0">
                    Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    Enim error molestiae aut modi corrupti fugit eaque rem nulla
                    incidunt temporibus quisquam,
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
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
