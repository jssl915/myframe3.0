<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
if(!(window.top==window.self)){//存在父页面
	parent.window.location.href="${ctx}/pt/login";	 
}
</script>
<link rel="stylesheet" type="text/css" href="${css}/login.css" />
<title>我的后台管理系统-登录</title>
</head>
<body>
<div class="singup">
    <h3>我的后台管理系统</h3>
    <div class="signup-main">
        <form id="form1">
            <input type="text" id="username" name="username" maxlength="15" class="login-text" placeholder="用户名"/>
            <input type="password" id="password" name="password" maxlength="18" class="login-text" style="margin-bottom:10px;" placeholder="密码"/>
            <div id="errorMsg" class="errorMsg"></div>
            <div id="loginBtn" class="login-btn">登录</div>
        </form>
    </div>
</div>
<script>
$(function(){
    $(document).keydown(function(e) {
		if (e.which == 13) {
			$('#loginBtn').click();
		}
 	});
    $('#loginBtn').click(function(){
    	 var username=$('#username').val();
    	 var password=$('#password').val();
    	 if(username==''||username==null){
    		 $('#errorMsg').html('用户名不能为空！').show();
    		 return
    	 }
    	 if(password==''||password==null){
    		 $('#errorMsg').html('密码不能为空！').show();
    		 return
    	 }
    	 $.ajaxApiResult('${ctx}/login/in',{"userName" : username,"userPwd" : password},function(d){
    		if (d.success!=0) {
 				window.location.href = '${ctx}/index';
 			} else {
 				$('#errorMsg').html(d.msg).show();
 			}
        });  
    })
})

</script>
</body>
</html>
