<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<div class="pageContent">

	<form method="post" action="manage/oper/updateDetailInfo.do" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<fieldset>
			<legend id="addtype">个人用户编辑</legend>
			<dl>
				<dt>邮箱：</dt>
				<dd>
					<input type="text" class="required" name="email" value="${oper.email}" size="20"/>
				</dd>
			</dl>
			<dl>
				<dt>性别：</dt>
				<dd>
					<label><input name="sex" type="radio" class="required" value="1" ${oper.sex==1?"checked":""}/>男</label>
					<label><input name="sex" type="radio" class="required" value="0" ${oper.sex==0?"checked":""}/>女</label>
				</dd>
			</dl>
			<dl>
				<dt>电话：</dt>
				<dd>
					<input name="telephone" type="text" class="required" size="20" value="${oper.telephone}" maxlength="11"/>
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
