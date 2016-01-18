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
        <input type="hidden" id="dictId" name="dictId" value="${sDict.dictId}">
        <table class="table-edit">
            <tr>
                <td><span class="required">*</span>字典名称</td>
                <td>
					<input type="text" id="dictName" name="dictName" value="${sDict.dictName}" maxlength="15">
                 	<div class="error-msg"></div>
                </td>
            </tr>
            <tr>
				<td>字典描述</td>
				<td colspan="3" style="line-height:25px;padding-top:10px;padding-bottom:10px;">
					<textarea name="dictDesc" id="dictDesc" maxlength="256">${sDict.dictDesc}</textarea>
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
        'dictName':{'required':true,'name':true}
    };
	validate(vData);
	$('#submitBtn').click(function(){
	    if(validateSubmit(vData)){
	    	$.ajaxApiResult('update',$('#form1').serialize(),function(d){
	    		$.myTip({content:"修改成功"});
	    		window.location.href="${ctx}/system/dict/init";
			}) 
        }
	});
});
</script>
</body>
</html>
