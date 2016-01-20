<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>购物车</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/sticky-footer.css">
<link rel="stylesheet" href="resources/css/non-responsive.css">
<link rel="stylesheet" href="resources/css/custom.css">
<link rel="stylesheet" href="resources/css/bootstrap-dashboard.css">
<link rel="stylesheet" href="resources/css/global.css">
<link rel="stylesheet" href="resources/css/cart.css" />
</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/web/common/top-nav.jsp"></jsp:include>

	<!-- content -->
	<div class="container">
		<div class="row cart">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="/WEB-INF/web/common/leftside-nav.jsp"></jsp:include>
			</div>
			<div class="col-sm-9  col-md-10  main">
				<div class="center-block">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">购买商品</h3>
						</div>
						<div class="panel-body">
							<table class="table table-bordered cart">
								<tr>
									<th colspan="2"><span>商品</span></th>
									<th><span>价格</span></th>
									<th><span>数量</span></th>
									<th><span>小计</span></th>
									<th><span></span></th>
								</tr>

								<c:forEach items="${sessionScope.shopping_cart.map}" var="cart"
									varStatus="status">
									<c:choose>
										<c:when test="${cart.value['cartType']==0 }">
											<tr>
												<td><img
													src="${cart.value['product'].productimglist[0].path}" /></td>
												<td><a class="text-primary">
														${cart.value['product'].productName } </a>
													<p class="text-info" id="productId">${cart.value['product'].productId}</p>
													<p class="text-success">有现货，随时可发出</p></td>
												<td>
													<div>
														<p id="aprice">${cart.value['currentPrice']}</p>
													</div>
												</td>
												<td>
													<div class="input-group" style="width: 120px">
														<span class="input-group-btn">
															<button type="button" class="btn btn-danger btn-number"
																data-type="minus" data-field="count${status.count}">
																<span class="glyphicon glyphicon-minus"></span>
															</button>
														</span> <input type="text" class="form-control input-number"
															value="${cart.value['productCount']}" min="1" max="100"
															name="count${status.count}" id="count"> <span
															class="input-group-btn">
															<button type="button" class="btn btn-success btn-number"
																data-type="plus" data-field="count${status.count}">
																<span class="glyphicon glyphicon-plus"></span>
															</button>
														</span>
													</div>
												</td>
												<td>
													<div>
														<p id="subtotal">￥
															${cart.value['currentPrice']*cart.value['productCount']}</p>
													</div>
												</td>
												<td>
													<div>
														<a href="javascript:;" data-toggle="modal"
															data-target="#myConfirmModal"
															data-product-id="${cart.value['product'].productId}" data-cart-type="0"
															class="btn btn-danger btn-xs"><span id="delete"
															class="glyphicon glyphicon-remove"></span></a>
													</div>
												</td>

											</tr>
										</c:when>
									</c:choose>
								</c:forEach>
							</table>
						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<p id="myPopover" class="help-block" data-container="body"
								data-toggle="popover" data-placement="right"
								data-content="请选择收货人地址">
							<h3 class="panel-title">租赁商品</h3>
							</p>

						</div>

						<div class="panel-body">
							<table id="leaseCart" class="table table-bordered rent">
								<tr>
									<th colspan="2"><span>商品</span></th>
									<th><span>押金</span></th>
									<th><span>数量</span></th>
									<th><span>租赁周期</span></th>
									<th><span>租金</span></th>
									<th><span>小计</span></th>
									<th><span></span></th>
								</tr>

								<c:forEach items="${sessionScope.shopping_cart.map}" var="cart">
									<c:choose>
										<c:when test="${cart.value['cartType']==2 }">
											<tr>
												<td><img
													src="${cart.value['product'].productimglist[0].path}" /></td>
												<td><a class="text-primary">
														${cart.value['product'].productName } </a>
													<p class="text-info" id="productId">${cart.value['product'].productId}</p>
													<p class="text-success">有现货，随时可发出</p></td>
												<td>
													<div>
														<p id="aprice"><fmt:formatNumber value="${cart.value['yajin']*cart.value['leaseConfig'].depositPercent}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> </p>
													</div>
												</td>
												<td>
													<div class="input-group" style="width: 120px">
														<span class="input-group-btn">
															<button type="button" disabled="disabled" class="btn btn-danger btn-number"
																data-type="minus" data-field="lcount${status.count}">
																<span class="glyphicon glyphicon-minus"></span>
															</button>
														</span> <input type="text" disabled="disabled" class="form-control input-number"
															value="${cart.value['productCount']}" min="1" max="100"
															name="lcount${status.count}" id="count"> <span
															class="input-group-btn">
															<button type="button" disabled="disabled" class="btn btn-success btn-number"
																data-type="plus" data-field="lcount${status.count}">
																<span class="glyphicon glyphicon-plus"></span>
															</button>
														</span>
													</div>
												</td>
												<td>
													<div>
														<p id="leaseCycle">${cart.value['leaseCycle']}</p>
													</div>
												</td>
												<td>
													<div>
														<p id="rent">
															<fmt:formatNumber value="${cart.value['leaseConfig'].money*0.01*cart.value['product'].shopPrice}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber>
														</p>
													</div>
												</td>

												<td>
													<div>
														<p id="subtotal">￥
															<fmt:formatNumber value="${cart.value['yajin']*cart.value['productCount']}" pattern="##.##" minFractionDigits="2" ></fmt:formatNumber> </p>
													</div>
												</td>
												<td>
													<div>
														<a href="javascript:;" data-toggle="modal"
															data-target="#myConfirmModal"
															data-product-id="${cart.value['product'].productId}" data-cart-type="2"
															class="btn btn-danger btn-xs"><span id="delete"
															class="glyphicon glyphicon-remove"></span></a>
													</div>
												</td>
											</tr>
										</c:when>
									</c:choose>
								</c:forEach>
							</table>


						</div>
					</div>


					<div class="modal fade" id="myConfirmModal" tabindex="-1"
						role="dialog" aria-labelledby="myDeleteModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-body">确定删除?</div>
								<div class="modal-footer">
									<a class="btn btn-danger" id="delete">删除</a>
									<button type="button" data-dismiss="modal" class="btn">取消</button>
								</div>
							</div>
						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<p id="myPopover" class="help-block" data-container="body"
								data-toggle="popover" data-placement="right"
								data-content="请选择收货人地址">
							<h3 class="panel-title">收货人信息</h3>
						</div>

						<div class="panel-body">
							<form>
								<c:forEach items="${userAddressesList}" var="add1">
									<div class="radio">
										<label><input type="radio" id="${add1.userAddressId}"
											name="radio-btn">${add1.stateId } ${add1.cityId }
											${add1.districtId} ${add1.street} <b>${add1.name}</b> 收
											${add1.telephone}</label>
									</div>
								</c:forEach>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer" style="height: 50px">
		<div class="container-fluid">
			<p class="text-right">
				<span> 总价： <em id="total" class="text-danger"></em></span> <small>共<em
					id="totalCount" class="text-danger"></em>件商品
				</small> <a class="btn btn-lg btn-danger" id="submitOrder"
					onclick="submit()" role="button">提交订单</a>
			</p>
		</div>
	</div>
	<script src="resources/js/jquery-1.11.1.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>

	<script src="resources/js/html5shiv.min.js"></script>
	<script src="resources/js/respond.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {

							$('#lcart').addClass('active');

							$("#myAlert").hide();

							$("input[id='count']")
									.change(
											function() {
												var base = $(this).parent()
														.parent().parent();
												var count = $(this).val();
												var price = $("#aprice", base)
														.text();
												var subtotal = price * count
												$("#subtotal", base).text(
														subtotal);
												var pid = $("#productId", base)
														.text().trim();
												//update nav bar
												$("input[name='aproductId']")
														.each(
																function() {
																	var parent1 = $(
																			this)
																			.parent()
																			.parent();
																	if ($(this)
																			.val()
																			.trim() == pid) {
																		$(
																				"#acount",
																				parent1)
																				.text(
																						count);
																		$(
																				"#asubtotal",
																				parent1)
																				.text(
																						subtotal);
																	}
																});

												var total = 0;
												var totalCount = 0;
												$("input[id='count']")
														.each(
																function() {

																	var base = $(
																			this)
																			.parent()
																			.parent()
																			.parent();
																	var price = $(
																			"#aprice",
																			base)
																			.text();
																	var rent = $(
																			"#rent",
																			base)
																			.text();
																	var count = parseInt($(
																			this)
																			.val());
																	var subtotal;
																	if (rent != '') {
																		subtotal = (parseFloat(price) + parseFloat(rent))
																				* count;
																	} else {
																		subtotal = price
																				* count
																	}
																	$(
																			"#subtotal",
																			base)
																			.text(
																					subtotal);
																	total += subtotal;
																	totalCount += count;
																	$
																			.post(
																					"eshop/product/operationCart.action",
																					{
																						'addcount' : totalCount,
																						'action' : 'update',
																						'productId' : pid,
																						'cartType' : '0',
																					},
																					function() {
																						window.location
																								.reload()
																					});

																	$("#total")
																			.text(
																					'￥'
																							+ total);
																	$(
																			"#totalCount")
																			.text(
																					totalCount);
																});
											});

							var total = new Number;
							var totalCount = new Number;

							$("input[id='count']")
									.each(
											function() {
												var base = $(this).parent()
														.parent().parent(); //loop-item
												var price = $("#aprice", base)
														.text();
												var count = parseInt($(this)
														.val());
												var rent = $("#rent", base)
														.text();
												var subtotal;
												if (rent != '') {
													subtotal = (parseFloat(price) + parseFloat(rent))
															* count;
												} else {
													subtotal = price * count
												}
												$("#subtotal", base).text(
														subtotal);
												total += subtotal;
												totalCount += count;
											});

							$("#total").text('￥' + total.toFixed(2));
							$("#totalCount").text(totalCount);
							
							
							$('#myConfirmModal').on(
									'show.bs.modal',
									function(e) {
										var productId = $(e.relatedTarget).data('product-id');
										var cartType = $(e.relatedTarget).data('cart-type');
										$(this).find('.btn-danger').on(
												'click',
												function(){
													$.post("eshop/product/operationCart.action", {
													'action' : 'remove',
													'cartType' : cartType,
													'productId' : productId,
													'addcount' : 0
												}, function() {
													window.location.reload()
												}
										);
									});
							
						});
						});

		function submit() {
			var selected = $("input:radio[name='radio-btn']:checked")
					.attr('id');
			//	alert (selected);
			if (selected) {
				//TBD orderType should be 4
				var url = "order/orderList/addOrder_list.do";
				var paymentway = "01";

				var rowCount = $('#leaseCart tr').length;

				var orderTypes = '<input type="text" name="orderTypes" value="'+1+'" hidden="true"/>'

				if (rowCount > 1) {
					orderTypes += '<input type="text" name="orderTypes" value="'+100+'" hidden="true"/>'
				}
				/*  $.post(url,{
					'orderTypes' : 1,
					'userAddressId': selected,
					'requireinvoice': 1,
					'invoicetype':1,
					'paymentway':paymentway,
					'invoicetitle':'xx',
					'invoicecontent':'xx',
					'remark':'dummy',
					'payofflineflag':0
				}, function(data) {
					console.log(data);
					//location.href ="order/orderList/orderList.do"
					//window.location.reload();
				});  */

				var form = $('<form action="' + url + '" method="post">'
						+ '<input type="text" name="userAddressId" value="'+selected+'" hidden="true"/>'
						+ '<input type="text" name="requireinvoice" value="'+1+'" hidden="true"/>'
						+ '<input type="text" name="invoicetype" value="'+1+'" hidden="true"/>'
						+ '<input type="text" name="paymentway" value="'+paymentway+'" hidden="true"/>'
						+ '<input type="text" name="invoicetitle" value="'+"xx"+'" hidden="true"/>'
						+ '<input type="text" name="invoicecontent" value="'+"xx"+'" hidden="true"/>'
						+ '<input type="text" name="remark" value="'+"dummy"+'" hidden="true"/>'
						+ orderTypes
						+ '<input type="text" name="payofflineflag" value="'+"0"+'" hidden="true"/>'
						+ '</form>');
				$('body').append(form);
				form.submit();
			} else {
				$("#myPopover").popover('show');
			}
		}
	</script>
	<script src="resources/js/numberIncrease.js"></script>


	<!-- footer
	<jsp:include page="/WEB-INF/web/common/footer.jsp"></jsp:include> -->
</body>
</html>