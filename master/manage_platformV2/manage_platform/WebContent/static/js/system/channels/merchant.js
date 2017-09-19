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

    $(".icon-plus-sign").on("click", function() {
        cleanForm();
        document.getElementById("onlineCommission").style.display = "none";
        document.getElementById("onlineCommission1").style.display = "none";
        document.getElementById("offlineCommission").style.display = "none";
        document.getElementById("offlineCommission1").style.display = "none";
		JY.Model.edit("addMer","新增渠道",function(){
			if(JY.Validate.form("form1")){
				var that =$(this);
                JY.Model.loading();
				JY.Ajax.doRequest("form1",jypath +'/channels/add',null,function(data) {
                    if(data.resMsg=="1"){
                        that.dialog("close");
                        getbaseList();
                        JY.Model.info("添加成功!");
                    }else if(data.resMsg=="2"){
                        JY.Model.info("电话号码不合法!");
                    }else if(data.resMsg=="3"){
                        JY.Model.info("电话号已存在!");
                    }else if(data.resMsg=="4"){
                        JY.Model.info("请选择上级渠道!");
                    }else if(data.resMsg=="5"){
                        JY.Model.info("请选择渠道来源!");
                    }else if(data.resMsg=="6"){
                        JY.Model.info("参数不合法!");
                    }else if(data.resMsg=="7"){
                        JY.Model.info("该用户已经关联销售，不可创建!");
                    }else if(data.resMsg=="8"){
                        JY.Model.info("彩票接口异常!");
                    }
				});
                JY.Model.loadingClose();
			};
		});
    });

	//批量删除
	$('#delMerchant').on('click', function(e) {
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
				JY.Ajax.doRequest(null,jypath +'/channels/delMerchant',{chks:chks.toString()},function(data){
					getbaseList();
					JY.Model.info(data.resMsg,function(){search();});
				});
			});
		}
	});
});

function search(){
	$("#searchBtn").trigger("click");
}

function hideRole(){
	$("#roleContent").fadeOut("fast");
	preisShow=false;
}

function getbaseList(init){
	if(init==1){
		$("#baseForm .pageNum").val(1);
	}
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/channels/findByPage',null,function(data){
		$("#baseTable tbody").empty();
		$("#baseHtml ul").empty();
		var obj=data.obj;
		var list=obj.list;
		if(list==null){
			var lists=obj.lists;
			var results=lists.results;
			var pageNum=lists.pageNum,pageSize=lists.pageSize,totalRecord=lists.totalRecord;
		}else{
			var results=list.results;
			var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
		}
		var permitBtn=obj.permitBtn;
        var mName = obj.mName;
		var html="";
		if(results!=null&&results.length>0){
			var leng=(pageNum-1)*pageSize;//计算序号
			if(obj.level=='2' || obj.level=='3'){
                $("#parentName").html("上级渠道");
			}else{
                $("#parentName").html("上级客户经理");
			}

			for(var i = 0;i<results.length;i++){
				var l=results[i];
				html+="<tr>";
                if('1'==l.isValid) {
                    html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.mId+"' class='ace' /> <span class='lbl'></span></label></td>";
                }else{
                    html += "<td class='center'> </td>";
                }
				html+="<td class='center'>"+JY.Object.notEmpty(l.mName)+"</td>";
				if(l.mType==1) html+="<td class='center hidden-480' >个人</td>";
				else 			html+="<td class='center hidden-480' >企业</td>";
                var mLevel="";
                if(l.mLevel==2){
                    mLevel="一级渠道";
                }else if(l.mLevel==3){
                    mLevel="二级渠道";
                }
                html+="<td class='center hidden-480'>"+JY.Object.notEmpty(mLevel)+"</td>";
				if(l.mStatus==0)  html+="<td class='center '>未审核</td>";
				else if (l.mStatus==1) html+="<td class='center '>一审通过</td>";
				else if(l.mStatus==2) html+="<td class='center '>有效</td>";
				else if(l.mStatus==3) html+="<td class='center '>拒绝</td>";
				else  html+="<td class='center '>未知</td>";
				html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.mMobile)+"</td>";
                html+="<td class='center hidden-480'><a style='cursor:pointer' onclick='findChildCustomer(\""+l.mId+"\")'>"+JY.Object.notEmpty(l.childCustomerNum)+"</a></td>";
                html+="<td class='center hidden-480'><a style='cursor:pointer' onclick='findChildMerchant(\""+l.mId+"\")'>"+JY.Object.notEmpty(l.childMerchantNum)+"</a></td>";
                var isValid="";
                if(l.isValid==1){
                    isValid="有效";
                }else if(l.isValid==2){
                    isValid="无效";
                }
                html+="<td class='center hidden-480'>"+JY.Object.notEmpty(l.mParentName)+"</td>";
                var v="";
                if(l.line==0){
                    v="线上+线下";
                }else if(l.line==1){
                    v="线上";
                }else if(l.line==2){
                    v="线下";
                }
                html+="<td class='center hidden-480'>"+JY.Object.notEmpty(v)+"</td>";

                html+=JY.Tags.setFunction(l.mId,permitBtn);
				html+="</tr>";
			}
			$("#baseTable tbody").append(html);
			JY.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
		}else{
			html+="<tr><td colspan='11' class='center'>没有相关数据</td></tr>";
			$("#baseTable tbody").append(html);
			$("#pageing ul").empty();//清空分页
		}
		JY.Model.loadingClose();
	});
}

