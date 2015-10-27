<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="trip/modulerecommend/toEdit.do?action=add" target="navTab" title="添加广告"><span>添加</span></a></li>
			<li><a class="delete" href="trip/modulerecommend/deleteModuleRecommendline.do?entitytypeAndentityid={entitytypeAndentityid}" target="ajaxTodo" title="确定要删除吗?" id="adTripDelete"><span>删除</span></a></li>
			<li><a class="edit" href="trip/modulerecommend/toEdit.do?action=update&entitytypeAndentityid={entitytypeAndentityid}" target="navTab" id="adTripEdit" title="修改广告信息"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="adTripDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width="">广告名称</th>
				<th width="">广告类型</th>
				<th width="">推荐名称</th>
				<th width="">广告开始时间</th>
				<th width="">广告结束时间</th>
				<th width="">最后修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${moduleRecommendsList}" var="ad" >
				<tr rel="${ad.entitytype}and${ad.entityid}">
					<td>${ad.title}</td>
					<td>
						<c:choose>
							<c:when test="${ad.entitytype eq '01'}">趣采摘</c:when>
							<c:when test="${ad.entitytype eq '02'}">趣旅游</c:when>
							<c:when test="${ad.entitytype eq '03'}">趣分享</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${ad.entitytype eq '01'}">${ad.activitiesList[0].title}</c:when>
							<c:when test="${ad.entitytype eq '02'}">${ad.recommendlinesList[0].title}</c:when>
							<c:when test="${ad.entitytype eq '03'}">${ad.blogsList[0].title}</c:when>
						</c:choose>
					</td>
					<fmt:formatDate value="${ad.recommBeginTime}" var="begintime" pattern="yyyy-MM-dd"/>
					<td>${begintime}</td>
					<fmt:formatDate value="${ad.recommEndTime}" var="endtime" pattern="yyyy-MM-dd"/>
					<td>${endtime}</td>
					<td>${ad.lastUpdateTime}</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width="">广告名称</th>
				<th width="">广告类型</th>
				<th width="">推荐名称</th>
				<th width="">广告开始时间</th>
				<th width="">广告结束时间</th>
				<th width="">最后修改时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#adTripDatatables').dataTable({
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
			 
			 $('#adTripDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#adTripDatatables").children("tbody").children(".selected");
			        var productIds = "";
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&entitytypeAndentityid="+$(data).attr("rel");
 						}
 					});
 					$("#adTripDelete").attr("href","trip/modulerecommend/deleteModuleRecommendline.do?entitytypeAndentityid="+(productIds==""?"{null}":productIds));
					$("#adTripEdit").attr("href","trip/modulerecommend/toEdit.do?action=update&entitytypeAndentityid="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
