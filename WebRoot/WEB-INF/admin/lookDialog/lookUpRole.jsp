<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="pageContent"  layoutH="0">
	<table  width="100%" id="rolelookUpDatatables" class="display" >
		<thead>
			<tr>
				<th width="10"></th>
				<th width="100">角色名称</th>
				<th width="80">产品销售商</th>
				<th width="10">选择角色</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${roleList}" var="role" varStatus="state">
				<c:if test="${role.providerId!=sessionScope.admin.providerId}">
					<c:set var="classes" value="showallrole"/>
				</c:if>
				<c:if test="${role.providerId==sessionScope.admin.providerId}">
					<c:set var="classes" value=""/>
				</c:if>
				<tr rel="${role.roleId}and${role.providerId}" class="${classes}">
					<td class="odd">${state.index+1}</td>
					<td>${role.roledesc}</td>
					<td>${role.provider.name}</td>
					<td>
						<a class="btnSelect" href="javascript:$.bringBack({roleId:'${role.roleId}',roleName:'${role.roledesc}',providerId:'${role.providerId}'})" title="${role.provider.name}：${role.roledesc}">选择</a>
					</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
			<th width="10"></th>
				<th width="100">角色名称</th>
				<th width="80">产品销售商</th>
				<th width="10">选择角色</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
		role_oTable = $('#rolelookUpDatatables').dataTable({
		         "sPaginationType" : "full_numbers",
		         "oLanguage": {
		 			"sLengthMenu": "每页显示 _MENU_ 条记录",
		 			"sZeroRecords": "抱歉， 没有找到",
		 			"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
		 			"sInfoEmpty": "没有数据",
		 			"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
		 			"oPaginate": {  
		                     "sFirst": "首页",  
		                     "sPrevious": "上一页",
		                     "sNext": "下一页",  
		                     "sLast": "尾页"  
		                 }, 
				    "sSearch": "搜索:",
		 			"sZeroRecords": "没有检索到数据",  
		 			"sProcessing": "<img src='./loading.gif' />"
		         }
		     });
			 
		</script>
</div>
