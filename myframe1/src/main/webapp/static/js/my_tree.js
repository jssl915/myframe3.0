/**
 * 
 * tree组件
 * 
 * */
$(function(){
    $.fn.extend({
    	//下拉树
    	showMenuTree:function(data){
    		$(this).show();
    		var _this = this;
			var html = [];
    		html.push('<ul show="true"><li data-pid='+data[0].pid+' data-id='+data[0].id+' data-name='+data[0].text+' data-level='+data[0].level+'><div class="t-edit">'+data[0].text+' </div></li><ul show="true">');
    		for(var i=0;i<data[0].children.length;i++){
    			var oneChild = data[0].children[i];
    			html.push('<li data-pid='+oneChild.pid+' data-id='+oneChild.id+' data-name='+oneChild.text+' data-level='+oneChild.level+'><div class="t-edit">'+oneChild.text+'</div></li>');
    			if(oneChild.children && oneChild.children.length>0){
    				html.push('<ul show="true">');
    				for(var j=0;j<oneChild.children.length;j++){
    					var twoChild = oneChild.children[j];
    					html.push('<li data-pid='+twoChild.pid+' data-id='+twoChild.id+' data-name='+twoChild.text+' data-level='+twoChild.level+'><div class="t-edit">'+twoChild.text+'</div></li>');
    					if(twoChild.children && twoChild.children.length>0){
    						html.push('<ul show="true">');
    						for(var k=0;k<twoChild.children.length;k++){
    							var threeChild = twoChild.children[k];
    							html.push('<li data-pid='+threeChild.pid+' data-id='+threeChild.id+' data-name='+threeChild.text+' data-level='+threeChild.level+'><div class="t-edit">'+threeChild.text+'</div></li>');
    						}
    						html.push('</ul>');
    					}
    					
    				}
    				html.push('</ul>');
    			}
    		}
    		html.push('</ul>');
    		$(_this).html(html.join(''));
    		$(_this).mCustomScrollbar({theme:"minimal-dark"});//自定义滚动条
    		$(_this).SimpleTree();
    		$(_this).find('div.t-edit').click(function(){
    			var id = $(this).parent().data('id');
    			var name= $(this).parent().data('name');
    			if($(this).parent().hasClass('folder')){
    				parent.$.dialogAlert({
    	  		         title:"提示",
    	  		         content:'只能选择子节点！'
    	  		     });
    				return false;
    			}
    			$(_this).empty();
    			$(_this).hide();
    			chooseParent(name,id);
    			return false;
    		});
    		$('body').click(function(){
    			$(_this).empty();
    			$(_this).hide();
    		});
    	},
    	//列表树
    	showMenuListCheckTree:function(data,aRole){
    		var _this = this;
			var html = [];
    		html.push('<ul show="true"><li><div class="t-edit"><span class="self-checkbox checkbox0"  data-pid='+data[0].pid+' data-id='+data[0].id+' data-name='+data[0].text+' data-level='+data[0].level+'></span>'+data[0].text+' </div></li><ul show="true">');
    		for(var i=0;i<data[0].children.length;i++){
    			var oneChild = data[0].children[i];
    			html.push('<li><div class="t-edit"><span class="self-checkbox checkbox0"  data-pid='+oneChild.pid+' data-id='+oneChild.id+' data-name='+oneChild.text+' data-level='+oneChild.level+'></span>'+oneChild.text+'</div></li>');
    			if(oneChild.children && oneChild.children.length>0){
    				html.push('<ul show="true">');
    				for(var j=0;j<oneChild.children.length;j++){
    					var twoChild = oneChild.children[j];
    					html.push('<li><div class="t-edit"><span class="self-checkbox checkbox0"  data-pid='+twoChild.pid+' data-id='+twoChild.id+' data-name='+twoChild.text+' data-level='+twoChild.level+'></span>'+twoChild.text+'</div></li>');
    					if(twoChild.children && twoChild.children.length>0){
    						html.push('<ul show="true">');
    						for(var k=0;k<twoChild.children.length;k++){
    							var threeChild = twoChild.children[k];
    							html.push('<li><div class="t-edit"><span class="self-checkbox checkbox0"  data-pid='+threeChild.pid+' data-id='+threeChild.id+' data-name='+threeChild.text+' data-level='+threeChild.level+'></span>'+threeChild.text+'</div></li>');
    						}
    						html.push('</ul>');
    					}
    					
    				}
    				html.push('</ul>');
    			}
    		}
    		html.push('</ul>');
    		$(_this).html(html.join(''));
    		$(_this).mCustomScrollbar({theme:"minimal-dark"});//自定义滚动条
    		$(_this).SimpleTree();
    		//判断已经选中的
    		if(aRole){
	    		for(var i=0;i<aRole.length;i++){
	    			var id = aRole[i].menuId;
	    			var oCheckboxs = $('span[data-id="'+id+'"]');
	    			oCheckboxs.attr('class','self-checkbox checkbox1');
	    		}
    		}
    		$(_this).find('.self-checkbox').click(function(event){
    			var id = $(this).data('id');
    			var oCheckboxs = $('span[data-pid="'+id+'"]');
    			var pid = $(this).data('pid');
    			var pCheckbox = $('span[data-id="'+pid+'"]');
    			var flag = $(this).hasClass('checkbox0');
    			if(flag){
    				//如果存在子节点，则子节点随着变化
    				$(this).attr('class','self-checkbox checkbox1');
    				oCheckboxs.attr('class','self-checkbox checkbox1');
    				for(var i=0;i<oCheckboxs.length;i++){
        				var id1 = $(oCheckboxs[i]).data('id');
        				var oCheckboxs1 = $('span[data-pid="'+id1+'"]');
        				oCheckboxs1.attr('class','self-checkbox checkbox1');
        				for(var j=0;j<oCheckboxs1.length;j++){
        					var id2 = $(oCheckboxs1[i]).data('id');
            				var oCheckboxs2 = $('span[data-pid="'+id2+'"]');
            				oCheckboxs2.attr('class','self-checkbox checkbox1');
        				}
        			}
    				//如果存在父节点，则父节点需要选中
    				pCheckbox.attr('class','self-checkbox checkbox1');
    				for(var i=0;i<pCheckbox.length;i++){
        				var pid1 = $(pCheckbox[i]).data('pid');
        				var pCheckboxs1 = $('span[data-id="'+pid1+'"]');
        				pCheckboxs1.attr('class','self-checkbox checkbox1');
        				for(var j=0;j<pCheckboxs1.length;j++){
        					var pid2 = $(pCheckboxs1[i]).data('pid');
            				var pCheckboxs2 = $('span[data-id="'+pid2+'"]');
            				pCheckboxs2.attr('class','self-checkbox checkbox1');
        				}
        			}
    			}else{
    				$(this).attr('class','self-checkbox checkbox0');
    				oCheckboxs.attr('class','self-checkbox checkbox0');
    				for(var i=0;i<oCheckboxs.length;i++){
    					var id1 = $(oCheckboxs[i]).data('id');
        				var oCheckboxs1 = $('span[data-pid="'+id1+'"]');
        				oCheckboxs1.attr('class','self-checkbox checkbox0');
        				for(var j=0;j<oCheckboxs1.length;j++){
        					var id2 = $(oCheckboxs1[i]).data('id');
            				var oCheckboxs2 = $('span[data-pid="'+id2+'"]');
            				oCheckboxs2.attr('class','self-checkbox checkbox0');
        				}
        			}
    			}
    			event.stopPropagation();
    		});
    	},
    	//列表树
    	showMenuListTree:function(data){
    		var _this = this;
			var html = [];
    		html.push('<ul show="true"><li data-pid='+data[0].pid+' data-id='+data[0].id+' data-name='+data[0].text+' data-level='+data[0].level+'><div class="t-edit">'+data[0].text+' </div></li><ul show="true">');
    		for(var i=0;i<data[0].children.length;i++){
    			var oneChild = data[0].children[i];
    			html.push('<li data-pid='+oneChild.pid+' data-id='+oneChild.id+' data-name='+oneChild.text+' data-level='+oneChild.level+'><div class="t-edit">'+oneChild.text+'</div></li>');
    			if(oneChild.children && oneChild.children.length>0){
    				html.push('<ul show="true">');
    				for(var j=0;j<oneChild.children.length;j++){
    					var twoChild = oneChild.children[j];
    					html.push('<li data-pid='+twoChild.pid+' data-id='+twoChild.id+' data-name='+twoChild.text+' data-level='+twoChild.level+'><div class="t-edit">'+twoChild.text+'</div></li>');
    					if(twoChild.children && twoChild.children.length>0){
    						html.push('<ul show="true">');
    						for(var k=0;k<twoChild.children.length;k++){
    							var threeChild = twoChild.children[k];
    							html.push('<li data-pid='+threeChild.pid+' data-id='+threeChild.id+' data-name='+threeChild.text+' data-level='+threeChild.level+'><div class="t-edit">'+threeChild.text+'</div></li>');
    						}
    						html.push('</ul>');
    					}
    					
    				}
    				html.push('</ul>');
    			}
    		}
    		html.push('</ul>');
    		$(_this).html(html.join(''));
    		$(_this).mCustomScrollbar({theme:"minimal-dark"});//自定义滚动条
    		$(_this).SimpleTree();
    		$(_this).find('div.t-edit').click(function(){
    			var id = $(this).parent().data('id');
    			var name= $(this).parent().data('name');
    			chooseParent(name,id);
    			$("#leftTree li").removeClass('selected');
    			$(this).parent().addClass('selected');
    			return false;
    		});
    	},
    	//编辑树
    	loadEditMenuTree:function(data){
    		var html = [];
    		html.push('<ul show="true"><li data-pid='+data[0].pid+' data-id='+data[0].id+' data-name='+data[0].text+' data-level='+data[0].level+'><span class="t-edit">'+data[0].text+' </span><span class="t-add">新增 </span></li><ul show="true">');
    		for(var i=0;i<data[0].children.length;i++){
    			var oneChild = data[0].children[i];
    			html.push('<li data-pid='+oneChild.pid+' data-id='+oneChild.id+' data-name='+oneChild.text+' data-level='+oneChild.level+'><span class="t-edit">'+oneChild.text+'</span> <span class="t-add">新增 </span><span class="t-del">删除</span></li>');
    			if(oneChild.children && oneChild.children.length>0){
    				html.push('<ul show="true">');
    				for(var j=0;j<oneChild.children.length;j++){
    					var twoChild = oneChild.children[j];
    					html.push('<li data-pid='+twoChild.pid+' data-id='+twoChild.id+' data-name='+twoChild.text+' data-level='+twoChild.level+'><span class="t-edit">'+twoChild.text+'</span> <span class="t-add">新增 </span><span class="t-del">删除</span></li>');
    					if(twoChild.children && twoChild.children.length>0){
    						html.push('<ul show="true">');
    						for(var k=0;k<twoChild.children.length;k++){
    							var threeChild = twoChild.children[k];
    							html.push('<li data-pid='+threeChild.pid+' data-id='+threeChild.id+' data-name='+threeChild.text+' data-level='+threeChild.level+'><span class="t-edit">'+threeChild.text+'</span> <span class="t-del">删除</span></li>');
    						}
    						html.push('</ul>');
    					}
    					
    				}
    				html.push('</ul>');
    			}
    		}
    		html.push('</ul>');
    		$(this).html(html.join(''));
    		$(this).SimpleTree();
    		$(this).mCustomScrollbar({theme:"minimal-dark"});//自定义滚动条
    	},
        SimpleTree:function(options){
            //初始化参数
            var option = $.extend({click:function(a){}},options);
            option.tree=this;	/* 在参数对象中添加对当前菜单树的引用，以便在对象中使用该菜单树 */
            option._init=function(){
                /*
                 * 初始化菜单展开状态，以及分叉节点的样式
                 */
                this.tree.find("ul").hide();	/* 隐藏所有子级菜单 */
                this.tree.find("ul").prev("li").removeClass("open");	/* 移除所有子级菜单父节点的 open 样式 */
                this.tree.find("ul[show='true']").show();	/* 显示 show 属性为 true 的子级菜单 */
                this.tree.find("ul[show='true']").prev("li").addClass("open");	/* 添加 show 属性为 true 的子级菜单父节点的 open 样式 */
                
            }/* option._init() End */
            /* 设置所有超链接不响应单击事件 */
            this.find("a").click(function(){$(this).parents("li").click(); return false; });
            /* 菜单项 <li> 接受单击 */
            this.find("li").click(function(){
            	option.click(this);
                /*
                 * 如果当前节点下面包含子菜单，并且其 show 属性的值为 true，则修改其 show 属性为 false
                 * 否则修改其 show 属性为 true
                 */
                if($(this).next("ul").attr("show")=="true"){
                    $(this).next("ul").attr("show","false");
                }else{
                    $(this).next("ul").attr("show","true");
                }

                /* 初始化菜单 */
                option._init();
            });
            this.find("li").hover(
                function(){$(this).addClass("hover")},
                function(){$(this).removeClass("hover")}
            );
            /* 设置所有父节点样式 */
            this.find("ul").prev("li").addClass("folder");
            /* 初始化菜单 */
            option._init();
        }
    });
});

