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
                <td>系统登录名</td>
                <td><input type="text" id="userName" name="userName" maxlength="32" class="search-text" style="width:120px"></td>
                <td><div class="search-btn">搜索</div></td>
                <td><div class="clear-btn">清空</div></td>
            </tr>
        </table>
        <a  class="add operate-btn-add" onclick="toAdd()">新增管理员</a>
    </div>
    <div id="userList" class="table-list-div"></div>
</div>
<script>
$(function(){
	$('.clear-btn').click(function(){
		$('#userName').val('');
	});
	$('.search-btn').click(function(){
	    $('#userList').loadPageNum();
		getList();
	});
	getList();
});
function getList(){
	$('#userList').loadList({
		url:'list',
		data:{"userName":$('#userName').val(),"pageSize":10},
		tableName:'管理员列表',
		column:[
			{'title':'序号'},        
			{'title':'系统登录名','name':'userName'},        
			{'title':'真实姓名','name':'realName'},        
			{'title':'手机号码','name':'mobile'},        
			{'title':'性别','name':'sex','returnBack':function(d){return JSON.parse('${sexMap}')[d.sex]}}, 
			{'title':'邮箱','name':'email'},  
			{'title':'状态','name':'userStatus','returnBack':function(d){return JSON.parse('${statusMap}')[d.userStatus]}}, 
			{'title':'排序','name':'userOrder'}, 
			{'title':'创建时间','name':'createTime'},
			{'title':'操作','width':'200','returnBack':function(d){
				var aHtml =[];
			    aHtml.push('<span onclick="toEdit('+d.userId+')" class="operate-btn">修改</span>');
			    aHtml.push('<span onclick="toDel('+d.userId+')" class="operate-btn">删除</span>');
			    aHtml.push('<span onclick="initPwd('+d.userId+')" class="operate-btn">初始化密码</span>');
				return aHtml.join(' | ');
			}}    
		]
	}); 
}
function toAdd(){
    window.location.href="${ctx}/system/user/showAdd";
}
function toEdit(id){
	window.location.href="${ctx}/system/user/showEdit?userId="+id;
}
function toDel(id){
	 $.myConfirm({
         content:'真的要删除吗？',
         onSubmit:function(){
        	 $.ajaxApiResult('del',{ids:id},function(d){
        		 $.myTip({content:"删除成功"});
        		 window.location.href="${ctx}/system/user/init";
        	 }); 
         }
     });
}
function initPwd(id){
	 parent.$.dialogConfirm({
         content:'真的要初始化密码吗？',
         onSubmit:function(){
        	 $.ajaxApiResult('initPwd',{"ids":id},function(d){
        		 $.myTip({content:"初始化密码成功，密码为888888"});
        		 window.location.href="${ctx}/system/user/init";
        	 }); 
         }
     });
}
</script>
</body>
</html>