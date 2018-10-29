<%-- 
    Document   : index
    Created on : Jul 30, 2017, 10:02:01 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/login.css">
        <script type="text/javascript" src="vendor/angular/angular.min.js"></script>
        <!--        <script type="text/javascript" src="vendor/angular/angular-route.min.js"></script>-->
        <script type="text/javascript" src="vendor/angular/angular-ui-router.min.js"></script>
        <script type="text/javascript" src="vendor/angular/angular-cookies.min.js"></script>
        <script src="vendor/alert/sweetalert.min.js"></script>
        <link rel="stylesheet" href="vendor/alert/sweetalert.css">
        <script type="text/javascript" src="controller/webconfig.js"></script>
        <script type="text/javascript" src="controller/LoginController.js"></script>

    </head>
    <body ng-app="AngularRouteApp">
        <div class="loginmodal-container" >
            <h1>Admin Login</h1>
            <form ng-controller="LoginController">
                <input type="text" name="xusername" placeholder="Username" ng-model="xusername">
                <input type="password" name="xpassword" placeholder="Password" ng-model="xpassword">
                <input type="submit" name="login" class="login loginmodal-submit" ng-click="login()" value="Login">
            </form>

            <div class="login-help">
                <a href="#">Register</a> - <a href="#">Forgot Password</a>
            </div>
        </div>
    </body>
</html>
