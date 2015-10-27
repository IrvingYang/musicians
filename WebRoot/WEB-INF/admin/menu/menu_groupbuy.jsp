<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>团购管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/groupbuy/getAllProductGroupBuy.do" target="navTab" rel="groupbuy">团购商品信息</a></li>
			<li><a href="manage/order/getOrderList.do?typeId=2" target="navTab" rel="demo_page1">团购购买者</a></li>
		</ul>
	</div>
</div>
