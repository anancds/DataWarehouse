<html>
<head lang="en">
    <title>登录测试</title>
    <%@ page pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery-3.1.1.js"></script>
    <style>
        body{
            font-family: 'microsoft yahei',Arial,sans-serif;
            background-color: #222;
        }

        .redborder {
            border:2px solid #f96145;
            border-radius:2px;
        }

        .row {
            padding: 20px 0px;
        }

        .bigicon {
            font-size: 97px;
            color: #f08000;
        }

        .loginpanel {
            text-align: center;
            width: 300px;
            border-radius: 0.5rem;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            margin: 10px auto;
            background-color: #555;
            padding: 20px;
        }

        input {
            width: 100%;
            margin-bottom: 17px;
            padding: 15px;
            background-color: #ECF4F4;
            border-radius: 2px;
            border: none;
        }

        h2 {
            margin-bottom: 20px;
            font-weight: normal;
            color: #EFEFEF;
        }

        .btn {
            border-radius: 2px;
            padding: 10px;
        }

        .btn span {
            font-size: 27px;
            color: white;
        }

        .buttonwrapper{
            position:relative;
            overflow:hidden;
            height:50px;
        }

        .lockbutton {
            font-size: 27px;
            color: #f96145;
            padding: 10px;
            width:100%;
            position:absolute;
            top:0;
            left:0;
        }

        .loginbutton {
            background-color: #f08000;
            width: 100%;
            -webkit-border-top-right-radius: 0;
            -webkit-border-bottom-right-radius: 0;
            -moz-border-radius-topright: 0;
            -moz-border-radius-bottomright: 0;
            border-top-right-radius: 0;
            border-bottom-right-radius: 0;
            left: -260px;
            position:absolute;
            top:0;
        }
    </style>
</head>
<body>
<div class="container-fluid" style="margin-top: 200px">
    <div class="row">
        <div class="loginpanel">
            <i id="loading" class="hidden fa fa-spinner fa-spin bigicon"></i>
            <h2>
                <span class="fa fa-quote-left "></span> 登录 <span class="fa fa-quote-right "></span>
            </h2>
            <div>
                <input id="username" type="text" placeholder="登录账号" onkeypress="check_values();">
                <input id="password" type="password" placeholder="输入密码" onkeypress="check_values();">
                <button class="btn" onclick="load()">&nbsp;&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;&nbsp;</button>

            </div>
        </div>
    </div>
</div>
</body>
<script >
    function load() {
        location.href="Main/Main.html";
    }
</script>
</html>
