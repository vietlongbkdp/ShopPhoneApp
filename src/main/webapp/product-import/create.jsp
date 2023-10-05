<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta charset="utf-8">
    <title>DASHMIN - Bootstrap Admin Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/toastr@2.1.4/build/toastr.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/toastr@2.1.4/build/toastr.min.css" rel="stylesheet">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="">
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&amp;display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="../lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="../css/style.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/toastr@2.1.4/build/toastr.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/toastr@2.1.4/build/toastr.min.css" rel="stylesheet">
</head>

<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
    <!-- Spinner Start -->
    <div id="spinner" class="bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->


    <!-- Sidebar Start -->
    <div class="sidebar pe-4 pb-3">
        <nav class="navbar bg-light navbar-light">
            <a href="/admin" class="navbar-brand mx-4 mb-3">
                <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>DASHMIN</h3>
            </a>
            <div class="d-flex align-items-center ms-4 mb-4">
                <div class="position-relative">
                    <img class="rounded-circle" src="img/user.jpg" alt="" style="width: 40px; height: 40px;">
                    <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                </div>
                <div class="ms-3">
                    <h6 class="mb-0">ADMIN</h6>
                    <span>Admin</span>
                </div>
            </div>
            <div class="navbar-nav w-100">
                <a href="/product" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Product</a>
                <a href="/product-import" class="nav-item nav-link"><i class="fa fa-keyboard me-2"></i>Product Import</a>
                <a href="/user" class="nav-item nav-link "><i class="fa fa-table me-2"></i>User</a>
                <a href="/order" class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>Order</a>
                <a href="/express" class="nav-item nav-link active"><i class="fa fa-chart-bar me-2"></i>Express</a>
            </div>
        </nav>
    </div>
    <!-- Sidebar End -->


    <!-- Content Start -->
    <div class="content">
        <div class="container">
            <div class="card container px-6" style="height: 100vh">
                <h3 class="text-center">Create Product Import</h3>
                <form action="/product-import?action=create" method="post" onsubmit="return">
                    <div class="mb-3">
                        <label for="code" class="form-label">Import Code</label>
                        <input type="text" class="form-control" id="code" name="code" required="true">
                        <div id="code-error" class="text-danger"></div>
                    </div>
                    <div class="mb-3">
                        <label for="importDate" class="form-label">Import Date</label>
                        <input type="date" class="form-control" id="importDate" name="importDate" required="true">
                        <div id="importDate-error" class="text-danger"></div>
                    </div>
                    <div class="row mb-3">
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
                        <div class="row mb-3" id="product-import-1">
                            <div class="col-4">
                                <select class="form-control" onchange="onChangeSelect(this)" name="productIds" id="product" required>
                                    <option value="">---Please Choose---</option>
                                    <c:forEach var="product" items="${products}">
                                        <option value="${product.id}">${product.productName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-3">
                                <input type="number" class="form-control" name="quantities" required>
                            </div>
                            <div class="col-3">
                                <input type="number" class="form-control" name="amounts" required>
                            </div>
                            <div class="col-2 d-flex justify-content-end">
                                <button type="button" class="btn btn-danger" onclick="deleteRow(1)">Delete</button>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Create Import</button>
                </form>
            </div>

        </div>
    </div>
    <!-- Content End -->


    <!-- Back to Top -->
    <%--  <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>--%>
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
    var codeInput = document.getElementById('code');
    var codeError = document.getElementById('code-error');

    codeInput.addEventListener('blur', function (){
        var codeValue = codeInput.value;
        if (codeValue .length < 6) {
            codeInput.classList.add('is-invalid'); // Thêm lớp CSS 'is-invalid' để hiển thị viền đỏ
            codeError.textContent = 'Code phải có ít nhất 6 kí tự'; // Hiển thị thông báo lỗi
        } else {
            codeInput.classList.remove('is-invalid'); // Xóa lớp CSS 'is-invalid'
            codeError.textContent = ''; // Xóa thông báo lỗi
        }
    })

    document.querySelector('form').addEventListener('submit', function(event) {

        var codeValue = codeInput.value;
        if (codeValue .length < 6) {
            event.preventDefault(); // Ngăn chặn gửi form đi

            codeInput.classList.add('is-invalid'); // Thêm lớp CSS 'is-invalid' để hiển thị viền đỏ
            codeError.textContent = 'Password phải có ít nhất 6 kí tự'; // Hiển thị thông báo lỗi

            codeInput.focus(); // Tập trung vào trường password không hợp lệ
        }


    });

    var importDateInput = document.getElementById('importDate');
    var importDateError = document.getElementById('importDate-error');

    importDateInput .addEventListener("blur", function() {
        var importDateValue = new Date(importDateInput.value).getTime();
        var currentDate = new Date().getTime();

        if (importDateValue > currentDate) {
            importDateInput.classList.add("is-invalid");
            importDateError.textContent = "Ngày không hợp lệ, không phải là ngày tương lai";
        } else {
            importDateInput.classList.remove("is-invalid");
            importDateError.textContent = "";
        }

    });
    // <![CDATA[  <-- For SVG support
    // if ('WebSocket' in window) {
    //   (function () {
    //     function refreshCSS() {
    //       var sheets = [].slice.call(document.getElementsByTagName("link"));
    //       var head = document.getElementsByTagName("head")[0];
    //       for (var i = 0; i < sheets.length; ++i) {
    //         var elem = sheets[i];
    //         var parent = elem.parentElement || head;
    //         parent.removeChild(elem);
    //         var rel = elem.rel;
    //         if (elem.href && typeof rel != "string" || rel.length == 0 || rel.toLowerCase() == "stylesheet") {
    //           var url = elem.href.replace(/(&|\?)_cacheOverride=\d+/, '');
    //           elem.href = url + (url.indexOf('?') >= 0 ? '&' : '?') + '_cacheOverride=' + (new Date().valueOf());
    //         }
    //         parent.appendChild(elem);
    //       }
    //     }
    //     var protocol = window.location.protocol === 'http:' ? 'ws://' : 'wss://';
    //     var address = protocol + window.location.host + window.location.pathname + '/ws';
    //     var socket = new WebSocket(address);
    //     socket.onmessage = function (msg) {
    //       if (msg.data == 'reload') window.location.reload();
    //       else if (msg.data == 'refreshcss') refreshCSS();
    //     };
    //     if (sessionStorage && !sessionStorage.getItem('IsThisFirstTime_Log_From_LiveServer')) {
    //       console.log('Live reload enabled.');
    //       sessionStorage.setItem('IsThisFirstTime_Log_From_LiveServer', true);
    //     }
    //   })();
    // }
    // else {
    //   console.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
    // }

    const message = document.getElementById('message');
    if (message !== null && message.innerHTML) {
        toastr.success(message.innerHTML);
    }

    const productId = document.getElementById('product');
    const productImportDetail = document.getElementById('product-import-detail');
    const eSelect = document.getElementsByName('productIds');
    const products = ${productsJSON};
    let rowProductImport = 1;
    let rowCount = rowProductImport;

    function addMore() {
        if(rowCount === products.length) return
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
    function onChangeSelect(e){
        if(productsSelected.find(id => +id === +e.value)){
            e.value = '';

        }
        productsSelected = Array.from(eSelect).map(e => e.value);
    }
    // ]]>
</script>


</body></html>