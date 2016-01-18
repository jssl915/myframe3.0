<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="rightFrame" class="right-frame">
    <div class="table-search-div">
        <table class="table-search">
            <tr>
                <td>字段名称</td>
                <td><input type="text" id="detailName" name="detailName" maxlength="32" class="search-text" style="width:120px"></td>
                <td><div class="search-btn">搜索</div></td>
                <td><div class="clear-btn">清空</div></td>
            </tr>
        </table>
        <a  class="add operate-btn-add" onclick="toAdd()">新增参数明细</a>
    </div>
    <div id="dictList" class="table-list-div"></div>
</div>
<script>
$(function(){
	addTitle('明细','/system/detail/init?dictId=${sysDict.dictId}',2);
	$('.clear-btn').click(function(){
		$('#detailName').val('');
	});
	$('.search-btn').click(function(){
	    $('#dictList').loadPageNum();
		getList();
	});
	getList();
});
function getList(){
	$('#dictList').loadList({
		url:'list',
		data:{"dictId":'${sysDict.dictId}',"detailName":$('#detailName').val(),"pageSize":10},
		tableName:'参数[${sysDict.dictName}]明细列表',
		column:[
			{'title':'序号'},        
			{'title':'字段名称','name':'detailName'},        
			{'title':'字段值','name':'detailValue'},        
			{'title':'字段描述','name':'detailDesc'},        
			{'title':'状态','name':'detailStatus','returnBack':function(d){return JSON.parse('${statusMap}')[d.detailStatus]}}, 
			{'title':'修改时间','name':'updateTime'},
			{'title':'操作','width':'200','returnBack':function(d){
				var aHtml =[];
			    aHtml.push('<span onclick="toEdit('+d.detailId+')" class="operate-btn">修改</span>');
			    aHtml.push('<span onclick="toDel('+d.detailId+')" class="operate-btn">删除</span>');
				return aHtml.join(' | ');
			}}       
		]
	}); 
}
function toAdd(){
    window.location.href="${ctx}/system/detail/showAdd?dictId=${sysDict.dictId}";
}
function toEdit(id){
	window.location.href="${ctx}/system/detail/showEdit?detailId="+id;
}
function toDel(id){
	 $.myConfirm({
         content:'真的要删除吗？',
         onSubmit:function(){
        	 $.ajaxApiResult('del',{ids:id},function(d){
        		 $.myTip({content:"删除成功"});
        		 window.location.href="${ctx}/system/detail/init?dictId=${sysDict.dictId}";
        	 }); 
         }
     });
}
</script>
</body>
</html>