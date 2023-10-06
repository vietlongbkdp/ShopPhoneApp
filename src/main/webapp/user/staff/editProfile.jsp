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
    <title>Edit Profile</title>
    <link href="/user/admin/assets/all.min.css" rel="stylesheet" type="text/css">
    <link href="/user/admin/assets/sb-admin-2.min.css" rel="stylesheet">
    <link href="/user/admin/assets/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/toastr@2.1.4/build/toastr.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/toastr@2.1.4/build/toastr.min.css" rel="stylesheet">
</head>
<body id="page-top">
<div id="wrapper">
    <c:if test="${message != null}">
        <h6 class="d-none" id="message">${message}</h6>
    </c:if>
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/shopping?action=shopping">
            <div class="sidebar-brand-icon">
                <i class="fas fa-home"></i>
            </div>
            <div class="sidebar-brand-text mx-3">HOME</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item">
            <a class="nav-link" href="/admin?action=userManager">
                <i class="fas fa-users-cog"></i>
                <span>User Management</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Sales
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="/product" aria-expanded="true" >
                <i class="fas fa-fw fa-table"></i>
                <span>Product</span>
            </a>
        </li>

        <!-- Nav Item - Utilities Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="/shopping" aria-expanded="true" >
                <i class="fas fa-file-invoice"></i>
                <span>Order</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="/branch" aria-expanded="true" >
                <i class="fas fa-mobile"></i>
                <span>Branch</span>
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
                            <a class="dropdown-item" href="#">
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
                <form class="user" action="/cart?action=editProfile" method="post">
                    <input type="hidden" name="url" id="urlInput">
                    <div class="sidebar-brand-text mx-5 mb-3 font-weight-bold">EDIT PROFILE</div>
                    <div class="col-sm-6 mb-3 mb-sm-3">User Name
                        <input type="text" class="form-control form-control-user" id="userName"  placeholder="User Name" name="userName" required value="${user.userName}">
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-3"> Full Name
                        <input type="text" class="form-control form-control-user" id="fullName"  placeholder="Full Name" name="fullName" required value="${user.fullName}">
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-3"> Email
                        <input type="email" class="form-control form-control-user" id="email"  placeholder="Email" name="email" required value="${user.email}">
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-3"> Address
                        <input type="text" class="form-control form-control-user" id="address"  placeholder="Address" name="address" required value="${user.address}">
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-3"> Phone
                        <input type="text" class="form-control form-control-user" id="phone"  placeholder="Phone" name="phone" required value="${user.phone}">
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-3"> Date of Birth
                        <input type="date" class="form-control form-control-user" id="dob"  placeholder="Date of Birth" name="dob" required value="${user.dob}">
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-3">
                        <label for="gender" class="form-label">Gender</label>
                        <select class="form-control" id="gender" name="gender">
                            <c:forEach var="gender" items="${genders}">
                                <option value="${gender}" ${gender == user.gender ? 'selected' : ''} >${gender}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" value="Submit" class="btn btn-primary">
                </form>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

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
<script>
    const message = document.getElementById('message');
    if (message !== null && message.innerHTML) {
        toastr.success(message.innerHTML);
    }
    var checkboxes = document.querySelectorAll('.myCheckBox');
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

