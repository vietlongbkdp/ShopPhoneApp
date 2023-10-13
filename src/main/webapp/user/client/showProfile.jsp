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
    <title>Phone Shop</title>
    <link href="../login/assets/all.min.css" rel="stylesheet" type="text/css">
    <link href="../login/assets/sb-admin-2.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <style>
        body {
            color: #797979;
            background: #f1f2f7;
            font-family: 'Open Sans', sans-serif;
            padding: 0px !important;
            margin: 0px !important;
            font-size: 13px;
            text-rendering: optimizeLegibility;
            -webkit-font-smoothing: antialiased;
            -moz-font-smoothing: antialiased;
        }

        .profile-nav, .profile-info{
            margin-top:30px;
        }

        .profile-nav .user-heading {
            background: #fbc02d;
            color: #fff;
            border-radius: 4px 4px 0 0;
            -webkit-border-radius: 4px 4px 0 0;
            padding: 30px;
            text-align: center;
        }

        .profile-nav .user-heading.round a  {
            border-radius: 50%;
            -webkit-border-radius: 50%;
            border: 10px solid rgba(255,255,255,0.3);
            display: inline-block;
        }

        .profile-nav .user-heading a img {
            width: 112px;
            height: 112px;
            border-radius: 50%;
            -webkit-border-radius: 50%;
        }

        .profile-nav .user-heading h1 {
            font-size: 22px;
            font-weight: 300;
            margin-bottom: 5px;
        }

        .profile-nav .user-heading p {
            font-size: 12px;
        }

        .profile-nav ul {
            margin-top: 1px;
        }

        .profile-nav ul > li {
            border-bottom: 1px solid #ebeae6;
            margin-top: 0;
            line-height: 30px;
        }

        .profile-nav ul > li:last-child {
            border-bottom: none;
        }

        .profile-nav ul > li > a {
            border-radius: 0;
            -webkit-border-radius: 0;
            color: #89817f;
            border-left: 5px solid #fff;
        }

        .profile-nav ul > li > a:hover, .profile-nav ul > li > a:focus, .profile-nav ul li.active  a {
            background: #f8f7f5 !important;
            border-left: 5px solid #fbc02d;
            color: #89817f !important;
        }

        .profile-nav ul > li:last-child > a:last-child {
            border-radius: 0 0 4px 4px;
            -webkit-border-radius: 0 0 4px 4px;
        }

        .profile-nav ul > li > a > i{
            font-size: 16px;
            padding-right: 10px;
            color: #bcb3aa;
        }

        .r-activity {
            margin: 6px 0 0;
            font-size: 12px;
        }


        .p-text-area, .p-text-area:focus {
            border: none;
            font-weight: 300;
            box-shadow: none;
            color: #c3c3c3;
            font-size: 16px;
        }

        .profile-info .panel-footer {
            background-color:#f8f7f5 ;
            border-top: 1px solid #e7ebee;
        }

        .profile-info .panel-footer ul li a {
            color: #7a7a7a;
        }

        .bio-graph-heading {
            background: #fbc02d;
            color: #fff;
            text-align: center;
            font-style: italic;
            padding: 40px 110px;
            border-radius: 4px 4px 0 0;
            -webkit-border-radius: 4px 4px 0 0;
            font-size: 16px;
            font-weight: 300;
        }

        .bio-graph-info {
            color: #89817e;
        }

        .bio-graph-info h1 {
            font-size: 22px;
            font-weight: 300;
            margin: 0 0 20px;
        }

        .bio-row {
            width: 50%;
            float: left;
            margin-bottom: 10px;
            padding:0 15px;
        }

        .bio-row p span {
            width: 100px;
            display: inline-block;
        }

        .bio-chart, .bio-desk {
            float: left;
        }

        .bio-chart {
            width: 40%;
        }

        .bio-desk {
            width: 60%;
        }

        .bio-desk h4 {
            font-size: 15px;
            font-weight:400;
        }

        .bio-desk h4.terques {
            color: #4CC5CD;
        }

        .bio-desk h4.red {
            color: #e26b7f;
        }

        .bio-desk h4.green {
            color: #97be4b;
        }

        .bio-desk h4.purple {
            color: #caa3da;
        }

        .file-pos {
            margin: 6px 0 10px 0;
        }

        .profile-activity h5 {
            font-weight: 300;
            margin-top: 0;
            color: #c3c3c3;
        }

        .summary-head {
            background: #ee7272;
            color: #fff;
            text-align: center;
            border-bottom: 1px solid #ee7272;
        }

        .summary-head h4 {
            font-weight: 300;
            text-transform: uppercase;
            margin-bottom: 5px;
        }

        .summary-head p {
            color: rgba(255,255,255,0.6);
        }

        ul.summary-list {
            display: inline-block;
            padding-left:0 ;
            width: 100%;
            margin-bottom: 0;
        }

        ul.summary-list > li {
            display: inline-block;
            width: 19.5%;
            text-align: center;
        }

        ul.summary-list > li > a > i {
            display:block;
            font-size: 18px;
            padding-bottom: 5px;
        }

        ul.summary-list > li > a {
            padding: 10px 0;
            display: inline-block;
            color: #818181;
        }

        ul.summary-list > li  {
            border-right: 1px solid #eaeaea;
        }

        ul.summary-list > li:last-child  {
            border-right: none;
        }

        .activity {
            width: 100%;
            float: left;
            margin-bottom: 10px;
        }

        .activity.alt {
            width: 100%;
            float: right;
            margin-bottom: 10px;
        }

        .activity span {
            float: left;
        }

        .activity.alt span {
            float: right;
        }
        .activity span, .activity.alt span {
            width: 45px;
            height: 45px;
            line-height: 45px;
            border-radius: 50%;
            -webkit-border-radius: 50%;
            background: #eee;
            text-align: center;
            color: #fff;
            font-size: 16px;
        }

        .activity.terques span {
            background: #8dd7d6;
        }

        .activity.terques h4 {
            color: #8dd7d6;
        }
        .activity.purple span {
            background: #b984dc;
        }

        .activity.purple h4 {
            color: #b984dc;
        }
        .activity.blue span {
            background: #90b4e6;
        }

        .activity.blue h4 {
            color: #90b4e6;
        }
        .activity.green span {
            background: #aec785;
        }

        .activity.green h4 {
            color: #aec785;
        }

        .activity h4 {
            margin-top:0 ;
            font-size: 16px;
        }

        .activity p {
            margin-bottom: 0;
            font-size: 13px;
        }

        .activity .activity-desk i, .activity.alt .activity-desk i {
            float: left;
            font-size: 18px;
            margin-right: 10px;
            color: #bebebe;
        }

        .activity .activity-desk {
            margin-left: 70px;
            position: relative;
        }

        .activity.alt .activity-desk {
            margin-right: 70px;
            position: relative;
        }

        .activity.alt .activity-desk .panel {
            float: right;
            position: relative;
        }

        .activity-desk .panel {
            background: #F4F4F4 ;
            display: inline-block;
        }


        .activity .activity-desk .arrow {
            border-right: 8px solid #F4F4F4 !important;
        }
        .activity .activity-desk .arrow {
            border-bottom: 8px solid transparent;
            border-top: 8px solid transparent;
            display: block;
            height: 0;
            left: -7px;
            position: absolute;
            top: 13px;
            width: 0;
        }

        .activity-desk .arrow-alt {
            border-left: 8px solid #F4F4F4 !important;
        }

        .activity-desk .arrow-alt {
            border-bottom: 8px solid transparent;
            border-top: 8px solid transparent;
            display: block;
            height: 0;
            right: -7px;
            position: absolute;
            top: 13px;
            width: 0;
        }

        .activity-desk .album {
            display: inline-block;
            margin-top: 10px;
        }

        .activity-desk .album a{
            margin-right: 10px;
        }

        .activity-desk .album a:last-child{
            margin-right: 0px;
        }
    </style>
