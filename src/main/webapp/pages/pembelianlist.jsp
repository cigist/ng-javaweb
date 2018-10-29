<%-- 
    Document   : dasboard
    Created on : Jul 31, 2017, 1:38:45 AM
    Author     : user
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <!-- Main content -->
        <section class="content">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li>
                        <a data-target="#pulsa" data-toggle="tab">Master Pulsa</a>
                    </li>
                    <li>
                        <a data-target="#data" data-toggle="tab">Master Paket Data</a>
                    </li>
                    <li>
                        <a data-target="#game" data-toggle="tab">Master Game Online</a>
                    </li>
                    <li>
                        <a data-target="#pay" data-toggle="tab">Master Payment</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="pulsa" ng-controller="MstPulsaController">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box box-info">
                                    <h3 class="box-title">Harga Pulsa</h3>
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover" ng-init="getDataPulsa()">
                                            <thead>
                                                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                                                    <th>Pulsa Code</th>
                                                    <th>Description</th>
                                                    <th>Vendor Price</th>
                                                    <th>Price</th>
                                                    <th>Amount Fee</th>
                                                    <th style="width: 100px;">Flag Active</th>
                                                    <th style="width:50px;">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr ng-repeat="dataPulsa in dataPulsa| startFrom:currentPage * pageSize | limitTo:pageSize">
                                                    <td hidden="true">{{dataPulsa.vendorCode}}</td>
                                                    <td>{{dataPulsa.pulsaCode}}</td>
                                                    <td>{{dataPulsa.pulsaName}}</td>
                                                    <td>{{dataPulsa.vendorPrice| currency :" "}}</td>
                                                    <td>{{dataPulsa.price| currency :" "}}</td>
                                                    <td>{{dataPulsa.price - dataPulsa.vendorPrice| currency :" "}}</td>
                                                    <td>{{dataPulsa.flagActive}}</td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myPulsa" ng-click="edit(dataPulsa)">
                                                            <span class="glyphicon glyphicon-edit"></span>
                                                        </button>
                                                    </td>
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
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- PPOB PAKET DATA -->
                    <div class="tab-pane fade in" id="data" ng-controller="MstPpobPaketData">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box box-info">
                                    <h3 class="box-title">Harga Paket Data</h3>
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover" ng-init="getDataVendor()">
                                            <thead>
                                                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                                                    <th>Pulsa Code</th>
                                                    <th>Description</th>
                                                    <th>Vendor Price</th>
                                                    <th>Price</th>
                                                    <th>Amount Fee</th>
                                                    <th style="width: 100px;">Flag Active</th>
                                                    <th style="width:50px;">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr ng-repeat="dataPulsa in dataPulsa| startFrom:currentPage * pageSize | limitTo:pageSize">
                                                    <td hidden="true">{{dataPulsa.vendorCode}}</td>
                                                    <td>{{dataPulsa.pulsaCode}}</td>
                                                    <td>{{dataPulsa.pulsaName}}</td>
                                                    <td>{{dataPulsa.vendorPrice| currency :" "}}</td>
                                                    <td>{{dataPulsa.price| currency :" "}}</td>
                                                    <td>{{dataPulsa.price - dataPulsa.vendorPrice| currency :" "}}</td>
                                                    <td>{{dataPulsa.flagActive}}</td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myPulsa" ng-click="edit(dataPulsa)">
                                                            <span class="glyphicon glyphicon-edit"></span>
                                                        </button>
                                                    </td>
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
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- PPOB GAME ONLINE -->
                    <div class="tab-pane fade in" id="game" ng-controller="MstPpobGame">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box box-info">
                                    <h3 class="box-title">Harga Game</h3>
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover" ng-init="getDataGame()">
                                            <thead>
                                                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                                                    <th>Game Code</th>
                                                    <th>Description</th>
                                                    <th>Vendor Price</th>
                                                    <th>Price</th>
                                                    <th>Amount Fee</th>
                                                    <th style="width: 100px;">Flag Active</th>
                                                    <th style="width:50px;">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr ng-repeat="dataPulsa in dataPulsa| startFrom:currentPage * pageSize | limitTo:pageSize">
                                                    <td hidden="true">{{dataPulsa.vendorCode}}</td>
                                                    <td>{{dataPulsa.pulsaCode}}</td>
                                                    <td>{{dataPulsa.pulsaName}}</td>
                                                    <td>{{dataPulsa.vendorPrice| currency :" "}}</td>
                                                    <td>{{dataPulsa.price| currency :" "}}</td>
                                                    <td>{{dataPulsa.price - dataPulsa.vendorPrice| currency :" "}}</td>
                                                    <td>{{dataPulsa.flagActive}}</td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myPulsa" ng-click="edit(dataPulsa)">
                                                            <span class="glyphicon glyphicon-edit"></span>
                                                        </button>
                                                    </td>
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
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- PPOB PAYMENT -->
                    <div class="tab-pane fade in" id="pay" ng-controller="MstPpobPayment">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box box-info">
                                    <h3 class="box-title">Harga Pulsa</h3>
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover" ng-init="getDataPayment()">
                                            <thead>
                                                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                                                    <th>Payment Code</th>
                                                    <th>Description</th>
                                                    <th>Vendor Price</th>
                                                    <th>Price</th>
                                                    <th>Amount Fee</th>
                                                    <th style="width: 100px;">Flag Active</th>
                                                    <th style="width:50px;">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr ng-repeat="dataPulsa in dataPulsa| startFrom:currentPage * pageSize | limitTo:pageSize">
                                                    <td hidden="true">{{dataPulsa.vendorCode}}</td>
                                                    <td>{{dataPulsa.pulsaCode}}</td>
                                                    <td>{{dataPulsa.pulsaName}}</td>
                                                    <td>{{dataPulsa.vendorPrice| currency :" "}}</td>
                                                    <td>{{dataPulsa.price| currency :" "}}</td>
                                                    <td>{{dataPulsa.price - dataPulsa.vendorPrice| currency :" "}}</td>
                                                    <td>{{dataPulsa.flagActive}}</td>
                                                    <td>
                                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myPulsa" ng-click="edit(dataPulsa)">
                                                            <span class="glyphicon glyphicon-edit"></span>
                                                        </button>
                                                    </td>
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
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END PPOB -->
                </div>
                <!-- /.tab-content -->
            </div>
            <!-- /.nav-tabs-custom -->
        </section>

        <!-- Modal -->
        <div id="myPulsa" class="modal fade" role="dialog">
            <div class="modal-dialog modal-sm">

                <!-- Modal content-->
                <div class="modal-content" ng-controller="MstPulsaController">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Change Price Pulsa</h4>
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

        <!-- Modal -->
        <div id="myData" class="modal fade" role="dialog">
            <div class="modal-dialog modal-sm">

                <!-- Modal content-->
                <div class="modal-content" ng-controller="MstPpobPaketData">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Change Price Paket Data</h4>
                    </div>
                    <div class="modal-body">
                        Paket Data Code
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