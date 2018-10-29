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
<div class="box-body" ng-controller="MstUserController">
    <div class="box-header">
        <h3 class="box-title">Change Password</h3>
    </div>
    <div class="box box-danger">
        <div class="box-body">
            <div class="col-sm-6">
                <div class="form-group">
                    <label>Username</label>
                    <input type="text" class="form-control" ng-model="username">
                </div>
                <div class="form-group">
                    <label>Old Password</label>
                    <input type="password" class="form-control" ng-model="oldpassword">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" ng-model="password">
                </div>
                <button type="button" class="btn btn-danger btn-sm" ng-click="update()">
                    Simpan
                </button>
                <button type="button" class="btn btn-danger btn-sm" ng-click="cancel()">
                    Batal
                </button>
            </div>
            <!-- /.form group -->
        </div>
        <!-- /.box-body -->
    </div>
    <!-- /.box -->
</div>
<!-- /.box-body -->
