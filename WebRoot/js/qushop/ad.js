/**
 * ad
 */

var ad={
		
	getAdByadarea:function(typeId,type,maxCount,divId){
		
		$.ajax({
			url:common.getRootPath()+"/ad/getAdByOp_area.action",
			type:"GET",
			async: true,
			dataType:'json',
			data:{type:type,typeId:typeId,maxCount:maxCount},
			success:function(data){
				
				if(typeId=='0101' || typeId=='1801' || typeId=='2001'){
					$.each(data,function(index,object){
						$("#"+divId).append('<li><a href="'+object.adlink+'">'+
								'<img src="'+object.ad_imagesList[0].imagepath+'" height="450" alt="" data-custom-thumb="'+object.ad_imagesList[0].imagepath+'"></a></li>');
					});
				}
				else if(typeId=="0102" || typeId=="0103"){
					$.each(data,function(index,object){
						var html = 
						'<div class="col-lg-6 col-md-6 col-sm-6 m_bottom_50 m_sm_bottom_20">'+
						'<a href="'+object.adlink+'" class="d_block banner animate_ftr wrapper r_corners relative m_xs_bottom_30">'+
						'<img src="'+object.ad_imagesList[0].imagepath+'" alt=""> <span class="banner_caption d_block vc_child t_align_c w_sm_auto">'+				
						'</span></span>	</a></div>';
						$("#"+divId).append(html);
					});
				}
				else if(typeId=='0104' || typeId=='0105' || typeId=='1201' || typeId=='1501' || typeId=='0701' || typeId=='0702' || typeId=='0801'|| typeId=='1001' || typeId=='1002' || typeId=='0501' || typeId=='0502'){
					$.each(data,function(index,object){
						var html = '<a href="'+object.adlink+'" class="widget animate_ftr d_block r_corners m_bottom_30" >'+
						'<img src="'+object.ad_imagesList[0].imagepath+'" alt=""></a>';
						if(typeId=='0105' || typeId=='0104'){
							$("#"+divId).append(html);
						}else{
							if(typeId=='0701' || typeId=='0801' || typeId=='1001' || typeId=='0501'){
								$("#"+divId).before(html);
							}
							else if(typeId=='0702' || typeId=='1002' || typeId=='0502'){
								$("#"+divId).after(html);
							}else{
								$("#"+divId).prepend(html);
//								$("#"+divId).append(html);
							}
						}
					});
				}
				else if(typeId=='0601'){
					$("#"+divId).children("img").remove();
					$.each(data,function(index,object){
						var html ='<a href="" ><img class="r_corners m_left_50 m_bottom_20" src="'+object.ad_imagesList[0].imagepath+'" alt=""></a>';
						$("#"+divId).append(html);
					});
				}
				else if(typeId=='0602' || typeId=='0603'){
					$.each(data,function(index,object){
						var html = '<a href="#" class="widget animate_ftr d_block r_corners m_bottom_30" >'+
						'<img src="'+object.ad_imagesList[0].imagepath+'" alt=""></a>';
						if(typeId=='0603'){
							$("#"+divId).append(html);
						}else{
							$("#"+divId).prepend(html);
						}
					});
				}
			},
			error:function(data){
				alert("服务器无响应");
			}
		});
		
	}
	
};