<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>用户管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/user/getUserListByUserType.do?userType=1" target="navTab" rel="user">普通用户</a></li>
			<li><a href="manage/user/getUserListByUserType.do?userType=2" target="navTab" rel="entuser">企业用户</a></li>
			<li><a href="manage/user/getNotThroughValidateEntUser.do" target="navTab" rel="demo_upload">企业用户审核</a></li>
			<li><a href="manage/useraddress/userAddressList.do" target="navTab" rel="useraddress">会员收货地址管理</a></li>
		</ul>
	</div>
</div>
