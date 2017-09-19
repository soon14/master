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
	if (init == 1)$("#baseForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm", jypath + '/backstage/ticketDifference/childDiffByBatchNo',{VStart:VStart}, function (data) {
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
       		    html+="<td id = 'builDates+"+i+"+ class='center hidden-550'>"+JY.Date.Format(l.usedDate,'yyyy-MM-dd')+"</td>";
				html += "<td class='center hidden-480''>" + JY.Object.notEmpty(l.vno) + "</td>";
				var vs="";
				if(l.vstatus=="1"){
					vs="已使用";
				}else if(l.vstatus=="2"){
					vs="未使用";
				}else if(l.vstatus=="3"){
					vs="已过期";
				}
				var s="";
				if(l.status=="1"){
					s="已使用";
				}else if(l.status=="2"){
					s="未使用";
				}else if(l.status=="3"){
					s="已过期";
				}
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(vs) + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(s) + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.vmoney) + "</td>";
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.money) + "</td>";
				var dMoney=l.vmoney-l.money;
       		 	dMoney=dMoney.toFixed(2);
				html += "<td class='center hidden-480' >" + JY.Object.notEmpty(dMoney) + "</td>";
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
