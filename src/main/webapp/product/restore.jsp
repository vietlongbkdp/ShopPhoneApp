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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
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
            <a class="nav-link collapsed" href="/total?action=product" aria-expanded="true" >
                <i class="fas fa-fw fa-table"></i>
                <span>Product Management</span>
            </a>
        </li>

        <!-- Nav Item - Utilities Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="/total?action=order" aria-expanded="true" >
                <i class="fas fa-file-invoice"></i>
                <span>Order Management</span>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="/total?action=branch" aria-expanded="true" >
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
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Tables</h1>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3" style="display: flex; justify-content: space-between">
                        <h6 class="m-0 font-weight-bold text-primary">Restore Product Management</h6>
                    </div>
                    <c:if test="${message != null}">
                        <h6 class="d-none" id="message">${message}</h6>
                    </c:if>

                    <form action="/product" method="get">
                        <input type="hidden" name="action" value="showRestore"> <!-- Thêm input hidden để truyền giá trị action -->
                    </form>
                    <form action="/product?action=restore" method="post" id="restoreForm"> <!-- Đặt id cho form -->
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Product Name</th>
                                        <th>Branch</th>
                                        <th>Image</th>
                                        <th>Price</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                <c:set var="hasProducts" value="false"/>
                                <c:forEach var="product" items="${products}">
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.productName}</td>
                                        <td>${product.branchName}</td>
                                        <td><img src="../images${product.urlImage}" alt="" style="width: 100px;height: 100px"></td>
                                        <td>${product.price}</td>
                                        <td>
                                            <input type="checkbox" name="restoredProduct" value="${product.id}" class="myCheckBox">
                                            <!-- Checkbox -->
                                        </td>
                                    </tr>
                                    <c:set var="hasProducts" value="true"/>
                                </c:forEach>
                                <c:if test="${hasProducts}">
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>Select All</td>
                                        <td><input type="checkbox" id="selectAllCheckbox"> <!-- Checkbox to select all --></td>
                                    </tr>
                                </c:if>

                            </table>
                            <div>
                                <a class="btn btn-primary" onclick="showConfirm(event)">Restore</a>
                                <a href="/product" class="btn btn-primary">Cancel</a>
                            </div>
                            <div id="confirmDialog" class="modal">
                                <div class="modal-content">
                                    <p>Are you sure to restore?</p>
                                    <div class="button-container">
                                        <button type="submit" class="btn btn-primary mb-2">Yes</button>
                                        <button class="btn btn-primary mb-2" onclick="hideConfirmDialog(event)">No</button>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                                <a class="btn btn-primary" href="/login?action=logout">Logout</a>
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
                </div>
                <script>
                    const message = document.getElementById('message');
                    if (message !== null && message.innerHTML) {
                        toastr.success(message.innerHTML);
                    }

                    var checkboxes = document.querySelectorAll('.myCheckBox');
                    var checkedIds = [];

                    function hideConfirmDialog(event) {
                        event.preventDefault(); // Ngăn chặn mặc định submit của form
                        document.getElementById("confirmDialog").style.display = "none";
                    }

                    function showConfirm(event) {
                        checkedIds = []; // Đặt lại mảng checkedIds mỗi khi bạn hiển thị modal
                        for (var i = 0; i < checkboxes.length; i++) {
                            if (checkboxes[i].checked) {
                                checkedIds.push(checkboxes[i].value);
                            }
                        }
                        if (checkedIds.length === 0) {
                            toastr.warning("Please select at least one product to restore.");
                        } else {
                            document.getElementById("confirmDialog").style.display = "block";
                        }
                    }

                    const selectAllCheckbox = document.getElementById('selectAllCheckbox');
                    const checkedboxes = document.getElementsByName('restoredProduct');

                    selectAllCheckbox.addEventListener('change', function () {
                        checkedboxes.forEach(function (checkbox) {
                            checkbox.checked = selectAllCheckbox.checked;
                        });
                    });

                    // Xử lý khi bấm nút "No"
                    const noButton = document.querySelector('#confirmDialog button[type="button"]');
                    noButton.addEventListener('click', function (event) {
                        hideConfirmDialog(event);
                        checkedIds = [];
                    });

                </script>

                <style>
                    /* Style cho modal */
                    #confirmDialog {
                        display: none;
                        position: fixed;
                        z-index: 1000;
                        top: 0;
                        left: 0;
                        width: 100%;
                        height: 100%;
                        background-color: rgba(0, 0, 0, 0.5); /* Màu nền với độ trong suốt */
                    }

                    .modal-content {
                        background-color: #fff;
                        position: absolute;
                        top: 50%;
                        left: 50%;
                        transform: translate(-50%, -50%);
                        padding: 20px;
                        border-radius: 5px;
                        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
                        text-align: center;
                        width : 30rem
                    }

                    p {
                        margin: 0;
                    }

                    .button-container {
                        margin-top: 20px;
                    }

                    button {
                        margin: 5px;
                        padding: 10px 20px;
                        border: none;
                        background-color: #007bff;
                        color: #fff;
                        cursor: pointer;
                        border-radius: 5px;
                    }

                    button:hover {
                        background-color: #0056b3;
                    }
                </style>

                <script src="/user/admin/assets/jquery.min.js"></script>
                <script src="/user/admin/assets/bootstrap.bundle.min.js"></script>
                <script src="/user/admin/assets/jquery.easing.min.js"></script>
                <script src="/user/admin/assets/sb-admin-2.min.js"></script>
                <script src="/user/admin/assets/jquery.dataTables.min.js"></script>
                <script src="/user/admin/assets/dataTables.bootstrap4.min.js"></script>
                <script src="/user/admin/assets/datatables-demo.js"></script>
</body>
</html>