function findChildCustomer(id){
		var index = jeBox.open({
			cell:"jbx",
			padding:"0",
			title:"查看发展客户",
			maxBtn:true,
			area:["700px","90%"],
			type:2,
			maskClose:true,
			content: 'findChildCustomer?mId='+id,
			button: [ {name: '取消'},
			],
			success:function(data){
			}
		})
}

function findChildMerchant(id){
    var index = jeBox.open({
        cell:"jbx",
        padding:"0",
        title:"查看发展渠道",
        maxBtn:true,
        area:["700px","90%"],
        type:2,
        maskClose:true,
        content: 'findChildMerchant?mId='+id,
        button: [ {name: '取消'},
        ],
        success:function(data){
        }
    })
}



//上传三证
function uploadMLicense(id){
	JY.Ajax.doRequest("baseForm",jypath +'/channels/findUserLevel',{mId:id},function(data) {
        var obj=data.obj;
        var list=obj.list;
        var lists=obj.lists;
        if(list!=null&&list.length>0){
        	if(list[0].mLevel==2 || list[0].mLevel==3){
                JY.Model.info("当前角色没有权限!");
                return;
			}else{
                if(lists[0].mLevel!=2){
                    JY.Model.info("选中角色无需上传三证!");
                }else{
                    opeMLicense(id);
                }
        	}
		}else{
			if(lists[0].mLevel!=2){
				JY.Model.info("选中角色无需上传三证!");
			}else{
                opeMLicense(id);
            }
		}

	});
}

function opeMLicense(id){
    var index = jeBox.open({
        cell:"jbx",
        padding:"0",
        title:"上传三证",
        maxBtn:true,
        area:["600px","37%"],
        type:2,
        maskClose:true,
        content: 'uploadMLicense?mId='+id,
        success:function(data){

        }
    })
}

//修改
function updateMerchant(id){
	niw="";
	niwLine="";
	cleanForm();
	$('#fileList').html('');
		JY.Ajax.doRequest(null,jypath +'/channels/findParticulars',{mId:id},function(data){
			setForm(data,"2");//1查看详情 2修改
		    JY.Model.edit("avDiv","修改",function(){
		    	if(JY.Validate.form("avForm")){
					var that =$(this);
					JY.Ajax.doRequest("avForm",jypath +'/channels/updateMerchant',null,function(data){
                        if(data.resMsg=="1"){
                            that.dialog("close");
                            getbaseList();
                            JY.Model.info("修改成功!");
                        }else if(data.resMsg=="2"){
                            JY.Model.info("电话号不合法!");
                        }else if(data.resMsg=="3"){
                            JY.Model.info("修改失败，请确认该商户是否可修改!");
                        }else if(data.resMsg=="4"){
                            JY.Model.info("电话号已存在!");
                        }
					});
				}
		    });
		});
}

