<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<div class="pageContent">

	<form method="post" action="manage/district/saveOrUpdate.do" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<input type="hidden" value="${action}" name="action"/>
		<div class="pageFormContent nowrap" layoutH="56">

			<fieldset>
			<legend id="addtype">区(县)信息编辑</legend>
			<dl>
				<dt>名称：</dt>
				<dd>
				<input type="hidden"  value="${district.districtId}" name="districtId">
				<input type="text"  class="required" value="${district.districtName}" name="districtName">
				</dd>
			</dl>
			<dl>
				<dt>选择省份</dt>
				<dd>
					<input type="hidden" name="district.cityId" value="${district.city.cityId}"/>
					<input type="text" class="required" name="district.cityName" value="${district.city.cityName}" readonly="readonly" lookupGroup="district" /> 
					<a class="btnLook" href="manage/city/getAllCity.do?action=dialog" lookupGroup="district">查找选择省份</a>
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
