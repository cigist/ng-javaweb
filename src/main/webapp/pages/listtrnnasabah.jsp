<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<section class="content" ng-controller="MstNasabahController">
    <h3>Data Transaksi Nasabah</h3>
    <div class="table-responsive">
        <table id="example2" class="table table-bordered table-hover" ng-init="getTrnNasabah()">
            <thead>
                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                    <th>Transaction Number</th>
                    <th>Amount</th>
                    <th>Transaction Date</th>
                    <th>Transaction Time</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataTrnNasabah in dataTrnNasabah">
                    <td>{{dataTrnNasabah.tran_number}}</td>
                    <td>{{dataTrnNasabah.amount}}.00</td>
                     <td>{{dataTrnNasabah.tranDate}}</td>
                    <td>{{dataTrnNasabah.tranTime}}</td>
                </tr>           
            </tbody>
        </table>
    </div>
</section>