//查看详情
function findParticulars(id){
	niw="";
	niwLine="";
	cleanForm();
		JY.Ajax.doRequest(null,jypath +'/channels/findParticulars',{mId:id},function(data){
			setForm(data,"1");//1查看详情 2修改
			JY.Model.check("avDiv");
		});
}

function cleanForm(){
	JY.Tags.isValid("avForm","1");
	JY.Tags.cleanForm("avForm");
    document.getElementById("form1").reset();
    $("#avForm input[name$='roleId']").val('0');//上级资源
	$("#avForm input[name$='loginName']").prop("disabled",false);
	hideRole();
}

var niw="";
var niwLine="";
function findLadder(s){
	 var ladderOne="阶梯一："+s.oneRankMin+"万~"+s.oneRankMax+"万&nbsp分润比例"+s.onePercent+"%";
	 var ladderTwo="";
	 var ladderThree="";
	 if(null!=s.twoRankMin){
		 ladderTwo="阶梯二："+s.twoRankMin+"万~"+s.twoRankMax+"万&nbsp分润比例"+s.twoPercent+"%";
	 }
	 if(null!=s.threeRankMin){
		 ladderThree="阶梯三："+s.threeRankMin+"万~"+s.threeRankMax+"万&nbsp分润比例"+s.threePercent+"%";
	 }

	 if(s.type=="1"){
		 niw+='<option value='+s.id+' title='+ladderOne+'&nbsp&nbsp'+ladderTwo+'&nbsp&nbsp'+ladderThree+'>'+s.name+'</option>';
	 }else if(s.type=="2"){
		 niwLine+='<option value='+s.id+' title='+ladderOne+'&nbsp&nbsp'+ladderTwo+'&nbsp&nbsp'+ladderThree+'>'+s.name+'</option>';
	 }
}

