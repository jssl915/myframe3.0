/**
 * 省市区组件
 */
$.extend({
	//根据districtId取cityId
	getCityIdByDistrictId:function(districtId){
		var cityId='';
		for(var i=0;i<aArea.length;i++){
			var d = aArea[i];
			if(districtId==d.a){
				cityId = d.b;
			}
		}
		return cityId;
	},
	//根据districtId取proviceId
	getProviceIdByDistrictId:function(districtId){
		var proviceId='',cityId=$.getCityIdByDistrictId(districtId);
		for(var i=0;i<aArea.length;i++){
			var d = aArea[i];
			if(cityId==d.a){
				proviceId = d.b;
			}
		}
		return proviceId
	},
	//根据districtId取proviceId
	getProviceIdByCityId:function(cityId){
		var proviceId='';
		for(var i=0;i<aArea.length;i++){
			var d = aArea[i];
			if(cityId==d.a){
				proviceId = d.b;
			}
		}
		return proviceId
	},
	//根据areaCode取到areaName
    areaGetName:function(d){
        var d1 = $.getProviceIdByDistrictId(d);
        var d2 = $.getCityIdByDistrictId(d);
        var aAreaName=[];
        for(var i=0;i<aArea.length;i++){
            var area = aArea[i];
            if(d1==area.a){
                aAreaName[0]=area.c;
            }
            if(d2==area.a){
                aAreaName[1]=area.c;
            }
            if(d==area.a){
                aAreaName[2]=area.c;
            }
        }
        if(aAreaName[2]==aAreaName[1]){
        	aAreaName.length = 2;
        }
        if(aAreaName[1]==aAreaName[0]){
        	aAreaName.length = 1;
        }
        return aAreaName;
    },
    //根据cityId取所有的districtId
    getDistrictIdsByCityId:function(cityId){
    	var aDistrictId = [];
    	for(var i=0;i<aArea.length;i++){
    		var area = aArea[i];
    		if(cityId==area.b){
    			aDistrictId.push(area.a);
    		}
    	}
    	return aDistrictId.join(',');
    },
    //根据districtids来取省市区下拉
    districtSelect:function(data){
    	//存对象
    	var tempProvice = [];
    	var tempCity = [];
    	var tempDistrict = [];
    	//只存字符串
    	var aDistricts = data.districtIds.split(',');
    	var aCitys = [];
    	var aProvices = [];
    	aDistricts.length = aDistricts.length-1;
    	aDistricts=aDistricts.unique2();
    	for(var i =0;i<aDistricts.length;i++){
    		var districtId = aDistricts[i];
    		var cityId = $.getCityIdByDistrictId(districtId);
    		aCitys.push(cityId);
    	}
    	aCitys=aCitys.unique2();
    	for(var i=0;i<aCitys.length;i++){
    		var cityId = aCitys[i];
    		var proviceId = $.getProviceIdByCityId(cityId);
    		aProvices.push(proviceId);
    	}
    	aProvices=aProvices.unique2();
    	for(var i=0;i<aArea.length;i++){
    		var area = aArea[i];
    		for(var a=0;a<aDistricts.length;a++){
    			if(aDistricts[a]==area.a){
    				tempDistrict.push(area);
        		}
    		}
    		for(var b=0;b<aCitys.length;b++){
    			if(aCitys[b]==area.a){
    				tempCity.push(area);
        		}
    		}
    		for(var c=0;c<aProvices.length;c++){
    			if(aProvices[c]==area.a){
    				tempProvice.push(area);
    			}
    		}
    	}
    	
    	var aId = data.aId;
		var oProvice = $('#'+aId[0]);
		var oCity = $('#'+aId[1]);
		var oDistrict = $('#'+aId[2]);
		
    	initArray();
		function initArray(){
			for(var i=0;i<tempProvice.length;i++){
				var d = tempProvice[i];
				oProvice.append('<option value="'+d.a+'">'+d.c+'</option>');
			}
			if(data.addressCode!=undefined){
				 oProvice.val($.getProviceIdByDistrictId(data.addressCode));
			}else{
				oProvice.val('');
			}
			initCity();
		}
		
		function initCity(city){
			oCity.empty();
			for(var i=0;i<tempCity.length;i++){	
				var d = tempCity[i];
				d.b == oProvice.val() && (oCity.append('<option value="'+d.a+'">'+d.c+'</option>'));	
			}
			if(data.addressCode!=undefined){
				oCity.val($.getCityIdByDistrictId(data.addressCode));
			}else{
				 oCity.val('');
			}
			initDistrict();
		}
		
		function initDistrict(){
			oDistrict.empty(); 
			for(var i=0;i<tempDistrict.length;i++){
				var d = tempDistrict[i];
				d.b==oCity.val() && oDistrict.append('<option value="'+d.a+'">'+d.c+'</option>');			
			}
			if(data.addressCode!=undefined){
				oDistrict.val(data.addressCode);
			}else{
				oDistrict.val('');
			}
		}

		//绑定选择方法
		oProvice.change(function(){
			initCity();		
			initDistrict();
		});
		oCity.change(function(){
			initDistrict();					   
		})	
    },
 	areaSelect:function(data){
		var aId = data.aId;
		var oProvice = $('#'+aId[0]);
		var oCity = $('#'+aId[1]);
		var oDistrict = $('#'+aId[2]);
		//生成数组
		var aCity=[],aDistrict=[];
		initArray();
		function initArray(){
			for(var i=0;i<aArea.length;i++){
				var area = aArea[i];
				area.d==1 && oProvice.append('<option value="'+area.a+'">'+area.c+'</option>');
				area.d==2 && aCity.push(area);
				area.d==3 && aDistrict.push(area);
			}
            oProvice.val('');
			initCity();
		}
		if(data.addressCode!=undefined){
			var addressCode = data.addressCode+'';
			var d1 = $.getProviceIdByDistrictId(addressCode);
	        var d2 = $.getCityIdByDistrictId(addressCode);
	        oProvice.val(d1);
	        initCity(d2);
			initDistrict(addressCode);
		}
		
		function initCity(city){
			oCity.empty();
			var flag = false; 
			for(var i=0;i<aCity.length;i++){	
				var d = aCity[i];
				d.b == oProvice.val() && oCity.append('<option value="'+d.a+'">'+d.c+'</option>');	
				if(city&&d.a==city){
					flag = true;
				}
			}
            flag?oCity.val(city):oCity.val(data.addressCode);
		}
		
		function initDistrict(district){
			oDistrict.empty(); 
			for(var i=0;i<aDistrict.length;i++){
				var d = aDistrict[i];
				d.b==oCity.val() && oDistrict.append('<option value="'+d.a+'">'+d.c+'</option>');			
			}
			oDistrict.val(district);
		}

		//绑定选择方法
		oProvice.change(function(){
			initCity();		
			initDistrict();
		});
		oCity.change(function(){
			initDistrict();					   
		})	
	}	
});
Array.prototype.unique2 = function() {
	this.sort(); //先排序
	var res = [ this[0] ];
	for (var i = 1; i < this.length; i++) {
		if (this[i] !== res[res.length - 1]) {
			res.push(this[i]);
		}
	}
	return res;
}
