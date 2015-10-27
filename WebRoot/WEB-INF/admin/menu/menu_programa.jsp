<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>栏目管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/programa/getProgramaByProgramaId.do?programaTypeId=1" target="navTab" rel="programa1">关于我们</a></li>
			<li><a href="manage/programa/getProgramaByProgramaId.do?programaTypeId=2" target="navTab" rel="programa2">配送条款</a></li>
			<li><a href="manage/programa/getProgramaByProgramaId.do?programaTypeId=3" target="navTab" rel="programa3">隐私策略</a></li>
			<li><a href="manage/programa/getProgramaByProgramaId.do?programaTypeId=4" target="navTab" rel="programa4">条款</a></li>
			<li><a href="manage/programa/getProgramaByProgramaId.do?programaTypeId=5" target="navTab" rel="programa5">购物流程</a></li>
			<li><a href="manage/programa/getProgramaByProgramaId.do?programaTypeId=6" target="navTab" rel="programa6">售后政策</a></li>
			<li><a href="manage/programa/getProgramaByProgramaId.do?programaTypeId=7" target="navTab" rel="programa7">返修/退换货</a></li>
			<li><a href="manage/programa/getProgramaByProgramaId.do?programaTypeId=8" target="navTab" rel="programa8">返款说明</a></li>
		</ul>
	</div>
</div>
