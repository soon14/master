<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<%@include file="../common/includeBaseSet.jsp" %>
	<%@include file="../common/includeSystemSet.jsp" %>
	<link href="/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${jypath}/static/plugins/webuploader/css/webuploader.css" />
	<script src="${jypath}/static/plugins/webuploader/js/webuploader.nolog.min.js"></script>
</head>
<body  onload="selectInformation()">
<div id="addMer" class="hide">
	<form id="form1" method="post" class="form-inline">
		<input type="hidden" id="mLevel" name="mLevel">
		<input type="hidden" id="parentMId" name="mParentMerchant">
		<input type="hidden" id="roleId" name="roleId">
		<input type="hidden" name="bcId" id="addbcId">
		<input type="hidden" name="bcIdLine" id="addbcIdLine">
		<table class="customTable">
		<tbody>
			<tr class="FormData">
				<td class="CaptionTD" width=100>&nbsp;<font color="red">*</font>渠道名称：</td>
				<td>
					<input type="text"  jyValidate="required" id="mName"  maxlength="16" name="mName">
				</td>
				<td class="CaptionTD" width=100>渠道类型：</td>
				<td>
					<select name="mType" style="width:163px;">
						<option value="1">个人</option>
						<option value="2">企业</option>
					</select>
				</td>
			</tr>	
			
			<tr class="FormData">
				<td class="CaptionTD" width=100>&nbsp;联系人：</td>
				<td>
					<input type="text"   maxlength="32" name="mContactUser">
				</td>
				<td class="CaptionTD" width=110>&nbsp;联系人身份证：</td>
				<td>
					<input type="text" maxlength="32"  name="mIdCard">
				</td>
			</tr>
			
			<tr class="FormData">
				<td class="CaptionTD" width=100>&nbsp;<font color="red">*</font>渠道电话号：</td>
				<td>
					<input type="text" id="mMobile" jyValidate="required"   maxlength="32" name="mMobile">
				</td>
				<td class="CaptionTD" width=100>渠道地址：</td>
				<td>
					<input type="text" maxlength="100" name="mAddress"></input>
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">&nbsp;渠道等级：</td>
				<td>
					<select id="merchantLevel" name="merchantLevel" style="width:163px;"></select>
				</td>
				<td class="CaptionTD">&nbsp;渠道反佣类型：</td>
				<td>
					<select name="mCommionType" style="width:163px;">
						<option value="1">日返</option>
						<option value="2">周返</option>
						<option value="3">月返</option>
						<option value="4">季度返</option>
					</select>
				</td>	
			</tr>
			<tr class="FormData">
				<td class="CaptionTD" width=100>&nbsp;<font color="red">*</font>渠道来源：</td>
				<td>
					<input id="online" type="checkbox" name="line" value="1"/><span>线上</span>
					<input id="offline" type="checkbox" name="line" value="2"/><span>线下</span>
				</td>
				<td id="parentMerchantTd" class="CaptionTD" width=100>&nbsp;上级渠道：</td>
				<td id="parentMerchantTd1">
					<select name="parentMerchant" id="parentMerchant" style="width:163px;"></select>
				</td>
			</tr>
			<tr class="FormData">
				<td id="onlineCommission" style="display:none;" class="CaptionTD" width=100>&nbsp;线上佣金：</td>
				<td id="onlineCommission1" style="display:none;">
					<select name="name" id="commissionName" style="width:163px;"></select>
				</td>
<%--				<td class="CaptionTD" width=100>渠道简介：</td>
				<td>
					<textarea style="width:163px;" rows="2" cols="10" maxlength="100" name="mIntroduce" multiline="true"></textarea>
				</td>--%>
				<td id="offlineCommission" style="display:none;" class="CaptionTD" width=100>&nbsp;线下佣金：</td>
				<td id="offlineCommission1" style="display:none;">
					<select name="nameLine" id="commissionNameLine" style="width:163px;"></select>
				</td>
		</tbody>
		</table>
	</form>
</div>
<script type="text/javascript" src="/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script src="${jypath}/static/js/system/channels/merchant.js"></script>
<script src="${jypath}/static/js/system/channels/addMLicense.js"></script>
</body>
</html>