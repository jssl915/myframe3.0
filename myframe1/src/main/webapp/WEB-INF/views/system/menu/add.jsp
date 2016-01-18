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
    <div class="table-edit-div">
        <form id="form1">
        <input type="hidden" id="menuPid" name="menuPid" value="${sMenuP.menuId}">
        <input type="hidden" id="menuLevel" name="menuLevel" value="${sMenuP.menuLevel}">
        <table class="table-edit">
            <tr>
                <td><span class="required">*</span>父菜单</td>
                <td>${sMenuP.menuName}</td>
            </tr>
            <tr>
             	<c:if test="${sMenuP.menuLevel==2}">
                <td><span class="required">*</span>按钮名称</td>
             	</c:if>  
             	<c:if test="${sMenuP.menuLevel!=2}">
                <td><span class="required">*</span>菜单名称</td>
             	</c:if>   
                <td><input type="text" id="menuName" name="menuName" maxlength="16"><div class="error-msg"></div></td>
            </tr>
            <tr>
                <td>排序</td>
                <td><input type="text" id="menuOrder" name="menuOrder"><div class="error-msg"></div></td>
            </tr>
            <c:if test="${sMenuP.menuLevel!=2}">
            <tr>
                <td>菜单URL</td>
                <td><input type="text" id="menuUrl" name="menuUrl"><div class="error-msg"></div></td>
            </tr>
            </c:if>
            <tr>
                <td colspan="4" style="text-align:center;padding-top:40px;">
                    <div id="submitBtn" class="submit operate-btn-add">提交</div>
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>
<script>
$(function(){
	addTitle('新增');
	//失去焦点验证
	var vData = {
        'menuName':{'required':true,'name':true},
        'menuOrder':{'number':true}
    };
	validate(vData);
	$('#submitBtn').click(function(){
	    if(validateSubmit(vData)){
	    	$.ajaxApiResult('insert',$('#form1').serialize(),function(d){
	    		$.myTip({content:"新增成功"});
	    		window.location.href="${ctx}/system/menu/init";
			}) 
        }
	});
});
</script>
</body>
</html>
