
$(function () {
	//下拉框
	JY.Dict.setSelect("selectType", "searchType", "", "--渠道用户--");
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function (e) {
		keycode = e.which || e.keyCode;
		if (keycode == 13) {
			search();
		}
	});

	$(".icon-plus-sign").on("click", function() {
		var index = jeBox.open({
			cell:"jbx",
			padding:"0",
			title:"添加数据",
			maxBtn:true,
			area:["700px","50%"],
			type:2,
			maskClose:true,
			content: 'addOutLineDataInit',
			success:function(data){
			}
		})
	});
});



function getbaseList(init) {
	if (init == 1)$("#baseForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm", jypath + '/channels/onlinedata/findOutLineData', null, function (data) {
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
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(i+1)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.dateTime)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.merchantId)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.merchantName)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.merchantPhone)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.machineNum)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.saleMoney)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.winningMoney)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.offsetVoucherMoney)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.returnVoucherMoney)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.oldPreDeposit)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.nowPreDeposit)+"</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.storageTime)+"</td>";
				// html+=JY.Tags.setFunction(l.accountId,permitBtn);
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize, pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='13' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();//清空分页
		}

		JY.Model.loadingClose();
	});
}