function setForm(data,type){
    if(type=="1"){
        $("#avDiv").ready(function(event) {
            $(this).find('input').attr("readonly","true");
            $(this).find('textarea').attr("readonly","true");
            $(this).find('select').attr("disabled","true");

        });
    }else{
        $("#avDiv").ready(function(event) {
            $(this).find('input').removeAttr("readonly");
            $(this).find('textarea').removeAttr("readonly");
            $(this).find('select').removeAttr("disabled");
        });
    }
	var l=data.obj;
    var pictures =""
	if(l.mLicense!=null){
    	if(l.mLicense.length>0){
            var mLicense = l.mLicense.split(",");
            for (var j = 0; j < mLicense.length; j++) {
                pictures+="<li id='li_"+j+"'><a href='/merchant"+mLicense[j]+"' data-rel='colorbox' class='cboxElement list-inline'>"+
                    "<img onload='if(this.width > 50 && this.height >50) this.width = 50 ,this.height =50'  src=/merchant"+mLicense[j]+"></a></li>";
            }
		}
	}
    document.getElementById("pic").innerHTML=pictures;
    $("#avForm input[name$='mLicense']").val(JY.Object.notEmpty(l.mLicense));

    JY.Ajax.doRequest(null,jypath +'/channels/findOwn',null,function(data){
        var obj=data.obj;
        var list=obj.list;
        var editParentMerchant=document.getElementById("editParentMerchant");
        var editMerchantLevel=document.getElementById("editMerchantLevel");
        var mlevel="";
        $("#mParentMerchant").attr("value",l.mParentMerchant);
        if(l.mLevel=='2'){
            mlevel='<option value="2">一级渠道</option>';
            editParentMerchant.innerHTML="";

		}else{
            mlevel='<option value="3">二级渠道</option>';
            JY.Ajax.doRequest(null,jypath +'/channels/findParentMerchant',null,function(data){
                var obj=data.obj;
                var list=obj.list;
                if(list!=null&&list.length>0){
                    var v='<option>'+list[0].mName+'</option>';
                    editParentMerchant.innerHTML=v;
                }
            });
		}
		editMerchantLevel.innerHTML=mlevel;
    });
	var commissionName=document.getElementById("edCommissionName");
	var commissionNameLine=document.getElementById("edCommissionNameLine");
    commissionName.innerHTML=niw;
    commissionNameLine.innerHTML=niwLine;
	var id=JY.Object.notEmpty(l.bcId)
	var idLine=JY.Object.notEmpty(l.bcIdLine)
	JY.Ajax.doRequest(null,jypath +'/backstage/commission/findByPageAll',{bcId:id,bcIdLine:idLine},function(data){
     	 var obj=data.obj;
	   	 var list=obj.list;
	   	 var results=list.results;
		 if(results!=null&&results.length>0){
		 if(id==""){
			niw+='<option value=0>请选择</option>';
			 for(var i = 0;i<results.length;i++){
				 var s=results[i];
				 if(s.type=="1"){
					 findLadder(s);
				 }
			 };
		 }else{
			 for(var i = 0;i<results.length;i++){
				 var s=results[i];
				 if(s.id==id){
					 if(s.type=="1"){
						 findLadder(s);
					 }
				 }
			 };
			 for(var i = 0;i<results.length;i++){
				 var s=results[i];
				 if(s.id!=id){
					 if(s.type=="1"){
						 findLadder(s);
					 }
				 }
			 };
			 niw+='<option value=1>删除佣金</option>';
		 }
		 if(idLine==""){
			niwLine+='<option value=0>请选择</option>';
			 for(var i = 0;i<results.length;i++){
				 var s=results[i];
				 if(s.type=="2"){
					 findLadder(s);
				 }
			 };
		 }else{
			 for(var i = 0;i<results.length;i++){
				 var s=results[i];
				 if(s.id==idLine){
					 if(s.type=="2"){
						 findLadder(s);
					 }
				 }
			 };
			 for(var i = 0;i<results.length;i++){
				 var s=results[i];
				 if(s.id!=idLine){
					 if(s.type=="2"){
						 findLadder(s);
					 }
				 }
			 };
			 niwLine+='<option value=1>删除佣金</option>';
		 }
		commissionName.innerHTML=niw;
		commissionNameLine.innerHTML=niwLine;
	};
	});

	var mCommionType=document.getElementById("mCommionType");
	var mc=JY.Object.notEmpty(l.mCommionType);
	var s="";
	if(mc=="1"){
		s='<option value="1">日返</option><option value="2">周返</option><option value="3">月返</option><option value="4">季度返</option>';
	}else if(mc=="2"){
		s='<option value="2">周返</option><option value="1">日返</option><option value="3">月返</option><option value="4">季度返</option>';
	}else if(mc=="3"){
		s='<option value="3">月返</option><option value="1">日返</option><option value="2">周返</option><option value="4">季度返</option>';
	}else if(mc=="4"){
		s='<option value="4">季度返</option><option value="1">日返</option><option value="2">周返</option><option value="3">月返</option>';
	}else{
		s='<option value="1">日返</option><option value="2">周返</option><option value="3">月返</option><option value="4">季度返</option>';
	}
	mCommionType.innerHTML=s;

	$("#avForm input[name$='bcId']").val(JY.Object.notEmpty(l.bcId));
    $("#avForm input[name$='bcIdLine']").val(JY.Object.notEmpty(l.bcIdLine));
	$("#avForm input[name$='mId']").val(JY.Object.notEmpty(l.mId));							//商户ID
	$("#avForm input[name$='mCpUserId']").val(JY.Object.notEmpty(l.mCpUserId));				//彩票系统账户ID
	$("#avForm input[name$='mName']").val(JY.Object.notEmpty(l.mName));						//商户名称
	var merType="";
	if(l.mType=='1'){
        merType='<option value="1">个人</option><option value="2">企业</option>';
	}else{
        merType='<option value="2">企业</option><option value="1">个人</option>';
    }
    var mType=document.getElementById("mType");
    mType.innerHTML=merType;
    var mStatus = JY.Object.notEmpty(l.mStatus);
    var status ="";
    if(mStatus==0){
        status='<option value="0">未审核</option>';
	}else if(mStatus==1){
        status='<option value="1">一审通过</option>';
	}else if(mStatus==2){
        status='<option value="2">有效</option>';
    }else if(mStatus==3){
        if(type=="1"){
            status='<option value="3">拒绝</option>';
		}else if(type=="2"){
            status='<option value="3">拒绝</option><option value="0">未审核</option>';
        }
    }
    var mStatus=document.getElementById("mStatus");
    mStatus.innerHTML=status;

    var isValid="";
    if(l.isValid=='1'){
        isValid='<option value="1">有效</option>';
    }else{
        isValid='<option value="2">无效</option>';
    }
    var merIsValid=document.getElementById("merIsValid");
    merIsValid.innerHTML=isValid;

	$("#avForm input[name$='mMobile']").val(JY.Object.notEmpty(l.mMobile));					//商户手机号
	$("#avForm textarea[name$='mAddress']").val(JY.Object.notEmpty(l.mAddress));			//商户地址
	$("#avForm input[name$='mContactUser']").val(JY.Object.notEmpty(l.mContactUser));		//联系人
	$("#avForm input[name$='mContactMobile']").val(JY.Object.notEmpty(l.mContactMobile));	//联系人手机号
	$("#avForm input[name$='mIdCard']").val(JY.Object.notEmpty(l.mIdCard));					//身份证
	$("#avForm textarea[name$='mIntroduce']").val(JY.Object.notEmpty(l.mIntroduce));		//商户简介
	$("#avForm input[name$='mAccountId']").val(JY.Object.notEmpty(l.mAccountId));			//平台用户绑定ID
	$("#avForm").find(".mBarcode").attr("src",JY.Object.notEmpty(l.mBarcode));
	$("#avForm").find(".mLicense").attr("src",JY.Object.notEmpty(l.mLicense));
    $("#avForm input[name$='line']").val(JY.Object.notEmpty(l.line));
    $("#avForm input[name$='mLevel']").val(JY.Object.notEmpty(l.mLevel));
    if(l.line==0){
        document.getElementById("edOnlineCommission").style.display = "";
        document.getElementById("edOnlineCommission1").style.display = "";
        document.getElementById("edOfflineCommission").style.display = "";
        document.getElementById("edOfflineCommission1").style.display = "";
	}else if(l.line==1){
        document.getElementById("edOnlineCommission").style.display = "";
        document.getElementById("edOnlineCommission1").style.display = "";
        document.getElementById("edOfflineCommission").style.display = "none";
        document.getElementById("edOfflineCommission1").style.display = "none";
	}else if(l.line==2){
        document.getElementById("edOfflineCommission").style.display = "";
        document.getElementById("edOfflineCommission1").style.display = "";
        document.getElementById("edOnlineCommission").style.display = "none";
        document.getElementById("edOnlineCommission1").style.display = "none";
    }
    if(l.mLevel==2){
        document.getElementById("edParentMerchantTd").style.display = "none";
        document.getElementById("edParentMerchantTd1").style.display = "none";
	}else{
        document.getElementById("edParentMerchantTd").style.display = "";
        document.getElementById("edParentMerchantTd1").style.display = "";
	}

}

