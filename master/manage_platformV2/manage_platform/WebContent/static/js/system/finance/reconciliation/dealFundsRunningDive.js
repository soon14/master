/**
 * Created by Administrator on 2017/3/3.
 */
$(function () {
    JY.Dict.setSelect("selectDealStatus","dealResultStatus","","已处理");
    /**
     * 增加回车事件


     $("#fundsForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if (keycode==13) {
			 search();
		 }
	});*/
    /**
     * 新加


     $('#addBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作
		e.preventDefault();
		cleanForm();
		loadRoleTree();
		JY.Model.edit("auDiv","新增",function(){
			 if(JY.Validate.form("auForm")){
				 var that =$(this);
				 JY.Ajax.doRequest("auForm",jypath +'/backstage/account/add',null,function(data){
				     that.dialog("close");
				     JY.Model.info(data.resMsg,function(){search();});
				 });
			 }
		});
	});*/
    /**
     * 批量删除

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
				JY.Ajax.doRequest(null,jypath +'/backstage/account/delBatch',{chks:chks.toString()},function(data){
					JY.Model.info(data.resMsg,function(){search();});
				});
			});
		}
	});*/
});

/**
 * 加载数据—默认是当前日期的数据
 * @param init
 */

function saveDive(){
    JY.Ajax.doRequest("avForm",jypath +'/backstage/reconciliation/dealFundsRunningDive',null,function(data){
        var res=data.res;
        var resMsg=data.resMsg;
        $("#resMsg").html(resMsg);
    });
}







