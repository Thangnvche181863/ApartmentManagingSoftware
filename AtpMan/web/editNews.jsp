
<%-- 
    Document   : editNews
    Created on : Oct 14, 2024, 5:12:56 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa Tin</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- Include TinyMCE -->
        <script src="https://cdn.tiny.cloud/1/n0b2uh23r0ya9qhhy07odsf6v4qhzjpn6aoav7c4rzx6ocd4/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>

        <script>
            tinymce.init({
                selector: '#newsContent',
                plugins: 'image link media',
                toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | link image media',
                height: 300
            });
        </script>

    </head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sửa Tin</title>

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


                <div class="container mt-5">
                    <center><h2>Sửa Tin</h2></center> 
                    <!-- Display success or error message -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>
                    <c:if test="${not empty success}">
                        <div class="alert alert-success">${success}</div>
                    </c:if>
                    <form id="newsForm" action="EditNews" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="newsId" value="${requestScope.news.newsID}" />  <!-- This should hold a valid ID -->

                        <div class="form-group">
                            <label for="newsTitle">Tiêu Đề:</label>
                            <input type="text" class="form-control" id="newsTitle" name="newsTitle" value="${news.newsTitle}" required>
                        </div>

                        <div class="form-group">
                            <label for="newsDescription">Chú Thích: </label>
                            <input type="text" class="form-control" id="newsDescription" name="newsDescription" value="${news.description}" >
                        </div>

                        <!-- File input for image thumbnail -->
                        <div class="form-group">
                            <label for="newsImg">Ảnh Thumbnail (Không bắt buộc):</label>
                            <input type="file" class="form-control-file" id="newsImg" name="newsImg" accept="image/*">
                            <!-- Display current thumbnail if available -->
                            <c:if test="${news.newsImg != null && !news.newsImg.isEmpty()}">
                                <img src="${news.newsImg}" alt="Current Thumbnail" style="max-width: 150px; margin-top: 10px;">
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="newsCategory">Mục Tin:</label>
                            <select class="form-control" id="newsCategory" name="newsCategory">
                                <c:forEach var="category" items="${newsCategories}">
                                    <option value="${category.newsCategoryID}" 
                                            <c:if test="${category.newsCategoryID == news.newsCategoryID}">selected</c:if>>${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="newsContent">Nội Dung:</label>
                            <textarea class="form-control" id="newsContent" name="newsContent" rows="10">${news.newsContent}</textarea>
                        </div>



                        <a class="btn btn-danger" href="newsmanage">Quay lại</a>
                        <input type="submit"  class="btn btn-primary" value="Update" />
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
</body>
</html>



