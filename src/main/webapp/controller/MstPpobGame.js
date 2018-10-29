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
var countDataPulsa;

app.controller('MstPpobGame', function ($scope, $http, $state,$filter) {

    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };

    $scope.getDataVendor = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'vendor',
            xtype:'D'
        };
        sendData($http, 'POST', 'semstvendorpulsa?', param, function (result) {
            $scope.dataVendor = result.DATA;
        });
    };
    $scope.getDataGame = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'type',
            xtype:'GME'
        };
        sendData($http, 'POST', 'seppob?', param, function (result) {
            $scope.dataPulsa = result.DATA;
            countDataPulsa=result.DATA;
        });
    };
    $scope.add = function () {
//        window.location.href = "#/adduser";
        $state.go("gamemaster");
    };
    $scope.save = function () {
        var paramSave = {
            xvendorcode: $scope.vendorcode,
            xpulsacode: $scope.pulsacode,
            xpulsaname: $scope.pulsaname,
            xamount: $scope.amount,
            xvendorprice:'0.00',
            xprice: $scope.price,
            xflagactive: $scope.flagactive,
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'insert'
        };
        console.log(paramSave);
        sendData($http, 'POST', 'semstpulsa?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.href = "#/gamelist";
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
   
    $scope.cancel = function () {
        $state.go("gamelist");
    };

    $scope.edit = function (dataPulsa) {
        var price = document.getElementById('price');
        price.value = dataPulsa.price;
        var code = document.getElementById('pulsacode');
        code.value = dataPulsa.ppobName;
        _pulsaCode = dataPulsa.ppobCode;
        _vendorCode = dataPulsa.vendorCode;

        return price.value;
    };

    $scope.price =$scope.edit;

    $scope.changePrice = function () {
        var paramSave = {
            xvendorcode: _vendorCode,
            xpulsacode: _pulsaCode,
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
                window.location.reload("#/gamelist");
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
    
    $scope.currentPage = 0;
    $scope.pageSize = 50;
    $scope.data = [];
    $scope.vendor = '';
    
    $scope.getData = function () {
      return $filter('filter')($scope.dataPulsa, $scope.vendor);
    };
    
    $scope.numberOfPages=function(){
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    };    

});

//We already have a limitTo filter built-in to angular,
//let's make a startFrom filter
app.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    };
});