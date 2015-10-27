<%@page import="com.qushop.user.entity.RoleAuthority"%>
<%@page import="com.qushop.user.entity.Role"%>
<%@page import="com.qushop.user.entity.UrlList"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">
<!-- 	<table class="table" width="100%" layoutH="5"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<th width="">编号</th> -->
<!-- 				<th width="">链接</th> -->
<!-- 				<th width="">权限描述</th> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 		<tbody> -->
			
			
<!-- 		</tbody> -->
<!-- 	</table> -->
		<form method="post" action="manage/role/updateRolePermissions.do" class="pageForm" onsubmit="return validateCallback(this ,navTabAjaxDone)" id="permissionsForm">
		<% 
		List<Role> list = (List)request.getAttribute("roleList"); 
		Role role = list.get(0);
		pageContext.setAttribute("role", role);
		List<RoleAuthority> roleauthor = list.get(0).getRoleauthoritiesList();
		List<UrlList> arrangeRoleUrlList = (List<UrlList>)request.getAttribute("arrangeRoleUrlList");
		String urlId="";
		%>
		<input type="hidden" value="${role.roleId}" name="roleId"/>
		<input type="hidden" value="${role.providerId}" name="providerId"/>
		<div class="pageFormContent nowrap" layoutH="56">
		<ul class="tree treeFolder treeCheck expand" oncheck="checkedRole">
			
			<%
			for(UrlList urlList:arrangeRoleUrlList)
			{
				String wch="";
				if(urlList.getUrlLink().contains("/index.do")){
					wch="checked=\"true\"";
				}
			%>
				<li ><a tname="name" tvalue="<%=urlList.getUrlId() %>" <%=wch %> ><%=urlList.getDescription() %></a>
				<%
				if(urlList.getUrlList()!=null && urlList.getUrlList().size()>0)
				{
					out.print("<ul>");
					for(UrlList urlList1:urlList.getUrlList())
					{
					String checked="";
					for(RoleAuthority au : roleauthor){
						if(au.getUrlId().equals(urlList1.getUrlId()))
						{
							urlId+=urlList1.getUrlId()+",";
							checked="checked=\"true\"";
						}
					}
					%>
						<li ><a tname="name" tvalue="<%=urlList1.getUrlId() %>" <%=checked %>><%=urlList1.getDescription()%></a></li>
					<%
					}
					out.print("</ul>");
				}
				%>
				</li>
			<%	
			}
			if(urlId!=null && !urlId.equals("")){
				urlId = urlId.substring(0,urlId.length()-1);
			}
			out.println("<input type=\"hidden\" value=\""+urlId+"\" name=\"urlId\" id=\"urlId\"/>");
			%>
		</ul>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交权限修改</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
		</form>
		<script type="text/javascript">
			function checkedRole(){
				var urlId = "";
				var checked = $(".checked");
				var checked1 = $(".indeterminate");
				$.each(checked,function(index,obj){
					urlId+=($(obj).find("input").val()+",");
				})
				$.each(checked1,function(index,obj){
					urlId+=($(obj).find("input").val()+",");
				})
				urlId = urlId.substring("0",urlId.length-1);
				$("#urlId").val(urlId);
			}
		</script>
</div>
