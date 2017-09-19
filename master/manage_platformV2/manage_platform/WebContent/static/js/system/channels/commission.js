$(function () {
    var value = $("#ids").val();
    if(value){
        $("#mergeData").remove();
    }
});
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
/** 佣金日报展示页*/
function getbaseList(init){
    if(init==1)$("#baseForm .pageNum").val(1);
    JY.Model.loading();
    JY.Ajax.doRequest("baseForm",jypath +'/backstage/commissions/findByPage'
        ,null,function(data){
            $("#baseTable tbody").empty();
            var obj=data.obj;
            var permitBtn=obj.permitBtn;
            var list=obj.list;
            var mLevel = obj.mLevel;
            if(mLevel==2){
                $("#resetData").remove();
                $("#mergeData").remove();
                $("#checkID").remove();
            }
            var results=list.results;
            var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
            var html="";
            if(results!=null&&results.length>0){
                for(var i = 0;i<results.length;i++){
                    var l=results[i];
                    html+="<tr>";
                    if('2'!=mLevel){
                        if('1'==l.isOver) {
                            html += "<td class='center'><label><input type='checkbox' name='ids' value='" + l.id + "' class='ace' /> <span class='lbl'></span></label></td>";
                        }else{
                            html += "<td class='center'> </td>";
                        }
                    }else{
                        html += "";
                    }
                    html+="<td class='center '>"+JY.Object.notEmpty(l.merchantName)+"</td>";
                    /*html+="<td class='center ' >"+JY.Object.notEmpty(l.commissionTypeName)+"</td>";*/
                    html+="<td class='center ' >"+JY.Object.notEmpty(l.dataTypeName)+"</td>";
                    html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.commission)+"</td>";
                    html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.sellMoney)+"</td>";
                    html+="<td class='center '>"+JY.Date.Defaults(l.beginDate)+"</td>";
                    html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.userName)+"</td>";
                    if('1'==l.isOver) {
                        html += "<td class='center hidden-480' style='color:#F00'>" + JY.Object.notEmpty(l.isOverStr) + "</td>";
                    }else{
                        html += "<td class='center hidden-480'>" + JY.Object.notEmpty(l.isOverStr) + "</td>";
                    }
                    /*// html+=JY.Tags.setFunction(l.id,permitBtn);*/
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

/**生成结算单*/
function account(){
    var ids = [];
    $("input[name='ids']:checked").each(function(){
        ids.push($(this).val());
    });
    console.log(ids);
    JY.Ajax.doRequest(null,jypath +'/backstage/commissions/account',{'ids[]':ids},function(data){
        JY.Model.info(data.resMsg,function(){search();});
        getbaseList(1);
    });
}

/**作废这条佣金记录*/
function deleteCommission(id){
    JY.Model.confirm("确认作废吗？",function(){
        JY.Ajax.doRequest(null,jypath +'/backstage/commissions/delete',{id:id});
        getbaseList(1);
    });

}

/**同步佣金日报数据*/
$('#resetData').bind('click', function() {

    // var index = jeBox.open({
    //     cell:"jbx",
    //     padding:"0",
    //     title:"选择重置数据的时间段",
    //     maxBtn:true,
    //     area:["40%","60%"],
    //     // width: 1000,
    //     // height: 200,
    //     type:2,
    //     maskClose:true,
    //     content:"resetData",
    //     button: [ {name: '取消'},
    //     ],
    // });
    JY.Model.confirm("确认重置吗？",function() {
        JY.Model.loading();
        JY.Ajax.doRequest("baseForm",jypath +'/backstage/commissions/synchro',null,function(data){
            var obj=data.obj;
            JY.Model.info(data.resMsg,function(){search();});
            window.parent.jeBox.close("jbx");
            document.getElementById('msg').innerHTML=obj.msg;
        });
        JY.Model.loadingClose();
        getbaseList(1);
    });
})