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
    JY.Ajax.doRequest("baseForm", jypath + '/backstage/reconciliation/lottery/lotteryBuyAndTicket/findDiffList', null, function (data) {
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
                html += "<td class='center hidden-480''>" + JY.Object.notEmpty(l.date) + "</td>";
                html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.schemeExtendId) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.diffMoney) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.diffType) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.diffReason) + "</td>";

                if(l.status=="0"){
                    html += "<td class='center hidden-480'>未处理</td>";
                }else if(l.status=="1"){
                    html += "<td class='center hidden-480'>处理中</td>";
                }else if(l.status=="2"){
                    html += "<td class='center hidden-480'>已处理</td>";
                }else if(l.status=="3"){
                    html += "<td class='center hidden-480'>不用处理</td>";
                }
               
                if(l.handler=="0"){
                    html += "<td class='center hidden-480'>系统处理</td>";
                }else{
                    html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.handler) + "</td>";
                }
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.handleTime) + "</td>";
                // html+=JY.Tags.setFunction(l.accountId,permitBtn);
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








