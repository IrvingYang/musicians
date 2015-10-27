<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:forEach items="${arrangeRoleUrlList}" var="url">
<li ><a tname="name" tvalue="${url.urlId}">${url.description }</a>
<!-- 	<c:if test="${!empty url.urlList }"> -->
<!-- 		<ul> -->
<!-- 			<c:set var="arrangeRoleUrlList" value="${urllist.urlList}" scope="request"/> -->
<!-- 			<jsp:include page="/WEB-INF/admin/recursive.jsp"></jsp:include> -->
<!-- 		</ul> -->
<!-- 	</c:if> -->
</li>
</c:forEach>

