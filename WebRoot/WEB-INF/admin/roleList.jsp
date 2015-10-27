<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript">
var role_selected;
var role_oTable; 
	$(function(){
		$("#showallrole").children("label").children("input[type='radio']").click(function(){
			if($(this).val()==0){
				$(".showallrole").hide();
			}else if($(this).val()==1){
				$(".showallrole").show();
			}
		});
	});
	
	$('#roleDatatables tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	        role_selected = $("#roleDatatables").children("tbody").children(".selected");
	        var roleIds = '';
				$.each(role_selected, function(index, data){
					if(index==0){
						roleIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
					}else{
						roleIds+="&roleId="+$(data).attr("rel");
					}
				});
				$("#roledelete").attr("href","manage/role/deleteRole.do?roleId="+(roleIds==""?"{null}":roleIds));
			    $("#viewroleauthority").attr("href","manage/role/getAllRoleInfo.do?type=2&typeid="+(roleIds==""?"{null}":roleIds));
	  } );
</script>
<div class="pageContent"  layoutH="0">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/role/toAddRole.do" target="navTab" title="添加角色"><span>添加</span></a></li>
			<li><a class="delete" href="manage/role/deleteRole.do?roleId={role}" target="ajaxTodo" title="确定要删除吗?" id="roledelete" ><span>删除</span></a></li>
			<li><a class="edit" href="manage/role/getAllRoleInfo.do?type=2&typeid={role}" target="dialog" mask="true" width="500" height="600" title="查看修改角色权限"  id="viewroleauthority"><span>查看修改角色权限</span></a></li>
			<li class="line">line</li>
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li style="margin-top:5px;">显示其他销售商角色 </li>
				<li id="showallrole"  class="edit">
					<label><input type="radio" name="showallrole" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="showallrole" value="0" />否</label>
				</li>
			</c:if>
		</ul>
		
	</div>
	<table  width="100%" id="roleDatatables" class="display" >
		<thead>
			<tr>
				<th width="10"></th>
				<th width="100">角色名称</th>
				<th width="80">产品销售商</th>
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
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="10"></th>
				<th width="100">角色名称</th>
				<th width="80">产品销售商</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
		role_oTable = $('#roleDatatables').dataTable({
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
