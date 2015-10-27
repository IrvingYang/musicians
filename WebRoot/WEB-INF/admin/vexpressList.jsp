<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/express/toEditExpress.do?action=add" target="navTab" title="添加快递公司"><span>添加</span></a></li>
			<li><a class="delete" href="manage/express/deleteExpress.do?vendorid={null}" target="ajaxTodo" title="确定要删除吗?" id="expressdelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/express/toEditExpress.do?action=update&vendorid={null}" target="navTab" id="expressedit" title="修改快递公司"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="expressDatatables" class="display" >	
		<thead>
			<tr>
				<th width="">编号</th>
				<th width="">快递名称</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${expressList}" var="express" varStatus="state">
				<tr rel="${express.vendorid}">
					<td>${express.vendorid}</td>
					<td>${express.vendorname}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">编号</th>
				<th width="">快递名称</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#expressDatatables').dataTable({
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
			 
			 $('#expressDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#expressDatatables").children("tbody").children(".selected");
			        var productIds = '';
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&vendorid="+$(data).attr("rel");
 						}
 					});
 					$("#expressdelete").attr("href","manage/express/deleteExpress.do?vendorid="+(productIds==""?"{null}":productIds));
					$("#expressedit").attr("href","manage/express/toEditExpress.do?action=update&vendorid="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
