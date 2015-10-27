<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="pageContent">

	<table  width="100%" id="lookgroupbuyDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width=""></th>
				<th width="">商品名</th>
				<th width="">团购模式</th>
				<th width="">团购价格</th>
				<th width="">开始时间</th>
				<th width="">结束时间</th>
				<th width="">开团条件</th>
				<th width="">开团结果</th>
				<th width="30">查找</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${groupBuysList}" var="group" varStatus="state">
				<tr rel="${group.groupbuyid}">
					<td>${state.index+1}</td>
					<td>${group.product.productName}</td>
					<td>
					<c:choose>
						<c:when test="${group.groupBuyType==0}">预开团</c:when>
						<c:when test="${group.groupBuyType==1}">立即开团</c:when>
					</c:choose>
					</td>
					<td>${group.groupBuyPrice}</td>
					<fmt:formatDate value="${group.groupBuyStartTime}" pattern="yyyy-MM-dd" var="startTime"/>
					<td>${startTime}</td>
					<fmt:formatDate value="${group.groupBuyEndTime}" pattern="yyyy-MM-dd" var="endTime"/>
					<td>${endTime}</td>
					<td>
					<c:choose>
						<c:when test="${group.targetType==0}">以金额为标准</c:when>
						<c:when test="${group.targetType==1}">以数量为标准</c:when>
					</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${empty group.openresult}">等待开团</c:when>
							<c:when test="${group.openresult==0}">失败</c:when>
							<c:when test="${group.openresult==1}">成功</c:when>
						</c:choose>
					</td>
					<td>
						<a class="btnSelect" href="javascript:$.bringBack({groupbuyid:'${group.groupbuyid}',productId:'${group.groupbuyid}',productName:'${group.product.productName}'})" title="查找带回">选择</a>
					</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
</div>

<script type="text/javascript">
	 $('#lookgroupbuyDatatables').dataTable({
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
