$(function () {
	getbaseList();
});

function download1() {
	var date = $("#beginTime").val();
	JY.Ajax.doRequest("baseForm",jypath +'/channels/develop/download',{fileName:date});
	
}

function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/channels/develop/findByPage',null,function(data){
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
            		 html+="<td class='center hidden-520''>"+JY.Date.Format(l.mCreateTime,'yyyy-MM-dd')+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.mName)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.mContactUser)+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.mMobile)+"</td>";            		        		 
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.developCustomerNumber)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.developCustomerToday)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.developCustomerNumber + l.developCustomerToday)+"</td>";
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
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"报表数据重新统计",
		maxBtn:true,
		area:["40%","80%"],
		type:2,
		maskClose:true,
		content:"resetData",
		button: [ {name: '取消'},
		],
	});
})
