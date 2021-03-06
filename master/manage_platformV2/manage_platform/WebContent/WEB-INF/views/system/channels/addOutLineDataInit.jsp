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
				<td class="CaptionTD" width=100>&nbsp;<font color="red">*</font>日期：</td>
				<td>
					<%--<input name="dateTime" id="dateTime"  class="date-picker"  type="text" placeholder="日期" >--%>
					<input name="endTime" id="endTime"   class="date-picker"  type="text" placeholder="请选择日期" >
				</td>
				<td class="CaptionTD" width=100><font color="red">*</font>机器号：</td>
				<td>
					<input type="text"  jyValidate="required" id="machineNum"  maxlength="16" name="machineNum" placeholder="请输入机器号">
				</td>
			</tr>
			<%--<tr class="FormData">--%>
				<%--<td class="CaptionTD" width=110>&nbsp;<font color="red">*</font>商户编号：</td>--%>
				<%--<td>--%>
					<%--<input type="text" maxlength="32"  name="merchantId" id="merchantId" placeholder="请输入商户编号">--%>
				<%--</td>--%>
				<%--<td class="CaptionTD" width=110>&nbsp;<font color="red">*</font>商户名称：</td>--%>
				<%--<td>--%>
					<%--&lt;%&ndash;<input type="text" maxlength="32"  name="merchantName" id="merchantName" placeholder="请输入商户名称">&ndash;%&gt;--%>
					<%--<select style="width:157px;" id="merchantName"  placeholder="请选择">--%>
					<%--</select>--%>
				<%--</td>--%>

			<%--</tr>--%>
			<tr class="FormData">
				<td class="CaptionTD" width=110>&nbsp;<font color="red">*</font>商户手机号：</td>
				<td>
					<input type="text" maxlength="32"  name="merchantPhone" id="merchantPhone" placeholder="请输入手机号">
				</td>
				<%--<td class="CaptionTD" width=110>&nbsp;<font color="red">*</font>商户名称：</td>--%>
				<%--<td>--%>
					<%--<select id="merchantName" name="merchantName" style="width:157px;">--%>
						<%--&lt;%&ndash;<option value="">&nbsp;</option>&ndash;%&gt;--%>
					<%--</select>--%>
				<%--</td>--%>
				<td class="CaptionTD" width=110>&nbsp;<font color="red">*</font>商户名称：</td>
				<td>
					<%--<input type="text" maxlength="32"  name="merchantName" id="merchantName" placeholder="请输入商户名称">--%>
					<select id="merchantName" name="merchantName"  placeholder="请选择">
					</select>
				</td>

			</tr>
			<tr class="FormData">
				<td class="CaptionTD" width=100>&nbsp;<font color="red">*</font>销售金额：</td>
				<td>
					<input type="text"   maxlength="32" value ="0" name="saleMoney" id="saleMoney" placeholder="请输入销售金额">
				</td>
				<td class="CaptionTD" width=100>&nbsp;<font color="red"></font>中奖金额：</td>
				<td>
					<input type="text"   maxlength="32" value ="0" name="winningMoney" id="winningMoney" placeholder="请输入中奖金额">
				</td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD" width=110>&nbsp;<font color="red"></font>抵用券金额：</td>
				<td>
					<input type="text" maxlength="32"  value ="0" name="offsetVoucherMoney" id="offsetVoucherMoney" placeholder="请输入抵用券金额">
				</td>
				<td class="CaptionTD" width=100>&nbsp;<font color="red"></font>返还券金额：</td>
				<td>
					<input type="text" maxlength="100" value ="0" name="returnVoucherMoney" id="returnVoucherMoney" placeholder="请输入返还券金额"/>
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
<script src="${jypath}/static/js/system/channels/addOutLineData.js"></script>
<script type="text/javascript">
	jQuery(function($) {
//		$("#merchantName").chosen();
	});
	$(function () {
		findMerchant();
		});
</script>
</body>
</html>