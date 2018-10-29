<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h3>Data Nasabah</h3>
<section class="content" ng-controller="MstNasabahController">
    <div class="row well-sm">
        <div class="col-sm-2">
            <button class="btn btn-danger btn-block" ng-click="getDataNasabah()"> <span class="glyphicon glyphicon-refresh"> Refresh</span></button>
        </div>
        <div class="col-sm-10">
            <div class="row">    
                <div class="col-xs-12">
                    <div class="input-group">
                        <div class="input-group-btn search-panel">
                            <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                                <span id="search_concept">Cari : </span></span>
                            </button>
<!--                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#contains">Contains</a></li>
                                <li><a href="#its_equal">It's equal</a></li>
                                <li><a href="#greather_than">Greather than ></a></li>
                                <li><a href="#less_than">Less than < </a></li>
                                <li class="divider"></li>
                                <li><a href="#all">Anything</a></li>
                            </ul>-->
                        </div>       
                        <input type="text" class="form-control" ng-model="name" placeholder="Input nama nasabah">
<!--                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                        </span>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="table-responsive">
        <table id="example2" class="table table-bordered table-hover" ng-init="getDataNasabah()">
            <thead>
                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                    <th>Account Number</th>
                    <th>Account Name</th>
                    <th>Account Type</th>
                    <th>Saldo</th>
                    <th>Flag Active</th>
                    <th style="width: 50px;">#</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataNasabah in dataNasabah|filter :name | startFrom:currentPage * pageSize | limitTo:pageSize">
                    <td>{{dataNasabah.accountNumber}}</td>
                    <td>{{dataNasabah.accountName}}</td>
                    <td>{{dataNasabah.accountType}}</td>
                    <td>{{dataNasabah.endingBalance}}.00</td>
                    <td>{{dataNasabah.flagActive}}</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm" ui-sref="listtrnnasabah({account:dataNasabah.accountNumber})">
                            <span class="glyphicon glyphicon-transfer"> </span>
                        </button>
                    </td>
                </tr>           
            </tbody>
        </table>
        <div class="container-full">
            <div class="col-sm-6">
                <select ng-model="pageSize" id="pageSize" class="btn btn-danger btn-sm">
                    <option value="10">10</option>
                    <option value="50">50</option>
                    <option value="250">250</option>
                    <option value="500">500</option>
                </select>
                <button ng-disabled="currentPage == 0" ng-click="currentPage = currentPage - 1" class="btn btn-danger btn-sm">
                    <span class="glyphicon glyphicon-arrow-left"></span>
                </button>
                <label class="btn btn-danger btn-sm" > {{currentPage + 1}}/{{numberOfPages()}} </label>
                <button ng-disabled="currentPage >= getData().length / pageSize - 1" ng-click="currentPage = currentPage + 1" class="btn btn-danger btn-sm">
                    <span class="glyphicon glyphicon-arrow-right"></span>
                </button>
            </div>
        </div>
    </div>
</section>