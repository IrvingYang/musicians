<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="navTab" class="tabsPage">
	<ul class="tabsMoreList">
		<li><a href="javascript:;">我的主页</a></li>
	</ul>
	<div class="navTab-panel tabsPageContent layoutBox">
		<div class="page unitBox">
<!-- 			<div class="accountInfo"> -->
				
<!-- 			</div> -->
			<div class="pageFormContent" layoutH="80">
<!-- 				<iframe width="100%" height="430" class="share_self"  frameborder="0" scrolling="no" src="http://www.baidu.com"></iframe> -->
					访问出错，详细信息${message}
			</div>
		</div>
		
	</div>
</div>