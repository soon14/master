$(function () {
	getbaseList();
});
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);
	JY.Model.loading();

	JY.Ajax.doRequest("baseForm",jypath +'/backstage/salesVolume/childMerchantList',null,function(data){
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
				html+="<td class='center hidden-480' >"+JY.Object.notEmpty(l.merchantId)+"</td>";
				html+="<td class='center hidden-480' >"+JY.Object.notEmpty(l.merchantName)+"</td>";
				html+="<td class='center '><a id='' href='javascript:childMerchantAndSignList("+l.merchantId+");'>"+JY.Object.notEmpty(l.sales)+"</a></td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.commission)+"</td>";
				html+="<td class='center '>"+JY.Object.notEmpty(l.name)+"</td>";
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
function childMerchantAndSignList(merchantId){
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"订单销售明细信息",
		maxBtn:true,
		area:["80%","80%"],
		type:2,
		maskClose:true,
		content:"childMerchantAndSignList?merchantId="+merchantId+"&bTime="+beginTime+"&eTime="+endTime,
		button: [ {name: '取消'},
		]
	});
}
function salesUserList(merchantId){
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"渠道销售明细信息",
		maxBtn:true,
		area:["80%","90%"],
		type:2,
		maskClose:true,
		content:"salesUserList?merchantId="+merchantId+"&bTime="+beginTime+"&eTime="+endTime,
		button: [ {name: '取消'},
		]
	});
}

function findchildMerchant(merchantId){
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"客户明细表",
		maxBtn:true,
		area:["80%","90%"],
		type:2,
		maskClose:true,
		content:"findchildMerchantInit?merchantId="+merchantId,
		button: [ {name: '取消'}]
	});
}


