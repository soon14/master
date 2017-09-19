<%@ page contentType="text/html;charset=UTF-8" %>
<head>
	<link rel="stylesheet" href="${jypath}/static/apidoc/jquery/css/colorbox.css" />
	<script src="${jypath}/static/js/ace/ace-extra.min.js"></script>
	<script src="${jypath}/static/plugins/webuploader/js/webuploader.nolog.min.js"></script>
</head>
<div id="avDiv" class="hide">
	<form id="avForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
			<tr class="FormData">
				<td class="CaptionTD">商户名称：</td>
				<td class="DataTD">&nbsp;
					<input type="text"  readonly="readonly"   maxlength="16" name="mName" class="FormElement ui-widget-content ui-corner-all"></td>
				<td class="CaptionTD">商户类型：</td>
				<td class="DataTD">&nbsp;
					<input type="text"  readonly="readonly"  maxlength="32" name="mType" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">商户等级：</td>
				<td class="DataTD">&nbsp;
					<input type="text"  readonly="readonly"  maxlength="32" name="mLevel" class="FormElement ui-widget-content ui-corner-all"></td>
				<td class="CaptionTD">商户手机号：</td>
				<td class="DataTD">&nbsp;
					<input type="text"  readonly="readonly"  maxlength="32" name="mMobile" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">联系人：</td>
				<td class="DataTD">&nbsp;
					<input type="text"   readonly="readonly"  maxlength="32" name="mContactUser" class="FormElement ui-widget-content ui-corner-all"></td>
				<td class="CaptionTD">上级商户：</td>
				<td class="DataTD">&nbsp;
					<input type="text"  readonly="readonly"   maxlength="32" name="mParentName" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">线上佣金：</td>
				<td class="DataTD">&nbsp;
					<input type="text"  readonly="readonly"   maxlength="32" name="commissionName" class="FormElement ui-widget-content ui-corner-all"></td>
				</td>
				<td class="CaptionTD">线下佣金：</td>
				<td class="DataTD">&nbsp;
					<input type="text"  readonly="readonly"   maxlength="32" name="commissionNameLine" class="FormElement ui-widget-content ui-corner-all"></td>
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">渠道反佣类型：</td>
				<td>&nbsp;
					<input type="text"  readonly="readonly"   maxlength="32" name="mCommionType" class="FormElement ui-widget-content ui-corner-all"></td>
				</td>
				<td class="CaptionTD">身份证：</td>
				<td class="DataTD">&nbsp;
					<input type="text"   readonly="readonly"  maxlength="32" name="mIdCard" class="FormElement ui-widget-content ui-corner-all"></td>

			</tr>
			<tr class="FormData">
				<td class="CaptionTD">商户地址：</td>
				<td class="DataTD">&nbsp;
					<textarea rows="1"  readonly="readonly" cols="10" maxlength="100" name="mAddress" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
				</td>
				<td class="CaptionTD">商户简介：</td>
				<td class="DataTD">&nbsp;
					<textarea rows="1"  readonly="readonly" cols="10" maxlength="100" name="mIntroduce" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
				</td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">三证：</td>
				<td class='center hidden-480'>
					<div class="row-fluid">
						<ul class="ace-thumbnails" id="pic">

						</ul>
					</div>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>
<script src="${jypath}/static/apidoc/JYUI/assets/js/jquery.colorbox-min.js"></script>
<script type="text/javascript">
    jQuery(function($) {
        var colorbox_params = {
            reposition:true,
            scalePhotos:true,
            scrolling:false,
            previous:'<i class="icon-arrow-left"></i>',
            next:'<i class="icon-arrow-right"></i>',
            close:'&times;',
            current:'{current} of {total}',
            maxWidth:'100%',
            maxHeight:'100%',
            onOpen:function(){
                document.body.style.overflow = 'hidden';
            },
            onClosed:function(){
                document.body.style.overflow = 'auto';
            },
            onComplete:function(){
                $.colorbox.resize();
            }
        };
        $('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
        $("#cboxLoadingGraphic").append("<i class='icon-spinner orange'></i>");//let's add a custom loading icon
    })
</script>