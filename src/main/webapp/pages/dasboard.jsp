<%-- 
    Document   : dasboard
    Created on : Jul 31, 2017, 1:38:45 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Main content -->
<section class="content" ng-controller="MstDasboardController" ng-init="chart()">
    <div class="row" ng-init="getDasbaord()">
        <div class="col-lg-3 col-xs-6">
            <!-- small box -->
            <div class="small-box bg-aqua">
                <div class="inner">
                    <h4>{{dataNasabah}}</h4>

                    <p>Nasabah Baru</p>
                </div>
                <div class="icon">
                    <i class="ion ion-bag"></i>
                </div>
                <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
            <!-- small box -->
            <div class="small-box bg-green">
                <div class="inner">
                    <h4>{{dataTransaksi}}</h4>

                    <p>Total Transaksi</p>
                </div>
                <div class="icon">
                    <i class="ion ion-stats-bars"></i>
                </div>
                <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
            <!-- small box -->
            <div class="small-box bg-yellow">
                <div class="inner">
                    <h4>{{dataPembelian}}</h4>

                    <p>Total Pembelian</p>
                </div>
                <div class="icon">
                    <i class="ion ion-person-add"></i>
                </div>
                <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
            <!-- small box -->
            <div class="small-box bg-red">
                <div class="inner">
                     <h4>{{dataPembayaran}}</h4>

                    <p>Total Pembayaran</p>
                </div>
                <div class="icon">
                    <i class="ion ion-pie-graph"></i>
                </div>
                <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
            </div>
        </div>
        <!-- ./col -->
    </div>
    <div class="nav-tabs-custom">
        <ul class="nav nav-tabs">
            <li><a href="#pembelian" data-toggle="tab">Pembelian</a></li>
            <li><a href="#pembayaran" data-toggle="tab">Pembayaran</a></li>
            <li><a href="#totalpembelian" data-toggle="tab">Total Pembelian</a></li>
            <li><a href="#totalpembayaran" data-toggle="tab">Total Pembayaran</a></li>
        </ul>
        <div class="tab-content">
            <div class=" active tab-pane" id="pembelian">             
                <div class="row">
                    <div class="col-md-12">
                        <div class="box box-info">
                            <h3 class="box-title">Pembelian</h3>
                            <div class="box-body chart-responsive">
                                <div class="chart" id="line-chart1" style="height: 300px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class=" active tab-pane" id="pembayaran">             
                <div class="box box-danger">
                    <h3 class="box-title">Pembayaran</h3>
                    <div class="box-body chart-responsive">
                        <div class="chart" id="line-chart2" style="height: 300px;"></div>
                    </div>
                </div>
            </div>
            <div class=" active tab-pane" id="totalpembelian">             
                <div class="row">
                    <div class="col-md-12">
                        <div class="box box-primary">
                            <h3 class="box-title">Total Pembelian</h3>
                            <div class="box-body chart-responsive">
                                <div class="chart" id="line-chart3" style="height: 300px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class=" active tab-pane" id="totalpembayaran">             
                <div class="row">
                    <div class="col-md-12">
                        <div class="box box-success">
                            <h3 class="box-title">Total Pembayaran</h3>
                            <div class="box-body chart-responsive">
                                <div class="chart" id="line-chart4" style="height: 300px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.tab-content -->
    </div>
    <!-- /.nav-tabs-custom -->
</section>

