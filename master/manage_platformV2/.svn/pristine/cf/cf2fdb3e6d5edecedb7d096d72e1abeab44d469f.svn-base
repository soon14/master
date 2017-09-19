<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../../common/includeBaseSet.jsp" %>
    <%@include file="../../common/includeSystemSet.jsp" %>
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
                        <%--<span id="selectDealStatus">--%>
								<%--<label></label>：--%>
								<%--<select data-placeholder="处理状态" name="dealResultStatus"--%>
                                        <%--class="chosen-select isSelect95"></select>--%>
							<%--</span>--%>
                        <%--类型：--%>
                        <%--<select   name="fundsType"  >--%>
                            <%--<option  value = "1" selected = "selected">正常</option >--%>
                            <%--<option  value = "2" >移动活动</option >--%>
                            <%--<option  value = "3" >得仕捕鱼</option >--%>
                            <%--<option  value = "4" >得仕棋牌</option >--%>
                            <%--<option  value = "5" >欧建活动</option >--%>
                        <%--</select >&nbsp;&nbsp;--%>
                        <button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button"
                                onclick="getList(1)">
                            <i class="icon-search bigger-110 icon-only"></i>
                        </button>
                        <button id='resetData' onclick="reloadDate('fundsForm','/backstage/reconciliation/platManual')" class="btn btn-warning  btn-xs" title="过滤" type="button" >数据重置</button>
                        <a id='excelsReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportReport('${jypath}/backstage/reconciliation/download')">导出报表</a>
                    </div>
                </div>
                <%--<div class="row">
                    <div class="widget-main-marginleft18">
                        <button id='resetData' class="btn btn-warning  btn-xs" title="过滤" type="button" >数据重置</button>
                        <a id='excelsReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportReport('${jypath}/backstage/reconciliation/download')">导出报表</a>
                        &lt;%&ndash;<a id='excelReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportFundsReport('${jypath}/backstage/reconciliation/download')">汇总差异报表</a>&ndash;%&gt;
                        &lt;%&ndash;<a id='excelCMCCReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportCMCCReport('${jypath}/backstage/reconciliation/download')">导出移动活动报表</a>&ndash;%&gt;
                    </div>
                </div>--%>
                <input type='hidden' class='pageNum' name='pageNum' value='1'/>
                <input type='hidden' class='pageSize' name='pageSize' value='10'/>
                <input type='hidden' class='pageSize' name="fundsType" value='1'/>

            </form>
            <table id="fundsTable" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th style="width:3%" class="center" rowspan="3">
                        <label><input type="checkbox" class="ace"><span class="lbl"></span></label>
                    </th>
                    <th style="width:8%" class="center" rowspan="3">日期</th>
                    <th style="width:5%" class="center" rowspan="3">期初余额</th>
                    <th style="width:5%" class="center hidden-480" colspan="2">充值金额</th>
                    <th style="width:7%" class="center hidden-480" colspan="4">购彩</th>
                    <th style="width:5%" class="center hidden-480" rowspan="3">派奖金额</th>
                    <th style="width:5%" class="center hidden-480" colspan="2">提现金额</th>
                    <th style="width:7%" class="center hidden-480" colspan="2">退款总额</th>
                    <th style="width:5%" class="center" rowspan="3">佣金</th>
                    <th style="width:5%" class="center" rowspan="3">优惠券</th>
                    <th style="width:5%" class="center" rowspan="3">期末余额</th>
                    <th style="width:5%" class="center" rowspan="3">差异额</th>
                    <%--<th style="width:5%" class="center hidden-480" rowspan="2">处理总金额</th>--%>
                    <%--<th style="width:5%" class="center hidden-480" rowspan="2">处理后总差异额</th>--%>
                    <%--<th style="width:5%" class="center hidden-480" rowspan="2">累计差异额</th>--%>
                    <%--<th style="width:5%" class="center hidden-480" rowspan="2">处理状态</th>--%>
                </tr>
                <tr>
                    <th style="width:7%" class="center hidden-480" rowspan="2">普通充值</th>
                    <th style="width:7%" class="center hidden-480" rowspan="2">内部存入</th>
                    <th style="width:7%" class="center hidden-480">方式1(自取)</th>
                    <th style="width:7%" class="center hidden-480" colspan="2">方式2(代办)</th>
                    <th style="width:5%" class="center hidden-480">小计</th>
                    <th style="width:7%" class="center hidden-480" rowspan="2">普通提现</th>
                    <th style="width:7%" class="center hidden-480" rowspan="2">内部取出</th>
                    <th style="width:7%" class="center hidden-480" rowspan="2">出票</th>
                    <th style="width:7%" class="center hidden-480" rowspan="2">提现</th>
                </tr>
                <tr>
                    <th style="width:7%" class="center hidden-480"></th>
                    <th style="width:7%" class="center hidden-480">购彩</th>
                    <th style="width:7%" class="center hidden-480" >追期</th>
                    <th style="width:7%" class="center hidden-480"></th>
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
        <div>
            <span style="color: red">说明：</span><br>
            <span style="color: red">总差异额：期末余额-计算得出的期末余额</span><br>
            <span style="color: red">期末余额：T-1日的期末余额=T日期初余额</span><br>
            <span style="color: red">计算得出的期末余额：期初余额+充值金额-购彩总额+派奖总额-提现总额+退款总额+佣金+优惠券</span><br>
            <%--<span style="color: red">处理总金额=程序对账查出的差异额</span><br>--%>
            <%--<span style="color: red">处理后总差异额=总差异额-处理总金额</span><br>--%>
            <%--<span style="color: red">T日累计总差异额=截止T日所有差异和</span>--%>
        </div>

        <!-- #addorUpdateFrom -->
        <%@include file="../../account/form.jsp" %>
        <!-- #dialog-confirm -->
        <%@include file="../../common/dialog.jsp" %>
    </div>
</div>
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/finance/reconciliation/reconciliation_dealStatus.js"></script>
<script src="${jypath}/static/js/system/finance/reconciliation/platformFunds.js"></script>
</body>
</html>