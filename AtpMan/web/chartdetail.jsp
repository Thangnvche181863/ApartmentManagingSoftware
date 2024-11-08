
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin 2 - Cards</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">
        <!-- Icon Font Stylesheet -->
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
            />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
            rel="stylesheet"
            />
        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

        <script src="tinymce_7.4.1/tinymce/js/tinymce/tinymce.min.js"></script>
        <script>
            tinymce.init({
                selector: '#description3'
            });
        </script>  
        <style>
            .is-invalid {
                border-color: red;
                background-color: #f8d7da;
            }

            /* Phong cách chung */
            .row {
                border: 1px solid #b0b0b0;
                border-radius: 10px;
                max-width: 800px;
                margin: 20px auto;
                padding: 20px;
                background-color: #f9f9f9;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-label {
                font-weight: 600;
                color: #333;
            }

            .form-control,
            .form-select {
                border-radius: 5px;
                outline: none;
                box-shadow: none;
                border: 1px solid #ced4da;
            }

            input[type="file"] {
                padding: 6px;
                border: 1px solid #ced4da;
            }

            #imgPreview {
                width: 100%;
                max-width: 230px;
                height: auto;
                margin-top: 10px;
                border-radius: 10px;
            }

            .text-center {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }

            .btn-primary {
                width: 100px;
                padding: 8px;
                font-weight: 600;
            }

            #feeError {
                display: none;
                color: #d9534f;
            }

            textarea {
                resize: none;
            }

            .form-label {
                font-weight: 600;
                margin-bottom: 8px;
                display: block;
                color: #333;
            }

            .discount-input {
                width: 150px;
                padding: 8px;
                margin-bottom: 8px;
                border-radius: 5px;
                border: 1px solid #ced4da;
                text-align: center;
            }

            div > div label {
                font-weight: 500;
                display: block;
                margin-bottom: 4px;
            }
        </style>

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <%@include file="sidebar.jsp" %>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->

            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">


                    <!-- Topbar -->

                    <%@include file = "topbar.jsp" %>

                    <!-- End of Topbar -->



                    <!-- Begin Page Content -->
                    <div class="container-fluid">


                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800"  style="margin: auto" ><strong>Mô Tả Chi Tiết</strong></h1>
                                <a class="btn btn-info" href="statistic">Thống Kê Phụ Phí</a>
                        </div>
                        

                        <div
                            style="
                            border: 1px darkgrey solid;
                            border-radius: 10px;
                            width: 100%;
                            margin: 0 auto;
                            padding: 20px;
                            margin-bottom: 20px
                            "
                            >
                            <!--                            enctype show how to encypt when put it on server -->
                            ${finance.description}
                        </div>
                    </div>
                    <!-- /.container-fluid -->


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

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>
        <!-- Page level plugins -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/datatables-demo.js"></script>
        <script src="js/main3.js"></script>
    </body>

</html>