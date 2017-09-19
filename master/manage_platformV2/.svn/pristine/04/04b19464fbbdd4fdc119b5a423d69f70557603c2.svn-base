$(function () {
	//下拉框
	JY.Dict.setSelect("selectisValid","isValid",2,"全部");
	getbaseList();
    loadFuncNo();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if (keycode==13) {
			 search();
		 } 
	});
});


function getbaseList(){
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/channels/channelOperationLog/findByPage',null,function(data){
		$("#baseTable tbody").empty();
		var obj=data.obj;
		var list=obj.list;
		var results=list.results;
		var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
		var permitBtn=obj.permitBtn;
		var html="";
		if(results!=null&&results.length>0){
			var leng=(pageNum-1)*pageSize;//计算序号
			for(var i = 0;i<results.length;i++){
				var l=results[i];
				html+="<tr>";
				html+="<td class='center'>"+JY.Object.notEmpty(l.funcName)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.funcContent)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.remarks)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.operationUser)+"</td>";
                html+="<td class='center'>"+JY.Date.Default(l.date)+"</td>";
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

function loadFuncNo() {
	var funcNo = document.getElementById("funcNo");
	JY.Ajax.doRequest(null, jypath + '/channels/channelOperationLog/findFuncNo', null, function (data) {
		var niw = "";
        var obj = data.obj;
		var list = obj.list;
        niw+='<option value="0">请选择</option>';
        if (list != null && list.length > 0) {
			for (var i = 0; i < list.length; i++) {
				var s = list[i];
				niw += '<option value=' + s.funcNo + '>' + s.funcName + '</option>';
			};
			funcNo.innerHTML = niw;
		}
	});
}

