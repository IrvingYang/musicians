<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/state/toEditState.do?action=add" target="navTab" title="添加省份"><span>添加</span></a></li>
			<li><a class="delete" href="manage/state/deleteState.do?stateId={null}" target="ajaxTodo" title="确定要删除吗?" id="statedelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/state/toEditState.do?action=update&stateId={null}" target="navTab" id="stateedit" title="修改省份"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="stateDatatables" class="display" >	
		<thead>
			<tr>
				<th width="">编号</th>
				<th width="">省名称</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${statesList}" var="state" >
				<tr rel="${state.stateId}">
					<td>${state.stateId}</td>
					<td>${state.stateName}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">编号</th>
				<th width="">省名称</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#stateDatatables').dataTable({
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
			 
			 $('#stateDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#stateDatatables").children("tbody").children(".selected");
			        var productIds = '';
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&stateId="+$(data).attr("rel");
 						}
 					});
 					$("#statedelete").attr("href","manage/state/deleteState.do?stateId="+(productIds==""?"{null}":productIds));
					$("#stateedit").attr("href","manage/state/toEditState.do?action=update&stateId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
