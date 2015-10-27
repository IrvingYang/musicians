<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/promote/toAddpromoteProduct.do" target="navTab" title="添加商城商品"><span>添加</span></a></li>
			<li><a class="delete" href="manage/promote/deletePromoteProduct.do?productId={null}" target="ajaxTodo" title="确定要删除吗?" id="productPromotedelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/promote/toEditRecommendProduct.do={null}" target="navTab" id="productPromoteedit"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="productPromoteDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width="">商品名</th>
				<th width="130">商城价</th>
				<th width="130">促销价</th>
				<th width="">促销开始时间</th>
				<th width="">促销结束时间</th>
				<th width="130">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${promoteList}" var="promote" varStatus="state">
				<tr rel="${promote.productId}">
					<td>${promote.product.productName}</td>
					<td>${promote.originalPrice}</td>
					<td>${promote.promotePrice}</td>
					<td>${promote.promoteStartTime}</td>
					<td>${promote.promoteEndTime}</td>
					<td>${promote.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">商品名</th>
				<th width="130">商城价</th>
				<th width="130">促销价</th>
				<th width="">促销开始时间</th>
				<th width="">促销结束时间</th>
				<th width="130">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#productPromoteDatatables').dataTable({
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
			 
			 $('#productPromoteDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#productPromoteDatatables").children("tbody").children(".selected");
			        var productIds = '';
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&productId="+$(data).attr("rel");
 						}
 					});
 					$("#productPromotedelete").attr("href","manage/promote/deletePromoteProduct.do?productId="+(productIds==""?"{null}":productIds));
					$("#productPromoteedit").attr("href","manage/promote/toEditPromoteProduct.do?productId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
