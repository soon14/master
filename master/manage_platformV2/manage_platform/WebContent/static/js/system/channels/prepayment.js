$(function () {
	//下拉框
	JY.Dict.setSelect("selectisValid","isValid",2,"全部");
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if (keycode==13) {
			 search();
		 } 
	});
	//批量删除
	$('#delBatchBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作
		e.preventDefault();
		var chks =[];
		$('#baseTable input[name="ids"]:checked').each(function(){
			chks.push($(this).val());
		});
		if(chks.length==0) {
			JY.Model.info("您没有选择任何内容!");
		}else{
			JY.Model.confirm("确认要删除选中的数据吗?",function(){
				JY.Ajax.doRequest(null,jypath +'/backstage/cash/delBatch',{chks:chks.toString()},function(data){
					JY.Model.info(data.resMsg,function(){search();});
				});
			});
		}
	});
});
function search(){
	$("#searchBtn").trigger("click");
}
function emptyRole(){
	$("#roleName").prop("value","");
	$("#auForm input[name$='roleId']").prop("value","0");
}
var preisShow=false;//窗口是否显示
function showRole() {
	if(preisShow){
		hideRole();
	}else{
		var obj = $("#roleName");
		var offpos = $("#roleName").position();
		$("#roleContent").css({left:offpos.left+"px",top:offpos.top+obj.heith+"px"}).slideDown("fast");	
		preisShow=true;
	}
}
function hideRole(){
	$("#roleContent").fadeOut("fast");
	preisShow=false;
}
/** 预存款展示页*/
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);	
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/backstage/prepayment/findByPage?date='+new Date().getTime()
		,null,function(data){
		 $("#baseTable tbody").empty();
        	 var obj=data.obj;
			var permitBtn=obj.permitBtn;
        	 var list=obj.list;
        	 var mLevel = obj.mLevel;
        	 var results=list.results;
         	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
					 html+="<tr>";
					 html+="<td class='center '>"+JY.Object.notEmpty(l.merchantName)+"</td>";
					 html+="<td class='center ' >"+JY.Object.notEmpty(l.payTypeName)+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.dataTypeName)+"</td>";
					 if(l.balance>l.warningMoney){
						 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.balance)+"</td>";
					 }else{
						 html+="<td class='center hidden-480' style='color:#F00'>"+JY.Object.notEmpty(l.balance)+"</td>";
					 }
                     html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.realBalance)+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.warningMoney)+"</td>";
					 html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.userName)+"</td>";
					 html+="<td class='center '>"+JY.Date.Default(l.changeTime)+"</td>";
					 //html+="<td class=\"center\">"+
						// "<div class=\"visible-md visible-lg hidden-sm hidden-xs btn-group\">"+
						// "<a href=\"#\" title=\"修改\" onclick=\"updatePrepayment(\'"+l.id+"\')\"class=\"aBtnNoTD\"><i class=\"icon-edit  bigger-140\"></i></a>"+
						// "<a href=\"#\" title=\"作废\" onclick=\"deletePrepayment(\'"+l.id+"\',\'"+l.merchantId+"\')\"class=\"aBtnNoTD\"><i class=\"icon-remove-sign  bigger-140\"></i></a>"+
						// "</div></td>";
					 // if(mLevel!=2){
                         if(l.dataType==1){
                             html+="<td class='center '></td>";
                         }else{
                             html+=JY.Tags.setFunction(l.id,permitBtn);
                         }
					 // }
                     if(mLevel==2) {
                         html+="";
                         // $("#twoIds").hide();
                         $("#twoId").hide();
                     }
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


/**审核拒绝想关预存款记录*/
function deletePrepayment(id){
	JY.Model.confirm("确认拒绝吗？",function(){
		JY.Ajax.doRequest(null,jypath +'/backstage/prepayment/deletePrepayment',{id:id,ppStatus:0});
		getbaseList(1);
	});

}
/**审核通过想关预存款记录*/
function auditPrepayment(id){
	JY.Model.confirm("确认通过吗？",function(){
		JY.Ajax.doRequest(null,jypath +'/backstage/prepayment/deletePrepayment',{id:id,ppStatus:2});
		getbaseList(1);
	});

}

