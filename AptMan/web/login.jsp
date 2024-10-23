    <%-- 
    Document   : login
    Created on : Sep 11, 2024, 11:38:13 PM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

        <!-- MDBootstrap CSS -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.0/mdb.min.css" rel="stylesheet" />

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
    </head>

    <body>
        <section class="gradient-custom py-5 ">
            <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card bg-dark text-white" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <div class="mb-md-5 mt-md-4 pb-5">
                                    <h2 class="fw-bold mb-2 text-uppercase">Login</h2>

                                    <form action="userlogin" method="post">
                                        <div class="row">
                                            <div class="col-12 mb-4">
                                                <label class="form-label select-label" style="color : white">Login as</label>
                                                <select class="select form-control-lg" name="userType">
                                                    <option value="" selected>Choose option</option>
                                                    <option value="2">Resident</option>
                                                    <option value="3">Manage</option>
                                                </select>

                                            </div>
                                        </div>
                                        <!-- Phần hiển thị lỗi nếu có -->
                                        <div class="text-danger mb-3">
                                            <c:if test="${not empty loginerr}">
                                                <p>${loginerr}</p>
                                            </c:if>
                                            <c:if test="${not empty err}">
                                                <p>${err}</p>
                                            </c:if>

                                        </div>

                                        <div data-mdb-input-init class="form-outline form-white mb-4">
                                            <input type="text" id="typeNameX" class="form-control form-control-lg" name="username" />
                                            <label class="form-label" for="typeNameX">Username</label>
                                        </div>

                                        <div data-mdb-input-init class="form-outline form-white mb-4">
                                            <input type="password" id="typePasswordX" class="form-control form-control-lg" name="password" />
                                            <label class="form-label" for="typePasswordX">Password</label>
                                        </div>

                                        <p class="small mb-5 pb-lg-2">
                                            <a class="text-white-50" href="#!">Forgot password?</a>
                                        </p>

                                        <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
                                    </form>


                                    <div class="d-flex justify-content-center text-center mt-4 pt-1">
                                        <!--                                        <a href="#!" class="text-white"><i class="fab fa-google fa-lg"></i></a>-->
                                        <button type="button" class="login-with-google-btn" id="googleLoginBtn">
                                            <img src="media/ggicon.png" alt="alt"/>
                                            Sign in with Google
                                        </button>
                                    </div>
                                </div>

                                <div>
                                    <p class="mb-0">Don't have an account? <a href="register.jsp" class="text-white-50 fw-bold">Sign Up</a></p>
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
        <script>
            document.getElementById("googleLoginBtn").onclick = function () {
                window.location.href = "https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=http://localhost:9999/AtpMan/logingoogle&response_type=code&client_id=568289174347-2i2rujrr6nrul944und4erc72c158p07.apps.googleusercontent.com&approval_prompt=force";
            };
        </script>
    </body>

    <style>
        .gradient-custom {
            background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));
        }

        .login-with-google-btn {
            display: flex;
            align-items: center; /* Căn giữa theo chiều dọc */
            font-size: 14px;
            font-weight: 500;
            color: black;
            background: rgba(255, 255, 255, 0.7);
            border: none;
            border-radius: 30px;
            height: 40px;
            width: 50%;
            margin-top: 10px; /* Khoảng cách từ nút đăng ký */
            margin-left: 2px;
            cursor: pointer;
            transition: background-color .3s, box-shadow .3s;
            position: relative; /* Thêm dòng này */
        }
        .btn-outline-light:hover {
            background-color: #ffffff; /* Màu nền khi di chuột vào */
            color: black; /* Màu chữ khi di chuột vào */
            transition: background-color 0.3s ease; /* Hiệu ứng chuyển đổi màu nền */
        }

        .login-with-google-btn:hover {
            background-color: #999999;
        }

        .login-with-google-btn img {
            margin-right: 20px;
            margin-left: 25px; /* Khoảng cách giữa hình ảnh và chữ */
            width: 20px;
        }
    </style>
</html>


