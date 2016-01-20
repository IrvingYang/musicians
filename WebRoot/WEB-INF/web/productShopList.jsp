<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>乐器商城</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="resources/css/sticky-footer.css" />
<link rel="stylesheet" href="resources/css/non-responsive.css" />
<link rel="stylesheet" href="resources/css/bootstrap-dashboard.css" />
<link rel="stylesheet" href="resources/css/bootstrap-carousel.css" />
<link rel="stylesheet" href="resources/css/global.css" />
<link rel="stylesheet" href="resources/css/custom.css" />
<link rel="stylesheet" href="resources/css/product-list.css" />
<link rel="stylesheet" href="resources/css/dropdown.css" />

<script src="resources/js/jquery-1.11.3.min.js"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/web/common/top-nav.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/web/common/top-nav2.jsp"></jsp:include>
	<!-- content -->
	<div class="container main">
		<a class="top_ad" href="javascript:;"> <img
			src="resources/images/top_ad.jpeg"></a>
		<div class="col-sm-3 col-md-2 left-menu">
			<h3 class="" style="display: none;">精确搜索</h3>
			<div class="left-view">

				<div class="brand according">
					<strong data-target="brand"> <span class="facetArrow"></span>
						<p>商标搜索</p>
					</strong>
					<div class="brand-content" id="brand">
						<input class="form-control" type="text" placeholder="搜索你想要的商标" />
						<div class="brand-list-content">
							<c:forEach items="${vendorsList}" var="brand">
								<div class="item">
									<input name="brands" onchange="getSelectedBrand()"
										type="checkbox" value="${brand.brandid}" /> <label
										for="brands01">${brand.brandname}</label>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="price according">
					<strong data-target="price"> <span class="facetArrow"></span>
						<p>按价格</p>
					</strong>
					<div class="price-content" id="price">
						<div>
							<span><input name="s_price" onchange="getAllPrices()"
								type="checkbox" value="100" /></span> <label for="s_price01">￥0-￥100</label>
						</div>
						<div>
							<span><input name="s_price" onchange="getAllPrices()"
								type="checkbox" value="500" /></span> <label for="s_price02">￥100-￥500</label>
						</div>
						<div>
							<span><input name="s_price" onchange="getAllPrices()"
								type="checkbox" value="1000" /></span> <label for="s_price03">￥500-￥1000</label>
						</div>
						<div>
							<span><input name="s_price" onchange="getAllPrices()"
								type="checkbox" value="3000" /></span> <label for="s_price04">￥1000-￥3000</label>
						</div>
						<div>
							<span><input name="s_price" onchange="getAllPrices()"
								type="checkbox" value="*" /></span> <label for="s_price05">￥3000以上</label>
						</div>
					</div>
				</div>

				<div class="rating according">
					<strong data-target="rating"> <span class="facetArrow"></span>
						<p>按评价</p>
					</strong>
					<div class="" id="rating">
						<div>
							<span><input name="sort_ratings" id="s_rating01" value="5"
								onchange="getSelectedRate()" type="radio" /></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star">以上</span>
						</div>
						<div>
							<span><input name="sort_ratings" id="s_rating02" value="4"
								onchange="getSelectedRate()" type="radio" /></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star-empty">以上</span>
						</div>
						<div>
							<span><input name="sort_ratings" id="s_rating03" value="3"
								onchange="getSelectedRate()" type="radio" /></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star-empty"> </span> <span
								class="glyphicon glyphicon-star-empty">以上</span>
						</div>
						<div>
							<span><input name="sort_ratings" id="s_rating04" value="2"
								onchange="getSelectedRate()" type="radio" /></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star-empty"></span> <span
								class="glyphicon glyphicon-star-empty"> </span> <span
								class="glyphicon glyphicon-star-empty">以上</span>
						</div>
						<div>
							<span><input name="sort_ratings" id="s_rating05" value="1"
								onchange="getSelectedRate()" type="radio" /></span> <span
								class="glyphicon glyphicon-star"></span> <span
								class="glyphicon glyphicon-star-empty"></span> <span
								class="glyphicon glyphicon-star-empty"></span> <span
								class="glyphicon glyphicon-star-empty"> </span> <span
								class="glyphicon glyphicon-star-empty">以上</span>
						</div>
					</div>
				</div>
			</div>
			<div class="">
				<img src="resources/images/left_ad.jpg">
			</div>
		</div>

		<div class="col-sm-9  col-md-10  main">
			<ol class="tabs">
				<li class="txt first-child"><a href="#" class="selected">测试分类标题<span></span></a>
				</li>
			</ol>
			<div class="product_list_box">
				<div class="list">
					<div class="sort">
						<div class="left">
							排序： <select id="sort">
								<option value="sell">销量</option>
								<option value="priceH">价格从高到低</option>
								<option value="priceL">价格从低到高</option>
								<option value="laest">最新</option>
								<option value="name">名字</option>
								<option value="point">评分</option>
							</select>
						</div>
						<c:choose>
							<c:when test="${typename!=''&&typename!=null}">
								<a onclick="getSelectedType()" class="btn btn-primary btn-xs">
									${typename} <span class="glyphicon glyphicon-remove"></span>
								</a>
							</c:when>
						</c:choose>
					</div>

					<c:forEach items="${shopList}" var="shop">
						<div class="col-sm-3 col-lg-3 col-md-3 product">
							<div class="productTag">
								<strong> <c:choose>
										<c:when test="${shop.promoteflag==1}">促销商品</c:when>
										<c:when test="${shop.promoteflag==0}">最新商品</c:when>
									</c:choose>
								</strong>
							</div>
							<div class="thumbnail">
								<img src="${shop.product.productimglist[0].path}" /> <a
									href="eshop/shopProduct/productShopDetail.html?productId=${shop.productId}">查看详情</a>
							</div>

							<div class="caption">
								<a> ${shop.product.productName } </a>
								<div class="comment">
									<span class="stars"></span> <span class="comment_count"><a
										href="javascript:;">${shop.product.reviewcount }评论</a></span>
								</div>
								<c:choose>
									<c:when test="${shop.promoteflag==1}">
										<p class="">促销价：￥${shop.promotePrice}</p>
										<p class="">
											原价：￥${shop.originalPrice}<br />(节省￥${shop.originalPrice-shop.promotePrice})
										</p>
									</c:when>
									<c:when test="${shop.promoteflag!=1}">
										<p class="">现价：￥${shop.originalPrice}</p>
										<p class="">
											商品定价：￥${shop.product.shopPrice}<br />(节省￥${shop.product.shopPrice-shop.originalPrice})
										</p>
									</c:when>
								</c:choose>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="page_info">
					<!-- <div class="sort_by">
						<span> 排序： <select id="sort">
								<option value="sell">销量</option>
								<option value="priceH">价格从高到低</option>
								<option value="priceL">价格从低到高</option>
								<option value="laest">最新</option>
								<option value="name">名字</option>
								<option value="point">评分</option>
						</select>
						</span>
					</div> -->
					<ul id="pagination-demo" class="pull-right"></ul>
				</div>
			</div>
		</div>
	</div>


	<!-- footer -->
	<jsp:include page="/WEB-INF/web/common/footer-nav.jsp"></jsp:include>


	<form id="pageForm"
		action="<%=basePath%>eshop/shopProduct/shopProduct.shtml" method=POST>
		<input type="hidden" name="action" value="list" /> <input
			type="hidden" id="pageNo" name="pageNostr" value="${page.pageno+1}" />
		<input type="hidden" id="pageSize" name="pageSizestr"
			value="${page.pagesize }" /> <input type="hidden" id="search"
			name="searchstr" value="${search}" /> <input type="hidden"
			id="order" name="orderstr" value="${orderstr}" /> <input
			type="hidden" id="pricestr" name="pricestr" value="${pricestr}" /> <input
			type="hidden" id="brandstr" name="brandstr" value="${brandstr}" /> <input
			type="hidden" id="gradestr" name="gradestr" value="${gradestr}" /> <input
			type="hidden" id="typestr" name="typestr" value="${typestr}" /> <input
			type="hidden" id="typename" name="typename" value="${typename}" />

	</form>

	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.twbsPagination.min.js"></script>
	<script src="resources/js/html5shiv.min.js"></script>
	<script src="resources/js/respond.min.js"></script>



	<script type="text/javascript">
		/* function onSubmit(){
			$.post("eshop/shopProduct/shopProduct.shtml", {
				'pageNostr' : $('#pageNo').val(),
				'pageSizestr' :$('#pageSize').val(),
				'searchstr' : $('#search').val(),
				'orderstr' : $('#order').val(),
				'pricestr' : $('#pricestr').val(),
				'brandstr' : $('#brandstr').val(),
				'gradestr' : $('#gradestr').val(),
				'typestr' : $('#typestr').val(),
				'typename' : $('#typename').val()
			}, function() {
				window.location.reload();
			});
			
		} */

		$(document)
				.ready(
						function() {
							var prices = '${pricestr}'.split(',');
							$(":checkbox[name='s_price']").each(
									function() {
										$(this)
												.attr(
														"checked",
														prices.indexOf($(this)
																.val()) != -1);
									});

							var prices = '${brandstr}'.split(',');
							$(":checkbox[name='brands']").each(
									function() {
										$(this)
												.attr(
														"checked",
														prices.indexOf($(this)
																.val()) != -1);
									});
							$("input:radio[name='sort_ratings']")
									.each(
											function() {
												$(this)
														.attr(
																"checked",
																'${gradestr}' == $(
																		this)
																		.val());
											});

							$('#sort')
									.on(
											'change',
											function() {
												top.document
														.getElementsByName("orderstr")[0].value = $(
														this).val();
												document.getElementById(
														"pageForm").submit();
											});

							$('select[id="sort"] option').each(function() {
								if (this.value == '${orderstr}') {
									$(this).attr("selected", 'selected');
								}
							});

							$("#myid li a")
									.click(
											function() {
												top.document
														.getElementsByName("searchstr")[0].value = '${search}';
												top.document
														.getElementsByName("typestr")[0].value = this.id;
												top.document
														.getElementsByName("typename")[0].value = $(
														this).text();
												document.getElementById(
														"pageForm").submit();
											});

							//left menu
							$(".left-view > .according")
									.each(
											function(i, item) {
												$(item)
														.find("strong")
														.bind(
																'click',
																function() {
																	$(
																			"#"
																					+ $(
																							this)
																							.data(
																									"target"))
																			.toggleClass(
																					'hidden');

																});
											});

							$('#pagination-demo').twbsPagination({
						        totalPages: '${page.totalpage}',
						        visiblePages:5,
						        first :'首页',
						        prev:'上一页',
						        next :'下一页',
						        last :'尾页',
						         href: 'eshop/shopProduct/shopProduct.shtml?pageNostr={{number}}&&c=d' ,
						        onPageClick: function (event, page) {
						        }
						    });

						});

		function getAllPrices() {
			var pricestr = []
			$(":checkbox[name='s_price']:checked").each(function() {
				pricestr.push($(this).val());
			});
			top.document.getElementsByName("searchstr")[0].value = '${search}';
			top.document.getElementsByName("pricestr")[0].value = pricestr
					.join();

			/*  	top.document.getElementsByName("pageNostr")[0].value=0;
			 	top.document.getElementsByName("pageSizestr")[0].value=12;
			 	if('${search}'!=''){
			 	document.getElementById("pageForm").action="eshop/shopProduct/searchProductShop.action"
			 	} */
			document.getElementById("pageForm").submit();
		}

		function getSelectedBrand() {
			var brandsstr = []
			$(":checkbox[name='brands']:checked").each(function() {
				brandsstr.push($(this).val());
			});
			top.document.getElementsByName("searchstr")[0].value = '${search}';
			top.document.getElementsByName("brandstr")[0].value = brandsstr
					.join();
			document.getElementById("pageForm").submit();
		}

		function getSelectedRate() {
			top.document.getElementsByName("searchstr")[0].value = '${search}';
			top.document.getElementsByName("gradestr")[0].value = $(
					"input:radio[name='sort_ratings']:checked").val();
			document.getElementById("pageForm").submit();
		}

		function getSelectedType() {
			top.document.getElementsByName("typestr")[0].value = '';
			top.document.getElementsByName("typename")[0].value = '';
			document.getElementById("pageForm").submit();
		}

		function forOrder() {

		}
	</script>
</body>
</html>