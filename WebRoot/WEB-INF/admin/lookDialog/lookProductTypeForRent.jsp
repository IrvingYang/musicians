<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">

	<table  targetType="dialog" width="100%" id="lookProductType">
		<thead>
			<tr>
				<th width=""></th>
				<th width="">类型名称</th>
				<th width="">上级名称</th>
				<th width="">最后更新时间</th>
				<th width="30">选择</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${productTypes}" var="producttype" varStatus="state">
				<tr target="product" rel="${producttype.productTypeId}">
					<td class="odd gradeA">${state.index+1}</td>
					<td>${producttype.typeName}</td>
					<td>${(empty producttype.parentProductType)?'一级类型':producttype.parentProductType.typeName}</td>
					<td>${producttype.lastUpdateTime}</td>
					<td>
						<a class="btnSelect" href="javascript:$.bringBack({productTypeId:'${producttype.productTypeId}', typeName:'${producttype.typeName}'})" title="${producttype.typeName}">选择</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript">
	 $('#lookProductType').dataTable({
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