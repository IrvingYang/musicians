<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor/ueditor.all.min.js"> </script>

<div class="pageContent">

	<form method="post" action="manage/blogs/validation.do" id="ventform" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden" name="blogid" value="${blogs.blogid}"/>
			<input type="hidden" name="checkflag" value="1"/>
			<fieldset>
			<legend>游记审核</legend>
			<dl>
				<dt>游记标题：</dt>
				<dd style="font-size:20px; font-weight: bold;">
					${blogs.title}
				</dd>
			</dl>
			
			<dl>
				<dt>游记内容：</dt>
				<dd style="width:100%;">
					<textarea name="htmlpath" style="width:960px; min-height: 300px;" id="textcontent">${blogs.htmlpath}</textarea>
				</dd>
			</dl>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="submit"  id="validentn">通过审核</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="submit"  id="validentn" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	<script type="text/javascript">
		var ue = UE.getEditor("textcontent");
	</script>
</div>
