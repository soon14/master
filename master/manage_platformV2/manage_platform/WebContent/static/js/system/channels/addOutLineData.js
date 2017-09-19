function closeJeBox(){
	var datetime = $("#endTime").val();
	var machineNum = $("#machineNum").val();
	var saleMoney = $("#saleMoney").val();
	var winningMoney = $("#winningMoney").val();
	var offsetVoucherMoney = $("#offsetVoucherMoney").val();
	var returnVoucherMoney = $("#returnVoucherMoney").val();
	//var merchantId = $("#merchantId").val();
	var merchantName = $("#merchantName").val();
	var merchantPhone = $("#merchantPhone").val();
	var reg =/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;//非负数校验
	//var regexp = /^([1][7-9][0-9][0-9]|[2][0][0-9][0-9])(\-)([0][1-9]|[1][0-2])(\-)([0-2][1-9]|[3][0-1])$/g;//日期校验

	if(datetime == ""){
		JY.Model.info("日期不能为空");
		return false;
	}	if(machineNum == ""){
		JY.Model.info("机器号不能为空");
		return false;
	}	if(saleMoney == ""){
		JY.Model.info("销售额不能为空");
		return false;
	}	if(winningMoney == ""){
		JY.Model.info("中奖金额不能为空");
		return false;
	}	if(offsetVoucherMoney == ""){
		JY.Model.info("抵用券金额不能为空");
		return false;
	}if(returnVoucherMoney == ""){
		JY.Model.info("返还券金额不能为空");
		return false;
	}if(merchantName == "请选择"){
		JY.Model.info("商户名称不能为空");
		return false;
	}if(merchantPhone == ""){
		JY.Model.info("商户手机号不能为空");
		return false;
	}
	//if(!regexp.test(datetime)){
	//	JY.Model.info("日期格式不正确");
	//	return false;
	//}
	if(!reg.test(saleMoney)){
		JY.Model.info("销售金额输入不正确");
		return false;
	}
	if(!reg.test(winningMoney)){
		JY.Model.info("中奖金额输入不正确");
		return false;
	}
	if(!reg.test(offsetVoucherMoney)){
		JY.Model.info("抵用券金额输入不正确");
		return false;
	}
	if(!reg.test(returnVoucherMoney)){
		JY.Model.info("返还券金额输入不正确");
		return false;
	}
	JY.Ajax.doRequest("form1",jypath +'/channels/onlinedata/checkBalance',null,function(data){
		if(data.resMsg=="success"){
			addOutLineData();
		}else if(data.resMsg=="notExist"){
			JY.Model.info("该商户不存在或未缴纳预存款!");return false;
		}else if(data.resMsg=="fail"){
			JY.Model.info("可用余额不足!");return false;
		}else if(data.resMsg=="error"){
			JY.Model.info("系统运行出现错误!");return false;
		}
	});

}

function addOutLineData(){
	JY.Ajax.doRequest("form1",jypath +'/channels/onlinedata/addOutLineData',null,function(data){
		if(data.resMsg=="success"){
			JY.Model.info("保存成功!");
			window.parent.location.reload();
			window.parent.jeBox.close("jbx");
		}else{
			JY.Model.info("保存失败!");return false;
		}

	});
}
function findMerchant() {
	var $selectmercha=$("#merchantName");
	var niw='<option>请选择</option>';
	JY.Ajax.doRequest("baseForm", jypath + '/channels/onlinedata/findMerchant', null, function (data) {
		var obj = data.obj;
		var list = obj.list;
		var results = list.results;
		if (results != null && results.length > 0) {
			var leng = obj.size;//计算序号
			for (var i = 0; i < results.length; i++) {
				var l = results[i];
				niw+="<option value='"+l.mId+";"+l.mName+";"+l.mLevel+"'>"+l.mName+"</option>";
			}
		}
		//selectmerchantId.innerHTML=niw;
		$selectmercha.append(niw);
		$("#merchantName").chosen();
	});

}



