<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微狐叫师后台管理平台</title>
</head>
<body>
<div id="rightFrame" class="right-frame">
    <div id="leftTree" class="st_tree" style="float:left;width:245px"></div>
    <div id="userDiv" class="table-list-div" style="margin:0 0 0 266px;">
    
    </div>
    <div id="userBtn" class="submit operate-btn-add" onclick="doSubmit()" style="position:absolute;bottom:10px;left:300px;z-index:2000">保存</div>
    
    <form id="form1" action="bindMenu">
     <input type="hidden" name="roleId" value="${roleId}"/>
	 <input type="hidden" name="menuIds" id="menuIds"/>
	 <div id="submitBtn" class="submit operate-btn-add" style="position:absolute;bottom:10px;left:60px;z-index:2000">保存</div>
	</form>
	<form id="form2" action="bindUser">
	<input type="hidden" name="roleId" value="${roleId}"/>
	<input type="hidden" name="userIds" id="userIds"/>
	</form>
</div>
<script>
$(function(){
	addTitle('配置权限');
	var leftHeight = $("#leftSide",window.parent.document).height();
	$('#leftTree').height(leftHeight-80);
	var aRole = JSON.parse('${sRoleMenuJson}');
	$.ajaxApiResult('${ctx}/system/menu/tree',null,function(data){
		$('#leftTree').showMenuListCheckTree(data,aRole);
	});
	
	$('#userDiv').userChoose(JSON.parse('${userData}'),JSON.parse('${sUserRoleList}'));	
	$('#submitBtn').click(function(){
		var aCheckboxs = $('#leftTree').find('.checkbox1');
		var aMenuId = [];
		for(var i=0;i<aCheckboxs.length;i++){
			aMenuId.push($(aCheckboxs[i]).data('id'));
		}
		$('#menuIds').val(aMenuId.join(','));
    	$.ajaxApiResult('${ctx}/system/role/bindMenu',$('#form1').serialize(),function(d){
    		$.myTip({content:"绑定菜单成功"});
		}) 
	});
});

function doSubmit(){
	var userIds="";
	$('input[name="userId"]:checked').each(function(){ 
		 userIds+=$(this).val()+','; 
	}); 
	$("#userIds").val(userIds);	
	$.ajaxApiResult('${ctx}/system/role/bindUser',$('#form2').serialize(),function(d){
		$.myTip({content:"绑定用户成功"});
	}) 
}

$.fn.extend({'userChoose':function(data,sUserRoleList){
	var aLetter = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];	
	var aCheckId = [];
	var _this = this;
	loadHtml(data);
	function loadHtml(data){
		var htmlArray = [];
		htmlArray.push('<div class="firstLetter">按拼音首字母选择<input type="text" id="search" name="search" class="user-search-text" placeholder="请输入登录名拼音首字母"></div>');
		htmlArray.push('<ul id="oneUl" class="oneUl">');
			for(var i=0;i<aLetter.length;i++){
				var aData = data[aLetter[i]];
				if(aData!=undefined&&aData.length>0){
					var line = aData.length%5==0?Math.floor(aData.length/5):(Math.floor(aData.length/5)+1);
					var iHeight = line*40;
					htmlArray.push(' <li class="oneLi" style="height:'+iHeight+'px"><div class="userDiv"><div class="letterDiv">'+aLetter[i]+'</div><ul class="twoUl">');
					for(var j=0;j<aData.length;j++){
						htmlArray.push('<li><input type="checkbox" value='+aData[j].id+' name="userId"');
						for(var k=0;k<sUserRoleList.length;k++){
							if(sUserRoleList[k].userId == aData[j].id){
								htmlArray.push('checked="checked"');
							}
						} 
						htmlArray.push('/>&nbsp;'+aData[j].name+'</li>');
					}
					htmlArray.push('</ul></div></li>');
				}
			}
		htmlArray.push('</ul>');
		$(_this).html(htmlArray.join(''));
	}
	function addIds(){
		$('input[name="userId"]:checked').each(function(){ 
			aCheckId.push($(this).val()); 
		}); 
	}
	function searchHtml(data,userName){
		var ulArray = [];
		for(var i=0;i<aLetter.length;i++){
			var aData = data[aLetter[i]];
			if(aData!=undefined&&aData.length>0){
				var line = aData.length%5==0?Math.floor(aData.length/5):(Math.floor(aData.length/5)+1);
				var iHeight = line*40;
				ulArray.push(' <li class="oneLi" style="height:'+iHeight+'px"><div class="userDiv"><div class="letterDiv">'+aLetter[i]+'</div><ul class="twoUl">');
				for(var j=0;j<aData.length;j++){					
					ulArray.push('<li><input type="checkbox" value='+aData[j].id+' name="userId"');
					if(aCheckId.indexOf(aData[j].id)!=-1){
						ulArray.push('checked="checked"');
					}
					ulArray.push('/>&nbsp;'+aData[j].name+'</li>');
				}
				ulArray.push('</ul></div></li>');
			}
		}
		$('#oneUl').html(ulArray.join(''));
	}
	$('#search').keyup(function(){
		addIds();
		var letter = $(this).val();
		if(letter.length == 0){
			searchHtml(data);
			return
		}
		var newData = {};
		if(data[letter.toUpperCase()]!=undefined||letter.length>0){
			newData[letter.toUpperCase()] =  data[letter.toUpperCase()];
			searchHtml(newData);
		}
	});
}});
</script>
</body>
</html>
