<%@ page contentType="text/html;charset=UTF-8" %>
<%--新增方法--%>
<div id="prepaymentExtendId" class="hide">
	<form id="prepaymentExtendForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
			<tr class="FormData" hidden="true">
				<td class="CaptionTD">上级渠道可分配金额：</td>
				<td class="DataTD" name="realBalance" style="padding-left: 10px">
				</td>
			</tr>
			<tr class="FormData" hidden="true">
				<td class="CaptionTD">商户名称：</td>
				<td class="DataTD" name="merchantName" style="padding-left: 10px">&nbsp;
					<%--<input type="text" size="10" readonly="true"   maxlength="20" name="merchantName" class="FormElement ui-widget-content ui-corner-all isSelect147">--%>
				</td>
			</tr>
			<tr class="FormData">
				<td class="DataTD" width=75 style="padding-left:7px;">出资商户：</td>
				<td class="DataTD" width=125>
				<td class="DataTD" width=75 style="padding-left:7px;">入资商户：</td>
			</tr>
			<tr class="FormData">
				<%--<td class="CaptionTD" width=75>出资商户：</td>--%>
				<td width=150>&nbsp;
					<select style="width:157px;" id="preEId"  name="preEId" placeholder="请选择">
					</select>
				</td>
				<%--<td class="CaptionTD"><font color="red">*</font>分配金额：</td>--%>
				<td class="DataTD" width=217>&nbsp;
					<img src="<c:url value="/static/images/system/arrow2.png" />" width="33px" alt="">
					<input type="text" hidden="true" size="10" readonly="true"   maxlength="20" name="merchantId" class="FormElement ui-widget-content ui-corner-all isSelect147">
					<input type="text" hidden="true" size="10" readonly="true"   maxlength="20" name="id" class="FormElement ui-widget-content ui-corner-all isSelect147">
					<input maxlength="9" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
						   name="payMoney" type="text"  size="10"  class="FormElement ui-widget-content ui-corner-all isSelect145" placeholder="分配金额(单位：元)"><img src="<c:url value="/static/images/system/arrow1.png" />" width="25px" alt=""></td>
				<%--<td class="CaptionTD" width=75>入资商户：</td>--%>
				<td width=178>&nbsp;
					<select style="width:157px;" id="preEIds"  name="preEIds" placeholder="请选择">
					</select>
				</td>
			</tr>
			<tr class="FormData" hidden="true">
				<td class="CaptionTD">剩余金额：</td>
				<td class="DataTD">&nbsp;
					<input name="balance" readonly="readonly" type="text" size="10"   class="FormElement ui-widget-content ui-corner-all isSelect147">元
				</td>
			</tr>
			<tr class="FormData" hidden="true">
				<td class="CaptionTD">预警金额：</td>
				<td class="DataTD">&nbsp;
					<input  name="warningMoney" type="text" size="10" class="FormElement ui-widget-content ui-corner-all isSelect147">元</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>