<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登陆</title>

<form:form action="user/user/login.shtml" method="post"  modelAttribute="loginAttribute">

	<input type="hidden" value="${returnURL}" name="returnURL" />
	<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<th>Username</th>
			<td><form:input type="text"  class="login-inp" path="userName" /></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><form:input type="password" value="************"  onfocus="this.value=''" class="login-inp" path="password"/></td>
		</tr>
		<tr>
			<th></th>
			<td valign="top"><input type="checkbox" class="checkbox-size" id="login-check" /><label for="login-check">Remember me</label></td>
		</tr>
		<tr>
			<th></th>
			<td><input type="submit" class="submit-login"  /></td>
		</tr>
	</table>
</form:form>