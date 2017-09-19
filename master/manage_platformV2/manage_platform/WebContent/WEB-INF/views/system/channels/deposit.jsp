<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<%@include file="../common/includeBaseSet.jsp" %>
	<%@include file="../common/includeSystemSet.jsp" %>
	<link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />
	<link href="${jypath}/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">
	<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>


</head>
<body>
<div class="page-content">
	<div class="row-fluid">
		<div class="col-xs-12">
			<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
				<div class="row">
					<div class="widget-main" id="baseHtml">
						<input name="mName" id="mName"   class="input-large"  type="text" placeholder="这里输入商户名称" >
						<input type="text" name="mMobile" id="mMobile" placeholder="这里输入手机号"   style="width:150px;" class="input-large">
						&nbsp;&nbsp;<button id='menuBtn' class="btn btn-warning  btn-xs" title="查询" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
						<ul></ul>
					</div>
				</div>
				<input type='hidden' class='findDepId' name='findDepId' value='1' />
				<input type='hidden' class='pageNum' name='pageNum' value='1'/>
				<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
			</form>
			<table id="baseTable" class="table table-striped table-bordered table-hover" >
				<thead>
				<tr>
					<th style="width:3%" class="center">序号</th>
					<th style="width:7%"  class="center hidden-480">商户编号</th>
					<th style="width:9%"  class="center hidden-480">商户名称</th>
					<th style="width:9%"  class="center hidden-480">手机号</th>
					<th style="width:7%" class="center hidden-480">押金金额</th>
					<th style="width:7%" class="center hidden-480">缴纳日期</th>
					<th style="width:7%" class="center hidden-480">操作</th>
				</tr>
				</thead>
				<tbody></tbody>
			</table>
			<%--1--%>
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
			<!-- #dialog-confirm -->
			<%@include file="../common/dialog.jsp" %>
		</div>
	</div>
</div>
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/channels/deposit.js"></script>
</body>
</html>