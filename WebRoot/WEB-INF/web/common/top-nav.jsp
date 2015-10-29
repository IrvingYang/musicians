<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a href="#" class="navbar-brand"> <img alt="Brand" src="resources/images/logo.jpg">
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<!-- 链接 -->
				<li class="topLink"><a ${flag eq 'shop' ? 'class="active"' : '' } href="eshop/shopProduct/shopProduct.shtml?action=list">商城</a></li>
				<li><a ${flag eq '' ? 'class="active"' : ''} href="javascript:;">教育</a></li>
				<li><a href="#contact">论坛</a></li>
			</ul>
			<form id="form-id" class="navbar-form navbar-left" role="search" action="eshop/shopProduct/searchProductShop.action" method="POST">
				<input type="hidden" name="pageNostr" value="${pageNostr}" />
				<input type="hidden" name="pageSizestr" value="${pageSizestr}" />
				<input class="form-control" name="searchstr" type="text" value="${search}" placeholder="请输入商品名称" />
				<button id="search_button" class="btn btn-default" type="submit" onclick="$('#form-id').submit()">搜索</button>
			</form>

			<ul class="nav navbar-nav navbar-right ">

				<c:if test="${empty sessionScope.user }">
					<li class="vert-align"><button type="button" class="btn btn-default navbar-btn" onclick="location.href='user/user/signup.shtml'">登录</button></li>
				</c:if>
				<c:if test="${!empty sessionScope.user }">
					<!-- Single button -->
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">您好：${sessionScope.user.user.userName}
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="user/user/userCenter.do">我的资料</a></li>
							<li><a href="order/orderList/orderList.do?orderType=1">我的商品订单</a></li>
							<li><a href="order/orderList/orderList.do?orderType=100">我的租赁订单</a></li>
							<li class="divider"></li>
							<li><a href="user/user/logout.action">登出</a></li>
						</ul></li>

				</c:if>

				<li class="dropdown navbar-right"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">购物车 <span
						class="badge">${fn:length(sessionScope.shopping_cart.map)}</span></a>
					<ul class="dropdown-menu" role="menu">
						<c:forEach items="${sessionScope.shopping_cart.map}" var="cart">
							<c:choose>
							<c:when test="${cart.value['cartType']==0 }">
							<li style="width: 300px">
								<%-- 	key = ${cart.key}, value = ${cart.value}<br> --%> <%-- <input type="text" hidden="true" id="aproductId"
												name="aproductId" value="${cart.value['product'].productId}">
											<span class="cart_proImg"> <img
												src="${cart.value['product'].productimglist[0].path}">
											</span>
												<p class="proOther1">${cart.value['product'].productName }
												</p> --%>
								<ul class="list-inline">
									<li><img src="${cart.value['product'].productimglist[0].path}"></li>
									<li>
										<ul>
											<li><strong>${cart.value['product'].productName }</strong>
											<li>数量： ${cart.value['productCount']}</li>
											<li>单价：<c:choose>
													<c:when test="${cart.value['promoteflag']==0}">${cart.value['originalPrice']}</c:when>
													<c:when test="${cart.value['promoteflag']==1}">${cart.value['promotePrice']}</c:when>
												</c:choose> 
											</li>
										</ul>
									</li>
								</ul>
							</li>
							</c:when>
							
							<c:when test="${cart.value['cartType']==2 }">
							<li style="width: 300px">
								<ul class="list-inline">
									<li><img src="${cart.value['product'].productimglist[0].path}"></li>
									<li>
										<ul>
											<li><strong>${cart.value['product'].productName } (租赁)</strong>
											<li>数量： ${cart.value['productCount']}</li>
											<li>押金：${cart.value['yajin']} </li>
											<li>租期：${cart.value['leaseCycle']} 天</li>
										</ul>
									</li>
								</ul>
							</li>
							</c:when>
							</c:choose>
						</c:forEach>
						<li class="divider"></li>
						<li class="text-right"><button type="button" class="btn btn-danger btn-sm" onClick="parent.location='eshop/shopProduct/cartList.do'">去结算</button></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>

 <!-- <div class="row">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".main-nav">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse main-nav">
                <ul class="nav navbar-nav">
                    <li><a href="#">商城</a></li>
                    <li class="divider-vertical"></li>
                    <li><a href="#about">教育</a></li>
                    <li class="divider-vertical"></li>
                    <li><a href="#contact">论坛</a></li>
                    <li class="divider-vertical"></li>
                </ul>
            </div>
        </div>
    </div> -->