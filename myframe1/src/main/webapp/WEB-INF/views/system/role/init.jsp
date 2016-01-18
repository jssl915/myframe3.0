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
                <td>角色名称</td>
                <td><input type="text" id="roleName" name="roleName" maxlength="32" class="search-text" style="width:120px"></td>
                <td><div class="search-btn">搜索</div></td>
                <td><div class="clear-btn">清空</div></td>
            </tr>
        </table>
        <a  class="add operate-btn-add" onclick="toAdd()">新增角色</a>
    </div>
    <div id="roleList" class="table-list-div"></div>
</div>
<script>
$(function(){
	$('.clear-btn').click(function(){
		$('#roleName').val('');
	});
	$('.search-btn').click(function(){
	    $('#roleList').loadPageNum();
		getList();
	});
	getList();
});
function getList(){
	$('#roleList').loadList({
		url:'list',
		data:{"roleName":$('#roleName').val(),"pageSize":10},
		tableName:'角色列表',
		column:[
			{'title':'序号'},        
			{'title':'角色名称','name':'roleName'},        
			{'title':'角色描述','name':'roleDesc'},        
			{'title':'状态','name':'roleStatus','returnBack':function(d){return JSON.parse('${statusMap}')[d.roleStatus]}}, 
			{'title':'排序','name':'roleOrder'}, 
			{'title':'创建时间','name':'createTime'},
			{'title':'修改时间','name':'updateTime'},
			{'title':'操作','width':'200','returnBack':function(d){
				var aHtml =[];
			    aHtml.push('<span onclick="toEdit('+d.roleId+')" class="operate-btn">修改</span>');
			    aHtml.push('<span onclick="toDel('+d.roleId+')" class="operate-btn">删除</span>');
			    aHtml.push('<span onclick="bindPower('+d.roleId+')" class="operate-btn">绑定权限</span>');
				return aHtml.join(' | ');
			}}      
		]
	}); 
}
function toAdd(){
    window.location.href="${ctx}/system/role/showAdd";
}
function toEdit(id){
	window.location.href="${ctx}/system/role/showEdit?roleId="+id;
}
function bindPower(id){
	window.location.href="${ctx}/system/role/bindPower?roleId="+id;
}
function toDel(id){
	 $.myConfirm({
         content:'真的要删除吗？',
         onSubmit:function(){
        	 $.ajaxApiResult('del',{ids:id},function(d){
        		 $.myTip({content:"删除成功"});
        		 window.location.href="${ctx}/system/role/init";
        	 }); 
         }
     });
}
</script>
</body>
</html>