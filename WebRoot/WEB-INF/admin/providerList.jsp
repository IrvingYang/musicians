<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/provider/toAddProvider.do" target="navTab" title="添加合作社"><span>添加</span></a></li>
			<li><a class="delete" href="manage/provider/deleteProvider.do?providerId={null}" target="ajaxTodo" title="确定要删除吗?" id="providerDelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/provider/toEditProvider.do?providerId={null}" target="navTab" id="providerEdit" title="修改合作社信息"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="providerDatables" class="display" style=" table-layout:fixed;">	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width="10"></th>
				<th width="">供应商名称</th>
				<th width="">方向</th>
				<th width="150">地址</th>
				<th width="">邮编</th>
				<th width="100">联系电话</th>
				<th width="">联系人</th>
				<th width="100">联系人电话</th>
				<th width="130">描述</th>
				<th width="130">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${providersList}" var="provider" varStatus="state">
				<tr rel="${provider.providerId}">
					<td>${state.index+1}</td>
					<td>${provider.name}</td>
					<td>
					<c:choose>
						<c:when test="${provider.direction eq '01'}">西南</c:when>
						<c:when test="${provider.direction eq '02'}">西北</c:when>
						<c:when test="${provider.direction eq '03'}">东南</c:when>
						<c:when test="${provider.direction eq '04'}">东北</c:when>
					</c:choose>
					</td>
					<td>${provider.city.state.stateName}${provider.city.cityName}${provider.street}</td>
					<td>${provider.postCode}</td>
					<td>${provider.telephone}</td>
					<td>${provider.contactman}</td>
					<td>${provider.phone}</td>
					<td style="overflow:hidden; text-overflow:ellipsis; word-break:keep-all; white-space:nowrap;">${provider.description}</td>
					<td>${provider.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width=""></th>
				<th width="">供应商名称</th>
				<th width="">方向</th>
				<th width="">地址</th>
				<th width="">邮编</th>
				<th width="">联系电话</th>
				<th width="">联系人</th>
				<th width="">联系人电话</th>
				<th width="">描述</th>
				<th width="">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#providerDatables').dataTable({
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
			 
			 $('#providerDatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#providerDatables").children("tbody").children(".selected");
			        var productIds = "";
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&providerId="+$(data).attr("rel");
 						}
 					});
 					$("#providerDelete").attr("href","manage/provider/deleteProvider.do?providerId="+(productIds==""?"{null}":productIds));
					$("#providerEdit").attr("href","manage/provider/toEditProvider.do?providerId="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
