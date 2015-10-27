<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/district/toEditDistrict.do?action=add" target="navTab" title="添加城市"><span>添加</span></a></li>
			<li><a class="delete" href="manage/district/deleteDistrict.do?districtId={null}" target="ajaxTodo" title="确定要删除吗?" id="districtndelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/district/toEditDistrict.do?action=update&districtId={null}" target="navTab" id="districtedit" title="修改城市"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="districtDatatables" class="display" >	
		<thead>
			<tr>
				<th width="">编码</th>
				<th width="">区域名</th>
				<th width="">城市名</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${districtsList}" var="district" varStatus="state">
				<tr rel="${district.districtId}">
					<td>${district.districtId}</td>
					<td>${district.districtName}</td>
					<td>${district.city.cityName}</td>				
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">编码</th>
				<th width="">城市名</th>
				<th width="">省份</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#districtDatatables').dataTable({
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
			 
			 $('#districtDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#districtDatatables").children("tbody").children(".selected");
			        var productIds = '';
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&districtId="+$(data).attr("rel");
 						}
 					});
 					$("#districtndelete").attr("href","manage/district/deleteDistrict.do?districtId="+(productIds==""?"{null}":productIds));
					$("#districtedit").attr("href","manage/district/toEditDistrict.do?action=update&districtId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
