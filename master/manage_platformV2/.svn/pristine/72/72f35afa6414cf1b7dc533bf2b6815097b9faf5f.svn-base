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
/** 返佣结算单展示页*/
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);	
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/payable/findByPage'
		,null,function(data){
		 $("#baseTable tbody").empty();
        	 var obj=data.obj;
			 var permitBtn=obj.permitBtn;
        	 var list=obj.list;
        	 var results=list.results;
             var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
					 html+="<tr>";
					 html+="<td class='center '>"+JY.Object.notEmpty(l.merchantName)+"</td>";
					 // html+="<td class='center ' >"+JY.Object.notEmpty(l.payWayStr)+"</td>";
                     html+="<td class='center '>"+JY.Date.Defaults(l.beginDate)+"——"+JY.Date.Defaults(l.endDate)+"</td>";
					 html+="<td class='center hidden-480'><a id='' href='javascript:commissionList(\""+l.ids+"\");'>"+JY.Object.notEmpty(l.money)+"</a></td>";
                     html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.userName)+"</td>";
                     html+="<td class='center '>"+JY.Date.Defaults(l.payDate)+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.statusName)+"</td>";
                     if(3==l.status){
                         html+="<td class=\"center\">"+
                             "<div class=\"visible-md visible-lg hidden-sm hidden-xs btn-group\">"+
                             "<a href=\"#\" title=\"付款成功\" class=\"aBtnNoTD\"><i class=\"icon-ok color-dark-green bigger-140\"></i></a>"+
                             "</div></td>";
                     }else{
                         html+=JY.Tags.setFunction(l.id,permitBtn);
                     }
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

/**付款*/
function payment(id){
    JY.Model.confirm("确认付款吗？",function(){
        JY.Model.loading();
        JY.Ajax.doRequest(null,jypath +'/backstage/payable/payment',{id:id});
        JY.Model.loadingClose();
        getbaseList(1);
    });

}

/**查看原佣金明细*/
function commissionList(ids) {
    var index = jeBox.open({
        cell: "jbx",
        padding: "0",
        title: "佣金明细",
        maxBtn: true,
        area: ["80%", "80%"],
        type: 2,
        maskClose: true,
        content: "commissionList?ids=" +ids,
        button: [{name: '取消'},
        ]
    })
}




