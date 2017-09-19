<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../../common/includeBaseSet.jsp" %>
    <%@include file="../../common/includeSystemSet.jsp" %>
    <link href="${jypath}/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="page-content">
		<div class="row-fluid">
			<div class="col-xs-12">
				<form id="baseForm" class="form-inline" method="POST"
					onsubmit="return false;">
					<div class="row">
						<div class="widget-main">
							&nbsp;批次:<input name="beginTime" id="beginTime" value=""
								class="date-picker" type="text" placeholder="开始日期">
							~&nbsp;<input name="endTime" id="endTime" value=""
								class="date-picker" type="text" placeholder="结束日期">
							&nbsp;
							<button id='searchBtn' class="btn btn-warning  btn-xs" title="查询"
								type="button" onclick="getbaseList(1)">
								<i class="icon-search bigger-110 icon-only"></i>
							</button>
							<a id='excelReport' class="btn btn-warning  btn-xs" href="#"
								onclick="javascript:exportReport('${jypath}/backstage/ticketDifference/loadExcel')">导出报表</a>
						</div>
					</div>
					<input type='hidden' class='pageNum' name='pageNum' value='1' /> <input
						type='hidden' class='pageSize' name='pageSize' value='10' />
				</form>
				<table id="baseTable"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th style="width: 15%" class="center" rowspan="2">批次</th>
							<th style="width: 25%" class="center" colspan="2">已用总额</th>
							<th style="width: 25%" class="center" colspan="2">未用总额</th>
							<th style="width: 25%" class="center" colspan="2">过期总额</th>
							<th style="width: 10%" class="center" rowspan="2">差异</th>
						</tr>
						<tr>
							<th style="width: 7%" class="center hidden-480">投注</th>
							<th style="width: 7%" class="center hidden-480">体彩</th>
							<th style="width: 7%" class="center hidden-480">投注</th>
							<th style="width: 7%" class="center hidden-480">体彩</th>
							<th style="width: 7%" class="center hidden-480">投注</th>
							<th style="width: 7%" class="center hidden-480">体彩</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<div class="row">
					<div class="col-sm-4">
						<div class="dataTables_info customBtn">
							<c:forEach var="pbtn" items="${permitBtn}">
								<a href="#" title="${pbtn.name}" id="${pbtn.btnId}"
									class="lrspace3"><i class='${pbtn.icon} bigger-220'></i></a>
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

			<div id="totalId" class=""></div>
			<br>
			<div>
				<table>
					<tr style='font-weight: bold; color: #F00'>
						<td>统计说明：此页面为体彩对账差异报表,差异金额为体彩金额减投注金额,投注数据为业务系统数据.</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
	<script src="${jypath}/static/js/system/finance/reconciliation/ticketDifference.js"></script>
	<script type="text/javascript">
		function exportReport(url) {
			window.location.href = url + "?date=" + $('#beginTime').val();
		}
	</script>
</body>
</html>