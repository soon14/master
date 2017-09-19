/**
 * Created by Administrator on 2017/3/3.
 */
$(function () {
});
/**
 * 加载数据—默认是当前日期的数据
 * @param init
 */

function add(){
    JY.Ajax.doRequest("avForm",jypath +'/statistics/qrCodeSoldDailyReport/add',null,function(data){
        var res=data.res;
        var resMsg=data.resMsg;
        $("#resMsg").html(resMsg);
    });
}







