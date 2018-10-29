/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var zona = getZona();
getLocation();
var lat = getLocLat();
var lot = getLocLot();
var cookievalue;

app.controller('MstVendorController', function ($scope, $http, $state) {

    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };

    $scope.getDataVendor = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken:getCookie('tokenid'),
            xaction: 'list'
        };
        sendData($http, 'POST', 'semstvendor?', param, function (result) {
            $scope.dataVendor = result.DATA;
        });
    };
    $scope.add = function () {
//        window.location.href = "#/adduser";
         $state.go("adduser");
    };
    $scope.save = function () {
        var paramSave = {
            xvendorcode: $scope.vendorcode,
            xvendorname: $scope.vendorname,
            xvendortype: $scope.vendortype,
            xflagactive: $scope.flagactive,
            xusername: getCookie('user'),
            xtoken:getCookie('tokenid'),
            xaction: 'insert'
        };
        sendData($http, 'POST', 'semstvendor?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.href = "#/vendorlist";
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
      $scope.update = function () {
        var paramSave = {
            xusername: $scope.username,
            xoldpassword: $scope.oldpassword,
            xpassword: $scope.password,
            xuser: getCookie('user'),
            xlocation: lot + ' ' + lat,
            xtoken:getCookie('tokenid'),
            xaction: 'update'
        };
        sendData($http, 'POST', 'semstuser?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.href = "#/listuser";
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
    $scope.cancel = function () {
        window.location.href = "#/vendorlist";
    };
    $scope.delete = function (dataUser) {
        var paramSave = {
            xusername: dataUser.username,
            xaction: 'delete'
        };
        sendData($http, 'GET', 'semstuser?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.MESSAGE, "success");
                window.location.reload("#/listuser");
            } else {
                sweetAlert("Erorr!", result.MESSAGE, "error");
            }
        });
    };

});