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
    <div class="card container px-6" style="height: 100vh">
        <h3 class="text-center">Shopping</h3>
        <c:if test="${message != null}">
            <h6 class="d-none" id="message">${message}</h6>
        </c:if>
        <form action="/cart">
            <div style="display: flex; align-items: center;">
                <div style="margin-right: 20px;">
                    <input type="text" id="searchProduct" name="search" class="form-control" placeholder="Search product">
                </div>
                <div>
                    <button id="searchButton" class="btn btn-primary">Search</button>
                </div>
            </div>
        </form>
        <div>
            <a href="/cart?action=showCart&id=${user.id}" class="btn btn-primary ">Cart</a>
        </div>
        
        <table class="table table-striped">
            <tr>
                <th>Id</th>
                <th>Product Name</th>
                <th>Branch</th>
                <th>Image</th>
                <th>Price</th>
                <th>Quantity</th>
                <%--                <th>Warranty Period</th>--%>
                <%--                <th>RAM</th>--%>
                <%--                <th>Size</th>--%>
                <%--                <th>Color</th>--%>
                <%--                <th>Camera</th>--%>
                <%--                <th>Operating System</th>--%>
                <%--                <th>Pin</th>--%>
                <th>Action</th>
            </tr>
            <c:set var="hasProducts" value="false" />
            <c:forEach var="product" items="${page.content}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.productName}</td>
                    <td>${product.branch.name}</td>
                    <td>${product.image}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>
                        <a href="/shopping?action=detail&id=${product.id}" class="btn btn-primary ">Product Detail</a>
                            <%--            <a class="btn btn-danger" onclick="return confirm('Do you want remove ${product.name} ?')" href="/product?action=delete&id=${product.id}">--%>
                            <%--              Delete--%>
                            <%--            </a>--%>
                        <a href="/cart?action=cart&id=${product.id}" class="btn btn-primary ">Add to Cart</a>

                    </td>
                </tr>
                <c:set var="hasProducts" value="true" />
            </c:forEach>
        </table>
        <c:if test="${hasProducts}">
            <nav aria-label="...">
                <c:set var="url" value="/product?page="/>
                <c:if test="${isShowRestore}">
                    <c:set var="url" value="/product?action=restore&page="/>
                </c:if>
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
        </c:if>
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