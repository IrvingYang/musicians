<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<div class="pageContent">

	<form method="post" action="manage/country/saveOrUpdate.do" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<input type="hidden" value="${action}" name="action"/>
		<div class="pageFormContent nowrap" layoutH="56">

			<fieldset>
			<legend id="addtype">国家信息操作</legend>
			<dl>
				<dt>名称：</dt>
				<dd>
				<input type="hidden"  value="${country.countryId}" name="countryId">
				<input type="text"  class="required" value="${country.countryName}" name="countryName">
				</dd>
			</dl>
			<%-- <dl>
				<dt>选择省份</dt>
				<dd>
					<input type="hidden" name="citys.stateId" value="${city.state.stateId}"/>
					<input type="text" class="required" name="citys.stateName" value="${city.state.stateName}" readonly="readonly" lookupGroup="citys" /> 
					<a class="btnLook" href="manage/state/getStateList.do?action=dialog" lookupGroup="citys">查找选择省份</a>
				</dd>
			</dl> --%>
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
