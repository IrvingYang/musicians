<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>趣旅游管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/recommendlinetype/getRecommendlinetype.do?action=page" target="navTab" rel="recommendlinetype">线路类型</a></li>
			<li><a href="manage/recommendline/getRecommendlineList.do?action=page" target="navTab" rel="recommendline">线路信息</a></li>
			<li><a href="manage/recommendlineapply/toRecommendapplyPage.do" target="navTab" rel="recommendlineapply">线路报名信息</a></li>
		</ul>
	</div>
</div>
