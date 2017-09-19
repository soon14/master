function closeJeBox(){
	var deposit = $("#deposit").val();
	var reg =/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;//非负数校验
	if(deposit == ""){
		JY.Model.info("金额不能为空");
		return false;
	}
		if(deposit == 0){
			JY.Model.info("金额不能为0");
			return false;
		}
	if(deposit >999999999){
			JY.Model.info("金额超出范围");
			return false;
		}
	if(!reg.test(deposit)){
		JY.Model.info("输入金额格式不正确");
		return false;
	}

	var flag = $("#addExist").val();
	if(flag==1) {
		updateDeposit();//修改
	}else{
		addDeposit();//新增
	}

}

function addDeposit(){

	JY.Ajax.doRequest("form1",jypath +'/channels/deposit/addDeposit',null,function(data){
		if(data.resMsg=="success"){
			JY.Model.info("保存成功!");
			window.parent.location.reload();
			window.parent.jeBox.close("jbx");
		}else{
			JY.Model.info("保存失败!");
		}

	});
}
function updateDeposit(){

	JY.Ajax.doRequest("form1",jypath +'/channels/deposit/updateDeposit',null,function(data){
		if(data.resMsg=="success"){
			JY.Model.info("修改成功!");
			window.parent.location.reload();
			window.parent.jeBox.close("jbx");
		}else{
			JY.Model.info("修改失败!");
		}

	});
}
//$(function () {
//	var flag = $("#addExist").val();
//	if(flag==1){
//		JY.Model.info("该用户已缴纳过押金!");
//		$("#deposit").attr("disabled",true);
//		$("#save").attr("disabled",true);
//
//	}
//});



