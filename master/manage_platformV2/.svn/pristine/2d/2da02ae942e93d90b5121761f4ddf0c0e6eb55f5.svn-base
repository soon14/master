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
				JY.Ajax.doRequest(null,jypath +'/backstage/cash/delBatch',{chks:chks.toString()},function(data){
					JY.Model.info(data.resMsg,function(){search();});
				});
			});
		}
	});
});
function search(){
	$("#searchBtn").trigger("click");
}
function emptyRole(){
	$("#roleName").prop("value","");
	$("#auForm input[name$='roleId']").prop("value","0");
}
var preisShow=false;//窗口是否显示
function showRole() {
	if(preisShow){
		hideRole();
	}else{
		var obj = $("#roleName");
		var offpos = $("#roleName").position();
		$("#roleContent").css({left:offpos.left+"px",top:offpos.top+obj.heith+"px"}).slideDown("fast");	
		preisShow=true;
	}
}
function hideRole(){
	$("#roleContent").fadeOut("fast");
	preisShow=false;
}
/** 预收款展示页*/
function getbaseList(init){
	if(init==1)$("#extendForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("extendForm",jypath +'/backstage/prepaymentExtend/findByPage?date='+new Date().getTime()
		,null,function(data){
		 $("#baseTable tbody").empty();
        	 var obj=data.obj;
			var permitBtn=obj.permitBtn;
        	 var list=obj.list;
        	 var mLevel = obj.mLevel;
        	 var results=list.results;
         	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
					 html+="<tr>";
					 html+="<td class='center '>"+JY.Object.notEmpty(l.merchantName)+"</td>";
					 // html+="<td class='center ' >"+JY.Object.notEmpty(l.payTypeName)+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.dataTypeName)+"</td>";
					 if(l.balance>l.warningMoney){
						 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.balance)+"</td>";
					 }else{
						 html+="<td class='center hidden-480' style='color:#F00'>"+JY.Object.notEmpty(l.balance)+"</td>";
					 }
                     // html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.realBalance)+"</td>";
					 html+="<td class='center hidden-480'>" ;
					 if(mLevel!=2){
                         html+="<a id='' href='javascript:setWarning("+l.id+");'>";
					 }
                     html+= JY.Object.notEmpty(l.warningMoney)+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.createUser)+"</td>";
					 html+="<td class='center '>"+JY.Date.Default(l.changeTime)+"</td>";
                     // html+=JY.Tags.setFunction(l.id,permitBtn);
				 }
        		 $("#baseTable tbody").append(html);
        		 JY.Page.setPage("extendForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }	
 	 
    	 JY.Model.loadingClose();
	 });
}

function setWarning(id){
    $("#setWarningFrom input[name$='id']").val(id);//类型
    $("#setWarningId").removeClass('hide').dialog({resizable: false,modal:true,title:"<div class='widget-header'><h4 class='smaller'>设置预警金额</h4></div>",title_html: true,
        buttons: [
            {
                html: "<i class='icon-ok bigger-110'></i>&nbsp;保存","class" : "btn btn-primary btn-xs",
                click: function() {
                    if(JY.Validate.form("setWarningFrom")){
                        var that =$(this);
                        JY.Ajax.doRequest("setWarningFrom",jypath +'/backstage/prepaymentExtend/setWarning',null,function(data){
                            that.dialog("close");
                            JY.Model.info(data.resMsg,function(){search();});
                        });
                        findprepaymentExtend(id);
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

// /** 线下二级渠道预收款修改*/
// function updatePrepaymentExtend(id){
// 	JY.Ajax.doRequest(null,jypath +'/backstage/prepaymentExtend/find',{id:id},function(data) {
//         setPreExtendForm(data);
//         findPreE();
// 		JY.Model.edit("prepaymentExtendId","二级预收款调配",function(){
// 				var that =$(this);
// 				JY.Ajax.doRequest("prepaymentExtendForm",jypath +'/backstage/prepaymentExtend/update',null,function(data){
// 					that.dialog("close");
// 					JY.Model.info(data.resMsg,function(){search();});
// 					getbaseList(1);
// 				});
// 		});
// 	})
// }
//
// function setPreExtendForm(data){
// 	var l=data.obj.list;
// 	$("#prepaymentExtendForm input[name$='id']").val(l.id);
// 	$("#prepaymentExtendForm td[name$='merchantName']").text(l.merchantName);
// 	$("#prepaymentExtendForm input[name$='merchantId']").val(l.merchantId);
// 	$("#prepaymentExtendForm input[name$='payMoney']").val(l.payMoney);
// 	$("#prepaymentExtendForm input[name$='payType']").val(l.payType);
// 	$("#prepaymentExtendForm input[name$='balance']").val(l.balance);
// 	$("#prepaymentExtendForm input[name$='warningMoney']").val(l.warningMoney);
//     $("#prepaymentExtendForm td[name$='realBalance']").text(l.realBalance+" 元");
// }
//
// function findPreE() {
//     var a =$("#preEId");
//     var id =$("#prepaymentExtendForm input[name$='id']").val();
//     var option='<option value="parentMerchant">上级可分配金额</option>';
//     JY.Ajax.doRequest("baseForm", jypath + '/backstage/prepaymentExtend/findPreE?id='+id, null, function (data) {
//         var obj = data.obj;
//         var list = obj.list;
//         var results = list.results;
//         if (results != null && results.length > 0) {
//             for (var i = 0; i < results.length; i++) {
//                 var l = results[i];
//                 option+="<option value='"+l.id+"'>"+l.merchantName+":"+l.balance+"</option>";
//             }
//         }
//         $("#preEId_chosen").remove();
//         a.html(option);
//         a.chosen("destroy");
// 		a.chosen();
//     });
// }



