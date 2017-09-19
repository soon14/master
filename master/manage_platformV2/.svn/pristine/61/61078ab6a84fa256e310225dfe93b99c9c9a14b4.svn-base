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
		JY.Ajax.doRequest("baseForm", jypath + '/statistics/qrCodeDetailDailyReport/findByPage', null, function (data) {
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
					html += "<td class='center hidden-480' >" +JY.Date.Format(l.date,'yyyy-MM-dd')+"</td>";
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.startTwoMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.startThreeMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.startTenMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.startTwentyMoney) + "</td>";
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.startFiftyMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.startHundredMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.startAllMoney) + "</td>";
					
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.soldTwoMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.soldThreeMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.soldTenMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.soldTwentyMoney) + "</td>";
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.soldFiftyMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.soldHundredMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.soldAllMoney) + "</td>";
					
					
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.endTwoMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.endThreeMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.endTenMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.endTwentyMoney) + "</td>";
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.endFiftyMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.endHundredMoney) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.endAllMoney) + "</td>";
					// html+=JY.Tags.setFunction(l.accountId,permitBtn);
					html += "</tr>";
				}
				$("#baseTable tbody").append(html);
				JY.Page.setPage("baseForm", "pageing", pageSize, pageNum, totalRecord, "getbaseList");
			} else {
				html += "<tr><td colspan='22' class='center'>没有相关数据</td></tr>";
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
	JY.Ajax.doRequest("baseForm",jypath +'/statistics/qrCodeDetailDailyReport/manual',null,function(data){
		JY.Model.loadingClose();
		var obj=data.obj;
		JY.Model.info(obj.msg,function(){ getbaseList();});
	});
})
function exportReport(url){
	window.location.href=url+"?date="+$('#beginTime').val();
}