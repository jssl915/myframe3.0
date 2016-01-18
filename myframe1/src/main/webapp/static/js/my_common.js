/**
 * ajax接口
 **/
$.extend({'ajaxApiResult':function(url,data,successCallback,failureCallback){
	$('.loader',window.parent.document).show();
	$('.alert-fade-hide').show();
	$.ajax({
		   type: "POST",
		   url: url,
		   data: data,
		   dataType: "json",
		   success: function(d){
			   $('.loader',window.parent.document).hide();
			   $('.alert-fade-hide').hide();
			   successCallback && successCallback(d);
		   },
		   error:function(msg){
			   failureCallback && failureCallback(msg);
		   },
		});
	}
});