</head>
<body class="bg-gradient-primary">

<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                        <div style="margin: 2%" class="row">
                        <div class="profile-nav col-md-3">
                            <div class="panel">
                                <div class="user-heading round">
                                    <a href="#">
                                        <img src="https://thumbs.dreamstime.com/b/ic-ne-priv%C3%A9e-d-avatar-de-jeune-homme-80893389.jpg" alt="Avatar">
                                    </a>
                                    <h1>${user.fullName}</h1>
                                    <p>${user.email}</p>
                                </div  >
                            </div>
                        </div>
                        <div class="profile-info col-md-9">
                            <div class="panel">
                                <div class="panel-body bio-graph-info">
                                    <h1>My Profile</h1>
                                    <div class="row">
                                        <div class="bio-row">
                                            <p><span>Full Name </span>: ${user.fullName}</p>
                                        </div>
                                        <div class="bio-row">
                                            <p><span>Username </span>: ${user.userName}</p>
                                        </div>
                                        <div class="bio-row">
                                            <p><span>Address </span>: ${user.address}</p>
                                        </div>
                                        <div class="bio-row">
                                            <p><span>Birthday</span>: ${user.dob}</p>
                                        </div>
                                        <div class="bio-row">
                                            <p><span>Gender </span>: ${user.gender}</p>
                                        </div>
                                        <div class="bio-row">
                                            <p><span>Email </span>: ${user.email}</p>
                                        </div>
                                        <div class="bio-row">
                                            <p><span>Mobile </span>: ${user.phone}</p>
                                        </div>
                                        <div class="bio-row">
                                            <p><span>Role </span>: ${user.role.roleName}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                    <div class="col-md-6 text-center">
                                        <a href="/shopping?action=editProfile" class="btn btn-info">Edit Proflie</a>
                                    </div>
                                <c:if test="${user.role.roleName == 'Client'}">
                                    <div class="col-md-6 text-center">
                                        <a href="/main?action=shopping" class="btn btn-warning">Back to Shopping</a>
                                    </div>
                                </c:if>
                                <c:if test="${user.role.roleName == 'Admin' || user.role.roleName == 'Staff'}">
                                    <div class="col-md-6 text-center">
                                        <a href="/total" class="btn btn-warning">Back to Management page</a>
                                    </div>
                                </c:if>
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
