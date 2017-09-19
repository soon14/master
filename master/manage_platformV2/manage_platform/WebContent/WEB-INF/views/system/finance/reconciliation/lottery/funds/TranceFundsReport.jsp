<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../../../../common/includeBaseSet.jsp" %>
    <%@include file="../../../../common/includeSystemSet.jsp" %>
    <link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />
    <link href="${jypath}/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">
    <script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
<div class="page-content">
    <div class="row-fluid">
        <div class="col-xs-12">
            <form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
                <div class="row">
                    <div class="widget-main-marginleft18">
                        <input name="beginTime" id="beginTime" value="" class="date-picker" type="text" placeholder="开始日期" >
                        <input name="endTime" value="" class="date-picker" type="text" placeholder="结束日期" >
                    &nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>

                        <button id='resetData' class="btn btn-warning  btn-xs" title="过滤" type="button" >数据重置</button>
                        <a id='excelReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportReport('${jypath}/backstage/reconciliation/lottery/tranceFunds/download')">导出报表</a>
                    </div>
                </div>
                <input type='hidden' class='pageNum' name='pageNum' value='1'/>
                <input type='hidden' class='pageSize'  name='pageSize' value='10'/>
            </form>
            <table id="baseTable" class="table table-striped table-bordered table-hover" >
                <thead>
                <tr>
                    <th style="width:10%" class="center">日期</th>
                    <th style="width:10%"  class="center hidden-480">期初追期额</th>
                    <th style="width:5%" class="center hidden-480">当日出票额</th>
                    <th style="width:8%"  class="center ">退款额</th>
                    <th style="width:5%"  class="center hidden-480">新增追期金额</th>
                    <th style="width:5%" class="center hidden-480">期末追期余额</th>
                    <th style="width:5%" class="center hidden-480">差异</th>
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
        <%@include file="../../../../account/form.jsp" %>
        <%@include file="../../../../common/dialog.jsp" %>
    </div>
    <div>
        <span style="color: red">说明：</span><br>
        <span style="color: red">期初追期额：截止T日所有支付成功但未出票的追期订单总额</span><br>
        <span style="color: red">当日出票额：T日出票的追期订单总额</span><br>
        <span style="color: red">退款额：T日退款的追期订单总额</span><br>
        <span style="color: red">新增追期额：T日下单成功的追期订单总额</span><br>
        <span style="color: red">期末追期额：T+1日期初追期订单总额</span><br>
        <span style="color: red">差异额：期末余额-计算得出的期末余额</span><br>
        <span style="color: red">计算得出的期末余额：期初-出票-退款+新增</span>
    </div>

</div>
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/finance/reconciliation/lottery/funds/tranceFunds.js"></script>
</body>
</html>