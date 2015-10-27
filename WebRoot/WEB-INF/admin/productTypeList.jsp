<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
   var ProdType_oTable;
   var ProdType_selected;
	$(function(){
		$("#salesbyself").children("label").children("input[type='radio']").click(function(){
			if($(this).val()==0){
				$(".salesbyself").hide();
			}else if($(this).val()==1){
				$(".salesbyself").show();
			}
		});
	});

	 $('#productTypeDatatables tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	        ProdType_selected = $("#productTypeDatatables").children("tbody").children(".selected");
	        var productTypeIds = '';
			$.each(ProdType_selected, function(index, data){
				if(index==0){
					productTypeIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
				}else{
					productTypeIds+="&productTypeId="+$(data).attr("rel");
				}
			});
			$("#productTypedelete").attr("href","manage/productType/deleteProductType.do?productTypeId="+(productTypeIds==""?"{null}":productTypeIds));
			$("#productTypeedit").attr("href","manage/productType/toEditProductType.do?productTypeId="+(productTypeIds==""?"{null}":productTypeIds));
	  } );
</script>



<div class="pageContent" layoutH="5">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/productType/toAddProductType.do" target="navTab" title="添加商品类型"><span>添加</span></a></li>
			<li><a class="delete" href="manage/productType/deleteProductType.do?productTypeId={product}" target="ajaxTodo" title="确定要删除吗?" id="productTypedelete" ><span>删除</span></a></li>
			<li><a class="edit" href="manage/productType/toEditProductType.do?productTypeId={product}" target="navTab" id="productTypeedit"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="productTypeDatatables" class="display">
		<thead>
			<tr>
				<th width="10"></th>
				<th width="">类型名称</th>
				<th width="">上级名称</th>
				<th width="280">含有属性</th>
				<th width="80">最后更新时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productTypes}" var="producttype" varStatus="state">
				<tr target="product" rel="${producttype.productTypeId}">
					<td class="odd gradeA">${state.index+1}</td>
					<td>${producttype.typeName}</td>
					<td>${(empty producttype.parentProductType)?'一级类型':producttype.parentProductType.typeName}</td>
					<td>${producttype.properties}</td>
					<td>${producttype.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="10"></th>
				<th width="120">类型名称</th>
				<th width="100">上级类型</th>
				<th width="80">含有属性</th>
				<th width="80">最后更新时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
		ProdType_oTable = $('#productTypeDatatables').dataTable({
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
