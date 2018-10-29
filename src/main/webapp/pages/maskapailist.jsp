<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h3>Master Maskapai</h3>
<section class="content" ng-controller="MstMaskapaiController" ng-init="getDataMaskapai()">
    <div class="row">
        <div class="col-sm-2">
            <button type="button" class="btn btn-danger btn-block" ng-click="add()">
                <span class="glyphicon glyphicon-plus"> Add</span>
            </button>
        </div>
        <div class="col-sm-10">
            <select type="text" class="form-control sm" placeholder="search airline name" ng-model="code">
                <option ng-repeat="dataMaskapai  in dataMaskapai" value="dataMaskapai.airlineCode">{{dataMaskapai.airlineName}}</option>
            </select>
        </div>
    </div>
    <br>
    <div class="table-responsive">
        <table id="example2" class="table table-bordered table-hover">
            <thead>
                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                    <th>Airline Code</th>
                    <th>Description</th>
                    <th>Domestic</th>
                    <th>Flag Active</th>
                    <th style="width:100px;">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataMaskapai  in dataMaskapai | orderBy :airlineCode:code">
                    <td>{{dataMaskapai .airlineCode}}</td>
                    <td>{{dataMaskapai .airlineName}}</td>
                    <td>{{dataMaskapai .domestic}}</td>
                    <td>{{dataMaskapai .flagActive}}</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm" ng-click="delete(dataMaskapai)">
                            <span class="glyphicon glyphicon-erase"></span>
                        </button>
                        <button type="button" class="btn btn-danger btn-sm" ui-sref="maskapaimaster({code:dataMaskapai .airlineCode})">
                            <span class="glyphicon glyphicon-edit"></span>
                        </button>
                    </td>
                </tr>           
            </tbody>
        </table>
    </div>
</section>