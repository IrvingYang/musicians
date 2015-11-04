<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
<!-- 			<li><a class="add" href="manage/productshop/toAddProductShop.do" target="navTab" title="添加商城商品"><span>添加</span></a></li> -->
			<li><a class="delete" href="manage/review/deleteProductReview.do?deleteKeyWord={null}" target="ajaxTodo" title="确定要删除吗?" id="reviewDelete"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="reviewDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width=""></th>
				<th width="">商品名</th>
				<th width="">评分</th>
				<th width="200">评价</th>
				<th width="">评论用户</th>
				<th width="">评论日期</th>
				<th width="">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${reviewsList}" var="review" varStatus="state">
				<tr rel="${review.reviewId}">
					<td>${state.index+1}</td>
					<td>${review.product.productName}</td>
					<td>${review.rate}</td>
					<td>${empty review.productComments?"暂无评论":review.productComments}</td>
					<td>${review.user.userName}</td>
					<td>${review.commentsDate}</td>
					<td>${review.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width=""></th>
				<th width="">商品名</th>
				<th width="">评分</th>
				<th width="200">评价</th>
				<th width="">评论用户</th>
				<th width="">评论日期</th>
				<th width="">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#reviewDatatables').dataTable({
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
			 
			 $('#reviewDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#reviewDatatables").children("tbody").children(".selected");
			        var productIds = '';
 					$.each(selected, function(index, data){
 						alert($(data).attr("rel"));
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&deleteKeyWord="+$(data).attr("rel");
 						}
 					});
 					$("#reviewDelete").attr("href","manage/review/deleteProductReview.do?deleteKeyWord="+(productIds==""?"{null}":productIds));
			  } );
			 
		</script>
</div>
