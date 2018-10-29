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

app.controller('MstDasboardController', function ($scope, $http, $state) {

    $scope.chart = function () {
        "use strict";
        // LINE CHART
        var pembelian = new Morris.Line({
            element: 'line-chart1',
            resize: true,
            data: [
                {y: '2011 Q1', item1: 2666},
                {y: '2011 Q2', item1: 2778},
                {y: '2011 Q3', item1: 4912},
                {y: '2011 Q4', item1: 3767},
                {y: '2012 Q1', item1: 6810},
                {y: '2012 Q2', item1: 5670},
                {y: '2012 Q3', item1: 4820},
                {y: '2012 Q4', item1: 15073},
                {y: '2013 Q1', item1: 10687},
                {y: '2013 Q2', item1: 8432}
            ],
            xkey: 'y',
            ykeys: ['item1'],
            labels: ['Item 1'],
            lineColors: ['#3c8dbc'],
            hideHover: 'auto'
        });
        var pembayaran = new Morris.Line({
            element: 'line-chart2',
            resize: true,
            data: [
                {y: '2011 Q1', item1: 2666},
                {y: '2011 Q2', item1: 2778},
                {y: '2011 Q3', item1: 4912},
                {y: '2011 Q4', item1: 3767},
                {y: '2012 Q1', item1: 6810},
                {y: '2012 Q2', item1: 5670},
                {y: '2012 Q3', item1: 4820},
                {y: '2012 Q4', item1: 15073},
                {y: '2013 Q1', item1: 10687},
                {y: '2013 Q2', item1: 8432}
            ],
            xkey: 'y',
            ykeys: ['item1'],
            labels: ['Item 1'],
            lineColors: ['#3c8dbc'],
            hideHover: 'auto'
        });
        var totalpembelian = new Morris.Line({
            element: 'line-chart3',
            resize: true,
            data: [
                {y: '2011 Q1', item1: 2666},
                {y: '2011 Q2', item1: 2778},
                {y: '2011 Q3', item1: 4912},
                {y: '2011 Q4', item1: 3767},
                {y: '2012 Q1', item1: 6810},
                {y: '2012 Q2', item1: 5670},
                {y: '2012 Q3', item1: 4820},
                {y: '2012 Q4', item1: 15073},
                {y: '2013 Q1', item1: 10687},
                {y: '2013 Q2', item1: 8432}
            ],
            xkey: 'y',
            ykeys: ['item1'],
            labels: ['Item 1'],
            lineColors: ['#3c8dbc'],
            hideHover: 'auto'
        });
        var totalpembayaran = new Morris.Line({
            element: 'line-chart4',
            resize: true,
            data: [
                {y: '2011 Q1', item1: 2666},
                {y: '2011 Q2', item1: 2778},
                {y: '2011 Q3', item1: 4912},
                {y: '2011 Q4', item1: 3767},
                {y: '2012 Q1', item1: 6810},
                {y: '2012 Q2', item1: 5670},
                {y: '2012 Q3', item1: 4820},
                {y: '2012 Q4', item1: 15073},
                {y: '2013 Q1', item1: 10687},
                {y: '2013 Q2', item1: 8432}
            ],
            xkey: 'y',
            ykeys: ['item1'],
            labels: ['Item 1'],
            lineColors: ['#3c8dbc'],
            hideHover: 'auto'
        });


    };
    // VALIDATION
    $scope.inputValidation = {
        word: /^\s*\w*\s*$/
    };

    $scope.getDasbaord = function () {
        var param = {
            xaction: 'dasboard',
            xemail: getCookie('user')
        };
        sendData($http, 'POST', 'sedasboard?', param, function (result) {
            $scope.dataNasabah = result.TOTAL_NASABAH;
            $scope.dataTransaksi = result.TOTAL_TRANSAKI;
            $scope.dataPembelian = result.TOTAL_PEMBELIAN;
            $scope.dataPembayaran = result.TOTAL_PEMBAYARAN;
            $scope.dataUser = result.DATA_USER;
            $scope.fullname = $scope.dataUser.fullname;
        });
    };
    $scope.getUserLogin = function () {
        var param = {
            xaction: 'login'
        };
        sendData($http, 'POST', 'sedasboard?', param, function (result) {
            $scope.dataLogin = result.LIST_LOGIN;
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
            xaction: 'insert'
        };
        sendData($http, 'POST', 'sesetornasabah?', param, function (result) {
            if (result.STATUS === 'OK') {
                sweetAlert("Success!", result.MESSAGE, "success");
                window.location.href = "#/listnasabah";
            } else {
                sweetAlert("Erorr!", result.MESSAGE, "error");
            }
        });
    };
    $scope.cancel = function () {
        window.location.href = "#/listnasabah";
    };

    function deleteAllCookies() {
        var cookies = document.cookie.split(";");

        for (var i = 0; i < cookies.length; i++) {
            var cookie = cookies[i];
            var eqPos = cookie.indexOf("=");
            var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
            document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
        }
    }
    $scope.logout = function () {
        var param = {
            xusername: getCookie('user')
        };
        sendData($http, 'POST', 'selogout?', param, function (result) {
            if (result.STATUS === 'OK') {
                deleteAllCookies();
                window.location.href = "index.jsp";
            } else {
                window.location.reload("/");
            }
        });

    };

});