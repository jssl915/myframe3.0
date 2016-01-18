<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的后台管理系统</title>
</head>
<body>
<div class="header">
    <a class="logo" href="${ctx}/index">我的管理后台</a>
     <div class="role-navbar">
     	<div class="role-nav-person">
            <div class="role-nav-title">${sysUser.userName}</div>
            <i class="caret"></i>
            <div class="nav-person-dialog">
				<div id="exitPgc">退出</div>
            </div>
        </div>
    </div>
</div>
<div class="content">
    <div id="leftSide" class="left-side">
        <div class="sidebar">
            <div class="left-title">Hello,${sysUser.userName}</div>
            <ul id="leftMenu"></ul>
        </div>
    </div>
    <div class="right-side">
	    <div id="headMenu" class="content-header" style="display:none">
	    	<a>系统管理</a>><a>管理员管理</a>
	    </div>
        <iframe id="pRightFrame" src="${ctx}/index/init" name="pRightFrame" width="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" onLoad="iFrameHeight()"></iframe>
    </div>
</div>
<script>
var aMenuHtml = [];
$(function(){
    $('#exitPgc').click(function(){
    	 window.location.href="${ctx}/login";
    });
    $.ajaxApiResult('${ctx}/index/tree',null,function(d){
    	$('#leftMenu').menuTree(d);
    	var height1 = $('.sidebar').height();
    	var height2 = $(window).height()-54;
    	var height = height1>height2?height1:height2;
    	$('#leftSide').height(height);
    	$('#pRightFrame').css('min-height',height-60);
    });  
});
function changeMenu(url,text,ptext){
	aMenuHtml.length=0;
	if(url.substring(0,1)=='/'){
		if(url){
			if(ptext){
				aMenuHtml.push('<span>'+ptext+'</span>');
				aMenuHtml.push('<a onclick="changeMenu(\''+url+'\',\''+text+'\',\''+ptext+'\')">'+text+'</a>');
			}else{
				aMenuHtml.push('<span>'+text+'</span>');
			}
			$('#headMenu').html(aMenuHtml.join('>')).show();
		}
		$('#pRightFrame').attr('src',ctx+url);
	}
}
function changeUrl(url,num){
	aMenuHtml.length = num||aMenuHtml.length-1;
	$('#headMenu').html(aMenuHtml.join('>')).show();
	$('#pRightFrame').attr('src',ctx+url);
}
function iFrameHeight() {
    var ifm= document.getElementById("pRightFrame");
    var subWeb = document.frames ? document.frames["pRightFrame"].document : ifm.contentDocument;
    if(ifm != null && subWeb != null) {
        ifm.height = subWeb.body.scrollHeight+20;
        ifm.width = subWeb.body.scrollWidth;
    }
}
</script>
</body>
</html>