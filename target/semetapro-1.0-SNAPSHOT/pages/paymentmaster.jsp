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
<div class="box-body" ng-controller="MstPaymentController">
    <div class="box-header">
        <h3 class="box-title">Data Payment</h3>
    </div>
    <div class="box box-danger">
        <div class="box-body">
            <div class="col-sm-6">
                <div class="form-group">
                    <label>Payment Code</label>
                    <input type="text" class="form-control" id="airlinecode" ng-model="paymentcode">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input type="text" class="form-control" ng-model="paymentdesc">
                </div>
                <div class="form-group">
                    <label>Fee</label>
                    <input type="text" class="form-control" ng-model="fee">
                </div>
                <div class="form-group">
                    <label>Flag Active</label>
                    <select class="form-control" id="sel1" ng-model="flagactive">
                        <option value="Yes">Yes</option>
                        <option value="No">No</option>
                    </select>
                </div>
                <button type="button" class="btn btn-danger btn-sm" ng-click="save()">
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
