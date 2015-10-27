<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!-- <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script> -->


<div class="pageContent">

	<form method="post" action="manage/user/editUser.do?userType=1" class="pageForm required-validate" name="myproducForm" onsubmit="return validateCallback(this ,navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="56">
			<input type="hidden" value="${user.userId}" name="userId"/>
			<input name="validflag" type="hidden"  value="1"/>
			<fieldset>
			<legend id="addtype">个人用户编辑</legend>
			<dl>
				<dt>用户名：</dt>
				<dd>
					<input type="text" name="userName" readonly value="${user.user.userName}" size="30"/>
				</dd>
			</dl>
<!-- 			<dl> -->
<!-- 				<dt>用户等级：</dt> -->
<!-- 				<dd> -->
<!-- 					<label><input name="grade" type="radio" class="required" size="20" value="1" ${user.grade==1?'checked':''} />一级</label> -->
<!-- 					<label><input name="grade" type="radio" class="required" size="20" value="2" ${user.grade==2?'checked':''} />二级</label> -->
<!-- 					<label><input name="grade" type="radio" class="required" size="20" value="3" ${user.grade==3?'checked':''} />三级</label> -->
<!-- 					<label><input name="grade" type="radio" class="required" size="20" value="4" ${user.grade==4?'checked':''} />四级</label> -->
<!-- 					<label><input name="grade" type="radio" class="required" size="20" value="5" ${user.grade==5?'checked':''} />五级</label> -->
<!-- 				</dd> -->
<!-- 			</dl> -->
			<dl>
				<dt>性别：</dt>
				<dd>
					<label><input name="sex" type="radio" class="required" size="20" value="1" ${user.sex==1?'checked':''} />男</label>
					<label><input name="sex" type="radio" class="required" size="20" value="0" ${user.sex==0?'checked':''} />女</label>
				</dd>
			</dl>
			<dl>
				<dt>邮箱：</dt>
				<dd>
					<input type="text" name="email" value="${user.user.email}" size="30"/>
				</dd>
			</dl>
			<dl>
				<dt>联系电话：</dt>
				<dd>
					<input name="telephone" type="text" class="required number" size="20" value="${user.telephone}"/>
				</dd>
			</dl>
			<dl>
				<dt>订阅状态：</dt>
				<dd>
					<label><input name="subscribe_status" type="radio" class="required" size="5" value="1" ${user.subscribe_status == 1?'checked':''} />已订阅</label>
					<label><input name="subscribe_status" type="radio" class="required" size="5" value="0" ${user.subscribe_status == 0?'checked':''} />未订阅</label>
				</dd>
			</dl>
			<dl>
				<dt>证件类型：</dt>
				<dd>
					<select name="certTypeID" size="6">
						<option  ${user.certTypeID eq 01?'selected':''} value="01">居民身份证</option>
						<option  ${user.certTypeID eq 02?'selected':''} value="02">护照</option>
						<option  ${user.certTypeID eq 03?'selected':''} value="03">港澳通行证</option>
						<option  ${user.certTypeID eq 04?'selected':''} value="04">台湾通行证</option>
						<option  ${user.certTypeID eq 05?'selected':''} value="05">回乡证</option>
						<option  ${user.certTypeID eq 06?'selected':''} value="06">军官证</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>证件号码</dt>
				<dd>
					<input name="certNumber" type="text" size="50" value="${user.certNumber}"  class="textInput valid" >
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
