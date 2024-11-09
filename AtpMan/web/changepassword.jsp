<%-- 
    Document   : changepassword
    Created on : Oct 10, 2024, 9:19:57 AM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đổi mật khẩu</title>
        <!-- Include Tailwind CSS CDN -->
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
        <!-- Custom fonts and stylesheets -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400,600,700" rel="stylesheet">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    </head>

    <body>
        <div id="wrapper">
            <!-- Page Wrapper -->
            <%@include file="sidebar.jsp" %>

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <%@include file="topbar.jsp" %>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <section class="bg-gray-50 dark:bg-gray-900">
                            <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
                                <div class="w-full p-6 bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md dark:bg-gray-800 dark:border-gray-700 sm:p-8">
                                    <h2 class="mb-1 text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                                        Đổi mật khẩu
                                    </h2>

                                    <%-- Hiển thị thông báo lỗi nếu có --%>
                                    <% if (request.getAttribute("errNewpass") != null) { %>
                                    <div class="alert alert-danger">
                                        <%= request.getAttribute("errNewpass") %>
                                    </div>
                                    <% } %>

                                    <% if (request.getAttribute("changepwderr") != null) { %>
                                    <div class="alert alert-danger">
                                        <%= request.getAttribute("changepwderr") %>
                                    </div>
                                    <% } %>

                                    <% if (request.getAttribute("changepwdsuccess") != null) { %>
                                    <div class="alert alert-success">
                                        <%= request.getAttribute("changepwdsuccess") %>
                                    </div>
                                    <% } %>



                                    <form class="mt-4 space-y-4 lg:mt-5 md:space-y-5" action="changepassword" method="POST">

                                        <div>
                                            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Tài khoản email</label>
                                            <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="name@company.com" required="">
                                        </div>
                                        <div>
                                            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Mật khẩu mới</label>
                                            <input type="password" name="newPassword" id="password" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="">
                                        </div>
                                        <div>
                                            <label for="confirm-password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Xác nhận mật khẩu mới</label>
                                            <input type="password" name="cfPassword" id="confirm-password" placeholder="••••••••" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required="">
                                        </div>

                                        <button type="submit" class="w-full text-white bg-primary hover:bg-blue-600 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">Đổi mật khẩu</button>
                                    </form>
                                    <div class="text-right">
                                        <a href="/AtpMan/profile">Trở về</a>
                                    </div>



                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>
        <script>
            tailwind.config = {
                darkMode: 'class',
                theme: {
                    extend: {
                        colors: {
                            primary: {"50": "#eff6ff", "100": "#dbeafe", "200": "#bfdbfe", "300": "#93c5fd", "400": "#60a5fa", "500": "#3b82f6", "600": "#2563eb", "700": "#1d4ed8", "800": "#1e40af", "900": "#1e3a8a", "950": "#172554"}
                        }
                    },
                    fontFamily: {
                        'body': [
                            'Inter',
                            'ui-sans-serif',
                            'system-ui',
                            '-apple-system',
                            'system-ui',
                            'Segoe UI',
                            'Roboto',
                            'Helvetica Neue',
                            'Arial',
                            'Noto Sans',
                            'sans-serif',
                            'Apple Color Emoji',
                            'Segoe UI Emoji',
                            'Segoe UI Symbol',
                            'Noto Color Emoji'
                        ],
                        'sans': [
                            'Inter',
                            'ui-sans-serif',
                            'system-ui',
                            '-apple-system',
                            'system-ui',
                            'Segoe UI',
                            'Roboto',
                            'Helvetica Neue',
                            'Arial',
                            'Noto Sans',
                            'sans-serif',
                            'Apple Color Emoji',
                            'Segoe UI Emoji',
                            'Segoe UI Symbol',
                            'Noto Color Emoji'
                        ]
                    }
                }
            };
        </script>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>
    </body>

</html>
