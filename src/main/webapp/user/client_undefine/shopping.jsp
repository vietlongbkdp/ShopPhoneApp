<%--
  Created by IntelliJ IDEA.
  User: Long Nguyen
  Date: 05/10/2023
  Time: 01:49 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <!-- basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <!-- site metas -->
    <title>Eflyer</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- bootstrap css -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <!-- style css -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <!-- Responsive-->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- fevicon -->
    <link rel="icon" href="images/fevicon.png" type="image/gif" />
    <!-- Scrollbar Custom CSS -->
    <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <!-- Tweaks for older IEs-->
    <!-- fonts -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
    <!-- font awesome -->
    <!--  -->
    <!-- owl stylesheets -->
    <link href="https://fonts.googleapis.com/css?family=Great+Vibes|Poppins:400,700&display=swap&subset=latin-ext" rel="stylesheet">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesoeet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>
<body>
<!-- banner bg main start -->
<div class="banner_bg_main">
    <!-- header top section start -->
    <div class="container">
        <div class="header_section_top">
            <div class="row">
                <div class="col-sm-12">
                    <div class="custom_menu">
                        <ul style="display: flex; justify-content: center ">
                            <li style="padding-left: 5%"><a href="#">Best Sellers</a></li>
                            <li style="padding-left: 5%"><a href="#">Gift Ideas</a></li>
                            <li style="padding-left: 5%"><a href="#">New Releases</a></li>
                            <li style="padding-left: 5%"><a href="#">Today's Deals</a></li>
                            <li style="padding-left: 5%"><a href="#">Customer Service</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- header top section start -->
    <!-- logo section start -->
    <div class="logo_section">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="logo"><a href="index.html"><img src="images/logo.png"></a></div>
                </div>
            </div>
        </div>
    </div>
    <!-- logo section end -->
    <!-- header section start -->
    <div class="header_section">
        <div class="container">
            <div class="containt_main">
                <form action="/main?page=${page.currentPage}">
                <div class="dropdown">
                    <select style="min-width: 8rem" class="form-select btn btn-secondary" aria-label="Default select example" id="branch_select" value="${branch}" name="branch" onchange="updateBranch(this)">
                        <option selected>All Branch</option>
                        <c:forEach var="branchh" items="${branchs}">
                            <option${branch==branchh?"selected":""}>${branchh.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="main">
                    <!-- Another variation with a button -->
                    <div  class="input-group">
                        <input type="text" class="form-control" placeholder="Search this blog" id="str_search" name="search" value="${search}">
                        <div class="input-group-append">
                            <button class="btn btn-secondary" id="searchButton" type="button" style="background-color: #f26522; border-color:#f26522 ">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <div style="margin: 0" class="dropdown">
                    <select style="min-width: 8rem" class="form-select btn btn-secondary" aria-label="Default select example" id="price_range" value="${ePriceRange}" name="ePriceRange" onchange="updatePriceRange(this)">
                        <option selected>Price Range</option>
                        <c:forEach var="priceRanges" items="${PriceRange}">
                            <option ${ePriceRange==priceRanges?"selected":""}>${priceRanges.title}</option>
                        </c:forEach>
                    </select>
                </div>
                </form>
                <div class="header_box">
                    <div class="login_menu">
                        <ul>
                            <c:if test="${user.role.roleName != 'Admin'&&user.role.roleName != 'Staff'}">
                                <li><a href="#">
                                    <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                    <span class="padding_10">Cart</span><span style="color: red; font-weight: bold; font-size: small; margin-right: 2rem; padding-left: 0.5rem" class="padding_10">3</span></a>
                                </li>
                            </c:if>
                            <c:if test="${user != null}">
                                <li><a href="#">
                                    <span class="padding_10"></span></a>
                                    <div class="dropdown">
                                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fa fa-user" aria-hidden="true"></i>
                                                ${user.fullName}
                                        </button>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <a style="color: #1b1e21" class="dropdown-item" href="/shopping?action=profile">Profile</a>
                                            <c:if test="${user.role.roleName != 'Admin'&&user.role.roleName != 'Staff'}">
                                                <a style="color: #1b1e21" class="dropdown-item" href="/order-client">Order Management</a>
                                            </c:if>
                                            <c:if test="${user.role.roleName == 'Admin'||user.role.roleName == 'Staff'}">
                                                <a style="color: #1b1e21" class="dropdown-item" href="/total">Go to management page</a>
                                            </c:if>
                                            <a style="color: #1b1e21" class="dropdown-item" href="/login?action=logout&message=Logout Success!">Logout</a>
                                        </div>
                                    </div>
                                </li>
                            </c:if>
                            <c:if test="${user == null}">
                                <li><a href="/login">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                    <span class="padding_10">Login/Register</span></a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div id="carouselExampleInterval1" class="carousel slide" style="text-align: center; margin: 2rem" data-bs-ride="carousel">
            <div class="carousel-inner align-content-center">
                <div class="carousel-item align-content-center active" data-bs-interval="5000">
                    <img src="/user/client_undefine/img/slide_img/pc1.jpg" class=" w-90" alt="Picturte 1">
                </div>
                <div class="carousel-item align-content-center" data-bs-interval="5000">
                    <img src="/user/client_undefine/img/slide_img/pc2.jpg" class=" w-90" alt="Picturte 2">
                </div>
                <div class="carousel-item align-content-center" data-bs-interval="5000">
                    <img src="/user/client_undefine/img/slide_img/pc3.jpg" class=" w-90" alt="Picturte 3">
                </div>
                <div class="carousel-item align-content-center" data-bs-interval="5000">
                    <img src="/user/client_undefine/img/slide_img/pc4.jpg" class=" w-90" alt="Picturte 4">
                </div>
                <div class="carousel-item align-content-center" data-bs-interval="5000">
                    <img src="/user/client_undefine/img/slide_img/pc5.jpg" class=" w-90" alt="Picturte 5">
                </div>
                <div class="carousel-item align-content-center" data-bs-interval="5000">
                    <img src="/user/client_undefine/img/slide_img/pc6.jpg" class=" w-90" alt="Picturte 6">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval1" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval1" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
    <div style="background-color: #ceebe1; border-radius: 10px; border-style: solid; border-width: 2px; border-color: #1b1e21; margin-bottom: 2rem" class="container">
        <div class="d-flex justify-content-center"><h2  style="color: red">BEST SELLER OF MONTH</h2></div>
        <div class="row row-cols-1 row-cols-md-3 g-4 p-2">
            <c:forEach var="productBS" items="${productBSs}">
                <div class="col d-flex justify-content-center">
                    <div class="card" style="width: 17rem; border-radius: 8px">
                        <a href="/main?action=showDetailProduct&id=${productBS.id}"><img style="padding: 1rem" src="/images/${productBS.image}" class="card-img-top" alt="#"></a>
                        <div class="card-body text-center" >
                            <a href="/main?action=showDetailProduct&id=${productBS.id}"><h5 class="card-title">${productBS.productName}</h5></a>
                            <div class="row">
                                <div style="padding: 0.1rem" class="col-5"> <i class="fas fa-memory"></i> ${productBS.ram}</div>
                                <div style="padding: 0.1rem" class="col-7"> <i class="fas fa-database"></i> ${productBS.size}</div>
                            </div>
                            <div class="row">
                                <div style="padding: 0.1rem" class="col-5"> <i class="fas fa-battery-three-quarters"></i> ${productBS.pin}</div>
                            </div>
                                <div style="padding: 0.1rem" class="col-7"> <i class="fas fa-camera"></i> ${productBS.camera}</div>
                            <p style="color:red; font-weight: bold" class="card-text">${productBS.price}</p>
                            <c:if test="${user.role.roleName != 'Admin'&& user.role.roleName != 'Staff'}">
                                <div>
                                    <a href="/cart?action=cart&id=${user.id}" class="btn btn-warning"> <i class="fas fa-cart-plus"></i>AddCart</a>
                                    <a href="/main?action=showDetailProduct&id=${productBS.id}" class="btn btn-primary"><i class="fas fa-credit-card"></i> BuyNow</a>
                                </div>
                            </c:if>
                            <c:if test="${user.role.roleName == 'Admin'|| user.role.roleName == 'Staff'}">
                                <div>
                                    <a href="#buyNow" class="btn btn-primary"><i class="fas fa-info"></i>  Detail</a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="container">
        <div class="row row-cols-4 row-cols-md-4 g-4">
            <c:forEach var="pager" items="${page.content}">
                <div class="col d-flex justify-content-center">
                    <div class="card" style="width: 17rem; border-radius: 8px">
                        <a href="/main?action=showDetailProduct&id=${pager.id}"><img style="padding: 1rem" src="/images/${pager.image}" class="card-img-top" alt="Iphone 14"></a>
                        <div class="card-body text-center" >
                            <a href="/main?action=showDetailProduct&id=${pager.id}"><h5 class="card-title">${pager.productName}</h5></a>
                            <div class="row">
                                <div style="padding: 0.1rem" class="col-5"> <i class="fas fa-memory"></i> ${pager.ram}</div>
                                <div style="padding: 0.1rem" class="col-7"> <i class="fas fa-database"></i> ${pager.size}</div>
                            </div>
                            <div class="row">
                                <div style="padding: 0.1rem" class="col-5"> <i class="fas fa-battery-three-quarters"></i> ${pager.pin}</div>
                            </div>
                                <div style="padding: 0.1rem" class="col-7"> <i class="fas fa-camera"></i> ${pager.camera}</div>
                            <p style="color:red; font-weight: bold" class="card-text">${pager.price}</p>
                            <c:if test="${user.role.roleName != 'Admin'&& user.role.roleName != 'Staff'}">
                                <div>
                                    <a href="/cart?action=cart&id=${user.id}" class="btn btn-warning"> <i class="fas fa-cart-plus"></i>AddCart</a>
                                    <a href="/main?action=showDetailProduct&id=${pager.id}" class="btn btn-primary"><i class="fas fa-credit-card"></i> BuyNow</a>
                                </div>
                            </c:if>
                            <c:if test="${user.role.roleName == 'Admin'|| user.role.roleName == 'Staff'}">
                                <div>
                                    <a href="#buyNow" class="btn btn-primary"><i class="fas fa-info"></i>  Detail</a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item <c:if test="${page.currentPage == 1}">disabled</c:if>">
                    <a class="page-link" href="${url}${(page.currentPage - 1)}" tabindex="-1"
                       aria-disabled="true">Previous</a>
                </li>
                <c:forEach var="number" begin="1" end="${page.totalPage}">
                    <c:if test="${number == page.currentPage}">
                        <li class="page-item active" aria-current="page">
                            <a class="page-link" href="${url}${number}">${number}</a>
                        </li>
                    </c:if>
                    <c:if test="${number != page.currentPage}">
                        <li class="page-item">
                            <a class="page-link" href="${url}${number}">${number}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <li class="page-item <c:if test="${page.currentPage == page.totalPage}">disabled</c:if>">
                    <a class="page-link" href="${url}${(page.currentPage + 1)}">Next</a>
                </li>
            </ul>
        </nav>
    </div>
<!-- jewellery  section end -->
<!-- footer section start -->
<div class="footer_section layout_padding">
    <div class="container">
        <div class="footer_logo"><a href="index.html"><img src="images/footer-logo.png"></a></div>
        <div class="input_bt">
            <input type="text" class="mail_bt" placeholder="Your Email" name="Your Email">
            <span class="subscribe_bt" id="basic-addon2"><a href="#">Subscribe</a></span>
        </div>
        <div class="footer_menu">
            <ul>
                <li><a href="#">Best Sellers</a></li>
                <li><a href="#">Gift Ideas</a></li>
                <li><a href="#">New Releases</a></li>
                <li><a href="#">Today's Deals</a></li>
                <li><a href="#">Customer Service</a></li>
            </ul>
        </div>
        <div class="location_main">Help Line  Number : <a href="#">+1 1800 1200 1200</a></div>
    </div>
</div>
<!-- footer section end -->
<!-- copyright section start -->
<div class="copyright_section">
    <div class="container">
        <p class="copyright_text">© 2020 All Rights Reserved. Design by <a href="https://html.design">Free html  Templates</a></p>
    </div>
</div>
</div>
<!-- copyright section end -->
<!-- Javascript files-->
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery-3.0.0.min.js"></script>
<script src="js/plugin.js"></script>
<!-- sidebar -->
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="js/custom.js"></script>
<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
    var priceRangeSelect = document.getElementById('price_range');
    var priceRangeOptions = priceRangeSelect.getElementsByTagName('option');

    for (var i = 0; i < priceRangeOptions.length; i++) {
        var value = priceRangeOptions[i].value;
        var displayText = '';

        switch (value) {
            case 'UNDER_100_USD':
                displayText = 'UNDER 100 USD';
                break;
            case 'RANGE_100_299_USD':
                displayText = '100-299 USD';
                break;
            case 'RANGE_300_499_USD':
                displayText = '300-499 USD';
                break;
            case 'RANGE_500_699_USD':
                displayText = '500-699 USD';
                break;
            case 'RANGE_700_999_USD':
                displayText = '700-999 USD';
                break;
            case 'OVER_999_USD':
                displayText = 'OVER 999 USD';
                break;
            default:
                displayText = value; // Nếu không có giá trị khớp, sử dụng giá trị gốc
                break;
        }

        priceRangeOptions[i].textContent = displayText;
    }
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossOrigin="anonymous"></script>
</script>
<script>
    function updatePriceRange(selectElement) {
        var selectedValue = selectElement.value;
        var ePriceRangeElement = document.getElementsByName("ePriceRange")[0];
        ePriceRangeElement.value = selectedValue;
    }
</script>
<script>
    function updateBranch(selectElement) {
        var selectedValue = selectElement.value;
        var branchElement = document.getElementsByName("branch")[0];
        branchElement.value = selectedValue;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>
