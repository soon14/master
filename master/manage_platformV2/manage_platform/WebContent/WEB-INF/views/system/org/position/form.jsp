<%@ page contentType="text/html;charset=UTF-8" %>
<div id="arrangeAccDiv" class="hide">
	<form id="arrangeAccForm" class="form-inline" method="POST" onsubmit="return false;">
		<div class="widget-main customBtn">	
			<input  type="text"  name="keyWord" placeholder="这里输入关键词" class="input-medium">												
			&nbsp;&nbsp;<button class="btn btn-warning  btn-xs" title="过滤" type="button" onclick="loadArrangeAcc(1)"><i class="icon-search bigger-110 icon-only"></i></button>		
		</div>			
		<input type='hidden' class='pageNum' name='pageNum' value='1'/>
		<input type='hidden' class='pageSize'  name='pageSize' value='3'/>
	</form>
	<table id="arrangeAccTable" cellspacing="0" cellpadding="0" border="0" class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th class="center" style="width:10%">
					<label><input type="checkbox" class="ace"><span class="lbl"></span></label>
				</th>
				<th style="width:20%" class="center">序号</th>
				<th style="width:35%" class="center">登录名</th>
				<th style="width:35%" class="center">用户名</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<div class="row">
		<div id="arrangeAccpageing" class="dataTables_paginate paging_bootstrap">
			<ul class="pagination"></ul>
		</div>
	</div>
</div>

<!-- 授权界面 -->
<div id="authorityDiv" class="hide">
	<div class="row-fluid">	
		<button title="选择显示层级" value='1' class="btn btn-xs btn-success"><i class="icon-desktop"></i></button>					
<!-- 		<input type="hidden" name="layer" value="1" >
		<input type="hidden" name="auth" value="org" >
		<input type="hidden" name="roleId" value="" > -->
		<div class="col-xs-12">
			<ul id="authorityTree" class="ztree authorityTree"></ul>
		</div>
	</div>
</div>