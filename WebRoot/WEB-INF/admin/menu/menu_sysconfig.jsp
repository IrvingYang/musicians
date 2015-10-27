<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>系统管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/role/getAllRoleInfo.do?type=1" target="navTab" rel="RoleInfo">角色信息</a></li>
<!-- 			<li><a href="manage/role/arrangeRoleUrlList.do" target="navTab" rel="RoleAuthority">角色权限</a></li> -->
			<li><a href="manage/oper/operList.do" target="navTab" rel="OpertoRole">操作员管理</a></li>
			<li><a href="manage/oper/toEditInfo.do" target="navTab" rel="OpertoInfo">修改我的信息</a></li>
			<li><a href="manage/oper/toEditPassword.do" target="navTab" rel="OpertoPwd">修改登录密码</a></li>
		</ul>
	</div>
</div>
