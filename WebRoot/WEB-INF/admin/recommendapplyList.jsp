<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript">
var prod_selected;
var prod_oTable; 
	
	$('#recommendlineApplyDatatables tbody').on( 'click', 'tr', function () {
	       $(this).toggleClass('selected');
	       prod_selected = $("#recommendlineApplyDatatables").children("tbody").children(".selected");
	        var productIds = '';
				$.each(prod_selected, function(index, data){
					if(index==0){
 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
					}else{			  
						productIds+="&useridAndRecommendlineid="+$(data).attr("rel");
					}
				});
			$("#recommendlineapplyupdateState").attr("href","manage/recommendlineapply/toUpdateRecommendlineAppyList.do?useridAndRecommendlineid="+(productIds==""?"{null}":productIds));
			$("#recommendlineapplyDelete").attr("href","manage/recommendlineapply/deleteRecommendlineApply.do?useridAndRecommendlineid="+(productIds==""?"{null}":productIds));
	  } );
</script>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					选择活动信息：
					<input type="hidden" name="recommendline.entityid" value="" id="recommendlineid" oldValue="" lookupGroup="recommendline"/>
					<input type="text" readonly="readonly" size="20" name="recommendline.title" lookupGroup="recommendline"/>
				</td>
				<td> 
				<a class="btnLook" href="manage/recommendline/getRecommendlineList.do?action=dialog" lookupGroup="recommendline">选择活动</a></td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent"  layoutH="0">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="manage/recommendlineapply/deleteRecommendlineApply.do?useridAndActivityid={null}" target="ajaxTodo" title="确定要删除吗?" id="recommendlineapplyDelete" ><span>删除</span></a></li>
			<li><a class="edit" href="manage/recommendlineapply/toUpdateRecommendlineAppyList.do?aciton=confirmJoin&useridAndActivityid={null}" target="dialog"  mask="true" id="recommendlineapplyupdateState"><span>修改状态</span></a></li>
		</ul>
		
	</div>
	<div id="w_list_print" layoutH="35" >
		<table  width="100%" id="recommendlineApplyDatatables" class="display" >
	<!-- 	class=""  layoutH="200"  -->
			<thead>
				<tr>
					<th width="10"></th>
					<th width="100">用户名</th>
					<th width="70">联系人</th>
					<th width="40">联系电话</th>
					<th width="40">活动标题</th>
					<th width="40">成人数量</th>
					<th width="40">小孩数量</th>
					<th width="40">状态</th>
					<th width="50">创建时间</th>
				</tr>
			</thead>
			<tbody id="activityApplyTable">
			</tbody>
			<tfoot>
				<tr>
					<th width="10"></th>
					<th width="100">用户名</th>
					<th width="70">联系人</th>
					<th width="40">联系电话</th>
					<th width="40">活动标题</th>
					<th width="40">成人数量</th>
					<th width="40">小孩数量</th>
					<th width="40">状态</th>
					<th width="50">创建时间</th>
				</tr>
			</tfoot>
		</table>
	</div>
	
		<script type="text/javascript">
		prod_oTable = $('#recommendlineApplyDatatables').dataTable({
			 "bDestroy":true,
	         "sPaginationType" : "full_numbers",
	 			"bSort": false, //排序功能
	 			"bInfo": false,//页脚信息
	 			"bFilter": false,
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
		function recommendlineThread()
		{
			var recommendlineidValue = $("#recommendlineid").val();
			var recommendlineidOldValue = $("#recommendlineid").attr("oldValue");
			if(recommendlineidValue != recommendlineidOldValue)
			{
				$("#recommendlineid").attr("oldValue",recommendlineidValue);
				$.ajax({
					url:"/manage/recommendlineapply/getRecommendlineApplyListByRecommendlineId.do",
					type:"GET",
					async: false,
					dataType:"text",
					data:{recommendlineid:recommendlineidValue},
					success:function(data){
						
						eval("var applyData = "+data);
						$("#activityApplyTable").children().remove();
						prod_oTable.fnClearTable();
						$.each(applyData,function(index,object)
						{
							var date = new Date(object.createtime);
							var year = date.getFullYear();
							
							var month = date.getMonth()+1;
							var day = date.getDate();
							var hours = date.getHours();
							var minutes = date.getMinutes();
							var seconds = date.getSeconds();
							
							var statusStr = "";
							switch(object.status)
							{
								case 0:
									statusStr="已报名";
									break;
								case 1:
									statusStr="已付款";
									break;
								case 2:
									statusStr="申请退款";
									break;
								case 3:
									statusStr="同意退款";
									break;
								case 4:
									statusStr="已退款";
									break;
								case 5:
									statusStr="确认参加";
									break;
								case 6:
									statusStr="活动完毕";
									break;
							}
							
							
							var html = 
								"<tr rel="+object.userid+"and"+object.recommendlineid+"><td>"+(index+1)+"</td>"+
								"<td>"+object.user.userName+"</td>"+
								"<td>"+object.name+"</td>"+
								"<td>"+object.phone+"</td>"+
								"<td>"+object.recommendline.title+"</td>"+
								"<td>"+object.adultcount+"</td>"+
								"<td>"+object.childcount+"</td>"+
								"<td>"+statusStr+"</td>"+//，status处理
								"<td>"+(year+'年'+month+'月'+day+'日  '+hours+':'+minutes+':'+seconds)+"</td></tr>";
							$("#activityApplyTable").append(html);
						});
// 						$("#activityApplyDatatables").dataTable({"bDestroy":true});
// 						prod_oTable = $('#activityApplyDatatables').dataTable({
// 							 "bDestroy":false,
// 					         "sPaginationType" : "full_numbers",
// 					         "oLanguage": {
// 					 			"sLengthMenu": "每页显示 _MENU_ 条记录",
// 					 			"sZeroRecords": "抱歉， 没有找到",
// 					 			"sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
// 					 			"sInfoEmpty": "没有数据",
// 					 			"sInfoFiltered": "(从 _MAX_ 条数据中检索)",
// 					 			"oPaginate": {  
// 					                     "sFirst": "首页",  
// 					                     "sPrevious": "上一页",
// 					                     "sNext": "下一页",  
// 					                     "sLast": "尾页"  
// 					                 }, 
// 							    "sSearch": "搜索:",
// 					 			"sZeroRecords": "没有检索到数据",  
// 					 			"sProcessing": "<img src='./loading.gif' />"
// 					         }
// 					     });
					}
				});
			}
		}
		setInterval(recommendlineThread, 1000);
		</script>
</div>
