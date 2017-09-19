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
	<link href="/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="page-content">
	<div class="row-fluid">
		<div class="col-xs-12">
			<form id="form1" action="salesSumDifferences" method="get" class="form">
				<div class="row">
					<%--<input id="id" name="id" value="1" type="hidden" >--%>
					<div class="widget-main">
						<table border="0" cellspacing="0" cellpadding="0" class="table_100">
							<tr>
								<td width="5%" ></td>
								<td  >开始日期</td>
								<td  >
									<input name="beginTime" value="" class="date-picker" type="text" placeholder="开始日期" >
								</td>
								<td  >截止日期</td>
								<td  >
									<input name="endTime" value="" class="date-picker" type="text" placeholder="结束日期" >
								</td>
								<td >
										<span id="selectDealStatus">
										<label></label>：
										<select  data-placeholder="处理状态" name="dealResultStatus" class="chosen-select isSelect95"></select>
										</span>
								</td>
								<td>
									<input type="submit" class="button_1_a" value="搜索">
									<input type="reset" class="button_1_a" value="重填">
								</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
			<%--<form action="listBetDelete.do" name="formName" method="post">--%>
				<%--<div>--%>
					<%--<input type="button" class="button_1_a" name="actionButton"  value="导出" onClick="do_action()" >--%>
				<%--</div>--%>
				<table id="baseTable" class="table table-striped table-bordered table-hover" >
					<thead>
					<tr style="line-height: 24px;">
						<th rowspan="2" class="center">
							<label><input type="checkbox" class="ace" ><span class="lbl"></span></label>
						<td rowspan="2" class="center">日期</td>
						<td colspan="2" class="center">投注系统销售总额</td>
						<td colspan="2" class="center">出票系统销售总额</td>
						<td rowspan="2" class="center">线下销量</td>
						<td rowspan="2" class="center">销售总额</td>
						<td colspan="2" class="center">差异总金额(投注系统-出票系统)</td>
						<td rowspan="2" class="center">处理状态</td>
					</tr>
					<tr>
						<td class="center">笔数</td>
						<td class="center">金额</td>
						<td class="center">笔数</td>
						<td class="center">金额</td>
						<td class="center">笔数</td>
						<td class="center">金额</td>
					</tr>
					</thead>
					<tbody></tbody>
					<c:forEach items="${list}" var="vo">
						<tr>
							<td class="center"><label> <input type="checkbox" name="ids" value="l.accountId+" class="ace" /> <span class="lbl"></span></label></td>
							<td class="center">${vo.betDate}</td>
							<td class="center">${vo.betBetCount}</td>
							<td class="showjeBoxbnt center"  data_date="${vo.betDate}" style="color:#F00" >${vo.betBetMoney}</td>
							<td class="center">${vo.betIssueCount}</td>
							<td class="center">${vo.betIssueMoney}</td>
							<td class="center">${vo.betOffline}</td>
							<td class="center">${vo.betSumMoney}</td>
							<td class="center">${vo.betDifferenceCount}</td>
							<c:if test="${vo.betDifferenceMoney==0}">
								<td class="center">${vo.betDifferenceMoney}</td>
							</c:if>
							<c:if test="${vo.betDifferenceMoney!=0}">
								<td class="jeBoxbet center" data_date="${vo.betDate}" style="color:#F00">${vo.betDifferenceMoney}</td>
							</c:if>
							<td class="center">${vo.dCondition}</td>
						</tr>
					</c:forEach>
				</table>
			<%--</form>--%>
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
<script type="text/javascript" src="/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/finance/reconciliation/salesSum.js"></script>
</body>
</html>