<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>乐星网后台管理系统</title>

<link href="<%=basePath %>manage/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath %>manage/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath %>manage/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=basePath %>datatables/css/jquery.dataTables.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=basePath %>datatables/css/jquery.dataTables_themeroller.css" rel="stylesheet" type="text/css" media="screen"/>

<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<style type="text/css">
	#header{height:85px}
	#leftside, #container, #splitBar, #splitBarProxy{top:90px}
</style>

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="<%=basePath %>manage/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/qushop/common.js" type="text/javascript"></script>
<script src="<%=basePath %>manage/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath %>manage/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=basePath %>manage/js/jquery.bgiframe.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>datatables/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath %>manage/js/jquery.form.js"></script>

<script type="text/javascript" src="<%=basePath %>ckfinder/ckfinder.js"></script>
<script type="text/javascript" src="<%=basePath %>ckeditor/ckeditor.js"></script>

<script src="<%=basePath %>manage/bin/dwz.min.js" type="text/javascript"></script>
<script src="<%=basePath %>manage/js/dwz.regional.zh.js" type="text/javascript"></script>


<script type="text/javascript">
$(function(){
	DWZ.init("<%=basePath %>manage/dwz.frag.xml", {
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
		}
	});
});
</script>
</head>

<body >
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="#"></a>
				<ul class="nav" style="margin-top:20px; color: white;">
					<li>您好：${sessionScope.admin.operName}</li>
					<li><a href="manage/oper/logout.do">退出</a></li>
				</ul>
			</div>
		
			<div id="navMenu">
				<ul>
					<li class="selected"><a href="manage/menu/product.do"><span>商品管理</span></a></li>
					<!-- <li><a href="manage/menu/groupbuy.do"><span>团购管理</span></a></li>
					<li><a href="manage/menu/bigdeal.do"><span>大宗交易管理</span></a></li>
					<li><a href="manage/menu/provider.do"><span>合作社管理</span></a></li> -->
					<li><a href="manage/menu/order.do"><span>订单管理</span></a></li>
					<li><a href="manage/menu/user.do"><span>会员管理</span></a></li>
					<!-- <li><a href="manage/menu/travel.do"><span>趣旅游管理</span></a></li>
					<li><a href="manage/menu/activity.do"><span>趣采摘管理</span></a></li>
					<li><a href="manage/menu/blogs.do"><span>趣分享管理</span></a></li> -->
					<li><a href="manage/menu/advertising.do"><span>广告管理</span></a></li>
					<li><a href="manage/menu/dict.do"><span>基本信息管理</span></a></li>
					<li><a href="manage/menu/programa.do"><span>栏目管理</span></a></li>
					<li><a href="manage/menu/sysconfig.do"><span>系统设置</span></a></li>
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>商品管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							
								<li><a href="manage/productType/getAllProductType.do" target="navTab" rel="productType">商品类型</a></li>
								<li><a href="manage/product/productList.do" target="navTab" rel="product">商品管理</a></li>
								<li><a href="manage/productshop/queryAllProductShop.do?typeId=7" target="navTab" rel="productShop">商城商品</a></li>
								<li><a href="manage/newProduct/newProductList.do" target="navTab" rel="newProduct">最新商品</a></li>
								<li><a href="manage/recommendProduct/recommendProductList.do" target="navTab" rel="recommendProduct">推荐商品</a></li>
								<li><a href="manage/promote/getAllPromoteProduct.do" target="navTab" rel="promote">商品促销</a></li>
								<li><a href="manage/relation/getAllRelationProduct.do" target="navTab" rel="relation">关联商品</a></li>
			                    <li><a href="manage/review/getAllProductReview.do" target="navTab" rel="review">商品评价</a></li>
			                   <li><a href="manage/leaseConfig/getAllLeaseConfig.do" target="navTab" rel="leaseConfig">租赁管理</a></li>
			                    
						</ul>
					</div>
