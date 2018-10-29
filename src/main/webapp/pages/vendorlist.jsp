<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h3>Master Vendor</h3>
<section class="content" ng-controller="MstVendorController">
    <div class="row">
        <div class="col-sm-2">
            <button type="button" class="btn btn-danger btn-block" ui-sref="vendormaster">
                <span class="glyphicon glyphicon-plus"> Add</span>
            </button>
        </div>
        <div class="col-sm-10">
            <select type="text" class="form-control sm" placeholder="search vendor name" ng-model="type">
                <option value="PULSA">PULSA</option>
                <option value="DATA">DATA</option>
                <option value="TOKEN">TOKEN</option>
                <option value="TIKET">TIKET</option>
            </select>
        </div>
    </div>
    <br>
    <div class="table-responsive">
        <table id="example2" class="table table-bordered table-hover" ng-init="getDataVendor()">
            <thead>
                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                    <th>Vendor Code</th>
                    <th>Description</th>
                    <th>Type</th>
                    <th>Flag Active</th>
                    <th style="width:100px;">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataVendor in dataVendor| orderBy :vendorType:type">
                    <td>{{dataVendor.vendorCode}}</td>
                    <td>{{dataVendor.vendorName}}</td>
                    <td>{{dataVendor.vendorType}}</td>
                    <td>{{dataVendor.flagActive}}</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm" ng-click="delete(dataVendor)">
                            <span class="glyphicon glyphicon-erase"></span>
                        </button>
                        <button type="button" class="btn btn-danger btn-sm" ng-click="edit(dataVendor)">
                            <span class="glyphicon glyphicon-edit"></span>
                        </button>
                    </td>
                </tr>           
            </tbody>
        </table>
    </div>
</section>