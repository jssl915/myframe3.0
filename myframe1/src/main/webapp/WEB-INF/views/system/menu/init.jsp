<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微狐叫师后台管理平台</title>
</head>
<body>
<div class="right-frame">
    <div id="leftTree" class="st_tree" style="float:left;"></div>
    <div class="table-search-div">
        <table class="table-search">
            <tr>
                <td>菜单名称</td>
                <td><input type="text" id="menuName" name="menuName" maxlength="32" class="search-text" style="width:120px"></td>
                <td><div class="search-btn">搜索</div></td>
                <td><div class="clear-btn">清空</div></td>
            </tr>
        </table>
    </div>
    <div id="menuList" class="table-list-div" style="margin:0 0 0 266px;"></div>
</div>
<script>
var menuId = ''
$(function(){
	$('.table-list-div').width($('.right-frame').width()-290);//列表
	$('#leftTree').height($("#leftSide",window.parent.document).height()-80);
	getList();
	$('.clear-btn').click(function(){
		$('#menuName').val('');
	});
	$('.search-btn').click(function(){
	    $('#menuList').loadPageNum();
		getList();
	});
	$.ajaxApiResult('${ctx}/system/menu/tree',null,function(data){
		$("#leftTree").loadEditMenuTree(data);
		parent.iFrameHeight();
		$("#leftTree").find('span.t-add').click(function(){
			var id = $(this).parent().data('id');
			window.location.href="${ctx}/system/menu/showAdd?menuPid="+id;
			return false;
		});
		$("#leftTree").find('span.t-edit').click(function(){
			$("#leftTree li").removeClass('selected');
			$(this).parent().addClass('selected');
			menuId = $(this).parent().data('id');
			$('#menuList').loadPageNum();
			getList();
			return false;
		});
		$("#leftTree").find('span.t-del').click(function(){
			var id = $(this).parent().data('id');
			toDel(id);
			return false;
		})
	});
});
function getList(){
	$('#menuList').loadList({
		url:'list',
		data:{"menuId":menuId,"menuName":$('#menuName').val(),"pageSize":10},
		tableName:'菜单列表',
		column:[
			{'title':'序号'},        
			{'title':'菜单名称','name':'menuName'},        
			{'title':'菜单URL','name':'menuUrl'},   
			{'title':'菜单级别','name':'menuLevel'},   
			{'title':'状态','name':'menuStatus','returnBack':function(d){return JSON.parse('${statusMap}')[d.menuStatus]}}, 
			{'title':'排序','name':'menuOrder'}, 
			{'title':'创建时间','name':'createTime'},
			{'title':'操作','returnBack':function(d){
				if(d.menuId==-1){
					return '';
				}
				var aHtml =[];
			    aHtml.push('<span onclick="toEdit('+d.menuId+')" class="operate-btn">修改</span>');
			    aHtml.push('<span onclick="toDel('+d.menuId+')" class="operate-btn">删除</span>');
				return aHtml.join(' | ');
			}}        
		]
	}); 
}
function toEdit(id){
	window.location.href="${ctx}/system/menu/showEdit?menuId="+id;
}
function toDel(id){
	 $.myConfirm({
        content:'真的要删除吗？',
        onSubmit:function(){
       	 $.ajaxApiResult('del',{ids:id},function(d){
       		 $.myTip({content:"删除成功"});
       		 window.location.href="${ctx}/system/menu/init";
       	 }); 
        }
    });
}
</script>
</body>
</html>
