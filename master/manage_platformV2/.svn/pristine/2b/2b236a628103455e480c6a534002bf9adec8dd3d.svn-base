<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<%@include file="../../common/includeBaseSet.jsp" %>
	<%@include file="../../common/includeSystemSet.jsp" %>
	<%--<link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />--%>
	<%--<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>--%>
	<script src="${jypath}/static/js/system/finance/reconciliation/reconciliation_dealStatus.js"></script>
	<%--<link href="/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">--%>
</head>
<body>
<div class="page-content">
	<div class="row-fluid">
		<div class="col-xs-12">
			<%--<form id="form1" action="findByPageselect" method="get" class="form">--%>
				<%--<div class="row">--%>
					<%--&lt;%&ndash;<input id="id" name="id" value="1" type="hidden" >&ndash;%&gt;--%>
					<%--<div class="widget-main">--%>
						<%--<table border="0" cellspacing="0" cellpadding="0" class="table_100">--%>
							<%--<tr>--%>
								<%--<td width="5%" ></td>--%>
								<%--<td  >开始日期</td>--%>
								<%--<td  >--%>
									<%--<input name="beginTime" value="" class="date-picker" type="text" placeholder="开始日期" >--%>
								<%--</td>--%>
								<%--<td  >截止日期</td>--%>
								<%--<td  >--%>
									<%--<input name="endTime" value="" class="date-picker" type="text" placeholder="结束日期" >--%>
								<%--</td>--%>
								<%--<td >--%>
										<%--<span id="selectDealStatus">--%>
										<%--<label></label>：--%>
										<%--<select  data-placeholder="处理状态" name="dealResultStatus" class="chosen-select isSelect95"></select>--%>
										<%--</span>--%>
								<%--</td>--%>
								<%--<td>--%>
									<%--<input type="submit" class="button_1_a" value="搜索">--%>
									<%--<input type="reset" class="button_1_a" value="重填">--%>
								<%--</td>--%>
							<%--</tr>--%>
						<%--</table>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</form>--%>

			<table id="baseTable" class="table table-striped table-bordered table-hover" >
				<thead>
				<tr style="line-height: 24px;">
					<%--<th rowspan="2" class="center">--%>
						<%--<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>--%>
					<td rowspan="2" class="center">日期</td>
					<td rowspan="2" class="center">销售额</td>
					<td colspan="8" class="center">彩种销售额(直接出票)</td>
					<td rowspan="2" class="center">总额</td>
				</tr>
				<tr>
					<td class="center">11选5</td>
					<td class="center">大乐透</td>
					<td class="center">竞彩篮球</td>
					<td class="center">竞彩足球</td>
					<td class="center">排列3/5</td>
					<td class="center">七星彩</td>
					<td class="center">任选9</td>
					<td class="center">胜负彩</td>
				</tr>
				</thead>
				<tbody></tbody>
				<c:forEach items="${sumSales}" var="Sales">
				<tr>
					<%--<td class="center"><label> <input type="checkbox" name="ids" value="l.accountId+" class="ace" /> <span class="lbl"></span></label></td>--%>
					<td class="center">${Sales.mDate}</td>
					<td class="center">${Sales.mOneself}</td>
					<td class="center">${Sales.mSyxw}</td>
					<td class="center">${Sales.mDlt}</td>
					<td class="center">${Sales.mJclq}</td>
					<td class="center">${Sales.mJczq}</td>
					<td class="center">${Sales.mPlsw}</td>
					<td class="center">${Sales.mQxc}</td>
					<td class="center">${Sales.mRxjq}</td>
					<td class="center">${Sales.mSfc}</td>
					<td class="center">${Sales.mSum}</td>
				</tr>
				</c:forEach>
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
		</div>
	</div>
</div>
</body>
</html>