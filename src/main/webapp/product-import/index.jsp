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
  <title>Title</title>
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
            <h6 class="m-0 font-weight-bold text-primary">Product Import Management</h6>
            <a href="/product-import?action=create" class="btn btn-primary ">Create new Product</a>


          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>Code</th>
                  <th>Date Import</th>
                  <th>Products</th>
                  <th>Total</th>
                  <th>Action</th>
                </tr>
                </thead>
                <c:forEach var="productImport" items="${productImports}">
                  <tr>
                    <td>${productImport.id}</td>
                    <td>${productImport.code}</td>
                    <td>${productImport.importDate}</td>
                    <td>${productImport.products}</td>
                    <td>${productImport.totalAmount}</td>
                    <td>
                      <a class="btn btn-info"
                         href="/product-import?action=edit&id=${productImport.id}">Edit</a>
                      <a class="btn btn-info"
                         href="/product-import?action=detail&id=${productImport.id}">Detail</a>
                    </td>
                  </tr>
                  <c:set var="hasProducts" value="true"/>
                </c:forEach>
              </table>
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
              <div class="modal-body">Select "Logout" below if you are ready to end your current
                session.
              </div>
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
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
      </div>
      <!-- JavaScript Libraries -->
      <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
      <script src="../lib/chart/chart.min.js"></script>
      <script src="../lib/easing/easing.min.js"></script>
      <script src="../lib/waypoints/waypoints.min.js"></script>
      <script src="../lib/owlcarousel/owl.carousel.min.js"></script>
      <script src="../lib/tempusdominus/js/moment.min.js"></script>
      <script src="../lib/tempusdominus/js/moment-timezone.min.js"></script>
      <script src="../lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
      <!-- Template Javascript -->
      <script src="../js/main.js"></script>
      <!-- Code injected by live-server -->
      <script>
        // <![CDATA[  <-- For SVG support
        if ('WebSocket' in window) {
          (function () {
            function refreshCSS() {
              var sheets = [].slice.call(document.getElementsByTagName("link"));
              var head = document.getElementsByTagName("head")[0];
              for (var i = 0; i < sheets.length; ++i) {
                var elem = sheets[i];
                var parent = elem.parentElement || head;
                parent.removeChild(elem);
                var rel = elem.rel;
                if (elem.href && typeof rel != "string" || rel.length == 0 || rel.toLowerCase() == "stylesheet") {
                  var url = elem.href.replace(/(&|\?)_cacheOverride=\d+/, '');
                  elem.href = url + (url.indexOf('?') >= 0 ? '&' : '?') + '_cacheOverride=' + (new Date().valueOf());
                }
                parent.appendChild(elem);
              }
            }
            var protocol = window.location.protocol === 'http:' ? 'ws://' : 'wss://';
            var address = protocol + window.location.host + window.location.pathname + '/ws';
            var socket = new WebSocket(address);
            socket.onmessage = function (msg) {
              if (msg.data == 'reload') window.location.reload();
              else if (msg.data == 'refreshcss') refreshCSS();
            };
            if (sessionStorage && !sessionStorage.getItem('IsThisFirstTime_Log_From_LiveServer')) {
              console.log('Live reload enabled.');
              sessionStorage.setItem('IsThisFirstTime_Log_From_LiveServer', true);
            }
          })();
        } else {
          console.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
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
