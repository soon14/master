<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html lang="en">
<head>
    <%@include file="../../common/includeBaseSet.jsp" %>
    <%@include file="../../common/includeSystemSet.jsp" %>
    <link rel="stylesheet" href="${jypath}/static/plugins/zTree/3.5/zTreeStyle.css" />
    <link href="${jypath}/static/js/jquery/jebox/skin/default.css" rel="stylesheet" type="text/css">
    <script src="${jypath}/static/plugins/zTree/3.5/jquery.ztree.core-3.5.min.js"></script>
</head>
<body>
<div class="page-content">
    <div class="row-fluid">
        <div class="col-xs-12">
            <form id="baseForm" class="form-inline" method="POST" onsubmit="return false;">
                <div class="row">
                    <div class="widget-main">
                        日期:<input id ="beginTime" name="beginTime" value="" class="date-picker" type="text" placeholder="开始日期" >
                        <input id ="endTime" name="endTime" value="" class="date-picker" type="text" placeholder="结束日期" >
                        <button id='resetData' class="btn btn-warning  btn-xs" title="过滤" type="button">数据重置</button>
                        <br/>
                        <br/>
                        <br/>
                        <br/>
                        <table border="0px">
                            <tr>
                                <td  id="msg"style="width:450px;text-decoration: none;text-align:center;font-size: 22pt; color: #ff3333; font-family:宋体">&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>
<script type="text/javascript" src="${jypath}/static/js/jquery/jebox/jebox.js?ver=2.2"></script>
<script type="text/javascript">
    $(function () {
        $('#resetData').bind('click', function() {
            if(dateCompare()) {
                $(this).attr("disabled","true");
                JY.Model.loading();
                JY.Ajax.doRequest("baseForm",jypath +'/backstage/cashDifference/restBill',null,function(data){
                    var obj=data.obj;
                    document.getElementById('msg').innerHTML=obj.msg;
                    setTimeout("$('#resetData').removeAttr('disabled')");
                });
            }
        })
    });

    function dateCompare() {
        var start = $('#beginTime').val();
        var end = $('#endTime').val();
        starts = start.split("-");
        ends = end.split("-");
        if (starts[0] == ends[0]) {
            /* if (starts[1] == ends[1]) {
             if ((starts[2] - ends[2]) < 10) {
             return true;
             } else {
             return false;
             }
             } else {
             return false;
             } */
            if ((ends[1] - starts[1]) < 2) {
                return true;
            }
        } else {
            return false;
        }
    }
</script>
</body>
</html>