<%-- 
    Document   : requestcomplaint
    Created on : Nov 8, 2024, 11:18:45 AM
    Author     : WuanTun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Customer Feedback Form">
        <meta name="author" content="WuanTun">
        <title>Request Service</title>

        <!-- Custom fonts and styles -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400,600,700,800,900" rel="stylesheet">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
        <style>
            .container {
                max-width: 600px;
                margin-top: 40px;
                padding: 20px;
                background-color: #f8f9fc;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            h2 {
                color: #4e73df;
                text-align: center;
                font-weight: 700;
                margin-bottom: 20px;
            }

            label {
                font-weight: bold;
                color: #4e73df;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-control {
                border-radius: 5px;
                border: 1px solid #ddd;
            }

            button[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #4e73df;
                border: none;
                color: #fff;
                font-size: 16px;
                font-weight: bold;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            button[type="submit"]:hover {
                background-color: #2e59d9;
            }

            .form-icon {
                color: #4e73df;
                margin-right: 5px;
            }
        </style>
    </head>
    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <%@include file="../sidebar.jsp" %>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">


                    <!-- Topbar -->

                    <%@include file = "../topbar.jsp" %>

                    <!-- End of Topbar -->



                    <!-- Begin Page Content -->
                    <div class="container">
                        <h2>Yêu cầu thêm dịch vụ</h2>
                        <form action="requestcomplaintservlet" method="post">
                            <div class="form-group">
                                <label for="title">
                                    <i class="bi bi-file-text form-icon"></i>Tiêu đề:
                                </label>
                                <input type="text" name="title" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="description">
                                    <i class="bi bi-chat-dots form-icon"></i>Mô tả:
                                </label>
                                <textarea name="description" rows="5" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="type">
                                    <i class="bi bi-tools form-icon"></i>Loại dịch vụ:
                                </label>
                                <select name="type" class="form-control" required>
                                    <option value="" selected>Chọn loại dịch vụ</option>
                                    <option value="Sữa chữa">Sửa chữa</option>
                                    <option value="Vệ sinh">Vệ sinh</option>
                                    <option value="Kiểm tra">Kiểm tra</option>
                                    <option value="Bảo trì">Bảo trì</option>
                                    <option value="Nâng cấp">Nâng cấp</option>
                                </select>
                            </div>
                            <button type="submit">Gửi yêu cầu</button>
                        </form>
                    </div>
                    <!-- End of Main Content -->

                </div>
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>


            <!-- Logout Modal-->
            <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                            <a class="btn btn-primary" href="logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div>

            <%-- Thông báo thành công --%>
            <% if ("success".equals(request.getParameter("status"))) { %>
            <script>
                alert("Thêm thành công!");
            </script>
            <% } %>
            <!-- Bootstrap core JavaScript-->
            <script src="vendor/jquery/jquery.min.js"></script>
            <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

            <!-- Core plugin JavaScript-->
            <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="js/sb-admin-2.min.js"></script>
            <!-- Page level plugins -->
            <script src="vendor/datatables/jquery.dataTables.min.js"></script>
            <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

            <!-- Page level custom scripts -->
            <script src="js/demo/datatables-demo.js"></script>

    </body>
</html>




