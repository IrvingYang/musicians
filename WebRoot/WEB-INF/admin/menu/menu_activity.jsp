<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>趣采摘管理管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/activity/getActivityList.do?action=page" target="navTab" rel="activity">活动信息</a></li>
			<li><a href="manage/activityapply/toActivityApplyPage.do" target="navTab" rel="activityapply">活动报名信息</a></li>
		</ul>
	</div>
</div>
