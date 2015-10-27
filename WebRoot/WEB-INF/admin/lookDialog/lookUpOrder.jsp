<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">

	<table  targetType="dialog" width="100%" id="lookOrderDialog">
		<thead>
			<tr>
				<th width="">商品名</th>
				<th width="">商品类型</th>
				<th width="">库存</th>
				<th width="">商品定价</th>
				<th width="">品牌名称</th>
				<th width="">评级</th>
				<th width="">热度</th>
				<th width="">产地</th>
				<th width="">发布时间</th>
				<th width="">撤销时间</th>
				<th width="30">选择</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productsList}" var="product" varStatus="state">
				<tr>
					<td>${product.productName}</td>
					<td>${product.product_type.typeName}</td>
					<td>${product.stockNumber}</td>
					<td>￥${product.shopPrice}</td>
					<td>${product.brand_vendor.brandname}</td>
					<td>${product.productGrade}</td>
					<td>${product.attentionGrade}</td>
					<td>${product.city.cityName}</td>
					<td>${product.onTime}</td>
					<td>${product.offTime}</td>
					<td>
						<a class="btnSelect" href="javascript:$.bringBack({productId:'${product.productId}', productName:'${product.productName}',shopPrice:'${product.shopPrice}'})" title="查找带回">选择</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript">
	 $('#lookOrderDialog').dataTable({
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
</script>