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
    JY.Ajax.doRequest("baseForm", jypath + '/backstage/reconciliation/getRunningDiveList', null, function (data) {
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
                html += "<td class='center hidden-480''>" + JY.Object.notEmpty(l.dDate) + "</td>";
                html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.dNumber) + "</td>";
                html += "<td class='center hidden-480' >" + JY.Object.notEmpty(l.dDifferenceMoney) + "</td>";
                if(l.dDifferenceType=="1"){
                    html += "<td class='center hidden-480'>彩票系统有，第三方无</td>";
                }else if(l.dDifferenceType=="2"){
                    html += "<td class='center hidden-480'>彩票系统无，第三方有</td>";
                }else if(l.dDifferenceType=="3"){
                    html += "<td class='center hidden-480'>金额差异</td>";
                }else if(l.dDifferenceType=="4"){
                    html += "<td class='center hidden-480'>时间差异</td>";
                }
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.dCause) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.dOpinion) + "</td>";

                if(l.dResult=="0"){
                    html += "<td class='center hidden-480'>未处理</td>";
                }else if(l.dResult=="1"){
                    html += "<td class='center hidden-480'>处理中</td>";
                }else if(l.dResult=="2"){
                    html += "<td class='center hidden-480'>已处理</td>";
                }else if(l.dResult=="3"){
                    html += "<td class='center hidden-480'>不用处理</td>";
                }
                if(l.dCondition==0){
                    html += "<td class='center hidden-480'>未处理</td>";
                }else if(l.dCondition==1){
                    html += "<td class='center hidden-480'>处理中</td>";
                }else if(l.dCondition==2){
                    html += "<td class='center hidden-480'>已处理</td>";
                }else if(l.dCondition==3){
                    html += "<td class='center hidden-480'>不用处理</td>";
                }
                if(l.dCreator=="0"){
                    html += "<td class='center hidden-480'>系统处理</td>";
                }else{
                    html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.dCreator) + "</td>";
                }
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.dCreateTime) + "</td>";
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








