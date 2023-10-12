<%--
  Created by IntelliJ IDEA.
  User: LONG PC
  Date: 10/2/2023
  Time: 12:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Forgot password</title>
    <link href="../login/assets/all.min.css" rel="stylesheet" type="text/css">
    <link href="../login/assets/sb-admin-2.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>
<body class="bg-gradient-primary">

<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-2">Hi ${user.fullName}</h1>
                                    <p class="mb-4">Please input new password and PIN code from your email to restore your account!</p>
                                </div>
                                <form class="user" action="/login?action=restorePassword&id=${user.id}" method="post">
                                    <div class="form-group">
                                        <input style="margin-bottom: 10px" type="password" class="form-control form-control-user"
                                               id="password" name="password" aria-describedby="emailHelp"
                                               placeholder="Enter new password..." required>
                                        <input style="margin-bottom: 10px" type="password" class="form-control form-control-user"
                                               id="re_password" name="re_password" aria-describedby="emailHelp"
                                               placeholder="Confirm password..." required>
                                        <input type="text" class="form-control form-control-user"
                                               id="pinCode" name="pinCode" aria-describedby="emailHelp"
                                               placeholder="PIN code from your email..." required>
                                    </div>
                                    <button class="btn btn-primary btn-user btn-block" type="submit">
                                        Restore Account
                                    </button>
                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="/login?action=registerUser">Create an Account!</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="/login">Already have an account? Login!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>

<script src="../login/assets/jquery.min.js"></script>
<script src="../login/assets/bootstrap.bundle.min.js"></script>
<script src="../login/assets/jquery.easing.min.js"></script>
<script src="../login/assets/sb-admin-2.min.js"></script>
</body>
</html>
