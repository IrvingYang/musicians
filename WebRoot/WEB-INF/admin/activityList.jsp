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
	
	$('#activityDatatables tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	       prod_selected = $("#activityDatatables").children("tbody").children(".selected");
	        var productIds = '';
				$.each(prod_selected, function(index, data){
					if(index==0){
 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
					}else{
						productIds+="&activityid="+$(data).attr("rel");
					}
				});
			$("#activitydelete").attr("href","manage/activity/deleteActivity.do?activityid="+(productIds==""?"{null}":productIds));
			$("#activityedit").attr("href","manage/activity/toEditActivityPage.do?opFlag=2&activityid="+(productIds==""?"{null}":productIds));
			$("#signupstart").attr("href","manage/activity/activityBusiness.do?action=signupStart&activityid="+(productIds==""?"{null}":productIds));
			$("#signupend").attr("href","manage/activity/activityBusiness.do?action=signupEnd&activityid="+(productIds==""?"{null}":productIds));
			$("#activitystart").attr("href","manage/activity/activityBusiness.do?action=begin&activityid="+(productIds==""?"{null}":productIds));
			$("#activityend").attr("href","manage/activity/activityBusiness.do?action=end&activityid="+(productIds==""?"{null}":productIds));
	  } );
</script>
<div class="pageContent"  layoutH="0">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manage/activity/toEditActivityPage.do?opFlag=1" target="navTab" title="添加活动"><span>添加</span></a></li>
			<li><a class="delete" href="manage/activity/deleteActivity.do?activityid={activity}" target="ajaxTodo" title="确定要删除吗?" id="activitydelete" ><span>删除</span></a></li>
			<li><a class="edit" href="manage/activity/toEditActivityPage.do?opFlag=2&activityid={activity}" target="navTab" id="activityedit"><span>修改</span></a></li>
			<li><a class="edit" href="manage/activity/activityBusiness.do?action=signupStart&activityid={activity}" target="ajaxTodo" id="signupstart" title="确定要开始报名吗?"><span>开始报名</span></a></li>
			<li><a class="edit" href="manage/activity/activityBusiness.do?action=signupEnd&activityid={activity}" target="ajaxTodo" id="signupend" title="确定结束报名吗?"><span>结束报名</span></a></li>
			<li><a class="edit" href="manage/activity/activityBusiness.do?action=begin&activityid={activity}" target="ajaxTodo" id="activitystart" title="确定要开始所选活动吗?"><span>开始活动</span></a></li>
			<li><a class="edit" href="manage/activity/activityBusiness.do?action=end&activityid={activity}" target="ajaxTodo" id="activityend" title="确定结束此活动吗?"><span>结束活动</span></a></li>
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
		<table  width="100%" id="activityDatatables" class="display" >
			<thead>
				<tr>
					<th width="10"></th>
					<c:if test="${sessionScope.admin.partnerflag==0}">
						<th width="80" class="activityPartner">合作社名称</th>
					</c:if>
					<th width="">活动标题</th>
					<th width="80">申请开始日期</th>
					<th width="80">申请结束日期</th>
					<th width="70">开始时间</th>
					<th width="80">状态</th>
					<th width="70">预定数量</th>
					<th width="70">已报名成人</th>
					<th width="70">已报名儿童</th>
					<th width="70">已报名总人数</th>
<!-- 					<th width="50">VIP专属</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${activitiesList}" var="activity" varStatus="state">
				
					<c:if test="${activity.providerId!=sessionScope.admin.providerId}">
						<c:set var="classes" value="activityPartner"/>
					</c:if>
					<c:if test="${activity.providerId==sessionScope.admin.providerId}">
						<c:set var="classes" value=""/>
					</c:if>
				
					<tr target="activity" rel="${activity.activityid}" class="${classes}">
						<td class="odd">${state.index+1}</td>
						<c:if test="${sessionScope.admin.partnerflag==0}">
							<td class="activityPartner">${activity.provider.name}</td>
						</c:if>
						<td><p style="text-overflow:ellipsis; white-space: nowrap; overflow: hidden; width: 400px;">${activity.title}</p></td>
						<fmt:formatDate value="${activity.beginapplytime}" pattern="yyyy-MM-dd" var="beginapplytime"/>
						<td>${beginapplytime}</td>
						<fmt:formatDate value="${activity.endapplytime}" pattern="yyyy-MM-dd" var="endapplytime"/>
						<td>${endapplytime}</td>
						<fmt:formatDate value="${activity.begintime}" pattern="yyyy-MM-dd" var="begintime"/>
						<td>${begintime}</td>
						<td>
							<c:choose>
								<c:when test="${activity.status==0}">未开始</c:when>
								<c:when test="${activity.status==1}">报名中</c:when>
								<c:when test="${activity.status==2}">报名已结束</c:when>
								<c:when test="${activity.status==3}">活动已开始</c:when>
								<c:when test="${activity.status==4}">活动已结束</c:when>
							</c:choose>
						</td>
						<td>${activity.bookcount}</td>
						<td>${activity.applyadultCount}</td>
						<td>${activity.applychildCount}</td>
						<td>${activity.applyCount}</td>
<!-- 						<td>${activity.isvip==1?'是':'否'}</td> -->
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
				<th width="10"></th>
				<c:if test="${sessionScope.admin.partnerflag==0}">
					<th width="80" class="activityPartner">合作社名称</th>
				</c:if>
				<th width="">活动标题</th>
				<th width="80">申请开始日期</th>
				<th width="80">申请结束日期</th>
				<th width="70">开始时间</th>
				<th width="80">状态</th>
				<th width="70">预定数量</th>
				<th width="70">已报名成人</th>
				<th width="70">已报名儿童</th>
				<th width="70">已报名总人数</th>
<!-- 				<th width="50">VIP专属</th> -->
				</tr>
			</tfoot>
		</table>
	</div>
	
		<script type="text/javascript">
		prod_oTable = $('#activityDatatables').dataTable({
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
