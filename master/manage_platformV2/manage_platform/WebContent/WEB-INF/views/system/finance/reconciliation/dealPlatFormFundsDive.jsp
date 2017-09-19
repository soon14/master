<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<%@include file="../../common/includeBaseSet.jsp" %>
	<%@include file="../../common/includeSystemSet.jsp" %>
	<link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />
	<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
<div id="avDiv">
	<form id="avForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
			<tr class="FormData">
				<td class="CaptionTD">差异类型：</td>
				<td class="DataTD">&nbsp;
					<input type="hidden" value="${dzDate}" name="dDate">
					<input type="hidden" value="${type}" name="type">
					<input type="text"  required="required" id="dDifferenceType" jyValidate="required"  maxlength="16" name="dDifferenceType" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">差异金额：</td>
				<td class="DataTD">&nbsp;
					<input type="text"  required="required" id="dDifferenceMoney" jyValidate="required"  maxlength="16" name="dDifferenceMoney" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">具体原因：</td>
				<td class="DataTD">&nbsp;
					<textarea rows="5" cols="25" maxlength="100" name="dCause" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">建议处理意见：</td>
				<td class="DataTD">&nbsp;
					<textarea rows="5" cols="25" maxlength="100" name="dOpinion" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">处理结果：</td>
				<td class="DataTD">&nbsp;
					<select  id ="result" name="dResult"  >
						<option  value = "0" >未处理</option >
						<option  value = "1" >处理中</option >
						<option  value = "2" selected = "selected">已处理</option >
						<option  value = "3" >不用处理</option >
					</select >
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">处理状态：</td>
				<td class="DataTD">&nbsp;
					<select  id ="sel" name="dCondition"  >
						<option  value = "0" >未处理</option >
						<option  value = "1" >处理中</option >
						<option  value = "2" selected = "selected">已处理</option >
						<option  value = "3" >不用处理</option >
					</select >
				</td>
			</tr>
			<tr class="FormData">
				<td ><input type="button" onclick="saveDive()" class="btn btn-info" value="保存"></td>
				<td id="resMsg" style="color:red"></td>
			</tr>
			</tbody>
		</table>
	</form>
</div>
<script src="${jypath}/static/js/system/finance/reconciliation/dealPlatFormFundsDive.js"></script>
</body>
</html>