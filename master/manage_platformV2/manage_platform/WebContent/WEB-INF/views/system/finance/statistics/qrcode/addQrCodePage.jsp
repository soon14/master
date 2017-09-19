<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<%@include file="../../../common/includeBaseSet.jsp" %>
	<%@include file="../../../common/includeSystemSet.jsp" %>
	<link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />
	<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
<div id="avDiv">
	<form id="avForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
			<tr class="FormData">
				<td class="CaptionTD">自取兑换券额：</td>
				<td class="DataTD">&nbsp;
					<input type="hidden" value="${date}" name="date">
					<input type="text"  required="required" id="selfUsedMoney" jyValidate="required"  maxlength="16" name="selfUsedMoney" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">委托兑换券额：</td>
				<td class="DataTD">&nbsp;
					<input type="text"  required="required" id="weiTuoUsedMoney" jyValidate="required"  maxlength="16" name="weiTuoUsedMoney" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			<tr class="FormData">
				<td ><input type="button" onclick="add()" class="btn btn-info" value="保存"></td>
				<td id="resMsg" style="color:red"></td>
			</tr>
			</tbody>
		</table>
	</form>
</div>
<script src="${jypath}/static/js/system/finance/reconciliation/addQrcodeValue.js"></script>
</body>
</html>