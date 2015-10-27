<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<div class="pageContent">

	<form method="post" action="manage/recommendlinetype/${action eq 'add'?'addRecommendlinetype.do':'updateRecommendlinetype.do'}" class="pageForm required-validate" name="myactivityForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<c:if test="${action eq 'update'}">
				<input type="hidden" value="${recommendlinetype.linetypeid}" name="linetypeid"/>
			</c:if>
			<dl>
				<dt>类型名称：</dt>
				<dd>
					<input name="title" type="text" class="required" size="30" value="${recommendlinetype.title}"/>
				</dd>
			</dl>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>













