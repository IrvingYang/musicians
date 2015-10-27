<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<div class="pageContent">

	<form method="post" action="manage/role/addRole.do" class="pageForm required-validate" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<input type="hidden" value="${action}" name="action"/>
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden"  value="1" name="validflag">
			<fieldset>
			<legend id="addtype">角色操作</legend>
			<dl>
				<dt>角色名字</dt>
				<dd>
					<input type="text" name="roledesc" class="required"/>
				</dd>
			</dl>
			<c:if test="${sessionScope.admin.partnerflag==0 }">
				<dl>
					<dt>选择合作社</dt>
					<dd>
						<select name="providerId">
							<option value="${sessionScope.admin.role.provider.providerId}">${sessionScope.admin.role.provider.name}</option>
							<c:forEach items="${providersList}" var="provider">
								<option value="${provider.providerId}">${provider.name}</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
			</c:if>
			
			<c:if test="${sessionScope.admin.partnerflag==1}">
					<input type="hidden" name="providerId" class="required" value="${sessionScope.admin.role.provider.providerId}"/>
			</c:if>
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
