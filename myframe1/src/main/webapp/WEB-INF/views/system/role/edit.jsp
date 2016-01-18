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
        <input type="hidden" id="roleId" name="roleId" value="${sRole.roleId}">
        <table class="table-edit">
            <tr>
                <td><span class="required">*</span>角色名称</td>
                <td>
					<input type="text" id="roleName" name="roleName" value="${sRole.roleName}" maxlength="15">
                 	<div class="error-msg"></div>
                </td>
                <td>排序</td>
                <td><input type="text" id="roleOrder" name="roleOrder" value="${sRole.roleOrder}"><div class="error-msg"></div></td>  
            </tr>
            <tr>
				<td>角色描述</td>
				<td colspan="3" style="line-height:25px;padding-top:10px;padding-bottom:10px;">
					<textarea name="roleDesc" id="roleDesc" maxlength="256">${sRole.roleDesc}</textarea>
					<div class="error-msg" style="bottom:-3px;top:auto;"></div>
				</td>
			</tr>
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
	addTitle('编辑');;
	//失去焦点验证
	var vData = {
        'roleName':{'required':true,'name':true},
        'roleOrder':{'number':true}
    };
	validate(vData);
	$('#submitBtn').click(function(){
	    if(validateSubmit(vData)){
	    	$.ajaxApiResult('update',$('#form1').serialize(),function(d){
	    		$.myTip({content:"修改成功"});
	    		window.location.href="${ctx}/system/role/init";
			}) 
        }
	});
});
</script>
</body>
</html>
