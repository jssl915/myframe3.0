/**
 * 
 * list组件
 * 
 * */
$.fn.extend({
	loadPageNum:function(){
		var id = $(this).attr('id');
		var oDivPageId = id+'_page';
		var oUlPageId = id+'_page_ul';
		$('#'+oDivPageId).remove();
		$(this).append('<div id="'+oDivPageId+'"><ul id="'+oUlPageId+'" class="pagination-sm"></ul></div>');
		return oUlPageId;
	},
	loadList:function(obj){
		var oDom = $(this);
		var id = $(this).attr("id");
		var currentPage=1;//当前页
		var pageSize=10;//每页显示的条数
		var totalPages = 1;//总页数
		oDom.empty();
		var pageId = oDom.loadPageNum();//加载分页html
		getList();
		function getList(){
			obj.data.pageNum = currentPage;
			$('#'+id+'_table').remove();
			//如果没有设置pageSize,则默认为10
		    if(!obj.data.pageSize){obj.data.pageSize = pageSize}
			$.ajaxApiResult(obj.url,obj.data,function(data){
			   var list = data.rows||data.list;//json数据
			   var pageTotal = data.total;//总条数
			   if(pageTotal==0){oDom.html('');return}
			   pageSize = obj.data.pageSize;//每页显示的条数
			   currentPage = obj.data.pageNum;
			   totalPages = Math.floor(pageTotal%pageSize==0?pageTotal/pageSize:pageTotal/pageSize+1);//总页数
			   var column = obj.column;
			   //拼head部分
			   var aHtml = [];
			   aHtml.push('<table id="'+id+'_table" class="table-list"><caption>'+obj.tableName+'</caption><thead><tr>');
			   for(var i=0;i<column.length;i++){
				   var title = column[i] && column[i].title;
				   if(column[i] && column[i].width){
					   aHtml.push('<th style="width:'+column[i].width+'px">'+title+'</th>');
				   }else{
					   aHtml.push('<th>'+title+'</th>');
				   }
			   }
			   aHtml.push('</tr></thead><tbody id="'+id+'_tablelist"></tbody></table>');
			   oDom.prepend(aHtml.join(''));
			   var tHtml = [];
			   for(var i=0;i<list.length;i++){
				   var d = list[i];
				   var num=(currentPage-1)*pageSize+i+1;
				   tHtml.push('<tr>');
				   for(var j=0;j<column.length;j++){
					   var columnTitle = column[j] && column[j].title;
					   var columnValue='';
					   var columnName = column[j] && column[j].name;
					   if(d[columnName]||d[columnName]=='0'){columnValue=d[columnName]}
					   var returnBack = column[j] && column[j].returnBack;
					   if(returnBack){
						   if(columnTitle=='操作'){
							   tHtml.push('<td style="color:#c7254e">'+returnBack(d)+'</td>');
						   }else{
							   tHtml.push('<td>'+returnBack(d)+'</td>');
						   }
						   continue
					   }
					   if(columnTitle=='序号'){
						   tHtml.push('<td>'+num+'</td>');
					   }else if(columnTitle=='操作'){
						   tHtml.push('<td></td>');
					   }else{
						   tHtml.push('<td>'+columnValue+'</td>');
					   }
				   }
				   tHtml.push('</tr>');
			   }
			   $('#'+id+'_tablelist').html(tHtml.join(''));
			   
			   $('#'+pageId).twbsPagination({
			        totalPages: totalPages,
			        visiblePages: 10, //显示的页数
			        current_page:currentPage,
			        onPageClick: function(event, page) {
			        	currentPage = page;
			        	getList();
			        }
			   }); 
			   parent.iFrameHeight();
		   }); 
		}
	}
});

/*
 * 
 * 弹出框，确认框组件
 * 
 * */
