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
<%--<%@include file="../common/dialog.jsp" %>--%>
<div class="page-content">
	<div class="row-fluid">
		<div class="col-xs-12">
			<form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
				<div class="row">
					<div class="widget-main" id="baseHtml">
						<input name="beginTime" id="beginTime" value="" class="date-picker" type="text" placeholder="开始日期" >&nbsp~
						<input name="endTime" id="endTime" value="" class="date-picker" type="text" placeholder="结束日期" >
						功能名称:<select id="funcNo" name="funcNo" style="width:150px;"></select>
						&nbsp;&nbsp;<button id='menuBtn' class="btn btn-warning  btn-xs" title="查询" type="button" onclick="getbaseList()"><i class="icon-search bigger-110 icon-only"></i></button>
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
					<th style="width:10%" class="center">功能名称</th>
					<th style="width:25%" class="center">修改内容</th>
					<th style="width:25%" class="center">备注</th>
					<th style="width:10%" class="center">操作者</th>
					<th style="width:15%" class="center">修改时间</th>

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
			<%@include file="../common/dialog.jsp" %>
		</div>
	</div>
</div>
<script src="${jypath}/static/js/system/channels/channelOperationLog.js"></script>
</body>
</html>