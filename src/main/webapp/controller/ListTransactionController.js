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


var zona = getZona();
getLocation();
var lat = getLocLat();
var lot = getLocLot();
var cookievalue;

app.controller('ListSetorTunai', function ($scope, $http, $state, $stateParams) {

    $scope.getTrnSetorTunai = function () {
        var param = {
            xtoken: getCookie('tokenid'),
            xusername: getCookie('user'),
            xaccountnumber: $scope.accountNumber,
            xtrantype: 'STR',
            xaction: 'type'
        };
        sendData($http, 'POST', 'setrnnasabah?', param, function (result) {
            $scope.dataTrnSetor = result.NASABAH;
        });
    };

    $scope.add = function () {
        window.location.href = "#/setornasabah";
    };
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data = [];
    $scope.vendor = '';

    $scope.getData = function () {
        return $filter('filter')($scope.dataTrnSetor, $scope.vendor);
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

app.controller('ListTarikTunai', function ($scope, $http, $state, $stateParams) {

    $scope.getTrnTarikTunai = function () {
        var param = {
            xtoken: getCookie('tokenid'),
            xusername: getCookie('user'),
            xaccountnumber: $scope.accountNumber,
            xtrantype: 'TRK',
            xaction: 'type'
        };
        sendData($http, 'POST', 'setrnnasabah?', param, function (result) {
            $scope.dataTrnTarik = result.NASABAH;
        });
    };

    $scope.add = function () {
        window.location.href = "#/tariknasabah";
    };
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data = [];
    $scope.vendor = '';

    $scope.getData = function () {
        return $filter('filter')($scope.dataTrnSetor, $scope.vendor);
    };

    $scope.numberOfPages = function () {
        return Math.ceil($scope.getData().length / $scope.pageSize);
    };

});
app.controller('ListKirimDana', function ($scope, $http, $state, $stateParams) {

    $scope.getTrnKirimDana = function () {
        var param = {
            xtoken: getCookie('tokenid'),
            xusername: getCookie('user'),
            xaccountnumber: $scope.accountNumber,
            xtrantype: 'TRF',
            xaction: 'type'
        };
        sendData($http, 'POST', 'setrnnasabah?', param, function (result) {
            $scope.dataTrnKirimDana = result.NASABAH;
        });
    };

    $scope.add = function () {
        window.location.href = "#/tariknasabah";
    };
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data = [];
    $scope.vendor = '';

    $scope.getData = function () {
        return $filter('filter')($scope.dataTrnSetor, $scope.vendor);
    };

    $scope.numberOfPages = function () {
        return Math.ceil($scope.getData().length / $scope.pageSize);
    };

});