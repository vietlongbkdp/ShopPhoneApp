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
    <title>Title</title>
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
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                </div>
                                <form class="user" action="/login?action=login" method="post">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               id="userName" aria-describedby="emailHelp" value="thang123"
                                               placeholder="Enter User Name" name="userName" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user" value="123"
                                               id="exampleInputPassword" placeholder="Password" name="password" required>
                                    </div>
                                    <input type="submit" value="Login" class="btn btn-primary btn-user btn-block">
<%--                                    <a type="submit" class="btn btn-primary btn-user btn-block">--%>
<%--                                        Login--%>
<%--                                    </a>--%>
                                    <hr>
                                    <a href="#" class="btn btn-google btn-user btn-block">
                                        <i class="fab fa-google fa-fw"></i> Login with Google
                                    </a>
                                    <a href="#" class="btn btn-facebook btn-user btn-block">
                                        <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                                    </a>
                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="/login?action=forgotPassword">Forgot Password?</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="/login?action=registerUser">Create an Account!</a>
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
