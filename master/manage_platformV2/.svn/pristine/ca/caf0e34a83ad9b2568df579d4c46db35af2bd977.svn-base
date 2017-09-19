$(function () {
	getbaseList();
});

function download1() {
	var date = $("#beginTime").val();
	var date1 = $("#endTime").val();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/cashDifference/download',{fileName:date,fileName1:date1});

}

function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/cashDifference/findByPage',null,function(data){
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
				/* html+="<td  id='ids' class='center hidden-480'>"+JY.Object.notEmpty(l.id)+"</td>";*/
				/* html+="<td class='center hidden-480'>"+JY.Date.Format(l.date,'yyyy-MM-dd')+"</td>";*/
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.tallyDate)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.firstDiffPrize)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.onlineCashPrize)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.bigAmount)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.onlineSentPrize)+"</td>";
				html+="<td class='center '><a id='' onclick='cashAcountDifference(\""+l.tallyDate+"\")'>"+JY.Object.notEmpty(l.cashSendDiff)+"</a></td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.lastDiffPrize)+"</td>";
				html+="<td id='"+l.date+"' ondblclick='changeInput(\""+l.tallyDate+"\","+l.onlineCashPrize+","+l.onlineSentPrize+")' class='center hidden-480'>"+JY.Object.notEmpty(l.offlineCashPrize)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.totalPrize)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.totalDiffPrize)+"</td>";
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


function cashAcountDifference(date){
	var builDate = date;
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"兑奖差异明细",
		maxBtn:true,
		area:["80%","80%"],
		type:2,
		maskClose:true,
		content:"buillIndex?billDate="+builDate,
		button: [ {name: '取消'},
		],
	});
}



$('#resetData').bind('click', function() {
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"报表数据重新统计",
		maxBtn:true,
		area:["40%","80%"],
		type:2,
		maskClose:true,
		content:"resetData",
		button: [ {name: '取消'},
		],
	});
})

function changeInput(id,offline,onlineRedeemAmount) {
	var offlineRedeemAmount=offline!=null?offline:0;
	var html="<input class='date-picker' id='edit' onblur='edit(\""+id+"\","+onlineRedeemAmount+")' type='text' value='"+offlineRedeemAmount+"'/>"
//	 var html="<input  type='text' value=''/>"
//	 console.info(html);
	$("#"+id).html(html).find('#edit').focus();
//	 alert(id);
//	  $("#"+id).html(html);
}

function edit(id,onlineRedeemAmount){
//  data:'id='+id+'&offlineRedeemAmount='+offline+'&totalRedeemAmount='+totalRedeemAmount, 
	var offline=$("#edit").val()!='null'?$("#edit").val():0;
	$("#edit").parent().html(offline);
//	alert(id);
	$.ajax({
		url:jypath +'/backstage/cashDifference/countAumone',
		type:'POST',

		data:'id='+id+'&offlineRedeemAmount='+offline+'&onlineRedeemAmount='+onlineRedeemAmount,
		dataType:'json'
	})
}
