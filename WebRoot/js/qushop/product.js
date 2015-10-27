/**
 * 首页js
 */
$(function(){
//	products.indexProduct('recommend');
})
var products={
		
	indexProduct: function(type){
		var nowDate = new Date().getTime();
		var url = common.getRootPath()+'/eshop/';
		if(type=='new'){
			url+='product_ext_new/getProduct_ext_new.action';
		}
		else if(type=='recommend'){
			url+='product_ext_recommProd/getRecommProduct.action';
		}
		
		$.ajax({
			url:url,
			type:"GET",
			async: false,
			dataType:'text',
			success:function(data){
				eval("var d = "+ data);
				$.each(d,function(index,object){
					var imagepath = object.product_ext_shop.product.productimglist[0].path;
					var promoteImg='';
					var nowDate = new Date().getTime();
					if(object.product_ext_shop.promoteflag==1&& nowDate-object.product_ext_shop.promoteStartTime>0 && object.product_ext_shop.promoteEndTime-nowDate>0){
						promoteImg = '<img src="images/sale_product.png" alt="">';
					}else if(common.isHot(object.productId)){
						promoteImg = '<img src="images/hot_product.png" alt="">';
					}
					var html = 
						'<div class="product_item hit w_xs_full"><figure class="r_corners photoframe animate_ftb type_2 t_align_c tr_all_hover shadow relative">'+
						'<a href="product_page.html?productId='+object.productId+'&productTypeId='+object.product_ext_shop.product.productTypeId+'&requestType=page" class="d_block relative pp_wrap m_bottom_15"><span class="hot_stripe">'+
						promoteImg+
						'</span> <img src="'+imagepath+'" class="tr_all_hover" alt=""><span role="button" data-popup="#quick_view_product" '+
						'class="button_type_5 box_s_none color_light r_corners tr_all_hover d_xs_none" onclick="products.getShopExtProductdetail(\'window\',\''+object.productId+'\');">预览</span></a><figcaption><h5 class="m_bottom_10">'+
						'<a href="product_page.html?productId='+object.productId+'&productTypeId='+object.product_ext_shop.product.productTypeId+'&requestType=page" class="color_dark">'+object.product_ext_shop.product.productName+'</a></h5><ul class="horizontal_list d_inline_b m_bottom_10 clearfix rating_list type_2 tr_all_hover">';
						
						for(var i=0;i<object.product_ext_shop.product.productGrade;i++){
							html+='<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
						}
						for(var i=object.product_ext_shop.product.productGrade;i<5;i++){
							html+='<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
						}
						html+='</ul>';
						if(object.product_ext_shop.promoteflag==1 && nowDate-object.product_ext_shop.promoteStartTime>0 && object.product_ext_shop.promoteEndTime-nowDate>0){
							html+='<p class="v_align_b f_size_large m_bottom_15"><s>￥'+object.product_ext_shop.originalPrice+'</s>&nbsp; <span class="scheme_color"> ￥'+object.product_ext_shop.promotePrice+'</span></p>';
						}else{
							html+='<p class="scheme_color f_size_large m_bottom_15">￥'+object.product_ext_shop.originalPrice+'</p>';
						}
						html+='<button class="button_type_4 bg_scheme_color r_corners tr_all_hover color_light mw_0 m_bottom_15" onclick="common.operationCart(\'add\',this,0);" key="'+object.productId+'" >购买</button>'+
						'<button class="d_sm_none d_xs_block button_type_1  bg_light_color_1 box_s_none bg_light_color_2 '+
						'tr_delay_hover d_inline_b r_corners color_dark m_left_5" onclick="user.addToWishList(this,0);"  key="'+object.productId+'">'+
						'<span class="tooltip tr_all_hover r_corners color_dark f_size_small">收藏</span><i class="fa fa-heart-o f_size_big"></i>	</button></figcaption></figure></div>';
					
					if(type=='new'){
						$("#newProduct").append(html);
					}
					else if(type=='recommend'){
						$("#recommendProduct").append(html);
					}
				});
			},
			error:function(data){
//				alert("首页商品请求超时");
			}
		});
	},
	hotSaleProduct:function(type,typeId,maxCount){
		var nowDate = new Date().getTime();
		$.ajax({
			url:common.getRootPath()+"/eshop/product_ext_hotProduct/getHotProduct.action",
			type:"GET",
			async: false,
			data:{type:type,typeId:typeId,maxCount:maxCount},
			dataType:'text',
			success:function(d){
				eval("var data="+d);
				$.each(data.product_ext_hotProduct,function(index,object){
					var imagepath = object.product_ext_shop.product.productimglist[0].path;
					var html = 
						'<div class="clearfix m_bottom_15">'+
						'<a href="product_page.html?productId='+object.productId+'&productTypeId='+object.product_ext_shop.product.productTypeId+'&requestType=page">'+
						'<img src="'+imagepath+'" alt="" class="f_left m_right_15 m_sm_bottom_10 f_sm_none f_xs_left m_xs_bottom_0"> </a>'+
						'<a href="product_page.html?productId='+object.productId+'&productTypeId='+object.product_ext_shop.product.productTypeId+'&requestType=page" class="color_dark d_block bt_link">'+object.product_ext_shop.product.productName+'</a>'+
						'<ul class="horizontal_list clearfix d_inline_b rating_list type_2 tr_all_hover m_bottom_10">';
						for(var i=0;i<object.product_ext_shop.product.productGrade;i++){
							html+='<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
						}
						for(var i=object.product_ext_shop.product.productGrade;i<5;i++){
							html+='<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
						}
						html+='</ul>';
						if(object.product_ext_shop.promoteflag==1 && nowDate-object.product_ext_shop.promoteStartTime>0 && object.product_ext_shop.promoteEndTime-nowDate>0){
							html+='<p class="v_align_b"><s>￥'+object.product_ext_shop.originalPrice+'</s></p>';
							html+='<p class="scheme_color f_size_large m_bottom_15">￥'+object.product_ext_shop.promotePrice+'</p>';
						}else{
							html+='<p class="scheme_color f_size_large m_bottom_15">￥'+object.product_ext_shop.originalPrice+'</p>';
						}
						html+='</div> <hr class="m_bottom_15">';
					$("#widget_content").append(html);
				});
			},
			error:function(data){
				alert("热门商品请求超时");
			}
		});
	},
	getProductTypedetailedByProductTypeId:function (productTypeId){

		var listtype = common.getParameter("listtype");
		if(listtype=='new'){
			$("#productTypeName").children("h2").text('商城商品');
            $("#navigation").append('<li class="m_right_10"><a href="javascript:; " class="default_t_color" >商城商品</a></li>');
		}else{
			if(productTypeId!="" && productTypeId!=null && productTypeId!="null"){
				$.ajax({
					url:common.getRootPath()+'/eshop/product_type/getProductTypedetailed.action',
					type:"GET",
					async: false,
					data:{productTypeId:productTypeId},
					dataType:'json',
					success:function(data){
						$("#productTypeName").children("h2").text(data.typeName);
					},
					error:function(){
		//				alert("类型详情查询超时");
					}
				});
			}
		}
		
	},

	getProviderProductdetail:function (productId){

		var url = common.getRootPath()+'/';
		url+='provider/provider/getProviderProductdetail.action';
		$.ajax({
			url:url,
			type:"GET",
			async: false,
			data:{productId:productId},
			dataType:'json',
			success:function(product){
				
				$("#addCartbtn").attr("key",productId);
				$("#collection").attr("key",productId);
				$("#rateProductId").val(productId);
				var nowDate = new Date().getTime();

				//处理preview图片部分
                var html = '';
                html+='<img id="zoom_image" src="'+product.productimglist[1].path+'"';
                html+='data-zoom-image="'+product.productimglist[0].path+'" class="tr_all_hover" alt="">';
                html+='<a href="'+product.productimglist[0].path+'" class="d_block button_type_5 r_corners tr_all_hover box_s_none color_light p_hr_0">';
                html+='<i class="fa fa-expand"></i></a>';
                $("#previewImage").append(html);
                
                //处理滚动图片部分
                html='';
                var i = 0;
                var len = product.productimglist.length/3;
                for( i = 0; i < len; i++){   							
                	html += '<a href="javascript:;" data-image="'+product.productimglist[i*3+1].path+'" ';
                	html += 'data-zoom-image="'+product.productimglist[i*3].path+'">';
                	html += '<img src="'+product.productimglist[i*3+2].path+'" alt=""></a>';                	
                }
                $("#productAllImage").append(html);
				$("#lastNavigation").append('<i class="fa fa-angle-right d_inline_middle m_left_10"></i>');
                $("#navigation").append('<li class="m_right_10"><a href="javascript:; " class="default_t_color" >'+product.productName+'</a></li>');
                
                $("#detailedProductName").text(product.productName);
                
				for(var i=0;i<product.productGrade;i++){
					$("#detailedGrade").append('<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>');
				}
				for(var i=product.productGrade;i<5;i++){
					$("#detailedGrade").append('<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>');
				}
				$.ajax({
					url:common.getRootPath()+"/eshop/product/getProductReviewById.action",
					type:"GET",
					async: false,
					data:{productId:product.productId, maxcount:10},
					dataType:'text',
					success:function(r){
						eval("var review = "+r);
						$("#prodcomment").html(review.length+' 评论');
						var i = 0;
						$.each(review,function(index,object){
							if(i>=10){
								return false;
							}
							i++;
							var date = new Date(object.commentsDate);
							var year = date.getFullYear();
							
							var month = date.getMonth()+1;
							var day = date.getDate();
							var hours = date.getHours();
							var minutes = date.getMinutes();
							var seconds = date.getSeconds();
							var html = 
								'<article>'+
								'	<div class="clearfix m_bottom_10">'+
								'		<p class="f_size_medium f_left f_mxs_none m_mxs_bottom_5">'+object.user.userName+
								'		--	'+(year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds)+'</p>'+
								'		<!--rating-->'+
								'		<ul class="horizontal_list f_right f_mxs_none clearfix rating_list type_2">';
							
								for(var i=0;i<object.rate;i++){
									html += '<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
								}
								for(var i=object.rate;i<5;i++){
									html += '<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
								}
							
								html+='		</ul>'+
								'	</div>'+
								'	<p class="m_bottom_15">'+object.productComments+'</p>'+
								'</article>';
							$("#review").append(html);
						});
					}
					
				});
				
				$("#detailedProvider").text(product.provider.name);				
				$("#detailedstockNumber").html('<span class="color_green">在库</span>  &nbsp;'+product.stockNumber + product.unit);
				$("#detailedProductId").text(product.productId);
				
				if(product.attribute.length>0){
					i = 0;
					eval("var attributes="+product.attribute);//  = $.parseJSON();
					$.each(attributes,function(index,property){
						$("#detailedProperty").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
						i++;
						if (i == 3){//只显示前三个属性
							return false;
				   		}
				   });
			    }
				$("#detailedDesc").text(product.description);
				
				var html = '<span class="v_align_b f_size_big m_left_5 scheme_color fw_medium">￥'+product.shopPrice+'</span>';
				$("#detailedPrice").html(html);

				$("#productUnit").text(product.unit);

				if(product.producttrack!=null){
					$("#l-map").before('<div class="m_bottom_15" >'+product.producttrack.trackInfo+'</div>');
				}
				$.ajax({
					url:common.getRootPath()+"/dict/city/getCityByCityId.action",
					type:"GET",
					async: false,
					data:{cityId:product.productCityId},
					dataType:'json',
					success:function(cityData){
						$("#l-map").attr("key",cityData.cityName);
					}
				});
				
				//Tab处理部分				
				$("#alldesc").append(product.remarkHtml);	
				$("#allpropertyleft").children().remove();
				$("#allpropertyright").children().remove();
				i = 0;
				$.each(attributes,function(index,property){
					if( i % 2 == 0){
						$("#allpropertyleft").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
					}else{
						$("#allpropertyright").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
					}
					i++;
					
				});

			},
			error:function(){
//				alert("商品详情查询超时");
			}
		});
	},
	getShopExtProductdetail:function (requestType,productId){

		var url = common.getRootPath()+'/';
		url+='eshop/shopProduct/getShopProductDetail.action';
		$.ajax({
			url:url,
			type:"GET",
			async: false,
			data:{productId:productId},
			dataType:'json',
			success:function(data){
				$("#addCount").val("1");
				$("#addCartbtn").attr("key",productId);
				$("#collection").attr("key",productId);
				$("#rateProductId").val(productId);
				var nowDate = new Date().getTime();
				
				if(requestType=='page'){
					if(data.promoteflag==1 && nowDate-data.promoteStartTime>0 && data.promoteEndTime-nowDate>0){
						$("#hot_stripe").html('<img src="images/sale_product.png" alt="">');
					}
					else if(common.isHot(data.productId)){
						$("#hot_stripe").html('<img src="images/hot_product.png" alt="">');
					}
					//处理preview图片部分
	                var html = '';
	                html+='<img id="zoom_image" src="'+data.product.productimglist[1].path+'"';
	                html+='data-zoom-image="'+data.product.productimglist[0].path+'" class="tr_all_hover" alt="">';
	                html+='<a href="'+data.product.productimglist[0].path+'" class="d_block button_type_5 r_corners tr_all_hover box_s_none color_light p_hr_0">';
	                html+='<i class="fa fa-expand"></i></a>';
	                $("#previewImage").append(html);
	                
	                //处理滚动图片部分
	                html='';
	                var i = 0;
	                var len = data.product.productimglist.length/3;
	                for( i = 0; i < len; i++){   							
	                	html += '<a href="javascript:;" data-image="'+data.product.productimglist[i*3+1].path+'" ';
	                	html += 'data-zoom-image="'+data.product.productimglist[i*3].path+'">';
	                	html += '<img src="'+data.product.productimglist[i*3+2].path+'" alt=""></a>';                	
	                }
	                $("#productAllImage").append(html);
					$("#lastNavigation").append('<i class="fa fa-angle-right d_inline_middle m_left_10"></i>');
	                $("#navigation").append('<li class="m_right_10"><a href="javascript:; " class="default_t_color" >'+data.product.productName+'</a></li>');
				}
				else if(requestType=='window'){
					
					$("#detailedProductName").text('');
					var html = '';
					if(data.promoteflag==1 && nowDate-data.promoteStartTime>0 && data.promoteEndTime-nowDate>0){
						html += '<span class="hot_stripe"><img src="images/sale_product.png" alt=""></span>';
					}else if(common.isHot(data.productId)){
						html += '<span class="hot_stripe"><img src="images/hot_product.png" alt=""></span>';
					}
					html += '<img src="'+data.product.productimglist[1].path+'" class="tr_all_hover" alt="" width="360">';
					$("#previewImage").html("");
					$("#previewImage").html(html);
					
					html = '<div class="owl-wrapper-outer autoHeight" style="height: 90px;">'+
						'<div class="owl-wrapper" style="display: block; width: 1164px; left: 0px; transform: translate3d(0px, 0px, 0px);">';

					for(var i = 0; i < data.product.productimglist.length/3; i++){
						var classes = '';
						if(i==0){
							classes = 'active';
						}
						html +='<div class="owl-item" style="width: 97px;">'+
						'<li data-src="'+data.product.productimglist[i*3+1].path+'" class="'+classes+'">'+
						'<img alt="" src="'+data.product.productimglist[i*3+2].path+'"></li></div>';
	                }
					html += '</div></div>';
					$("#productAllImage").html("");
	                $("#productAllImage").html(html);
	                	
	                $("#productAllImage").css("opacity","1");
	                
	                $("#detailedGrade").children("li").remove();
	                $("#detailedProperty").children().remove();
	             
	                
				}
                
                $("#detailedProductName").text(data.product.productName);
				for(var i=0;i<data.product.productGrade;i++){
					$("#detailedGrade").append('<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>');
				}
				for(var i=data.product.productGrade;i<5;i++){
					$("#detailedGrade").append('<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>');
				}
				$.ajax({
					url:common.getRootPath()+"/eshop/product/getProductReviewById.action",
					type:"GET",
					async: false,
					data:{productId:data.productId, maxcount:10},
					dataType:'text',
					success:function(r){
						eval("var review = "+r);
						$("#prodcomment").html(review.length+' 评论');
						var i = 0;
						$.each(review,function(index,object){
							if(i>=10){
								return false;
							}
							i++;
							var date = new Date(object.commentsDate);
							var year = date.getFullYear();
							
							var month = date.getMonth()+1;
							var day = date.getDate();
							var hours = date.getHours();
							var minutes = date.getMinutes();
							var seconds = date.getSeconds();
							var html = 
								'<article>'+
								'	<div class="clearfix m_bottom_10">'+
								'		<p class="f_size_medium f_left f_mxs_none m_mxs_bottom_5">'+object.userPersonal.user.userName+
								'		--	'+(year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds)+'</p>'+
								'		<!--rating-->'+
								'		<ul class="horizontal_list f_right f_mxs_none clearfix rating_list type_2">';
							
								for(var i=0;i<object.rate;i++){
									html += '<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
								}
								for(var i=object.rate;i<5;i++){
									html += '<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
								}
							
								html+='		</ul>'+
								'	</div>'+
								'	<p class="m_bottom_15">'+object.productComments+'</p>'+
								'</article>';
							$("#review").append(html);
						});
					}
					
				});
				
				$("#detailedProvider").text(data.product.provider.name);				
				$("#detailedstockNumber").html('<span class="color_green">在库</span>  &nbsp;'+data.product.stockNumber +data.unit);
				$("#detailedProductId").text(data.productId);
				$("#productSalearea").text(data.salesarea);
				
				if(data.product.attribute.length>0){
					i = 0;
					eval("var attributes="+data.product.attribute);//  = $.parseJSON();
					$.each(attributes,function(index,property){
						$("#detailedProperty").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
						i++;
						if (i == 3){//只显示前三个属性
							return false;
				   		}
				   });
			    }
				$("#detailedDesc").text(data.product.description);
				

				if(data.promoteflag==1 && nowDate-data.promoteStartTime>0 && data.promoteEndTime-nowDate>0){
					var html = '<s class="v_align_b f_size_ex_large">￥'+data.originalPrice+'</s><span class="v_align_b f_size_big m_left_5 scheme_color fw_medium">￥'+data.promotePrice+'</span>';
					$("#detailedPrice").html(html);
				}else{
					var html = '<span class="v_align_b f_size_big m_left_5 scheme_color fw_medium">￥'+data.originalPrice+'</span>';
					$("#detailedPrice").html(html);
				}

				$("#productUnit").text(data.unit);
				if(data.product.producttrack!=null){
					$("#l-map").before('<div class="m_bottom_15" >'+data.product.producttrack.trackInfo+'</div>');
				}
				$.ajax({
					url:common.getRootPath()+"/dict/city/getCityByCityId.action",
					type:"GET",
					async: false,
					data:{cityId:data.product.productCityId},
					dataType:'json',
					success:function(cityData){
						$("#l-map").attr("key",cityData.cityName);
					}
				});
				
				//Tab处理部分				
				$("#alldesc").append(data.product.remarkHtml);	
				$("#allpropertyleft").children().remove();
				$("#allpropertyright").children().remove();
				i = 0;
				$.each(attributes,function(index,property){
					if( i % 2 == 0){
						$("#allpropertyleft").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
					}else{
						$("#allpropertyright").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
					}
					i++;
					
				});

			},
			error:function(){
//				alert("商品详情查询超时");
			}
		});
	},
	searchProductShopResults : function(searchKey,ordercondition,appendId,pageno,pagesize){
		$.ajax({
			url:common.getRootPath()+"/eshop/shopProduct/searchProductShop.action",
			type:"GET",
			async: false,
			data:{
				searchKey:searchKey,ordercondition:ordercondition,
				appendId:appendId,pageno:pageno==null?0:pageno,pagesize:(pagesize==null||pagesize==0)?16:pagesize
			},
			dataType:'text',
			success:function(d){
				eval("var data = "+d);
				if(data==null || data==''){
					$("#"+appendId).append('<div class="product_item hit w_xs_full">暂无相关数据</div>');
				}else{
					var resultHtml = '共'+data.page.totalcount+'条数据     第'+data.page.pageno+'/'+data.page.totalpage+'页';
					$("#results1").html(resultHtml);
					$("#results2").html(resultHtml);
					$("#pagesize1").text(data.page.pagesize);
					$("#pagesize2").text(data.page.pagesize);
					var fpage="";
					for(var i=0;i<data.page.totalpage;i++){
						var classes = 'color_dark';
						if(i+1==data.page.pageno){
							classes = 'scheme_color';
						}
						var page = i+1;
						fpage += '<li class="m_right_10"><a class="'+classes+'" href="javascript:products.topage('+page+');">'+page+'</a></li>';//
					}
					$("#prevPage1").next("ul").html(fpage);
					$("#prevPage1").attr("href","javascript:products.topages('"+(parseInt(data.page.pageno)-1)+"');");
					$("#nextPage1").attr("href","javascript:products.topages('"+(parseInt(data.page.pageno)+1)+"');");
					
					$("#prevPage2").next("ul").html(fpage);
					$("#prevPage2").attr("href","javascript:products.topages('"+(parseInt(data.page.pageno)-1)+"');");
					$("#nextPage2").attr("href","javascript:products.topages('"+(parseInt(data.page.pageno)+1)+"');");
					
					$.each(data.shopproduct,function(index,object){

						var imagepath = object.product.productimglist[0].path;
						
						var html = 
							'<div class="product_item hit w_xs_full"><figure class="r_corners photoframe animate_ftb type_2 t_align_c tr_all_hover shadow relative">'+
							'<a href="product_page.html?productId='+object.productId+'&productTypeId='+object.product.productTypeId+'&requestType=page" class="d_block relative pp_wrap m_bottom_15"><span class="hot_stripe">';
							var nowDate = new Date().getTime();
							if(object.promoteflag==1 && nowDate-object.promoteStartTime>0 && object.promoteEndTime-nowDate>0){
								html+='<img src="images/sale_product.png" alt="">';	
							}
							html+=
							'</span> <img src="'+imagepath+'" class="tr_all_hover" alt=""><span role="button" data-popup="#quick_view_product" '+
							'class="button_type_5 box_s_none color_light r_corners tr_all_hover d_xs_none" onclick="products.getShopExtProductdetail(\'window\',\''+object.productId+'\');">预览</span></a><figcaption>	<h5 class="m_bottom_10">'+
							'<a href="product_page.html?productId='+object.productId+'&productTypeId='+object.product.productTypeId+'&requestType=page" class="color_dark">'+
							object.product.productName+'</a></h5><ul class="horizontal_list d_inline_b m_bottom_10 clearfix rating_list type_2 tr_all_hover">';
							
							for(var i=0;i<object.product.productGrade;i++){
								html+='<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
							}
							for(var i=object.product.productGrade;i<5;i++){
								html+='<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
							}
							
							html+='</ul>';
							
							if(object.promoteflag==1 && nowDate-object.promoteStartTime>0 && object.promoteEndTime-nowDate>0){
								html+='<p class="v_align_b f_size_large m_bottom_15"><s>￥'+object.originalPrice+'</s>&nbsp; <span class="scheme_color"> ￥'+object.promotePrice+'</span></p>';
							}else{
								html+='<p class="scheme_color f_size_large m_bottom_15">￥'+object.originalPrice+'</p>';
							}
							html+='<button class="button_type_4 bg_scheme_color r_corners tr_all_hover color_light mw_0 m_bottom_15" onclick="common.operationCart(\'add\',this,0);" key="'+object.productId+'" >购买</button>'+
							'<button class="d_sm_none d_xs_block button_type_1  bg_light_color_1 box_s_none bg_light_color_2 '+
							'tr_delay_hover d_inline_b r_corners color_dark m_left_5" onclick="user.addToWishList(this,0);"  key="'+object.productId+'"">'+
							'<span class="tooltip tr_all_hover r_corners color_dark f_size_small">收藏</span><i class="fa fa-heart-o f_size_big"></i>	</button></figcaption></figure></div>';
							
						$("#"+appendId).append(html);
					});

				}
			},
			error:function(data){
//				alert('操作超时');
			}
		});
	},
	getShopProductByProductTypeId : function(level,productTypeId,ordercondition,appendId,pageno,pagesize){
		$.ajax({
			url:common.getRootPath()+"/eshop/shopProduct/getShopProductByProductTypeId.action",
			type:"GET",
			async: false,
			data:{
				level:level,productTypeId:productTypeId,ordercondition:ordercondition,
				appendId:appendId,pageno:pageno==null?0:pageno,pagesize:(pagesize==null||pagesize==0)?16:pagesize,
				listtype:common.getParameter("listtype")
			},
			dataType:'text',
			success:function(d){
				eval("var data = "+d);
				if(data==null || data==''){
					$("#"+appendId).append('<div class="product_item hit w_xs_full">暂无相关数据</div>');
				}else{
					var resultHtml = '共'+data.page.totalcount+'条数据     第'+data.page.pageno+'/'+data.page.totalpage+'页';
					$("#results1").html(resultHtml);
					$("#results2").html(resultHtml);
					$("#pagesize1").text(data.page.pagesize);
					$("#pagesize2").text(data.page.pagesize);
					var fpage="";
					for(var i=0;i<data.page.totalpage;i++){
						var classes = 'color_dark';
						if(i+1==data.page.pageno){
							classes = 'scheme_color';
						}
						var page = i+1;
						fpage += '<li class="m_right_10"><a class="'+classes+'" href="javascript:products.topage('+page+');">'+page+'</a></li>';//
					}
					$("#prevPage1").next("ul").html(fpage);
					$("#prevPage1").attr("href","javascript:products.topage('"+(parseInt(data.page.pageno)-1)+"');");
					$("#nextPage1").attr("href","javascript:products.topage('"+(parseInt(data.page.pageno)+1)+"');");
					
					$("#prevPage2").next("ul").html(fpage);
					$("#prevPage2").attr("href","javascript:products.topage('"+(parseInt(data.page.pageno)-1)+"');");
					$("#nextPage2").attr("href","javascript:products.topage('"+(parseInt(data.page.pageno)+1)+"');");
					
					$.each(data.shopproduct,function(index,object){

						var imagepath = object.product.productimglist[0].path;
						
						var html = 
							'<div class="product_item hit w_xs_full"><figure class="r_corners photoframe animate_ftb type_2 t_align_c tr_all_hover shadow relative">'+
							'<a href="product_page.html?productId='+object.productId+'&productTypeId='+object.product.productTypeId+'&requestType=page" class="d_block relative pp_wrap m_bottom_15"><span class="hot_stripe">';
							var nowDate = new Date().getTime();
							if(object.promoteflag==1 && nowDate-object.promoteStartTime>0 && object.promoteEndTime-nowDate>0){
								html+='<img src="images/sale_product.png" alt="">';	
							}
							html+=
							'</span> <img src="'+imagepath+'" class="tr_all_hover" alt=""><span role="button" data-popup="#quick_view_product" '+
							'class="button_type_5 box_s_none color_light r_corners tr_all_hover d_xs_none" onclick="products.getShopExtProductdetail(\'window\',\''+object.productId+'\');">预览</span></a><figcaption>	<h5 class="m_bottom_10">'+
							'<a href="product_page.html?productId='+object.productId+'&productTypeId='+object.product.productTypeId+'&requestType=page" class="color_dark">'+
							object.product.productName+'</a></h5><ul class="horizontal_list d_inline_b m_bottom_10 clearfix rating_list type_2 tr_all_hover">';
							
							for(var i=0;i<object.product.productGrade;i++){
								html+='<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
							}
							for(var i=object.product.productGrade;i<5;i++){
								html+='<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
							}
							
							html+='</ul>';
							
							if(object.promoteflag==1 && nowDate-object.promoteStartTime>0 && object.promoteEndTime-nowDate>0){
								html+='<p class="v_align_b f_size_large m_bottom_15"><s>￥'+object.originalPrice+'</s>&nbsp; <span class="scheme_color"> ￥'+object.promotePrice+'</span></p>';
							}else{
								html+='<p class="scheme_color f_size_large m_bottom_15">￥'+object.originalPrice+'</p>';
							}
							html+='<button class="button_type_4 bg_scheme_color r_corners tr_all_hover color_light mw_0 m_bottom_15" onclick="common.operationCart(\'add\',this,0);" key="'+object.productId+'" >购买</button>'+
							'<button class="d_sm_none d_xs_block button_type_1  bg_light_color_1 box_s_none bg_light_color_2 '+
							'tr_delay_hover d_inline_b r_corners color_dark m_left_5" onclick="user.addToWishList(this,0);"  key="'+object.productId+'"">'+
							'<span class="tooltip tr_all_hover r_corners color_dark f_size_small">收藏</span><i class="fa fa-heart-o f_size_big"></i>	</button></figcaption></figure></div>';
							
						$("#"+appendId).append(html);
					});

				}
			},
			error:function(data){
//				alert('操作超时');
			}
		});
	},
	getAssociatedShopProduct:function(productId){
		$.ajax({
			url:common.getRootPath()+"/eshop/shopProduct/getAssociatedShopProduct.action",
			type:"GET",
			async: false,
			data:{productId:productId},
			dataType:'text',
			success:function(d){
				$("#associatedProduct").children().remove();
				if(d!='[]'){
					var data = $.parseJSON(d);
					$.each(data,function(index,shopProduct){
						
						var nowDate = new Date();

						var html =
							'<figure class="r_corners photoframe shadow relative d_xs_inline_b tr_all_hover">'+
							'<a href="product_page.html?productId='+shopProduct.productId+'&productTypeId='+shopProduct.product.product_type.productTypeId+'&requestType=page&url='+common.getParameter("url")+'" class="d_block relative pp_wrap">';
							if(shopProduct.promoteflag==1 && nowDate-shopProduct.promoteStartTime>0 && shopProduct.promoteEndTime-nowDate>0){
								html+='<span class="hot_stripe type_2"><img src="images/sale_product_type_2.png" alt=""></span>';
							}

							
							html+='<img src="'+shopProduct.product.productimglist[0].path+'" class="tr_all_hover" alt="">'+
							'  </a><figcaption class="t_xs_align_l">'+
							'	<h5 class="m_bottom_10">'+
							'		<a href="product_page.html?productId='+shopProduct.productId+'&productTypeId='+shopProduct.product.product_type.productTypeId+'&requestType=page&url='+common.getParameter("url")+'" class="color_dark ellipsis">'+shopProduct.product.productName+'</a>'+
							'	</h5>'+
							'	<div class="clearfix">';
							
							if(shopProduct.promoteflag==1 && nowDate-shopProduct.promoteStartTime>0 && shopProduct.promoteEndTime-nowDate>0){
								html+='<p class="v_align_b f_size_large m_bottom_15"><s>￥'+shopProduct.originalPrice+'</s>&nbsp; <span class="scheme_color"> ￥'+shopProduct.promotePrice+'</span></p>';
							}else{
								html+='<p class="scheme_color f_size_large m_bottom_15">￥'+shopProduct.originalPrice+'</p>';
							}
							

							html+='<ul	class="horizontal_list f_right clearfix rating_list tr_all_hover">';
							for(var i=0;i<shopProduct.product.productGrade;i++){
								html+='<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
							}
							for(var i=shopProduct.product.productGrade;i<5;i++){
								html+='<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
							}

							html+='</ul></div><button class="button_type_4 bg_scheme_color r_corners tr_all_hover color_light mw_0" onclick="common.operationCart(\'add\',this,0);" key="'+shopProduct.productId+'"   >购买</button>'+
							'</figcaption>'+
							'</figure>';
						
						$("#associatedProduct").append(html);
					});
				}
				else{
					$("#associatedH").hide();
				}
			}
		});
	},
	getEqualLevelProductType:function(productTypeId){
		
		$.ajax({
			url:common.getRootPath()+"/eshop/product_type/getLevelProductType.action",
			type:"GET",
			async: false,
			data:{productTypeId:productTypeId},
			dataType:'json',
			success:function(data){
				$.each(data,function(index,productType){
					html=
						'<li'+
						'	class="m_right_15 f_mxs_none w_mxs_auto d_mxs_inline_b m_mxs_bottom_20">'+
						'	<a href="category_list.html?level=3&productTypeId='+productType.productTypeId+'"'+
						'	class="d_block photoframe tr_all_hover shadow color_dark r_corners">'+
						'		<span class="d_block wrapper"> <img class="tr_all_long_hover" src="'+productType.logImagePath+'" alt="">'+
						'	</span>'+productType.typeName+
						'</a>'+
						'</li>';
					$("#equallevelnode").append(html);
				});
			}
		});
	},
	topage:function(pageno){
		var pagesize = (common.getParameter("pagesize")==null || common.getParameter("pagesize")=='null')?'0':common.getParameter("pagesize");
		window.location.href='category_list.html?level=3&productTypeId='+
			common.getParameter('productTypeId')
			+'&ordercondition='+common.getParameter("ordercondition")
			+'&brandid'+common.getParameter('brandid')
			+'&pageno='+pageno
			+'&pagesize='+pagesize
			+'&listtype='+common.getParameter("listtype");
	},
	topages:function(pageno){
		var pagesize = (common.getParameter("pagesize")==null || common.getParameter("pagesize")=='null')?'0':common.getParameter("pagesize");
		window.location.href='search_list.html?&searchKey='+
			common.getParameter('searchKey')
			+'&ordercondition='+common.getParameter("ordercondition")
			+'&pageno='+pageno
			+'&pagesize='+pagesize
	},
	getGroupBuyProductbyType:function(divid, type, typeId, maxcount){
		 
		$.ajax({
			url:common.getRootPath()+"/eshop/groupBuyProduct/getgroupBuyProductByType.action",
			type:"GET",
			async: false,
			data:{type:type, typeId:typeId, maxcount:maxcount},  //type 1:查询全部   
			dataType:'json',
			success:function(data){
				
				var html='';
				$.each(data, function(index,groupbuyprod){
									
					html+='<div class="portfolio_item t_xs_align_c '+groupbuyprod.product.productTypeId.substr(0,2)+'">';
					html+='<figure class="d_xs_inline_b d_mxs_block"><div	class="photoframe with_buttons relative shadow r_corners wrapper m_bottom_15">';
					html+='<img src="'+groupbuyprod.product.productimglist[1].path+'" alt="" class="tr_all_long_hover">';
					html+='<div class="open_buttons clearfix">';
					html+='<div class="f_left f_size_large tr_all_hover">';
					html+='<a href="'+groupbuyprod.product.productimglist[0].path+'" role="button" class="color_light button_type_13 r_corners box_s_none d_block jackbox"';
					html+='data-group="portfolio" data-title="大图"><i class="fa fa-camera"></i></a></div>';
					html+='<div class="f_left m_left_10 f_size_large tr_all_hover">';
					html+='<a href="groupbuy_product_page.html?groupbuyid='+groupbuyprod.groupbuyid+'" role="button"	class="color_light button_type_13 r_corners box_s_none d_block">'; 
					html+='<i class="fa fa-link"></i></a></div></div></div>';
					html+='<figcaption class="t_xs_align_l"><h4 class="m_bottom_3"><a href="groupbuy_product_page.html?groupbuyid='+groupbuyprod.groupbuyid+'" class="color_dark"><b>'+groupbuyprod.product.productName+'</b></a></h4>';
					html+='<a href="#" class="color_dark">'+groupbuyprod.product.product_type.typeName+'</a></figcaption></figure></div>';
				});
				$("#"+divid).append(html);
			},
			error:function(){
				alert('操作超时');
			}
		});		
		
	},
	getrelatedgroupbuyproduct:function(divid, type, typeId, maxcount){
		//为产品ID赋值
		var typeId2 = common.getParameter("groupbuyid");
		
		$.ajax({
			url:common.getRootPath()+"/eshop/groupBuyProduct/getgroupBuyProductByType.action",
			type:"GET",
			async: false,
			data:{type:type, typeId:typeId2, maxcount:maxcount},  //maxcount 最大查询记录数  
			dataType:'json',
			success:function(data){
				var html='';
				$.each(data,function(index,groupbuyprod){
									
					html+='<div class="clearfix m_bottom_15"><a href="groupbuy_product_page.html?groupbuyid='+groupbuyprod.groupbuyid+'"><img src="'+groupbuyprod.product.productimglist[0].path+'" alt=""';
					html+='class="f_left m_right_15 m_sm_bottom_10 f_sm_none f_xs_left m_xs_bottom_0"></a>';
					html+='<a href="groupbuy_product_page.html?groupbuyid='+groupbuyprod.groupbuyid+'" class="color_dark d_block bt_link">'+groupbuyprod.product.productName+'</a>';
					html+='<ul class="horizontal_list clearfix d_inline_b rating_list type_2 tr_all_hover m_bottom_10">';
					for(var i=0;i<groupbuyprod.product.productGrade;i++){
						html+='<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
					}
					for(var i=groupbuyprod.product.productGrade;i<5;i++){
						html+='<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
					}
					html+='</ul>';
					html+='<p class="scheme_color">￥'+groupbuyprod.groupBuyPrice+'</p></div> <hr class="m_bottom_15">';
	

				});
				$("#"+divid).append(html);
			},
			error:function(){
				alert('操作超时');
			}
		});		
		
	},
	getGroupBuyProductdetail:function (type, typeid, maxcount){

		$.ajax({
			url:common.getRootPath()+"/eshop/groupBuyProduct/getgroupBuyProductByType.action",
			type:"GET",
			async: false,
			data:{type:type, typeId:typeid, maxcount:maxcount},  //maxcount 最大查询记录数  
			dataType:'json',
			success:function(datalist){
                var data = datalist[0];
                //处理preview图片部分
                var html = '';
                html+='<img id="zoom_image" src="'+data.product.productimglist[1].path+'"';
                html+='data-zoom-image="'+data.product.productimglist[0].path+'" class="tr_all_hover" alt="">';
                html+='<a href="'+data.product.productimglist[0].path+'" class="d_block button_type_5 r_corners tr_all_hover box_s_none color_light p_hr_0">';
                html+='<i class="fa fa-expand"></i></a>';
                $("#previewImage").append(html);
                
                //处理滚动图片部分
                html='';
                var i = 0;
                var len = data.product.productimglist.length/3;
                for( i = 0; i < len; i++){   							
                	html += '<a href="#" data-image="'+data.product.productimglist[i*3+1].path+'" ';
                	html += 'data-zoom-image="'+data.product.productimglist[i*3].path+'">';
                	html += '<img src="'+data.product.productimglist[i*3+2].path+'" alt=""></a>';                	
                }
                $("#productAllImage").append(html);

                
				$("#detailedProductName").text(data.product.productName);
				for(var i=0;i<data.product.productGrade;i++){
					$("#detailedGrade").append('<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>');
				}
				for(var i=data.product.productGrade;i<5;i++){
					$("#detailedGrade").append('<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>');
				}
				
				$.ajax({
					url:common.getRootPath()+"/eshop/product/getProductReviewById.action",
					type:"GET",
					async: false,
					data:{productId:data.productId, maxcount:10},
					dataType:'text',
					success:function(r){
						eval("var review = "+r);
						$("#prodcomment").html(review.length+' 评论');
					}
				});
				
				$("#detailedProvider").text(data.product.provider.name);				
				$("#detailedstockNumber").html('<span class="color_green">在库</span>  '+data.stockNumber+data.unit);
				$("#detailedProductId").text(data.productId);
				$("#productSalearea").text(data.salesarea);
				
				if(data.product.attribute.length>0){
					i = 0;
					eval("var attributes="+data.product.attribute);//  = $.parseJSON();
					$.each(attributes,function(index,property){
						$("#detailedProperty").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
						i++;
						if (i == 3){//只显示前三个属性
							return false;
				   		}
				   });
			    }
				$("#detailedDesc").text(data.product.description);
				$("#detailedPrice").text("￥"+data.product.shopPrice+'/'+data.product.unit);
				$("#detailedGroupBuyPrice").text("￥"+data.groupBuyPrice);
				$("#productUnit").text(data.unit);
				

				if(data.product.producttrack!=null){
					$("#l-map").before('<div class="m_bottom_15" >'+data.product.producttrack.trackInfo+'</div>');
				}
				$.ajax({
					url:common.getRootPath()+"/dict/city/getCityByCityId.action",
					type:"GET",
					async: false,
					data:{cityId:data.product.productCityId},
					dataType:'json',
					success:function(cityData){
						$("#l-map").attr("key",cityData.cityName);
					}
				});
				
				
				//Tab处理部分				
				$("#alldesc").html(data.product.remarkHtml);				
				$("#allpropertyleft").children().remove();
				$("#allpropertyright").children().remove();
				i = 0;
				$.each(attributes,function(index,property){
					if( i % 2 == 0){
						$("#allpropertyleft").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
					}else{
						$("#allpropertyright").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
					}
					i++;
					
				});
				$("#groupbuyinst").html('<p class="m_bottom_10">'+data.groupbuyhint+'</p>');
				
			},
			error:function(){
				alert("商品详情查询超时");
			}
		});
	},
	getBigDealProductbyType:function(divid, type, typeId, maxcount){
		 
		$.ajax({
			url:common.getRootPath()+"/eshop/bigDealProduct/getbigDealProductByType.action",
			type:"GET",
			async: false,
			data:{type:type, typeId:typeId, maxcount:maxcount},  //type 1:查询全部   
			dataType:'json',
			success:function(data){
				var html='';
				$.each(data,function(index,bigdealprod){
									
					html+='<div class="portfolio_item t_xs_align_c '+bigdealprod.product.productTypeId.substr(0,2)+'">';
					html+='<figure class="d_xs_inline_b d_mxs_block"><div	class="photoframe with_buttons relative shadow r_corners wrapper m_bottom_15">';
					html+='<img src="'+bigdealprod.product.productimglist[1].path+'" alt="" class="tr_all_long_hover">';
					html+='<div class="open_buttons clearfix">';   
					html+='<div class="f_left f_size_large tr_all_hover">';
					html+='<a href="'+bigdealprod.product.productimglist[0].path+'" role="button" class="color_light button_type_13 r_corners box_s_none d_block jackbox"'
					html+='data-group="portfolio" data-title="大图"><i class="fa fa-camera"></i></a></div>';
					html+='<div class="f_left m_left_10 f_size_large tr_all_hover">';
					html+='<a href="bigdeal_product_page.html?bigdealid='+bigdealprod.bigdealId+'" role="button"	class="color_light button_type_13 r_corners box_s_none d_block">'; 
					html+='<i class="fa fa-link"></i></a></div></div></div>';
					html+='<figcaption class="t_xs_align_l"><h4 class="m_bottom_3"><a href="bigdeal_product_page.html?bigdealid='+bigdealprod.bigdealId+'" class="color_dark"><b>'+bigdealprod.product.productName+'</b></a></h4>';
					html+='<a href="#" class="color_dark">'+bigdealprod.product.product_type.typeName+'</a></figcaption></figure></div>';
				});
				$("#"+divid).append(html);
			},
			error:function(){
				alert('操作超时');
			}
		});		
		
	},
	getrelatedbigdealproduct:function(divid, type, typeId, maxcount){
		//为产品ID赋值
		var typeId2 = common.getParameter("bigdealid");
		
		$.ajax({
			url:common.getRootPath()+"/eshop/bigDealProduct/getbigDealProductByType.action",
			type:"GET",
			async: false,
			data:{type:type, typeId:typeId2, maxcount:maxcount},  //maxcount 最大查询记录数  
			dataType:'json',
			success:function(data){
				var html='';
				$.each(data,function(index,bigdealprod){
									
					html+='<div class="clearfix m_bottom_15"><a href="bigdeal_product_page.html?bigdealid='+bigdealprod.bigdealId+'"><img src="'+bigdealprod.product.productimglist[0].path+'" alt=""';
					html+='class="f_left m_right_15 m_sm_bottom_10 f_sm_none f_xs_left m_xs_bottom_0"> </a>';
					html+='<a href="bigdeal_product_page.html?bigdealid='+bigdealprod.bigdealId+'" class="color_dark d_block bt_link">'+bigdealprod.product.productName+'</a>';
					html+='<ul class="horizontal_list clearfix d_inline_b rating_list type_2 tr_all_hover m_bottom_10">';
					for(var i=0;i<bigdealprod.product.productGrade;i++){
						html+='<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
					}
					for(var i=bigdealprod.product.productGrade;i<5;i++){
						html+='<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>';
					}
					html+='</ul>';
					html+='<p class="scheme_color">￥'+bigdealprod.referencePrice+'</p></div> <hr class="m_bottom_15">';
	

				});
				$("#"+divid).append(html);
			},
			error:function(){
				alert('操作超时');
			}
		});		
		
	},
	getBigDealProductdetail:function (type, typeid, maxcount){

		$.ajax({
			url:common.getRootPath()+"/eshop/bigDealProduct/getbigDealProductByType.action",
			type:"GET",
			async: false,
			data:{type:type, typeId:typeid, maxcount:maxcount},  //maxcount 最大查询记录数  
			dataType:'json',
			success:function(srcdata){
                var data = srcdata[0];
                //处理preview图片部分
                var html = '';
                html+='<img id="zoom_image" src="'+data.product.productimglist[1].path+'"';
                html+='data-zoom-image="'+data.product.productimglist[0].path+'" class="tr_all_hover" alt="">';
                html+='<a href="'+data.product.productimglist[0].path+'" class="d_block button_type_5 r_corners tr_all_hover box_s_none color_light p_hr_0">';
                html+='<i class="fa fa-expand"></i></a>';
                $("#previewImage").append(html);
                
                //处理滚动图片部分
                html='';
                var i = 0;
                var len = data.product.productimglist.length/3;
                for( i = 0; i < len; i++){   							
                	html += '<a href="#" data-image="'+data.product.productimglist[i*3+1].path+'" ';
                	html += 'data-zoom-image="'+data.product.productimglist[i*3].path+'">';
                	html += '<img src="'+data.product.productimglist[i*3+2].path+'" alt=""></a>';                	
                }
                $("#productAllImage").append(html);

                
				$("#detailedProductName").text(data.product.productName);
				for(var i=0;i<data.product.productGrade;i++){
					$("#detailedGrade").append('<li class="active"><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>');
				}
				for(var i=data.product.productGrade;i<5;i++){
					$("#detailedGrade").append('<li><i class="fa fa-star-o empty tr_all_hover"></i><i class="fa fa-star active tr_all_hover"></i></li>');
				}
				
				$.ajax({
					url:common.getRootPath()+"/eshop/product/getProductReviewById.action",
					type:"GET",
					async: false,
					data:{productId:data.productId, maxcount:10},
					dataType:'json',
					success:function(review){
						$("#prodcomment").html(review.length+' 评论');
					}
				});
				
				$("#detailedProvider").text(data.product.provider.name);				
				$("#detailedProductId").text(data.productId);
				$("#productSalearea").text(data.salesarea);
				
				if(data.product.attribute.length>0){
					i = 0;
					eval("var attributes="+data.product.attribute);//  = $.parseJSON();
					$.each(attributes,function(index,property){
						$("#detailedProperty").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
						i++;
						if (i == 3){//只显示前三个属性
							return false;
				   		}
				   });
			    }
				$("#detailedDesc").text(data.product.description);
				$("#detailedPrice").text("￥"+data.product.shopPrice+'/'+data.product.unit);
				$("#detailedRefPrice").text("￥"+data.referencePrice);
				$("#productUnit").text(data.unit);

				if(data.product.producttrack!=null){
					$("#l-map").before('<div class="m_bottom_15">'+data.product.producttrack.trackInfo+'</div>');
				}
				$.ajax({
					url:common.getRootPath()+"/dict/city/getCityByCityId.action",
					type:"GET",
					async: false,
					data:{cityId:data.product.productCityId},
					dataType:'json',
					success:function(cityData){
						$("#l-map").attr("key",cityData.cityName);
					}
				});
				
				
				//Tab处理部分				
				$("#alldesc").html(data.product.remarkHtml);				
				$("#allpropertyleft").children().remove();
				$("#allpropertyright").children().remove();
				i = 0;
				$.each(attributes,function(index,property){
					if( i % 2 == 0){
						$("#allpropertyleft").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
					}else{
						$("#allpropertyright").append('<tr><td>'+property.propertyName+':</td>'+
								'<td><span class="color_dark">'+property.propertyValue+'</span></td></tr>');
					}
					i++;
					
				});
				$("#contactman").text("联系人: "+data.contactMan);
				$("#contactnumber").text("联系电话: "+data.contactTelephone);
				
			},
			error:function(){
				alert("商品详情查询超时");
			}
		});
	}
	,
	getNavigation : function(type, TypeId){
		var prodtypeid = '';
		if(type == 2){//传到groupbuyid
			$.ajax({
				url:common.getRootPath()+"/eshop/groupBuyProduct/getgroupBuyProductByType.action",
				type:"GET",
				async: false,
				data:{type:3, typeId:TypeId, maxcount:1},  //maxcount 最大查询记录数  
				dataType:'json',
				success:function(data){
	                prodtypeid = data[0].product.productTypeId;				
				},
				error:function(){
					alert("商品详情查询超时");
				}
			});
			
			
		}else if(type == 3){//传到bigdealid
			$.ajax({
				url:common.getRootPath()+"/eshop/bigDealProduct/getbigDealProductByType.action",
				type:"GET",
				async: false,
				data:{type:3, typeId:TypeId, maxcount:1},  //maxcount 最大查询记录数  
				dataType:'json',
				success:function(data){
	                prodtypeid = data[0].product.productTypeId;			
				},
				error:function(){
					alert("商品详情查询超时");
				}
			});
		}else if(type == 1){
			prodtypeid = TypeId;
		}
		if(prodtypeid!='null'){
			$.ajax({
				url:common.getRootPath()+"/eshop/product_type/getNavigation.action",
				type:"GET",
				async: false,
				data:{productTypeId:prodtypeid},  //maxcount 最大查询记录数  
				dataType:'json',
				success:function(data){
					var len = data.length;
	
					$("#navigation").children("li:gt(0)").remove();
					for(var i=0;i<len;i++){
						var di =  '';
						if(i != len-1){
							di = '<i class="fa fa-angle-right d_inline_middle m_left_10"></i>';
						}
						var html='<li class="m_right_10 current" ><a href="category_list.html?level='+(i+1)+'&productTypeId='+data[len-i-1].productTypeId+'" class="default_t_color" '+(i==0?" id=\'lastNavigation\'":"")+'>'+data[len-i-1].typeName+'</a>'+di+'  </li>';
						$("#navigation").append(html);
						
					}
				}
			});
		}
	},
	getSideNewProduct : function(){
		
		$.ajax({
			url:"/eshop/product_ext_new/getProduct_ext_new.action",
			type:"GET",
			async: false,
			data:{maxcount:'5'},
			dataType:'json',
			success:function(data){
				var nowDate = new Date().getTime();
				$.each(data,function(index,object){

					var imagepath = object.product_ext_shop.product.productimglist[0].path;
					
					var html =
					'<div class="clearfix m_bottom_15">'+
					'	<img src="'+imagepath+'" alt=""'+
					'		class="f_left m_right_15 m_sm_bottom_10 f_sm_none f_xs_left m_xs_bottom_0">'+
					'	<a href="product_page.html?productId='+object.productId+'&productTypeId='+object.product_ext_shop.product.productTypeId+'&requestType=page" class="color_dark d_block m_bottom_5 bt_link">'+object.product_ext_shop.product.productName+'</a>';
					if(object.product_ext_shop.promoteflag==1 && nowDate-object.product_ext_shop.promoteStartTime>0 && object.product_ext_shop.promoteEndTime-nowDate>0){
						html+='<p class="v_align_b"><s>￥'+object.product_ext_shop.originalPrice+'</s></p>';
						html+='<p class="scheme_color">￥'+object.product_ext_shop.promotePrice+'</p>';
					}else{
						html+='<p class="scheme_color">￥'+object.product_ext_shop.originalPrice+'</p>';
					}
					html+='</div>';
					
					$("#newProductSide").append(html);
					
				})
			}
		});
	},
	getRecommendProduct:function(maxCount,appendId){
		
		$.ajax({
			url:"/eshop/product_ext_recommProd/getRecommProduct.action",
			type:"GET",
			async: false,
			dataType:'json',
			data:{maxCount:maxCount},
			success:function(data){

				var nowDate = new Date().getTime();
				$.each(data,function(index,object){
					html=
					'<div class="specials_item">'+
					'<a href=""product_page.html?productId='+object.productId+'&productTypeId='+object.product_ext_shop.product.productTypeId+'&requestType=page" class="d_block d_xs_inline_b wrapper m_bottom_20">'+
					'<img class="tr_all_long_hover" src="'+object.product_ext_shop.product.productimglist[0].path+'"'+
					'alt="">'+
					'</a>'+
					'<h5 class="m_bottom_10">'+
					'	<a href=""product_page.html?productId='+object.productId+'&productTypeId='+object.product_ext_shop.product.productTypeId+'&requestType=page" class="color_dark">'+object.product_ext_shop.product.productName+'</a>'+
					'</h5>'+
					'<p class="f_size_large m_bottom_15">';
					
					if(object.product_ext_shop.promoteflag==1 && nowDate-object.product_ext_shop.promoteStartTime>0 && object.product_ext_shop.promoteEndTime-nowDate>0){
						html+='<s>￥'+object.product_ext_shop.originalPrice+'</s> <span class="scheme_color">￥'+object.product_ext_shop.promotePrice+'</span>';
					}else{
						html+='<span class="scheme_color">￥'+object.product_ext_shop.promotePrice+'</span>';
					}
					'	'+
					'</p>'+
					'<button'+
					'	class="button_type_4 mw_sm_0 r_corners color_light bg_scheme_color tr_all_hover m_bottom_5" onclick="common.operationCart(\'add\',this,0);" key="'+object.productId+'">购买'+
					'</button>'+
					'</div>';

					$("#"+appendId).append(html);
				});
			}
		});
		
	}
};



