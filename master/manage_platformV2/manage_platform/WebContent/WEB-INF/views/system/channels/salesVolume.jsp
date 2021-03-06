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
					<div class="widget-main">
						<input name="beginTime" id="beginTime" value="${monthAgo}" class="date-picker" type="text" placeholder="开始日期" >
						~
						<input name="endTime" id="endTime" value="${dayAgo}"  class="date-picker"  type="text" placeholder="结束日期" >
						<input name="preMerchantName" value=""  type="text" placeholder="客户经理" >
						<%--<%@include file="../org/orgTrees.jsp" %>--%>
					</div>
					<div class="widget-main">
						<input name="merchantId" value=""  type="text" placeholder="渠道Id" >
						<input name="merchantName" value=""  type="text" placeholder="渠道商户名" >
						<button id='searchBtn' class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="getbaseList(1)"><i class="icon-search bigger-110 icon-only"></i></button>
					</div>
				</div>
				<input type='hidden' class='pageNum' name='pageNum' value='1'/>
				<input type='hidden' class='pageSize'  name='pageSize' value='10'/>
			</form>
			<table id="baseTable" class="table table-striped table-bordered table-hover" >
				<thead>
				<tr>
					<th style="width:10%"  class="center hidden-480">渠道Id</th>
					<th style="width:10%" class="center" id="name">渠道商户名</th>
					<th style="width:8%"  class="center ">销售总额</th>
					<%--<th style="width:5%" class="center hidden-480" id="count">销售等级</th>--%>
					<%--<th style="width:5%"  class="center hidden-480" id="percent">客户数</th>--%>
					<th style="width:15%" class="center hidden-480">佣金总额</th>
					<th style="width:15%" class="center hidden-480">客户经理</th>
					<th style="width:15%" class="center hidden-480">操作</th>
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

			<div id="totalId" class="">
				<table>
					<tr>
					
						<td style='font-weight:bold;color: #707070;'>
							总销量(单位：元)：
						</td>
						<td id="allConsume" style='color:#F00'>
						</td>
						<td style='font-weight:bold;color: #707070;'>
							总佣金(单位：元)：
						</td>
						<td id="allCommission" style='color:#F00'>
						</td>
					</tr>

				</table>
			</div>
			<br>
			<div >
				<table>
					<tr  style='font-weight:bold;color:#F00'>
						<td >统计说明：
						</td>
					</tr>
					<tr  style='font-weight:bold;color:#F00'>
						<td >
							1.销量统计默认为六个月的数据.包含个人购彩金额和归属客户购彩金额.
						</td>
					</tr>
					<tr  style='font-weight:bold;color:#F00'>
						<td >
							2.客户数统计为时间区间内的有效客户（即:注册并登录过系统的客户）总和.
						</td>
					</tr>
					<tr  style='font-weight:bold;color:#F00'>
						<td >
							3.时间段查询可自由选择不同时间段并查看销量和客户数.
						</td>
					</tr>
					<tr  style='font-weight:bold;color:#F00'>
						<td >
							4.统计的销量为代办成功的购彩金额.
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/channels/salesVolume.js"></script>
<script>
function getDataList(){
var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
nodes=treeObj.getCheckedNodes(true),
v="";
	var needId="";
for(var i=0;i<nodes.length;i++){
var a = nodes[i].isParent;
if(!a){
v+=nodes[i].name + ",";
	needId+=nodes[i].id + ",";
	$("#orgId").val(needId);
alert(nodes[i].id);
}
}
}
/* huangsf */
</script>
</body>
</html>