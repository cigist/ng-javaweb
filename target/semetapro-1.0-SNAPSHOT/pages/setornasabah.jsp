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
        <h3 class="box-title">Setor Nasabah</h3>
    </div>
    <div class="box box-danger">
        <div class="box-body">
            <div class="col-sm-6">
                <div class="form-group">
                    <label>Account Number</label>
                    <input type="text" class="form-control" ng-model="accountnumber">
                </div>
                <div class="form-group">
                    <label>Nominal</label>
                    <input type="text" class="form-control" ng-model="nominal">
                </div>
                <div class="form-group">
                    <label>Keterangan</label>
                    <textarea type="text" class="form-control" ng-model="keterangan"></textarea>
                </div>
                <button type="button" class="btn btn-danger btn-sm" ng-click="postSetor()">
                    Submit
                </button>
                <button type="button" class="btn btn-danger btn-sm" ng-click="cancelSetor()">
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