$.extend({
	 'myAlert':function(obj){
		 parent.$.dialogAlert(obj);
	 },
	 'myConfirm':function(obj){
		 parent.$.dialogConfirm(obj);
	 },
	 'myTip':function(obj){
		 parent.$.dialogTip(obj);
	 },
	 'dialogAlert':function(obj){
        var title="温馨提示";
        var content = "该字段不能为空！";
        if(obj){
        	obj.title && (title=obj.title);
        	obj.content && (content=obj.content);
        }
        //创建弹出层遮罩
        var oDivFade = $('<div class="dialog-fade-hide"></div>');
        $('body').append(oDivFade);
        var aHtml = [];
        aHtml.push('<div class="dialog-div">');
        aHtml.push('<div class="dialog-header">'+title+'</div>');
        aHtml.push('<div class="dialog-center">'+content+'</div>');
        aHtml.push('<div class="dialog-footer"><button id="dialogCloseBtn" class="dialog-btn close">关闭</button></div></div>');
        $('body').append(aHtml.join(''));
        //动画效果
        $('.dialog-fade-hide').fadeIn('fast',function(){
            $('.dialog-div').animate({top:'50%',opacity:1},'fast');
        });
        //取消
        $('#dialogCloseBtn').click(function(){
            $('.dialog-div').animate({top:-250,opacity:0},'fast',function(){
                oDivFade.remove();
                $('.dialog-div').remove();
            });
        });
        //点遮罩取消
        $('.dialog-fade-hide').click(function(){
            $('.dialog-div').animate({top:-250,opacity:0},'fast',function(){
                oDivFade.remove();
                $('.dialog-div').remove();
            });
        });
    },
    'dialogConfirm':function(obj){
        var title="温馨提示";
        var content = "确定要删除吗！";
        if(obj){
        	obj.title && (title=obj.title);
        	obj.content && (content=obj.content);
        }
        //创建弹出层遮罩
        var oDivFade = $('<div class="dialog-fade-hide"></div>');
        $('body').append(oDivFade);
        var aHtml = [];
        aHtml.push('<div class="dialog-div">');
        aHtml.push('<div class="dialog-header">'+title+'</div>');
        aHtml.push('<div class="dialog-center">'+content+'</div>');
        aHtml.push('<div class="dialog-footer"><button id="dialogConfirmBtn" class="dialog-btn submit">确定</button>');
        aHtml.push('<button id="dialogCancelBtn" class="dialog-btn cancel">取消</button></div></div>');
        $('body').append(aHtml.join(''));
        //动画效果
        $('.dialog-fade-hide').fadeIn('fast',function(){
            $('.dialog-div').animate({top:'50%',opacity:1},'fast');
        });
        //确定
        $('#dialogConfirmBtn').click(function(){
            $('.dialog-div').animate({top:-250,opacity:0},'fast',function(){
                oDivFade.remove();
                $('.dialog-div').remove();
                obj.onSubmit && obj.onSubmit();
            });

        });
        //取消
        $('#dialogCancelBtn').click(function(){
            $('.dialog-div').animate({top:-250,opacity:0},'fast',function(){
                oDivFade.remove();
                $('.dialog-div').remove();
                obj.onCancel && obj.onCancel();
            });
        });
        //点遮罩取消
        $('.dialog-fade-hide').click(function(){
            $('.dialog-div').animate({top:-250,opacity:0},'fast',function(){
                oDivFade.remove();
                $('.dialog-div').remove();
            });
        });
    },
    'dialogTip':function(obj){
        var title="温馨提示";
        var content = "删除成功！";
        if(obj){
        	obj.title && (title=obj.title);
        	obj.content && (content=obj.content);
        }
        //创建弹出层遮罩
        var aHtml = [];
        aHtml.push('<div class="dialog-tip">');
        aHtml.push('<div class="dialog-header">'+title+'</div>');
        aHtml.push('<div class="dialog-center">'+content+'</div>');
        aHtml.push('<div class="dialog-footer" style="padding:10px 0;"><button id="dialogCloseBtn" class="dialog-btn close">关闭</button></div></div>');
        $('body').append(aHtml.join(''));
        //动画效果
        $('.dialog-tip').animate({bottom:'5px',opacity:1},'slow',function(){
        	setTimeout(function(){
        		$('.dialog-tip').animate({bottom:'-400px',opacity:0},'normal',function(){
                    $('.dialog-tip').remove();
                });
        	},1000);
        });
        //取消
        $('#dialogCloseBtn').click(function(){
            $('.dialog-tip').animate({bottom:'-400px',opacity:0},'normal',function(){
                $('.dialog-tip').remove();
            });
        });
    },
});
/**
 * 
 * 菜单树组件
 * 
 * */
$.fn.extend({
	menuTree:function(aMenu){
		var menuData = aMenu[0].children;
		var aHtml = [];
	    for(var i=0;i<menuData.length;i++){
	        var d = menuData[i];
	        if(d.menuUrl) {
	            aHtml.push('<li class="nav-header" data-url="' + d.menuUrl + '" data-text="'+d.text+'">');
	        }else{
	            aHtml.push('<li class="nav-header">');
	        }
	        aHtml.push('<span class="menu-title">'+ d.text+'</span>');
	        var menu = d.children;
	        if(menu&& menu.length>0){
	            aHtml.push('<span class="icon icon1"></span>');
	            aHtml.push('<ul class="nav-list">');
	            for(var j=0;j< menu.length;j++){
	                var d1 = menu[j];
	                if(j == menu.length-1){
	                    aHtml.push('<li class="nav-menu" data-ptext="'+d.text+'" data-url="'+d1.menuUrl+'" data-text="'+d1.text+'" style="border-bottom:1px solid #c8c8cb;">');
	                }else{
	                    aHtml.push('<li class="nav-menu" data-ptext="'+d.text+'" data-url="'+d1.menuUrl+'" data-text="'+d1.text+'" >');
	                }
	                aHtml.push('<span class="icon3"></span>');
	                aHtml.push('<span class="menu-title2">'+d1.text+'</span>');
	                aHtml.push('</li>');
	            }
	            aHtml.push('</ul>');
	        }
	        aHtml.push('</li>');
	    }
	    $(this).html(aHtml.join(''));
        $('.icon').click(function(){
            var oParent = $(this).parent();
            var oUl = oParent.find('.nav-list');
            var oSpan = oParent.find('.icon');
            if(oUl.is(':hidden')){
                oUl.slideDown();
                oSpan.attr('class','icon icon1');
            }else{
                oUl.slideUp();
                oSpan.attr('class','icon icon2');
            }
        });
	    $('.menu-title').click(function(){
	        var oParent = $(this).parent();
	        var oUl = oParent.find('.nav-list');
	        var oSpan = oParent.find('.icon');
	        if(oUl.is(':hidden')){
	            oUl.slideDown();
	            oSpan.attr('class','icon icon1');
	        }else{
	            oUl.slideUp();
	            oSpan.attr('class','icon icon2');
	        }
	    });
	    $('.nav-header').click(function(){
	        var url = $(this).data('url');
	        if(url){changeMenu($(this).data('url'),$(this).data('text'));}
	    });
	    $('.nav-menu').click(function(){
	        $('.nav-menu').removeClass('active');
	        $(this).addClass('active');
	        changeMenu($(this).data('url'),$(this).data('text'),$(this).data('ptext'));
	        return false
	    });
	}
});