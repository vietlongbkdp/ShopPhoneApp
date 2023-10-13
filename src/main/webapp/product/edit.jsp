<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/18/2023
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Phone Shop</title>
    <link href="/user/admin/assets/all.min.css" rel="stylesheet" type="text/css">
    <link href="/user/admin/assets/sb-admin-2.min.css" rel="stylesheet">
    <link href="/user/admin/assets/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
</head>
<body id="page-top">
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/main">
            <div class="sidebar-brand-icon">
                <i class="fas fa-home"></i>
            </div>
            <div class="sidebar-brand-text mx-3">SHOPPING</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item">
            <a class="nav-link" href="/total?action=user">
                <i class="fas fa-users-cog"></i>
                <span>User Management</span></a>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="/total?action=product" aria-expanded="true">
                <i class="fas fa-fw fa-table"></i>
                <span>Product Management</span>
            </a>
        </li>

        <!-- Nav Item - Utilities Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="/total?action=order" aria-expanded="true">
                <i class="fas fa-file-invoice"></i>
                <span>Order Management</span>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="/total?action=branch" aria-expanded="true">
                <i class="fas fa-mobile"></i>
                <span>Branch Management</span>
            </a>
        </li>
        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <form class="form-inline">
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                </form>
                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">
                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                             aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small"
                                           placeholder="Search for..." aria-label="Search"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">Welcome ${user.userName}</span>
                            <img class="img-profile rounded-circle"
                                 src="/user/admin/assets/undraw_profile_2.svg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="/shopping?action=profile">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
