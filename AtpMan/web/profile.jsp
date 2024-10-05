<%-- 
    Document   : profile
    Created on : Sep 20, 2024, 11:39:32 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/3.6.95/css/materialdesignicons.css"
            rel="stylesheet">
        <link
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            rel="stylesheet">
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"
            rel="stylesheet">
        <link
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            rel="stylesheet">


        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">














        <!-- cai nay cua table -->

        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>
    <style>

        body {
            background: rgb(99, 39, 120)
        }

        .form-control:focus {
            box-shadow: none;
            border-color: #BA68C8
        }

        .profile-button {
            background: rgb(99, 39, 120);
            box-shadow: none;
            border: none
        }

        .profile-button:hover {
            background: #682773
        }

        .profile-button:focus {
            background: #682773;
            box-shadow: none
        }

        .profile-button:active {
            background: #682773;
            box-shadow: none
        }

        .back:hover {
            color: #682773;
            cursor: pointer
        }

        .labels {
            font-size: 14px
        }

        .add-experience:hover {
            background: #BA68C8;
            color: #fff;
            cursor: pointer;
            border: solid 1px #BA68C8
        }
    </style>
    <body>
        <%@include file="sidebar.jsp" %>
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <%@include file="topbar.jsp" %>




                <div class="container rounded bg-white mt-5 mb-5">
                    <div class="row">
                        <div class="col-md-4 border-right">
                            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                                <span class="font-weight-bold">${user.name}</span><span class="text-black-50">${user.email}</span><span> </span></div>
                        </div>
                        <div class="col-md-6 border-right">


                            <form action="profile" method="POST">
                                <div class="p-3 py-5">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h4 class="text-right">Profile Settings</h4>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="col-md-12"><label class="labels">Name</label><input type="text" name="name" class="form-control" placeholder="first name" value="${user.name}"></div>
                                        <div class="col-md-12"><label class="labels">Mobile Number</label><input type="text" name="phoneNumber" class="form-control" placeholder="enter phone number" value="${user.phoneNumber}"></div>
                                        <div class="col-md-12"><label class="labels">User Name</label><input type="text" class="form-control" placeholder="username" value="${user.username}" readonly></div>
                                        <div class="col-md-12"><label class="labels">Password</label><input type="text" class="form-control" placeholder="password" value="********" readonly>
                                            <a href="#" class="btn btn-link text-primary justify-content-end" style="font-size: 12px; text-decoration: underline;"> </div>
                                        Change password
                                        </a>

                                        <div class="col-md-12"><label class="labels">Role</label><input type="text" class="form-control" value="" readonly></div>
                                        <div class="col-md-12"><label class="labels">Email </label><input type="text" name="email" class="form-control" placeholder="enter email " value="${user.email}" readonly></div>
                                        <a href="#" class="btn btn-link text-primary justify-content-end" style="font-size: 12px; text-decoration: underline;"> 
                                            Change Email
                                        </a>
                                        <div class="col-md-12">
                                            <label class="labels">Start Date(Hire/Register) </label>
                                            <input type="text" class="form-control" 
                                                   placeholder="enter hire date" 
                                                   value="${userType =='3' ? user.hireDate : user.registrationDate}" 
                                                   readonly>
                                        </div>

                                    </div>

                                                   <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Save Profile</button></div>
                                </div>
                            </form>

                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
