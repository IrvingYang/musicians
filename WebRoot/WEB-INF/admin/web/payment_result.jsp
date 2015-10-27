<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<!--[if IE 9 ]><html class="ie9" lang="en"><![endif]-->
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
<link rel="stylesheet" type="text/css" media="all" href="css/style.css">
<!--font include-->
<link href="css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="js/qushop/common.js"  charset="utf-8"></script>
<script type="text/javascript" src="js/qushop/dict.js" charset="utf-8"></script>
<script type="text/javascript" src="js/qushop/ad.js" charset="utf-8"></script>
<script type="text/javascript" src="js/qushop/product.js" charset="utf-8"></script>
<script src="js/jquery.form.js"></script>
<script src="js/qushop/trip.js"  charset="utf-8"></script>

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
						<a href="index.html" class="logo m_xs_bottom_15 d_xs_inline_b">
							<img src="images/logo.png" alt="">
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
							<div class="col-lg-6 col-md-6 col-sm-6" id="search">
								<!-- 搜索 -->
								<script type="text/javascript">
									common.search();
								</script>
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
						class="f_right horizontal_list clearfix t_align_l t_xs_align_c site_settings d_xs_inline_b f_xs_none" id="collectionurl">
					</ul>
				</section>
				<script type="text/javascript">
					common.collection();
				</script>
			</div>
		</header>
		<!--breadcrumbs-->
		<section class="breadcrumbs">
			<div class="container">
				<ul class="horizontal_list clearfix bc_list f_size_medium">
					<li class="m_right_10 current"><a href="#"
						class="default_t_color">主页<i
							class="fa fa-angle-right d_inline_middle m_left_10"></i></a></li>
					<li><a href="#" class="default_t_color">支付结果</a></li>
				</ul>
			</div>
		</section>
		<!--content-->
		<div class="page_content_offset">
			<div class="container">
				<div class="row clearfix">
					<h2 class="tt_uppercase color_dark m_bottom_30">支付结果</h2>
					<div
						class="bg_light_color_3 r_corners t_xs_align_c">
						<div class="row clearfix m_bottom_25 m_xs_bottom_0 t_align_c">
							<img src="images/${img}.jpg"/>
						</div>
					</div>
				</div>
				<a href="orders_list.html" role="button"
					class="d_inline_b bg_light_color_2 color_dark tr_all_hover button_type_4 r_corners"><i
					class="fa fa-reply m_left_5 m_right_10 f_size_large"></i>返回订单列表</a>
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
				<iframe width="100%" height="300" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=300&fansRow=2&ptype=1&speed=300&skin=1&isTitle=0&noborder=0&isWeibo=1&isFans=0&uid=5137660863&verifier=fddfa663&colors=d6f3f7,fafafa,666666,0082cb,ecfbfd&dpc=1"></iframe>
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
	<script src="js/jquery-2.1.0.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/retina.js"></script>
	<script src="js/elevatezoom.min.js"></script>
	<script src="js/waypoints.min.js"></script>
	<script src="js/jquery.tweet.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.custom-scrollbar.js"></script>
	<script src="js/jquery.fancybox-1.3.4.js"></script>
	<script src="js/jquery.tabs.js"></script>
	<script src="js/scripts.js"></script>
	<script src="js/qushop/user.js" charset="utf-8"></script>
	<script type="text/javascript">
		user.getwishListCount();
	</script>
</body>
</html>