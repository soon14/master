$(function () {
	getbaseList();
});

function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/withDrawDifference/findByPage',null,function(data){
		 $("#baseTable tbody").empty();
        	 var obj=data.obj;
        	 var list=obj.list;
        	 var results=list.results;
        	 var permitBtn=obj.permitBtn;
         	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
        	 var html="";
	       	 var sumTmoney=obj.sumTmoney;
	    	 var sumMoney=obj.sumMoney;
    		 var sumTmoney = sumTmoney.toFixed(2);
    		 var sumMoney = sumMoney.toFixed(2);
    		 var sumCyMoney=sumTmoney-sumMoney;
    		 var sumCyMoney = sumCyMoney.toFixed(2);
	    	 $("#sumTmoney").text(sumTmoney);
	   		 $("#sumMoney").text(sumMoney);
	   		 $("#sumCyMoney").text(sumCyMoney);
	   		
    		 if(results!=null&&results.length>0){
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td id = 'builDates+"+i+"+ class='center hidden-550'>"+JY.Date.Format(l.tdate,'yyyy-MM-dd')+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.tflowNo)+"</td>";
            		 var s="";
            		 if(l.status=="1"){
            			 s="成功";
            		 }else if(l.status=="2"){
            			 s="失败";
            		 }else if(l.status=="3"){
            			 s="处理中";
            		 }else if(l.status=="4"){
            			 s="其它";
            		 }
            		 var vs="";
            		 if(l.tstatus=="1"){
            			 vs="成功";
            		 }else if(l.tstatus=="2"){
            			 vs="失败";
            		 }else if(l.tstatus=="3"){
            			 vs="处理中";
            		 }else if(l.tstatus=="4"){
            			 vs="其它";
            		 }
            		 var a =l.tmoney-l.money;
            		 var cy = parseFloat(a).toFixed(2);
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(vs)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(s)+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.tmoney)+"</td>";            		        		 
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.money)+"</td>";
            		 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(cy)+"</td>";
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
