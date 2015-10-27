<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/oper/toAddOper.do" target="navTab" title="添加操作员" id="operedit"><span>添加</span></a></li>
			<li><a class="delete" href="manage/oper/deleteOper.do" target="ajaxTodo" title="确定要删除吗?" id="operdelete"><span>删除</span></a></li>
<!-- 			<li><a class="edit" href="manage/oper" target="navTab" id="operedit" title="修改操作员"><span>修改</span></a></li> -->
<!-- 			<li><a class="edit" href="manage/oper" target="navTab" id="operedit" title="修改操作员权限"><span>修改权限</span></a></li> -->
			<li class="line">line</li>
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li style="margin-top:5px;">显示非本合作社管理员 </li>
				<li id="salesbyself"  class="edit">
					<label><input type="radio" name="salesbyself" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="salesbyself" value="0" />否</label>
				</li>
			</c:if>
		</ul>
		
	</div>
	<table  width="100%" id="operdatatables" class="display" >	
		<thead>
			<tr>
				<th width="">编号</th>
				
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<th width="" class="salesbyself">合作社</th>
				</c:if>
				<th width="">账号</th>
				<th width="">邮件</th>
				<th width="">性别</th>
				<th width="">移动电话</th>
				<th width="">登陆状态</th>
				<th width="">合作社</th>
				<th width="">最后登陆时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${operList}" var="oper" varStatus="state">
			
				<c:if test="${oper.providerId!=sessionScope.admin.providerId}">
					<c:set var="classes" value="salesbyself"/>
				</c:if>
				<c:if test="${oper.providerId==sessionScope.admin.providerId}">
					<c:set var="classes" value=""/>
				</c:if>
				
				<tr rel="${oper.operId}" class="${classes}">
					<td>${oper.operId}</td>
					
					<c:if test="${sessionScope.admin.partnerflag==0}">
						<td class="salesbyself">${oper.provider.name}</td>
					</c:if>
					<td>${oper.operName}</td>
					<td>${oper.email}</td>
					<td>${oper.sex==1?"男":"女"}</td>
					<td>${oper.telephone}</td>
					<td>${oper.loginStatus==1?"已登录":"未登录"}</td>
					<td>${oper.role.provider.name}</td>
					<td>${oper.lastLoginTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">编号</th>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<th width="" class="salesbyself">合作社</th>
				</c:if>
				<th width="">账号</th>
				<th width="">邮件</th>
				<th width="">性别</th>
				<th width="">移动电话</th>
				<th width="">登陆状态</th>
				<th width="">合作社</th>
				<th width="">最后登陆时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#operdatatables').dataTable({
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
			 $(function(){
				$("#salesbyself").children("label").children("input[type='radio']").click(function(){
					if($(this).val()==0){
						$(".salesbyself").hide();
					}else if($(this).val()==1){
						$(".salesbyself").show();
					}
				});
			});
			 
			 $('#operdatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#operdatatables").children("tbody").children(".selected");
			        var productIds = '';
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&operId="+$(data).attr("rel");
 						}
 					});
 					$("#operedit").attr("href","manage/oper/toAddOper.do?operId="+(productIds==""?"{null}":productIds));
					$("#operdelete").attr("href","manage/oper/deleteOper.do?operId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
