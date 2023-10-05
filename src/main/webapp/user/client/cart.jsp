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
            <h3 class="text-center">Shopping Cart</h3>
            <c:if test="${message != null}">
                <h6 class="d-none" id="message">${message}</h6>
            </c:if>
            <table class="table table-striped">
                <tr>
                    <th></th>
                    <th>Product</th>
                    <th></th>
                    <th></th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total value</th>
                    <th>Action</th>
                </tr>
                <c:set var="hasCartDetail" value="false"/>
                <c:forEach var="cartDetail" items="${cart.cartDetails}">
                    <tr>
                        <td><input type="checkbox" name="cartDetailID" value="${cartDetail.id}" class="myCheckBox">
                        </td>
                        <td>${cartDetail.product.productName}</td>
                        <td><input type="hidden" value="${cartDetail.id}"name="cDetailID"></td>
                        <td>${cartDetail.product.image}</td>
                        <td>${cartDetail.product.price}</td>
                        <td><input type="number" value="${cartDetail.quantity}" min="1"
                                   max="${cartDetail.product.quantity}"
                                   id="myInput" onblur="adjustValue()" name="quantities"
                                   oninput="updateTotalAmount(${cartDetail.product.price}, this.value,${cartDetail.id})"
                        ></td>
                        <td id="totalAmount_${cartDetail.id}">${cartDetail.totalAmount}</td>
                        <td>
                            <a href="/cart?action=deleteCD&id=${cartDetail.id}" class="btn btn-primary ">Delete</a>
                        </td>
                    </tr>
                    <c:set var="hasCartDetail" value="true"/>
                </c:forEach>
                <c:if test="${hasCartDetail}">
                    <tr>
                        <td><input type="checkbox" id="selectAllCheckbox"> <!-- Checkbox to select all --></td>
                        <td>Select All</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </c:if>
            </table>
            <div>
                <a class="btn btn-primary" onclick="setAction('/cart?action=delete',${user.id})">Delete</a>
                <a class="btn btn-primary" onclick="setAction('/cart?action=buy')">Buy product</a>
                <a onclick="setActionCancel('/cart?action=updateCart')" class="btn btn-primary">Cancel</a>
            </div>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script>
    const message = document.getElementById('message');
    if (message !== null && message.innerHTML) {
        toastr.success(message.innerHTML);
    }
    var checkboxes = document.querySelectorAll('.myCheckBox');

    function setAction(action, link) {
        var checkedIds = [];
        var url = link;
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                checkedIds.push(checkboxes[i].id);
            }
        }
        if (checkedIds.length === 0) {
            window.location.href = "/cart?action=showCart&id="+url;
        } else {
            document.getElementById('cartForm').action = action;
            document.getElementById('cartForm').submit();
        }
    }
    function setActionCancel(action){
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

        if (   isNaN(value) || value < min) {
            inputElement.value = min;
        } else if (value > max) {
            inputElement.value = max;
        }
    }
    function updateTotalAmount(price, quantity,link) {
        var totalAmountElement = document.getElementById("totalAmount_"+link);
        var totalAmount = price * quantity;
        totalAmountElement.innerText = totalAmount;
    }
</script>
</body>
</html>