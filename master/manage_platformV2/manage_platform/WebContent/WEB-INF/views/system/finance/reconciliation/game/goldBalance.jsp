<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../../../common/includeBaseSet.jsp" %>
    <%@include file="../../../common/includeSystemSet.jsp" %>
    <link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css"/>
    <link rel="stylesheet" href="${jypath}/static/js/jquery/jebox/skin/default.css" />
    <link rel="stylesheet" href="${jypath}/static/css/system/system/basic.css" />
    <script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
<div class="page-content">
    <div class="row-fluid">
        <div class="col-xs-12">
            <form id="fundsForm" class="form-inline" method="POST" onsubmit="return false;">
                <div class="row">
                    <div class="widget-main-marginleft18">
                        <input name="beginTime" id="beginTime" value="" class="date-picker" type="text" placeholder="开始日期">
                        <input name="endTime" value="" class="date-picker" type="text" placeholder="结束日期">
                        <button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button"
                                onclick="getList(1)">
                            <i class="icon-search bigger-110 icon-only"></i>
                        </button>
                        <button id='resetData' class="btn btn-warning  btn-xs" title="过滤" type="button" >数据重置</button>
                        <a id='excelsReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportReport('${jypath}/backstage/reconciliation/goldBalance/download')">导出报表</a>
                    </div>
                </div>
          <%--      <div class="row">
                    <div class="widget-main-marginleft18">
                        <button id='resetData' class="btn btn-warning  btn-xs" title="过滤" type="button" >数据重置</button>
                        <a id='excelsReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportReport('${jypath}/backstage/reconciliation/goldBalance/download')">导出报表</a>
                        &lt;%&ndash;<a id='excelReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportFundsReport('${jypath}/backstage/reconciliation/download')">汇总差异报表</a>&ndash;%&gt;
                    </div>
                </div>--%>
                <input type='hidden' class='pageNum' name='pageNum' value='1'/>
                <input type='hidden' class='pageSize' name='pageSize' value='10'/>
            </form>
            <table id="fundsTable" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th style="width:3%" class="center" rowspan="2">
                        <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                    </th>
                    <th style="width:8%" class="center" >日期</th>
                    <th style="width:5%" class="center" >期初金币数</th>
                    <th style="width:5%" class="center hidden-480" >期初结余金额</th>
                    <th style="width:7%" class="center hidden-480" >本期有价金币购买数</th>
                    <th style="width:5%" class="center hidden-480" >本期有价金币购买价值</th>
                    <th style="width:5%" class="center hidden-480" >购买金币消耗钻石数量</th>
                    <th style="width:5%" class="center hidden-480" >有价金币货币单价（加权平均）</th>
                    <th style="width:7%" class="center hidden-480" >本期有价金币消耗数</th>
                    <th style="width:5%" class="center hidden-480" >本期有价金币结转价值</th>
                    <th style="width:5%" class="center hidden-480" >期末金币数量</th>
                    <th style="width:5%" class="center hidden-480" >期末结余货币价值</th>
                    <th style="width:5%" class="center hidden-480" >期末金币货币单价（加权平均）</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
            <div>
                <span style="color: red">说明：</span><br>
                <span style="color: red">有价金币货币单价（加权平均）：(本期有价金币购买价值+期初结余金额)/(本期有价金币购买数+期初金币数)</span><br>
                <span style="color: red">期末金币货币单价（加权平均）：期末结余货币价值/期末金币数量</span>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <div class="dataTables_info customBtn">
                        <c:forEach var="pbtn" items="${permitBtn}">
                            <a href="#" title="${pbtn.name}" id="${pbtn.btnId}" class="lrspace3"><i
                                    class='${pbtn.icon} bigger-220'></i></a>
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
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/finance/reconciliation/game/goldBalance.js"></script>
</body>
</html>