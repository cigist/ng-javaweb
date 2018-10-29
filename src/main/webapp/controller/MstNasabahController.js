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

app.controller('MstNasabahController', function ($scope, $http, $state, $stateParams) {

    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };
    $scope.accountNumber = $stateParams.account;

    $scope.getDataNasabah = function () {
        var param = {
            xtoken: getCookie('tokenid'),
            xusername: getCookie('user'),
            xaction: 'list'
        };
        sendData($http, 'GET', 'semstnasabah?', param, function (result) {
            $scope.dataNasabah = result.LIST_NASABAH;
        });
        $scope.pageSize = 10;
    };

    $scope.getTrnNasabah = function () {
        var param = {
            xtoken: getCookie('tokenid'),
            xusername: getCookie('user'),
            xaccountnumber: $scope.accountNumber,
            xaction: 'detail'
        };
        sendData($http, 'POST', 'setrnnasabah?', param, function (result) {
            $scope.dataTrnNasabah = result.NASABAH;
        });
    };

    $scope.add = function () {
        window.location.href = "#/adduser";
    };
    $scope.postSetor = function () {
        var param = {
            xaccountnumber: $scope.accountnumber,
            xnominal: $scope.nominal,
            xketerangan: $scope.keterangan,
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xlocation: lot + ' ' + lat,
            xtranfrom: 'W',
            xaction: 'insert'
        };
        sendData($http, 'POST', 'sesetornasabah?', param, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.MESSAGE, "success");
                window.location.href = "#/istsetortunai";
            } else {
                sweetAlert("Erorr!", result.MESSAGE, "error");
            }
        });
    };
    $scope.cancelSetor = function () {
        window.location.href = "#/istsetortunai";
    };
    $scope.postTarik = function () {
        var param = {
            xaccountnumber: $scope.accountnumber,
            xnominal: $scope.nominal,
            xketerangan: $scope.keterangan,
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xlocation: lot + ' ' + lat,
            xaction: 'insert'
        };
        sendData($http, 'POST', 'setariknasabah?', param, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.MESSAGE, "success");
                window.location.href = "#/listnasabah";
            } else {
                sweetAlert("Erorr!", result.MESSAGE, "error");
            }
        });
    };
    $scope.cancelNasabah = function () {
        window.location.href = "#/listnasabah";
    };

    $scope.getDataCustomer = function () {
        var param = {
            xtoken: getCookie('tokenid'),
            xusername: getCookie('user'),
            xaction: 'list'
        };
        sendData($http, 'GET', 'semstcustomer?', param, function (result) {
            $scope.dataCustomer = result.LIST_CUSTOMER;
        });
    };
    $scope.postNasabah = function () {
        var image = document.getElementById('ximage');
        var param = {
            xfullname: $scope.fullname,
            xemail: $scope.email,
            xphonenumber: $scope.phonenumber,
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xlocation: lot + ' ' + lat,
            ximage: image.value,
            xaddress: $scope.address,
            xaction: 'insert'
        };
        sendData($http, 'POST', 'semstnasabah?', param, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.MESSAGE, "success");
                window.location.href = "#/listnasabah";
            } else {
                sweetAlert("Erorr!", result.MESSAGE, "error");
            }
        });
    };

    $scope.delete = function () {
        window.location.reload("#/listuser");
    };

    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data = [];
    $scope.vendor = '';

    $scope.getData = function () {
        return $filter('filter')($scope.dataNasabah, $scope.vendor);
    };

    $scope.numberOfPages = function () {
        return Math.ceil($scope.getData().length / $scope.pageSize);
    };

});

//We already have a limitTo filter built-in to angular,
//let's make a startFrom filter
app.filter('startFrom', function () {
    return function (input, start) {
        start = +start; //parse to int
        return input.slice(start);
    };
});