<%-- 
    Document   : test
    Created on : Oct 19, 2024, 9:56:08 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Quản Lý Tin</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Include TinyMCE -->


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
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <center><h1 class="h3 mb-4 text-gray-800">Quản Lý Tin</h1></center>  
                        <center>
                            <c:if test="${not empty message}">
                                <c:choose>
                                    <c:when test="${message.startsWith('News deleted')}">
                                        <p class="text-center alert alert-success">${message}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-center alert alert-danger">${message}</p>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </center>
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <form class="form-inline d-none d-sm-inline-block mw-100" action="newsmanage" method="get">
                                <div class="input-group">
                                    <input type="text" name="search" class="form-control bg-light border-0 small" 
                                           placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">

                                    <!-- Dropdown for News Categories -->
                                    <select name="category" class="form-control ml-2">
                                        <option value="all">All</option>
                                        <c:forEach var="category" items="${newsCategories}">
                                            <option value="${category.newsCategoryID}">${category.name}</option>
                                        </c:forEach>
                                    </select>

                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <div>
                                <a href="newscategorymanage" class="btn btn-outline-primary mr-2">Quản Lý Mục Tin</a>
                                <a href="AddNews" class="btn btn-outline-primary">Thêm Tin</a>
                            </div>

                        </div>
                    </div>
                    <!-- /.container-fluid -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Bảng Tin</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>

                                            <th>Tên</th>
                                            <th>Mô Tả</th>
                                            <th>Tác Giả</th>
                                            <th>Mục Tin</th>
                                            <th>Thời Gian Đăng</th>
                                            <th>Tương Tác</th>

                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach items="${news}" var="newsItem">
                                            <tr>

                                                <td width="250">${newsItem.newsTitle}</td>
                                                <td width="500">${newsItem.description}</td>
                                                <td>${newsItem.staffName}</td>
                                                <td><a href="newsmanage?category=${newsItem.newsCategoryID}">${newsItem.newsCategoryName}</a></td>
                                                <td><fmt:formatDate value="${newsItem.postDate}" pattern="EEEE dd/MM/yyyy HH:mm" /></td>
                                                <td>
                                                    <div class="btn-group" role="group">
                                                        <a class="btn btn-sm btn-primary" href="NewsDetail?id=${newsItem.newsID}">Xem</a>
                                                        <a class="btn btn-sm btn-warning" href="EditNews?id=${newsItem.newsID}">Sửa</a>
                                                        <a class="btn btn-sm btn-danger" href="newsdelete?id=${newsItem.newsID}" onclick="return confirmDelete();">Xóa</a>
                                                    </div>
                                                </td>
                                            </tr>

                                        </c:forEach>





                                    </tbody>
                                </table>
                                <!-- Pagination -->
                                <div class="row">
                                    <div class="col-md-12">
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination justify-content-start">
                                                <c:if test="${currentPage > 1}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="newsmanage?page=${currentPage - 1}&search=${param.search}&category=${param.category}">Previous</a>
                                                    </li>
                                                </c:if>

                                                <c:forEach var="i" begin="1" end="${totalPages}">
                                                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                        <a class="page-link" href="newsmanage?page=${i}&search=${param.search}&category=${param.category}">${i}</a>
                                                    </li>
                                                </c:forEach>

                                                <c:if test="${currentPage < totalPages}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="newsmanage?page=${currentPage + 1}&search=${param.search}&category=${param.category}">Next</a>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </nav>
                                    </div><!-- end col -->
                                </div><!-- end row/pagination --> 
                            </div>
                        </div>
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
                        <a class="btn btn-primary" href="login.html">Logout</a>
                    </div>
                </div>
            </div>
        </div>

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

        <script>
            document.getElementById("newsForm").addEventListener("submit", function (event) {
                const fileInput = document.getElementById("newsImg");
                const filePath = fileInput.value;
                const allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;

                if (filePath) {
                    if (!allowedExtensions.exec(filePath)) {
                        alert("Please upload a valid image file (jpg, jpeg, png, gif).");
                        fileInput.value = ''; // Clear the input
                        event.preventDefault(); // Prevent form submission
                    }
                }
            });
        </script>

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
    </body>

</html>
