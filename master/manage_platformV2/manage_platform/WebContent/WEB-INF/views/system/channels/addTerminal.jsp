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
	<link rel="stylesheet" href="${jypath}/assets/css/chosen.css" />
	<link rel="stylesheet" href="${jypath}/assets/css/ace.min.css" />
	<script src="${jypath}/static/plugins/webuploader/js/webuploader.nolog.min.js"></script>
</head>
<body>
<div>
	<form id="form1" method="post" class="form">
		<table class="customTable">
		<tbody>
			<tr class="FormData">
				<td class="CaptionTD" width=110>&nbsp;<font color="red">*</font>机器码：</td>
				<td>
					<input type="text" maxlength="32"  name="machineNum" id="machineNum" placeholder="请输入机器码">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD" width=110>&nbsp;<font color="red">*</font>商户名称：</td>
				<td>
					<%--<input type="text" maxlength="32"  name="merchantName" id="merchantName" placeholder="请输入商户名称">--%>
					<select id="merchantName" name="merchantName"  placeholder="请选择">
					</select>
				</td>
			</tr>

			<tr class="FormData" align="center">
				<td  bgcolor="#E0E0E0"></td><td bgcolor="#E0E0E0"></td><td bgcolor="#E0E0E0"></td><td bgcolor="#E0E0E0"></td>
				<td bgcolor="#E0E0E0">
					<input type="button" onclick="closeJeBox()" class="btn btn-info" value="保存">
				</td>
			</tr>
		</tbody>
		</table>
	</form>
	<%@include file="../common/dialog.jsp" %>
</div>
<script type="text/javascript" src="/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/channels/addTerminal.js"></script>
<script type="text/javascript">
	$(function () {
		findMerchant();
		});
</script>
</body>
</html>