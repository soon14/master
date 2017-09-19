$(function () {
	getList();
});

/**
 * 加载数据—默认是当前日期的数据
 * @param init
 */
function getList(init){
	if(init==1)$("#fundsForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("fundsForm",jypath +'/backstage/reconciliation/findFundsRunningListByPage',null,function(data){
		$("#fundsTable tbody").empty();
		var obj=data.obj;
		var list=obj.list;
		var results=list.results;
		var permitBtn=obj.permitBtn;
		var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
		var html="";
		if(results!=null&&results.length>0){
			var leng=(pageNum-1)*pageSize;//计算序号
			for(var i = 0;i<results.length;i++){
				var res=results[i];
				html+="<tr>";
				html+="<td class='center'><label><input type='checkbox' name='ids' value='"+res.id+"' class='ace' /> <span class='lbl'></span></label></td>";
				html+="<td class='center'>"+JY.Date.Format(res.dzDate,'yyyy-MM-dd')+"</td>";
				html+="<td class='center'><span data-color='green'> "+JY.Object.notEmpty(res.insideRecharge)+"</span></td>";
				html+="<td class='center hidden-480' >"+JY.Object.notEmpty(res.totalUnionAmt)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(res.totalWechatAmt)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(res.totalDaysAmt)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(res.totalAmt)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(res.totalRunUnionAmt)+"</td>";
                html+="<td class='center hidden-480'>"+JY.Object.notEmpty(res.totalRunWechatAmt)+"</td>";
                html+="<td class='center hidden-480'>"+JY.Object.notEmpty(res.totalRunDaysAmt)+"</td>";
                html+="<td class='center hidden-480'>"+JY.Object.notEmpty(res.totalRunAmt)+"</td>";
                // html+="<td class='center'>"+JY.Object.notEmpty(res.qmye)+"</td>";
                // if(res.totalDive!=0)
					// html+="<td class='center' onclick='getDiveDetail(this);' dzdate='"+JY.Date.Format(res.dzDate, 'yyyy-MM-dd')+"'><span style='color: red'> "+JY.Object.notEmpty(res.totalDive)+"</span></td>";
                // else
                	html+="<td class='center'><span data-color='green'> "+JY.Object.notEmpty(res.totalDive)+"</span></td>";
				if(res.totalDive!=0)
					html+="<td class='center' onclick='getDiveDetail(this);' dzdate='"+JY.Date.Format(res.dzDate, 'yyyy-MM-dd')+"'><span style='color: red'> "+JY.Object.notEmpty(res.dealAmt)+"</span></td>";
				else
					html+="<td class='center'><span data-color='green'> "+JY.Object.notEmpty(res.dealAmt)+"</span></td>";
				// html+="<td class='center'>"+JY.Object.notEmpty(res.diveAfterDeal)+"</td>";
                // html+="<td class='center'>"+JY.Object.notEmpty(res.totalSumDive)+"</td>";
                // if(res.dealStatus==0) html+="<td class='center hidden-480' onclick='dealData(this);' dzdate='"+JY.Date.Format(res.dzDate, 'yyyy-MM-dd')+"'><span class='label label-sm arrowed-in'>未处理</span></td>";
                // else if(res.dealStatus==1) html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>处理中</span></td>";
                // else if(res.dealStatus==2) html+="<td class='center hidden-480'  onclick='getDiveDetail(this);' dzdate='"+JY.Date.Format(res.dzDate, 'yyyy-MM-dd')+"'><span class='label label-sm label-success'>已处理</span></td>";
                // else if(res.dealStatus==3) html+="<td class='center hidden-480'><span class='label label-sm label-success'>不用处理</span></td>";
				html+="</tr>";
			}
			$("#fundsTable tbody").append(html);
			JY.Page.setPage("fundsForm","pageing",pageSize,pageNum,totalRecord,"getList");
		}else{
			html+="<tr><td colspan='16' class='center'>没有相关数据</td></tr>";
			$("#fundsTable tbody").append(html);
			$("#pageing ul").empty();//清空分页
		}
		JY.Model.loadingClose();
	});
}


function getDiveDetail(obj) {
	var t=$(obj);
	var date=t.attr("dzdate");
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"差异详情",
		maxBtn:true,
		area:["500px","80%"],
		type:2,
		content:"showFundsRunningDive?dzDate="+date,
		masklock : true ,
		button: [ {name: '取消'},],
	})

}

//处理差异
function dealData(obj) {
	var t=$(obj);
	var date=t.attr("dzdate");
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"差异处理",
		maxBtn:true,
		area:["800px","80%"],
		type:2,
		content:"dealFundsRunningDivePage?dzDate="+date,
		masklock : true ,
		button: [ {name: '取消'},],
	})
}

$('#resetData').bind('click', function() {
	var beginTime=$("input[name=beginTime]").val().substring(0,7).trim();
	var endTime=$("input[name=endTime]").val().substring(0,7).trim();
	if(beginTime==""||endTime==""){
		jeBox.msg("请输入重置时间，不可跨月重置");
		return;
	}
	if(beginTime!=endTime){
		jeBox.msg("不可跨月重置");
		return;
	}
	JY.Model.loading();
	JY.Ajax.doRequest("fundsForm",jypath +'/backstage/reconciliation/manual',null,function(data){
		JY.Model.loadingClose();
		var obj=data.obj;
		JY.Model.info(obj.msg,function(){ getList();});
	});
})

function exportReport(url){
	window.location.href=url+"?date="+$('#beginTime').val()+"&type=2";
}

function exportRunningReport(url){
	window.location.href=url+"?date="+$('#beginTime').val()+"&type=4";
}