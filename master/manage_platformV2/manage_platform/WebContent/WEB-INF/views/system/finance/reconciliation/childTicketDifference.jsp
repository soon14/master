<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../../common/includeBaseSet.jsp" %>
    <%@include file="../../common/includeSystemSet.jsp" %>
</head>
<body>
<div class="page-content">
    <div class="row-fluid">
        <div class="col-xs-12">
            <form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
                <div class="row">
                    <div class="widget-main">
                   	  <input name ="beginTime" id = "beginTime" value="" class="date-picker" type="text" placeholder="使用日期">
                   	  券号 :<input type="text" name="VNo"  class="input-large">
                   	&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="查询" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
                    </div>
                </div>
                <input type='hidden' class='pageNum' name='pageNum' value='1'/>
                <input type='hidden' class='pageSize'  name='pageSize' value='10'/>
                <input type='hidden'  name='VStart' value='${VStart}' id='VStart'/>
            </form>
            <table id="baseTable" class="table table-striped table-bordered table-hover" >
                <thead>
                 <tr>
                    <th style="width:7%" class="center hidden-480">日期</th>
                    <th style="width:7%" class="center hidden-480">券号</th>
                    <th style="width:7%" class="center hidden-480">体彩状态</th>
                    <th style="width:7%" class="center hidden-480">投注状态</th>
                    <th style="width:7%" class="center hidden-480">体彩金额</th>
                    <th style="width:7%" class="center hidden-480">投注金额</th>
                    <th style="width:7%" class="center hidden-480">差异金额</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
            <div class="row">
            <div class="col-sm-4">
            <div class="dataTables_info customBtn" >
            <c:forEach var="pbtn" items="${permitBtn}">
            <a href="#" title="${pbtn.name}" id="${pbtn.btnId}" class="lrspace3" ><i class='${pbtn.icon} bigger-220'></i></a>
            </c:forEach>
            </div>
            </div>
            <div class="col-sm-8">
            <!--设置分页位置-->
            <div id="pageing" class="dataTables_paginate paging_bootstrap">
            <ul class="pagination"></ul>
            </div>
            </div>
            </div>
        </div>
    </div>
</div>
<script src="${jypath}/static/js/system/finance/reconciliation/childTicketDifference.js"></script>
</body>
</html>