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
            <a class="nav-link" href="/cart?action=showCart">
                <i class="fas fa-users-cog"></i>
                <span>Cart</span></a>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="/order-client" aria-expanded="true">
                <i class="fas fa-fw fa-table"></i>
                <span>Order Management</span>
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

                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Cart</h1>
                </div>

                <!-- Content Row -->
                <div class="row">

                    <!-- Earnings (Monthly) Card Example -->
                    <!-- Earnings (Monthly) Card Example -->
                    <!-- Earnings (Monthly) Card Example -->
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
            <div class="card-header py-3" style="display: flex; justify-content: space-between">
                <h6 class="m-0 font-weight-bold text-primary">Cart</h6>
            </div>
            <form method="post" id="cartForm">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <tr>
                                <th></th>
                                <th hidden></th>
                                <th>Product</th>
                                <th hidden></th>
                                <th></th>
                                <th>Price (USD)</th>
                                <th>Quantity</th>
                                <th>Total value (USD)</th>
                                <th>Action</th>
                            </tr>
                            <c:set var="hasCartDetail" value="false"/>
                            <c:forEach var="cartDetail" items="${cart.cartDetails}">
                                <tr>
                                    <td><input type="checkbox" name="cartDetailID" value="${cartDetail.id}"
                                               class="myCheckBox">
                                    </td>
                                    <td hidden><input type="hidden" name="cartChecked" value="${cartDetail.checked}">
                                    </td>
                                    <td>${cartDetail.product.productName}</td>
                                    <td hidden><input type="hidden" value="${cartDetail.id}" name="cDetailID"></td>
                                    <td><img src="/images${cartDetail.product.image}" style="width: 145px;height: 90px"></td>
                                    <td>${cartDetail.product.price}</td>
                                    <td><input type="number" value="${cartDetail.quantity}" min="1"
                                               max="${cartDetail.product.quantity}"
                                               id="myInput" onblur="adjustValue()" name="quantities"
                                               oninput="updateTotalAmount(${cartDetail.product.price}, this.value,${cartDetail.id})"
                                    ></td>
                                    <td id="totalAmount_${cartDetail.id}">${cartDetail.totalAmount}</td>
                                    <td>
                                        <a href="/cart?action=deleteCD&id=${cartDetail.id}&idu=${user.id}"
                                           class="btn btn-danger ">Delete</a>
                                    </td>
                                </tr>
                                <c:set var="hasCartDetail" value="true"/>
                            </c:forEach>
                            <c:if test="${hasCartDetail}">
                                <tr>
                                    <td><input type="checkbox" id="selectAllCheckbox"> <!-- Checkbox to select all -->
                                    </td>
                                    <td hidden></td>
                                    <td>Select All</td>
                                    <td hidden></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td id="total_amount_chosen"></td>
                                    <td></td>
                                </tr>
                            </c:if>
                        </table>
                        <div>
                            <a class="btn btn-danger" onclick="setAction('/cart?action=delete')">Delete</a>
                            <a class="btn btn-primary" onclick="setAction('/cart?action=buy')">Buy product</a>
                            <a onclick="setActionCancel('/cart?action=updateCart')" class="btn btn-primary">Cancel</a>
                        </div>
                    </div>
                </div>
            </form>
            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>---------  Website Design by Huy - Long - Thắng Team  ---------</span>
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
                    <a class="btn btn-primary" href="/login?action=logout">Logout</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script>
    const message = document.getElementById('message');
    if (message !== null && message.innerHTML) {
        toastr.success(message.innerHTML);
    }
    var checkboxes = document.querySelectorAll('.myCheckBox');

    function setAction(action) {
        var checkedIds = [];

        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                checkedIds.push(checkboxes[i].id);
            }
        }
        if (checkedIds.length === 0) {
            window.location.href = "/cart?action=showCart";
        } else {
            document.getElementById('cartForm').action = action;
            document.getElementById('cartForm').submit();
        }
    }

    function setActionCancel(action) {
        document.getElementById('cartForm').action = action;
        document.getElementById('cartForm').submit();
    }

    const selectAllCheckbox = document.getElementById('selectAllCheckbox');
    const checkedboxes = document.getElementsByName('cartDetailID');

    selectAllCheckbox.addEventListener('change', function () {
        checkedboxes.forEach(function (checkbox) {
            checkbox.checked = selectAllCheckbox.checked;
        });
    });

    function adjustValue() {
        var inputElement = document.getElementById("myInput");
        var value = parseInt(inputElement.value);
        var min = parseInt(inputElement.getAttribute("min"));
        var max = parseInt(inputElement.getAttribute("max"));

        if (isNaN(value) || value < min) {
            inputElement.value = min;
        } else if (value > max) {
            inputElement.value = max;
        }
    }

    function updateTotalAmount(price, quantity, link) {
        var totalAmountElement = document.getElementById("totalAmount_" + link);
        var totalAmount = price * quantity;
        totalAmountElement.innerText = totalAmount;
    }

    /////
    // Lắng nghe sự kiện khi các checkbox được chọn hoặc bỏ chọn
    document.querySelectorAll('.myCheckBox').forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            updateTotalAmountChosen();
        });
    });

    // Lắng nghe sự kiện khi giá trị số lượng thay đổi
    document.querySelectorAll('input[name="quantities"]').forEach(function (quantityInput) {
        quantityInput.addEventListener('change', function () {
            updateTotalAmountChosen();
        });
    });

    document.getElementById('selectAllCheckbox').addEventListener('click', function () {
        var selectAllCheckbox = document.getElementById('selectAllCheckbox');
        var isChecked = selectAllCheckbox.checked;
        document.querySelectorAll('.myCheckBox').forEach(function (checkbox) {
            checkbox.checked = isChecked;
        });
        updateTotalAmountChosen();
    });

    // Hàm cập nhật giá trị tổng
    function updateTotalAmountChosen() {
        var totalAmount = 0;
        var checkboxes = document.querySelectorAll('.myCheckBox');
        var selectAllCheckbox = document.getElementById('selectAllCheckbox');
        var isChecked = selectAllCheckbox.checked;

        checkboxes.forEach(function (checkbox) {
            var row = checkbox.closest('tr');
            var priceElement = row.querySelector('td:nth-child(6)');
            var quantityInput = row.querySelector('input[name="quantities"]');
            var price = parseFloat(priceElement.textContent);
            var quantity = parseFloat(quantityInput.value);

            // Kiểm tra tính hợp lệ của giá trị price và quantity
            if (!isNaN(price) && !isNaN(quantity)) {
                if (checkbox.checked || isChecked) {
                    totalAmount += price * quantity;
                }
            }
        });

        // Kiểm tra nếu totalAmount là NaN và không hợp lệ, thì gán giá trị mặc định
        if (isNaN(totalAmount) || !isFinite(totalAmount)) {
            totalAmount = 0;
        }

        document.getElementById('total_amount_chosen').textContent = totalAmount;
    }

    document.addEventListener('DOMContentLoaded', function () {
        updateTotalAmountChosen();
    });
    document.querySelectorAll('.myCheckBox').forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            var hiddenInput = this.parentNode.nextElementSibling.querySelector('input[name="cartChecked"]');
            hiddenInput.value = this.checked ? '1' : '0';
        });
    });
    document.addEventListener('DOMContentLoaded', function () {
        // Lặp qua tất cả các checkbox và input ẩn
        document.querySelectorAll('.myCheckBox').forEach(function (checkbox) {
            var hiddenInput = checkbox.parentNode.nextElementSibling.querySelector('input[name="cartChecked"]');

            // Kiểm tra giá trị ban đầu của input ẩn
            if (hiddenInput.value === '1') {
                checkbox.checked = true; // Đặt trạng thái checked cho checkbox
            }
        });
    });
    window.onload = function () {
        updateTotalAmountChosen();
    };

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
