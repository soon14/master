$(function () {
	getbaseList();
});

function download1() {
	var date = $("#beginTime").val();
	JY.Ajax.doRequest("baseForm",jypath +'/statistics/lotteryWaySale/download',{fileName:date});
	
}

function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/statistics/lotteryWaySale/findOrderTicketByPage',null,function(data){
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
            		 html+="<td class='center hidden-550''>"+JY.Date.Format(l.date,'yyyy-MM-dd')+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.commonOrderMoney)+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.tranceOrderMoney)+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.activityOrderMoney)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.orderMoney)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.ticketMoney)+"</td>";
					 if(l.diff=="0") {
						 html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.diff) + "</td>";
					 }else{
						 html+="<td class='center' onclick='getDetail(this);' dzdate='"+JY.Date.Format(l.date, 'yyyy-MM-dd')+"'><span style='color: red'> "+JY.Object.notEmpty(l.diff)+"</span></td>";
					 }
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
	JY.Ajax.doRequest("baseForm",jypath +'/statistics/lotteryWaySale/manualOrderTicket',null,function(data){
		JY.Model.loadingClose();
		var obj=data.obj;
		JY.Model.info(obj.msg,function(){ getbaseList();});
	});
})


function getDetail(obj) {
	var t=$(obj);
	var date=t.attr("dzdate");
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"差异详情",
		maxBtn:true,
		area:["500px","80%"],
		type:2,
		content:"indexDiff?date="+date,
		masklock : true ,
		button: [ {name: '取消'},],
	})

}