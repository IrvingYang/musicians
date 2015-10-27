<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="pageContent">
	<div class="pageFormContent" layoutH="58">
		<ul class="tree expand">
			
			<c:forEach items="${statesList}" var="state">
				<li><a href="javascript:">${state.stateName}</a>
					<ul>
						<c:forEach items="${state.citysList}" var="city">
						<li><a href="javascript:" onclick="$.bringBack({cityId:'${city.cityId}', cityName:'${city.cityName }',stateId:'${state.stateId}', stateName:'${state.stateName}'})">${city.cityName }</a></li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
		
		</ul>
	</div>
	
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</div>