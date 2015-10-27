<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->

<div class="pageContent">

	<form method="post" action="manage/user/validateEntUser.do" id="ventform" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden" name="userId" value="${user.userId}"/>
			<fieldset>
			<legend>企业信息查看审核</legend>
			
			<dl>
				<dt>企业名称：</dt>
				<dd style="font-size:20px; font-weight: bold;">
					${user.enterprisename}
				</dd>
			</dl>
			
			<dl>
				<dt>营业执照影像：</dt>
				<dd>
					<input type="hidden" name="image1" value="${imageURl1}" id="image1"/>
					<img src="${imageURl1}" alt="营业执照影像" id="image1image"/>
				</dd>
			</dl>
			<dl>
				<dt>税务登记证影像：</dt>
				<dd>
					<input type="hidden" name="image2" value="${imageURl2}" id="image2"/>
					<img src="${imageURl2}" alt="税务登记证影像" id="image2image"/>
				</dd>
			</dl>
			<dl>
				<dt>组织机构代码证影像：</dt>
				<dd>
					<input type="hidden" name="image3" value="${imageURl3}" id="image3"/>
					<img src="${imageURl3}" alt="组织机构代码证影像" id="image3image"/>
				</dd>
			</dl>
			<dl>
				<dt>是否通过审核：</dt>
				<dd>
					<label><input type="radio" name="type" id="entvStatus" value="1" class="required"/>是</label>
					<label><input type="radio" name="type" id="entvStatus" value="0" class="required"/>否</label>
				</dd>
			</dl>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="submit"  id="validentn">提交操作</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="submit"  id="validentn" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
