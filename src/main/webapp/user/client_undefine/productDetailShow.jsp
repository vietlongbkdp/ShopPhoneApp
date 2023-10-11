<%--
  Created by IntelliJ IDEA.
  User: Long Nguyen
  Date: 10/10/2023
  Time: 11:36 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        .icon-hover:hover {
            border-color: #3b71ca !important;
            background-color: white !important;
            color: #3b71ca !important;
        }

        .icon-hover:hover i {
            color: #3b71ca !important;
        }
    </style>
</head>
<body>
<header>
    <div style="background-color: #e3b53d; margin-top: 2rem">
        <div class="container py-4">
            <nav class="d-flex">
                <h6 class="mb-0">
                    <a href="/main"  style="font-size: 30px; font-weight: bold; text-decoration: none; color: black; padding: 2rem" ><i class="fas fa-cart-plus"></i> SHOPPING</a>
                </h6>
            </nav>
        </div>
    </div>
</header>
<section class="py-5">
    <div class="container">
        <div class="row gx-5">
            <aside class="col-lg-6">
                <div class="border rounded-4 mb-3 d-flex justify-content-center">
                    <a data-fslightbox="mygalley" class="rounded-4" target="_blank" data-type="image" href="#">
                        <img style="max-width: 100%; max-height: 100vh; margin: auto;" class="rounded-4 fit" src="/images/${product.image}" />
                    </a>
                </div>
            </aside>
            <main class="col-lg-6">
                <div class="ps-lg-3">
                    <h4 class="title text-dark">${product.productName}</h4>
                        <span class="text-muted"><i class="fas fa-shopping-basket fa-sm mx-1"></i>${product.quantity} product</span>
                        <span class="text-success ms-2">Inventory</span>
                    </div>

                    <div style="margin: 2rem; color: #b21f2d" class="mb-3">
                        <span class="h3">$${product.price}</span>
                        <span class="text-muted">/per peice</span>
                    </div>

                    <p>
                        Ceramic Shield front
                        Glass back and aluminum design
                    </p>

                    <div class="row">
                        <dt class="col-5"><i class="fas fa-file-code"></i> Operating System: </dt>
                        <dd class="col-7">${product.operatingSystem}</dd>

                        <dt class="col-5"><i class="fas fa-memory"></i> RAM: </dt>
                        <dd class="col-7">${product.ram}</dd>

                        <dt class="col-5"><i class="fas fa-database"></i> Storage: </dt>
                        <dd class="col-7">${product.size}</dd>

                        <dt class="col-5"><i class="fas fa-palette"></i> Color: </dt>
                        <dd class="col-7">${product.color}</dd>

                        <dt class="col-5"><i class="fas fa-camera"></i> Camera: </dt>
                        <dd class="col-7">${product.camera}</dd>

                        <dt class="col-5"><i class="fas fa-battery-three-quarters"></i> Battery Capacity: </dt>
                        <dd class="col-7">${product.pin}</dd>

                        <dt class="col-5"><i class="fas fa-screwdriver-wrench"></i> Warranty Period</dt>
                        <dd class="col-7">${product.warrantyPeriod}</dd>
                    </div>

                    <hr />

                    <div class="row mb-4">
                        <div class="col-md-4 col-6 mb-3">
                            <div class="input-group mb-3" style="width: 170px;">
                                <label style="margin-right: 1rem; vertical-align: center; text-align: center">Quantity</label>
                                <input type="number" class="form-control text-center border border-secondary" placeholder="0" aria-label="Example text with button addon"  value="1" min="0" max="10" aria-describedby="button-addon1" />
                            </div>
                        </div>
                    </div>
                    <a href="#" class="btn btn-warning shadow-0"> Buy now </a>
                    <a href="/cart?action=cart&id=${product.id}" class="btn btn-primary shadow-0"> <i class="me-1 fa fa-shopping-basket"></i> Add to cart </a>
            </main>
        </div>
    </div>
</section>
<footer class="text-center text-lg-start text-muted bg-primary mt-3">

    <div class="">
        <div class="container">
            <div class="d-flex justify-content-between py-4 border-top row">
                <!--- payment --->
                <div class="col-4">
                    <i class="fab fa-lg fa-cc-visa text-white"></i>
                    <i class="fab fa-lg fa-cc-amex text-white"></i>
                    <i class="fab fa-lg fa-cc-mastercard text-white"></i>
                    <i class="fab fa-lg fa-cc-paypal text-white"></i>
                </div>
                <div class="col-8">
                    <p style="color: white">-------------------- LONG - HUY - THẮNG TEAM ----------------------</p>
                </div>
            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
