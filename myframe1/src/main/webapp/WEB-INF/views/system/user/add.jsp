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
        <table class="table-edit">
            <tr>
                <td><span class="required">*</span>登录名</td>
                <td>
					<input type="text" id="userName" name="userName" maxlength="15">
                 	<div class="error-msg"></div>
                </td>
                <td><span class="required">*</span>初始密码</td>
                <td><input type="text" id="userPwd" name="userPwd" value="${initPassword}" readonly="readonly" maxlength="10"><div class="error-msg"></div></td>
            </tr>
            <tr>
                <td><span class="required">*</span>真实姓名</td>
                <td><input type="text" id="realName" name="realName" maxlength="16"><div class="error-msg"></div></td>
                <td>手机号</td>
                <td><input type="text" id="mobile" name="mobile" maxlength="16"><div class="error-msg"></div></td>
            </tr>
            <tr>
                <td>邮箱地址</td>
                <td><input type="text" id="email" name="email"><div class="error-msg"></div></td>
                <td>性别</td>
                <td>
					<select id="sex" name="sex">
						<c:forEach var="item" items="${sexMap}">
							<option value="${item.key}">${item.value}</option>
						</c:forEach>
					</select>
				</td>
            </tr>
            <tr>
                <td>排序</td>
                <td><input type="text" id="userOrder" name="userOrder"><div class="error-msg"></div></td>
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
	addTitle('新增');
	//失去焦点验证
	var vData = {
        'userName':{'required':true,'name':true},
        'realName':{'required':true,'name':true},
        'mobile':{'mobile':true},
        'userOrder':{'number':true}
    };
	validate(vData);
	$('#submitBtn').click(function(){
	    if(validateSubmit(vData)){
	    	$.ajaxApiResult('insert',$('#form1').serialize(),function(d){
	    		$.myTip({content:"新增成功"});
	    		window.location.href="${ctx}/system/user/init";
			}) 
        }
	});
});
</script>
</body>
</html>
