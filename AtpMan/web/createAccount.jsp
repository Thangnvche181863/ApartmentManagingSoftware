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
                                </c:if>
                                <form action="createaccount" method="post">


                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="username">Username</label>
                                            <input type="text" id="username" class="form-control form-control-lg" name="username" required/>
                                            <small class="text-danger" id="usernameError"></small>
                                        </div>

                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="name">Name</label>
                                            <input type="text" id="name" class="form-control form-control-lg" name="name" required/>
                                        </div>
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


                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="phoneNumber">Phone Number</label>
                                            <input type="text" id="phoneNumber" class="form-control form-control-lg" name="phoneNumber" required/>
                                            <small class="text-danger" id="phoneNumberError"></small>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label" for="email">Email</label>
                                            <input type="text" id="email" class="form-control form-control-lg" name="email" required/>
                                            <small class="text-danger" id="emailError"></small>
                                        </div>
                                    </div>


                                    <div class="row">

                                        <div class="col-md-6 mb-4">
                                            <label class="form-label select-label">Building</label>
                                            <select class="select form-control-lg" name="building" id="building" required onchange="loadApartments()">
                                                <option value="" selected>Choose building</option>
                                                <c:forEach items="${listBuildings}" var="b">
                                                    <option value="${b.buildingID}">${b.name}</option>
                                                </c:forEach>
                                            </select>
                                            <small class="text-danger" id="buildingError"></small>
                                        </div>


                                        <div class="col-md-6 mb-4">
                                            <label class="form-label select-label">Apartment</label>
                                            <select class="select form-control-lg" name="apartment" id="apartment" required>
                                                <option value="" selected>Choose apartment</option>
                                            </select>
                                            <small class="text-danger" id="apartmentError"></small>
                                        </div>
                                        
                                    </div>


                                    <div class="row">
                                        <div class="col-12 mb-4">
                                            <label class="form-label select-label">Type</label>
                                            <select class="select form-control-lg" name="userType" required>
                                                <option value="" selected>Choose option</option>
                                                <option value="2">Resident</option>
                                                <option value="3">Owner</option>
                                            </select>
                                            <small class="text-danger" id="userTypeError"></small>
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
                // Updated email pattern to allow general emails (like gmail.com) and .edu.vn
                const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.(com|edu)(\.vn)?$/;
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
//            document.getElementById("password").addEventListener("input", validatePassword);
            document.getElementById("email").addEventListener("input", validateEmail);
            document.getElementById("phoneNumber").addEventListener("input", validatePhone);
//            document.getElementById("age").addEventListener("input", validateAge);

            function loadApartments() {
                var buildingId = document.getElementById("building").value;

                if (buildingId !== "") {
                    var xhr = new XMLHttpRequest();
                    xhr.open("GET", "createaccount?buildingId=" + buildingId, true);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) { //404, 400, 500
                            // Replace apartment select box options with the response from the server
                            document.getElementById("apartment").innerHTML = xhr.responseText;
                        }
                    };
                    xhr.send();
                }
            }

        </script>


            </body>

            </html>
