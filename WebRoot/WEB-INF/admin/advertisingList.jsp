<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/ad/toAddAdvertising.do" target="navTab" title="添加广告"><span>添加</span></a></li>
			<li><a class="delete" href="manage/ad/deleteAdvertising.do?adid={null}" target="ajaxTodo" title="确定要删除吗?" id="adDelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/ad/toEditAdvertising.do?adid={null}" target="navTab" id="adEdit" title="修改广告信息"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="adDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width="">广告名称</th>
				<th width="">广告位</th>
				<th width="">广告类型</th>
				<th width="">广告开始时间</th>
				<th width="">广告结束时间</th>
				<th width="">是否收费</th>
				<th width="">收费</th>
				<th width="">价格</th>
				<th width="">序号</th>
				<th width="">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adlist}" var="ad" >
				<tr rel="${ad.adid}">
					<td>${ad.adname}</td>
					<td>${ad.adarea}</td>
					<td>
						<c:choose>
							<c:when test="${ad.adtype==1}">单个产品</c:when>
							<c:when test="${ad.adtype==2}">批量产品</c:when>
							<c:when test="${ad.adtype==3}">团购</c:when>
						</c:choose>
					</td>
					<fmt:formatDate value="${ad.adbegintime}" var="begintime" pattern="yyyy-MM-dd"/>
					<td>${begintime}</td>
					<fmt:formatDate value="${ad.adendtime}" var="endtime" pattern="yyyy-MM-dd"/>
					<td>${endtime}</td>
					<td>${ad.ischarge==1?'收费':'免费'}</td>
					<td>
						<c:choose>
							<c:when test="${ad.chargemode==1}">按日</c:when>
							<c:when test="${ad.chargemode==2}">按月</c:when>
							<c:when test="${ad.chargemode==3}">一次性</c:when>
						</c:choose>
					</td>
					<td>${ad.price}</td>
					<td>${ad.adserial}</td>
					<td>${ad.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">广告名称</th>
				<th width="">广告位</th>
				<th width="">广告类型</th>
				<th width="">广告开始时间</th>
				<th width="">广告结束时间</th>
				<th width="">是否收费</th>
				<th width="">收费</th>
				<th width="">价格</th>
				<th width="">序号</th>
				<th width="">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#adDatatables').dataTable({
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
			 
			 $('#adDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#adDatatables").children("tbody").children(".selected");
			        var productIds = "";
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&adid="+$(data).attr("rel");
 						}
 					});
 					$("#adDelete").attr("href","manage/ad/deleteAdvertising.do?adid="+(productIds==""?"{null}":productIds));
					$("#adEdit").attr("href","manage/ad/toEditAdvertising.do?adid="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
