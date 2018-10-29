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
<div class="box-body" ng-controller="ReportController">
    <div class="box-header">
        <h3 class="box-title">Report</h3>
    </div>
    <div class="box box-danger">
        <div class="box-body">
            <div class="col-sm-6">
                <button type="button" class="btn btn-danger btn-sm" ng-click="doPrintTrnRpt()">
                    Print Report Transaction
                </button>
            </div>
            <!-- /.form group -->
        </div>
        <!-- /.box-body -->
    </div>
    <div class="box box-danger">
        <div class="box-body">
            <div class="col-sm-6">
                <button type="button" class="btn btn-danger btn-sm" ng-click="doPrintTrnFeeRpt()">
                    Print Report Agen Fee
                </button>
            </div>
            <!-- /.form group -->
        </div>
        <!-- /.box-body -->
    </div>
     <div class="box box-danger">
        <div class="box-body">
            <div class="col-sm-6">
                <button type="button" class="btn btn-danger btn-sm" ng-click="doPrintPulsa()">
                    Print Report Pulsa
                </button>
            </div>
            <!-- /.form group -->
        </div>
        <!-- /.box-body -->
    </div>
    <div class="box box-danger">
        <div class="box-body">
            <div class="col-sm-6">
                <button type="button" class="btn btn-danger btn-sm" ng-click="doPrintPaketData()">
                    Print Report Paket Data
                </button>
            </div>
            <!-- /.form group -->
        </div>
        <!-- /.box-body -->
    </div>
    <!-- /.box -->
</div>
<!-- /.box-body -->