<!-- 					<div class="accordionHeader"> -->
<!-- 						<h2><span>Folder</span>典型页面</h2> -->
<!-- 					</div> -->
<!-- 					<div class="accordionContent"> -->
<!-- 						<ul class="tree treeFolder treeCheck"> -->
<!-- 							<li><a href="demo_page1.html" target="navTab" rel="demo_page1">查询我的客户</a></li> -->
<!-- 							<li><a href="demo_page1.html" target="navTab" rel="demo_page2">表单查询页面</a></li> -->
<!-- 							<li><a href="demo_page4.html" target="navTab" rel="demo_page4">表单录入页面</a></li> -->
<!-- 							<li><a href="demo_page5.html" target="navTab" rel="demo_page5">有文本输入的表单</a></li> -->
<!-- 							<li><a href="javascript:;">有提示的表单输入页面</a> -->
<!-- 								<ul> -->
<!-- 									<li><a href="javascript:;">页面一</a></li> -->
<!-- 									<li><a href="javascript:;">页面二</a></li> -->
<!-- 								</ul> -->
<!-- 							</li> -->
<!-- 							<li><a href="javascript:;">选项卡和图形的页面</a> -->
<!-- 								<ul> -->
<!-- 									<li><a href="javascript:;">页面一</a></li> -->
<!-- 									<li><a href="javascript:;">页面二</a></li> -->
<!-- 								</ul> -->
<!-- 							</li> -->
<!-- 							<li><a href="javascript:;">选项卡和图形切换的页面</a></li> -->
<!-- 							<li><a href="javascript:;">左右两个互动的页面</a></li> -->
<!-- 							<li><a href="javascript:;">列表输入的页面</a></li> -->
<!-- 							<li><a href="javascript:;">双层栏目列表的页面</a></li> -->
<!-- 						</ul> -->
<!-- 					</div> -->
<!-- 					<div class="accordionHeader"> -->
<!-- 						<h2><span>Folder</span>流程演示</h2> -->
<!-- 					</div> -->
<!-- 					<div class="accordionContent"> -->
<!-- 						<ul class="tree"> -->
<!-- 							<li><a href="newPage1.html" target="dialog" rel="dlg_page">列表</a></li> -->
<!-- 							<li><a href="newPage1.html" target="dialog" rel="dlg_page">列表</a></li> -->
<!-- 							<li><a href="newPage1.html" target="dialog" rel="dlg_page2">列表</a></li> -->
<!-- 							<li><a href="newPage1.html" target="dialog" rel="dlg_page2">列表</a></li> -->
<!-- 							<li><a href="newPage1.html" target="dialog" rel="dlg_page2">列表</a></li> -->商品管理
<!-- 						</ul> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox" >
<!-- 						<div class="accountInfo"> -->
<!-- 							<div class="alertInfo"> -->
<!-- 								<h2><a href="doc/dwz-user-guide.pdf" target="_blank">DWZ框架使用手册(PDF)</a></h2> -->
<!-- 								<a href="doc/dwz-user-guide.swf" target="_blank">DWZ框架演示视频</a> -->
<!-- 							</div> -->
<!-- 							<div class="right"> -->
<!-- 								<p><a href="doc/dwz-user-guide.zip" target="_blank" style="line-height:19px">DWZ框架使用手册(CHM)</a></p> -->
<!-- 								<p><a href="doc/dwz-ajax-develop.swf" target="_blank" style="line-height:19px">DWZ框架Ajax开发视频教材</a></p> -->
<!-- 							</div> -->
<!-- 							<p><span>DWZ富客户端框架</span></p> -->
<!-- 							<p>DWZ官方微博:<a href="http://weibo.com/dwzui" target="_blank">http://weibo.com/dwzui</a></p> -->
<!-- 						</div> -->
						<div class="pageFormContent"  layoutH="0" >
							<iframe width="100%"  height="100%" class="share_self"  frameborder="0" scrolling="yes" src="#"></iframe>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2014 <a href="http://www.webfin.com.cn" target="dialog">Stephen团队</a></div>

<!-- 注意此处js代码用于google站点统计，非DWZ代码，请删除 -->
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-16716654-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? ' https://ssl' : ' http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>

</body>
</html>