<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../common/includeBaseSet.jsp" %>
<%@include file="../common/includeSystemSet.jsp" %>
<link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />
<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
<script src="${jypath}/static/js/system/finance/reconciliation/reconciliation_dealStatus.js"></script>
<link href="${jypath}/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="page-content">
		<div class="row-fluid">	
			<div class="col-xs-12">
				<table border="0" cellspacing="0" cellpadding="0" class="table_100">
					<tr>
						<td>
							<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
								<div class="row">
									<div class="widget-main">
										&nbsp;<input name="beginTime" value=""  class="date-picker"  placeholder="开始日期" >
										<input name="endTime" value=""  class="date-picker"  placeholder="结束日期" >
										<input name="merchantName" value=""  class=""  placeholder="渠道名称" >
										&nbsp;&nbsp;<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
									</div>
								</div>
								<input type='hidden' class='pageNum' name='pageNum' value='1'/>
								<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
							</form>
						</td>
					</tr>
				</table>
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead>
						<tr>
							<%--<th style="width:3%" class="center" rowspan="2">--%>
								<%--<label><input type="checkbox" class="ace"><span class="lbl"></span></label>--%>
							<%--</th>--%>
							<th style="width:8%"  class="center hidden-480">渠道</th>
							<%--<th style="width:6%" class="center">支付类型</th>--%>
							<th style="width:12%" class="center">结算周期</th>
							<th style="width:6%" class="center hidden-480">付款金额(单位：元)</th>
							<th style="width:8%"  class="center ">操作人</th>
							<th style="width:8%" class="center hidden-480">结算时间</th>
							<th style="width:6%"  class="center ">支付状态</th>
							<th style="width:6%" class="center hidden-480">操作</th>
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
			    <%@include file="../common/dialog.jsp" %>
			</div>
		</div>
	</div>
<script type="text/javascript" src="${jypath}/static/js/system/channels/payable.js"></script>
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
</body>
</html>