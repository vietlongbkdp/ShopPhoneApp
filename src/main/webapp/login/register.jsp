<%--
  Created by IntelliJ IDEA.
  User: LONG PC
  Date: 10/1/2023
  Time: 8:59 PM
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
                      Create Account
                  </h2>
                  <div class="auth-external-container">
                      <div class="auth-external-list">
                          <ul>
                              <li><a href="#"><i class="fa fa-google" style="padding-top: 5px"></i></a></li>
                              <li><a href="#"><i class="fa fa-facebook" style="padding-top: 5px"></i></a></li>
                              <li><a href="#"><i class="fa fa-twitter" style="padding-top: 5px"></i></a></li>
                              <li><a href="#"><i class="fa fa-linkedin" style="padding-top: 5px"></i></a></li>
                          </ul>
                      </div>
                      <p class="auth-sgt">or use your email for registration:</p>
                  </div>
                  <form class="login-form" action="/login?action=register" method="post">
                      <input type="text" class="auth-form-input" placeholder="Full Name" name="fullName">
                      <input type="text" class="auth-form-input" placeholder="User Name" name="userName">
                      <input type="email" class="auth-form-input" placeholder="Email" name="email">
                      <div class="input-icon">
                          <input type="password" class="auth-form-input" placeholder="Password" name="password">
                          <i class="fa fa-eye show-password"></i>
                      </div>
                      <input type="password" class="auth-form-input" placeholder="Confirm Password" name="confirmPassword">
                      <label class="btn active">
                          <input type="checkbox" name='email1' checked>
                          <i class="fa fa-square-o"></i><i class="fa fa-check-square-o"></i>
                          <span> I agree to the <a href="#">Terms</a> and <a href="#">Privacy Policy</a>.</span>
                      </label>
                      <div class="footer-action">
                          <input type="submit" value="Sign Up" class="auth-submit">
                          <a href="login.jsp" class="auth-btn-direct">Sign In</a>
                      </div>
                  </form>
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
