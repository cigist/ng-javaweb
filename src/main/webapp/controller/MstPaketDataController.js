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
var _pulsaCode;
var _vendorCode;

app.controller('MstPaketDataController', function ($scope, $http, $state,$filter) {

    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };

    $scope.getDataVendor = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'vendor',
            xtype: 'data'
        };
        sendData($http, 'POST', 'semstvendorpulsa?', param, function (result) {
            $scope.dataVendor = result.DATA;
        });
    };
    $scope.getPaketData = function () {
        var param = {
            xvendorcode: '',
            xpaketdatacode: '',
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xtype:'DTA',
            xaction: 'type'
        };
        sendData($http, 'POST', 'seppob?', param, function (result) {
            $scope.dataPaketData = result.DATA;
        });
    };
    $scope.add = function () {
        $state.go("paketdatamaster");
    };
    $scope.save = function () {
        var paramSave = {
            xvendorcode: $scope.vendorcode,
            xpaketdatacode: $scope.paketdatacode,
            xpaketdataname: $scope.paketdataname,
            xkuota: $scope.kuota,
            xprice: $scope.price,
            xdiscount: '',
            xflagactive: $scope.flagactive,
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'insert'
        };
        sendData($http, 'POST', 'semstpaketdata?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.href = "#/paketdatalist";
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };

    $scope.cancel = function () {
        window.location.href = "#/paketdatalist";
    };

    $scope.editData = function (dataPaketData) {
        var price = document.getElementById('pricedata');
        price.value = dataPaketData.price;
        var code = document.getElementById('datacode');
        code.value = dataPaketData.ppobName;
        _pulsaCode = dataPaketData.ppobCode;
        _vendorCode = dataPaketData.vendorCode;
        
        console.log(_vendorCode);
    };

    $scope.changePrice = function () {
        var paramSave = {
            xvendorcode: _vendorCode,
            xpaketdatacode: _pulsaCode,
            xprice: $scope.price,
            xdiscount: 0.00,
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'change'
        };

        console.log(paramSave);
        sendData($http, 'POST', 'seppob?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.reload("#/paketdatalist");
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };

    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $scope.data = [];
    $scope.vendor = '';

    $scope.getDataPaket = function () {
        return $filter('filter')($scope.dataPaketData, $scope.vendor);
    };

    $scope.numberOfPages = function () {
        return Math.ceil($scope.getDataPaket().length / $scope.pageSize);
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