$(function () {
	getbaseList();
});


function dataReset(){
	JY.Ajax.doRequest(null,jypath +'/backstage/ticketDifference/dataReset');
}
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/ticketDifference/findByPage',null,function(data){
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
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.vstart+"~"+l.vend)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.usedDCMoney)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.usedTSMoney)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.unUsedDCMoney)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.unUsedTSMoney)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.overDCMoney)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.overTSMoney)+"</td>";
            		 html+="<td class='center hidden-480'><a onclick='ticketDiff(\""+l.vstart+","+l.vend+"\")'>"+JY.Object.notEmpty(l.dNumber)+"</a></td>";
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

function ticketDiff(VStart){
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"券差异明细",
		maxBtn:true,
		area:["850px","70%"],
		type:2,
		maskClose:true,
		content: "childIndex?VStart="+VStart,
	});
}
