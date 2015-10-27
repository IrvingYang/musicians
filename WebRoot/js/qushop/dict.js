var dict={
		
		getBrand_vendorByType:function(type,typeId,maxCount,divId){
			$.ajax({
				url:common.getRootPath()+'/dict/brand_vendor/getBrand_vendorByType.action',
				type:"GET",
				async: false,
				data:{type:type,typeId:typeId,maxCount:maxCount},
				dataType:'text',
				success:function(d){
					eval("var d1 = "+d);
					var data = d1.brand_vendorsList;
					$.each(data,function(index,brand){
						$("#"+divId).append('<a href="'+(brand.url==null?'javascript:;':"http://"+brand.url)+'" class="d_block t_align_c animate_fade"><img src="'+brand.imagepath+'" alt=""></a>');
					});
				},
				error:function(){
					alert("所有品牌查询超时");
				}
			});
		},
		
		getAllState : function(){
			$.ajax({
				url:common.getRootPath()+'/dict/state/getAllState.action',
				type:"GET",
				async: false,
				dataType:'json',
				success:function(data){
					$("#province").children("option:gt(0)").remove();
					$.each(data,function(index,state){
						$("#province").append('<option value="'+state.stateId+'">'+state.stateName+'</option>');
					});
				},
				error:function(){
					alert("所有品牌查询超时");
				}
			});
			
		},
		getCityByStateId : function(stateId){
			if(stateId=='00'){
				return;
			}
			$.ajax({
				url:common.getRootPath()+'/dict/city/getCityByStateId.action',
				type:"POST",
				async: false,
				dataType:'json',
				data:{stateId:stateId},
				success:function(data){
					$("#city").children("option:gt(0)").remove();
					$.each(data,function(index,city){
						$("#city").append('<option value="'+city.cityId+'">'+city.cityName+'</option>');
					});
				},
				error:function(){
					alert("根据省份查询城市查询超时");
				}
			});
		},
		getDistrictByCityId : function(cityId){
			if(cityId=='00'){
				return;
			}
			$("#cityId").val(cityId);
			$.ajax({
				url:common.getRootPath()+'/dict/district/getDistrictByCityId.action',
				type:"POST",
				async: false,
				dataType:'json',
				data:{cityId:cityId},
				success:function(data){
					$("#district").children("option:gt(0)").remove();
					$.each(data,function(index,district){
						$("#district").prev("ul").append("<li value='"+district.districtId+"' class='tr_delay_hover' onclick='dict.districtValue(\""+district.districtId+"\")' >"+district.districtName+"</li>");
						$("#district").append('<option value="'+district.districtId+'">'+district.districtName+'</option>');
					});
				},
				error:function(){
					alert("根据省份查询城市查询超时");
				}
			});
		},
		districtValue : function(districtId){
			$("#districtId").val(districtId);
		},
		programaDetail : function(programaTypeId){
			$.ajax({
				url:common.getRootPath()+'/common/programa/getProgramaByProgramaId.action',
				type:"POST",
				async: false,
				dataType:'json',
				data:{programaTypeId:programaTypeId},
				success:function(data){
					$("#blogs").html(data.programaTitle);
					$("#title").html(data.programaTitle);
					$("#content").html(data.programaContent);
				},
				error:function(){
				}
			});
		}
		
};