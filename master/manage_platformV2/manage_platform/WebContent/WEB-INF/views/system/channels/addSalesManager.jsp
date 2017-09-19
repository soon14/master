<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
	<link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css"/>
	<link href="${jypath}/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">
	<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core.js"></script>
	<script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.excheck.js"></script>
</head>
<body>
<div id="addSalesM" class="hide" style="text-align:center">
	<form id="form1" method="post" class="form">
		<table class="customTable">
		<tbody>
			<tr class="FormData">
				<td><font color="red">*</font>姓&nbsp;&nbsp;&nbsp;&nbsp;名：
					<input type="text"  jyValidate="required" id="mName"  maxlength="16" name="mName">
				</td>
			</tr>
			<tr class="FormData">
				<td><font color="red">*</font>电话号：
					<input type="text" id="mMobile" jyValidate="required"   maxlength="32" name="mMobile">
				</td>
			</tr>
			<tr class="FormData">
				<td><font color="red">*</font>角&nbsp;&nbsp;&nbsp;&nbsp;色：
					<input id="citySel" type="text" jyValidate="required" readonly value="" style="width:163px;" onclick="showMenu();"/>
					<input type="hidden" name="roleId" value="0" >
					<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
						<ul id="treeDemo" class="ztree" style="margin-top:0; width:220px; height: 300px;"></ul>
					</div>
				</td>
			</tr>
			<tr class="FormData">
				<td>身份证：&nbsp;&nbsp;
					<input type="text" maxlength="32"  name="mIdCard">
				</td>
			</tr>
			<tr class="FormData">
				<td>地&nbsp;&nbsp;&nbsp;&nbsp;址：&nbsp;&nbsp;
					<input type="text" maxlength="100" name="mAddress"></input>
				</td>
			</tr>

		</tbody>
		</table>
	</form>
</div>
<script src="${jypath}/static/js/system/channels/salesManager.js"></script>
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
        $("#form1 input[name$='roleId']").prop("value",roleId);
    }

    function showMenu() {
        var cityObj = $("#citySel");
        var cityOffset = $("#citySel").offset();
        $("#menuContent").css({left:267+"px", top:120 + "px"}).slideDown("fast");
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
        JY.Ajax.doRequest(null,jypath +'/channels/salesManager/roleTree',null,function(data){
            $.fn.zTree.init($("#treeDemo"), setting, data.obj);
        });
    });
</SCRIPT>
</body>
</html>