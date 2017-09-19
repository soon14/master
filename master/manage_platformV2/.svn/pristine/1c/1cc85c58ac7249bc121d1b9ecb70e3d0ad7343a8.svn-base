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
                       	 <input name ="beginTime" id = "beginTime" placeholder="开始日期" value="" class="date-picker" type="text">
                       	 流水号:<input type="text" name="tFlowNo"  class="input-large">
                   &nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="查询" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
						 <a id='excelReport' class="btn btn-warning  btn-xs" href="#" onclick="javascript:exportReport('${jypath}/backstage/withDrawDifference/exportReport')">导出报表</a>
                    </div>
                </div>
				<input type='hidden' class='pageNum' name='pageNum' value='1'/>
				<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
            </form>
            <table id="baseTable" class="table table-striped table-bordered table-hover" >
                <thead>
                <tr>
                 	<th style="width:12.5%" class="center" >日期</th>
                 	<th style="width:12.5%" class="center" >流水号</th>
                 	<th style="width:12.5%" class="center" >第三方使用状态</th>
                 	<th style="width:12.5%" class="center" >数据中心使用状态</th>
                 	<th style="width:12.5%" class="center" >第三方金额</th>
                 	<th style="width:12.5%" class="center" >数据中心金额</th>
                 	<th style="width:12.5%" class="center" >差异金额</th>
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
				<%

				%>
				<div class="col-sm-8">
					<!--设置分页位置-->
					<div id="pageing" class="dataTables_paginate paging_bootstrap">
					<ul class="pagination"></ul>
				</div>
				</div>
			</div>
				
				<div id="totalId" class="">
				<table>
					<tr>
					
						<td style='font-weight:bold;color: #707070;'>
							第三方差异总额：
						</td>
						<td id="sumTmoney" style='color:#F00'>
						</td>
						<td style='font-weight:bold;color: #707070;'>
							&nbsp;&nbsp;数据中心差异总额：
						</td>
						<td id="sumMoney" style='color:#F00'>
						</td>
						<td style='font-weight:bold;color: #707070;'>
							&nbsp;&nbsp;差异总额：
						</td>
						<td id="sumCyMoney" style='color:#F00'>
						</td>
						<td style='font-weight:bold;color: #707070;'>
							&nbsp;&nbsp;(单位：元)
						</td>
					</tr>

				</table>
			</div>
			<br>
			<div >
				<table>
					<tr  style='font-weight:bold;color:#F00'>
						<td >统计说明：此页面为提现差异报表,差异金额为第三方金额减数据中心金额,数据中心数据为业务系统数据.
						</td>
					</tr>
				</table>
			</div>
			</div>
    </div>
</div>
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/finance/reconciliation/withDrawDifference.js"></script>
<script type="text/javascript">
function exportReport(url){
	window.location.href=url+"?date="+$('#beginTime').val();
}
</script>
</body>
</html>