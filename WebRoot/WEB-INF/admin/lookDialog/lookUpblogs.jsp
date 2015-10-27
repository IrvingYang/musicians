<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">
var prod_selected;
var prod_oTable; 
	
	$('#activityDatatables tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	       prod_selected = $("#activityDatatables").children("tbody").children(".selected");
	        var productIds = '';
				$.each(prod_selected, function(index, data){
					if(index==0){
 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
					}else{
						productIds+="&blogid="+$(data).attr("rel");
					}
				});
			$("#activitydelete").attr("href","manage/blogs/deleteBlogs.do?blogid="+(productIds==""?"{null}":productIds));
			$("#activityedit").attr("href","manage/blogs/getBlogsDetail.do?blogid="+(productIds==""?"{null}":productIds));
// 			$("#signupstart").attr("href","manage/activity/activityBusiness.do?action=signupStart&activityid="+(productIds==""?"{null}":productIds));
// 			$("#signupend").attr("href","manage/activity/activityBusiness.do?action=signupEnd&activityid="+(productIds==""?"{null}":productIds));
// 			$("#activitystart").attr("href","manage/activity/activityBusiness.do?action=begin&activityid="+(productIds==""?"{null}":productIds));
// 			$("#activityend").attr("href","manage/activity/activityBusiness.do?action=end&activityid="+(productIds==""?"{null}":productIds));
	  } );
</script>
<div class="pageContent"  layoutH="0">
	<div class="panelBar">
		<ul class="toolBar">
<!-- 			<li><a class="add" href="manage/activity/toEditActivityPage.do?opFlag=1" target="navTab" title="添加商品"><span>添加</span></a></li> -->
			<li><a class="delete" href="manage/blogs/deleteBlogs.do?blogid={blogid}" target="ajaxTodo" title="确定要删除吗?" id="activitydelete" ><span>删除</span></a></li>
			<li><a class="edit" href="manage/blogs/getBlogsDetail.do?blogid={blogid}" target="dialog" id="activityedit" max="true"><span>审核</span></a></li>
<!-- 			<li><a class="edit" href="manage/activity/activityBusiness.do?action=signupStart&activityid={activity}" target="ajaxTodo" id="signupstart" title="确定要开始报名吗?"><span>开始报名</span></a></li> -->
<!-- 			<li><a class="edit" href="manage/activity/activityBusiness.do?action=signupEnd&activityid={activity}" target="ajaxTodo" id="signupend" title="确定结束报名吗?"><span>结束报名</span></a></li> -->
<!-- 			<li><a class="edit" href="manage/activity/activityBusiness.do?action=begin&activityid={activity}" target="ajaxTodo" id="activitystart" title="确定要开始所选活动吗?"><span>开始活动</span></a></li> -->
<!-- 			<li><a class="edit" href="manage/activity/activityBusiness.do?action=end&activityid={activity}" target="ajaxTodo" id="activityend" title="确定结束此活动吗?"><span>结束活动</span></a></li> -->
		</ul>
		
	</div>
	<div id="w_list_print" layoutH="35" >
		<table  width="100%" id="activityDatatables" class="display" >
			<thead>
				<tr>
					<th width="10"></th>
					<th width="">游记标题</th>
					<th width="80">游记类型</th>
					<th width="80">阅读数量</th>
					<th width="70">创建时间</th>
					<th width="80">是否审核</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${blogsList}" var="blogs" varStatus="state">
					<tr target="activity" rel="${blogs.blogid}" >
						<td class="odd">${state.index+1}</td>
						<td >${blogs.title}</td>
						<td >${blogs.blogstype.blogstypename}</td>
						<td >${blogs.readcount}</td>
						<td >${blogs.createtime}</td>
						<td >${blogs.checkflag==1?'已审核':'未审核'}</td>
						<td>
							<a class="btnSelect" href="javascript:$.bringBack({entityid:'${blogs.blogid}', title:'${blogs.title}'})" title="${blogs.title}">选择</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th width="10"></th>
					<th width="">游记标题</th>
					<th width="80">游记类型</th>
					<th width="80">阅读数量</th>
					<th width="70">创建时间</th>
					<th width="80">是否审核</th>
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
