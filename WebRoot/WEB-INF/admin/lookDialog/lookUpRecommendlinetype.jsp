<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="pageContent"  layoutH="0">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/recommendlinetype/toEditActivityPage.do?opFlag=1" target="navTab" title="添加商品"><span>添加</span></a></li>
			<li><a class="delete" href="manage/recommendlinetype/deleteActivity.do?linetypeid={null}" target="ajaxTodo" title="确定要删除吗?" id="recommenlinetypedelete" ><span>删除</span></a></li>
			<li><a class="edit" href="manage/recommendlinetype/toEditActivityPage.do?opFlag=2&linetypeid={null}" target="navTab" id="recommenlinetypeedit"><span>修改</span></a></li>
		</ul>
		
	</div>
	<div id="w_list_print" layoutH="35" >
		<table  width="100%" id="recommenlinetypeDatatables1" class="display" >
			<thead>
				<tr>
					<th width="">线路类型名称</th>
					<th width="40">选择</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${recommendlinetypesList}" var="type" varStatus="state">
					<tr>
						<td>${type.title}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({linetypeid:'${type.linetypeid}',title:'${type.title}'})" title="${type.title}">选择</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th width="">线路类型名称</th>
					<th>选择</th>
				</tr>
			</tfoot>
		</table>
	</div>
	
		<script type="text/javascript">
		prod_oTable = $('#recommenlinetypeDatatables1').dataTable({
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
