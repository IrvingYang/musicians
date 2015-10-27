<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
var recommendProduct_selected;
var recommendProduct_oTable; 
	$('#recommendProductDatatables tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	        recommendProduct_selected = $("#recommendProductDatatables").children("tbody").children(".selected");
	        var productIds = '';
				$.each(recommendProduct_selected, function(index, data){
					if(index==0){
 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
					}else{
						productIds+="&productId="+$(data).attr("rel");
					}
				});
				$("#recommenProductdelete").attr("href","manage/recommendProduct/deleteRecommendProduct.do?productId="+(productIds==""?"{null}":productIds));
			$("#recommenProductedit").attr("href","manage/recommendProduct/toEditRecommendProduct.do?productId="+(productIds==""?"{null}":productIds)); 
	  } );
</script>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/recommendProduct/toAddRecomendProduct.do" target="navTab" title="添加商品"><span>添加</span></a></li>
			<li><a class="delete" href="manage/recommendProduct/ajaxDone.html?productId={product}" target="ajaxTodo" title="确定要删除吗?" id="recommenProductdelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/recommendProduct/toEditRecommendProduct.do?productId={product}" target="navTab" id="recommenProductedit"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="recommendProductDatatables" class="display" >
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width="120">商品</th>
				<th width="120">商品名</th>
				<th width="40">库存</th>
				<th width="80">商品原价</th>
				<th width="80">促销价</th>
				<th width="80">是否促销中</th>
				<th width="170">推荐开始时间</th>
				<th width="170">推荐结束时间</th>
				<th width="170">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${recommProdsList}" var="recommend" varStatus="state">
				<tr target="product" rel="${recommend.productId}">
					<td>${recommend.product_ext_shop.product.productName}</td>
					<td>${recommend.productName}</td>
					<td>${recommend.product_ext_shop.product.stockNumber}</td>
					<td>
						<c:if test="${recommend.product_ext_shop.promoteflag==0}">
							${recommend.product_ext_shop.originalPrice }
						</c:if>
						<c:if test="${recommend.product_ext_shop.promoteflag==1}">
							<s>${recommend.product_ext_shop.originalPrice }</s>
						</c:if>
					</td>
					<td>${recommend.product_ext_shop.promotePrice}</td>
					<td>${recommend.product_ext_shop.promoteflag==1?"是":"否"}</td>
					<td>${recommend.recommBeginTime}</td>
					<td>${recommend.recommEndTime}</td>
					<td>${recommend.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="120">商品</th>
				<th width="120">商品名</th>
				<th width="40">库存</th>
				<th width="80">商品原价</th>
				<th width="80">促销价</th>
				<th width="80">是否促销中</th>
				<th width="170">推荐开始时间</th>
				<th width="170">推荐结束时间</th>
				<th width="170">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			recommendProduct_oTable = $('#recommendProductDatatables').dataTable({
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
