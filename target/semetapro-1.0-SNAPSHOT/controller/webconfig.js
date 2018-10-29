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

function sendData($http,method,service,args, result) {
    swal({
        title: "",
        text: "Please wait.....!",
        timer: 1000,
        showConfirmButton: false
    });
    $http({
        method:method,
        url: '/semetapro/api/'+service,
        params: args,
        headers: {
            'Content-Type': 'application/json'
        }

    }).then(function mySucces(response) {
        result(response.data);
    }, function myError(response) {
        sweetAlert("Error!","Oops!", "error");
    });
}
;

var lat;
var lot;
function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function showPosition(position) {
    lat = position.coords.latitude;
    lot = position.coords.longitude;
}

function getLocLat() {
    return lat;
}
function getLocLot() {
    return lot;
}
function getZona() {
    var currentTime = new Date();
    var currentTimezone = currentTime.getTimezoneOffset();
    currentTimezone = (currentTimezone / 60) * -1;
    var gmt;
    if (currentTimezone !== 0) {
        gmt = currentTimezone > 0 ? ' +' : ' ';
        gmt = currentTimezone;
    }

    return gmt;
}
function setCookie(cname, cvalue) {
    document.cookie = cname + "=" + cvalue;
}
;

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

function formatDate(date) {
    var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
}

function loadData() {
    swal({
        title: "",
        text: "Please wait.....!",
        timer: 2000,
        showConfirmButton: false
    });
}

function  confirmSave() {
    var saveConfrim = confirm("Do you want to save ?");

    return saveConfrim;
}

