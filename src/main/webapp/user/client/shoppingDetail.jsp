<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 10/10/2023
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/toastr@2.1.4/build/toastr.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/toastr@2.1.4/build/toastr.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form method="post" id="cartForm">
        <div class="card container px-6" style="height: 100vh">
            <h3 class="text-center">${product.productName}</h3>
            <c:if test="${message != null}">
                <h6 class="d-none" id="message">${message}</h6>
            </c:if>
            <input type="hidden" name="idProduct" value="${product.id}">
            <table class="table table-striped">
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
                    <tr>
                        <td>${product.productName}</td>
                        <td>${product.price}</td>
                        <td><input type="number" name="quantityB" min="1" max="${product.quantity}" onblur="adjustValue()" id="myInput"></td>
                        <td>
                            <a class="btn btn-primary" onclick="setAction('/cart?action=buyNow')">Buy now</a>
                            <a class="btn btn-primary" onclick="setAction('/cart?action=addCart')">Add to Cart</a>
                        </td>
                    </tr>
            </table>
        </div>
    </form>
</div>
<script>
    function adjustValue() {
        var inputElement = document.getElementById("myInput");
        var value = parseInt(inputElement.value);
        var min = parseInt(inputElement.getAttribute("min"));
        var max = parseInt(inputElement.getAttribute("max"));

        if (   isNaN(value) || value < min) {
            inputElement.value = min;
        } else if (value > max) {
            inputElement.value = max;
        }
    } function setActionCancel(action){
        document.getElementById('cartForm').action = action;
        document.getElementById('cartForm').submit();
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossOrigin="anonymous"></script>
</body>
</html>
