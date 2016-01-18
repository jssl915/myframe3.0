<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404</title>
<style>
body{
	font-family: 'Love Ya Like A Sister', cursive;
}
.wrap{
	margin:0 auto;
	width:1000px;
}
.logo{
	text-align:center;
	margin-top:120px;
	height:540px;
}
.logo img{
	width:350px;
}
.logo p{
	color:#272727;
	font-size:40px;
	margin-top:1px;
}	
.logo p span{
	color:lightgreen;
}	
.sub a{
	color:#fff;
	background:#272727;
	text-decoration:none;
	padding:10px 20px;
	font-size:13px;
	font-family: arial, serif;
	font-weight:bold;
	-webkit-border-radius:.5em;
	-moz-border-radius:.5em;
	-border-radius:.5em;
}	
.footer{
	color:black;
	position:absolute;
	right:10px;
	bottom:10px;
}	
.footer a{
	color:rgb(114, 173, 38);
}	
</style>
</head>
<body>
 <div class="wrap">
	<div class="logo">
		<p>对不起！找不到页面啦!</p>
		<img src="${img}/404-1.png"/>
		<div class="sub">
		  <p><a href="#" onclick="goIndex()">回到首页 </a></p>
		</div>
	</div>
 </div>	
 <script>
 function goIndex(){
	 parent.window.location.href="${ctx}/index";
 }
 </script>
</body>
</html>
