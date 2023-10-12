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
    <div>
        <a href="/main" class="btn btn-primary ">Home</a>
    </div>
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
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total value</th>
                </tr>
                <%--                <input type="text" name="quantity" value="10" />--%>
                <c:if test="${not empty cartDetails}">
                    <c:forEach var="cartDetail" items="${cartDetails}">
                        <tr>
                            <td><input type="hidden" name="cartChecked" value="${cartDetail.checked}"></td>
                            <td>${cartDetail.product.productName}</td>
                            <td><input name="productIds" value="${cartDetail.product.id}">
                                <input type="hidden" value="${cartDetail.id}" name="DetailIDS">
                                <input type="hidden" name="quantities" value="${cartDetail.quantity}"></td>
                            <td>${cartDetail.product.price}</td>
                            <td>${cartDetail.quantity}</td>
                            <td id="totalAmount_${cartDetail.id}" class="total_detail">${cartDetail.totalAmount}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${not empty orderDetails}">
                    <c:forEach var="od" items="${orderDetails}">
                        <input type="hidden" name="productIds" value="${od.product.id}">
                        <tr>
                            <td></td>
                            <td>${od.product.productName}</td>
                            <td>
                                <input type="hidden" value="${OrderDTs}" name="OrderDTs">
                                <img src="/images${od.product.image}" style="width: 145px;height: 90px">
                                <input type="hidden" name="quantities" value="${od.quantity}"></td>
                            <td>${od.product.price}></td>
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
                    <td>Total cost</td>
                    <td id="total_amount_chosen"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>VAT 10%</td>
                    <td id="VAT"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>Total cost including VAT</td>
                    <td id="total_cost_VAT"></td>
                </tr>
            </table>
            <div>
                <button type="submit" class="btn btn-primary">Payment</button>
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
    // window.onload = calculateTotal;
    window.addEventListener('DOMContentLoaded', function () {
        var price = parseFloat(document.getElementById("price").innerText);
        var quantityB = parseInt(document.getElementById("quantityB").innerText);

        // Tính tổng
        var total = price * quantityB;

        // Cập nhật giá trị trong phần tử có id "total"
        document.getElementById("total").innerText = total;
        var totalAmountChosen = document.getElementById('total_amount_chosen');
        var vatElement = document.getElementById('VAT');
        var totalCostVATElement = document.getElementById('total_cost_VAT');
        // Lấy danh sách các thẻ có class "total_detail"
        var totalDetailElements = document.getElementsByClassName('total_detail');
        // Tính tổng giá trị từ các thẻ "total_detail"
        var totalAmount = 0;
        for (var i = 0; i < totalDetailElements.length; i++) {
            totalAmount += parseFloat(totalDetailElements[i].innerText);
        }
        // Tính giá trị VAT
        var vat = totalAmount * 0.1;
        // Tính tổng giá trị bao gồm VAT
        var totalCostVAT = totalAmount + vat;
        // Chèn giá trị vào các thẻ tương ứng
        totalAmountChosen.innerText = totalAmount.toFixed(1) +' USD';
        vatElement.innerText = vat.toFixed(1)+' USD';
        totalCostVATElement.innerText = totalCostVAT.toFixed(1)+' USD';
        document.getElementById("total").innerText = total.toFixed(1)+' USD'
    });

</script>
</body>
</html>