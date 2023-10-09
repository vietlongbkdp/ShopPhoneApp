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
    <style>
        .error {
            color: red;
            font-style: italic;
            font-size: 14px;
        }
        </style>
</head>
<body class="bg-gradient-primary">
<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                        </div>
                        <form class="user" action="/login?action=register" method="post" id="createForm">
                            <div class="form-group row">

                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="text" class="form-control form-control-user" id="fullName"
                                           placeholder="Full Name" name="fullName" required>
                                    <span id="fullName-error" class="error"></span>

                                </div>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control form-control-user" id="userName"
                                           placeholder="User Name" name="userName" >
                                    <span id="userName-error" class="error"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control form-control-user" id="email"
                                       placeholder="Email Address" name="email" >
                                <span id="email-error" class="error"></span>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="password" class="form-control form-control-user"
                                           id="password" placeholder="Password" name="password" >
                                    <span id="password-error" class="error"></span>
                                </div>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control form-control-user"
                                           id="repeatPassword" placeholder="Repeat Password" name="confirmPassword" >
                                    <span id="repeatPassword-error" class="error"></span>
                                </div>
                            </div>
                            <input type="submit" value="Register Account" class="btn btn-primary btn-user btn-block">
<%--                            <a href="/login" class="btn btn-primary btn-user btn-block">--%>
<%--                                Register Account--%>
<%--                            </a>--%>
                            <hr>
                            <a href="/login" class="btn btn-google btn-user btn-block">
                                <i class="fab fa-google fa-fw"></i> Register with Google
                            </a>
                            <a href="/login" class="btn btn-facebook btn-user btn-block">
                                <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
                            </a>
                        </form>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="/login?action=forgotPassword">Forgot Password?</a>
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
<script src="../login/assets/jquery.min.js"></script>
<script src="../login/assets/bootstrap.bundle.min.js"></script>
<script src="../login/assets/jquery.easing.min.js"></script>
<script src="../login/assets/sb-admin-2.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
<script>
    $(document).ready(function () {
        jQuery.validator.addMethod('userName_rule', function (value, element) {
            return /^[a-zA-Z0-9_-]+$/.test(value);
        });
        // jQuery.validator.addMethod('email_rule', function (value, element) {
        //     return /^([a-zA-Z0-9_\-\.]+)\+?([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/.test(value);
        // });
        $('#createForm').validate({
            rules: {
                'email': {
                    required: true,
                    email_rule: true
                },
                'userName': {
                    required: true,
                    userName_rule: true
                },
                'fullName': {
                    required: true,
                },
            },
            messages: {
                'email': "Please enter a valid email address.",
                'userName': "Please type your user name.",
                'fullName': "Please type your full name."
            }
        });
    });
</script>
</body>
</html>
