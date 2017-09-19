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
});



	function getbaseList(init) {
		if (init == 1)$("#baseForm .pageNum").val(1);
		JY.Model.loading();
		JY.Ajax.doRequest("baseForm", jypath + '/backstage/salesVolume/findSaleList', null, function (data) {
			$("#baseTable tbody").empty();
			var obj = data.obj;
			var list = obj.list;
			var results = list.results;
			var permitBtn = obj.permitBtn;
			var sumSales=obj.sumSales;
			var userName=obj.userName;
			if(sumSales==null){
				sumSales=0;
			}
			$("#sumSales").text(sumSales);
			var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
			var html = "";
			if (results != null && results.length > 0) {
				var leng = (pageNum - 1) * pageSize;//计算序号
				for (var i = 0; i < results.length; i++) {
					var l = results[i];
					html += "<tr>";
					html += "<td class='center hidden-480''>" +JY.Object.notEmpty(l.transNo)  + "</td>";
					html += "<td class='center hidden-480''>" +JY.Object.notEmpty(l.lotteryName)  + "</td>";
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.schemeId) + "</td>";
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.money) + "</td>";
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.buyTime) + "</td>";
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
