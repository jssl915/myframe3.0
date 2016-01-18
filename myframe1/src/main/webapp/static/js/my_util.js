/**
 * 时间处理组件
 */
$.extend({
	/*
	 * 当前日期加上day天
	 */
	addDate:function(day){
	  var dd = new Date();
	  dd.setDate(dd.getDate()+day);
	  var y = dd.getFullYear();
	  var m = dd.getMonth()+1;//获取当前月份的日期
	  var d = dd.getDate();
	  return y+"-"+m+"-"+d;
	},
	/*
	 * 将时间戳转成年月日
	 */
	formatDateYMD:function(timestamp){
	   var format = 'yyyy-MM-dd';
	   var newDate = new Date();
	   newDate.setTime(timestamp);
	   var date = {
	              "M+": newDate.getMonth() + 1,
	              "d+": newDate.getDate(),
	              "h+": newDate.getHours(),
	              "m+": newDate.getMinutes(),
	              "s+": newDate.getSeconds(),
	              "q+": Math.floor((newDate.getMonth() + 3) / 3),
	              "S+": newDate.getMilliseconds()
	       };
	       if (/(y+)/i.test(format)) {
	              format = format.replace(RegExp.$1, (newDate.getFullYear() + '').substr(4 - RegExp.$1.length));
	       }
	       for (var k in date) {
	              if (new RegExp("(" + k + ")").test(format)) {
	                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
	                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
	              }
	       }
	  return format;
	},
	/*
	 * 将时间戳转成年月日时分秒
	 */
	formatDate:function(timestamp){
	   var format = 'yyyy-MM-dd h:m:s';
	   var newDate = new Date();
	   newDate.setTime(timestamp);
	   var date = {
	              "M+": newDate.getMonth() + 1,
	              "d+": newDate.getDate(),
	              "h+": newDate.getHours(),
	              "m+": newDate.getMinutes(),
	              "s+": newDate.getSeconds(),
	              "q+": Math.floor((newDate.getMonth() + 3) / 3),
	              "S+": newDate.getMilliseconds()
	       };
	       if (/(y+)/i.test(format)) {
	              format = format.replace(RegExp.$1, (newDate.getFullYear() + '').substr(4 - RegExp.$1.length));
	       }
	       for (var k in date) {
	           if (new RegExp("(" + k + ")").test(format)) {
	        	   	  var newdate = (date[k]+'').length==1?'0'+date[k]:date[k];	
	                  format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? newdate : ("00" + newdate).substr(("" + newdate).length));
	           }
	       }
	  return format;
	},
	/*
	 * 将2015-07-27 11:16:11 转成时间戳
	 */
	transdate:function(time){
	  var date=new Date();
	  date.setFullYear(endTime.substring(0,4));
	  date.setMonth(endTime.substring(5,7)-1);
	  date.setDate(endTime.substring(8,10));
	  date.setHours(endTime.substring(11,13));
	  date.setMinutes(endTime.substring(14,16));
	  date.setSeconds(endTime.substring(17,19));
	  return Date.parse(date);
	}
})
