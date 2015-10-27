<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>合作社管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/provider/getProviderList.do" target="navTab" rel="provider">合作社信息</a></li>
			<li><a href="manage/provideraccount/getAllProviderAccount.do" target="navTab" rel="provideraccount">合作社账户</a></li>
		</ul>
	</div>
</div>
