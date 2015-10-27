'<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<div class="pageContent">

	<form method="post" action="manage/oper/addOper.do" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<input type="hidden" value="${action}" name="action"/>
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden"  value="1" name="validflag">
			<input type="hidden"  value="0" name="loginStatus">
			<input type="hidden"  value="1" name="partnerflag">
			<fieldset>
			<legend id="addtype">操作员编辑</legend>
			<dl>
				<dt>操作员账号</dt>
				<dd>
					<input type="text" name="operName" class="required"/>
				</dd>
			</dl>
			<dl>
				<dt>操作员密码</dt>
				<dd>
					<input type="text" name="password" class="required"/>
				</dd>
			</dl>
			<dl>
				<dt>选择角色</dt>
				<dd>
					<input type="hidden" class="required" name="providerlookup.roleId" value=""  lookupGroup="providerlookup" /> 
					<input type="text" class="required" name="providerlookup.roleName" value="" readonly="readonly"  lookupGroup="providerlookup" /> 
					<input type="hidden" class="required" name="providerlookup.providerId" value="" readonly="readonly"  lookupGroup="providerlookup" /> 
					<a class="btnLook" id="chAdPro" href="manage/role/getAllRoleInfo.do?type=1&action=dialog" lookupGroup="providerlookup">查找选择角色</a>
				</dd>
			</dl>
			<dl>
				<dt>邮件</dt>
				<dd>
					<input type="text" name="email" class="required email"/>
				</dd>
			</dl>
			<dl>
				<dt>性别</dt>
				<dd>
					<label><input type="radio" name="sex" class="required" value="1" checked="checked"/>男</label>
					<label><input type="radio" name="sex" class="required" value="0"/>女</label>
				</dd>
			</dl>
			<dl>
				<dt>移动电话</dt>
				<dd>
					<input type="text" name="telephone" class="required"/>
				</dd>
			</dl>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
