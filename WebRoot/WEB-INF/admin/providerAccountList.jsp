<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/provideraccount/toAddProviderAccount.do" target="navTab" title="添加合作社账户"><span>添加</span></a></li>
			<li><a class="delete" href="manage/provideraccount/deleteProviderAccount.do?keyword={null}" target="ajaxTodo" title="确定要删除吗?" id="providerAccountdelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/provideraccount/toEditProviderAccount.do?groupbuyId={null}" target="navTab" id="providerAccountedit" title="修改合作社账户"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="providerAccountDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width="50"></th>
				<th width="">合作社名称</th>
				<th width="">开户行名称</th>
				<th width="">账户名称</th>
				<th width="">账户号码</th>
				<th width="130">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${accountList}" var="account" varStatus="state">
				<tr rel="${account.providerId}and${account.accountNumber}">
					<td>${state.index+1}</td>
					<td>${account.provider.name}</td>
					<td>${account.branchName}</td>
					<td>${account.accountName}</td>
					<td>${account.accountNumber}</td>
					<td>${account.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
					<th width="50"></th>
				<th width="">合作社名称</th>
				<th width="">开户行名称</th>
				<th width="">账户名称</th>
				<th width="">账户号码</th>
				<th width="130">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#providerAccountDatatables').dataTable({
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
			 
			 $('#providerAccountDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#providerAccountDatatables").children("tbody").children(".selected");
			        var productIds = "";
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&keyword="+$(data).attr("rel");
 						}
 					});
 					$("#providerAccountdelete").attr("href","manage/provideraccount/deleteProviderAccount.do?keyword="+(productIds==""?"{null}":productIds));
					$("#providerAccountedit").attr("href","manage/provideraccount/toEditProviderAccount.do?keyword="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
