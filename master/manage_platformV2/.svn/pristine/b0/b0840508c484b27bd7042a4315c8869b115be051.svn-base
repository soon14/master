$(function () {
	//下拉框
	JY.Dict.setSelect("selectisValid","isValid",2,"全部");
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if (keycode==13) {
			 search();
		 } 
	});
	//新加
	$('#addBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		cleanForm();
		//loadRoleTree();
		JY.Model.edit("auDiv","新增",function(){
			 if(JY.Validate.form("auForm")){
				 var that =$(this);
				 JY.Ajax.doRequest("auForm",jypath +'/backstage/account/add',null,function(data){
				     that.dialog("close");      
				     JY.Model.info(data.resMsg,function(){search();});
				 });
			 }	
		});
	});
	//批量删除
	$('#delBatchBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		var chks =[];    
		$('#baseTable input[name="ids"]:checked').each(function(){    
			chks.push($(this).val());    
		});     
		if(chks.length==0) {
			JY.Model.info("您没有选择任何内容!"); 
		}else{
			JY.Model.confirm("确认要删除选中的数据吗?",function(){	
				JY.Ajax.doRequest(null,jypath +'/backstage/account/delBatch',{chks:chks.toString()},function(data){
					JY.Model.info(data.resMsg,function(){search();});
				});
			});		
		}		
	});
});
function search(){
	$("#searchBtn").trigger("click");
}

var preisShow=false;//窗口是否显示

function hideRole(){
	$("#roleContent").fadeOut("fast");
	preisShow=false;
}
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);	
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/account/findByPage',null,function(data){
		 $("#baseTable tbody").empty();
        	 var obj=data.obj;
        	 var list=obj.list;
        	 var results=list.results;
        	 var permitBtn=obj.permitBtn;
         	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.accountId+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center hidden-480'>"+(i+leng+1)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.loginName)+"</td>";
            		 html+="<td class='center hidden-480' >"+JY.Object.notEmpty(l.name)+"</td>";
            		 html+="<td class='center '>"+JY.Object.notEmpty(l.roleName)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.email)+"</td>";
            		 if(l.isValid==1) html+="<td class='center hidden-480'><span class='label label-sm label-success'>有效</span></td>";
            		 else             html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>无效</span></td>";
            		 html+=JY.Tags.setFunction(l.accountId,permitBtn);
            		 html+="</tr>";		 
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JY.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }	
 	 
    	 JY.Model.loadingClose();
	 });
}
function check(accountId){
	cleanForm();
	JY.Ajax.doRequest(null,jypath +'/backstage/account/find',{accountId:accountId},function(data){
        setForm(data,"1");
        JY.Model.check("auDiv");
    });
}
function del(accountId){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,jypath +'/backstage/account/del',{accountId:accountId},function(data){
			JY.Model.info(data.resMsg,function(){search();});
		});
	});
}
function edit(accountId){
	cleanForm();
	JY.Ajax.doRequest(null,jypath +'/backstage/account/find',{accountId:accountId},function(data){
		setForm(data,"2");
		JY.Model.edit("auDiv","修改",function(){
			if(JY.Validate.form("auForm")){
				var that =$(this);
				JY.Ajax.doRequest("auForm",jypath +'/backstage/account/update',null,function(data){
					that.dialog("close");
					JY.Model.info(data.resMsg,function(){search();});
				});
			}
		});
	});
}
function resetPwd(accountId){
	$("#resetPwdFrom input[name$='accountId']").val(accountId);//类型
	$("#resetPwdFrom input[name$='pwd']").val('');//类型
	$("#resetPwdDiv").removeClass('hide').dialog({resizable: false,modal:true,title:"<div class='widget-header'><h4 class='smaller'>密码重置</h4></div>",title_html: true,
		buttons: [
		  {  
			 html: "<i class='icon-ok bigger-110'></i>&nbsp;保存","class" : "btn btn-primary btn-xs",
			 click: function() {
				 if(JY.Validate.form("resetPwdFrom")){
					 var that =$(this);
					 JY.Ajax.doRequest("resetPwdFrom",jypath +'/backstage/account/resetPwd',null,function(data){
						 that.dialog("close");
			        	JY.Model.info(data.resMsg,function(){search();});		
					 });
				 }		
			}
		  },
		   {
			 html: "<i class='icon-remove bigger-110'></i>&nbsp;取消","class":"btn btn-xs",
			 click: function() {
				$(this).dialog("close");
			 }
		   }
		]
	});
}
function cleanForm(){
	JY.Tags.isValid("auForm","1");
	JY.Tags.cleanForm("auForm");
	$("#auForm input[name$='roleId']").val('');//上级资源
	$("#auForm input[name$='loginName']").prop("disabled",false);
    $.fn.zTree.getZTreeObj("treeDemo").checkAllNodes(false);
	hideRole();
}
function setForm(data,type) {

    if(type=="1"){
        $("#auDiv").ready(function(event) {
            $(this).find('input').attr("readonly","true");
        });
    }else{
        $("#auDiv").ready(function(event) {
            $(this).find('input').removeAttr("readOnly");
        });
    }

    var obj = data.obj;
    var l = obj.acount;
    var roles = obj.listRole;
    $("#auForm input[name$='accountId']").val(l.accountId);
    JY.Tags.isValid("auForm", (JY.Object.notNull(l.isValid) ? l.isValid : "0"));
    $("#auForm input[name$='loginName']").val(JY.Object.notEmpty(l.loginName));
    $("#auForm input[name$='loginName']").prop("disabled", true);
    $("#auForm input[name$='name']").val(JY.Object.notEmpty(l.name));
    $("#auForm input[name$='email']").val(JY.Object.notEmpty(l.email));
    $("#auForm textarea[name$='description']").val(JY.Object.notEmpty(l.description));//描述

    var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo")
    var nodes = zTreeObj.getCheckedNodes(false);
    var v="";
    var roleId="";
	for (var i = 0; i < roles.length; i++) {
		var s = roles[i];
		if (s.accountId != null) {
            roleId+="role" + s.roleId + ",";
			var roleIds = s.roleId.split(",");
			for (var j = 0; j < roleIds.length; j++) {
                for (var k = 0; k < nodes.length; k++) {
                    var node = nodes[k].id.substring(4,nodes[k].id.length);
                	if(roleIds[j]==node){
                        zTreeObj.expandNode(nodes[k], true); //展开选中的
                        zTreeObj.checkNode(nodes[k], true);
                        v += nodes[k].name + ",";
					}
                }
			}
		}
	}
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    if (roleId.length > 0 ) roleId = roleId.substring(0, roleId.length-1);
    $("#citySel").val(v);
    $("#auForm input[name$='roleId']").prop("value",roleId);
}
