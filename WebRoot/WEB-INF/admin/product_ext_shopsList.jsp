<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/productshop/toAddProductShop.do" target="navTab" title="添加商城商品"><span>添加</span></a></li>
			<li><a class="delete" href="manage/productshop/deleteProductShop.do?productId={null}" target="ajaxTodo" title="确定要删除吗?" id="productShopdelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/productshop/toEditProductShop.do?productId=={null}" target="navTab" id="productShopedit"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="productShopDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width=""></th>
				<th width="">商品名</th>
				<th width="130">上架时间</th>
				<th width="130">下架时间</th>
				<th width="">原价</th>
				<th width="">促销价</th>
				<th width="">促销</th>
				<th width="">单位</th>
				<th width="">销售范围</th>
				<th width="130">促销开始时间</th>
				<th width="130">促销结束时间</th>
				<th width="130">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productShop}" var="shop" varStatus="state">
				<tr rel="${shop.productId}">
					<td>${state.index+1}</td>
					<td>${shop.product.productName}</td>
					<td>${shop.onTime}</td>
					<td>${shop.offTime}</td>
					<td>
						<c:if test="${shop.promoteflag==0}">
							${shop.originalPrice }
						</c:if>
						<c:if test="${shop.promoteflag==1}">
							<s>${shop.originalPrice }</s>
						</c:if>
					</td>
					<td>${shop.promoteflag==1?shop.promotePrice:""}</td>
					<td>${shop.promoteflag==0?"否":"是"}</td>
					<td>${shop.unit}</td>
					<td>${shop.salesarea}</td>
					<td>${shop.promoteStartTime}</td>
					<td>${shop.promoteEndTime}</td>
					<td>${shop.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width=""></th>
				<th width="">商品名</th>
				<th width="130">上架时间</th>
				<th width="130">下架时间</th>
				<th width="">原价</th>
				<th width="">促销价</th>
				<th width="">促销</th>
				<th width="">单位</th>
				<th width="">销售范围</th>
				<th width="130">促销开始时间</th>
				<th width="130">促销结束时间</th>
				<th width="130">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#productShopDatatables').dataTable({
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
			 
			 $('#productShopDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#productShopDatatables").children("tbody").children(".selected");
			        var productIds = "";
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&productId="+$(data).attr("rel");
 						}
 					});
 					$("#productShopdelete").attr("href","manage/productshop/deleteProductShop.do?productId="+(productIds==""?"{null}":productIds));
					$("#productShopedit").attr("href","manage/productshop/toEditProductShop.do?productId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
