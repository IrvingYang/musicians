<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="accordion" fillSpace="sideBar">
	<div class="accordionHeader">
		<h2><span>Folder</span>订单管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="manage/order/getAllOrder_List.do?typeId=1" target="navTab" rel="productOrder">商城订单</a></li>
			<li><a href="manage/order/getAllOrder_List.do?typeId=2" target="navTab" rel="groupbuyOrder">团购订单</a></li>
			<li><a href="manage/order/getAllOrder_List.do?typeId=3" target="navTab" rel="bigdealOrder">大宗交易订单</a></li>
			<li><a href="manage/siteEmail/siteEmailList.do" target="navTab" rel="siteemail">站内邮件管理</a></li>
			<li><a href="manage/repo/getRepoList.do" target="navTab" rel="repo">回购管理</a></li>
			<li><a href="manage/lease/getLeaseList.do" target="navTab" rel="lease">租赁管理</a></li>
<!-- 			<li><a href="manage/order/getAllOrder_List.do?typeId=10" target="navTab" rel="reProductOrder">退货管理</a></li> -->
<!-- 			<li><a href="manage/order/getAllOrder_List.do?typeId=11" target="navTab" rel="reMoneyrder">退款管理</a></li> -->
		</ul>
	</div>
</div>
