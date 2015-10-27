<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="header_topList">
	<div class="content_maxwidth">
		<ul class="fst_nav">
			<!-- logo -->
			<li class="logo"><a href=""> <img
					src="resources/images/logo.jpg">
			</a></li>
			<!-- 链接 -->
			<li class="topLink"><a
				${flag eq 'shop' ? 'class="active"' : '' }
				href="eshop/shopProduct/shopProduct.shtml?action=list">商城</a> <a
				${flag eq '' ? 'class="active"' : ''} href="javascript:;">论坛</a> <a
				${flag eq '' ? 'class="active"' : ''} href="javascript:;">教育</a></li>
			<!-- 搜索 -->
			<li class="search">
				<div class="search_box">
					<form id="form-id"
						action="eshop/shopProduct/searchProductShop.action" method="POST">
						<input type="hidden" name="pageNostr" value="${pageNostr}" /> <input
							type="hidden" name="pageSizestr" value="${pageSizestr}" /> <input
							class="search_text" name="searchstr" type="text" placeholder="搜索"
							value="${search}" />
						<button id="search_button" class="search_btn" type="submit" />
					</form>
				</div>
			</li>
			<!-- 登录 -->
			<li class="login">
				<div id="login">
					<c:if test="${empty sessionScope.user }">
						<a href="user/user/signup.shtml" class="lg_txt1">登录</a>
					</c:if>
					<c:if test="${!empty sessionScope.user }">
						<a class="lg_txt1">您好：${sessionScope.user.user.userName}</a>
						<div class="login_sednav">
							<ul>
								<li><a href="user/user/userCenter.shtml">我的资料</a></li>
								<li><a
									href="order/orderList/orderList.shtml?action=notfinished">我的订单</a></li>
								<li><a href="javascript:;">我的收藏</a></li>
								<!-- <li><a href="user/user/logout.action">登出</a></li> -->
							</ul>
						</div>
					</c:if>

					<%--  <div class="login_sednav">
                         <ul>
                             <li><a href="user/user/userCenter.shtml">我d的资料</a></li>
                             <li><a href="order/orderList/orderList.shtml?action=notfinished">我的订单</a></li>
                             <li><a href="javascript:;">我的收藏</a></li>
                 	 		 <c:if test="${empty sessionScope.user }">
                             <li><a class="sign_btn" href="user/user/signup.shtml">登录</a></li>
                             </c:if>
                         </ul>
                     </div> --%>
				</div>
			</li>
			<!-- 购物车 -->
			<li id="cart" class="cart_li"><a href="javascript:;"
				class="cart">
					<div class="cart_icon">
						<span class="cart_count">${fn:length(sessionScope.shopping_cart.map)}</span>
					</div>
					<div class="cart_txt">
						<p>购物车</p>
					</div>
			</a>
				<div class="cart_sednav">
					<ul>
						<c:forEach items="${sessionScope.shopping_cart.map}" var="cart">
							<li>
								<%-- 	key = ${cart.key}, value = ${cart.value}<br> --%>
								<div class="cart_pro">
									<input type="text" hidden="true" id="aproductId"
										name="aproductId" value="${cart.value['product'].productId}">
									<span class="cart_proImg"> <img
										src="${cart.value['product'].productimglist[0].path}">
									</span> <span class="cart_proOther">
										<p class="proOther1">${cart.value['product'].productName }
										<p>
										<p class="proOther2">
											<span>数量：</span>
										<p id="acount">${cart.value['productCount']}</p>
										</p>
										<p class="proOther3">
											<span>单价：</span>
											<c:choose>
												<c:when test="${cart.value['promoteflag']==0}">
													<p id="asubtotal">${cart.value['originalPrice']}</p>
												</c:when>
												<c:when test="${cart.value['promoteflag']==1}">
													<p id="asubtotal">${cart.value['promotePrice']}</p>
												</c:when>
											</c:choose>
										</p>
									</span>
								</div>
							</li>
						</c:forEach>
					</ul>
					<div class="cart_settle">
						<a href="eshop/shopProduct/cartList.do">去结算</a>
					</div>
				</div></li>

		</ul>

	</div>
</div>