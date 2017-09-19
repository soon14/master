<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<%@include file="../common/includeBaseSet.jsp" %>
	<%@include file="../common/includeSystemSet.jsp" %>
	<link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />
	<link href="/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${jypath}/static/plugins/webuploader/css/webuploader.css" />
	<script src="${jypath}/static/plugins/webuploader/js/webuploader.nolog.min.js"></script>
</head>
<body>
<div>
	<form id="form1" method="post" class="form">
		<table class="customTable">
		<tbody>
			<tr class="FormData">
				<input type="hidden" name="mId" value="${mId}">
				<input type="hidden" name="addExist" id="addExist" value="${addExist}">
				<td class="CaptionTD" width=100>&nbsp;<font color="red">*</font>缴纳金额：</td>
				<td>
					<input type="number"   maxlength="32"  name="deposit" id="deposit" value="${deposit}" placeholder="请输入缴纳押金金额">
				</td>
			</tr>

			<tr class="FormData" align="center">
				<td  bgcolor="#E0E0E0"></td><td bgcolor="#E0E0E0"></td><td bgcolor="#E0E0E0"></td><td bgcolor="#E0E0E0"></td>
				<td bgcolor="#E0E0E0">
					<input type="button" id="save" onclick="closeJeBox()" class="btn btn-info" value="保存">
				</td>
			</tr>
		</tbody>
		</table>
	</form>
	<%@include file="../common/dialog.jsp" %>
</div>
<script type="text/javascript" src="/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/channels/addDeposit.js"></script>
<script type="text/javascript">


</script>
</body>
</html>