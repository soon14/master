<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../../../common/includeBaseSet.jsp" %>
    <%@include file="../../../common/includeSystemSet.jsp" %>
    <link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />
    <script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
<div>

</div>
<div class="page-content">
    <div class="row-fluid">
        <div class="col-xs-12">
            <form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
                <div class="row">
                    <div class="widget-main-marginleft18">
                        <input name="beginTime" id="beginTime" value="" class="date-picker" type="text" placeholder="开始日期">
                        <input name="endTime" value="" class="date-picker" type="text" placeholder="结束日期">
                        <select   name="source"  >
                            <option  value = "" selected = "selected">全部</option >
                            <option  value = "1" >方案和票</option >
                            <option  value = "2" >购彩和出票</option >
                            <option  value = "3" >出票系统和彩票机</option >
                            <option  value = "4" >销售汇总</option >
                        </select >&nbsp;&nbsp;
                        <button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button"
                                onclick="getDataList(1)">
                            <i class="icon-search bigger-110 icon-only"></i>
                        </button>
                        <button id='resetData' class="btn btn-warning  btn-xs" title="过滤" type="button" >数据重置</button>
                        <a id='excelCMCCReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportReport('${jypath}/backstage/statistics/lottery/lotterySalesDiffReport/download')">导出报表</a>
                    </div>
                </div>
                <input type='hidden' class='pageNum' name='pageNum' value='1'/>
                <input type='hidden' class='pageSize' name='pageSize' value='10'/>
            </form>
            <table id="baseTable" class="table table-striped table-bordered table-hover" >
                <thead>
                <tr>
                    <th style="width:5%"  class="center hidden-480">时间</th>
                    <th style="width:5%" class="center">编号</th>
                    <th style="width:5%"  class="center hidden-480">差异金额</th>
                    <th style="width:5%" class="center hidden-480">差异类型</th>
                    <th style="width:5%" class="center hidden-480">差异原因</th>
                    <th style="width:5%" class="center hidden-480">处理状态</th>
                    <th style="width:5%" class="center hidden-480">是否审核</th>
                    <th style="width:5%" class="center hidden-480">来源</th>
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
        <%@include file="../../../account/form.jsp" %>
        <!-- #dialog-confirm -->
        <%@include file="../../../common/dialog.jsp" %>
    </div>
</div>
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/finance/statistics/lottery/LotterySalesDiffReport.js"></script>
</body>
</html>