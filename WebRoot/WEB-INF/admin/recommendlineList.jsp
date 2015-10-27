<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">
var prod_selected;
var prod_oTable; 
	$(function(){
		$("#activityPartner").children("label").children("input[type='radio']").click(function(){
			if($(this).val()==0){
				$(".activityPartner").hide();
			}else if($(this).val()==1){
				$(".activityPartner").show();
			}
		});
	});
	
	$('#recommendlineDatatables tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	       prod_selected = $("#recommendlineDatatables").children("tbody").children(".selected");
	        var productIds = '';
				$.each(prod_selected, function(index, data){
					if(index==0){
 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
					}else{
						productIds+="&recommendlineid="+$(data).attr("rel");
					}
				});
			$("#activitydelete").attr("href","manage/recommendline/deleteRecommendline.do?recommendlineid="+(productIds==""?"{null}":productIds));
			$("#activityedit").attr("href","manage/recommendline/toEditRecommendlinePage.do?opFlag=2&recommendlineid="+(productIds==""?"{null}":productIds));
			$("#signupstart").attr("href","manage/recommendline/recommendlineBusiness.do?action=signupStart&recommendlineid="+(productIds==""?"{null}":productIds));
			$("#signupend").attr("href","manage/recommendline/recommendlineBusiness.do?action=signupEnd&recommendlineid="+(productIds==""?"{null}":productIds));
			$("#activitystart").attr("href","manage/recommendline/recommendlineBusiness.do?action=begin&recommendlineid="+(productIds==""?"{null}":productIds));
			$("#activityend").attr("href","manage/recommendline/recommendlineBusiness.do?action=end&recommendlineid="+(productIds==""?"{null}":productIds));
	  } );
</script>
<div class="pageContent"  layoutH="0">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/recommendline/toEditRecommendlinePage.do?opFlag=1" target="navTab" title="添加线路"><span>添加</span></a></li>
			<li><a class="delete" href="manage/recommendline/deleteRecommendline.do?recommendlineid={null}" target="ajaxTodo" title="确定要删除吗?" id="activitydelete" ><span>删除</span></a></li>
			<li><a class="edit" href="manage/recommendline/toEditRecommendlinePage.do?opFlag=2&recommendlineid={null}" target="navTab" id="activityedit"><span>修改</span></a></li>
			<li><a class="edit" href="manage/recommendline/recommendlineBusiness.do?action=signupEnd&recommendlineid={null}" target="ajaxTodo" id="signupstart" title="确定要开始报名吗?"><span>开始报名</span></a></li>
			<li><a class="edit" href="manage/recommendline/recommendlineBusiness.do?action=signupEnd&recommendlineid={null}" target="ajaxTodo" id="signupend" title="确定结束报名吗?"><span>结束报名</span></a></li>
			<li><a class="edit" href="manage/recommendline/recommendlineBusiness.do?action=begin&recommendlineid={null}" target="ajaxTodo" id="activitystart" title="确定要开始所选活动吗?"><span>开始活动</span></a></li>
			<li><a class="edit" href="manage/recommendline/recommendlineBusiness.do?action=end&recommendlineid={null}" target="ajaxTodo" id="activityend" title="确定结束此活动吗?"><span>结束活动</span></a></li>
			<c:if test="${sessionScope.admin.partnerflag==0}">
				<li style="margin-top:5px;">显示非自营产品 </li>
				<li id="activityPartner"  class="edit">
					<label><input type="radio" name="salesbyself" value="1" checked="checked"/>是</label>
					<label><input type="radio" name="salesbyself" value="0" />否</label>
				</li>
			</c:if>
		</ul>
		
	</div>
	<div id="w_list_print" layoutH="35" >
		<table  width="100%" id="recommendlineDatatables" class="display" >
			<thead>
				<tr>
					<th width="10"></th>
					<c:if test="${sessionScope.admin.partnerflag==0}">
						<th width="80" class="activityPartner">合作社</th>
					</c:if>
					<th width="">线路标题</th>
					<th width="100">线路类型</th>
					<th width="80">申请开始日期</th>
					<th width="90">申请结束日期</th>
					<th width="80">开始时间</th>
					<th width="90">状态</th>
					<th width="70">价格</th>
					<th width="70">出游天数</th>
					<th width="70">已报名成人</th>
					<th width="70">已报名儿童</th>
					<th width="70">已报名总人数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${recommendlinesList}" var="recommendline" varStatus="state">
				
					<c:if test="${recommendline.providerId!=sessionScope.admin.providerId}">
						<c:set var="classes" value="activityPartner"/>
					</c:if>
					<c:if test="${recommendline.providerId==sessionScope.admin.providerId}">
						<c:set var="classes" value=""/>
					</c:if>
				
					<tr target="activity" rel="${recommendline.recommendlineid}" class="${classes}">
						<td class="odd">${state.index+1}</td>
						<c:if test="${sessionScope.admin.partnerflag==0}">
							<td class="activityPartner">${recommendline.provider.name}</td>
						</c:if>
						<td><p style="text-overflow:ellipsis; white-space: nowrap; overflow: hidden; width: 200px;">${recommendline.title}</p></td>
						<td>${recommendline.recommendlinetype.title}</td>
						<fmt:formatDate value="${recommendline.beginapplytime}" pattern="yyyy-MM-dd" var="beginapplytime"/>
						<td>${beginapplytime}</td>
						<fmt:formatDate value="${recommendline.endapplytime}" pattern="yyyy-MM-dd" var="endapplytime"/>
						<td>${endapplytime}</td>
						<fmt:formatDate value="${recommendline.departuretime}" pattern="yyyy-MM-dd" var="departuretime"/>
						<td>${departuretime}</td>
						<td>
							<c:choose>
								<c:when test="${recommendline.status==0}">未开始</c:when>
								<c:when test="${recommendline.status==1}">报名中</c:when>
								<c:when test="${recommendline.status==2}">报名已结束</c:when>
								<c:when test="${recommendline.status==3}">活动已开始</c:when>
								<c:when test="${recommendline.status==4}">活动已结束</c:when>
							</c:choose>
						</td>
						<td>${recommendline.price}元</td>
						<td>${recommendline.days}天</td>
						<td>${recommendline.applyadultCount}</td>
						<td>${recommendline.applychildCount}</td>
						<td>${recommendline.applyCount}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th width="10"></th>
					<c:if test="${sessionScope.admin.partnerflag==0}">
						<th width="80" class="activityPartner">合作社</th>
					</c:if>
					<th width="">线路标题</th>
					<th width="100">线路类型</th>
					<th width="80">申请开始日期</th>
					<th width="90">申请结束日期</th>
					<th width="80">开始时间</th>
					<th width="90">状态</th>
					<th width="70">价格</th>
					<th width="70">出游天数</th>
					<th width="70">已报名成人</th>
					<th width="70">已报名儿童</th>
					<th width="70">已报名总人数</th>
				</tr>
			</tfoot>
		</table>
	</div>
	
		<script type="text/javascript">
		prod_oTable = $('#recommendlineDatatables').dataTable({
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
