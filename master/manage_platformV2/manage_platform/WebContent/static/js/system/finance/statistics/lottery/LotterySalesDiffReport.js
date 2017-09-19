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
    JY.Ajax.doRequest("baseForm", jypath + '/backstage/statistics/lottery/lotterySalesDiffReport/findByPage', null, function (data) {
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
                html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.number) + "</td>";
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
               
                if(l.isCheck=="0"){
                    html += "<td class='center hidden-480'>未审核</td>";
                }else  if(l.isCheck=="1"){
                    html += "<td class='center hidden-480'>审核中</td>";
                }else  if(l.isCheck=="2"){
                    html += "<td class='center hidden-480'>已审核</td>";
                }
                if(l.source=="1"){
                    html += "<td class='center hidden-480'>方案和票差异</td>";
                }else if(l.source=="2"){
                    html += "<td class='center hidden-480'>购彩和出票差异</td>";
                }else if(l.source=="3"){
                    html += "<td class='center hidden-480'>出票系统和彩票机差异</td>";
                }else if(l.source=="4"){
                    html += "<td class='center hidden-480'>销售汇总差异</td>";
                }
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
    JY.Ajax.doRequest("baseForm",jypath +'/backstage/statistics/lottery/lotterySalesDiffReport/manual',null,function(data){
        JY.Model.loadingClose();
        var obj=data.obj;
        JY.Model.info(obj.msg,function(){ getDataList();});
    });
})

function exportReport(url){
    window.location.href=url+"?date="+$('#beginTime').val();
}


