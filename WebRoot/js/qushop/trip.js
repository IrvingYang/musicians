
var trip={
		
	getTripModuleRecommend:function(maxCount,divId){
		
		var url = common.getRootPath()+'/trip/moduleRecommend/getTripModuleRecommendWithEntityType.action';
		
		$.ajax({
			url:url,
			type:'GET',
			async: false,
			data: {maxCount:maxCount},
			dataType:'json',
			success:function(data){
				$("#"+divId).children().remove();
				$.each(data,function(index,recommend){
					var html = '';
					//活动
					if(recommend.entitytype=='01'){
						html = 
							'<figure class="r_corners photoframe animate_ftb long tr_all_hover type_2 t_align_c shadow relative">'+
							'<a href="activity_detail.html?activityid='+recommend.activitiesList[0].activityid+'" class="d_block relative pp_wrap m_bottom_15"><span class="hot_stripe type_2">'+
							'<img src="images/hot_product_type_2.png" alt=""></span><img src="'+recommend.activitiesList[0].entityimage[0].imagepath+'" class="tr_all_hover" alt=""></a>'+
							'<figcaption><h5 class="m_bottom_10"><a href="#" class="color_dark">'+recommend.title+'</a></h5>	</figcaption></figure>';
					//线路
					}else if(recommend.entitytype=='02'){
						html = 
							'<figure class="r_corners photoframe animate_ftb long tr_all_hover type_2 t_align_c shadow relative">'+
							'<a href="trip_detail.html?recommendlineid='+recommend.recommendlinesList[0].recommendlineid+'" class="d_block relative pp_wrap m_bottom_15"><span class="hot_stripe type_2">'+
							'<img src="images/hot_product_type_2.png" alt=""></span><img src="'+recommend.recommendlinesList[0].entityimage[2].imagepath+'" class="tr_all_hover" alt=""></a>'+
							'<figcaption><h5 class="m_bottom_10"><a href="#" class="color_dark">'+recommend.title+'</a></h5>	</figcaption></figure>';
					//游记
					}else if(recommend.entitytype=='03'){
						html = 
							'<figure class="r_corners photoframe animate_ftb long tr_all_hover type_2 t_align_c shadow relative">'+
							'<a href="blog_detail.html?blogid='+recommend.blogsList[0].blogid+'" class="d_block relative pp_wrap m_bottom_15"><span class="hot_stripe type_2">'+
							'<img src="images/hot_product_type_2.png" alt=""></span><img src="'+recommend.blogsList[0].entityimage[1].imagepath+'" class="tr_all_hover" alt=""></a>'+
							'<figcaption><h5 class="m_bottom_10"><a href="#" class="color_dark">'+recommend.title+'</a></h5>	</figcaption></figure>';
					}
					
					$("#"+divId).append(html);
				});
			},
			error:function(data){
				alert("请求超时");
			}
		});
	},
	getActivityList : function()
	{
		$.ajax({
			url:common.getRootPath()+"/trip/activity/getActivityList.action",
			type:'GET',
			async: false,
			dataType:'json',
			success:function(data)
			{
				$.each(data,function(index,activity){
					
					var introduction = "";
					if(activity.introduction.length>200){
						introduction = ((activity.introduction).substring(0,200)+"……");
					}
					else{
						introduction = activity.introduction;
					}
					
					var date = new Date(activity.begintime);
					var year = date.getFullYear();
					
					var month = date.getMonth()+1;
					var day = date.getDate();
					
					var date1 = new Date(activity.endapplytime);
					var year1 = date1.getFullYear();
					
					var month1 = date1.getMonth()+1;
					var day1 = date1.getDate();
					
					var html = 
						'<div><div class="d_table full_width d_xs_block m_bottom_25">'+
						'	<div class="d_table_cell v_align_m d_xs_block m_xs_bottom_15">'+
						'		<h2 class="tt_uppercase color_dark">'+activity.title+'</h2>'+
						'	</div>'+
						'</div>'+
						'<div class="row clearfix m_bottom_15">'+
						'	<section class="col-lg-8 col-md-8 col-sm-8">'+
						'		<div class="photoframe r_corners shadow wrapper m_bottom_45">'+
						'			<img src="'+activity.entityimage[0].imagepath+'" alt=""'+
						'				class="tr_all_long_hover">'+
						'		</div>'+
						'	</section>'+
						'	<aside class="col-lg-4 col-md-4 col-sm-4 m_xs_bottom_30">'+
						'		<h5 class="fw_medium m_bottom_10">活动介绍</h5>'+
						'		<p class="m_bottom_15">'+introduction+
						'		</p>'+
						'	<div class="m_bottom_15">'+
						'		<span class="v_align_b f_size_big scheme_color fw_medium" id="activityPrice">￥'+activity.price+'</span>'+
						'	</div>'+
						'		<table class="about_project full_width m_bottom_20">'+
						'			<tr>'+
						'				<td>活动日期:</td>'+
						'				<td>'+(year+'年'+month+'月'+day+'日  ')+'</td>'+
						'			</tr>'+
						'			<tr>'+
						'				<td>报名截止日期:</td>'+
						'				<td>'+(year1+'年'+month1+'月'+day1+'日  ')+'</td>'+
						'			</tr>'+
						'			<tr>'+
						'				<td>已报名人数:</td>';
						
						html += '<td>'+activity.applyCount+' 人</td>';
					
						html+=
						'			</tr>'+
						'			<tr>'+
						'				<td>预定数量:</td>'+
						'				<td>'+activity.bookcount+' 人</td>'+
						'			</tr>'+
						'		</table>'+
						
						'		<a href="activity_detail.html?activityid='+activity.activityid+'"'+
						'			class="color_dark fw_medium d_inline_b m_bottom_25 "><button'+ //m_left_20
						'				class="tr_delay_hover r_corners button_type_14 bg_scheme_color color_light">'+
						'				<i class="fa fa-check m_right_6"></i>查看详情'+
						'			</button></a><br>'+
						'		<p class="d_inline_middle m_md_bottom_5">分享:</p>'+
						'		<div'+
						'			class="d_inline_middle m_left_5 m_md_left_0 addthis_widget_container">'+
						'			<div class="bdsharebuttonbox">'+
						'				<a href="#" class="bds_more" data-cmd="more"></a><a href="#"'+
						'					class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a'+
						'					href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a'+
						'					href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a'+
						'					href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a'+
						'					href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>'+
						'			</div>'+
						'		</div>'+
						'	</aside>'+
						'</div></div>';
					$("#activityList").append(html);
				});
			}
		});
	},
	getActivityDetail : function(activityid){
		
		$.ajax({
			url:common.getRootPath()+"/trip/activity/getActvityDetail.action",
			type:'GET',
			async: false,
			data:{activityid:activityid},
			dataType:'json',
			success:function(data){
				
				$("#activityPrice").html("￥"+data.price);
				$("#bookCount").html(data.bookcount+" 人");
				$("#activitytitle").html(data.title);
				$("#introduction").html(data.introduction);
				$.ajax({
					url:common.getRootPath()+"/trip/activityapply/getApplyPeopleNum.action",
					type:'GET',
					async: false,
					dataType:'json',
					data:{activityid:data.activityid},
					success:function(data)
					{
						$("#applyCount").html(data[0]+" 人");
					}
				});
				
				var date = new Date(data.begintime);
				var year = date.getFullYear();
				var month = date.getMonth()+1;
				var day = date.getDate();
				
				var date1 = new Date(data.endapplytime);
				var year1 = date1.getFullYear();
				var month1 = date1.getMonth()+1;
				var day1 = date1.getDate();
				
				var date2 = new Date(data.beginapplytime);
				var year2 = date2.getFullYear();
				var month2 = date2.getMonth()+1;
				var day2 = date2.getDate();
				
				$("#tripTime").html(" &nbsp;&nbsp;"+year+'年'+month+'月'+day+'日  ');
				$("#endapplytime").html(" &nbsp;&nbsp;"+year1+'年'+month1+'月'+day1+'日  ');
				$("#applytime").html(" &nbsp;&nbsp;"+year2+'年'+month2+'月'+day2+'日  ');
				$("#coverimage").attr("src",data.entityimage[0].imagepath);
				$("#tab-1").html(data.schedule);
				$("#tab-2").html(data.highlight);
				$("#tab-3").html(data.route);
				$("#tab-4").html(data.pricedesc);
			}
		});
		
	},
	getRecommendlineList : function(){
		
		$.ajax({
			url:common.getRootPath()+"/trip/recommendline/getRecommendlineList.action",
			type:'GET',
			async: false,
			dataType:'json',
			success:function(data){
				
				$.each(data,function(index,line){
					var html = 
						'<div class="portfolio_item t_xs_align_c '+line.recommendlinetype.linetypeid+'">'+
						'	<figure class="d_xs_inline_b d_mxs_block">'+
						'		<div'+
						'			class="photoframe with_buttons relative shadow r_corners wrapper m_bottom_15">'+
						'			<img src="'+line.entityimage[1].imagepath+'" alt=""'+
						'				class="tr_all_long_hover">'+
						'			<div class="open_buttons clearfix">'+
						'				<div class="f_left f_size_large tr_all_hover">'+
						'					<a href="'+line.entityimage[0].imagepath+'" role="button"'+
						'						class="color_light button_type_13 r_corners box_s_none d_block jackbox"'+
						'						data-group="portfolio" data-title="title 1"><i'+
						'						class="fa fa-camera"></i></a>'+
						'				</div>'+
						'				<div class="f_left m_left_10 f_size_large tr_all_hover">'+
						'					<a href="trip_detail.html?recommendlineid='+line.recommendlineid+'" role="button"'+
						'						class="color_light button_type_13 r_corners box_s_none d_block"><i'+
						'						class="fa fa-link"></i></a>'+
						'				</div>'+
						'			</div>'+
						'		</div>'+
						'		<figcaption class="t_xs_align_l">'+
						'			<h4 class="m_bottom_3">'+
						'				<a href="trip_detail.html?recommendlineid='+line.recommendlineid+'" class="color_dark"><b>'+line.title+'</b></a>'+
						'			</h4>'+
						'			<a href="javascript:;" class="color_dark">'+line.recommendlinetype.title+'</a>'+
						'		</figcaption>'+
						'	</figure>'+
						'</div>';
					$("#recommendline").append(html);
				});
			}
		});
	},
	getRecommendlineDetail : function(recommendlineid){
		
		$.ajax({
			url:common.getRootPath()+"/trip/recommendline/getRecommendlineDetail.action",
			type:'GET',
			async: false,
			data:{recommendlineid:recommendlineid},
			dataType:'json',
			success:function(datas){
				var data = datas[0];
				$.ajax({
					url:common.getRootPath()+"/trip/recommendlineapply/applyCount.action",
					dataType:"json",
					data:{recommendlineid:recommendlineid},
					success:function(data){
						$("#applyCount").html(data[0][0]+data[0][1]);
					}
				});
				
				$("#detailimage").attr("src",data.entityimage[0].imagepath);
				$("#detailtitle").html(data.title);
				$("#detailtitle1").html(data.title);
				$("#detailprice").html("￥"+data.price);
				$("#detailintroduction").html(data.introduction);

				$("#tripTime").html(data.departuretime);
				$("#applytime").html(data.beginapplytime);
				$("#endapplytime").html(data.endapplytime);
				
				
				$("#tab-1").html(data.feature);
				$("#tab-2").html(data.priceexplain);
				$("#tab-3").html(data.oneselfexpense);
				$("#tab-4").html(data.travelprompt);
				$("#tab-5").html(data.share);

				
			}
		});
	},
	getRecommendlinetypeList : function(){
		
		$.ajax({
			url:common.getRootPath()+"/trip/recommendlinetype/getRecommendlineType.action",
			type:'GET',
			async: false,
			dataType:'json',
			success:function(data){

				$.each(data,function(index,type){
					var html = '<option data-filter=".'+type.linetypeid+'" value="country">'+type.title+'</option>';
					$("#recommendlinetype").append(html);
				});
			}
		});
	},
	
	getBlogsList : function(){
		
		$.ajax({
			url:common.getRootPath()+"/trip/blogs/getBlogsList.action",
			type:'GET',
			async: false,
			dataType:'json',
			success:function(data){
				$.each(data,function(index,blogs){
					var html = 
						'<div class="portfolio_item t_xs_align_c country">'+
						'	<figure class="d_xs_inline_b d_mxs_block">'+
						'		<div'+
						'			class="photoframe relative shadow r_corners wrapper m_bottom_15">'+
						'			<a href="blog_detail.html?blogid='+blogs.blogid+'" class="color_dark"> '+
						'           <img src="'+(blogs.entityimage.length==0?"images/portfolio_img_07.jpg":blogs.entityimage[0].imagepath)+'" width="340" height="340" alt=""'+
						'				class="tr_all_long_hover"> </a>'+
//						'			<div class="open_buttons clearfix">'+
//						'				<div class="f_left f_size_large tr_all_hover">'+
//						'					<a href="'+(blogs.entityimage.length==0?"images/portfolio_img_07.jpg":blogs.entityimage[0].imagepath)+'" role="button"'+
//						'						class="color_light button_type_13 r_corners box_s_none d_block jackbox"'+
//						'						data-group="portfolio" data-title="title 1"><i'+
//						'						class="fa fa-camera"></i></a>'+
//						'				</div>'+
//						'				<div class="f_left m_left_10 f_size_large tr_all_hover">'+
//						'					<a href="blog_detail.html?blogid='+blogs.blogid+'" role="button"'+
//						'						class="color_light button_type_13 r_corners box_s_none d_block"><i'+
//						'						class="fa fa-link"></i></a>'+
//						'				</div>'+
//						'			</div>'+
						'		</div>'+
						'		<figcaption class="t_xs_align_l">'+
						'			<h4 class="m_bottom_3">'+
						'				<a href="blog_detail.html?blogid='+blogs.blogid+'" class="color_dark"><b>'+blogs.title+'</b></a>'+
						'			</h4>'+
						'			<a href="javascript:;" class="color_dark">'+blogs.user.userName+'</a>'+
						'		</figcaption>'+
						'	</figure>'+
						'</div>';
					$("#blogsList").append(html);
				});
			}
		});
	},

	getBlogsDetail : function(blogid){
		
		$.ajax({
			url:common.getRootPath()+"/trip/blogs/getBlogsDetail.action",
			type:'GET',
			async: false,
			dataType:'json',
			data:{blogid:blogid},
			success:function(data){
				$("#title").html(data.title);
				$("#content").html(data.htmlpath);
			}
		});
	},
	getBlogsTypeList : function(){
		$.ajax({
			url:common.getRootPath()+"/trip/blogstype/getBlogstypeList.action",
			type:'GET',
			async: false,
			dataType:'json',
			success:function(data){
				$.each(data,function(index,type){
					$("#blogstypeselector").append("<option value='"+type.blogstypeid+"'>"+type.blogstypename+"</option>");
				});
			}
		});
	},
	
	
	
	
	
	getPartner : function(maxCount){
		
		$.ajax({
			url:common.getRootPath()+"/provider/provider/getAllProvider.action",
			type:'GET',
			async: false,
			data:{maxCount:maxCount},
			dataType:'json',
			success:function(data){
				$.each(data.providerList,function(index,provider){
					
					$("#partnerList").append(
					'<div class="product_item location'+provider.direction+'">'+
					'		<figure'+
					'		class="r_corners photoframe shadow relative animate_ftb long">'+
					'		<!--product preview-->'+
					'		<a href="partner_detail.html?providerId='+provider.providerId+'" class="d_block relative wrapper pp_wrap"> <img'+
					'			src="'+provider.provider_image[0].imagepath+'" class="tr_all_hover" alt="">'+
					'		</a>'+
					'		<!--description and price of product-->'+
					'		<figcaption>'+
					'			<h5 class="m_bottom_10">'+
					'				<a href="partner_detail.html" class="color_dark">'+provider.name+'</a>'+
					'			</h5>'+
					'		</figcaption>'+
					'	</figure>'+
					'</div>'
					);
				});
				trip.baiduMap(data.mapList);//data.mapList
			}
		});
	},
	baiduMap:function (markerArr){

		var styleJson = [ {
			"featureType" : "water",
			"elementType" : "all",
			"stylers" : {
				"color" : "#72b8fe"
			}
		}, {
			"featureType" : "road",
			"elementType" : "geometry.fill",
			"stylers" : {
				"color" : "#ffffff"
			}
		}, {
			"featureType" : "road",
			"elementType" : "geometry.stroke",
			"stylers" : {
				"color" : "#bababa"
			}
		}, {
			"featureType" : "road",
			"elementType" : "labels.text.fill",
			"stylers" : {
				"color" : "#767676"
			}
		}, {
			"featureType" : "road",
			"elementType" : "labels.text.stroke",
			"stylers" : {
				"color" : "#ffffff"
			}
		}, {
			"featureType" : "land",
			"elementType" : "all",
			"stylers" : {
				"color" : "#CEE79C"
			}
		} ];
		
// var markerArr = [ {
// title : "名称：广州火车站",
// point : "113.264531,23.157003",
// address : "广东省广州市广州火车站",
// tel : "12306"
// }, {
// title : "名称：广州塔（赤岗塔）",
// point : "113.330934,23.113401",
// address : "广东省广州市广州塔（赤岗塔） ",
// tel : "18500000000"
// }, {
// title : "名称：广州动物园",
// point : "113.312213,23.147267",
// address : "广东省广州市广州动物园",
// tel : "18500000000"
// }, {
// title : "名称：天河公园",
// point : "113.372867,23.134274",
// address : "广东省广州市天河公园",
// tel : "18500000000"
// }
//
// ];

// function map_init() {
			var map = new BMap.Map("baidumap"); // 创建Map实例
			var point = new BMap.Point(102.1114660877,27.8571076151); // 地图中心点
			map.centerAndZoom(point, 9); // 初始化地图,设置中心点坐标和地图级别。
			map.enableScrollWheelZoom(true); // 启用滚轮放大缩小
			// 向地图中添加缩放控件
			var ctrlNav = new window.BMap.NavigationControl({
				anchor : BMAP_ANCHOR_TOP_LEFT,
				type : BMAP_NAVIGATION_CONTROL_LARGE
			});
			map.addControl(ctrlNav);
//			map.setMapStyle(mapStyle);

			// 向地图中添加缩略图控件
			var ctrlOve = new window.BMap.OverviewMapControl({
				anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
				isOpen : 1
			});
			map.addControl(ctrlOve);

			// 向地图中添加比例尺控件
			var ctrlSca = new window.BMap.ScaleControl({
				anchor : BMAP_ANCHOR_BOTTOM_LEFT
			});
			map.addControl(ctrlSca);
			map.setMapStyle({styleJson:styleJson});
			var point = new Array(); // 存放标注点经纬信息的数组
			var marker = new Array(); // 存放标注点对象的数组
			var info = new Array(); // 存放提示信息窗口对象的数组
			for (var i = 0; i < markerArr.length; i++) {
				var p0 = markerArr[i].point.split(",")[0]; //  
				var p1 = markerArr[i].point.split(",")[1]; // 按照原数组的point格式将地图点坐标的经纬度分别提出来
				point[i] = new window.BMap.Point(p0, p1); // 循环生成新的地图点
				marker[i] = new window.BMap.Marker(point[i]); // 按照地图点坐标生成标记
				map.addOverlay(marker[i]);
				marker[i].setAnimation(BMAP_ANIMATION_BOUNCE); // 跳动的动画
				var label = new window.BMap.Label(markerArr[i].title, {
					offset : new window.BMap.Size(10, -15)
				});
				marker[i].setLabel(label);
				info[i] = new window.BMap.InfoWindow(
						"<p >名称："
								+ markerArr[i].title + "<br />地址："
								+ markerArr[i].address + "<br /> 电话：" + markerArr[i].tel
								+ "<br /></p>"); // 创建信息窗口对象
				
			}
//			for (var i=0; i < marker.length; i++) {
//				marker[i].addEventListener("mouseover",function(data){
//					this.openInfoWindow(info[1]);
//				});
//			}
//		}
			$("#locationAll").children("li").click(function(){
				var filter = $(this).children("button").attr("data-filter");
				switch(filter){
					case ".location04"://东
						var point = new BMap.Point(102.832818,28.013719); // 地图中心点
						map.centerAndZoom(point, 9); // 初始化地图,设置中心点坐标和地图级别。
						break;
					case ".location03"://西
						var point = new BMap.Point(100.953057,28.360344); // 地图中心点
						map.centerAndZoom(point, 9); // 初始化地图,设置中心点坐标和地图级别。
						break;
					case ".location02"://南
						var point = new BMap.Point(102.191734,27.331194); // 地图中心点
						map.centerAndZoom(point, 9); // 初始化地图,设置中心点坐标和地图级别。
						break;
					case ".location01"://北
						var point = new BMap.Point(102.29397,29.235485); // 地图中心点
						map.centerAndZoom(point, 9); // 初始化地图,设置中心点坐标和地图级别。
						break;
				}
				
			});
		
	},
	getProviderDetail : function(providerId){
		
		$.ajax({
			url:common.getRootPath()+"/provider/provider/getProviderDetail.action",
			type:'GET',
			async: false,
			data:{providerId:providerId},
			dataType:'json',
			success:function(data){
				$("#providerBigImg").attr("src",data.provider_image[0].imagepath);
				$("#providerDesc").html(data.description);
				$("#providerProducts").children().remove();
				$("#providerName").text(data.name);
				$("#providerName1").text(data.name);
				$.each(data.productList,function(index,product){
					var html =
						'<article class="m_bottom_20 clearfix">'+
						'	<a href="provider_product_page.html?productId='+product.productId+'&providerId='+product.providerId+'"'+
						'		class="photoframe d_block d_xs_inline_b f_xs_none wrapper shadow f_left m_right_20 m_bottom_10 r_corners">'+
						'		<img src="'+product.productimglist[0].path+'" class="tr_all_long_hover"'+
						'		alt="">'+
						'	</a>'+
						'	<div class="mini_post_content">'+
						'		<h4 class="m_bottom_5">'+
						'			<a href="provider_product_page.html?productId='+product.productId+'&providerId='+product.providerId+'" class="color_dark fw_medium">'+product.productName+'</a>'+
						'		</h4>'+
						'		<p class="f_size_medium m_bottom_10">';
					
						$.ajax({
							url:common.getRootPath()+"/eshop/product/getProductReviewById.action",
							type:"GET",
							async: false,
							data:{productId:product.productId, maxcount:0},
							dataType:'json',
							success:function(rData){
								html += '<a href="javascript:;" class="color_dark">'+rData.length+'个评价</a>';
							}
						});
					
						html+=
						'		</p>'+
						'		<hr>'+
						'		<hr class="m_bottom_15">'+
						'		<p class="m_bottom_10">'+product.description+'</p>'+
						'		<a href="provider_product_page.html?productId='+product.productId+'&providerId='+product.providerId+'" class="tt_uppercase f_size_large">了解更多</a>'+
						'	</div>'+
						'</article>';
					
					$("#providerProducts").append(html);
				
				});
			}
		});
		
	}
};










