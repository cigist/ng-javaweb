<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h3>Master PPOB</h3>
<section class="content" ng-controller="MstPpobGame">
    <div class="row">
        <!--        <div class="col-sm-2">
            <label class="control-label">Search BY :</label>
                        <button type="button" class="btn btn-danger btn-block" ng-click="add()">
                            <span class="glyphicon glyphicon-plus"> Add</span>
                        </button>
        </div>-->
        <!-- <div class="col-sm-12" ng-init="getDataVendor()">
            <div class="form-group">
                <label class="control-label">Vendor Code</label>
                <select type="text" class="form-control sm" placeholder="search vendor name" ng-model="vendor">
                    <option value="">-- All --</option>
                    <option ng-repeat="dataVendor in dataVendor" value="{{dataVendor.vendorCode}}">{{dataVendor.vendorName}}</option>
                </select>
            </div>
        </div> -->
    </div>
    <br>
    <div class="table-responsive">
        <table class="table table-bordered table-hover" ng-init="getDataPayment()">
            <thead>
                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                    <th>Payment Code</th>
                    <th>Description</th>
                    <th>Categoty</th>
                    <!--                            <th>Vendor Price</th>
                                                <th>Price</th>
                                                <th>Amount Fee</th>
                                                <th style="width: 100px;">Flag Active</th>
                                                <th style="width:50px;">Action</th>-->
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataPulsa in dataPulsa| startFrom:currentPage * pageSize | limitTo:pageSize">
                    <td hidden="true">{{dataPulsa.vendorCode}}</td>
                    <td>{{dataPulsa.ppobCode}}</td>
                    <td>{{dataPulsa.ppobName}}</td>
                    <td>{{dataPulsa.response}}</td>
                    <!--                            <td>{{dataPulsa.vendorPrice| currency :" "}}</td>
                                                <td>{{dataPulsa.price| currency :" "}}</td>
                                                <td>{{dataPulsa.price - dataPulsa.vendorPrice| currency :" "}}</td>
                                                <td>{{dataPulsa.flagActive}}</td>
                                                <td>
                                                    <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myPulsa" ng-click="edit(dataPulsa)">
                                                        <span class="glyphicon glyphicon-edit"></span>
                                                    </button>
                                                </td>-->
                </tr>
            </tbody>
        </table>
        <div class="container-full">
            <div class="col-sm-6">
                <select ng-model="pageSize" id="pageSize" class="btn btn-danger btn-sm">
                    <option value="50">50</option>
                    <option value="250">250</option>
                    <option value="500">500</option>
                    <option value="1000">1000</option>
                </select>
                <button ng-disabled="currentPage == 0" ng-click="currentPage = currentPage - 1" class="btn btn-danger btn-sm">
                    <span class="glyphicon glyphicon-arrow-left"></span>
                </button>
                <label class="btn btn-danger btn-sm"> {{currentPage + 1}}/{{numberOfPages()}} </label>
                <button ng-disabled="currentPage >= getData().length / pageSize - 1" ng-click="currentPage = currentPage + 1" class="btn btn-danger btn-sm">
                    <span class="glyphicon glyphicon-arrow-right"></span>
                </button>
            </div>
        </div>
    </div>
</section>

<!-- Modal -->
<div id="myPulsa" class="modal fade" role="dialog">
    <div class="modal-dialog modal-sm">

        <!-- Modal content-->
        <div class="modal-content" ng-controller="MstPulsaController">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Change Price</h4>
            </div>
            <div class="modal-body">
                Pulsa Code
                <input type="text" class="form-control" id="pulsacode" disabled="true"> Vendor Price
                <input type="number" class="form-control" id="price" disabled> New Price
                <input type="number" class="form-control" ng-model="price" id="price" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger btn-block" ng-click="changePrice()" data-dismiss="modal">Change Price</button>
            </div>
        </div>

    </div>
</div>