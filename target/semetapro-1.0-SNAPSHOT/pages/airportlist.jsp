<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h3>Master Airport</h3>
<section class="content" ng-controller="MstAirportController" ng-init="getDataAirport()">
    <div class="row">
        <div class="col-sm-2">
            <button type="button" class="btn btn-danger btn-block" ng-click="add()">
                <span class="glyphicon glyphicon-plus"> Add</span>
            </button>
        </div>
        <div class="col-sm-10">
            <select type="text" class="form-control sm" placeholder="search airport name" ng-model="code">
                <option value="">-- All --</option>
                <option ng-repeat="dataAirport  in dataAirport" value="{{dataAirport.airportCode}}">{{dataAirport.airportName}}</option>
            </select>
        </div>
    </div>
    <br>
    <div class="table-responsive">
        <table id="example2" class="table table-bordered table-hover">
            <thead>
                <tr style="color: white;background-color:#CF3A24;text-align:center;">
<!--                    <th>No</th>-->
                    <th>Airport Code</th>
                    <th>Description</th>
                    <th>Domestic</th>
                    <th>Flag Active</th>
                    <th style="width:100px;">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataAirport  in dataAirport | filter :code | startFrom:currentPage*pageSize | limitTo:pageSize">
<!--                    <td>{{$index + 1}}</td>-->
                    <td>{{dataAirport.airportCode}}</td>
                    <td>{{dataAirport.airportName}}</td>
                    <td>{{dataAirport.domestic}}</td>
                    <td>{{dataAirport.flagActive}}</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm" ng-click="delete(dataAirport)">
                            <span class="glyphicon glyphicon-erase"></span>
                        </button>
                        <button type="button" class="btn btn-danger btn-sm" ui-sref="airportmaster({code:dataAirport.airportCode})">
                            <span class="glyphicon glyphicon-edit"></span>
                        </button>
                    </td>
                </tr>           
            </tbody>
        </table>
        <div class="container-full">
            <div class="col-sm-6">
                <select ng-model="pageSize" id="pageSize" class="btn btn-danger btn-sm">
                    <option value="5">5</option>
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