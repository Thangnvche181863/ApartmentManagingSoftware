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

        <title>Quản Lý Mục Tin</title>
       
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
                        <center><h1 class="h3 mb-4 text-gray-800">Quản Lý Mục Tin</h1></center>  
                        
                        <center>
                            <c:if test="${not empty message}">
                                <c:choose>
                                    <c:when test="${message.startsWith('Thành công')}">
                                        <p class="text-center alert alert-success">${message}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-center alert alert-danger">${message}</p>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </center>
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <form class="form-inline d-none d-sm-inline-block mw-100" action="newscategorymanage" method="get">
                                <div class="input-group">
                                    <input type="text" name="search" class="form-control bg-light border-0 small" 
                                           placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">



                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <div>
                                <a href="newsmanage" class="btn btn-outline-primary mr-2">Quản Lý Tin</a>
                                <a href="newscategoryadd.jsp" class="btn btn-outline-primary">Thêm Mục Tin</a>
                            </div>

                        </div>
                    </div>
                    <!-- /.container-fluid -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">
                                Bảng Mục Tin
                                <!-- Add a clickable '?' icon to trigger the modal -->
                                <a href="#" data-toggle="modal" data-target="#infoModal" class="ml-2">
                                    <i class="fas fa-question-circle"></i>
                                </a>
                            </h6>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="infoModalLabel">Thông tin về NewsCategory</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        -Một số tập tin (NewsCategory) được gắn liền với hệ thống cùng các tính năng riêng biệt nên không thể bị xóa.
                                        <br></br>
                                        -Việc thêm NewsCategory sẽ tạo ra một mục tin mới ở trang chủ.
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>

                                            <th>ID</th>
                                            <th>Tên Mục Tin</th>
                                            <th>Miêu tả</th>                                       
                                            <th>Tương Tác</th>

                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach items="${newsCategory}" var="nc">
                                            <tr>
                                                <td>${nc.newsCategoryID}</td>
                                                <td><a href="newsmanage?category=${nc.newsCategoryID}">${nc.name}</a></td>
                                                <td>${nc.description}</td>
                                                <td>
                                                    <div class="btn-group" role="group">

                                                        <a class="btn btn-sm btn-warning unpressable-btn"  href="EditNewsCategory?id=${nc.newsCategoryID}">Sửa</a>
                                                        <a class="btn btn-sm btn-danger unpressable-btn" href="NewsCategoryDelete?id=${nc.newsCategoryID}" onclick="return confirmDelete();" data-id="${nc.newsCategoryID}">Xóa</a>
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
                                                        <a class="page-link" href="newscategorymanage?page=${currentPage - 1}&search=${param.search}">Previous</a>
                                                    </li>
                                                </c:if>

                                                <c:forEach var="i" begin="1" end="${totalPages}">
                                                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                        <a class="page-link" href="newscategorymanage?page=${i}&search=${param.search}">${i}</a>
                                                    </li>
                                                </c:forEach>

                                                <c:if test="${currentPage < totalPages}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="newscategorymanage?page=${currentPage + 1}&search=${param.search}">Next</a>
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
            // Disable delete button for system categories (newsCategoryID <= 10)
            document.addEventListener("DOMContentLoaded", function () {
                const deleteButtons = document.querySelectorAll(".unpressable-btn");

                deleteButtons.forEach(function (button) {
                    const categoryId = parseInt(button.getAttribute("data-id"));

                    if (categoryId <= 10) {
                        button.classList.add("disabled");
                        button.style.pointerEvents = "none";
                        button.title = "This NewsCategory is part of the system and cannot be deleted.";
                    }
                });
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
