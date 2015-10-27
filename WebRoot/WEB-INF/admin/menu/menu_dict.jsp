<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>基本信息管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/state/getStateList.do" target="navTab" rel="state">省代码信息</a></li>
			<li><a href="manage/city/getAllCity.do" target="navTab" rel="city">城市信息</a></li>
			<li><a href="manage/district/getAllDistrict.do" target="navTab" rel="district">区（县）信息</a></li>
			<li><a href="manage/express/getAllExpress.do" target="navTab" rel="express">物流信息</a></li>
			<li><a href="manage/brand_vendor/getAllbrand_vendor.do" target="navTab" rel="brand">品牌信息</a></li>
			<li><a href="manage/payment/getAllPaymentMethod.do" target="navTab" rel="payment">支付方式</a></li>
		</ul>
	</div>
</div>

