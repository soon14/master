<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<%@include file="../common/includeBaseSet.jsp" %>
	<%@include file="../common/includeSystemSet.jsp" %>
	<link rel="stylesheet" href="${jypath}/static/plugins/webuploader/css/webuploader.css" />
	<script src="${jypath}/static/plugins/webuploader/js/webuploader.nolog.min.js"></script>
	<link rel="stylesheet" href="${jypath}/static/apidoc/jquery/css/colorbox.css" />
	<script src="${jypath}/static/js/ace/ace-extra.min.js"></script>
</head>
<body>
<div>
	<form id="mlForm" method="post" class="form">
			<input id="mId" name="mId" value="${id}" type="hidden" >
				<table width="600px">
					<tr>
						<td width="600px">
							<input type="hidden" id="savePath" readonly="readonly"  maxlength="32" name="mLicense">
							<div>
								<ul class="ace-thumbnails" id="pick">

								</ul>
							</div>
							<div id="fileList" class="uploader-list"></div>
						        <td width="100px" id ="captionID"class="CaptionTD">
							    <div id="filePicker">上传三证</div>
						        </td>
						</td>

					</tr>
					<tr>
						<td bgcolor="#E0E0E0"  style="text-align:left";></td>
						<td width="1px" bgcolor="#E0E0E0"  style="text-align:right";>
							<input type="button" onclick="saveMLicense()" class="btn" value="保存">
						</td>
						
					</tr>
				</table>
	</form>
	<%@include file="../common/dialog.jsp" %>
</div>
<script src="${jypath}/static/js/system/channels/addMLicense.js"></script>
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
</body>
</html>