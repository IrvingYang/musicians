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
<title>乐器商城</title>

<link rel="stylesheet" href="<%=basePath%>resources/css/public.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/css/search-and-classify.css" />
<link rel="stylesheet"
	href="<%=basePath%>resources/css/right-show-box.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>resources/css/style.css" />
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>resources/css/laypage.css">
<script src="<%=basePath%>resources/js/jquery-1.11.1.min.js"></script>
<script src="<%=basePath%>resources/js/easySlider1.7.js"></script>
<script src="<%=basePath%>resources/js/js.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/js/laypage.js"></script>
</head>
<body>
	<!-- header -->
	<div class="header">
		<!-- 导航 -->
		<div class="nav">
			<!-- 第一部分导航 -->
			<jsp:include page="/WEB-INF/web/common/top.jsp"></jsp:include>
			<div class="clear"></div>
			<!-- 第二部分导航 -->
			<jsp:include page="/WEB-INF/web/common/navigation.jsp"></jsp:include>
		</div>
	</div>

	<!-- content -->
	<div class="content_maxwidth topbot">
		<a class="top_ad" href="javascript:;"> <img
			src="resources/images/top_ad.jpeg">
		</a>

		<div class="lsb">
			<div class="classify">
				<h3 class="lsb_title">商品分类</h3>
				<c:forEach items="${productTypeList}" var="productType">
					<a href="javascript:;">${productType.typeName}</a>
				</c:forEach>
			</div>

			<div class="search_by top20">
				<h3 class="lsb_title">精确搜索</h3>
				<div class="brands">
					<div class="accordion">
						<p class="acd_show">商标搜索</p>
					</div>
					<div class="accordion_content">
						<input class="brands_search" type="text" placeholder="搜索你想要的商标" />
						<div class="brands_result"></div>
						<div class="brands_example">
							<c:forEach items="${vendorsList}" var="brand">
								<div>
									<span><input name="brands" class="brands"
										onchange="getSelectedBrand()" type="checkbox"
										value="${brand.brandid}" /></span> <label for="brands01">${brand.brandname}</label>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="sort_price">
					<div class="accordion">
						<p class="acd_show">按价格</p>
					</div>
					<div class="accordion_content">
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

				</script>
				<div class="sort_rating">
					<div class="accordion">
						<p class="acd_show">按评价</p>
					</div>
					<div class="accordion_content">
						<div>
							<span><input name="sort_ratings" id="s_rating01" value="5"
								onchange="getSelectedRate()" type="radio" /></span> <label
								for="s_rating01"></label>
						</div>
						<div>
							<span><input name="sort_ratings" id="s_rating02" value="4"
								onchange="getSelectedRate()" type="radio" /></span> <label
								for="s_rating02">以上</label>
						</div>
						<div>
							<span><input name="sort_ratings" id="s_rating03" value="3"
								onchange="getSelectedRate()" type="radio" /></span> <label
								for="s_rating03">以上</label>
						</div>
						<div>
							<span><input name="sort_ratings" id="s_rating04" value="2"
								onchange="getSelectedRate()" type="radio" /></span> <label
								for="s_rating04">以上</label>
						</div>
						<div>
							<span><input name="sort_ratings" id="s_rating05" value="1"
								onchange="getSelectedRate()" type="radio" /></span> <label
								for="s_rating05">以上</label>
						</div>
					</div>
				</div>
			</div>

			<div class="lsb_ad top20">
				<img src="resources/images/left_ad.jpg">
			</div>
		</div>

		<div class="rsb">
			<div class="rsb_title">
				<span>商品总数（${page.totalcount}）</span>
			</div>
			<div class="product_box">
				<div class="page_infor">
					<div class="sort_by">
						<span> 排序： <select id="sort">
								<option value="sell">销量</option>
								<option value="priceH">价格从高到低</option>
								<option value="priceL">价格从低到高</option>
								<option value="laest">最新</option>
								<option value="name">名字</option>
								<option value="point">评分</option>
						</select>
						</span>
					</div>
					<div class="page" id="pageDiv"></div>
				</div>
				<div class="product_list_box">
					<ul class="product_list">
						<c:forEach items="${shopList}" var="shop">
							<li class="right_liner_border">
								<div class="bubble">
									<c:choose>
										<c:when test="${shop.promoteflag==1}">促销商品</c:when>
										<c:when test="${shop.promoteflag==0}">降价商品</c:when>
									</c:choose>
								</div>
								<div class="triangle"></div> <a class="r_pro"
								href="javascript:;">
									<div class="pro_imgbox">
										<img src="${shop.product.productimglist[0].path }" width="120"
											height="120"> <span class="detailed_btn"></span> <a
											href="eshop/shopProduct/productShopDetail.html?productId=${shop.productId}">
											<div class="detailed_message">查看详情</div>
										</a>
									</div>
							</a>
								<div class="pro_message top10">
									<a href="javascript:;"> ${shop.product.productName } </a>
									<div class="comment">
										<span class="stars"></span> <span class="comment_count"><a
											href="javascript:;">${shop.product.reviewcount }评论</a></span>
									</div>
									<c:choose>
										<c:when test="${shop.promoteflag==1}">
											<p class="new_price">促销价：￥${shop.promotePrice}</p>
											<p class="old_price">
												原价：￥${shop.originalPrice}（购买节省￥${shop.originalPrice-shop.promotePrice}）
											</p>
										</c:when>
										<c:when test="${shop.promoteflag!=1}">
											<p class="new_price">现价：￥${shop.originalPrice}</p>
											<p class="old_price">
												商品定价：￥${shop.product.shopPrice}（购买节省￥${shop.product.shopPrice-shop.originalPrice}）
											</p>
										</c:when>
									</c:choose>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="page_infor">
					<div class="sort_by">
						<span> 排序： <select id="sort">
								<option value="sell">销量</option>
								<option value="priceH">价格从高到低</option>
								<option value="priceL">价格从低到高</option>
								<option value="laest">最新</option>
								<option value="name">名字</option>
								<option value="point">评分</option>
						</select>
						</span>
					</div>
					<div class="page" id="pageDiv2"></div>
				</div>
			</div>
		</div>

		<div class="float-fix"></div>
	</div>

	<!-- footer -->
	<div class="footer">
		<jsp:include page="/WEB-INF/web/common/footer.jsp"></jsp:include>
	</div>

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
			type="hidden" id="gradestr" name="gradestr" value="${gradestr}" />

	</form>
	<script type="text/javascript">
			$(document).ready(function() {
					var prices='${pricestr}'.split(',');
					$(":checkbox[name='s_price']").each(function(){ 
					$(this).attr("checked", prices.indexOf($(this).val()) != -1) ;
		           });
					
					var prices='${brandstr}'.split(',');
					$(":checkbox[name='brands']").each(function(){ 
					$(this).attr("checked", prices.indexOf($(this).val()) != -1) ;
		           });
					$("input:radio[name='sort_ratings']").each(function(){ 
					$(this).attr("checked",'${gradestr}' ==$(this).val()) ;
		           });
					
					$('#sort').on('change', function() {
						 top.document.getElementsByName("orderstr")[0].value= $(this).val();
						document.getElementById("pageForm").submit();
					});
					
					$('select[id="sort"] option').each(function(){
						if (this.value=='${orderstr}'){
							$(this).attr("selected", 'selected');
						}
					});
			});
			
				function getAllPrices() {
				         var pricestr =[]
				        $(":checkbox[name='s_price']:checked").each(function(){  
				        	 pricestr.push($(this).val());
				           });
				        top.document.getElementsByName("searchstr")[0].value='${search}';
				       top.document.getElementsByName("pricestr")[0].value=pricestr.join();
				    	
				   /*  	top.document.getElementsByName("pageNostr")[0].value=0;
				    	top.document.getElementsByName("pageSizestr")[0].value=12;
				    	if('${search}'!=''){
				    	document.getElementById("pageForm").action="eshop/shopProduct/searchProductShop.action"
				    	} */
				    	document.getElementById("pageForm").submit();
				}
				
				function getSelectedBrand(){
					 var brandsstr =[]
				        $(":checkbox[name='brands']:checked").each(function(){  
				        	brandsstr.push($(this).val());
				           });
					  top.document.getElementsByName("searchstr")[0].value='${search}';
					 top.document.getElementsByName("brandstr")[0].value=brandsstr.join();
						document.getElementById("pageForm").submit();
				}
				
				function getSelectedRate(){
					  top.document.getElementsByName("searchstr")[0].value='${search}';
					 top.document.getElementsByName("gradestr")[0].value=$("input:radio[name='sort_ratings']:checked").val();
						document.getElementById("pageForm").submit();
				}
				
				function forOrder(){
					
				}
	</script>
	<script type="text/javascript">
		laypage({
		    cont: $('#pageDiv'), //容器。值支持id名、原生dom对象，jquery对象,
		    pages: ${page.totalpage}, //总页数
		    curr: ${page.pageno+1},
		    skip: false, //是否开启跳页
		    groups: 5, //连续显示分页数
		    first: '首页', //若不显示，设置false即可
		    last: '尾页', //若不显示，设置false即可
		    prev: '<', //若不显示，设置false即可
		    next: '>', //若不显示，设置false即可
		    jump: function(obj){
		    	if(document.readyState == "complete" || document.readyState == "COMPLETE"){
		    		if(obj.curr > ${page.totalpage}){//判断下一页是否有效
		    			return;
		    		}
		    		$("#pageNo").val(obj.curr);
			    	$("#pageForm").submit();
		    	}
		    }
		});
		
		laypage({
		    cont: $('#pageDiv2'), //容器。值支持id名、原生dom对象，jquery对象,
		    pages: ${page.totalpage}, //总页数
		    curr: ${page.pageno+1},
		    skip: false, //是否开启跳页
		    groups: 5, //连续显示分页数
		    first: '首页', //若不显示，设置false即可
		    last: '尾页', //若不显示，设置false即可
		    prev: '<', //若不显示，设置false即可
		    next: '>', //若不显示，设置false即可
		    jump: function(obj){
		    	if(document.readyState == "complete" || document.readyState == "COMPLETE"){
		    		if(obj.curr > ${page.totalpage}){//判断下一页是否有效
		    			return;
		    		}
		    		$("#pageNo").val(obj.curr);
			    	$("#pageForm").submit();
		    	}
		    }
		});
	 </script>
</body>
</html>