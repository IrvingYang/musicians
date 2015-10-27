<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<div class="pageContent">

	<form method="get" action="manage/activityapply/updateActivityStatus.do" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,dialogAjaxDone)">
		<input type="hidden" name="useridAndActivityid" value="${useridAndActivityids}"/>
		<div class="pageFormContent nowrap" layoutH="56">
		<fieldset>
			<legend id="addtype">编辑活动报名信息状态</legend>
			<dl>
				<dt>选择状态：</dt>
				<dd>
					<select name="status">
						<option value="1">已付款</option>
						<option value="2">申请退款</option>
						<option value="3">同意退款</option>
						<option value="4">已退款</option>
						<option value="5">确认参加</option>
						<option value="6">活动完毕</option>
					</select>
				</dd>
			</dl>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>