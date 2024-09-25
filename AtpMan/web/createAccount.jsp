<%-- 
    Document   : createAccount
    Created on : Sep 19, 2024, 5:34:29 PM
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
    <style>
        .gradient-custom {
            /* fallback for old browsers */
            background: #f093fb;

            /* Chrome 10-25, Safari 5.1-6 */
            background: -webkit-linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1));

            /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
            background: linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1));
        }

        .form-label {
            display: block;
            margin-bottom:0.5px;
            font-size: 1rem;
            font-weight: 500;
        }

        .form-control-lg {
            padding: 0.25em;
            font-size: 1rem;
            width: 100%;
            margin-bottom: 0.5em;
        }

        .card-registration .select-arrow {
            top: 13px;
        }
    </style>

    <%@include file="sidebar.jsp" %>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->
                <form
                    class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                               aria-label="Search" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                             aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small"
                                           placeholder="Search for..." aria-label="Search"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <!-- Nav Item - Alerts -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-bell fa-fw"></i>
                            <!-- Counter - Alerts -->
                            <span class="badge badge-danger badge-counter">3+</span>
                        </a>
                        <!-- Dropdown - Alerts -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="alertsDropdown">
                            <h6 class="dropdown-header">
                                Alerts Center
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-primary">
                                        <i class="fas fa-file-alt text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 12, 2019</div>
                                    <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-success">
                                        <i class="fas fa-donate text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 7, 2019</div>
                                    $290.29 has been deposited into your account!
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-warning">
                                        <i class="fas fa-exclamation-triangle text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 2, 2019</div>
                                    Spending Alert: We've noticed unusually high spending for your account.
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                        </div>
                    </li>

                    <!-- Nav Item - Messages -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-envelope fa-fw"></i>
                            <!-- Counter - Messages -->
                            <span class="badge badge-danger badge-counter">7</span>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="messagesDropdown">
                            <h6 class="dropdown-header">
                                Message Center
                            </h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="img/undraw_profile_1.svg"
                                         alt="...">
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div class="font-weight-bold">
                                    <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                        problem I've been having.</div>
                                    <div class="small text-gray-500">Emily Fowler · 58m</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="img/undraw_profile_2.svg"
                                         alt="...">
                                    <div class="status-indicator"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">I have the photos that you ordered last month, how
                                        would you like them sent to you?</div>
                                    <div class="small text-gray-500">Jae Chun · 1d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="img/undraw_profile_3.svg"
                                         alt="...">
                                    <div class="status-indicator bg-warning"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">Last month's report looks great, I am very happy with
                                        the progress so far, keep up the good work!</div>
                                    <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                         alt="...">
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                        told me that people say this to all dogs, even if they aren't good...</div>
                                    <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                </div>
                            </a>
                            <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                        </div>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">AdminName</span>
                            <img class="img-profile rounded-circle"
                                 src="img/undraw_profile.svg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="profile.jsp">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                Settings
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Activity Log
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->
            
            
            
            <body>
                <section class="gradient-custom">
                    <div class="container py-5 h-100">
                        <div class="row justify-content-center align-items-center h-100">
                            <div class="col-12 col-lg-9 col-xl-7">
                                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                                    <div class="card-body p-4 p-md-5">
                                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Create Account</h3>

                                        <c:if test="${not empty messExist}">
                                            <div class="alert alert-danger" role="alert">
                                                ${messExist}
                                            </div>
                                        </c:if>

                                        <!-- Display success message if account is created successfully -->
                                        <c:if test="${not empty successCreate}">
                                            <div class="alert alert-success" role="alert">
                                                ${successCreate}
                                            </div>
                                        </c:if>
                                        <form action="createaccount" method="post">

                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label" for="username">Username</label>
                                                    <input type="text" id="username" class="form-control form-control-lg" name="username"/>
                                                    <small class="text-danger" id="usernameError"></small>
                                                </div>

                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label" for="password">Password</label>
                                                    <input type="password" id="password" class="form-control form-control-lg" name="password" />
                                                    <small class="text-danger" id="passwordError"></small>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label" for="name">Name</label>
                                                    <input type="text" id="name" class="form-control form-control-lg" name="name"/>
                                                </div>

                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label" for="email">Email</label>
                                                    <input type="text" id="email" class="form-control form-control-lg" name="email"/>
                                                    <small class="text-danger" id="emailError"></small>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label" for="phoneNumber">Phone Number</label>
                                                    <input type="text" id="phoneNumber" class="form-control form-control-lg" name="phoneNumber"/>
                                                    <small class="text-danger" id="phoneNumberError"></small>
                                                </div>

                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label" for="age">Age</label>
                                                    <input type="text" id="age" class="form-control form-control-lg" name="age" />
                                                    <small class="text-danger" id="ageError"></small>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-md-6 mb-4">
                                                    <label class="form-label" for="regDate">Registration Date</label>
                                                    <input type="date" id="regDate" class="form-control form-control-lg" name="registrationDate" />
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="col-12 mb-4">
                                                    <label class="form-label select-label">Type</label>
                                                    <select class="select form-control-lg" name="userType">
                                                        <option value="" selected>Choose option</option>
                                                        <option value="2">Resident</option>
                                                        <option value="3">Owner</option>
                                                    </select>
                                                </div>
                                            </div>


                                            <div class="mt-4 pt-2">
                                                <div>
                                                    <input data-mdb-ripple-init class="btn btn-primary btn-lg" type="submit" value="Create" />
                                                </div>
                                                <a href="logout">Logout</a>
                                            </div>

                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <script>
                    function validateUsername() {
                        const username = document.getElementById("username").value;
                        const usernamePattern = /^[a-zA-Z0-9]{6,16}$/;
                        const usernameError = document.getElementById('usernameError');

                        if (!usernamePattern.test(username)) {
                            usernameError.textContent = "Username must be 6-16 characters and only contain letters and numbers.";
                            usernameError.classList.add('text-danger');
                            usernameError.classList.remove('text-success');
                        } else {
                            usernameError.textContent = "Valid username!";
                            usernameError.classList.remove('text-danger');
                            usernameError.classList.add('text-success');
                        }
                    }

                    function validatePassword() {
                        const password = document.getElementById("password").value;
                        const passwordError = document.getElementById('passwordError');

                        // Kiểm tra độ dài từ 8 đến 31 ký tự
                        if (password.length < 8 || password.length > 31) {
                            passwordError.textContent = "Password must be 8-31 characters long.";
                            passwordError.classList.add('text-danger');
                            passwordError.classList.remove('text-success');
                        }
                        // Kiểm tra ít nhất một chữ cái viết hoa
                        else if (!/[A-Z]/.test(password)) {
                            passwordError.textContent = "Password must contain at least one uppercase letter.";
                            passwordError.classList.add('text-danger');
                            passwordError.classList.remove('text-success');
                        }
                        // Kiểm tra ít nhất một ký tự đặc biệt
                        else if (!/[!@#$%^&*(),.?":{}|<>]/.test(password)) {
                            passwordError.textContent = "Password must contain at least one special character.";
                            passwordError.classList.add('text-danger');
                            passwordError.classList.remove('text-success');
                        }
                        // Kiểm tra ít nhất một chữ số
                        else if (!/\d/.test(password)) {
                            passwordError.textContent = "Password must contain at least one number.";
                            passwordError.classList.add('text-danger');
                            passwordError.classList.remove('text-success');
                        }
                        // Kiểm tra không chứa khoảng trắng
                        else if (password.includes(" ")) {
                            passwordError.textContent = "Password must not contain whitespace.";
                            passwordError.classList.add('text-danger');
                            passwordError.classList.remove('text-success');
                        }
                        // Nếu tất cả điều kiện hợp lệ
                        else {
                            passwordError.textContent = "Valid password!";
                            passwordError.classList.remove('text-danger');
                            passwordError.classList.add('text-success');
                        }
                    }

                    function validateEmail() {
                        const email = document.getElementById("email").value;
                        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}(?:\.[a-zA-Z]{2,})?$/;
                        const emailError = document.getElementById('emailError');

                        if (!emailPattern.test(email)) {
                            emailError.textContent = "Please enter a valid email address.";
                            emailError.classList.add('text-danger');
                            emailError.classList.remove('text-success');
                        } else {
                            emailError.textContent = "Valid email!";
                            emailError.classList.remove('text-danger');
                            emailError.classList.add('text-success');
                        }
                    }

                    function validatePhone() {
                        const phone = document.getElementById("phoneNumber").value;
                        const phonePattern = /^\d{10}$/;
                        const phoneError = document.getElementById('phoneNumberError');

                        if (!phonePattern.test(phone)) {
                            phoneError.textContent = "Phone number must be exactly 10 digits.";
                            phoneError.classList.add('text-danger');
                            phoneError.classList.remove('text-success');
                        } else {
                            phoneError.textContent = "Valid phone number!";
                            phoneError.classList.remove('text-danger');
                            phoneError.classList.add('text-success');
                        }
                    }

                    function validateAge() {
                        const age = document.getElementById("age").value;
                        const ageError = document.getElementById('ageError');

                        if (isNaN(age) || age < 1 || age > 120) {
                            ageError.textContent = "Please enter a valid age between 1 and 120.";
                            ageError.classList.add('text-danger');
                            ageError.classList.remove('text-success');
                        } else {
                            ageError.textContent = "Valid age!";
                            ageError.classList.remove('text-danger');
                            ageError.classList.add('text-success');
                        }
                    }

                    // Attach the validate functions to the input fields
                    document.getElementById("username").addEventListener("input", validateUsername);
                    document.getElementById("password").addEventListener("input", validatePassword);
                    document.getElementById("email").addEventListener("input", validateEmail);
                    document.getElementById("phoneNumber").addEventListener("input", validatePhone);
                    document.getElementById("age").addEventListener("input", validateAge);
                </script>


            </body>

            </html>