$("#edCommissionName").change(function(){
	var id = document.getElementById("edCommissionName").options[document.getElementById("edCommissionName").selectedIndex].value;
	$("#avForm input[name$='bcId']").val(id);
});

$("#edCommissionNameLine").change(function(){
	var id = document.getElementById("edCommissionNameLine").options[document.getElementById("edCommissionNameLine").selectedIndex].value;
	$("#avForm input[name$='bcIdLine']").val(id);
});

$("#commissionName").change(function(){
	var id = document.getElementById("commissionName").options[document.getElementById("commissionName").selectedIndex].value;
	$("#addbcId").attr("value",id);
});

$("#commissionNameLine").change(function(){
	var id = document.getElementById("commissionNameLine").options[document.getElementById("commissionNameLine").selectedIndex].value;
	$("#addbcIdLine").attr("value",id);
});

$("#merchantLevel").change(function(){
    var id = document.getElementById("merchantLevel").options[document.getElementById("merchantLevel").selectedIndex].value;
    var parentMerchant=document.getElementById("parentMerchant");
    var v="";
    if(id==1){

        if(document.getElementById("online").checked){
			document.getElementById("onlineCommission").style.display = "";
			document.getElementById("onlineCommission1").style.display = "";
        }else{
            document.getElementById("onlineCommission").style.display = "none";
            document.getElementById("onlineCommission1").style.display = "none";
        }

        if(document.getElementById("offline").checked){
			document.getElementById("offlineCommission").style.display = "";
			document.getElementById("offlineCommission1").style.display = "";
        }else{
            document.getElementById("offlineCommission").style.display = "none";
            document.getElementById("offlineCommission1").style.display = "none";
        }

        document.getElementById("parentMerchantTd").style.display = "none";
        document.getElementById("parentMerchantTd1").style.display = "none";
        parentMerchant.innerHTML=v;
        JY.Ajax.doRequest(null,jypath +'/channels/findOwn',null,function(data){
            var obj=data.obj;
            var list=obj.list;
            if(list.length!=0){
				$("#parentMId").attr("value",list[0].mId);
				$("#mLevel").attr("value","2");
                $("#roleId").attr("value","role74bbda9fe4ae4cc6ba3e89db05d7bd59");
            }
        });
	}else{
        document.getElementById("onlineCommission").style.display = "none";
        document.getElementById("onlineCommission1").style.display = "none";
        document.getElementById("offlineCommission").style.display = "none";
        document.getElementById("offlineCommission1").style.display = "none";
        document.getElementById("parentMerchantTd").style.display = "";
        document.getElementById("parentMerchantTd1").style.display = "";
        $("#mLevel").attr("value","3");
        $("#parentMId").attr("value","");
        $("#roleId").attr("value","role71a681470e074030b3040e8ba731b22b");
        v+='<option value=0>请选择</option>';
        JY.Ajax.doRequest(null,jypath +'/channels/findParentMerchant',null,function(data){
            var obj=data.obj;
            var list=obj.list;
            if(list!=null&&list.length>0){
                for(var i = 0;i<list.length;i++){
                    var s=list[i];
                    v+='<option value='+s.mId+'>'+s.mName+'</option>';
                };
            };
            parentMerchant.innerHTML=v;
        });
	}
});