/** 预存款修改*/
function updatePrepayment(id){
	JY.Ajax.doRequest(null,jypath +'/backstage/prepayment/find',{id:id},function(data) {
		setPrepayForm(data);
		JY.Model.edit("prepaymentId","预存款配置",function(){
			if(JY.Validate.form("prepaymentForm")){
				var that =$(this);
				JY.Ajax.doRequest("prepaymentForm",jypath +'/backstage/prepayment/update',null,function(data){
					that.dialog("close");
					JY.Model.info(data.resMsg,function(){search();});
					getbaseList(1);
				});
			}
		});
	})
}

function setPrepayForm(data){
	var l=data.obj.list;
	$("#prepaymentForm input[name$='id']").val(l.id);
	$("#prepaymentForm input[name$='merchantName']").val(l.merchantName);
	$("#prepaymentForm input[name$='merchantId']").val(l.merchantId);
	$("#prepaymentForm input[name$='payMoney']").val(l.payMoney);
	$("#prepaymentForm input[name$='payType']").val(l.payType);
	$("#prepaymentForm input[name$='balance']").val(l.balance);
	$("#prepaymentForm input[name$='warningMoney']").val(l.warningMoney);


}
/**同步预存款信息*/
function synchroPrepayment(){
    JY.Model.confirm("确认同步数据吗？",function(){
        JY.Model.loading();
        JY.Ajax.doRequest(null,jypath +'/backstage/prepayment/synchro',null,function(data){
            JY.Model.loadingClose();
            JY.Model.info(data.resMsg,function(){search();});
            getbaseList(1);
		});
    });

}
function findprepaymentExtend(id){
    var index = jeBox.open({
        cell:"jbx",
        padding:"0",
        title:"二级渠道预存款信息",
        maxBtn:true,
        area:["70%","70%"],
        type:2,
        maskClose:true,
        content:jypath +'/backstage/prepaymentExtend/index?id='+id,
        button: [ {name: '取消'}]
    });
}

/** 线下二级渠道预存款调配*/
function updatePrepaymentExtend(id) {
    JY.Ajax.doRequest(null, jypath + '/backstage/prepayment/find', {id: id}, function (data) {
        $("#prepaymentExtendId").css({
            "padding-bottom": "9em"
		});
        setPreExtendForm(data);
        findPreE();
        var title =$("#prepaymentExtendForm td[name$='merchantName']").text();
        JY.Model.edit("prepaymentExtendId", "预存款调配（"+title+"）", function () {
            var that = $(this);
            JY.Ajax.doRequest("prepaymentExtendForm", jypath + '/backstage/prepaymentExtend/update', null, function (data) {
                that.dialog("close");
                JY.Model.info(data.resMsg, function () {
                    search();
                });
                getbaseList(1);
            });
        });
    })
}

function setPreExtendForm(data){
    var l=data.obj.list;
    $("#prepaymentExtendForm input[name$='id']").val(l.id);
    $("#prepaymentExtendForm td[name$='merchantName']").text(l.merchantName);
    // $("#prepaymentExtendForm input[name$='merchantId']").val(l.merchantId);
    // $("#prepaymentExtendForm input[name$='payMoney']").val(l.payMoney);
    // $("#prepaymentExtendForm input[name$='payType']").val(l.payType);
    // $("#prepaymentExtendForm input[name$='balance']").val(l.balance);
    // $("#prepaymentExtendForm input[name$='warningMoney']").val(l.warningMoney);
    $("#prepaymentExtendForm td[name$='realBalance']").text(l.realBalance);
}

function findPreE() {
    var a =$("#preEId");
    var b =$("#preEIds");
    var id =$("#prepaymentExtendForm input[name$='id']").val();
    var realBalance=$("#prepaymentExtendForm td[name$='realBalance']").text();
    var option='<option value="parentMerchant_'+id+'">余额：'+realBalance+'元'+'</option>';
    JY.Ajax.doRequest("baseForm", jypath + '/backstage/prepaymentExtend/findPreE?id='+id, null, function (data) {
        var obj = data.obj;
        var list = obj.list;
        var results = list.results;
        if (results != null && results.length > 0) {
            for (var i = 0; i < results.length; i++) {
                var l = results[i];
                option+="<option value='"+l.id+"'>"+l.merchantName+"："+" "+l.balance+"元"+"</option>";
            }
        }
        a.html(option);
        b.html(option);
        a.chosen("destroy");
        b.chosen("destroy");
        b.chosen();
        a.chosen();
    })}




