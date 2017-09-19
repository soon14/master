$(function () {
    getList();
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
function getList(init) {
    if (init == 1)$("#fundsForm .pageNum").val(1);
    JY.Model.loading();
    JY.Ajax.doRequest("fundsForm", jypath + '/backstage/reconciliation/findFundsListByPage', null, function (data) {
        $("#fundsTable tbody").empty();
        var obj = data.obj;
        var list = obj.list;
        var results = list.results;
        var permitBtn = obj.permitBtn;
        var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
        var html = "";
        if (results != null && results.length > 0) {
            var leng = (pageNum - 1) * pageSize;//计算序号
            for (var i = 0; i < results.length; i++) {
                var res = results[i];
                html += "<tr>";
                html += "<td class='center'><label><input type='checkbox' name='ids' value='" + res.id + "' class='ace' /> <span class='lbl'></span></label></td>";
                html += "<td class='center'>" + JY.Date.Format(res.dzDate, 'yyyy-MM-dd') + "</td>";
                // html += "<td class='bluecenter' onclick='getQcyeDetail(this);' dzdate='"+JY.Date.Format(res.dzDate, 'yyyy-MM-dd')+"'>" + JY.Object.notEmpty(res.qcye) + "</td>";
                html += "<td class='bluecenter'  '>" + JY.Object.notEmpty(res.qcye) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.recharge) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.internalDeposit) + "</td>";
                // html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalRecharge) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalBuyPick) + "</td>";
                // html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalBuyEntrust) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalBuyCommon) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalBuyTrance) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalBuy) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalPrizeAmt) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.withdraw) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.internalExtraction) + "</td>";
                // html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalWithdraw) + "</td>";
                // html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalRefund) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalRefundTicketFail) + "</td>";
                html += "<td class='center hidden-480'>" + JY.Object.notEmpty(res.totalRefundWithDraw) + "</td>";
                html += "<td class='center'>" + JY.Object.notEmpty(res.commission) + "</td>";
                html += "<td class='center'>" + JY.Object.notEmpty(res.coupon) + "</td>";
                html += "<td class='center'>" + JY.Object.notEmpty(res.qmye) + "</td>";
                if (res.totalDive != 0)
                    html += "<td class='redcenter' onclick='showData(this);' dzdate='"+JY.Date.Format(res.dzDate, 'yyyy-MM-dd')+"' type='"+JY.Object.notEmpty(res.type)+"'>" + JY.Object.notEmpty(res.totalDive) + "</span></td>";
                else
                    html += "<td class='greencenter'><span data-color='#00ff00'> " + JY.Object.notEmpty(res.totalDive) + "</span></td>";
                // html += "<td class='center'>" + JY.Object.notEmpty(res.dealAmt) + "</td>";
                // if (res.diveAfterDeal != 0)
                //     html += "<td class='redcenter'>" + JY.Object.notEmpty(res.diveAfterDeal) + "</td>";
                // else
                //     html += "<td class='greencenter'>" + JY.Object.notEmpty(res.diveAfterDeal) + "</td>";
                // if (res.totalSumDive != 0)
                //     html += "<td class='redcenter'>" + JY.Object.notEmpty(res.totalSumDive) + "</td>";
                // else
                //     html += "<td class='greencenter'>" + JY.Object.notEmpty(res.totalSumDive) + "</td>";
                // if (res.dealStatus == 0) html += "<td class='center hidden-480' onclick='dealData(this);' dzdate='"+JY.Date.Format(res.dzDate, 'yyyy-MM-dd')+"' type='"+JY.Object.notEmpty(res.type)+"'><span class='label label-sm arrowed-in'>未处理</span></td>";
                // else if (res.dealStatus == 1) html += "<td class='center hidden-480'><span class='label label-sm arrowed-in'>处理中</span></td>";
                // else if (res.dealStatus == 2) html += "<td class='center hidden-480' onclick='showData(this);' dzdate='"+JY.Date.Format(res.dzDate, 'yyyy-MM-dd')+"' type='"+JY.Object.notEmpty(res.type)+"'><span class='label label-sm label-success'>已处理</span></td>";
                // else if (res.dealStatus == 3) html += "<td class='center hidden-480'><span class='label label-sm label-success'>不用处理</span></td>";
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

/**
 * 查看期初余额明细
 */
 function getQcyeDetail(obj) {
    alert("12323");
    var t=$(obj);
    var qcye=t.text();
    var date=t.attr("dzdate");
    var index = jeBox.open({
        cell:"jbx",
        padding:"0",
        title:"用户资金期初余额明细",
        maxBtn:true,
        area:["800px","80%"],
        type:2,
        content:"findQcyeDetail?dzDate="+date+"&qcye="+qcye,
        masklock : true ,
        button: [ {name: '取消'},],
        //nofun: function(index){ return false; },
        success:function(cell){
            //alert(cell.find(".jeBox-content").height())
        }
    })
}



function search() {
    $("#searchBtn").trigger("click");
}
function loadRoleTree() {
    JY.Ajax.doRequest(null, jypath + '/backstage/account/roleTree', null, function (data) {
        $.fn.zTree.init($("#roleTree"), {
            view: {dblClickExpand: false, selectedMulti: false, nameIsHTML: true},
            data: {simpleData: {enable: true}},
            callback: {onClick: clickRole}
        }, data.obj);
    });
}
function emptyRole() {
    $("#roleName").prop("value", "");
    $("#auForm input[name$='roleId']").prop("value", "0");
}
var preisShow = false;//窗口是否显示
function showRole() {
    if (preisShow) {
        hideRole();
    } else {
        var obj = $("#roleName");
        var offpos = $("#roleName").position();
        $("#roleContent").css({left: offpos.left + "px", top: offpos.top + obj.heith + "px"}).slideDown("fast");
        preisShow = true;
    }
}
function clickRole(e, treeId, treeNode) {
    var check = (treeNode && !treeNode.isParent);
    if (check) {
        var zTree = $.fn.zTree.getZTreeObj("roleTree"),
            nodes = zTree.getSelectedNodes(), v = "", n = "", o = "", p = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            v += nodes[i].name + ",";//获取name值
            n += nodes[i].id + ",";//获取id值
            o += nodes[i].other + ",";//获取自定义值
            var pathNodes = nodes[i].getPath();
            for (var y = 0; y < pathNodes.length; y++) {
                p += pathNodes[y].name + "/";//获取path/name值
            }
        }
        if (v.length > 0) v = v.substring(0, v.length - 1);
        if (n.length > 0) n = n.substring(0, n.length - 1);
        if (o.length > 0) o = o.substring(0, o.length - 1);
        if (p.length > 0) p = p.substring(0, p.length - 1);
        if (o == 'r') {//判断是否角色
            $("#roleName").val(p);
            n = n.replace("role", "");
            $("#auForm input[name$='roleId']").prop("value", n);
            //因为单选选择后直接关闭，如果多选请另外写关闭方法
            hideRole();
        }
    }
}
function hideRole() {
    $("#roleContent").fadeOut("fast");
    preisShow = false;
}

function check(accountId) {
    cleanForm();
    JY.Ajax.doRequest(null, jypath + '/backstage/account/roleTree', null, function (data) {
        $.fn.zTree.init($("#roleTree"), {
            view: {dblClickExpand: false, selectedMulti: false, nameIsHTML: true},
            data: {simpleData: {enable: true}},
            callback: {onClick: clickRole}
        }, data.obj);
        JY.Ajax.doRequest(null, jypath + '/backstage/account/find', {accountId: accountId}, function (data) {
            setForm(data);
            JY.Model.check("auDiv");
        });
    });
}
function del(accountId) {
    JY.Model.confirm("确认删除吗？", function () {
        JY.Ajax.doRequest(null, jypath + '/backstage/account/del', {accountId: accountId}, function (data) {
            JY.Model.info(data.resMsg, function () {
                search();
            });
        });
    });
}
function edit(accountId) {
    cleanForm();
    JY.Ajax.doRequest(null, jypath + '/backstage/account/roleTree', null, function (data) {
        $.fn.zTree.init($("#roleTree"), {
            view: {dblClickExpand: false, selectedMulti: false, nameIsHTML: true},
            data: {simpleData: {enable: true}},
            callback: {onClick: clickRole}
        }, data.obj);
        JY.Ajax.doRequest(null, jypath + '/backstage/account/find', {accountId: accountId}, function (data) {
            setForm(data);
            JY.Model.edit("auDiv", "修改", function () {
                if (JY.Validate.form("auForm")) {
                    var that = $(this);
                    JY.Ajax.doRequest("auForm", jypath + '/backstage/account/update', null, function (data) {
                        that.dialog("close");
                        JY.Model.info(data.resMsg, function () {
                            search();
                        });
                    });
                }
            });
        });
    });
}
function resetPwd(accountId) {
    $("#resetPwdFrom input[name$='accountId']").val(accountId);//类型
    $("#resetPwdFrom input[name$='pwd']").val('');//类型
    $("#resetPwdDiv").removeClass('hide').dialog({
        resizable: false,
        modal: true,
        title: "<div class='widget-header'><h4 class='smaller'>密码重置</h4></div>",
        title_html: true,
        buttons: [
            {
                html: "<i class='icon-ok bigger-110'></i>&nbsp;保存", "class": "btn btn-primary btn-xs",
                click: function () {
                    if (JY.Validate.form("resetPwdFrom")) {
                        var that = $(this);
                        JY.Ajax.doRequest("resetPwdFrom", jypath + '/backstage/account/resetPwd', null, function (data) {
                            that.dialog("close");
                            JY.Model.info(data.resMsg, function () {
                                search();
                            });
                        });
                    }
                }
            },
            {
                html: "<i class='icon-remove bigger-110'></i>&nbsp;取消", "class": "btn btn-xs",
                click: function () {
                    $(this).dialog("close");
                }
            }
        ]
    });
}
function cleanForm() {
    JY.Tags.isValid("auForm", "1");
    JY.Tags.cleanForm("auForm");
    $("#auForm input[name$='roleId']").val('0');//上级资源
    $("#auForm input[name$='loginName']").prop("disabled", false);
    hideRole();
}
function setForm(data) {
    var l = data.obj;
    $("#auForm input[name$='accountId']").val(l.accountId);
    JY.Tags.isValid("auForm", (JY.Object.notNull(l.isValid) ? l.isValid : "0"));
    $("#auForm input[name$='loginName']").val(JY.Object.notEmpty(l.loginName));
    $("#auForm input[name$='loginName']").prop("disabled", true);
    $("#auForm input[name$='name']").val(JY.Object.notEmpty(l.name));
    $("#auForm input[name$='email']").val(JY.Object.notEmpty(l.email));
    $("#auForm textarea[name$='description']").val(JY.Object.notEmpty(l.description));//描述
    var treeObj = $.fn.zTree.getZTreeObj("roleTree");
    var nodes = treeObj.getNodesByParam("id", "role" + l.roleId);
    if (nodes.length > 0) {
        treeObj.selectNode(nodes[0]);
        clickRole(null, null, nodes[0]);
    }
}

/*$('#resetData').bind('click', function() {
    var index = jeBox.open({
        cell:"jbx",
        padding:"0",
        title:"报表数据重新统计",
        maxBtn:true,
        area:["40%","80%"],
        type:2,
        maskClose:true,
        content:"platResetData",
        button: [ {name: '取消'},
        ],
    });
})*/


function exportReport(url){
    window.location.href=url+"?date="+$('#beginTime').val()+"&type=1";
}


function exportFundsReport(url){
    window.location.href=url+"?date="+$('#beginTime').val()+"&type=3";
}


function exportCMCCReport(url){
    window.location.href=url+"?date="+$('#beginTime').val()+"&type=5";
}

