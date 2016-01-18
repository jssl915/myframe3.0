<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="ctx"  value="<%= request.getContextPath() %>" />
<c:set  var="js"   value="${ctx}/static/js"/>
<c:set  var="css"  value="${ctx}/static/css"/>
<c:set  var="img"  value="${ctx}/static/images"/>
<script src="${js}/jquery-1.11.3.min.js"></script>
<script src="${js}/jquery.lazyload.js"></script>
<script>
ctx= '${ctx}';
</script>
<link rel="shortcut icon" href="${img}/wh_ico.ico"/>
<script src="${js}/plupload.full.min.js"></script>
<script src="${js}/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${js}/twbsPagination.js"></script>
<script type="text/javascript" src="${js}/laydate/laydate.js"></script>
<script src="${js}/areaData.js"></script>
<script src="${js}/areaSelect.js"></script>
<script type="text/javascript" src="${js}/my_ui.js"></script>
<script type="text/javascript" src="${js}/my_tree.js"></script>
<script src="${js}/my_validate.js"></script>
<script src="${js}/my_util.js"></script>
<script src="${js}/my_common.js"></script>

<link rel="stylesheet" type="text/css" href="${js}/scrollbar/jquery.mCustomScrollbar.min.css">
<link rel="stylesheet" type="text/css" href="${css}/pagination.css">
<link rel="stylesheet" type="text/css" href="${css}/my_ui.css"/>
<link rel="stylesheet" type="text/css" href="${css}/style.css" />

<div class="loader"></div>
<div class="alert-fade"></div>
<script>
$(function(){
	$('#alertRightFrame').mCustomScrollbar({theme:"minimal-dark"});//自定义滚动条
})
function addTitle(title,url,num){
	if(url){
		parent.aMenuHtml.push('<a onclick="changeUrl(\''+url+'\','+num+')">'+title+'</a>');
	}else{
		parent.aMenuHtml.push('<span>'+title+'</span>');
	}
	$('#headMenu',window.parent.document).html(parent.aMenuHtml.join('>')).show();
}
</script>


