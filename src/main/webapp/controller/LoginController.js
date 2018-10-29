/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global sweetAlert */


var zona = getZona();
getLocation();
var lat = getLocLat();
var lot = getLocLot();
var cookievalue;

var app = angular.module('AngularRouteApp', ['ui.router']);
app.controller('LoginController', function ($scope, $http) {
    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };

    $scope.login = function () {
        var loginUser = {
            xusername: $scope.xusername,
            xpassword: $scope.xpassword,
            xtoken: getCookie('tokenid'),
            xlocation: lot + ' ' + lat,
            xloginfrom: 'W'
        };

        sendData($http, 'POST', 'selogin?', loginUser, function (result) {
            setCookie("tokenid", result.TOKEN);
            setCookie("user", $scope.xusername);
            if (result.STATUS === 'OK') {
                window.location.href = "admin.html?" + getCookie("tokenid");
            } else {
                sweetAlert("Erorr!", result.MESSAGE, "error");
            }
        });
    };
});