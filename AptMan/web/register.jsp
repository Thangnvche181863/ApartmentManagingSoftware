<%-- 
    Document   : register
    Created on : Sep 11, 2024, 11:57:35 PM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>

        <!-- MDBootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet" />

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
    </head>

    <body>
        <section class="gradient-custom py-5">
            <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card bg-dark text-white" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">
                                <div class="mb-md-5 mt-md-4 pb-4">
                                    <h2 class="fw-bold mb-2 text-uppercase">Sign Up</h2>
                                    <p class="text-white-50 mb-5">Please enter your information</p>
                                    <form action="userregister" method="post">
                                        <div class="form-outline form-white mb-4">
                                            <input type="name" id="typeNameX" class="form-control form-control-lg" name="username"/>
                                            <label class="form-label" for="typeNameX">Username</label>
                                            <% if (request.getAttribute("messUsername") != null) { %>
                                            <p class="text-danger"><%= request.getAttribute("messUsername") %></p>
                                            <% } %>
                                        </div>

                                        <div class="form-outline form-white mb-4">
                                            <input type="email" id="typeEmailX" class="form-control form-control-lg" name="email"/>
                                            <label class="form-label" for="typeEmailX">Email</label>
                                            <% if (request.getAttribute("messGmail") != null) { %>
                                            <p class="text-danger"><%= request.getAttribute("messGmail") %></p>
                                            <% } %>
                                        </div>

                                        <div class="form-outline form-white mb-4">
                                            <input type="phone" id="typePhoneX" class="form-control form-control-lg" name="phone"/>
                                            <label class="form-label" for="typePhoneX">Phone</label>
                                            <% if (request.getAttribute("messPhone") != null) { %>
                                            <p class="text-danger"><%= request.getAttribute("messPhone") %></p>
                                            <% } %>
                                        </div>

                                        <div class="form-outline form-white mb-4">
                                            <input type="password" id="typePasswordX" class="form-control form-control-lg" name="password"/>
                                            <label class="form-label" for="typePasswordX">Password</label>
                                            <% if (request.getAttribute("messPassword") != null) { %>
                                            <p class="text-danger"><%= request.getAttribute("messPassword") %></p>
                                            <% } %>
                                        </div>

                                        <div class="form-outline form-white mb-4">
                                            <input type="password" id="typeCfpasswordX" class="form-control form-control-lg" name="cfpassword"/>
                                            <label class="form-label" for="typeCfpasswordX">Confirm password</label>
                                            <% if (request.getAttribute("messCfPass") != null) { %>
                                            <p class="text-danger"><%= request.getAttribute("messCfPass") %></p>
                                            <% } %>
                                        </div>

                                        <button class="btn btn-outline-light btn-lg px-5" type="submit">Sign Up</button>
                                    </form>




                                    <div>
                                        <p class="mb-0">Already have an account? <a href="login.jsp" class="text-white-50 fw-bold">Login</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <!-- MDBootstrap JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.js">
        </script>
    </body>

    <style>
        .gradient-custom {
            background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
        }


        .btn-outline-light:hover {
            background-color: #ffffff; /* Màu nền khi di chuột vào */
            color: black; /* Màu chữ khi di chuột vào */
            transition: background-color 0.3s ease; /* Hiệu ứng chuyển đổi màu nền */
        }




    </style>
</html>
