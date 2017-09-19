<%@ page contentType="text/html;charset=UTF-8" %>
<head>
	<link rel="stylesheet" href="${jypath}/static/plugins/webuploader/css/webuploader.css" />
	<script src="${jypath}/static/plugins/webuploader/js/webuploader.nolog.min.js"></script>
</head>
<div id="avDiv" class="hide">
	<form id="avForm" method="POST" onsubmit="return false;" >
		<table  class="customTable">
			<tbody>
			<tr style="display:none">
				<td colspan="2" class="ui-state-error">
					<input type="hidden" name="mId" >
					<input type="hidden" name="mCpUserId" >
					<input type="hidden" name="mStatus" >
					<input type="hidden" name="mParentMerchant" id="mParentMerchant">
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>姓名：</td>
				<td class="DataTD">&nbsp;
					<input type="text"   jyValidate="required"  maxlength="16" name="mName" class="FormElement ui-widget-content ui-corner-all"></td>
				<td class="CaptionTD"><font color="red">*</font>电话号：</td>
				<td class="DataTD">&nbsp;
					<input type="text" jyValidate="required"  maxlength="32" name="mMobile" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">身份证：</td>
				<td class="DataTD">&nbsp;
					<input type="text"     maxlength="32" name="mIdCard" class="FormElement ui-widget-content ui-corner-all"></td>
				<td class="CaptionTD">地址：</td>
				<td class="DataTD">&nbsp;
					<textarea style="width:163px;" rows="2"  cols="10" maxlength="100" name="mAddress" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">彩票账户：</td>
				<td class="DataTD">&nbsp;
					<input type="text"  readonly="readonly"   maxlength="16" name="mCpUserId" class="FormElement ui-widget-content ui-corner-all"></td>
				<td class="CaptionTD">系统用户登录名：</td>
				<td class="DataTD">&nbsp;
					<input type="text"   readonly="readonly"  maxlength="32" name="mAccountId" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">审核进度：</td>
				<td class="DataTD">&nbsp;
					<select name="mStatus" id="mStatus" style="width:163px;"></select>
				</td>
				<td class="CaptionTD">状态：</td>
				<td class="DataTD">&nbsp;
					<select name="merIsValid" id="merIsValid" style="width:163px;"></select>
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">备注：</td>
				<td class="DataTD">&nbsp;
					<textarea style="width:163px;" rows="3" cols="10" maxlength="100" name="mIntroduce" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
				</td>
				<td class="CaptionTD">渠道二维码：</td>
				<td class="DataTD">&nbsp;
					<div id="qrCodeDiv">
						<img class="mBarcode" id="mBarcode" name="mBarcode" src="" onload="if(this.width > 150 && this.height >150) this.width = 150 ,this.height =150 " >
					</div>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>