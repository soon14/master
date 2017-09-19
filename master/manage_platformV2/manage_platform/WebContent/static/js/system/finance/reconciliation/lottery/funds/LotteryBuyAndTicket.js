/**
 * Created by Administrator on 2017/3/3.
 */
$(function () {
   getDataList()
});

/**
 * 加载数据—默认是当前日期的数据
 * @param init
 */
//处理差异

function getDataList(){
    JY.Model.loading();
    JY.Ajax.doRequest("baseForm", jypath + '/backstage/reconciliation/lottery/lotteryBuyAndTicket/findByPage', null, function (data) {
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
                html += "<td class='center hidden-480''>" + JY.Date.Format(l.date, 'yyyy-MM-dd') + "</td>";
                html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.commonSales) + "</td>";
                html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.merchantSales) + "</td>";
                html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.sales) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.commonTicketMoney) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.merchantTicketMoney) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.ticketMoney) + "</td>";

                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.commonRefundMoney) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.merchantRefundMoney) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.refundMoney) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.commonNotTicket) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.merchantNotTicket) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.notTicket) + "</td>";
                // html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.diff) + "</td>";
                if(l.diff=="0") {
                    html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.diff) + "</td>";
                }else{
                    html+="<td class='center' onclick='getDetail(this);' date='"+JY.Date.Format(l.date, 'yyyy-MM-dd')+"'><span style='color: red'> "+JY.Object.notEmpty(l.diff)+"</span></td>";
                }
                html += "</tr>";
            }
            $("#baseTable tbody").append(html);
            JY.Page.setPage("baseForm", "pageing", pageSize, pageNum, totalRecord, "getDataList");
        } else {
            html += "<tr><td colspan='20' class='center'>没有相关数据</td></tr>";
            $("#baseTable tbody").append(html);
            $("#pageing ul").empty();//清空分页
        }

        JY.Model.loadingClose();
    });
}
$('#resetData').bind('click', function() {
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
    JY.Ajax.doRequest("baseForm",jypath +'/backstage/reconciliation/lottery/lotteryBuyAndTicket/manual',null,function(data){
        JY.Model.loadingClose();
        var obj=data.obj;
        JY.Model.info(obj.msg,function(){ getDataList();});
    });
})

function getDetail(obj) {
    var t=$(obj);
    var date=t.attr("date");
    var index = jeBox.open({
        cell:"jbx",
        padding:"0",
        title:"差异详情",
        maxBtn:true,
        area:["500px","80%"],
        type:2,
        content:"indexDiff?date="+date,
        masklock : true ,
        button: [ {name: '取消'},],
    })

}





