<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">

	<table  targetType="dialog" width="100%" id="shopProductDialog">
		<thead>
			<tr>
				<th width="">商品名</th>
				<th width="">上级时间</th>
				<th width="">下架时间</th>
				<th width="">原价</th>
				<th width="">促销价</th>
				<th width="">促销</th>
				<th width="">单位</th>
				<th width="">促销开始时间</th>
				<th width="">促销结束时间</th>
				<th width="30">选择</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productShop}" var="shop" varStatus="state">
				<tr rel="${shop.productId}">
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
					<td>${shop.promotePrice}</td>
					<td>${shop.promoteflag==0?"否":"是"}</td>
					<td>${shop.unit}</td>
					<td>${shop.promoteStartTime}</td>
					<td>${shop.promoteEndTime}</td>
					<td>
						<a class="btnSelect" href="javascript:$.bringBack({productId:'${shop.productId}', productName:'${shop.product.productName}', productTypeId:'${shop.product.productTypeId}'})" title="${shop.product.productName}">选择</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript">
	 $('#shopProductDialog').dataTable({
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