<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<!--[if IE 9 ]>
	<html class="ie9" lang="en">
<![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <base href="<%=basePath%>">
<title>彝家优品</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!--meta info-->
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="icon" type="image/ico" href="images/fav.ico">
<!--stylesheet include-->
<link rel="stylesheet" type="text/css" media="all"
	href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="all"
	href="css/owl.carousel.css">
<link rel="stylesheet" type="text/css" media="all"
	href="css/owl.transitions.css">
<link rel="stylesheet" type="text/css" media="all" href="css/style.css">
<!--font include-->
<link href="css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="js/qushop/common.js"></script>
<script type="text/javascript" src="js/qushop/dict.js"></script>
<script type="text/javascript" src="js/qushop/ad.js"></script>
<script type="text/javascript" src="js/qushop/product.js"></script>
<script type="text/javascript" src="js/qushop/shopproduct.js"></script>
<script type="text/javascript" src="js/qushop/pubfunc.js"></script>
<script type="text/javascript" src="js/qushop/order.js"></script>
<script src="js/qushop/user.js" charset="utf-8"></script>
</head>
<body>
	<!--wide layout-->
	<div class="wide_layout relative w_xs_auto">
		<!--[if (lt IE 9) | IE 9]>
				<div style="background:#fff;padding:8px 0 10px;">
				<div class="container" style="width:1170px;"><div class="row wrapper"><div class="clearfix" style="padding:9px 0 0;float:left;width:83%;"><i class="fa fa-exclamation-triangle scheme_color f_left m_right_10" style="font-size:25px;color:#e74c3c;"></i><b style="color:#e74c3c;">Attention! This page may not display correctly.</b> <b>You are using an outdated version of Internet Explorer. For a faster, safer browsing experience.</b></div><div class="t_align_r" style="float:left;width:16%;"><a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode" class="button_type_4 r_corners bg_scheme_color color_light d_inline_b t_align_c" target="_blank" style="margin-bottom:2px;">Update Now!</a></div></div></div></div>
			<![endif]-->
		<!--markup header-->
		<header role="banner">
			<!--header top part-->
			<section class="h_top_part">
				<div class="container">
					<div class="row clearfix">
						<div class="col-lg-4 col-md-4 col-sm-3 t_xs_align_c" id="loginheader">
						</div>
						<nav class="col-lg-4 col-md-4 col-sm-6 t_align_c t_xs_align_c" id="orderoperation">
						</nav>
					</div>
				</div>
			</section>
			<!--header bottom part-->
			<section class="h_bot_part container">
				<div class="clearfix row">
					<div class="col-lg-6 col-md-6 col-sm-4 t_xs_align_c">
						<a href="index.html"
							class="logo m_xs_bottom_15 d_xs_inline_b f_left"> <img
							src="images/logo.png" alt="">
						</a>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-8">
						<div class="row clearfix">
							<div
								class="col-lg-6 col-md-6 col-sm-6 t_align_r t_xs_align_c m_xs_bottom_15">
								<dl class="l_height_medium">
									<dt class="f_size_small">联系我们:</dt>
									<dd class="f_size_ex_large color_dark">
										<b>0834-3233223</b>
									</dd>
								</dl>
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6">
								<form class="relative type_2" role="search">
									<input type="text" placeholder="Search" name="search"
										class="r_corners f_size_medium full_width">
									<button class="f_right search_button tr_all_hover f_xs_none">
										<i class="fa fa-search"></i>
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</section>
			<script type="text/javascript">
					common.logStatus();
			</script>
			<!--main menu container-->
			<div class="container">
				<section
					class="menu_wrap type_2 relative clearfix t_xs_align_c m_bottom_20">
					<!--button for responsive menu-->
					<button id="menu_button"
						class="r_corners centered_db d_none tr_all_hover d_xs_block m_bottom_15">
						<span class="centered_db r_corners"></span> <span
							class="centered_db r_corners"></span> <span
							class="centered_db r_corners"></span>
					</button>
					<!--main menu-->
					<nav role="navigation"
						class="f_left f_xs_none d_xs_none t_xs_align_l">
						<ul class="horizontal_list main_menu clearfix" id="navigationurl">

						</ul>
					</nav>
					<script type="text/javascript">
							common.navivations();
					</script>
					<ul
						class="f_right horizontal_list clearfix t_align_l t_xs_align_c site_settings d_xs_inline_b f_xs_none">
						<!--like-->
						<li class="d_sm_none d_xs_block"><a role="button" href="#"
							class="button_type_1 color_dark d_block bg_light_color_1 r_corners tr_delay_hover box_s_none"><i
								class="fa fa-heart-o f_size_ex_large"></i><span
								class="count circle t_align_c" id="wishCount">0</span></a></li>
						<!--shopping cart-->
						<li class="m_left_5 relative container3d" id="shopping_button">
							<a role="button" href="#"
							class="button_type_3 color_light bg_scheme_color d_block r_corners tr_delay_hover box_s_none">
								<span class="d_inline_middle shop_icon"> <i
									class="fa fa-shopping-cart"></i> <span
									class="count tr_delay_hover type_2 circle t_align_c" id="cartCount">0</span>
							</span> <b id="sumPrice">￥0.00</b>
						</a>
							<div class="shopping_cart top_arrow tr_all_hover r_corners">
								<div class="f_size_medium sc_header">最近添加</div>
								<ul class="products_list"  id="carUl">
									
								</ul>
								<!--total price-->
								<ul class="total_price bg_light_color_1 t_align_r color_dark">
									<li class="m_bottom_10">折扣: <span
										class="f_size_large sc_price t_align_l d_inline_b m_left_15">￥0.00</span></li>
									<li>总价: <b
										class="f_size_large bold scheme_color sc_price t_align_l d_inline_b m_left_15"  id="totalPrice">￥0.00</b></li>
								</ul>
								<div class="sc_footer t_align_c">
<!-- 									<a href="#" role="button" -->
<!-- 										class="button_type_4 d_inline_middle bg_light_color_2 r_corners color_dark t_align_c tr_all_hover m_mxs_bottom_5">查看购物车</a> -->
									<a href="checkout.html?checkouttype=01" role="button"
										class="button_type_4 bg_scheme_color d_inline_middle r_corners tr_all_hover color_light">去结算</a>
								</div>
							</div>
						</li>
					</ul>
				</section>
				<script type="text/javascript">
					common.operationCart('getCart','');
				</script>
			</div>
		</header>
		<!--breadcrumbs-->
		<section class="breadcrumbs">
			<div class="container">
				<ul class="horizontal_list clearfix bc_list f_size_medium">
					<li class="m_right_10 current"><a href="#"
						class="default_t_color">首页<i
							class="fa fa-angle-right d_inline_middle m_left_10"></i></a></li>
					<li class="m_right_10 current"><a href="#"
						class="default_t_color">结算<i
							class="fa fa-angle-right d_inline_middle m_left_10"></i></a></li>
					<li><a href="#" class="default_t_color">购物车</a></li>
				</ul>
			</div>
		</section>
		<!--content-->
		<div class="page_content_offset">
			<div class="container">
				<div class="row clearfix">
					<!--left content column-->
					<aside class="col-lg-3 col-md-3 col-sm-3">

						<div id="catnav" class="m_bottom_25">
							<div class="mod_cate_hd">全部商品分类</div>
							<ul class="tit">
							</ul>
						</div>
						<script type="text/javascript">
								common.getAllProductType();
						</script>
						<!-- popup menu end -->


						<!--wishlist-->
						<figure class="widget shadow r_corners wrapper m_bottom_30">
							<figcaption>
								<h3 class="color_light">收藏</h3>
							</figcaption>
							<div class="widget_content" id="wish">
								
								<a href="wishlist.html" class="color_dark"><i
									class="fa fa-heart-o m_right_10"></i>打开收藏列表</a>
							</div>
							<script type="text/javascript">
								user.getCheckOutSideWish(5);
							</script>
						</figure>

						<figure class="widget shadow r_corners wrapper m_bottom_30" id="checkoutAd">
							<figcaption>
								<h3 class="color_light">热销产品</h3>
							</figcaption>
							<div class="widget_content" id="widget_content">
								<script type="text/javascript">
									try{
										products.hotSaleProduct(0,null,10);
									}catch(e){
									}
								</script>
							</div>
						</figure>
						<script type="text/javascript">
							ad.getAdByadarea('0801',0,null,'checkoutAd');
						</script>
						<!--New products-->
						<figure class="widget shadow r_corners wrapper m_bottom_30">
							<figcaption>
								<h3 class="color_light">新到产品</h3>
							</figcaption>
							<div class="widget_content" id="newProductSide">
								
								<hr class="m_bottom_15">
							</div>
						</figure>
						<script type="text/javascript">
							products.getSideNewProduct();
						</script>
						<!--Specials-->
						<figure class="widget shadow r_corners wrapper m_bottom_30">
							<figcaption class="clearfix relative">
								<h3
									class="color_light f_left f_sm_none m_sm_bottom_10 m_xs_bottom_0">特别推荐</h3>
								<div
									class="f_right nav_buttons_wrap_type_2 tf_sm_none f_sm_none clearfix">
									<button
										class="button_type_7 bg_cs_hover box_s_none f_size_ex_large color_light t_align_c bg_tr f_left tr_delay_hover r_corners sc_prev">
										<i class="fa fa-angle-left"></i>
									</button>
									<button
										class="button_type_7 bg_cs_hover box_s_none f_size_ex_large color_light t_align_c bg_tr f_left m_left_5 tr_delay_hover r_corners sc_next">
										<i class="fa fa-angle-right"></i>
									</button>
								</div>
							</figcaption>
							<div class="widget_content">
								<div class="specials_carousel" id="specials_carousel">
									<!--carousel item-->
								</div>
							</div>
							<script type="text/javascript">
								products.getRecommendProduct(3,"specials_carousel");
							</script>
						</figure>
					</aside>
					<!--right column-->
					<section class="col-lg-9 col-md-9 col-sm-9 m_xs_bottom_30">
						<h2 class="tt_uppercase color_dark m_bottom_25">购物车</h2>
						<!--cart table-->
						<form action="order/orderList/addOrder_list.action" method="post" id="orderForm">
						<input type="hidden" value="" id="orderTypeInput" name="orderType"/>
						<input type="hidden" value="" id="groupbuyid" name="groupbuyid"/>
						<input type="hidden" value="" id="bigdealid" name="bigdealid"/>
						<input type="hidden" value="" id="productId" name="productId"/>
						<input type="hidden" value="" id="count" name="count"/>
						<input type="hidden" value="" id="typeId" name="typeId"/>
						<script type="text/javascript">
							$("#orderTypeInput").val(common.getParameter("checkouttype"));
							$("#count").val(common.getParameter("count"));
							$("#groupbuyid").val(common.getParameter("groupbuyid"));
							$("#bigdealid").val(common.getParameter("bigdealid"));
							$("#productId").val(common.getParameter("productId"));
						</script>
						<table
							class="table_type_4 responsive_table full_width r_corners wraper shadow t_align_l t_xs_align_c m_bottom_30">
							<thead>
								<tr class="f_size_large">
									<!--titles for td-->
									<th>产品 名</th>
									<th>产品编码</th>
									<th>单价</th>
									<th>数量</th>
									<th>小计</th>
								</tr>
							</thead>
							<tbody id="cartTable">
								<!--prices-->
								<tr id="prices">
									<td colspan="4">
										<p class="fw_medium f_size_large t_align_r t_xs_align_c">小计:</p>
									</td>
									<td colspan="1">
										<p class="fw_medium f_size_large color_dark totalPrice totalPrice">￥0.00</p>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<p class="fw_medium f_size_large t_align_r t_xs_align_c">运费:</p>
									</td>
									<td colspan="1">
										<p class="fw_medium f_size_large color_dark">￥0.00</p>
									</td>
								</tr>
								<!--total-->
								<tr>
									<td colspan="4">
										<p
											class="fw_medium f_size_large t_align_r t_xs_align_c scheme_color">总计:</p>
									</td>
									<td colspan="1">
										<p class="fw_medium f_size_large scheme_color totalPrice">￥0.00</p>
									</td>
								</tr>
							</tbody>
						</table>
						<script type="text/javascript">
						 	order.pageCartList();
						</script>
						<div id="AddressInfo">
							<h2 class="tt_uppercase color_dark m_bottom_30">地址信息</h2>
							
							<div class="bs_inner_offsets bg_light_color_3 shadow r_corners m_bottom_45"  id="userAddress">												
							</div>
							<script type="text/javascript">
								user.getUserAddressByUserId();
							</script>
						</div>
						<script type="text/javascript">
							var checkouttype = common.getParameter("checkouttype");
							if(checkouttype=='01' || checkouttype=='02')
							{
								$("#AddressInfo").append('<button type="button" onclick="window.location.href=\'/register_address.html\'" class="button_type_6 bg_scheme_color f_size_large r_corners tr_all_hover color_light m_bottom_20" id="determineBuy">添加地址</button>');
							}
						</script>
						<div id="DeliveryInfo">
						<h2 class="tt_uppercase color_dark m_bottom_30">运送方式</h2>
						<div
							class="bs_inner_offsets bg_light_color_3 shadow r_corners m_bottom_45" id="transport">
							<figure class="block_select clearfix relative m_bottom_15">
								<input type="radio" checked name="radio_1" class="d_none" value="01">
								<img src="images/shipment_logo.jpg" alt=""
									class="f_left m_right_20 f_mxs_none m_mxs_bottom_10">
								<figcaption>
									<h5 class="color_dark fw_medium m_bottom_15 m_sm_bottom_5">快递送货</h5>
								</figcaption>
							</figure>
							<hr class="m_bottom_20">

						</div>
						</div>
						<div id="PaymentInfo">
						<h2 class="tt_uppercase color_dark m_bottom_30">支付方式</h2>
						<div
							class="bs_inner_offsets bg_light_color_3 shadow r_corners m_bottom_45" id="payMethod">
							<hr class="m_bottom_20">
							
							<input type="checkbox" name="payofflineflag"
								id="payofflineflag" class="d_none payofflineflag" value="1"><label for="payofflineflag">货到付款</label>
								<input name="payofflineflag" type="hidden" value="0" id="payofflineflagValue"/>
							<hr class="m_bottom_20">
							
							
						</div>
						<script type="text/javascript">
							order.payMethod();
						</script>
						</div>
						<div id="ReceiptInfo">
						<h2 class="tt_uppercase color_dark m_bottom_30">发票信息</h2>
						<div class="bs_inner_offsets shadow m_bottom_30">
							<div class="bg_light_color_3  r_corners m_bottom_25">
								<div class="block_select clearfix relative d_inline_b">
									<input type="radio" name="requireinvoice" class="d_none" value="1">需要
								</div>
								<div class="block_select clearfix relative d_inline_b m_left_10">
									<input type="radio" name="requireinvoice" class="d_none" value="0"  checked="checked">不需要
								</div>
							</div>
							<div class="bg_light_color_3  r_corners m_bottom_25">
								<div class="block_select clearfix relative d_inline_b">
									<input type="radio" name="invoicetype" class="d_none" value="0" checked="checked">个人
								</div>
								<div class="block_select clearfix relative d_inline_b m_left_10">
									<input type="radio" name="invoicetype" class="d_none" value="1">单位
								</div>
							</div>
							<div class="bg_light_color_3  r_corners m_bottom_25">
								<div class="clearfix relative">
									<label for="a_name_1" class="d_inline_b m_bottom_5 d_inline_b">单位抬头:</label>
									<input type="text" id="a_name_1" name="invoicetitle"
										class="r_corners half_column d_inline_b m_left_15">
								</div>
							</div>
							<div class="bg_light_color_3  r_corners m_bottom_25">
								<label for="a_name_1" class="d_inline_b m_bottom_5 d_inline_b">发票内容:</label>
								<div class="block_select clearfix relative d_inline_b  m_left_15 ehecked">
									<input type="radio" name="invoicecontent" class="d_none" checked="checked" value="01">明细
								</div>
 								<div class="block_select clearfix relative d_inline_b m_left_10">
 									<input type="radio" name="invoicecontent" class="d_none" value="02">生鲜 
 								</div>
							</div>
						</div>
						</div>
						<h2 class="tt_uppercase color_dark m_bottom_30">服务条款</h2>
						<div
							class="bs_inner_offsets bg_light_color_3 shadow r_corners m_bottom_45">
							<p class="m_bottom_10">服务条款</p>
						</div>
						<h2 class="tt_uppercase color_dark m_bottom_30">留言</h2>
						<!--requests table-->
						<table
							class="table_type_5 full_width r_corners wraper shadow t_align_l">
							<tr>
								<td colspan="2"><label for="notes"
									class="d_inline_b m_bottom_5">留言所指定的特殊需求，不作为本网站的服务标准:</label> <textarea
										id="notes" class="r_corners notes full_width" name="remark"></textarea></td>
							</tr>
							<tr>
								<td colspan="2"><input type="checkbox" name="tk"
									id="checkbox_8" class="d_none" checked="checked"><label for="checkbox_8">我已阅读并同意服务条款</label></td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="submit"
										class="button_type_6 bg_scheme_color f_size_large r_corners tr_all_hover color_light m_bottom_20" id="determineBuy">确认订单</button>
								</td>
							</tr>
						</table>
					</form>
					</section>
				</div>
			</div>
		</div>
		<!--markup footer-->
		<footer id="footer" class="type_2">
		</footer>
		<script type="text/javascript">
			common.footer();
		</script>
	</div>
	<!--social widgets-->
	<ul class="social_widgets d_xs_none">
		<!--sinawb-->
		<li class="relative">
			<button class="sw_button t_align_c sinawb">
				<i class="fa fa-facebook"></i>
			</button>
			<div class="sw_content">
				<iframe width="100%" height="300" class="share_self" frameborder="0"
					scrolling="no"
					src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=300&fansRow=2&ptype=1&speed=300&skin=1&isTitle=0&noborder=0&isWeibo=1&isFans=0&uid=5137660863&verifier=fddfa663&colors=d6f3f7,fafafa,666666,0082cb,ecfbfd&dpc=1"></iframe>
			</div>
		</li>
		<!--wechat-->
		<li class="relative">
			<button class="sw_button t_align_c wechat">
				<i class="fa fa-twitter"></i>
			</button>
			<div class="sw_content">
				<img src="images/webfin.gif" alt="">
			</div>
		</li>
		<!--contact info-->
		<li class="relative" id="contact">
		</li>
	</ul>
	<script type="text/javascript">
		common.contact();
	</script>
	
	<!--login popup-->	
	<div class="popup_wrap d_none" id="login_popup">
		<section class="popup r_corners shadow">
			<button
				class="bg_tr color_dark tr_all_hover text_cs_hover close f_size_large">
				<i class="fa fa-times"></i>
			</button>
			<h3 class="m_bottom_20 color_dark">用户登录</h3>
			<form id="userLogin" action="user/user/login.action" method="post">
				<ul>
					<li class="m_bottom_15"><label for="username"
						class="m_bottom_5 d_inline_b">用户名</label><br> <input
						type="text" name="userName" id="username" placeholder="用户名/手机号/邮箱"
						class="r_corners full_width"></li>
					<li class="m_bottom_25"><label for="password"
						class="m_bottom_5 d_inline_b">密码</label><br> <input
						type="password" name="password" id="password" class="r_corners full_width">
					</li>
					<li class="m_bottom_15"><input type="checkbox" class="d_none"
						id="checkbox_10"><label for="checkbox_10">记住我</label></li>
					<li class="clearfix m_bottom_30">
						<button
							class="button_type_4 tr_all_hover r_corners f_left bg_scheme_color color_light f_mxs_none m_mxs_bottom_15" type="button" id="clogin">登录</button>
						<div class="f_right f_size_medium f_mxs_none">
							<a href="#" class="color_dark">忘记密码?</a><br> <a href="#"
								class="color_dark">忘记用户名?</a>
						</div>
					</li>
				</ul>
			</form>
		</section>
	</div>
	
	<button class="t_align_c r_corners type_2 tr_all_hover animate_ftl"
		id="go_to_top">
		<i class="fa fa-angle-up"></i>
	</button>
	<!--scripts include-->
	<script
		src="http://api.map.baidu.com/api?v=1.5&ak=rGfGpVN279NnHzjfGeGkvM20"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/retina.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/waypoints.min.js"></script>
	<script src="js/jquery.tweet.min.js"></script>
	<script src="js/jquery.tabs.js"></script>
	<script src="js/scripts.js"></script>
	<script src="js/jquery-1.2.6.pack.js"></script>
	<script src="js/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>

	<script type="text/javascript">
	
   	$(function(){
   	    $("#catnav .tit").slide({
   			type:"menu",
   			titCell:".mod_cate",
   			targetCell:".mod_subcate",
   			delayTime:0,
   			triggerTime:10,
   			defaultPlay:false,
   			returnDefault:true
   		});
   	 	$("#payofflineflag").removeAttr("checked");
   	    $("#payofflineflag").click(function(){
   	    	if($(this).attr("checked")==false){
   	    		$(this).siblings("figure").show();
   	    		$("#payofflineflagValue").val("0");
   	    	}
   	    	else{
   	    		$(this).siblings("figure").hide();
   	    		$("#payofflineflagValue").val("1");
   	    	}
   	    });
   	});
	user.getwishListCount();
   	
   	var type = common.getParameter("checkouttype");
   	if(type == '03'){
   	   /*清除不必要的信息*/
   	   $("#AddressInfo").remove();
   	   $("#DeliveryInfo").remove();
   	   $("#PaymentInfo").remove();
   	   $("#ReceiptInfo").remove();
   	}
	</script>
</body>
</html>