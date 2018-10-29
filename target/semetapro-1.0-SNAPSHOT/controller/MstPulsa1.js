

var zona = getZona();
getLocation();
var lat = getLocLat();
var lot = getLocLot();
var cookievalue;
var _pulsaCode;
var _vendorCode;
var countDataPulsa;

app.controller('MstPulsaController', function ($scope, $http, $state,$filter) {

    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };

    $scope.getDataVendor = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'vendor',
            xtype:'pulsa'
        };
        sendData($http, 'POST', 'semstvendorpulsa?', param, function (result) {
            $scope.dataVendor = result.DATA;
        });
    };
    $scope.getDataPulsa = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xpulsatype:'P',
            xaction: 'list'
        };
        sendData($http, 'POST', 'semstpulsa?', param, function (result) {
            $scope.dataPulsa = result.DATA;
            countDataPulsa=result.DATA;
        });
    };
    $scope.add = function () {
//        window.location.href = "#/adduser";
        $state.go("pulsamaster");
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
                window.location.href = "#/pulsalist";
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
   
    $scope.cancel = function () {
        $state.go("pulsalist");
    };

    $scope.edit = function (dataPulsa) {
        var price = document.getElementById('price');
        price.value = dataPulsa.price;
        var code = document.getElementById('pulsacode');
        code.value = dataPulsa.pulsaName;

        _pulsaCode = dataPulsa.pulsaCode;
        _vendorCode = dataPulsa.vendorCode;
    };

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
        sendData($http, 'POST', 'semstpulsa?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.reload("#/pulsalist");
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