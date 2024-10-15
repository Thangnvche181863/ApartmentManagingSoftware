<%-- 
    Document   : createAccountStaff
    Created on : Oct 1, 2024, 11:35:18 AM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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

               
                

                <!-- Nút tạo tài khoản -->
                <div class="mt-4 pt-2">
                    <div>
                        <input data-mdb-ripple-init class="btn btn-primary btn-lg" type="submit" value="Create" />
                    </div>
                    <a href="logout">Logout</a>
                </div>

            </form>
        </div>
    </body>
</html>
