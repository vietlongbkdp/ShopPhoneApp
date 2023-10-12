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
    <title>Client Page</title>
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
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/main?action=shopping">
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
                    <h1 class="h3 mb-0 text-gray-800">Payment page</h1>
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
                <h6 class="m-0 font-weight-bold text-primary">Payment</h6>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <form method="post" id="cartForm" action="${pageContext.request.contextPath}/cart?action=payment">

                        <div class="card container px-6" style="height: 100vh">
                            <h3 class="text-center">Payment page</h3>
                            <c:if test="${message != null}">
                                <h6 class="d-none" id="message">${message}</h6>
                            </c:if>
                            <table class="table table-striped">
                                <tr>
                                    <th></th>
                                    <th>Product</th>
                                    <th></th>
                                    <th>Price (USD)</th>
                                    <th>Quantity</th>
                                    <th>Total value (USD)</th>
                                </tr>
                                <%--                <input type="text" name="quantity" value="10" />--%>
                                <c:if test="${not empty cartDetails}">
                                    <c:forEach var="cartDetail" items="${cartDetails}">
                                        <tr>
                                            <td><input type="hidden" name="cartChecked" value="${cartDetail.checked}"></td>
                                            <td>${cartDetail.product.productName}</td>
                                            <td><input hidden name="productIds" value="${cartDetail.product.id}">
                                                <input type="hidden" value="${cartDetail.id}" name="DetailIDS">
                                                    <img src="../images${cartDetail.product.image}" alt="" style="width: 145px;height: 90px">
                                                <input type="hidden" name="quantities" value="${cartDetail.quantity}"></td>
                                            <td>${cartDetail.product.price}</td>
                                            <td>${cartDetail.quantity}</td>
                                            <td id="totalAmount_${cartDetail.id}" class="total_detail">${cartDetail.totalAmount}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${not empty orderDetails}">
                                    <c:forEach var="od" items="${orderDetails}">
                                        <input hidden  type="hidden" name="productIds" value="${od.product.id}">
                                        <tr>
                                            <td></td>
                                            <td>${od.product.productName}</td>
                                            <td>
                                                <input type="hidden" value="${OrderDTs}" name="OrderDTs">
                                                    <img src="/images${od.product.image}" style="width: 145px;height: 90px">
                                                <input type="hidden" name="quantities" value="${od.quantity}"></td>
                                            <td>${od.product.price}</td>
                                            <td>${od.quantity}</td>
                                            <td id="totalAmount_${od.id}" class="total_detail">${od.totalAmount}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${not empty product}">
                                    <input type="hidden" value="${product.id}" name="productIds">
                                    <tr>
                                        <td></td>
                                        <td>${product.productName}</td>
                                        <td><input type="hidden" value="${quantityB}" name="quantityB">
                                            <img src="/images${product.image}" style="width: 145px;height: 90px">
                                            <input type="hidden" name="quantities" value="${quantityB}"></td>
                                        <td id ="price">${product.price}</td>
                                        <td id ="quantityB">${quantityB}</td>
                                        <td class="total_detail" id="total"></td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>Total cost (USD)</td>
                                    <td id="total_amount_chosen"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>VAT 10% (USD)</td>
                                    <td id="VAT"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>Total cost including VAT (USD)</td>
                                    <td id="total_cost_VAT"></td>
                                </tr>
                            </table>
                            <div>
                                <button type="submit" class="btn btn-primary">Payment</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
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
<script>
    const message = document.getElementById('message');
    if (message !== null && message.innerHTML) {
        toastr.success(message.innerHTML);
    }
    // function calculateTotal() {
    //     // Lấy giá trị của price và quantityB từ phần tử html
    //     var price = parseFloat(document.getElementById("price").innerText);
    //     var quantityB = parseInt(document.getElementById("quantityB").innerText);
    //
    //     // Tính tổng
    //     var total = price * quantityB;
    //
    //     // Cập nhật giá trị trong phần tử có id "total"
    //     document.getElementById("total").innerText = total;
    // }

    // Gọi hàm calculateTotal khi trang được tải hoặc khi có sự thay đổi trong giá trị price hoặc quantityB

    window.addEventListener('DOMContentLoaded', function () {
        // Lấy thông tin từ các phần tử HTML
        var priceElement = document.getElementById("price");
        var quantityBElement = document.getElementById("quantityB");
        var totalElement = document.getElementById("total");
        var totalAmountElements = document.getElementsByClassName("total_detail");
        var totalAmountChosenElement = document.getElementById("total_amount_chosen");
        var VATElement = document.getElementById("VAT");
        var totalCostVATElement = document.getElementById("total_cost_VAT");

        // Lấy giá trị từ các phần tử HTML
        var price = parseFloat(priceElement.innerText);
        var quantityB = parseInt(quantityBElement.innerText);

        // Tính toán tổng
        var total = price * quantityB;
        totalElement.innerText = total;

        // Tính toán tổng giá trị của các thẻ class="total_detail"
        var totalAmountChosen = 0;
        for (var i = 0; i < totalAmountElements.length; i++) {
            totalAmountChosen += parseFloat(totalAmountElements[i].innerText);
        }
        totalAmountChosenElement.innerText = totalAmountChosen;

        // Tính toán giá trị của VAT (10%)
        var VAT = totalAmountChosen * 0.1;
        VATElement.innerText = VAT;

        // Tính toán tổng giá trị bao gồm VAT
        var totalCostVAT = totalAmountChosen + VAT;
        totalCostVATElement.innerText = totalCostVAT;
    });
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
