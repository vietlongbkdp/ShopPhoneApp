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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="card container px-6" style="height: 100vh">
        <h3 class="text-center">Create Product</h3>
        <form action="/product?action=create" method="post" enctype="multipart/form-data">
            <label for="productName" class="form-label">Product Name</label>
            <input type="text" class="form-control" id="productName" name="productName" value="${product.productName}">
            <div class="mb-3">
                <label for="image" class="form-label">Image</label>
                <input type="text" class="form-control" id="image" name="image" value="${product.image}">
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price</label>
                <input type="number" class="form-control" name="price" id="price" value="${product.price}">
            </div>
            <div class="mb-3">
                <label for="quantity" class="form-label">Quantity</label>
                <input type="text" class="form-control" name="quantity" id="quantity" value="${product.quantity}">
            </div>
            <div class="mb-3">
                <label for="warrantyPeriod" class="form-label">Warranty Period</label>
                <input type="text" class="form-control" name="warrantyPeriod" id="warrantyPeriod"
                       value="${product.warrantyPeriod}">
            </div>
            <div class="mb-3">
                <label for="ram" class="form-label">RAM</label>
                <input type="text" class="form-control" name="ram" id="ram" value="${product.ram}">
            </div>
            <div class="mb-3">
                <label for="size" class="form-label">Size</label>
                <input type="text" class="form-control" name="size" id="size" value="${product.size}">
            </div>
            <div class="mb-3">
                <label for="color" class="form-label">Color</label>
                <input type="text" class="form-control" name="color" id="color" value="${product.color}">
            </div>
            <div class="mb-3">
                <label for="camera" class="form-label">Camera</label>
                <input type="text" class="form-control" name="camera" id="camera" value="${product.camera}">
            </div>
            <div class="mb-3">
                <label for="operatingSystem" class="form-label">Operating System</label>
                <input type="text" class="form-control" name="operatingSystem" id="operatingSystem"
                       value="${product.operatingSystem}">
            </div>
            <div class="mb-3">
                <label for="pin" class="form-label">Pin</label>
                <input type="text" class="form-control" name="pin" id="pin" value="${product.pin}">
            </div>
            <div class="mb-3">
                <label for="branch" class="form-label">Branch</label>
                <select class="form-control" name="branch" id="branch">
                    <c:forEach var="branch" items="${branchs}">
                        <option value="${branch.id}"
                            ${branch.id == product.branch.id ?'selected' : ' ' }>
                        ${branch.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <a href="/product" class="btn btn-primary ">Cancel</a>
        </form>
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
</script>
</body>
</html>
