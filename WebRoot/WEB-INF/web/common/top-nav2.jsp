<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="row second">
    <div class="container">
		<div class="dropdown" id="left-dropdown">
			<a id="dLabel" role="button" data-toggle="dropdown" class="btn btn-black">商品分类:<span id="typeSelected" class="caret"></span></a>
			<ul class="dropdown-menu mega-dropdown-menu row left-menu" role="menu" aria-labelledby="dropdownMenu">
				<c:forEach items="${productTypeList}" var="fproduct">
					<li class="dropdown-submenu"><a tabindex="-1">${fproduct.typeName}</a>
						<ul id="myid" class="dropdown-menu mega-dropdown-menu row category-list">
							<c:forEach items="${fproduct.childTypeList}" var="sproduct" varStatus="sStatus">
								<div class="category-container">
									<div class="category-heading"><a id="${sproduct.productTypeId}">${sproduct.typeName}</a></div>
									<ul>
										<c:forEach items="${sproduct.childTypeList}" var="tproduct">
											<li><a id="${tproduct.productTypeId}">${tproduct.typeName }</a></li>
										</c:forEach>
									</ul>
								</div>
							</c:forEach>
						</ul></li>
				</c:forEach>
			</ul>
		</div>
    </div>
</div>