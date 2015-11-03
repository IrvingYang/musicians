<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>广告管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/ad/getAllAdvertising.do" target="navTab" rel="ad">所有广告信息</a></li>
			<!-- <li><a href="trip/modulerecommend/getTripModuleRecommendList.do" target="navTab" rel="moduleRecommend">乐享生活</a></li> -->
		</ul>
	</div>
</div>
