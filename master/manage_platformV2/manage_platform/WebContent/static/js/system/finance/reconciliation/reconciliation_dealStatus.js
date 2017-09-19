$(function () {
	//下拉框
	JY.Dict.setSelect("selectDealStatus","dealResultStatus",2,"--全部--");

});

 function reloadDate(from ,url) {
    var beginTime=$("input[name=beginTime]").val().substring(0,7).trim();
    var endTime=$("input[name=endTime]").val().substring(0,7).trim();
    if(beginTime==""||endTime==""){
        jeBox.msg("请输入重置时间，不可跨月重置");
        return;
    }
    if(beginTime!=endTime){
        jeBox.msg("不可跨月重置");
        return;
    }
    JY.Model.loading();
    JY.Ajax.doRequest(from,jypath +url,null,function(data){
        JY.Model.loadingClose();
        var obj=data.obj;
        JY.Model.info(obj.msg,function(){ getList();});
    })
 }