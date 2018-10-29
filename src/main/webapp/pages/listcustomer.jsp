<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<section class="content" ng-controller="MstNasabahController">
    <h3>Data Customer</h3>
    <div class="table-responsive">
        <table id="example2" class="table table-bordered table-hover" ng-init="getDataCustomer()">
            <thead>
                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                    <th>Customer Code</th>
                    <th>Fullname</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Flag Active</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataCustomer in dataCustomer">
                    <td>{{dataCustomer.cusCode}}</td>
                    <td>{{dataCustomer.fullname}}</td>
                    <td>{{dataCustomer.email}}</td>
                    <td>{{dataCustomer.phoneNumber}}</td>
                    <td>{{dataCustomer.flagActive}}</td>
                </tr>           
            </tbody>
        </table>
    </div>
</section>