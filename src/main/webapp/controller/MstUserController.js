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

app.controller('MstUserController', function ($scope, $http, $state) {

    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };

    $scope.getDataUser = function () {
        var param = {
            xusername: '',
            xaction: 'list'
        };
        sendData($http, 'GET', 'semstuser?', param, function (result) {
            $scope.dataUser = result.LIST_USER;
        });
    };
    $scope.add = function () {
//        window.location.href = "#/adduser";
         $state.go("adduser");
    };
    $scope.save = function () {
        var paramSave = {
            xusername: $scope.username,
            xpassword: $scope.password,
            xflagactive: $scope.flagactive,
            xuser: getCookie('user'),
            xlocation: lot + ' ' + lat,
            xtoken:getCookie('tokenid'),
            xaction: 'insert'
        };
        
        console.log(paramSave);
        sendData($http, 'POST', 'semstuser?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.href = "#/listuser";
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
      $scope.update = function () {
        var paramSave = {
            xusername: $scope.username,
            xoldpassword: $scope.oldpassword,
            xnewpassword: $scope.password,
            xuser: getCookie('user'),
            xlocation: lot + ' ' + lat,
            xtoken:getCookie('tokenid'),
            xaction: 'update'
        };
        sendData($http, 'POST', 'sechangepassword?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.href = "#/listuser";
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
    $scope.cancel = function () {
        window.location.href = "#/listuser";
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