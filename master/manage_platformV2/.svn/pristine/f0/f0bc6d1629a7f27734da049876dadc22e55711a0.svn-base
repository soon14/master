$(function () {

});
	
$('.showjeBoxbnt').bind('click', function() {
	var difference=$(this).text();
	var currobj=$(this);
	var date=currobj.attr("data_date");
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"投注系统销售总报表",
		maxBtn:true,
		area:["600px","80%"],
		type:2,
		maskClose:true,
		content:"salesSum?datetime="+date,
		button: [ {name: '取消'},
		],

		// nofun: function(index){ return false; },
		// success:function(cell){
		// 	alert(cell.find(".jeBox-content").height())
		// }
	})
});

$('.jeBoxbet').bind('click', function() {
	var difference=$(this).text();
	var currobj=$(this);
	var date=currobj.attr("data_date");
	var index = jeBox.open({
		cell:"jbx",
		padding:"0",
		title:"销售差异明细报表",
		maxBtn:true,
		area:["600px","80%"],
		type:2,
		content:"salesDifferencesDetail?datetime="+date,
		masklock : true ,
		button: [ {name: '取消'},],
		//nofun: function(index){ return false; },
		success:function(cell){
			//alert(cell.find(".jeBox-content").height())
		}
	})
});

// <%--/* 执行操作 */--%>
// <%--function do_action(){--%>
// <%--checkAll("formName","ids");--%>
// <%--}--%>
//
// <%--/* 检查是否有checkbox被选中--%>
//  <%--* formName 所在form的name值--%>
//  <%--* checkboxName checkbox的name值--%>
//  <%--* 注意：所有checkbox的name值都必须一样，这样才能达到全选的效果--%>
//  <%--*/--%>
// <%--function checkAll(formName,checkboxName){--%>
// <%--var hasCheck = false;--%>
// <%--var form = document.all.item(formName);--%>
// <%--var elements = form.elements[checkboxName];--%>
// <%--//            var jsonlist=$("#jsonlist").attr("data");--%>
// <%--&lt;%&ndash;var jsonlists=${jsonlist};&ndash;%&gt;--%>
// <%--&lt;%&ndash;var obj = eval('(' + ${jsonlist} + ')');&ndash;%&gt;--%>
// <%--var json_str=JSON.stringify(${jsonlist});--%>
// <%--var idAll="";--%>
// <%--for (var i=0;i<elements.length;i++){--%>
// <%--if(elements[i].checked){--%>
// <%--idAll+=elements[i].value+",";--%>
// <%--}--%>
// <%--}--%>
// <%--if(null!=idAll && ""!=idAll){--%>
// <%--if(confirm("您确定要全部导出吗?")){--%>
// <%--window.location="/finance/derive.do?beginDate="+idAll+"&json="+json_str;--%>
// <%--hasCheck = true;--%>
// <%--}--%>
// <%--}else{--%>
// <%--confirm("请先勾选要导出的数据.")--%>
// <%--}--%>
// <%--return hasCheck;--%>
// <%--}--%>


