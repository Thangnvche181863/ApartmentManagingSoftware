<%-- 
    Document   : newsCommentManager
    Created on : Nov 4, 2024, 11:00:50 PM
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

        <title>Quản Lý Bình Luận</title>

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
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <center><h1 class="h3 mb-4 text-gray-800">Quản Lý Bình Luận</h1></center>  
                        <center>
                            <c:if test="${not empty message}">
                                <c:choose>
                                    <c:when test="${message.startsWith('Bỏ Qua Báo Cáo Thành Công')}">
                                        <p class="text-center alert alert-success">${message}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-center alert alert-danger">${message}</p>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </center>
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <form class="form-inline d-none d-sm-inline-block mw-100" action="#" method="get">
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
                                <a href="newscategorymanage" class="btn btn-outline-primary mr-2">Quản Lý Tập Tin</a>
                            </div>

                        </div>
                    </div>
                    <!-- /.container-fluid -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Bảng Bình Luận bị Báo Cáo </h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>

                                            <th>CommentID</th>
                                            <th>NewsID</th>
                                            <th>Người Viết</th>
                                            <th>Nội Dung</th>
                                            <th>Ngày Đăng</th>
                                            <th>Tương Tác</th>

                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach items="${comments}" var="com">
                                            <tr>

                                                <td>${com.commentID}</td>
                                                <td><a href="NewsDetail?id=${com.newsID}">${com.newsID}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${not empty com.customerName}">
                                                            ${com.customerName}
                                                        </c:when>
                                                        <c:when test="${not empty com.staffName}">
                                                            ${com.staffName}
                                                        </c:when>
                                                        <c:otherwise>
                                                            Anonymous
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>${com.commentText}</td>
                                                <td><fmt:formatDate value="${com.commentDate}" pattern="EEEE dd/MM/yyyy HH:mm" /></td>
                                                <td>
                                                    <div class="btn-group" role="group">
                                                        <form action="newscommentmanage" method="post">
                                                            <input type="hidden" name="commentID" value="${com.commentID}"> </input>
                                                            <button type ="submit" class="btn btn-sm btn-success" onclick="return confirmCancel();">
                                                                Bỏ qua
                                                            </button> </form>
                                                        <a class="btn btn-sm btn-danger" href="CommentDelete?id=${com.commentID}" onclick="return confirmDelete();">Xóa</a>
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
                                                        <a class="page-link" href="newsmanage?page=${currentPage - 1}">Previous</a>
                                                    </li>
                                                </c:if>

                                                <c:forEach var="i" begin="1" end="${totalPages}">
                                                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                                                        <a class="page-link" href="newsmanage?page=${i}">${i}</a>
                                                    </li>
                                                </c:forEach>

                                                <c:if test="${currentPage < totalPages}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="newsmanage?page=${currentPage + 1}">Next</a>
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
        <script type="text/javascript">
        
                    function confirmCancel(){
                        return confirm("Bạn có chắc chắn muốn bỏ qua báo cáo này?");
                    }
        </script>
    </body>
</html>
