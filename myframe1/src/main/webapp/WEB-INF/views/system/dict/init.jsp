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
                <td>字典名称</td>
                <td><input type="text" id="dictName" name="dictName" maxlength="32" class="search-text" style="width:120px"></td>
                <td><div class="search-btn">搜索</div></td>
                <td><div class="clear-btn">清空</div></td>
            </tr>
        </table>
        <a  class="add operate-btn-add" onclick="toAdd()">新增参数</a>
    </div>
    <div id="dictList" class="table-list-div"></div>
</div>
<script>
$(function(){
	$('.clear-btn').click(function(){
		$('#dictName').val('');
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
		data:{"dictName":$('#dictName').val(),"pageSize":10},
		tableName:'参数列表',
		column:[
			{'title':'序号'},        
			{'title':'字典名称','name':'dictName'},        
			{'title':'字典描述','name':'dictDesc'},        
			{'title':'状态','name':'dictStatus','returnBack':function(d){return JSON.parse('${statusMap}')[d.dictStatus]}}, 
			{'title':'修改时间','name':'updateTime'},
			{'title':'操作','width':'200','returnBack':function(d){
				var aHtml =[];
			    aHtml.push('<span onclick="toEdit('+d.dictId+')" class="operate-btn">修改</span>');
			    aHtml.push('<span onclick="toDel('+d.dictId+')" class="operate-btn">删除</span>');
			    aHtml.push('<span onclick="toDetail('+d.dictId+')" class="operate-btn">明细</span>');
				return aHtml.join(' | ');
			}}       
		]
	}); 
}
function toAdd(){
    window.location.href="${ctx}/system/dict/showAdd";
}
function toEdit(id){
	window.location.href="${ctx}/system/dict/showEdit?dictId="+id;
}
function toDetail(id){
	window.location.href="${ctx}/system/detail/init?dictId="+id;
}
function toDel(id){
	 $.myConfirm({
         content:'真的要删除吗？',
         onSubmit:function(){
        	 $.ajaxApiResult('del',{ids:id},function(d){
        		 $.myTip({content:"删除成功"});
        		 window.location.href="${ctx}/system/dict/init";
        	 }); 
         }
     });
}
</script>
</body>
</html>