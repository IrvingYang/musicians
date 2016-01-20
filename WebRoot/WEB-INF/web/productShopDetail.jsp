<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>商品详细页-${shop.product.productName}</title>

<link rel="stylesheet" href="resources/css/bootstrap.css"/>
<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css"/>
<link rel="stylesheet" href="resources/css/sticky-footer.css"/>
<link rel="stylesheet" href="resources/css/non-responsive.css"/>
<link rel="stylesheet" href="resources/css/bootstrap-table.css"/>
<link rel="stylesheet" href="resources/css/bootstrap-table-filter.css"/>
<link rel="stylesheet" href="resources/css/custom.css"/>
<link rel="stylesheet" href="resources/css/global.css"/>
<link rel="stylesheet" href="resources/css/product-detail.css"/>
<link rel="stylesheet" href="resources/css/lightbox.css"/>
</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/web/common/top-nav.jsp"></jsp:include>
	<!-- content -->
	<div class="container main">
		<div class="row">
			<div class="col-sm-1 small-icon">
				<ul class="nav  nav-sidebar">
					<c:forEach begin="0" end="${fn:length(shop.product.productimglist)/3-1}" varStatus="status">
						<li data-target="#myCarousel1" data-slide-to="${status.index}" class="${status.index == 0?'active':'item'}">
							<div class="" id="size${shop.product.productimglist.size()}">
								<a data-target="#myCarousel1" data-slide-to="${status.index}"><img class="img-responsive"
									src="${shop.product.productimglist[status.index*3].path }"></a>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>

			<div id="myCarousel1" class="carousel col-sm-6" data-ride="carousel">

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<c:forEach begin="0" end="${fn:length(shop.product.productimglist)/3-1}" varStatus="status">
						<div class="${status.index == 0?'item active':'item'}">
							<a href="${shop.product.productimglist[status.index*3].path}" data-lightbox="example-1"><img src="${shop.product.productimglist[status.index*3+1].path}" class="center-block"></a>
						</div>
					</c:forEach>
				</div>
			</div>

			<div class="col-sm-5">
				<!-- 商品信息、购买和价钱 -->
				<div class="">
					<ul class="list-inline">
						<li><img class="img-thumbnail" src="resources/images/brand01.jpg" /></li>
						<li>${shop.productId}</li>
					</ul>
					<dl>
						<dt>${shop.product.productName}</dt>
						<dd>${shop.product.description}</dd>
					</dl>
					<div>
						<span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><span
							class="glyphicon glyphicon-star"></span><span class="glyphicon glyphicon-star"></span><a href="javascript:;"> 30条评价</a>
					</div>
					<div>
						<dl class="">
							<c:choose>
								<c:when test="${shop.promoteflag==1}">
									<dt>
										<span>促销价:</span> ￥${shop.promotePrice}
									</dt>

									<dd>
										<span>原价:</span>￥${shop.originalPrice}（ 购买节省￥${shop.originalPrice-shop.promotePrice})
									</dd>
								</c:when>
								<c:when test="${shop.promoteflag!=1}">
									<dt>
										<span>现价 :</span> ￥${shop.originalPrice}
									</dt>
									<dd>商品定价:￥${shop.product.shopPrice}（购买节省￥${shop.product.shopPrice-shop.originalPrice}）</dd>
								</c:when>
							</c:choose>
						</dl>
						<!-- 	<div class="text-right">
								运费：<span>免运费</span>
							</div> -->
					</div>
					<div class="form-inline buy-btn-group">
						<div class="input-group" style="width: 120px">
							<span class="input-group-btn">
								<button type="button" class="btn btn-danger btn-number" data-type="minus" data-field="addcount">
									<span class="glyphicon glyphicon-minus"></span>
								</button>
							</span>
							<input type="text" class="form-control input-number" value="1" min="1" max="100" name="addcount">
							<span class="input-group-btn">
								<button type="button" class="btn btn-success btn-number" data-type="plus" data-field="addcount">
									<span class="glyphicon glyphicon-plus"></span>
								</button>
							</span>
						</div>
						<input type="button" id="addCart" value="去购买" class="btn btn-primary" onclick="updateCart()" />
						<input type="button" id="forRent" value="去租赁" class="btn btn-primary" data-toggle="modal" data-productTypeId="${shop.product.productTypeId}" data-productName="${shop.product.productName}" data-target="#rentModal"/>
						
					</div>
					<script type="text/javascript">
						function updateCart() {
							var pid = '${shop.productId}';
							var count = $("input[name='addcount']").val();
							$.post("eshop/product/operationCart.action", {
								'action' : 'add',
								'cartType' : '0',
								'productId' : pid,
								'addcount' : count
							}, function() {
								window.location.reload()

							});
						}
					</script>
				</div>
			</div>
		</div>


		<div class="well">
			<h4>猜你喜欢</h4>
			<!-- Carousel
            ================================================== -->
			<div id="myCarousel" class="carousel slide">
				<div class="carousel-inner">
					<div class="item active">
						<div class="row">
							<c:forEach items="${recommProdsList}" var="recommend" varStatus="status">
								<div class="col-md-3">
									<div class="thumbnail" id="myRecommend">
										<img class="img-responsive" id="reco" src="${recommend.product_ext_shop.product.productimglist[0].path}"
											alt="${status.index}">
										<div class="caption">
											<h3>${recommend.product_ext_shop.product.productName}</h3>
											<p>
												<a href="#" class="btn btn-primary" role="button">￥${recommend.product_ext_shop.product.shopPrice}</a> <a href="#"
													class="btn btn-default" role="button">Wishlist</a>
											</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#myCarousel" data-slide="prev"><i class="fa fa-chevron-left fa-2x"></i></a> <a
					class="right carousel-control" href="#myCarousel" data-slide="next"><i class="fa fa-chevron-right fa-2x"></i></a>

				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				</ol>
			</div>
		</div>
		<div class="row">
			<!-- 商品描述 -->
			<div class="center-block">
				<h3>商品详细</h3>
				<p class="center-block">${shop.product.remarkHtml}</p>
			</div>
		</div>
		
		<div>
			<div id="filter-bar"></div>
			<table id="tbl" data-toggle="table" data-url="eshop/productreview/getProductReviewById.action?productId=${shop.product.productId}" data-flat="true"
				data-height="500" data-page-size="5" data-side-pagination="server" data-pagination="true" data-page-list="[5, 10]" data-show-toggle="true"
				data-show-columns="true">
				<thead>
					<tr>
						<th data-field="productComments" data-align="center">评价心得</th>
						<th data-field="rate" data-align="center" data-formatter="ratingFormatter">客户满意度</th>
						<th data-field="user.userName" data-align="center">评价用户</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/bootstrap-table.js"></script>
<script src="resources/js/bootstrap-table-flatJSON.js"></script>
<!-- <script src="resources/js/bootstrap-table-filter.js"></script>
 -->
<script src="resources/js/html5shiv.min.js"></script>
<script src="resources/js/respond.min.js"></script>

<jsp:include page="/WEB-INF/web/common/modal-rent.jsp"></jsp:include>
<script>
function ratingFormatter(value, row) {
	var html1 = "";
	for (var i = 0; i < value; i++) {
		html1 += '<span class="glyphicon glyphicon-star"></span>';
	}
	for (var i = 0; i < 5 - value; i++) {
		html1 += '<span class="glyphicon glyphicon-star-empty"></span>';
	}
	return html1
}
$(document).ready(function() {
	$('#myCarousel1').carousel({
		interval : 0
	});
	
	
	$(".small-icon ul li").click(function() {
		$(".small-icon ul li").each(function(i, item){
			$(item).removeClass("active");
		});
	    $(this).addClass("active");
	});
});
</script>
<script type="text/javascript" src="resources/js/lightbox.min.js"></script>
<script type="text/javascript" src="resources/js/numberIncrease.js"></script>
<jsp:include page="/WEB-INF/web/common/footer-nav.jsp"></jsp:include>
</body>
</html>