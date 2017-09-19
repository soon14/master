$(function () {
    getList();
});

/**
 * 加载数据—默认是当前日期的数据
 * @param init
 */
function getList(init) {
    if (init == 1)$("#fundsForm .pageNum").val(1);
    JY.Model.loading();
    JY.Ajax.doRequest("fundsForm", jypath + '/backstage/reconciliation/toolsBalance/findByPage', null, function (data) {
        $("#fundsTable tbody").empty();
        var obj = data.obj;
        var list = obj.list;
        var results = list.results;

        var permitBtn = obj.permitBtn;
        var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
        var html = "";
        if (results != null && results.length > 0) {

            $("#showData").text(JY.Date.Format(results[0].date, 'yyyy-MM-dd')+"日数据" );
            $("#allQcTools").text(JY.Object.notEmpty(results[0].allQcTools));
            $("#allQcMoney").text(JY.Object.notEmpty(results[0].allQcMoney));
            $("#allAddTools").text(JY.Object.notEmpty(results[0].allAddTools));
            $("#allAddMoney").text(JY.Object.notEmpty(results[0].allAddMoney));
            $("#allConsumeTools").text(JY.Object.notEmpty(results[0].allConsumeTools));
            $("#allConsumeMoney").text(JY.Object.notEmpty(results[0].allConsumeMoney));
            $("#allQmTools").text(JY.Object.notEmpty(results[0].allQmTools));
            $("#allQmMoney").text(JY.Object.notEmpty(results[0].allQmMoney));
            var leng = (pageNum - 1) * pageSize;//计算序号
            for (var i = 0; i < results.length; i++) {
                var res = results[i];
                html += "<tr>";
                html += "<td class='center'><label><input type='checkbox' name='ids' value='" + res.id + "' class='ace' /> <span class='lbl'></span></label></td>";
                html += "<td class='center'>" + JY.Date.Format(res.date, 'yyyy-MM-dd') + "</td>";
                html += "<td class='center'>" + JY.Object.notEmpty(res.toolsId) + "</td>";
                html += "<td class='center hidden-480'  '>" + JY.Object.notEmpty(res.qcTools) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.qcMoney) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.addTools) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.addMoney) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.consumeDiamond) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.averageTools) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.consumeTools) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.consumeMoney) + "</td>";
                html += "<td class='center'>" + JY.Object.notEmpty(res.qmTools) + "</td>";
                html += "<td class='center'>" + JY.Object.notEmpty(res.qmMoney) + "</td>";
                html += "<td class='center'>" + JY.Object.notEmpty(res.qmAverageTools) + "</td>";
                html += "</tr>";
            }
            $("#fundsTable tbody").append(html);
            JY.Page.setPage("fundsForm", "pageing", pageSize, pageNum, totalRecord, "getList");
        } else {
            html += "<tr><td colspan='16' class='center'>没有相关数据</td></tr>";
            $("#fundsTable tbody").append(html);
            $("#pageing ul").empty();//清空分页
        }
        JY.Model.loadingClose();
    });
}
//处理差异
function dealData(obj) {
    var t=$(obj);
    var date=t.attr("dzdate");
    var type=t.attr("type");
    var index = jeBox.open({
        cell:"jbx",
        padding:"0",
        title:"差异处理",
        maxBtn:true,
        area:["800px","80%"],
        type:2,
        content:"dealPlatFormFundsDive?dzDate="+date+"&type="+type,
        masklock : true ,
        button: [ {name: '取消'},],
    })
}

//显示差异
function showData(obj) {
    var t=$(obj);
    var date=t.attr("dzdate");
    var type=t.attr("type");
    var index = jeBox.open({
        cell:"jbx",
        padding:"0",
        title:"差异处理",
        maxBtn:true,
        area:["500px","80%"],
        type:2,
        content:"showPlatFormFundsDive?dzDate="+date+"&type="+type,
        masklock : true ,
        button: [ {name: '取消'},],



    })
}




function search() {
    $("#searchBtn").trigger("click");
}


$('#resetData').bind('click', function() {
    var index = jeBox.open({
        cell:"jbx",
        padding:"0",
        title:"报表数据重新统计",
        maxBtn:true,
        area:["40%","80%"],
        type:2,
        maskClose:true,
        content:"resetData",
        button: [ {name: '取消'},
        ],
    });
})
function exportReport(url){
    window.location.href=url+"?date="+$('#beginTime').val();
}

