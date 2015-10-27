<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="manage/user/deleteUser.do?userType=2&userId={null}" target="ajaxTodo" title="确定要删除吗?" id="entUserDelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/user/toEditUser.do?userType=2&groupbuyId={null}" target="navTab" id="entUserEdit" title="修改企业用户"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="entUserDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width=""></th>
				<th width="">账号</th>
				<th width="">邮件</th>
				<th width="80">最后登录时间</th>
				<th width="">用户状态</th>
				<th width="">联系人</th>
				<th width="">联系电话</th>
				<th width="">营业执照编号</th>
				<th width="">税务登记证编号</th>
				<th width="">组织机构代码证编号</th>
				<th width="">企业名称</th>
				<th width="80">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usersList}" var="user" varStatus="state">
				<tr rel="${user.userId}">
					<td>${state.index+1}</td>
					<td>${user.user.userName}</td>
					<td>${user.user.email}</td>
					<td>${user.user.lastLoginTime}</td>
					<td>
						<c:choose>
							<c:when test="${user.user.status==0}">未验证</c:when>
							<c:when test="${user.user.status==1}">正常</c:when>
							<c:when test="${user.user.status==2}">禁用</c:when>
							<c:when test="${user.user.status==3}">已删除</c:when>
							<c:when test="${user.user.status==4}">未通过审核</c:when>
						</c:choose>
					</td>
					<td>${user.contactman}</td>
					<td>${user.telephone}</td>
					<td>${user.certid1}</td>
					<td>${user.certid2}</td>
					<td>${user.certid3}</td>
					<td>${user.enterprisename}</td>
					<td>${user.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width=""></th>
				<th width="">账号</th>
				<th width="">邮件</th>
				<th width="">最后登录时间</th>
				<th width="">用户状态</th>
				<th width="">联系人</th>
				<th width="">联系电话</th>
				<th width="">营业执照编号</th>
				<th width="">税务登记证编号</th>
				<th width="">组织机构代码证编号</th>
				<th width="">企业名称</th>
				<th width="">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#entUserDatatables').dataTable({
		         "sPaginationType" : "full_numbers",
		         "oLanguage": {
		 			"sLengthMenu": "每页显示 _MENU_ 条记录",
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
			 
			 $('#entUserDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#entUserDatatables").children("tbody").children(".selected");
			        var productIds = "";
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&userId="+$(data).attr("rel");
 						}
 					});
 					$("#entUserDelete").attr("href","manage/user/deleteUser.do?userType=2&userId="+(productIds==""?"{null}":productIds));
					$("#entUserEdit").attr("href","manage/user/toEditUser.do?userType=2&userId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
