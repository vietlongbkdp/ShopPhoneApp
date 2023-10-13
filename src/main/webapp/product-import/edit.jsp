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
        </div>
    <!-- Sidebar End -->


    <!-- Content Start -->
    <div class="content">
        <div class="container">
            <div class="card container px-6" style="height: 100vh">
                <h3 class="text-center">Edit Product Import</h3>
                <form action="/product-import?action=edit&id=${productImport.id}" method="post">


                    <div class="mb-3">
                        <label for="code" class="form-label">Import Code</label>
                        <input type="text" class="form-control" id="code" name="code" required value="${productImport.code}">
                    </div>
                    <div class="mb-3">
                        <label for="importDate" class="form-label">Import Date</label>
                        <input type="date" class="form-control" id="importDate" name="importDate" required value="${productImport.importDate}">
                    </div>
                    <%--        <div class="mb-3">--%>
                    <%--            <label for="totalAmount" class="form-label">Tổng giá trị</label>--%>
                    <%--            <input type="number" class="form-control" id="totalAmount" name="totalAmount" required>--%>
                    <%--        </div>--%>
                    <div class="row mb-3" id="product-import-${status.index + 1}">
                        <div class="col-4">
                            Product
                        </div>
                        <div class="col-3">
                            Quantity
                        </div>
                        <div class="col-3">
                            Amount
                        </div>
                        <div class="col-2 d-flex justify-content-end">
                            <button type="button" class="btn btn-info" onclick="addMore()">Add More</button>
                        </div>
                    </div>
                    <div id="product-import-detail">
                        <c:forEach var="piDetail" varStatus="status" items="${productImport.productImportDetails}">


                            <div class="row mb-3" id="product-import-${status.index + 1}" data-index="${status.index + 1}">
                                <div class="col-4">
                                    <select class="form-control" onchange="onChangeSelect(this)" name="productIds" id="product" required>
                                        <option value="">---Please Choose---</option>
                                        <c:forEach var="product" items="${products}">
                                            <option value="${product.id}" ${product.id == piDetail.product.id ? 'selected' : ''}>
                                                    ${product.productName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-3">
                                    <input type="number" class="form-control" name="quantities" value="${piDetail.quantity}" required>
                                </div>
                                <div class="col-3">
                                    <input type="number" class="form-control" name="amounts" value="${piDetail.amount}" required>
                                </div>
                                <div class="col-2 d-flex justify-content-end">
                                    <button type="button" class="btn btn-danger" onclick="deleteRow(${status.index + 1})">Delete</button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <button type="submit" class="btn btn-primary">Edit import</button>
                    <a href="/product-import" class="btn btn-dark ">Cancel</a>
                </form>
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
    <!-- Content End -->


    <!-- Back to Top -->
    <%--  <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>--%>
</div>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Template Javascript -->
<!-- Code injected by live-server -->
<script>


    const productId = document.getElementById('product');
    const productImportDetail = document.getElementById('product-import-detail');
    const eSelect = document.getElementsByName('productIds');
    const productss = ${productsJSON};

    let rowProductImport = ${productImport.productImportDetails.size()};
    let rowCount = rowProductImport;
    function addMore() {
        if(rowCount === productss.length) return
        rowCount++;
        let selectStr = '<select class="form-control" onchange="onChangeSelect(this)" name="productIds" id="product" required><option value="">---Please Choose---</option>';
        for (const product of productss) {
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
                <input type="number" class="form-control"  name="amounts" required>
            </div>
            <div class="col-2 d-flex justify-content-end">
                <button class="btn btn-danger" onclick="deleteRow(\${rowProductImport})">Delete</button>
            </div>
        </div>`
        document.querySelector('#product-import-detail').innerHTML += strRow;
    }

    function deleteRow(number) {
        rowCount--;
        const row = document.getElementById('product-import-' + number);
        productImportDetail.removeChild(row);
    }
    let productsSelected = Array.from(eSelect).map(e => e.value);
    function onChangeSelect(e){
        if(productsSelected.find(id => +id === +e.value)){
            e.value = '';
            toastr.warn('Product has been selected');
        }
        productsSelected = Array.from(eSelect).map(e => e.value);
    }
    // ]]>
</script>
    <script src="/user/admin/assets/jquery.min.js"></script>
    <script src="/user/admin/assets/bootstrap.bundle.min.js"></script>
    <script src="/user/admin/assets/jquery.easing.min.js"></script>
    <script src="/user/admin/assets/sb-admin-2.min.js"></script>
    <script src="/user/admin/assets/jquery.dataTables.min.js"></script>
    <script src="/user/admin/assets/dataTables.bootstrap4.min.js"></script>
    <script src="/user/admin/assets/datatables-demo.js"></script>


</body>
</html>