$("#parentMerchant").change(function(){
    var id = document.getElementById("parentMerchant").options[document.getElementById("parentMerchant").selectedIndex].value;
    $("#parentMId").val(id);
});

$("#online").change(function(){
    var level=$("#mLevel").val();
    if(document.getElementById("online").checked){
    	if(level=='2'){
            document.getElementById("onlineCommission").style.display = "";
            document.getElementById("onlineCommission1").style.display = "";
		}
    }else{
        document.getElementById("onlineCommission").style.display = "none";
        document.getElementById("onlineCommission1").style.display = "none";
    }
});

$("#offline").change(function(){
    var level=$("#mLevel").val();
    if(document.getElementById("offline").checked){
        if(level=='2'){
            document.getElementById("offlineCommission").style.display = "";
            document.getElementById("offlineCommission1").style.display = "";
        }
    }else{
        document.getElementById("offlineCommission").style.display = "none";
        document.getElementById("offlineCommission1").style.display = "none";
    }
});

/** 新增佣金配置*/
function setCommissionForm(mId,mName){
	$("#commissionForm input[name$='merchantName']").val(mName);
	$("#commissionForm input[name$='merchantId']").val(mId);
}

/** 预收款配置*/
function prepayment(mID,mName){
	setPrepayment(mID,mName);
	JY.Model.edit("prepaymentId","预收款配置",function(){
		if(JY.Validate.form("prepaymentForm")){
			var that =$(this);
			JY.Ajax.doRequest("prepaymentForm",jypath +'/backstage/prepayment/create',null,function(data){
				that.dialog("close");
				JY.Model.info(data.resMsg,function(){search();});
				getbaseList(1);
			});
		}
	});
}
/** 新增预收款配置*/
function setPrepayment(mId,mName){
	$("#prepaymentForm input[name$='merchantName']").val(mName);
	$("#prepaymentForm input[name$='merchantId']").val(mId);
}

