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
var flightticketcode;
var destinationfrom;
var destinationto;
var airlinecode;
var datedeparture;
var datereturn;
var seatclass;

app.controller('MstMaskapaiController', function ($scope, $http, $state, $stateParams) {

    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };
    $scope.airlinecode = $stateParams.code;

    $scope.getDataMaskapai = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'list'
        };
        sendData($http, 'POST', 'semstairline?', param, function (result) {
            $scope.dataMaskapai = result.DATA;
        });
    };
    $scope.getDataTicket = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'list'
        };
        sendData($http, 'POST', 'semstflightticket?', param, function (result) {
            $scope.dataTicket = result.DATA;
        });
    };
    $scope.add = function () {
        $state.go("maskapaimaster");
    };
    $scope.save = function () {
        var paramSave = {
            xairlinecode: $scope.airlinecode,
            xairlinename: $scope.airlinename,
            xdomestic: $scope.domestic,
            xicon: '',
            xflagactive: $scope.flagactive,
            xusername: getCookie('user'),
            xlocation: lot + ' ' + lat,
            xtoken: getCookie('tokenid'),
            xaction: 'insert'
        };
        sendData($http, 'POST', 'semstairline?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                $state.go("maskapailist");
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
            xtoken: getCookie('tokenid'),
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
        window.location.href = "#/airportlist";
    };
    $scope.delete = function (dataMaskapai) {
        var param = {
            xairlinecode: dataMaskapai.airlineCode,
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'delete'
        };
        sendData($http, 'POST', 'semstairline?', param, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.reload("#/airportlist");
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };

    $scope.change = function (dataTicket) {
        var code = document.getElementById('code');
        code.value = dataTicket.flightTicketCode;
        var price = document.getElementById('price');
        price.value = dataTicket.price;

        flightticketcode = dataTicket.flightTicketCode;
        destinationfrom = dataTicket.destinationTrom;
        destinationto = dataTicket.destinationTo;
        airlinecode = dataTicket.airlineCode;
        datedeparture = dataTicket.dateDeparture;
        datereturn = dataTicket.dateReturn;
        seatclass = dataTicket.seatClass;

    };
    $scope.changePrice = function () {
        var param = {
            xflightticketcode: flightticketcode,
            xdestinationfrom: destinationfrom,
            xdestinationto: destinationto,
            xairlinecode: airlinecode,
            xdatedeparture: datedeparture,
            xdatereturn: datereturn,
            xseatclass: seatclass,
            xprice: document.getElementById('price').value,
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'price'
        };
        console.log(param);
        sendData($http, 'POST', 'semstflightticket?', param, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.reload("#/flightticketlist");
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
    $scope.update = function () {
         window.location.reload("#/flightticketlist");
    };

});