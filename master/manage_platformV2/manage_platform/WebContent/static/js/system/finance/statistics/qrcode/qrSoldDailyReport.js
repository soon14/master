$(function () {
	//下拉框
	JY.Dict.setSelect("selectisValid", "isValid", 2, "全部");
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function (e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});
});



	function getbaseList(init) {
		if (init == 1)$("#baseForm .pageNum").val(1);
		JY.Model.loading();
		JY.Ajax.doRequest("baseForm", jypath + '/statistics/qrCodeSoldDailyReport/findByPage', null, function (data) {
			$("#baseTable tbody").empty();
			var obj = data.obj;
			var list = obj.list;
			var results = list.results;
			var permitBtn = obj.permitBtn;
			var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
			var html = "";
			if (results != null && results.length > 0) {
				var leng = (pageNum - 1) * pageSize;//计算序号
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td class='center hidden-480'  >" + JY.Object.notEmpty(l.batchId)
						+"<br/>"
						+"("+JY.Date.Format(l.batchStartTime,'yyyy-MM-dd mm:hh:ss')
						+"<br/>"
						+"~~"+JY.Date.Format(l.batchEndTime,'yyyy-MM-dd mm:hh:ss')+")"+ "</td>";
					html += "<td class='center hidden-480' >" +JY.Date.Format(l.date,'yyyy-MM-dd')+ "</td>";
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.startAllMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.selfSaleMoney) + "</td>";
					if(l.selfUsedMoney==0){
						html += "<td class='center hidden-480' style='color: red' onclick='addValue(this);' dzdate='"+JY.Date.Format(l.date, 'yyyy-MM-dd')+"'>" + JY.Object.notEmpty(l.selfUsedMoney) + "</td>";
					}else{
						html += "<td class='center hidden-480'  >" + JY.Object.notEmpty(l.selfUsedMoney) + "</td>";
					}
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.weiTuoSaleMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.weiTuoUsedMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.saleAllMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.usedAllMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.diff) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.startExpireMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.nowNewExpireMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.nowExpireMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.endExpireMoney) + "</td>";


					// html+=JY.Tags.setFunction(l.accountId,permitBtn);
					html += "</tr>";
				}
				$("#baseTable tbody").append(html);
				JY.Page.setPage("baseForm", "pageing", pageSize, pageNum, totalRecord, "getbaseList");
			} else {
				html += "<tr><td colspan='20' class='center'>没有相关数据</td></tr>";
				$("#baseTable tbody").append(html);
				$("#pageing ul").empty();//清空分页
			}

			JY.Model.loadingClose();
		});

	}
function addValue(obj) {
	var t=$(obj);
	var date=t.attr("dzdate");
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"添加兑换券金额",
		maxBtn:true,
		area:["800px","80%"],
		type:2,
		content:"addQrCodePage?date="+date,
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
	JY.Ajax.doRequest("baseForm",jypath +'/statistics/qrCodeSoldDailyReport/manual',null,function(data){
		JY.Model.loadingClose();
		var obj=data.obj;
		JY.Model.info(obj.msg,function(){ getbaseList();});
	});
})
function exportReport(url){
	window.location.href=url+"?date="+$('#beginTime').val();
}