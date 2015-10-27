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
<base href="<%=basePath%>"/>
<title>彝家优品</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!--meta info-->
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="icon" type="image/ico" href="${pageContext.request.contextPath}/images/fav.ico">
<!--stylesheet include-->
<link rel="stylesheet" type="text/css" media="all"	href="${pageContext.request.contextPath}/css/flexslider.css">
<link rel="stylesheet" type="text/css" media="all"  href="${pageContext.request.contextPath}/css/jackbox.min.css">
<link rel="stylesheet" type="text/css" media="all"	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="all"	href="${pageContext.request.contextPath}/css/owl.carousel.css">
<link rel="stylesheet" type="text/css" media="all"	href="${pageContext.request.contextPath}/css/owl.transitions.css">
<link rel="stylesheet" type="text/css" media="all"	href="${pageContext.request.contextPath}/css/jquery.custom-scrollbar.css">
<link rel="stylesheet" type="text/css" media="all"  href="${pageContext.request.contextPath}/css/style.css">
<!--font include-->
<link href="css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="<%=basePath %>js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/uploadPreview.js"></script>
<script type="text/javascript" src="<%=basePath %>js/qushop/common.js"  charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath %>js/qushop/dict.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath %>js/qushop/ad.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath %>js/qushop/product.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath %>js/qushop/user.js" charset="utf-8"></script>
<script src="<%=basePath %>js/qushop/trip.js"  charset="utf-8"></script>

<script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript">
	$(function(){

// 		$("#imagefile").uploadPreview({ Img: "imgDiv", Width: 120, Height: 120, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
		new uploadPreview({ UpBtn: "imagefile", DivShow: "imgDiv", ImgShow: "imgShow" });
	});
</script>
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
			<script type="text/javascript">
					common.blogStatus();
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
		<script type="text/javascript">
			common.operationCart('getCart','');
		</script>
		<!--breadcrumbs-->
		<section class="breadcrumbs">
			<div class="container">
				<ul class="horizontal_list clearfix bc_list f_size_medium">
					<li class="m_right_10 current"><a href="#"
						class="default_t_color">首页<i
							class="fa fa-angle-right d_inline_middle m_left_10"></i></a></li>
					<li><a href="#" class="default_t_color">游记分享</a></li>
				</ul>
			</div>
		</section>
		<!--content-->
		<div class="page_content_offset">
			<form action="trip/blogs/addBlogs.action" method="POST" id="addblogsForm" enctype="multipart/form-data">
				<div class="container">
					<div class="d_table full_width d_xs_block m_bottom_10 ">
							游记标题
					</div>
					<div style="height: auto;" class="m_bottom_15 col-lg-6 col-md-6 col-sm-6">
						<input  type="text" id="title" name="title" size="100" class="r_corners full_width"/>
					</div>
					
					<div class="d_table full_width d_xs_block m_bottom_10">
							游记类型
					</div>
					<div class="m_bottom_15 col-lg-4 col-md-4 col-sm-4">
					
						<div id="blogstype"
							class="custom_select relative color_dark portfolio_filter d_inline_b t_align_l d_xs_block">
							<div class="select_title type_2 r_corners relative mw_0" id="blogstypetext">选择类别</div>
							<ul id="filter_portfolio" class="select_list d_none"></ul>
							<select id="blogstypeselector" name="blogstypeid">
							</select>
							<script type="text/javascript">
								trip.getRecommendlinetypeList();
							</script>
						</div>
					</div>
					
					<div class="d_table full_width d_xs_block m_bottom_10">
							封面图片
					</div>
					<div style="height: auto;" class="m_bottom_15 col-lg-4 col-md-4 col-sm-4">
						<input type="file" style="display:none;" name="image" id="imagefile"/>
						<button type="button" id="coverImg" class="tr_delay_hover r_corners button_type_14 bg_color_green color_light"
						 onclick="imagefile.click();" size="40" > 选择游记封面图片  </button><div style="margin: 5px;">建议像素(340*340)</div>
					</div>
					<div id="imgDiv" class="m_bottom_15 col-lg-4 col-md-4 col-sm-4">
						<img src="" alt="" id="imgShow"/>
					</div>
					
					<div class="d_table full_width d_xs_block m_bottom_10">
							游记正文
					</div>
					
					<div id="content" style="height: auto;" class="m_bottom_15 col-lg-12 col-md-12 col-sm-12">
						<textarea name="htmlpath" style="width:90%; min-height: 300px; height: auto" id="textcontent"></textarea>
					</div>	
					<div class="d_table_cell v_align_m t_align_r d_xs_block">
						<div
							class="custom_select relative color_dark portfolio_filter d_inline_b t_align_l d_xs_block">
							<button class="tr_delay_hover r_corners button_type_14 bg_color_green color_light" type="button" id="redeploy">
								<i class="fa fa-check m_right_6"></i>发表
							</button>						
						</div>
					</div>
					
				</div>
			</form>
		</div>
		<script type="text/javascript">

			var ue = UE.getEditor("textcontent");
			$(function(){
				$("#redeploy").click(function(){
					var imagefile = $("#imagefile").val();
					var title = $("#title").val();
					if(title==""){
						alert("请输入游记标题");
						return;
					}
					if($("#blogstypetext").text()=="选择类别"){
						alert("请选择类别");
						return;
					}
					if(imagefile==""){
						alert("请选择封面图片");
						return;
					}
					if(ue.getContentTxt().length<100){
						alert("游记正文内容太少");
						return;
					}
					var options={
	                    type:"post",
	                    dataType:"text", 
	                    success:function(data){
	                      	if(data=="needLogin"){
	  							alert("您需要登录个人用户");
	  							$("#login").click();
	  						}
	  						if(data==true || data=="true"){
	  							alert("发表成功");
	  							window.location.reload(true);
	  						}
	                    },
	                    error:function(){
	                    	alert("请求超时");
	                    }
                  	};
                   	$("#addblogsForm").ajaxSubmit(options);
				});
			});
		</script>
		<!--markup footer-->
		<footer id="footer" class="type_2">
		</footer>
	</div>
	<script type="text/javascript">
		trip.getBlogsTypeList();
		common.footer();
	</script>
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
	<script src="<%=basePath %>js/jquery-ui.min.js"></script>
	<script src="<%=basePath %>js/retina.js"></script>
	<script src="<%=basePath %>js/owl.carousel.min.js"></script>
	<script src="<%=basePath %>js/waypoints.min.js"></script>
	<script src="<%=basePath %>js/jquery.tweet.min.js"></script>
	<script src="<%=basePath %>js/jquery.tabs.js"></script>
	<script src="<%=basePath %>js/scripts.js"></script>
	<script src="<%=basePath %>js/jquery-1.2.6.pack.js"></script>
	<script src="<%=basePath %>js/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery.form.js"></script>
	
	
	
	
	<script src="http://api.map.baidu.com/api?v=2.0&ak=rGfGpVN279NnHzjfGeGkvM20"></script>
	<script type="text/javascript">
		user.getwishListCount();
	</script>
	
	
</body>
</html>