<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>" />
<title>订单列表</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/sticky-footer.css">
<link rel="stylesheet" href="resources/css/non-responsive.css">
<link rel="stylesheet" href="resources/css/bootstrap-dashboard.css">
<!-- <link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css" rel="stylesheet"> -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/star-rating.css">
<link rel="stylesheet" href="resources/css/global.css">
<link rel="stylesheet" href="resources/css/custom.css"/>
</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/web/common/top-nav.jsp"></jsp:include>

	<div class="container">
		<div class="row orders">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="/WEB-INF/web/common/leftside-nav.jsp"></jsp:include>
			</div>
			<div class="col-sm-9  col-md-10 main">
				<div>
					<div class="row">
						<div class="col-lg-6">
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">商城订单</a></li>
								<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">租赁订单</a></li>
								<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">回购订单</a></li>
								<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">whatever</a></li>
							</ul>

						</div>
					</div>

					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">评价打分</h4>
								</div>
								<div class="modal-body">
									<input type="hidden" id="mproductId">
									<input type="hidden" id="morderId">
									<dl>
										<dt>产品名称:</dt>
										<dd id="mproductName"></dd>
										<dt>打分:</dt>
										<dd>
											<input id="input-rating" value="0" type="number" class="rating" min=0 max=5 step=1 data-size="sm" data-stars="5">
										</dd>
										<dt>评价:</dt>
										<dd>
											<textarea id="productComments" class="form-control" rows="3"></textarea>
										</dd>
									</dl>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary" onclick="onSubmit()">提交</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<table class="table table-bordered">
								<tbody>
									<tr class="order_title">
										<th>商品</th>
										<th>单价（元）</th>
										<th>数量</th>
										<th>实付款（元）</th>
										<th>交易状态</th>
										<th>评价打分</th>
									</tr>
									<c:forEach items="${orderList}" var="order">
										<tr>
											<td colspan="6"><strong>${order.createTime}&nbsp;&nbsp;</strong>
												<div class="pull-right">
													<em>订单号：${order.orderId}</em> <span class="order_delete"><a>X</a></span>
												</div></td>
										</tr>
										<c:set var="flag" value="0" />
										<c:forEach items="${order.order_detail}" var="order_detail">
											<tr>
												<td><a href="eshop/shopProduct/productShopDetail.html?productId=${order_detail.product.productId}"> <img
														class="img-thumbnail img-responsive" src="${order_detail.product.productimglist[2].path}" /> <span>${order_detail.product.productName}</span>
												</a></td>
												<td>￥${order_detail.price}</td>
												<td>${order_detail.quantity}</td>
												<c:choose>
													<c:when test="${flag == 0}">
														<c:set var="flag" value="1" />
														<td rowspan="2">￥${order.totalamt}</td>
														<td rowspan="2"><a href="javascript:;">${order.status eq '02' || order.status eq '08'?'已完成订单':'未完成订单' }</a></td>
													</c:when>
												</c:choose>
												<td>
												<c:set var="reviewflag" value="0" /> <c:forEach items="${order.productReviews}" var="productReview">
														<c:choose><c:when test="${productReview.productId == order_detail.product.productId}">
															<c:set var="reviewflag" value="1" />
														</c:when>
														</c:choose>
													</c:forEach>
													<c:choose>
													<c:when test="${reviewflag==0}">
														<a href="javascript:;" data-toggle="modal" data-target="#myModal" data-product-id="${order_detail.product.productId}"
															data-product-name="${order_detail.product.productName}" data-order-id="${order_detail.orderId}"> 评价</a>
													</c:when>
													</c:choose></td>
											</tr>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
							<div id="pageDiv" class="pages">
								<a href="javascript">上一页</a> <a class="active" href="javascript">1</a> <a href="javascript">2</a> <a href="javascript">3</a> <a
									href="javascript">4</a> <a href="javascript">5</a> <a href="javascript">下一页</a>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="profile">...2</div>
						<div role="tabpanel" class="tab-pane" id="messages">...3</div>
						<div role="tabpanel" class="tab-pane" id="settings">...4</div>

					</div>

				</div>
			</div>

		</div>
	</div>

	<jsp:include page="/WEB-INF/web/common/footer-nav.jsp"></jsp:include>

<script type="text/javascript" src="resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/laypage.js"></script>
<script type="text/javascript" src="resources/js/star-rating.js"></script>
<script type="text/javascript" src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script type="text/javascript" src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

	<script type="text/javascript">
	
			function onSubmit(){
			var productId=$("#mproductId").val();
			var productComments=$("#productComments").val();
			var rate=$("#input-rating").val();
			var orderId=$("#morderId").val();
			
				alert(productComments);
			$.post("eshop/productreview/addProductReview.action", {
				'productId' : productId,
				'productComments' : productComments,
				'orderId' : orderId,
				'rate' : rate
			}, function() {
				$('#myModal').modal('hide');
				window.location.reload()
			});
			
				
			}
			
			$(document).ready(function() {
				$('#lorders').addClass('active');		
			});
			
			
			
			$('#myModal')
			.on(
					'show.bs.modal',
					function(e) {
						var productId = $(e.relatedTarget).data('product-id');
						var productName = $(e.relatedTarget).data('product-name');
						var orderId = $(e.relatedTarget).data('order-id');
						$('#mproductName').text(productName);
						$('#mproductId').val(productId);
						$('#morderId').val(orderId);
						
					});
			
			
			
			
			
	
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
	 	</script>
</body>
</html>