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

app.controller('MstPaymentController', function ($scope, $http, $state, $stateParams) {

    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };
    $scope.payment = $stateParams.code;

    $scope.getDataPayment = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'list'
        };
        sendData($http, 'POST', 'semstpayment?', param, function (result) {
            $scope.dataPayment = result.DATA;
        });
    };

    $scope.add = function () {
        $state.go("paymentmaster");
    };
    $scope.save = function () {
        var paramSave = {
            xpaymentcode: $scope.paymentcode,
            xpaymentdesc: $scope.paymentdesc,
            xfee: $scope.fee,
            xflagactive: $scope.flagactive,
            xusername: getCookie('user'),
            xlocation: lot + ' ' + lat,
            xtoken: getCookie('tokenid'),
            xaction: 'insert'
        };
        sendData($http, 'POST', 'semstpayment?', paramSave, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                $state.go("paymentlist");
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };

    $scope.cancel = function () {
        window.location.href = "#/paymentlist";
    };
    $scope.delete = function (dataPayment) {
        var param = {
            xpaymentcode: dataMaskapai.airlineCode,
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'delete'
        };
        sendData($http, 'POST', 'semstpayment?', param, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.STATUS, "success");
                window.location.reload("#/paymentlist");
            } else {
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
});