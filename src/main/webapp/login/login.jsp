<%--
  Created by IntelliJ IDEA.
  User: LONG PC
  Date: 10/1/2023
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../login/assets/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg==" crossorigin="anonymous" />
</head>
<body>
<div class="auth-wrapper">
    <div class="auth-container">

        <div class="auth-action-left">
            <div class="auth-form-outer">
                <h2 class="auth-form-title">
                    Sign In
                </h2>
                <div class="auth-external-container">
                    <div class="auth-external-list">
                        <ul>
                            <li><a href="#" ><i class="fa fa-google" ></i></a></li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                        </ul>
                    </div>
                    <p class="auth-sgt">or sign in with:</p>
                </div>
                <form class="login-form" action="/login?action=login" method="post">
                    <input type="text" class="auth-form-input" placeholder="User Name" name="userName">
                    <div class="input-icon">
                        <input type="password" class="auth-form-input" placeholder="Password" name="password">
                        <i class="fa fa-eye show-password"></i>
                    </div>
                    <label class="btn active">
                        <input type="checkbox" name='rememberPassword' checked>
                        <i class="fa fa-square-o"></i><i class="fa fa-check-square-o"></i>
                        <span> Remember password.</span>
                    </label>
                    <div class="footer-action">
                        <input type="submit" value="Sign In" class="auth-submit">
                        <a href="../login/register.jsp" class="auth-btn-direct">Sign Up</a>
                    </div>
                </form>
                <div class="auth-forgot-password">
                    <a href="#">Forfot Password</a>
                </div>
            </div>
        </div>
        <div class="auth-action-right">
            <div class="auth-image">
                <img src="../login/assets/vector.jpg" alt="login">
            </div>
        </div>
    </div>
</div>
<script src="../login/assets/common.js"></script>
</body>
</html>
