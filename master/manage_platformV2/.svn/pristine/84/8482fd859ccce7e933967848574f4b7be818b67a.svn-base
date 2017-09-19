<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../common/includeBaseSet.jsp" %>
<%@include file="../common/includeSystemSet.jsp" %>
<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
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
										<input name="name" value=""  class=""  placeholder="佣金名称" >
										<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
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
							<th style="width:7%"  class="center hidden-480">佣金名称</th>
							<th style="width:8%" class="center">佣金规则类型</th>
							<th style="width:10%" class="center hidden-480">一级佣金范围(单位：万元)</th>
							<th style="width:8%"  class="center ">一级返佣百分比</th>
							<th style="width:10%" class="center hidden-480">二级佣金范围(单位：万元)</th>
							<th style="width:8%"  class="center ">二级返佣百分比</th>
							<th style="width:10%" class="center hidden-480">三级佣金范围(单位：万元)</th>
							<th style="width:8%"  class="center ">三级返佣百分比</th>
							<th style="width:5%" class="center hidden-480">创建者</th>
							<th style="width:12%"  class="center hidden-480">创建时间</th>
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
			<%@include file="editMerchant.jsp" %>
			<%@include file="baseCommissionForm.jsp" %>
			<%@include file="../common/dialog.jsp" %>
			</div>
		</div>
	</div>
<script src="${jypath}/static/js/system/channels/baseCommission.js"></script>
</body>
</html>