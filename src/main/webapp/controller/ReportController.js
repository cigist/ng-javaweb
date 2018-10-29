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

app.controller('ReportController', function ($scope, $http, $window) {
    $scope.getDataVendor = function () {
        var param = {
            xusername: getCookie('user'),
            xtoken: getCookie('tokenid'),
            xaction: 'vendor',
            xtype: 'pulsa'
        };
        sendData($http, 'POST', 'semstvendorpulsa?', param, function (result) {
            $scope.dataVendor = result.DATA;
        });
    };
    $scope.updatePpob = function () {
        $.LoadingOverlay("show");
        sendData($http, 'GET', 'seupdateppob', '', function (result) {
            if (result.STATUS === 'OK') {
                $.LoadingOverlay("hide");
                sweetAlert("Success!", result.STATUS, "success");
            } else {
                $.LoadingOverlay("hide");
                sweetAlert("Erorr!", result.STATUS, "error");
            }
        });
    };
    $scope.doPrintTrnRpt = function () {
        $window.open('/semetapro/api/seprint', '_blank');
    };
    $scope.doPrintTrnFeeRpt = function () {
//        var param = {
//            xname: 'TrnFeeRpt.jasper'
//        };
//        sendData($http, 'GET', 'seprint', param, function (result) {
//
//        });
        $window.open('/semetapro/api/seprintfee', '_blank');
    };
    $scope.doPrintPulsa = function () {
//        var param = {
//            xname: 'TrnFeeRpt.jasper'
//        };
//        sendData($http, 'GET', 'seprint', param, function (result) {
//
//        });
        $window.open('/semetapro/api/seprintpulsa', '_blank');
    };
    $scope.doPrintPaketData = function () {
//        var param = {
//            xname: 'TrnFeeRpt.jasper'
//        };
//        sendData($http, 'GET', 'seprint', param, function (result) {
//
//        });
        $window.open('/semetapro/api/seprintpaketdata', '_blank');
    };

});