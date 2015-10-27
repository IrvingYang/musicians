<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>大宗交易管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/productbigdeal/getAllBigDeal.do" target="navTab" rel="bigdeal">大宗交易商品信息</a></li>
		</ul>
	</div>
</div>
