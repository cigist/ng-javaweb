<%-- 
    Document   : listuser
    Created on : Jul 31, 2017, 1:39:06 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<section class="content" ng-controller="MstUserController">
    <h3>Master User</h3>
    <div class="row">
        <div class="col-sm-3">
            <button type="button" class="btn btn-danger btn-sm" ng-click="add()">
                <span class="glyphicon glyphicon-plus"> Add</span>
            </button>
        </div>
    </div>
    <br>
    <div class="table-responsive">
        <table id="example2" class="table table-bordered table-hover" ng-init="getDataUser()">
            <thead>
                <tr style="color: white;background-color:#CF3A24;text-align:center;">
                    <th>User Code</th>
                    <th>Username</th>
                    <th>Flag Active</th>
                    <th style="width:50px;">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="dataUser in dataUser">
                    <td>{{dataUser.userCode}}</td>
                    <td>{{dataUser.username}}</td>
                    <td>{{dataUser.flagActive}}</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm" ng-click="delete(dataUser)">
                            <span class="glyphicon glyphicon-erase"></span>
                        </button>
<!--                        <button type="button" class="btn btn-danger btn-sm" ng-click="edit(dataUser)">
                            <span class="glyphicon glyphicon-edit"></span>
                        </button>-->
                    </td>
                </tr>           
            </tbody>
        </table>
    </div>
</section>