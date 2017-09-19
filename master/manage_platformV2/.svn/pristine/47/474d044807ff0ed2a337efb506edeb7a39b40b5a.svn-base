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
	var billDate = $("#builDate").val();
//		var schemeExtendId = $("#schemeExtendId").val();
	if (init == 1)$("#baseForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm", jypath + '/backstage/cashDifference/buill',{billDate:billDate}, function (data) {
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
				html += "<td class='center hidden-480''>" + JY.Date.Format(l.normalDate,'yyyy-MM-dd') + "</td>";
				html += "<td class='center hidden-480''>" + JY.Date.Format(l.prizeTime,'yyyy-MM-dd') + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.ticketNo) + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.sendPrize) + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.ticketPrize) + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.diffMoney) + "</td>";
				/*html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.dfProcessStauts) + "</td>";*/
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.dfProcessInfo) + "</td>";
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize, pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();//清空分页
		}

		JY.Model.loadingClose();
	});
}
//单独增加用户方案编号的查询
function getbaseLists(init) {
	var schemeExtendId = $("#schemeExtendId").val();
	if (init == 2)$("#baseForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm", jypath + '/backstage/cashDifference/schemeExtendId',{schemeExtendId:schemeExtendId},function (data) {
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
				html += "<td class='center hidden-480''>" + JY.Date.Format(l.normalDate,'yyyy-MM-dd') + "</td>";
				html += "<td class='center hidden-480''>" + JY.Date.Format(l.prizeTime,'yyyy-MM-dd') + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.ticketNo) + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.sendPrize) + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.ticketPrize) + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.diffMoney) + "</td>";
				/*html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.dfProcessStauts) + "</td>";*/
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.dfProcessInfo) + "</td>";
				html += "</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm", "pageing", pageSize, pageNum, totalRecord, "getbaseList");
		} else {
			html += "<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();//清空分页
		}

		JY.Model.loadingClose();
	});
}
