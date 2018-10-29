/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
;

var app = angular.module('AngularRouteApp', ['ui.router']);
app.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
            .state('/', {
                url: '/',
                templateUrl: 'pages/dasboard.jsp',
                controller: 'MstDasboardController',
                resolve: {
                    "check": function ($location) {
                        if (getCookie('tokenid') !== '') {
                            templateUrl: 'pages/dasboard.jsp';
                        } else {
                            swal("Error", "Error access permission !", "error");
                            window.location.href = "index.jsp";
                        }
                    }
                }

            })
            .state('listuser', {
                url: '/listuser',
                templateUrl: 'pages/listuser.jsp',
                controller: 'MstUserController'
            })
            .state('adduser', {
                url: '/adduser',
                templateUrl: 'pages/adduser.jsp',
                controller: 'MstUserController'
            })
            .state("changepassword", {
                url: "/changepassword",
                templateUrl: 'pages/changepassword.jsp',
                controller: 'MstUserController'
            })
            .state('listcustomer', {
                url: '/listcustomer',
                templateUrl: 'pages/listcustomer.jsp',
                controller: 'MstNasabahController'
            })
            .state('createcustomer', {
                url: '/createcustomer',
                templateUrl: 'pages/createcustomer.jsp',
                controller: 'MstNasabahController'
            })
            .state('listnasabah', {
                url: '/listnasabah',
                templateUrl: 'pages/listnasabah.jsp',
                controller: 'MstNasabahController',
                authenticate: true
            })
            .state('listtrnnasabah', {
                url: '/listtrnnasabah?account=:accountNumer',
                templateUrl: 'pages/listtrnnasabah.jsp',
                controller: 'MstNasabahController',
                authenticate: true
            })
            .state('createnasabah', {
                url: '/createnasabah',
                templateUrl: 'pages/createnasabah.jsp',
                controller: 'MstNasabahController',
                authenticate: true
            })
            .state('setornasabah', {
                url: '/setornasabah',
                templateUrl: 'pages/setornasabah.jsp',
                controller: 'MstNasabahController',
                authenticate: true
            })
            .state('tariknasabah', {
                url: '/tariknasabah',
                templateUrl: 'pages/tariknasabah.jsp',
                controller: 'MstNasabahController',
                authenticate: true
            })
            .state('addnasabah', {
                url: '/addnasabah',
                templateUrl: 'pages/adduser.jsp',
                controller: 'MstUserController',
                authenticate: true
            })
            .state('vendorlist', {
                url: '/vendorlist',
                templateUrl: 'pages/vendorlist.jsp',
                controller: 'MstVendorController',
                authenticate: true
            })
            .state('vendormaster', {
                url: '/vendormaster',
                templateUrl: 'pages/vendormaster.jsp',
                controller: 'MstVendorController',
                authenticate: true
            })
            .state('pulsamaster', {
                url: '/pulsamaster',
                templateUrl: 'pages/pulsamaster.jsp',
                controller: 'MstPulsaController',
                authenticate: true
            })
            .state('pulsalist', {
                url: '/pulsalist',
                templateUrl: 'pages/pulsalist.jsp',
                controller: 'MstPulsaController',
                authenticate: true
            })
            .state('paketdatamaster', {
                url: '/paketdatamaster',
                templateUrl: 'pages/paketdatamaster.jsp',
                controller: 'MstPaketDataController',
                authenticate: true
            })
            .state('paketdatalist', {
                url: '/paketdatalist',
                templateUrl: 'pages/paketdatalist.jsp',
                controller: 'MstPaketDataController',
                authenticate: true
            })
            .state('gamelist', {
                url: '/gamelist',
                templateUrl: 'pages/gamelist.jsp',
                controller: 'MstPpobGame',
                authenticate: true
            })
            .state('ppoblist', {
                url: '/ppoblist',
                templateUrl: 'pages/ppoblist.jsp',
                controller: 'MstPpobPayment',
                authenticate: true
            })
            .state('maskapailist', {
                url: '/maskapailist?',
                templateUrl: 'pages/maskapailist.jsp',
                controller: 'MstMaskapaiController',
                authenticate: true
            })
            .state('maskapaimaster', {
                url: '/maskapaimaster?airlinecode=:code',
                templateUrl: 'pages/maskapaimaster.jsp',
                controller: 'MstMaskapaiController',
                authenticate: true
            })
            .state('airportlist', {
                url: '/airportlist?',
                templateUrl: 'pages/airportlist.jsp',
                controller: 'MstAirportController',
                authenticate: true
            })
            .state('airportmaster', {
                url: '/airportmaster?airportcode=:code',
                templateUrl: 'pages/airportmaster.jsp',
                controller: 'MstAirportController',
                authenticate: true
            })
            .state('flightticketlist', {
                url: '/flightticketlist',
                templateUrl: 'pages/flightticketlist.jsp',
                controller: 'MstMaskapaiController',
                authenticate: true
            })
            .state('flightticketmaster', {
                url: '/flightticketmaster?ticketcode=:code',
                templateUrl: 'pages/flightticketmaster.jsp',
                controller: 'MstMaskapaiController',
                authenticate: true
            })
            .state('paymentlist', {
                url: '/paymentlist',
                templateUrl: 'pages/paymentlist.jsp',
                controller: 'MstPaymentController',
                authenticate: true
            })
            .state('paymentmaster', {
                url: '/paymentmaster?paymentcode=:code',
                templateUrl: 'pages/paymentmaster.jsp',
                controller: 'MstPaymentController',
                authenticate: true
            })
            .state('pembelianlist', {
                url: '/pembelianlist',
                templateUrl: 'pages/pembelianlist.jsp',
                controller: 'MstPembelianController',
                authenticate: true
            })
            .state('trnreport', {
                url: '/trnreport',
                templateUrl: 'pages/trnreport.jsp',
                controller: 'ReportController',
                authenticate: true
            })
            .state('listsetortunai', {
                url: '/listsetortunai',
                templateUrl: 'pages/listsetortunai.jsp',
                controller: 'ListSetorTunai',
                authenticate: true
            })
            .state('listtariktunai', {
                url: '/listtariktunai',
                templateUrl: 'pages/listtariktunai.jsp',
                controller: 'ListTarikTunai',
                authenticate: true
            })
            .state('listkirimdana', {
                url: '/listkirimdana',
                templateUrl: 'pages/listkirimdana.jsp',
                controller: 'ListKirimDana',
                authenticate: true
            });




    $urlRouterProvider.otherwise("/");
});
