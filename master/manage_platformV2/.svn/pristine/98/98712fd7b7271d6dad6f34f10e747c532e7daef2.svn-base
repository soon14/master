<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../../common/includeBaseSet.jsp" %>
    <%@include file="../../common/includeSystemSet.jsp" %>
    <link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css"/>
    <link href="${jypath}/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">
    <script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>

</head>
<body>
<div class="page-content">
    <div class="row-fluid">
        <div class="col-xs-12">
            <form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
                <div class="row">
                    <div class="widget-main">
                        <input name="beginTime" id="beginTime" value="" class="date-picker" type="text"
                                    placeholder="开始日期">
                        <input name="endTime" id="endTime" value="" class="date-picker" type="text"
                                    placeholder="结束日期">
                        &nbsp;&nbsp;
                        <button id='searchBtn' class="btn btn-warning  btn-xs" title="查询" type="button"
                                onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
                        <!-- <button id="download" class="btn btn-warning  btn-xs" title="下载" type="button" onclick="download1()">下载</button> -->
                        <button id='resetData' class="btn btn-warning  btn-xs" title="过滤" type="button">数据重置</button>
                        <a id='excelReport' class="btn btn-warning  btn-xs" href="#"
                           onclick="javascript:exportReport('${jypath}/backstage/cashDifference/downloadCash')">导出报表</a>
                    </div>
                </div>
                <input type='hidden' class='pageNum' name='pageNum' value='1'/>
                <input type='hidden' class='pageSize' name='pageSize' value='10'/>
            </form>
            <table id="baseTable" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <!-- <th style="width:8%" class="center" rowspan="1">日期</th> -->

                    <th style="width:5%" class="center hidden-480" colspan="1">日期</th>
                    <th style="width:5%" class="center hidden-480" colspan="1">期初差异额</th>
                    <th style="width:7%" class="center hidden-480" colspan="1">第三方对奖总额</th>
                    <th style="width:5%" class="center hidden-480" colspan="1">大额兑奖</th>
                    <th style="width:5%" class="center hidden-480" colspan="1">投注系统派奖总额</th>
                    <th style="width:7%" class="center hidden-480" colspan="1">线上派奖和兑奖的差异</th>
                    <th style="width:5%" class="center hidden-480" colspan="1">期末差异额</th>
                    <th style="width:5%" class="center hidden-480" colspan="1">线下兑奖金额</th>
                    <th style="width:5%" class="center hidden-480" colspan="1">总兑奖金额</th>
                    <th style="width:5%" class="center hidden-480" colspan="1">总差异金额</th>
                </tr>

                </thead>
                <tbody></tbody>
            </table>
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

<div>
</div>
<div>
    <span style="color: red">&nbsp&nbsp说明：</span><br>
    <span style="color: red">&nbsp&nbsp1>期初差异额：上个月期末差异额</span><br>
    <span style="color: red">&nbsp&nbsp2>第三方兑奖金额：该月目前所有兑奖的票机税后奖金的和</span><br>
    <span style="color: red">&nbsp&nbsp3>线上派奖和兑奖差异：该月投注系统派奖总额-第三方兑奖金额</span><br>
    <span style="color: red">&nbsp&nbsp4>期末差异额：期初差异额+线上派奖和兑奖差异额</span><br>
    <span style="color: red">&nbsp&nbsp5>线下兑奖金额：双击该金额,即可人工录入该金额</span><br>
    <span style="color: red">&nbsp&nbsp6>兑奖总金额：第三方兑奖金额+线下兑奖金额</span><br>
    <span style="color: red">&nbsp&nbsp7>单击线上派奖和兑奖差异额,即出现该差异的差异明细</span><br>
    <span style="color: red">&nbsp&nbsp8>数据重置的时间区间若在同一个月：重置该月的所有数据</span><br>
    <span style="color: red">&nbsp&nbsp9>数据重置的时间区间若跨越同一个月：重置区间内所有月的所有数据</span><br>
    <span style="color: red">&nbsp&nbsp10>数据重置的时间区间不能跨年,且不能超过2个月</span><br>
</div>
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/finance/reconciliation/cashDifference.js"></script>
<script type="text/javascript">
    function exportReport(url) {
        window.location.href = url + "?date=" + $('#beginTime').val();
    }
</script>


</body>
</html>