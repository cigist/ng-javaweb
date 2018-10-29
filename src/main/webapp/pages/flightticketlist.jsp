<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h3>Master Tiket Maskapai</h3>
<section class="content" ng-controller="MstMaskapaiController" ng-init="getDataTicket()">
    <div class="row">
        <div class="col-sm-2">
            <button type="button" class="btn btn-danger btn-block" ng-click="update()">
                <span class="glyphicon glyphicon-dashboard"> Update</span>
            </button>
        </div>
        <div class="col-sm-10" ng-init="getDataMaskapai()">
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
                    <th>Ticket Code</th>
                    <th>Dest From</th>
                    <th>Dest To</th>
                    <th>Airline Name</th>
                    <th>Date Departure</th>
                    <th>Date Return</th>
                    <th>Class</th>
                    <th>Price</th>
                    <th style="width:40px; border: 0x;"></th>
                    <th>Ticket</th>
                    <th>Flag Active</th>
                    <th style="width:40px;">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataTicket  in dataTicket| orderBy :airlineCode:code">
                    <td hidden="true">{{dataMaskapai.airlineCode}}</td>
                    <td>{{dataTicket.flightTicketCode}}</td>
                    <td>{{dataTicket.destinationTrom}}</td>
                    <td>{{dataTicket.destinationTo}}</td>
                    <td>{{dataTicket.airlineName}}</td>
                    <td>{{dataTicket.dateDeparture}}</td>
                    <td>{{dataTicket.dateReturn}}</td>
                    <td>{{dataTicket.seatClass}}</td>
                    <td>{{dataTicket.price}}</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal" ng-click="change(dataTicket)">
                            <span class="glyphicon glyphicon-edit"></span>
                        </button>
                    </td>
                    <td>{{dataTicket.ticket}}</td>
                    <td>{{dataTicket.flagActive}}</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm" ui-sref="maskapaimaster({code:dataTicket.airlineCode})">
                            <span class="glyphicon glyphicon-erase"></span>
                        </button>
                    </td>
                </tr>           
            </tbody>
        </table>
    </div>
</section>
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog" >
    <div class="modal-dialog modal-sm">

        <!-- Modal content-->
        <div class="modal-content" ng-controller="MstMaskapaiController">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Change Price</h4>
            </div>
            <div class="modal-body">
                Flight Ticket Code
                <input type="text" class="form-control" id="code" disabled="true">
                New Price
                <input type="text" class="form-control" ng-model="price" id="price">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger btn-block" ng-click="changePrice()" data-dismiss="modal">Change Price</button>
            </div>
        </div>

    </div>
</div>