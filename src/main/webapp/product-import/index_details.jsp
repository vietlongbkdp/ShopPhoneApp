<%--
  Created by IntelliJ IDEA.
  User: LONG PC
  Date: 10/2/2023
  Time: 12:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create User</title>
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
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/shopping?action=shopping">
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
            <div class="card shadow mb-4">
                <div class="card-header py-3" style="display: flex; justify-content: space-between">
                    <h6 class="m-0 font-weight-bold text-primary"> Product Import Detail </h6>
                    <a href="/product-import" class="btn btn-primary ">Home</a>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Code</th>
                                <th>Date Import</th>
                                <th>Total</th>
                            </tr>
                            </thead>
                                <tr>
                                    <td>${productImport.id}</td>
                                    <td>${productImport.code}</td>
                                    <td>${productImport.importDate}</td>
                                    <td>${productImport.totalAmount}</td>
                                </tr>


                            <thead>

                            <tr>
                                <th></th>
                                <th>Product Name</th>
                                <th>Quantity</th>
                                <th>Price Import</th>
                            </tr>
                            </thead>
                            <c:forEach var="pid" items="${productImport.productImportDetails}">
                                <tr>
                                    <td></td>
                                    <td>${pid.product.productName}</td>
                                    <td>${pid.quantity}</td>
                                    <td>${pid.amount}</td>
                                    <td>${product.price}</td>
                                </tr>
                                <c:set var="hasProducts" value="true"/>
                            </c:forEach>
                        </table>
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
    </div>
</div>
<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>


<!-- Template Javascript -->
<%--    <script src="../js/main.js"></script>--%>
<!-- Code injected by live-server -->
<script>
    var codeInput = document.getElementById('code');
    var codeError = document.getElementById('code-error');

    codeInput.addEventListener('blur', function () {
        var codeValue = codeInput.value;
        if (codeValue.length < 6) {
            codeInput.classList.add('is-invalid'); // Thêm lớp CSS 'is-invalid' để hiển thị viền đỏ
            codeError.textContent = 'Code phải có ít nhất 6 kí tự'; // Hiển thị thông báo lỗi
        } else {
            codeInput.classList.remove('is-invalid'); // Xóa lớp CSS 'is-invalid'
            codeError.textContent = ''; // Xóa thông báo lỗi
        }
    })

    document.querySelector('form').addEventListener('submit', function (event) {

        var codeValue = codeInput.value;
        if (codeValue.length < 6) {
            event.preventDefault(); // Ngăn chặn gửi form đi

            codeInput.classList.add('is-invalid'); // Thêm lớp CSS 'is-invalid' để hiển thị viền đỏ
            codeError.textContent = 'Password phải có ít nhất 6 kí tự'; // Hiển thị thông báo lỗi

            codeInput.focus(); // Tập trung vào trường password không hợp lệ
        }


    });

    var importDateInput = document.getElementById('importDate');
    var importDateError = document.getElementById('importDate-error');

    importDateInput.addEventListener("blur", function () {
            var importDateValue = new Date(importDateInput.value).getTime();
            var currentDate = new Date().getTime();

            if (importDateValue > currentDate) {
                importDateInput.classList.add("is-invalid");
                importDateError.textContent = "Ngày không hợp lệ, không phải là ngày tương lai";
            } else {
                importDateInput.classList.remove("is-invalid");
                importDateError.textContent = "";
            }
        }
    )

    const productId = document.getElementById('product');
    const productImportDetail = document.getElementById('product-import-detail');
    const eSelect = document.getElementsByName('productIds');
    const products = ${productsJSON};
    let rowProductImport = 1;
    let rowCount = rowProductImport;

    function addMore2() {
        if (rowCount === products.length) return
        rowCount++;
        let selectStr = '<select class="form-control" onchange="onChangeSelect(this)" name="productIds" id="product" required><option value="">---Please Choose---</option>';
        for (const product of products) {
            selectStr += `<option value="\${product.id}">\${product.productName}</option>`;
        }

        selectStr += '</select>';
        const strRow = `<div class="row mb-3" id="product-import-\${++rowProductImport}">
            <div class="col-4">
                \${selectStr}
            </div>
            <div class="col-3">
                <input type="number" class="form-control"  name="quantities" required>
            </div>
            <div class="col-3">
                <input type="number" class="form-control"  name="amounts" value="\${product.price}" required>
            </div>
            <div class="col-2 d-flex justify-content-end">
                <button class="btn btn-danger" onclick="deleteRow(\${rowProductImport})">Delete</button>
            </div>

            </div>`
        document.querySelector('#product-import-detail').innerHTML += strRow;
    }

    const message = document.getElementById('message');
    if (message !== null && message.innerHTML) {
        toastr.success(message.innerHTML);
    }


    function deleteRow(number) {
        if (rowCount === 1) {
            toastr.warn('At least 1 product');
            return;
        }
        rowCount--;
        const row = document.getElementById('product-import-' + number);
        productImportDetail.removeChild(row);
    }

    let productsSelected = Array.from(eSelect).map(e => e.value);

    function onChangeSelect(e) {
        if (productsSelected.find(id => +id === +e.value)) {
            e.value = '';
        }
        productsSelected = Array.from(eSelect).map(e => e.value);
    }
</script>

<script src="/user/admin/assets/jquery.min.js"></script>
<script src="/user/admin/assets/bootstrap.bundle.min.js"></script>
<script src="/user/admin/assets/jquery.easing.min.js"></script>
<script src="/user/admin/assets/sb-admin-2.min.js"></script>
<%--<script src="/user/admin/assets/jquery.dataTables.min.js"></script>--%>
<script src="/user/admin/assets/dataTables.bootstrap4.min.js"></script>
<script src="/user/admin/assets/datatables-demo.js"></script>
</body>
</html>