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
					<td class="center">日期</td>
					<td class="center">编号</td>
					<td class="center">差异金额</td>
					<td class="center">差异类型</td>
					<td class="center">具体原因</td>
					<td class="center">建议处理意见</td>
					<td class="center">处理结果</td>
					<td class="center">处理状态</td>
				</tr>
				</thead>
				<tbody></tbody>
				<c:forEach items="${salesDetail}" var="vo">
				<tr>
					<%--<td class="center">${vo.dId}</td>--%>
					<td class="center">${vo.dDate}</td>
					<td class="center">${vo.dNumber}</td>
					<td class="center">${vo.dDifferenceMoney}</td>
					<td class="center">${vo.dDifferenceType}</td>
					<td class="center">${vo.dCause}</td>
					<td class="center">${vo.dOpinion}</td>
					<td class="center">${vo.dResult}</td>
					<td class="center">${vo.dCondition}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>