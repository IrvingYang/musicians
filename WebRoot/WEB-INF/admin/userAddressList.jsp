<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="pageContent" layoutH="20">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete" href="manage/useraddress/deleteUserAddress.do?keywords={null}" target="ajaxTodo" title="确定要删除吗?" id="userAddressDelete"><span>删除</span></a></li>
			<li><a class="edit" href="manage/useraddress/toEditUserAddress.do?keywords={null}" target="navTab" id="userAddressEdit" title="修改普通用户"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
		
	</div>
	<table  width="100%" id="userAddressDatatables" class="display" >	
<!-- 	class=""  layoutH="200"  -->
		<thead>
			<tr>
				<th width=""></th>
				<th width="">用户名</th>
				<th width="">邮件</th>
				<th width="">省</th>
				<th width="">市</th>
				<th width="">区(县)</th>
				<th width="">街道</th>
				<th width="">邮编</th>
				<th width="">电话</th>
				<th width="">联系人名称</th>
				<th width="">是否为默认地址</th>
				<th width="">送货时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userAddressList}" var="useraddress" varStatus="state">
				<tr rel="${useraddress.userId}and${useraddress.userAddressId}">
					<td>${state.index+1}</td>
					<td>${useraddress.user_Ext_Personal.user.userName}</td>
					<td>${useraddress.user_Ext_Personal.user.email}</td>
					<td>${useraddress.district.city.state.stateName }</td>
					<td>${useraddress.district.city.cityName}</td>
					<td>${useraddress.district.districtName}</td>
					<td>${useraddress.street}</td>
					<td>${useraddress.postCode}</td>
					<td>${useraddress.telephone}</td>
					<td>${useraddress.name}</td>
					<td>${useraddress.defaultflag==0?'不是':'是'}</td>
					<td>
						<c:choose>
							<c:when test="${useraddress.deliveryschedule eq '01'}">周一至五 09:00-18:00</c:when>
							<c:when test="${useraddress.deliveryschedule eq '02'}">周一至五 18:00-21:00</c:when>
							<c:when test="${useraddress.deliveryschedule eq '03'}">周六至周日 09:00-21:00</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			
		</tbody>
		<tfoot>
			<tr>
				<th width=""></th>
				<th width="">用户名</th>
				<th width="">邮件</th>
				<th width="">省</th>
				<th width="">市</th>
				<th width="">区(县)</th>
				<th width="">街道</th>
				<th width="">邮编</th>
				<th width="">电话</th>
				<th width="">联系人名称</th>
				<th width="">是否为默认地址</th>
				<th width="">送货时间</th>
			</tr>
		</tfoot>
	</table>
		<script type="text/javascript">
			 $('#userAddressDatatables').dataTable({
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
			 
			 $('#userAddressDatatables tbody').on( 'click', 'tr', function () {
			        $(this).toggleClass('selected');
			        var selected = $("#userAddressDatatables").children("tbody").children(".selected");
			        var productIds = "";
 					$.each(selected, function(index, data){
 						if(index==0){
 	 						productIds+=$(data).attr("rel")==undefined?"":$(data).attr("rel");
 						}else{
 							productIds+="&keywords="+$(data).attr("rel");
 						}
 					});
 					$("#userAddressDelete").attr("href","manage/useraddress/deleteUserAddress.do?keywords="+(productIds==""?"{null}":productIds));
					$("#userAddressEdit").attr("href","manage/useraddress/toEditUserAddress.do?keywords="+(productIds==""?"{null}":productIds)); 
			  } );
			 
		</script>
</div>
