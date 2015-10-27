/**
 * 
 * 通用js
 * 
 */
    
function remove(obj){
		var productId = $(obj).children("i").attr("key");
		common.operationCart("remove", productId);
		common.operationCart("getCart", '');
}
var common = {

	getParameter:function(name) {
		   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
		   var r = window.location.search.substr(1).match(reg);
		   if (r!=null) return (r[2]); return null;
	},
	getRootPath: function () {//获得根目录
		var strFullPath = window.document.location.href;
	    var strPath = window.document.location.pathname;
	    var pos = strFullPath.indexOf(strPath);
	    var prePath = strFullPath.substring(0, pos);
//	    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	    return (prePath);// + postPath);
	},
	getBrand_vendor:function(){
		$.ajax({
			url:common.getRootPath()+'/dict/brand_vendor/getBrandVendor.action',
			type:"GET",
			async: true,
			dataType:'json',
			success:function(data){
				$.each(data,function(index,brand){
					$("#brandcondition").append('<option value="'+brand.brandid+'" key="'+brand.brandid+'">'+brand.brandname+'</option>');
				});
			},
			error:function(){
//				alert("所有品牌查询超时");
			}
		});
	},
	getAllProductType: function(){
		$.ajax({
			url:common.getRootPath()+"/eshop/product_type/getAllProductType.action",
			type:"GET",
			async: true,
			dataType:'json',
			success:function(data){

				$(".tit").children("li").remove();
				$.each(data,function(index,root){
					var html = 
						'<li class="mod_cate">'+
						'<h2>'+
						'	<i class="arrow_dot f_right"></i>'+
						'	<a href="category_list.html?level=1&productTypeId='+root.root.productTypeId+'">'+root.root.typeName+'</a>'+
						'</h2><p class="mod_cate_r">';
						
						for(var i=0;i<2;i++){
							if(root.senode[i]!=null){
								var second = root.senode[i];
								html+='<a href="category_list.html?listtype=producttypeId&level=2&productTypeId='+second.second.productTypeId+'">'+second.second.typeName+'</a>';
							}
						}
						html+='<div class="mod_subcate" style="display: none;">'+
						'	<div class="mod_subcate_main">';
					
						$.each(root.senode,function(index,seconds){
							
							html+='<dl><dt><a href="category_list.html?listtype=producttypeId&level=2&productTypeId='+seconds.second.productTypeId+'">'+seconds.second.typeName+'</a></dt><dd>';
							$.each(seconds.thirdType,function(index,third){
								html+='<a href="category_list.html?level=3&productTypeId='+third.productTypeId+'">'+third.typeName+'</a>';
							});
							html+='</dd></dl>';
						});
						html+=	'	</div></li>';
					$(".tit").append(html);
				});
			},
			error:function(data){
				alert("请求超时");
			}
		});
	},
	
	getTopProductType: function (divid) {//获得一级产品类型
		$.ajax({
			url:common.getRootPath()+"/eshop/product_type/getTopProductType.action",
			type:"GET",
			async: false,
			dataType:'json',
			success:function(data){
				
				var html = "<select class='d_none'><option data-filter='*' value='All'>全部</option>";
				
				$.each(data,function(index,root){
					html+="<option data-filter='."+root.productTypeId+"' value='"+root.productTypeId+"'>"+root.typeName+"</option>";			
				});
				html+="</select>";
				$("#"+divid).append(html);
			},
			error:function(data){
				alert("请求超时");
			}
		});
	},
	isHot:function(productId){
		var bool = false;
		$.ajax({
			url:common.getRootPath()+"/eshop/product_ext_hotProduct/getHotProduct.action",
			type:"GET",
			async: false,
			dataType:'json',
			data:{type:1,typeId:productId},
			success:function(data){
				if(data.count!=null && data.count!=0){
					bool = true;
				}
			}
		});
		return bool;
	},
	isPromote:function(data){

		bool = false;
		if(data.promoteflag!=null && data.promoteflag==1 && data.promoteEndTime<new Date()){
			bool = true;
		}
		return bool;
	},
	operationCart:function(action,productId,cartType){
		if(action=='add'){
			productId = $(productId).attr("key");
		}
		var addcount = $("#addCount").val();
		var url=common.getRootPath()+"/eshop/product/operationCart.action";
		$.ajax({
			url:url,
			type:"POST",
			async: false,
			data:{productId:productId,action:action,addcount:addcount,cartType:cartType},
			dataType:'text',
			success:function(d){
				if('getCart' == action){
					$('#carUl').children("li").remove();
					var pCount = 0;
					eval("var data = "+d);
					if(data.totalPrice!=-1){
						$.each(data.product,function(index,object){
							pCount+=1;
							html = 	'<li>'+
									'	<div class="clearfix">'+
									'		<!--product image-->'+
									'		<img class="f_left m_right_10"'+ //productimglist productimgsList
									'			src="'+object.product.productimglist[0].path+'" width="60px" alt="">'+
									'		<!--product description-->'+
									'		<div class="f_left product_description">'+
									'			<a href="#" class="color_dark m_bottom_5 d_block">'+object.product.productName+'</a> <span'+
									'				class="f_size_medium">产品代码 '+object.product.productId+'</span>'+
									'		</div>'+
									'		<!--product price-->'+
									'		<div class="f_left f_size_medium">'+
									'			<div class="clearfix">';
											if(object.cartType==0)
											{
										        if(object.promoteflag == 1 ){
										        	html+=object.productCount+ 'x <b class="color_dark">￥'+object.promotePrice+'</b>';
										        }else{
										        	html+=object.productCount+ 'x <b class="color_dark">￥'+object.originalPrice+'</b>';
										        }
											}
											else if(object.cartType==1)
											{
									        	html+=object.productCount+ 'x <b class="color_dark">￥'+object.shopPrice+'</b>';
											}
											html+='	</div>'+
											
									'			<button class="close_product color_dark tr_hover deleteOfCart" type="button" >'+
									'				<i class="fa fa-times" key="'+object.product.productId+'"></i>'+
									'			</button>'+
									'		</div>'+
									'	</div>'+
									'</li>';
							$('#carUl').append(html);
						});
						
						$(".deleteOfCart").click(function(){
				    		setTimeout(remove,1000,this);
				    	});
						
					}else{
						html = 	
						'<li>'+
						'	<div class="clearfix">购物车为空！</div>'+
						'</li>';
						$('#carUl').append(html);
					}
					$("#cartCount").text(pCount);
					$("#totalPrice").text('￥'+data.totalPrice);
					$("#sumPrice").text('￥'+data.totalPrice);
				}
				else{
					$("#addCount").val("1");
					common.operationCart('getCart','');
				}
			},
			error:function(){
			}
		});
	},

	logStatus:function(){
		$.ajax({
			url:'user/user/getLoginUser.action',
			type:'POST',
			dataType:'text',
			success:function(tdata){
				if(tdata=='nologin'){
					$("#loginheader").html('<p class="f_size_small t_align_l">尊敬的顾客您好，您可以'+
							'<a href="#" data-popup="#login_popup" class="scheme_color">登录</a> 或注册'+
							'<a href="register.html" class="scheme_color">个人用户</a>&nbsp<a href="ent_register.html" class="scheme_color">企业用户</a></p>');
					$("#orderoperation").html('');
				}else{
					eval("var data = "+tdata);
					$("#loginheader").html('<p class="f_size_small t_align_l">尊敬的'+data.loginuser.user.userName+',您好，欢迎来到彝家优品 &nbsp;'+
							'[<a href="javascript:user.logout();" class="scheme_color">退出</a>] [<a href="update_password.html" class="scheme_color" >修改密码</a>]</p>');
					if(data.loginuser.user.userType=='1'){
					$("#orderoperation").html('<ul class="d_inline_b horizontal_list clearfix f_size_small users_nav">'+
					        '<li><a href="orders_list.html" class="default_t_color">商城订单</a></li>'+
					        '<li><a href="orders_list2.html?orderType=2" class="default_t_color">团购订单</a></li>'+
					        '<li><a href="register_address.html" class="default_t_color">收货地址</a></li>'+
					         '<li><a href="checkout.html?checkouttype=01" class="default_t_color">结算</a></li>'+
				            '</ul>');
			   	    }else{
			   	    	$("#orderoperation").html('<ul class="d_inline_b horizontal_list clearfix f_size_small users_nav">'+
						        '<li><a href="orders_list2.html?orderType=3" class="default_t_color">大宗交易订单</a></li>'+
						        '</ul>');
			   	    }

				   	var type = common.getParameter("checkouttype");
				   	//如果是结算页
				   	if(type!=null || type !="" || type!=undefined)
			   		{
				   		if((type=='01' || type=='02') && data.loginuser.user.userType=='2')
			   			{
				   			alert('企业用户不支持此类订单');
				   			window.location.href="/index.html";
				   			return false;
			   			}
				   		else if(type=='03' && data.loginuser.user.userType=='1')
			   			{
				   			alert('个人用户不支持此类订单');
				   			window.location.href="/index.html";
				   			return false;
			   			}
			   		}
				}
			}
		});
	},
	blogStatus:function(){
		$.ajax({
			url:'user/user/personLogin.action',
			type:'POST',
			dataType:'text',
			success:function(tdata){
				if(tdata=='nologin'){
					alert("您需要登录个人用户");
					window.location.href="/user_login.html?returnURI="+window.location.href;
				}
			}
		});
	},
	navivations:function(){
		$("#navigationurl").html(
				'<li class="relative f_xs_none m_xs_bottom_5"><a '+
				'href="index.html"'+
				'	class="tr_delay_hover color_light f_size_large"><b>首&nbsp;&nbsp;页</b></a>'+
				'</li>'+
				'<li class="relative f_xs_none m_xs_bottom_5"><a'+
				'	href="category_list.html?listtype=new"'+
				'	class="tr_delay_hover color_light f_size_large"><b>商&nbsp;&nbsp;城</b></a>'+
				'</li>'+
				'<li class="relative f_xs_none m_xs_bottom_5"><a'+
				'	href="groupbuy_list.html"'+
				'	class="tr_delay_hover color_light f_size_large"><b>团&nbsp;&nbsp;购</b></a>'+
				'</li>'+
				'<li class="relative f_xs_none m_xs_bottom_5"><a href="bigdeal_list.html"'+
				'	class="tr_delay_hover color_light f_size_large"><b>大宗交易</b></a>'+
				'</li>'+
				'<li class="relative f_xs_none m_xs_bottom_5"><a'+
				'	href="partner_list.html"'+
				'	class="tr_delay_hover color_light f_size_large"><b>合&nbsp;作&nbsp;社</b></a>'+
				'</li>'+
				'<li class="relative f_xs_none m_xs_bottom_5"><a'+
				'	href="activity_list.html" class="tr_delay_hover color_light f_size_large"><b>趣&nbsp;采&nbsp;摘</b></a>'+
				'</li>'+
				'<li class="relative f_xs_none m_xs_bottom_5"><a'+
				'	href="trip_list.html" class="tr_delay_hover color_light f_size_large"><b>趣&nbsp;旅&nbsp;游</b></a>'+
				'</li>'+
				'<li class="relative f_xs_none m_xs_bottom_5"><a'+
				'	href="blog_list.html"'+
				'	class="tr_delay_hover color_light f_size_large"><b>趣&nbsp;分&nbsp;享</b></a></li>');
		
	},
	collection:function(){
		
		$("#collectionurl").html(
				'<li class="d_sm_none d_xs_block"><a role="button" href="wishlist.html"'+
				'	class="button_type_1 color_dark d_block bg_light_color_1 r_corners tr_delay_hover box_s_none"><i'+
				'		class="fa fa-heart-o f_size_ex_large"></i>'+
				'		<span class="count circle t_align_c" id="wishCount">0</span>'+
				'		</a></li>'+
				'<li class="m_left_5 relative container3d" id="shopping_button">'+
				'	<a role="button" href="wishlist.html"'+
				'	class="button_type_3 color_light bg_scheme_color d_block r_corners tr_delay_hover box_s_none">'+
				'		<span class="d_inline_middle shop_icon"> <i'+
				'			class="fa fa-shopping-cart"></i> <span'+
				'			class="count tr_delay_hover type_2 circle t_align_c" id="cartCount">0</span>'+
				'	</span> <b id="sumPrice"></b>'+
				'</a>'+
				'	<div class="shopping_cart top_arrow tr_all_hover r_corners">'+
				'		<div class="f_size_medium sc_header">最近添加</div>'+
				'		<ul class="products_list" id="carUl">'+
				'		</ul>'+
				'		<ul class="total_price bg_light_color_1 t_align_r color_dark">'+
				'			<li class="m_bottom_10">折扣: <span'+
				'				class="f_size_large sc_price t_align_l d_inline_b m_left_15">￥0.00</span></li>'+
				'			<li>总价: <b'+
				'				class="f_size_large bold scheme_color sc_price t_align_l d_inline_b m_left_15" id="totalPrice">￥0.00</b></li>'+
				'		</ul>'+
				'		<div class="sc_footer t_align_c">'+
				'			<a href="checkout.html?checkouttype=01" role="button"'+
				'				class="button_type_4 bg_scheme_color d_inline_middle r_corners tr_all_hover color_light">去结算</a>'+
				'		</div>'+
				'	</div>'+
				'</li>');
	},
	search : function(){
		$("#search").append(
			'<form class="relative type_2" action="search_list.html" method="get" role="search" id="search">'+
			'<input type="text" placeholder="搜索" name="searchKey" id="searchKey"'+
			'		class="r_corners f_size_medium full_width">'+
			'<button class="f_right search_button tr_all_hover f_xs_none" type="submit">'+
			'	<i class="fa fa-search"></i>'+
			'</button></form>');
	},
	loginForm : function(){
		$("#loginformh").after(
		'<form id="userLogin" action="user/user/login.action" method="post">'+
				'<ul>'+
				'<li class="m_bottom_15"><label for="username"'+
				'	class="m_bottom_5 d_inline_b">用户名</label><br> <input'+
				'	type="text" name="userName" id="username" placeholder="用户名"'+
				'	class="r_corners full_width"></li>'+
				'<li class="m_bottom_25"><label for="password"'+
				'	class="m_bottom_5 d_inline_b">密码</label><br> <input'+
				'	type="password" name="password" id="password" class="r_corners full_width">'+
				'</li>'+
				'<li class="m_bottom_15"><input type="checkbox" class="d_none"'+
				'	id="checkbox_10"><label for="checkbox_10">记住我</label></li>'+
				'<li class="clearfix m_bottom_30">'+
				'	<button'+
				'		class="button_type_4 tr_all_hover r_corners f_left bg_scheme_color color_light f_mxs_none m_mxs_bottom_15" type="button" id="clogin">登录</button>'+
				'	<div class="f_right f_size_medium f_mxs_none">'+
				'		<a href="forget_password.html" class="color_dark">忘记密码?</a><br> <a href="#"'+
				'	</div>'+
				'</li>'+
			'</ul>'+
		'</form>');
	},
	footer : function(){
		
		$("#footer").html(
			'<div class="footer_top_part">'+
			'	<div class="container">'+
			'	<div class="row clearfix">'+
			'		<div class="col-lg-3 col-md-3 col-sm-3 m_xs_bottom_30">'+
			'			<h3 class="color_light_2 m_bottom_20">关于我们</h3>'+
			'			<p class="m_bottom_15">彝家优品是一个融合生态农产品，电商，团购和大宗交易于一体的综合在线购物商城，同时整合以农产品原产地为目的地的体验式旅游生活。'+
			'				彝家优品是一个融合生态农产品，电商，团购和大宗交易于一体的综合在线购物商城，同时整合以农产品原产地为目的地的体验式旅游生活。</p>'+
			'		</div>'+
			'		<div class="col-lg-2 col-md-2 col-sm-2 m_xs_bottom_30">'+
			'			<h3 class="color_light_2 m_bottom_20">服务</h3>'+
			'			<ul class="vertical_list">'+
			'				<li><a class="color_light tr_delay_hover" href="orders_list.html">商城订单<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'				<li><a class="color_light tr_delay_hover" href="orders_list2.html?orderType=2">团购订单<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'				<li><a class="color_light tr_delay_hover" href="orders_list2.html?orderType=3">大宗交易订单<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'				<li><a class="color_light tr_delay_hover" href="wishlist.html">收藏夹<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'			</ul>'+
			'		</div>'+
			'		<div class="col-lg-2 col-md-2 col-sm-2 m_xs_bottom_30">'+
			'			<h3 class="color_light_2 m_bottom_20">信息</h3>'+
			'			<ul class="vertical_list">'+
			'				<li><a class="color_light tr_delay_hover" href="/programa_detail.html?programaTypeId=1">关于我们<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'				<li><a class="color_light tr_delay_hover" href="/programa_detail.html?programaTypeId=2">配送条款<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'				<li><a class="color_light tr_delay_hover" href="/programa_detail.html?programaTypeId=3">隐私策略<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'				<li><a class="color_light tr_delay_hover" href="/programa_detail.html?programaTypeId=4">条款<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'			</ul>'+
			'		</div>'+
			'		<div class="col-lg-2 col-md-2 col-sm-2 m_xs_bottom_30">'+
			'			<h3 class="color_light_2 m_bottom_20">目录</h3>'+
			'			<ul class="vertical_list">'+
			'				<li><a class="color_light tr_delay_hover" href="/programa_detail.html?programaTypeId=5">购物流程<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'				<li><a class="color_light tr_delay_hover" href="/programa_detail.html?programaTypeId=6">售后政策<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'				<li><a class="color_light tr_delay_hover" href="/programa_detail.html?programaTypeId=7">返修/退换货<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'				<li><a class="color_light tr_delay_hover" href="/programa_detail.html?programaTypeId=8">退款说明<i'+
			'						class="fa fa-angle-right"></i></a></li>'+
			'			</ul>'+
			'		</div>'+
			'		<div class="col-lg-3 col-md-3 col-sm-3">'+
			'			<h3 class="color_light_2 m_bottom_20">联系我们</h3>'+
			'			<ul class="c_info_list">'+
			'				<li class="m_bottom_10">'+
			'					<div class="clearfix m_bottom_15">'+
			'						<i class="fa fa-map-marker f_left"></i>'+
			'						<p class="contact_e">'+
			'							西昌市石塔街19号（州供销社院内）'+
			'						</p>'+
			'					</div>'+
			'				</li>'+
			'				<li class="m_bottom_10">'+
			'					<div class="clearfix m_bottom_10">'+
			'						<i class="fa fa-phone f_left"></i>'+
			'						<p class="contact_e">0834-3233223</p>'+ //400-888-1888
			'					</div>'+
			'				</li>'+
			'				<li class="m_bottom_10">'+
			'					<div class="clearfix m_bottom_10">'+
			'						<i class="fa fa-envelope f_left"></i> <a'+
			'							class="contact_e color_light" href="mailto:#">2858006018@qq.com</a>'+
			'					</div>'+
			'				</li>'+
			'				<li>'+
			'					<div class="clearfix">'+
			'						<i class="fa fa-clock-o f_left"></i>'+
			'						<p class="contact_e">'+
			'							周一 - 周五: 08.00-20.00 <br>周六: 09.00-15.00<br> 周日: 休息'+
			'						</p>'+
			'					</div>'+
			'				</li>'+
			'			</ul>'+
			'		</div>'+
			'	</div>'+
			'</div>'+
			'</div>'+
			'<div class="footer_bottom_part">'+
			'	<div class="container clearfix t_mxs_align_c">'+
			'		<p class="f_left f_mxs_none m_mxs_bottom_10">'+
			'			<img src="/images/partner.png" align="bottom"/>Copyright &copy; 2015 彝家优品. 蜀ICP备15008810号'+
			'		</p>'+
			'		<ul'+
			'			class="f_right horizontal_list clearfix f_mxs_none d_mxs_inline_b">'+
			'			<li><img src="images/payment_img_1.png" alt=""></li>'+
			'			<li class="m_left_5"><img src="images/payment_img_2.png"'+
			'				alt=""></li>'+
			'			<li class="m_left_5"><img src="images/payment_img_3.png"'+
			'				alt=""></li>'+
			'			<li class="m_left_5"><img src="images/payment_img_4.png"'+
			'				alt=""></li>'+
			'			<li class="m_left_5"><img src="images/payment_img_5.png"'+
			'				alt=""></li>'+
			'		</ul>'+
			'	</div>'+
			'</div>'
		);
	},
	broadside : function(){
		$("#broadside").html(
			'<li class="relative">'+
			'	<button class="sw_button t_align_c sinawb">'+
			'		<i class="fa fa-facebook"></i>'+
			'	</button>'+
			'	<div class="sw_content">'+
			'		<iframe width="100%" height="300" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=300&fansRow=2&ptype=0&speed=0&skin=1&isTitle=1&noborder=1&isWeibo=1&isFans=1&uid=5575480347&verifier=37ed6535&dpc=1"></iframe>'+
			'	</div>'+
			'</li>'+
			'<!--wechat-->'+
			'<li class="relative">'+
			'	<button class="sw_button t_align_c wechat">'+
			'		<i class="fa fa-twitter"></i>'+
			'	</button>'+
			'	<div class="sw_content">'+
			'		<img src="images/dlsyph.jpg" alt="">'+
			'	</div>'+
			'</li>'+
			'<!--contact info-->'+
			'<li class="relative" id="contact">'+
			'</li>');
	},
	contact : function(){
		
		$("#contact").html(
			'<button class="sw_button t_align_c googlemap">'+
			'	<i class="fa fa-map-marker"></i>'+
			'</button>'+
			'<div class="sw_content">'+
			'	<h3 class="color_dark m_bottom_20">我的位置</h3>'+
			'	<ul class="c_info_list">'+
			'		<li class="m_bottom_10">'+
			'			<div class="clearfix m_bottom_15">'+
			'				<i class="fa fa-map-marker f_left color_dark"></i>'+
			'				<p class="contact_e">'+
			'					西昌市石塔街19号（州供销社院内）'+
			'				</p>'+
			'			</div>'+
			'			<div style="width: 200px; height: 160px; border: 1px solid gray"'+
			'				id="map"></div>'+
			'		</li>'+
			'		<li class="m_bottom_10">'+
			'			<div class="clearfix m_bottom_10">'+
			'				<i class="fa fa-phone f_left color_dark"></i>'+
			'				<p class="contact_e">0834-3233223</p>'+
			'			</div>'+
			'		</li>'+
			'		<li class="m_bottom_10">'+
			'			<div class="clearfix m_bottom_10">'+
			'				<i class="fa fa-envelope f_left color_dark"></i> <a'+
			'					class="contact_e default_t_color" href="mailto:#">2858006018@qq.com</a>'+
			'			</div>'+
			'		</li>'+
			'		<li>'+
			'			<div class="clearfix">'+
			'				<i class="fa fa-clock-o f_left color_dark"></i>'+
			'				<p class="contact_e">'+
			'					周一 - 周五: 08.00-20.00 <br>周六: 09.00-15.00<br> 周日: 休息'+
			'				</p>'+
			'			</div>'+
			'		</li>'+
			'	</ul>'+
			'</div>');
		
	}
};
