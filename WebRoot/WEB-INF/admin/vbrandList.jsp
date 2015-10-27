<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/brand_vendor/toEditBrand.do?action=add" target="navTab" title="添加品牌"><span>添加</span></a></li>
			<li><a class="delete" href="manage/brand_vendor/deleteBrand.do?brandid={null}" target="ajaxTodo" title="确定要删除吗?" id="branddelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/brand_vendor/toEditBrand.do?action=update&brandid={null}" target="navTab" id="brandedit" title="修改品牌"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="brandDatatables" class="display" >	
		<thead>
			<tr>
				<th width="">编号</th>
				<th width="">名称</th>
				<th width="">链接</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vendorsList}" var="brand" varStatus="state">
				<tr rel="${brand.brandid}">
					<td>${brand.brandid}</td>
					<td>${brand.brandname}</td>
					<td>${brand.url}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">编号</th>
				<th width="">名称</th>
				<th width="">链接</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#brandDatatables').dataTable({
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
			 
			 $('#brandDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#brandDatatables").children("tbody").children(".selected");
			        var productIds = '';
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&productId="+$(data).attr("rel");
 						}
 					});
 					$("#branddelete").attr("href","manage/brand_vendor/deleteBrand.do?brandid="+(productIds==""?"{null}":productIds));
					$("#brandedit").attr("href","manage/brand_vendor/toEditBrand.do?action=update&brandid="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
