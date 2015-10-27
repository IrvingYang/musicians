<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<div class="pageContent">

	<form method="post" action="manage/oper/updatePassword.do" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<fieldset>
			<legend id="addtype">个人用户密码修改</legend>
			<dl>
				<dt>输入旧密码：</dt>
				<dd>
					<input type="password" class="required" name="oldpwd" value="" size="20" placeholder="输入旧密码"/>
				</dd>
			</dl>
			<dl>
				<dt>输入新密码：</dt>
				<dd>
					<input name="newpwd" type="password" class="required" size="20" value="" placeholder="输入新密码"/>
				</dd>
			</dl>
			<dl>
				<dt>再次输入新密码：</dt>
				<dd>
					<input name="confirmpwd" type="password" class="required" size="20" value="" placeholder="再次输入新密码"/>
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
