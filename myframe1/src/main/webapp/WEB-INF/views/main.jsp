<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微狐官网后台管理系统</title>
<style>
.indexbg{
	background:url('${img}/bg1.jpg');
	background-repeat:repeat-x;
	width:100%;
}
.indexbg2{
	position:absolute;
	left:50%;
	top:150px;
	margin-left:-240px;
	background:url('${img}/bj2.png');
	width:480px;
	height:320px;
}
</style>
</head>
<body>
<div title="我的主页">
	<div class="indexbg">
		<div class="indexbg2"></div>
	</div>
</div> 
<script>
$(function(){
	$('#headMenu',window.parent.document).hide();
	$('.indexbg').height($("#leftSide",window.parent.document).height());
});
</script>
</body>
</html>