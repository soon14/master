<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../../common/includeBaseSet.jsp" %>
    <%@include file="../../common/includeSystemSet.jsp" %>
    <link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />
    <script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>

</head>
<body>
<div class="page-content">
    <div class="row-fluid">
        <div class="col-xs-12">
            <form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
                <div class="row">
                    <div class="widget-main">
                        <input name ="beginTime" id = "beginTime" value="" class="date-picker" type="text" placeholder="开始日期">

                        <!-- <button id="download" class="btn btn-warning  btn-xs" title="下载" type="button" onclick="download1()">下载</button> -->
                        <a id='excelReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportReport('${jypath}/backstage/cashDifference/downloadDetail')">导出报表</a>
                        <input name ="schemeExtendId" id = "schemeExtendId" value=""  type="text" placeholder="方案扩展ID">
                        &nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="查询" type="button" onclick="getbaseLists(2)"><i class="icon-search bigger-110 icon-only"></i></button>
                    </div>
                </div>
                <input type='hidden' class='pageNum' name='pageNum' value='1'/>
                <input type='hidden' class='pageSize'  name='pageSize' value='10'/>
                <input type='hidden'  name='billDate' value='${billDate}' id='billDate' />
                <%-- <input type='hidden'  name='ids' value='${ids}' id='ids' /> --%>
            </form>
            <table id="baseTable" class="table table-striped table-bordered table-hover" >
                <thead>
                <tr>
                    <th style="width:7%" class="center hidden-480">计奖日期</th>
                    <th style="width:7%" class="center hidden-480">派奖日期</th>
                    <th style="width:7%" class="center hidden-480">方案扩展ID</th>
                    <th style="width:7%" class="center hidden-480">线上派奖金额</th>
                    <th style="width:7%" class="center hidden-380">线上兑奖金额</th>
                    <th style="width:7%" class="center hidden-580">差异金额</th>
                    <!-- <th style="width:7%" class="center hidden-480">差异状态</th> -->
                    <th style="width:7%" class="center hidden-480">备注</th>
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
<script src="${jypath}/static/js/system/finance/reconciliation/childCashDifference.js"></script>
<script type="text/javascript">
    function exportReport(url){
        window.location.href=url+"?date="+$('#beginTime').val();
    }
</script>
</body>
</html>