<div class="container">
    <div class="card container px-6" >
        <h3 class="sidebar-brand-text mx-5 mb-3 font-weight-bold">Edit Product</h3>
        <form    action="/product?action=edit&id=${product.id}" method="post" enctype="multipart/form-data">
            <label for="productName" class="col-sm-6 mb-3 mb-sm-3">Product Name</label>
            <input type="text" class="form-control form-control-user" id="productName" name="productName"
                   value="${product.productName}">
            <div class="mb-3">
                <label for="price" class="col-sm-6 mb-3 mb-sm-3">Price</label>
                <input type="number" class="form-control form-control-user" name="price" id="price" value="${product.price}">
            </div>
            <div class="mb-3">
                <label for="warrantyPeriod" class="col-sm-6 mb-3 mb-sm-3">Warranty Period</label>
                <input type="text" class="form-control form-control-user" name="warrantyPeriod" id="warrantyPeriod"
                       value="${product.warrantyPeriod}">
            </div>
            <div class="mb-3">
                <label for="ram" class="col-sm-6 mb-3 mb-sm-3">RAM</label>
                <input type="text" class="form-control form-control-user" name="ram" id="ram" value="${product.ram}">
            </div>
            <div class="mb-3">
                <label for="size" class="col-sm-6 mb-3 mb-sm-3">Size</label>
                <input type="text" class="form-control form-control-user" name="size" id="size" value="${product.size}">
            </div>
            <div class="mb-3">
                <label for="color" class="col-sm-6 mb-3 mb-sm-3">Color</label>
                <input type="text" class="form-control form-control-user" name="color" id="color" value="${product.color}">
            </div>
            <div class="mb-3">
                <label for="camera" class="col-sm-6 mb-3 mb-sm-3">Camera</label>
                <input type="text" class="form-control form-control-user" name="camera" id="camera" value="${product.camera}">
            </div>
            <div class="mb-3">
                <label for="operatingSystem" class="col-sm-6 mb-3 mb-sm-3">Operating System</label>
                <input type="text" class="form-control form-control-user" name="operatingSystem" id="operatingSystem"
                       value="${product.operatingSystem}">
            </div>
            <div class="mb-3">
                <label for="pin" class="col-sm-6 mb-3 mb-sm-3">Pin</label>
                <input type="text" class="form-control form-control-user" name="pin" id="pin" value="${product.pin}">
            </div>
            <div class="mb-3">
                <label for="branch" class="col-sm-6 mb-3 mb-sm-3">Branch</label>
                <select class="form-control form-control-user" name="branch" id="branch">
                    <c:forEach var="branch" items="${branchs}">
                        <option value="${branch.id}">
                                ${branch.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label for="file-input" class="form-label">Image</label>
                <img id="img-preview" style=" width: 100%;
    max-width: 350px;" src="../images${product.image}"/>
                <input type="file" accept="image/*" class="form-control" id="file-input" name="image"
                       value="${product.image}">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <a href="/product" class="btn btn-primary ">Cancel</a>
        </form>
    </div>
</div>
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
                            <a class="btn btn-primary" href="/login?action=logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>---------  Website Design by Huy - Long - Thắng Team  ---------</span>
                    </div>
                </div>
            </footer>
            <script>
                const message = document.getElementById('message');
                if (message !== null && message.innerHTML) {
                    toastr.success(message.innerHTML);
                }

                const input = document.getElementById('file-input');
                const image = document.getElementById('img-preview');

                input.addEventListener('change', (e) => {
                    if (e.target.files.length) {
                        const src = URL.createObjectURL(e.target.files[0]);
                        image.src = src;
                    }
                });
            </script>

            <script>
                let existingFiles = [];
                let currentFiles = [];
                const inputElement = document.getElementById("img");
                const previewContainer = document.getElementById("image-preview");

                // Lấy danh sách đường dẫn file từ dữ liệu ban đầu
                <c:forEach items="${room.images}" var="image">
                existingFiles.push("/image${image.url}");
                </c:forEach>

                // Gọi hàm để tải và gán tệp vào ô input
                loadFiles(existingFiles);

                function loadFiles(fileUrls) {
                    // Lặp qua danh sách đường dẫn file và tải tệp từ mỗi URL
                    fileUrls.forEach(function (fileUrl) {
                        fetch(fileUrl)
                            .then(response => response.blob())
                            .then(blob => {
                                // Tạo một đối tượng File từ Blob và đặt tên tệp
                                const fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                                const file = new File([blob], fileName);

                                // Kiểm tra xem tệp đã tồn tại trong danh sách hiện tại chưa
                                if (!currentFiles.some(existingFile => existingFile.name === file.name)) {
                                    currentFiles.push(file);
                                }

                                // Cập nhật danh sách tệp trong ô input
                                const newFileList = new DataTransfer();
                                currentFiles.forEach(function (file) {
                                    newFileList.items.add(file);
                                });
                                inputElement.files = newFileList.files;

                                // Sau khi thêm tệp vào ô input, cần cập nhật lại giao diện người dùng
                                renderUploadedFiles(currentFiles);
                            });
                    });
                }

                function renderUploadedFiles(files) {
                    previewContainer.innerHTML = ""; // Xóa nội dung hiện tại

                    files.forEach(function (file) {
                        const imgContainer = document.createElement("div");
                        imgContainer.classList.add("img-container");

                        const img = document.createElement("img");
                        img.src = URL.createObjectURL(file); // Sử dụng URL.createObjectURL để hiển thị tệp đã thêm

                        img.style.width = "200px"; // Kích thước hình ảnh xem trước
                        img.style.height = "auto";
                        img.style.marginTop = "5px";

                        const deleteIcon = document.createElement("span");
                        deleteIcon.classList.add("delete-icon");
                        deleteIcon.innerHTML = "&times;";

                        // Gắn sự kiện click vào biểu tượng "x" để xóa hình ảnh và tệp tin
                        deleteIcon.addEventListener("click", function () {
                            imgContainer.remove();

                            // Xóa tệp tương ứng từ danh sách
                            const index = currentFiles.findIndex(existingFile => existingFile.name === file.name);
                            if (index !== -1) {
                                currentFiles.splice(index, 1);

                                // Cập nhật lại danh sách tệp trong ô input
                                const newFileList = new DataTransfer();
                                currentFiles.forEach(function (file) {
                                    newFileList.items.add(file);
                                });
                                inputElement.files = newFileList.files;
                            }
                        });

                        imgContainer.appendChild(img);
                        imgContainer.appendChild(deleteIcon);
                        previewContainer.appendChild(imgContainer);
                    });
                }

                inputElement.addEventListener("change", function (event) {
                    const newFiles = event.target.files;
                    addNewFiles(newFiles);
                });

                function addNewFiles(newFiles) {
                    // Kiểm tra và lọc ra các đối tượng File từ newFiles
                    const validNewFiles = Array.from(newFiles).filter(function (file) {
                        return file instanceof File;
                    });

                    validNewFiles.forEach(function (file) {
                        if (!currentFiles.some(existingFile => existingFile.name === file.name)) {
                            currentFiles.push(file);
                        }
                    });

                    // Cập nhật lại danh sách tệp trong ô input
                    const newFileList = new DataTransfer();
                    currentFiles.forEach(function (file) {
                        newFileList.items.add(file);
                    });
                    inputElement.files = newFileList.files;

                    // Sau khi thêm tệp vào ô input, cần cập nhật lại giao diện người dùng
                    renderUploadedFiles(currentFiles);
                }
            </script>


            <script src="/user/admin/assets/jquery.min.js"></script>
            <script src="/user/admin/assets/bootstrap.bundle.min.js"></script>
            <script src="/user/admin/assets/jquery.easing.min.js"></script>
            <script src="/user/admin/assets/sb-admin-2.min.js"></script>
            <script src="/user/admin/assets/jquery.dataTables.min.js"></script>
            <script src="/user/admin/assets/dataTables.bootstrap4.min.js"></script>
            <script src="/user/admin/assets/datatables-demo.js"></script>
        </div>
</body>
</html>