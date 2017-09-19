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
		JY.Ajax.doRequest("baseForm", jypath + '/statistics/salesCommissionReport/findChildListByMerchantId', null, function (data) {
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
					html += "<td class='center hidden-480''>" + JY.Object.notEmpty(l.mCpUserId) + "</td>";
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.mName) + "</td>";
					html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.mParentMerchant) + "</td>";
					if(l.mType==1) html+="<td class='center hidden-480'><span class='label label-sm label-success'>个人</span></td>";
					else if(l.mType==2)   html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>企业</span></td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.mLevel) + "</td>";
					if(l.mStatus==0) html+="<td class='center hidden-480'><span class='label label-sm label-success'>未审核</span></td>";
					else if(l.mStatus==1)   html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>一审</span></td>";
					else if(l.mStatus==2) html+="<td class='center hidden-480'><span class='label label-sm label-success'>有效</span></td>";
					else  if(l.mStatus==3) html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>拒绝</span></td>";
					else  if(l.mStatus==4) html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>待定</span></td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.mContactMobile) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.mAddress) + "</td>";
					html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.mContactUser) + "</td>";
					// html+=JY.Tags.setFunction(l.accountId,permitBtn);
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
