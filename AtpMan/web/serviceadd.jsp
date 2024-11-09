<%@page contentType="text/html" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
                                                                   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <meta name="description" content="" />
        <meta name="author" content="" />

        <title>SB Admin 2 - Cards</title>

        <!-- Custom fonts for this template-->
        <link
            href="vendor/fontawesome-free/css/all.min.css"
            rel="stylesheet"
            type="text/css"
            />
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet"
            />

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet" />
        <!-- Icon Font Stylesheet -->
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
            />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
            rel="stylesheet"
            />


        <script src="tinymce_7.4.1/tinymce/js/tinymce/tinymce.min.js"></script>
        <script>
            tinymce.init({
                selector: '#description1'
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
                        <div
                            class="d-sm-flex align-items-center justify-content-between mb-4"
                            >
                            <h1 class="h3 mb-0 text-gray-800">Thêm Dịch Vụ</h1>
                        </div>



                        <!-- Hiển thị thông báo lỗi -->
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger" role="alert">
                                ${errorMessage}
                            </div>
                        </c:if>


                        <div
                            class="row"
                            style="
                            border: 1px darkgrey solid;
                            border-radius: 10px;
                            width: 100%;
                            margin: 0 auto;
                            padding: 20px;
                            "
                            >
                            <!--                            enctype show how to encypt when put it on server -->
                            <form
                                action="serviceadd"
                                method="post"
                                onsubmit="return validateForm()"
                                enctype="multipart/form-data"
                                >
                                <div class="row g-0">
                                    <div class="col-md-12 d-flex justify-content-between">
                                        <div>
                                            <div class="form-group mb-4">
                                                <label for="name" class="form-label">Tên:</label>
                                                <input
                                                    type="text"
                                                    class="form-control w-100"
                                                    name="name"
                                                    id="name"
                                                    required
                                                    value="${name}"
                                                    />
                                            </div>
                                            <div class="form-group mb-4">
                                                <label for="type" class="form-label">Loại dịch vụ:</label>
                                                <select class="form-select w-100" name="type" id="type">
                                                    <c:forEach items="${serviceType}" var="ls">
                                                        <option value="${ls.type}" <c:if test="${type == ls.type}">selected</c:if>>${ls.type}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group mb-4">
                                                <label for="img" class="form-label">URL ảnh:</label>
                                                <input
                                                    type="file"
                                                    class="form-control w-100"
                                                    name="img"
                                                    id="img"
                                                    accept="image/*"
                                                    value="${ls.img}"
                                                    onchange="previewImg(event)"
                                                    required
                                                    />
                                                <img
                                                    src=""
                                                    id="imgPreview"
                                                    style="
                                                    width: 230px;
                                                    height: 200px;
                                                    margin-top: 20px;
                                                    border-radius: 10px;
                                                    display: none;
                                                    "
                                                    />
                                            </div>
                                        </div>
                                        <div class="mr-5">
                                            <label class="form-label">Giảm giá (%):</label>
                                            <div>
                                                <div>
                                                    <label for="discount1Month">1 tháng:</label>
                                                    <input type="number" id="discount1Month" min="0" value="0" name="discount1Month"  class="discount-input" placeholder="Nhập giá"/>
                                                </div>
                                                <div>
                                                    <label for="discount2Months">2 tháng:</label>
                                                    <input type="number" id="discount2Months" min="0" value="0" name="discount2Month" class="discount-input" placeholder="Nhập giá"/>
                                                </div>
                                                <div>
                                                    <label for="discount3Months">3 tháng:</label>
                                                    <input type="number" id="discount3Months" min="0" value="0" name="discount3Month" class="discount-input" placeholder="Nhập giá"/>
                                                </div>
                                            </div>
                                        </div>    
                                    </div>

                                    <div class="col-md-12">
                                        <div class="form-group mb-4">
                                            <label for="fee" class="form-label">Giá:</label>
                                            <input
                                                type="text"
                                                class="form-control w-100"
                                                name="fee"
                                                id="fee"
                                                value="${fee}"
                                                required
                                                />
                                            <div
                                                id="feeError"
                                                class="text-danger"
                                                style="display: none"
                                                ></div>
                                        </div>
                                        <div class="form-group mb-4">
                                            <label for="icon" class="form-label" style="width: 100%"
                                                   >URL icon:</label
                                            >
                                            <input
                                                type="text"
                                                class="form-control w-100"
                                                name="icon"
                                                id="icon"
                                                value="${icon}"
                                                required
                                                />
                                        </div>
                                        <div class="form-group mb-4">
                                            <label for="description" class="form-label"
                                                   >Mô tả:</label
                                            >
                                            <textarea
                                                class="form-control w-100"
                                                name="description"
                                                id="description1"
                                                rows="9"

                                                >${description}</textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="text-center mt-4 d-flex">
                                    <input
                                        type="submit"
                                        class="btn btn-primary btn-block"
                                        value="Thêm"
                                        style="width: 80px; margin: 0 auto"
                                        />
                                    <a
                                        class="btn btn-primary"
                                        href="servicelist?page=${page}"
                                        style="margin-right: 150px"
                                        >Hoàn Tác</a
                                    >
                                </div>
                            </form>
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
        <script src="js/main.js"></script>
        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/main1.js"></script>
    </body>
</html>
