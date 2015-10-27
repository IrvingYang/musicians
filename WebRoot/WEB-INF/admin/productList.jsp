<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript">
var prod_selected;
var prod_oTable; 
	$(function(){
		$("#salesbyself").children("label").children("input[type='radio']").click(function(){
			if($(this).val()==0){
				$(".salesbyself").hide();
			}else if($(this).val()==1){
				$(".salesbyself").show();
			}
		});
	});
	
	$('#productDatatables tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	       prod_selected = $("#productDatatables").children("tbody").children(".selected");
	        var productIds = '';
				$.each(prod_selected, function(index, data){
					if(index==0){
 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
					}else{
						productIds+="&productId="+$(data).attr("rel");
					}
				});
				$("#productdelete").attr("href","manage/product/deleteProduct.do?productId="+(productIds==""?"{null}":productIds));
			$("#productedit").attr("href","manage/product/toEditPage.do?productId="+(productIds==""?"{null}":productIds)); 
	  } );
</script>
<div class="pageContent"  layoutH="0">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/product/toAddProduct.do" target="navTab" title="添加商品"><span>添加</span></a></li>
			<li><a class="delete" href="manage/product/deleteProduct.do?productId={product}" target="ajaxTodo" title="确定要删除吗?" id="productdelete" ><span>删除</span></a></li>
			<li><a class="edit" href="manage/product/toEditPage.do?productId={product}" target="navTab" id="productedit"><span>修改</span></a></li>
			<li class="line">line</li>
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li style="margin-top:5px;">显示非自营产品 </li>
				<li id="salesbyself"  class="edit">
					<label><input type="radio" name="salesbyself" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="salesbyself" value="0" />否</label>
				</li>
			</c:if>
		</ul>
		
	</div>
	<div id="w_list_print" layoutH="35" >
		<table  width="100%" id="productDatatables" class="display" >
	<!-- 	class=""  layoutH="200"  -->
			<thead>
				<tr>
					<th width="10"></th>
					<th width="100">商品名称</th>
					<th width="70">商品类型</th>
					<th width="40">单位</th>
					<th width="40">库存</th>
					<th width="50">定价</th>
					<c:if test="${sessionScope.admin.partnerflag==0}">
						<th width="80"  class="salesbyself">产品销售商</th>
					</c:if>
					<th width="80">品牌名称</th>
					<th width="30">评级</th>
					<th width="40">热度</th>
					<th width="100">销售范围</th>
					<th width="80">产地</th>
					<th width="170">发布时间</th>
					<th width="170">撤销时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productsList}" var="product" varStatus="state">
					<c:if test="${product.providerId!=sessionScope.admin.providerId}">
						<c:set var="classes" value="salesbyself"/>
					</c:if>
					<c:if test="${product.providerId==sessionScope.admin.providerId}">
						<c:set var="classes" value=""/>
					</c:if>
					<tr target="product" rel="${product.productId}" class="${classes}">
						<td class="odd">${state.index+1}</td>
						<td>${product.productName}</td>
						<td>${product.product_type.typeName}</td>
						<td>${product.unit}</td>
						<td>${product.stockNumber}</td>
						<td>${product.shopPrice}</td>
						<c:if test="${sessionScope.admin.partnerflag==0}">
							<td class="salesbyself">${product.provider.name}</td>
						</c:if>
						<td>${product.brand_vendor.brandname}</td>
						<td>${product.productGrade}</td>
						<td>${product.attentionGrade}</td>
						<td>${fn:substring(product.salesrange,0,1) eq '1'?'商城':''}&nbsp;
						${fn:substring(product.salesrange,1,2) eq '1'?'团购':''}&nbsp;
						${fn:substring(product.salesrange,2,3) eq '1'?'大宗交易':''}&nbsp;</td>
						<td>${product.city.cityName}</td>
						<td>${product.onTime}</td>
						<td>${product.offTime}</td>
					</tr>
				</c:forEach>
				
			</tbody>
			<tfoot>
				<tr>
					<th width="10"></th>
					<th width="100">商品名称</th>
					<th width="70">商品类型</th>
					<th width="40">单位</th>
					<th width="40">库存</th>
					<th width="50">定价</th>
					<c:if test="${sessionScope.admin.partnerflag==0}">
						<th width="80"  class="salesbyself">产品销售商</th>
					</c:if>
					<th width="80">品牌名称</th>
					<th width="30">评级</th>
					<th width="40">热度</th>
					<th width="100">销售范围</th>
					<th width="80">产地</th>
					<th width="">发布时间</th>
					<th width="">撤销时间</th>
				</tr>
			</tfoot>
		</table>
	</div>
	
		<script type="text/javascript">
		prod_oTable = $('#productDatatables').dataTable({
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
