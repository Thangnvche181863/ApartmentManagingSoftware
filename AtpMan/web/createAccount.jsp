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

                                    <!-- Username và Name -->
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

                                    <!-- Phone Number và Email -->
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

                                    <!-- Building và Apartment -->
                                    <div class="row">
                                        <!-- Danh sách Building -->
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label select-label">Building</label>
                                            <select class="select form-control-lg" name="building" id="building" required onchange="loadApartments()">
                                                <option value="" selected>Choose building</option>
                                                <!-- Lặp qua danh sách Building từ servlet -->
                                                <c:forEach items="${listBuildings}" var="b">
                                                    <option value="${b.buildingID}">${b.name}</option>
                                                </c:forEach>
                                            </select>
                                            <small class="text-danger" id="buildingError"></small>
                                        </div>

                                        <!-- Danh sách Apartment sẽ được nạp qua AJAX -->
                                        <div class="col-md-6 mb-4">
                                            <label class="form-label select-label">Apartment</label>
                                            <select class="select form-control-lg" name="apartment" id="apartment" required>
                                                <option value="" selected>Choose apartment</option>
                                            </select>
                                            <small class="text-danger" id="apartmentError"></small>
                                        </div>
                                    </div>

                                    <!-- Loại tài khoản -->
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

                                    <!-- Nút tạo tài khoản -->
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
                        if (xhr.readyState == 4 && xhr.status == 200) {
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