function selectInformation(){
    var commissionName=document.getElementById("commissionName");
	var commissionNameLine=document.getElementById("commissionNameLine");
	niw+='<option>请选择</option>';
	niwLine+='<option>请选择</option>';
	JY.Ajax.doRequest(null,jypath +'/backstage/commission/findByPage',null,function(data){
     	 var obj=data.obj;
	   	 var list=obj.list;
	   	 var results=list.results;
		 if(results!=null&&results.length>0){
			 for(var i = 0;i<results.length;i++){
	    		 var s=results[i];
	    		 	findLadder(s);
	    		 };
		commissionName.innerHTML=niw;
		commissionNameLine.innerHTML=niwLine;
	};
	});
    JY.Ajax.doRequest(null,jypath +'/channels/findOwn',null,function(data){
        var obj=data.obj;
        var list=obj.list;
        var merchantLevel=document.getElementById("merchantLevel");
        var parentMerchant=document.getElementById("parentMerchant");
        var s="";
        if(list.length!=0){
            if(list[0].mLevel==2){
                s+='<option value=2>二级渠道</option>';
                var v="";
                $("#parentMId").attr("value",list[0].mId);
                $("#mLevel").attr("value","3");
                $("#roleId").attr("value","role71a681470e074030b3040e8ba731b22b");
                v+='<option value='+list[0].mId+'>'+list[0].mName+'</option>';
                parentMerchant.innerHTML=v;
            }else{
                document.getElementById("parentMerchantTd").style.display = "none";
                document.getElementById("parentMerchantTd1").style.display = "none";
                $("#parentMId").attr("value",list[0].mId);
                $("#mLevel").attr("value","2");
                $("#roleId").attr("value","role74bbda9fe4ae4cc6ba3e89db05d7bd59");
                s+='<option value=1>一级渠道</option>';
                s+='<option value=2>二级渠道</option>';
            }
		}else{//admin权限
            s+='<option value=2>二级渠道</option>';
            $("#mLevel").attr("value","3");
            $("#parentMId").attr("value","");
            $("#roleId").attr("value","role71a681470e074030b3040e8ba731b22b");
            var v="";
            v+='<option value=0>请选择</option>';
            JY.Ajax.doRequest(null,jypath +'/channels/findParentMerchant',null,function(data){
                var obj=data.obj;
                var list=obj.list;
                if(list!=null&&list.length>0){
                    for(var i = 0;i<list.length;i++){
                        var s=list[i];
                        v+='<option value='+s.mId+'>'+s.mName+'</option>';
                    };
                };
                parentMerchant.innerHTML=v;
            });
		}
		merchantLevel.innerHTML=s;
    });
}

