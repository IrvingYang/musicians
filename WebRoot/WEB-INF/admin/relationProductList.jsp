<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/relation/toAddRelationProduct.do" target="navTab" title="添加关联商品"><span>添加</span></a></li>
			<li><a class="delete" href="manage/relation/deleteRelationProduct.do?productId={null}" target="ajaxTodo" title="确定要删除吗?" id="productRelationdelete"><span>删除</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="productRelationDatatables" class="display" >	
		<thead>
			<tr>
				<th width="">商品</th>
				<th width="">关联商品</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${relations}" var="relation" varStatus="state">
				<tr rel="${relation.productId1}and${relation.productId2}">
					<td>${relation.product_ext_shop1.product.productName}</td>
					<td>${relation.product_ext_shop2.product.productName}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">商品</th>
				<th width="">关联商品</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#productRelationDatatables').dataTable({
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
			 
			 $('#productRelationDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#productRelationDatatables").children("tbody").children(".selected");
			        var productIds = '';
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&productId="+$(data).attr("rel");
 						}
 					});
 					$("#productRelationdelete").attr("href","manage/relation/deleteRelationProduct.do?productId="+(productIds==""?"{null}":productIds));
					$("#productRelationedit").attr("href","manage/relation/toEditProductShop.do?productId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
