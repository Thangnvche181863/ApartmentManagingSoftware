

<%-- 
    Document   : newscategoryadd
    Created on : Oct 23, 2024, 11:11:31 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.io.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Mục Tin</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- Include TinyMCE -->
        <script src="https://cdn.tiny.cloud/1/n0b2uh23r0ya9qhhy07odsf6v4qhzjpn6aoav7c4rzx6ocd4/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>

        <script>
            tinymce.init({
                selector: '#newsContent', // Target the textarea
                plugins: 'image link media',
                toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | link image media',
                height: 300
            });

            // Ensure the TinyMCE content is saved before form submission
            $(document).ready(function () {
                $('#newsForm').on('submit', function (e) {
                    console.log("Form is being submitted...");

                    tinymce.triggerSave(); // Update textarea with TinyMCE content

                    // Debug: Check if the textarea now has content
                    console.log("News content:", $('#newsContent').val());

                    if ($('#newsContent').val() === '') {
                        e.preventDefault();  // Prevent form submission if content is missing
                        alert("News content is empty!");
                    }
                });
            });
        </script>

    </head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Thêm Tin</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">




</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <jsp:include page="/sidebar.jsp"/>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <%@include file = "topbar.jsp" %>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container mt-5">
                    <center><h2>Thêm Mục Tin</h2></center> 
                        <% if (request.getAttribute("key") != null) { %>
                    <div class="alert alert-danger text-center" role="alert">
                        <%= request.getAttribute("key") %>
                    </div>
                    <% } %>
                    <form id="newsForm" action="AddNewsCategory" method="post" >
                        <div class="form-group">
                            <label for="newsTitle">Tên Mục Tin:</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>



                        <div class="form-group">
                            <label for="newsTitle">Chú Thích:</label>
                            <input type="text" class="form-control" id="description" name="description" >
                        </div>


                        <a class="btn btn-danger" href="newscategorymanage">Quay lại</a>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>

                </div>
            </div>



            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>



    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
    <script type="text/javascript">
            function confirmDelete() {
                return confirm("Bạn có chắc chắn muốn xóa tin tức này?");
            }
    </script>
    <script type="text/javascript">

        function confirmCancel() {
            return confirm("Bạn có chắc chắn muốn bỏ qua báo cáo này?");
        }
    </script>
</body>
</html>


