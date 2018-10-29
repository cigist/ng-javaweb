<%-- 
    Document   : tambahuser
    Created on : Aug 5, 2017, 12:44:07 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>
<!-- Main content -->
<div class="box-body" ng-controller="MstNasabahController">
    <div class="box-header">
        <h3 class="box-title">Data Nasabah</h3>
    </div>
    <div class="box box-danger">
        <div class="box-body">
            <div class="col-sm-6">
                <div class="form-group">
                    <label>Fullname</label>
                    <input type="text" class="form-control" ng-model="fullname">
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" class="form-control" ng-model="email">
                </div>
                <div class="form-group">
                    <label>Phone Number</label>
                    <input type="text" class="form-control" ng-model="phonenumber">
                </div>
                <div class="form-group">
                    <label>Address </label>
                    <textarea type="text" class="form-control" ng-model="address"></textarea>
                </div>
                <div class="form-group">
                    <label>Select File: KTP/SIM</label>
                    <input type="file" class="btn btn-danger" id="ximage" ng-model="image"/><br/>  
                </div>
                <button type="button" class="btn btn-danger btn-sm" ng-click="postNasabah()">
                    Submit
                </button>
                <button type="button" class="btn btn-danger btn-sm" ng-click="cancelNasabah()">
                    Cancel
                </button>
            </div>
            <!-- /.form group -->
        </div>
        <!-- /.box-body -->
    </div>
    <!-- /.box -->
</div>
<!-- /.box-body -->
