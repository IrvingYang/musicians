<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	var newProduct_selected;
	var newProduct_oTable; 

	$('#newProductTables tbody').on( 'click', 'tr', function () {
	      $(this).toggleClass('selected');
	      newProduct_selected = $("#newProductTables").children("tbody").children(".selected");
	      var productIds = '';
		$.each(newProduct_selected, function(index, data){
			if(index==0){
					productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
			}else{
				productIds+="&productId="+$(data).attr("rel");
			}
		});
		$("#newProductdelete").attr("href","manage/newProduct/deleteNewProduct.do?productId="+(productIds==""?"{null}":productIds));
		$("#newProductedit").attr("href","manage/newProduct/toEditNewProduct.do?productId="+(productIds==""?"{null}":productIds)); 
	} );
</script>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/newProduct/toAddNewProduct.do" target="navTab" title="添加商品"><span>添加</span></a></li>
			<li><a class="delete" href="manage/newProduct/deleteNewProduct.do?productId=={product}" target="ajaxTodo" title="确定要删除吗?" id="newProductdelete" ><span>删除</span></a></li>
			<li><a class="edit" href="manage/newProduct/toEditNewProduct.do?productId={null}" target="navTab" id="newProductedit"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="newProductTables" class="display" >
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width="">商品</th>
				<th width="">商品名</th>
				<th width="">库存</th>
				<th width="">原价</th>
				<th width="">促销价</th>
				<th width="">是否促销中</th>
				<th width="">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${newProductList}" var="newproduct" varStatus="state">
				<tr target="product" rel="${newproduct.productId}">
					<td>${newproduct.product_ext_shop.product.productName}</td>
					<td>${newproduct.productName}</td>
					<td>${newproduct.product_ext_shop.product.stockNumber}</td>
					<td>
						<c:if test="${newproduct.product_ext_shop.promoteflag==0}">
							${newproduct.product_ext_shop.originalPrice }
						</c:if>
						<c:if test="${newproduct.product_ext_shop.promoteflag==1}">
							<s>${newproduct.product_ext_shop.originalPrice}</s>
						</c:if>
					</td>
					<td>${newproduct.product_ext_shop.promoteflag==1?newproduct.product_ext_shop.promotePrice:""}</td>
					<td>${newproduct.product_ext_shop.promoteflag==1?"是":"否"}</td>
					<td>${newproduct.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">商品</th>
				<th width="">商品名</th>
				<th width="">库存</th>
				<th width="">原价</th>
				<th width="">促销价</th>
				<th width="">是否促销中</th>
				<th width="">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			newProduct_oTable  = $('#newProductTables').dataTable({
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
