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

app.controller('MstAirportController', function ($scope, $http, $state, $stateParams,$filter) {

    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };
    $scope.airportcode = $stateParams.code;

    $scope.getDataAirport = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'list'
        };
        sendData($http, 'POST', 'semstairport?', param, function (result) {
            $scope.dataAirport = result.DATA;
        });
    };
    $scope.add = function () {
        $state.go("airportmaster");
    };
    $scope.save = function () {
        var paramSave = {
            xairportcode: $scope.airportcode,
            xairportname: $scope.airportname,
            xdomestic: $scope.domestic,
            xflagactive: $scope.flagactive,
            xusername: getCookie('user'),
            xlocation: lot + ' ' + lat,
            xtoken: getCookie('tokenid'),
            xaction: 'insert'
        };
        sendData($http, 'POST', 'semstairport?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                $state.go("airportlist");
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
    $scope.cancel = function () {
        window.location.href = "#/airportlist";
    };
    $scope.delete = function (dataMaskapai) {
        var param = {
            xairportcode: dataMaskapai.airportCode,
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'delete'
        };
        console.log(param);
        sendData($http, 'POST', 'semstairport?', param, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.reload("#/airportlist");
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };

    $scope.currentPage = 0;
    $scope.pageSize = 5;
    $scope.data = [];
    $scope.code = '';
   
    $scope.getData = function () {
        return $filter('filter')($scope.dataAirport, $scope.code);
    };

    $scope.numberOfPages = function () {
        return Math.ceil($scope.getData().length/ $scope.pageSize);
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