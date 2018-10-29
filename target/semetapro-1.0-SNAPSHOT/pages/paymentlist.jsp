<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h3>Master Payment</h3>
<section class="content" ng-controller="MstPaymentController" ng-init="getDataPayment()">
    <div class="row">
        <div class="col-sm-2">
            <button type="button" class="btn btn-danger btn-block" ng-click="add()">
                <span class="glyphicon glyphicon-plus"> Add</span>
            </button>
        </div>
        <div class="col-sm-10">
            <select type="text" class="form-control sm" placeholder="search airline name" ng-model="code">
                <option ng-repeat="dataPayment  in dataPayment" value="dataPayment.paymentCode">{{dataPayment.paymentDesc}}</option>
            </select>
        </div>
    </div>
    <br>
    <div class="table-responsive">
        <table id="example2" class="table table-bordered table-hover">
            <thead>
                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                    <th>Payment Code</th>
                    <th>Description</th>
                    <th>Fee</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataPayment  in dataPayment | orderBy :ppobCode:code">
                    <td>{{dataPayment .ppobCode}}</td>
                    <td>{{dataPayment .ppobName}}</td>
                    <td>{{dataPayment .fee}}</td>
                    <td>{{dataPayment .flagActive}}</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm" ui-sref="paymentmaster({code:dataPayment.paymentCode})">
                            <span class="glyphicon glyphicon-edit"></span>
                        </button>
                    </td>
                </tr>           
            </tbody>
        </table>
    </div>
</section>