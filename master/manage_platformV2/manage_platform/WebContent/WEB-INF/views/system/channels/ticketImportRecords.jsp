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
							<div class="">
								<p class="" style="display:inline-block;">
									文件交易类型：
									<select name="payWay" id="payWayId" class="FormElement ui-widget-content ui-corner-all isSelect147">
										<option value="" style="display:none">请选择…</option>
										<option value="0">出票</option>
										<option value="1">微信</option>
										<option value="2">得仕通</option>
										<option value="3">银联</option>
										<option value="4">兑奖</option>
										<option value="5">体彩券</option>
									</select>
								</p>
								<p class="" style="display:inline-block;margin-left:20px;">
									<input type="file" style="width:170px;" id="fileId" name="uploadFile" /></p>
								<p class="" style="display:inline-block;margin-left:20px;"><input type="button" class="" value="导 入" onclick="importT()" /></p>
								<p></p>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
								<div class="row">
									<div class="widget-main">
										&nbsp;<input name="beginTime" value=""  class="date-picker"  placeholder="开始日期" >
										<input name="endTime" value=""  class="date-picker"  placeholder="结束日期" >
										<select name="transType" placeholder="交易类型">
											<option value="" style="display:none">请选择…</option>
											<option value="0">出票</option>
											<option value="1">微信</option>
											<option value="2">得仕通</option>
											<option value="3">银联</option>
											<option value="4">兑奖</option>
											<option value="5">体彩券</option>
										</select>
										<select name="isValid" placeholder="数据状态 ">
											<option value="1">在使用</option>
											<option value="0">已作废</option>
										</select>
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
							<th style="width:8%"  class="center hidden-480">日期</th>
							<th style="width:10%" class="center">文件名</th>
							<th style="width:10%" class="center">原始文件</th>
							<th style="width:8%" class="center hidden-480">交易类型</th>
							<th style="width:8%" class="center hidden-480">操作用户</th>
							<th style="width:8%"  class="center ">数据状态</th>
							<th style="width:12%"  class="center hidden-480">创建时间</th>
							<th style="width:6%" class="center hidden-480">清除该批次数据</th>
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
<script src="${jypath}/static/js/system/channels/ticketImportRecordsl.js"></script>
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
</body>
</html>