<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>趣分享管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/blogstype/getBlogstypeList.do" target="navTab" rel="blogstype">游记类型</a></li>
			<li><a href="manage/blogs/getBlogsList.do" target="navTab" rel="blogs">游记信息</a></li>
		</ul>
	</div>
</div>

