<%@ page contentType="text/html;charset=UTF-8" %>
<head>
	<link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css"/>
	<link href="${jypath}/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">
	<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core.js"></script>
	<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.excheck.js"></script>
</head>
<div id="auDiv" class="hide">
	<form id="auForm" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
			<tr style="display:none">
				<td colspan="2" class="ui-state-error"><input type="hidden" name="accountId" ></td>
			</tr>
			<tr class="FormData" >
				<td class="CaptionTD">状态：</td>
				<td class="DataTD">&nbsp;
					<label class="inline isValidCheckbox">
						<input type="checkbox" checked="checked" sh-isValid="" role="checkbox" class="FormElement ace ace-switch ace-switch-5" />
						<span  class="lbl"></span>
						<!-- cb-isValid和Yes和No选择框配套使用-->
						<input type="hidden" hi-isValid=""  name="isValid" value="1" />
					</label>
				</td>
			</tr>
			<tr style="display:none">
				<td colspan="2" class="ui-state-error"><input type="hidden" name="id" ></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>登录名：</td>
				<td class="DataTD">&nbsp;
					<input type="text" jyValidate="required"  maxlength="16" name="loginName" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD"><font color="red">*</font>用户角色：</td>
				<td class="DataTD">&nbsp;
					<input id="citySel" type="text" jyValidate="required" readonly value="" style="width:163px;" onclick="showMenu();"/>
					<input type="hidden" name="roleId" value="0" >
					<input type="hidden" name="orgId" value="0" >
					<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
						<ul id="treeDemo" class="ztree" style="margin-top:0; width:220px; height: 300px;"></ul>
					</div>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">用户名：</td>
				<td class="DataTD">&nbsp;
					<input type="text" maxlength="32" name="name" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">电子邮箱：</td>
				<td class="DataTD">&nbsp;
					<input type="text" jyValidate="email"  maxlength="32" name="email" class="FormElement ui-widget-content ui-corner-all"></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">描述：</td>
				<td class="DataTD">&nbsp;
					<textarea rows="2" cols="10" maxlength="100" name="description" multiline="true" class="FormElement ui-widget-content ui-corner-all isSelect147"></textarea>
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>
<div id="resetPwdDiv" class="hide">
	<form id="resetPwdFrom" method="POST" onsubmit="return false;" >
		<table cellspacing="0" cellpadding="0" border="0" class="customTable">
			<tbody>
			<tr style="display:none">
				<td colspan="2" class="ui-state-error"><input type="hidden" name="accountId" ></td>
			</tr>
			<tr class="FormData">
				<td class="CaptionTD">重置密码：</td>
				<td class="DataTD">&nbsp;
					<input type="password" jyValidate="required"  maxlength="16" name="pwd" class="FormElement ui-widget-content ui-corner-all">
				</td>
			</tr>
			</tbody>
		</table>
	</form>
</div>
<SCRIPT type="text/javascript">
    var setting = {
        check: {
            enable: true,
            chkboxType: {"Y":"ps", "N":"ps"}
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick,
            onCheck: onCheck
        }
    };

    function beforeClick(treeId, treeNode) {
        return false;
//        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//        zTree.checkNode(treeNode, !treeNode.checked, null, true);

    }

    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getCheckedNodes(true),
            v = "";
        var roleId="";
        for (var i=0, l=nodes.length; i<l; i++) {
            var a = nodes[i].isParent;
            if(!a){
                v += nodes[i].name + ",";
                roleId +=  nodes[i].id + ",";
			}
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        if (roleId.length > 0 ) roleId = roleId.substring(0, roleId.length-1);
        $("#citySel").val(v);
        $("#auForm input[name$='roleId']").prop("value",roleId);
    }

    function showMenu() {
        var cityObj = $("#citySel");
        var cityOffset = $("#citySel").offset();
        $("#menuContent").css({left:189+"px", top:117 + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

    $(document).ready(function(){
        JY.Ajax.doRequest(null, jypath + '/backstage/org/position/getOrgRoleTree', null, function (data) {
            $.fn.zTree.init($("#treeDemo"), setting, data.obj);
        });
    });
</SCRIPT>