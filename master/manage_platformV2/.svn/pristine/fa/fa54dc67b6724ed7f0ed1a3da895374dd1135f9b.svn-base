$(function () {

	$("input[name='beginTime']").datetimepicker({
		format:'yyyy-mm-dd',language:'zh-CN',weekStart:1,todayBtn:1,autoclose: 1,todayHighlight: 1,startView: 2,minView:2,initialDate:getDay(-1),endDate:getDay(-1),
    }).on('changeDate', function(ev){
		var beginTime=$("input[name='beginTime']").val();
		$("input[name='endTime']").datetimepicker('setStartDate',beginTime);
	});
	$("input[name='endTime']").datetimepicker({
		format: 'yyyy-mm-dd',language:'zh-CN',weekStart: 1,todayBtn:1,autoclose:1,todayHighlight:1,startView:2,minView:2,initialDate:getDay(-1),endDate:getDay(-1),
	}).on('changeDate', function(ev){
		var endTime=$("input[name='endTime']").val();
		$("input[name='beginTime']").datetimepicker('setEndDate',endTime);
	});
});
/**
 * 获取时间 昨天，今天，明天
 * @param -1，0,1
 * @returns {string}
 */
function getDay(day){
    var today = new Date();

    var targetday_milliseconds=today.getTime() + 1000*60*60*24*day;

    today.setTime(targetday_milliseconds); //注意，这行是关键代码

    var tYear = today.getFullYear();
    var tMonth = today.getMonth();
    var tDate = today.getDate();
    tMonth = doHandleMonth(tMonth + 1);
    tDate = doHandleMonth(tDate);
    return tYear+"-"+tMonth+"-"+tDate;
}
function doHandleMonth(month){
    var m = month;
    if(month.toString().length == 1){
        m = "0" + month;
    }
    return m;
}