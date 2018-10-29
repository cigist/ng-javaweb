<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<section class="content" ng-controller="ListSetorTunai">
    <h3>Data Transaksi Setor Tunai</h3>
<!--    <div class="row">
        <div class="col-sm-3">
            <button type="button" class="btn btn-danger btn-sm" ng-click="add()">
                Setor Tunai
            </button>
        </div>
    </div>-->
    <br>
    <div class="table-responsive">
        <table id="example2" class="table table-bordered table-hover" ng-init="getTrnSetorTunai()">
            <thead>
                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                    <th>Transaction Number</th>
                    <th>Account Nasabah</th>
                    <th>Account Agent</th>
                    <th>Amount</th>
                    <th>Transaction Date</th>
                    <th>Transaction Time</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataTrnSetor in dataTrnSetor">
                    <td>{{dataTrnSetor.tran_number}}</td>
                    <td>{{dataTrnSetor.accountNumber}}</td>
                    <td>{{dataTrnSetor.accountNumberAgen}}</td>
                    <td>{{dataTrnSetor.amount}}.00</td>
                    <td>{{dataTrnSetor.tranDate}}</td>
                    <td>{{dataTrnSetor.tranTime}}</td>
                </tr>           
            </tbody>
        </table>
    </div>
</section>