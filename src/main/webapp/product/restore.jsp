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
</head>
<body>
<div class="container">
  <h3 class="text-center">Restore Product</h3>
  <c:if test="${message != null}">
    <h6 class="d-none" id="message">${message}</h6>
  </c:if>

  <form action="/product" method="get">
    <input type="hidden" name="action" value="showRestore"> <!-- Thêm input hidden để truyền giá trị action -->
    <input type="text" id="searchBook" value="${search}" name="search" class="form-control d-flex" placeholder="Search product">
    <button id="searchButton" class="btn btn-primary">Search</button>
  </form>
  <form action="/product?action=restore" method="post">
    <div class="card container px-6" style="height: 100vh">
      <table class="table table-striped">
      <tr>
        <th>Id</th>
        <th>Product Name</th>
        <th>Branch</th>
        <th>Image</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Warranty Period</th>
        <th>RAM</th>
        <th>Size</th>
        <th>Color</th>
        <th>Camera</th>
        <th>Operating System</th>
        <th>Pin</th>
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
          <td>${product.warrantyPeriod}</td>
          <td>${product.ram}</td>
          <td>${product.size}</td>
          <td>${product.color}</td>
          <td>${product.camera}</td>
          <td>${product.operatingSystem}</td>
          <td>${product.pin}</td>
          <td>
            <input type="checkbox" name="restoredProduct" value="${product.id}" class="myCheckBox">
          </td>
        </tr>
          <c:set var="hasProducts" value="true" />
        </c:forEach>
        <c:if test="${hasProducts}">
          <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>Select All</td>
            <td><input type="checkbox" id="selectAllCheckbox"> <!-- Checkbox to select all --></td>
          </tr>
        </c:if>

      </table>
      <c:if test="${hasProducts}">
        <nav aria-label="...">
          <c:set var="url" value="/product?page="/>
          <c:if test="${isShowRestore}">
            <c:set var="url" value="/product?action=showRestore&page="/>
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
      <div>
        <a class="btn btn-primary" onclick="showConfirm()">Restore</a>
        <a href="/product" class="btn btn-primary ">Cancel</a>
      </div>
      <div id="confirmDialog" class="modal">
        <div class="modal-content">
          <p>Are you sure to restore?</p>
          <div class="button-container">
            <button type="submit" class="btn btn-primary mb-2" >Yes</button>
            <button onclick="hideConfirmDialog()">No</button>
          </div>
        </div>
      </div>

    </div>
  </form>
</div>
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
  var checkboxes = document.querySelectorAll('.myCheckBox');
  var checkedIds = [];
  function hideConfirmDialog() {
    document.getElementById("confirmDialog").style.display = "none";
  }
  function showConfirm(){
    for (var i = 0; i < checkboxes.length; i++) {
      if (checkboxes[i].checked) {
        checkedIds.push(checkboxes[i].id);
      }
    }
    if (checkedIds.length===0){
      window.location.href="/product?action=showRestore"
    }else {
      document.getElementById("confirmDialog").style.display = "block";
    }
  }
  const selectAllCheckbox = document.getElementById('selectAllCheckbox');
  const checkedboxes = document.getElementsByName('restoredProduct');

  selectAllCheckbox.addEventListener('change', function () {
    checkedboxes.forEach(function (checkbox) {
      checkbox.checked = selectAllCheckbox.checked;
    });
  });
</script>
</body>
</html>