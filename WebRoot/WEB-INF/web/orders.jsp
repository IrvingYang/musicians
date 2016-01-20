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
<link rel="stylesheet" href="resources/css/bootstrap-table.css">
<link rel="stylesheet" href="resources/css/sticky-footer.css">
<link rel="stylesheet" href="resources/css/non-responsive.css">
<link rel="stylesheet" href="resources/css/bootstrap-dashboard.css">
<!-- <link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css" rel="stylesheet"> -->
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/star-rating.css">
<link rel="stylesheet" href="resources/css/global.css">
<link rel="stylesheet" href="resources/css/custom.css" />
<link rel="stylesheet" href="resources/css/orders.css" />
<link rel="stylesheet" href="resources/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="resources/js/jquery.dataTables.min.js"></script>
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
								<li role="presentation" class="active"><a href="#home"
									aria-controls="home" role="tab" data-toggle="tab">商品订单</a></li>
								<li role="presentation"><a href="#profile"
									aria-controls="profile" role="tab" data-toggle="tab">租赁订单</a></li>
								<li role="presentation"><a href="#messages"
									aria-controls="messages" role="tab" data-toggle="tab">回购订单</a></li>
							</ul>
						</div>
					</div>

					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">评价打分</h4>
								</div>
								<div class="modal-body">
									<input type="hidden" id="mproductId"> <input
										type="hidden" id="morderId">
									<dl>
										<dt>产品名称:</dt>
										<dd id="mproductName"></dd>
										<dt>打分:</dt>
										<dd>
											<input id="input-rating" value="0" type="number"
												class="rating" min=0 max=5 step=1 data-size="sm"
												data-stars="5">
										</dd>
										<dt>评价:</dt>
										<dd>
											<textarea id="productComments" class="form-control" rows="3"></textarea>
										</dd>
									</dl>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary"
										onclick="onSubmit()">提交</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<table id="table2" class="table cell-border rent">
								<thead>
									<tr>
										<th></th>
										<th>商品</th>
										<th>原价（元）</th>
										<th>数量</th>
										<th>实付款</th>
										<th>付款</th>
										<th>交易状态</th>
										<th>评价打分</th>
									</tr>
								</thead>

							</table>
						</div>
						<div role="tabpanel" class="tab-pane" id="profile">
							<table id="leaseTable" class="table cell-border order">
								<thead>
									<tr>
										<th></th>
										<th>商品</th>
										<th>押金（元）</th>
										<th>租期（天）</th>
										<th>总金额（押金+租金）</th>
										<th>付款</th>
										<th>交易状态</th>
										<th>评价打分</th>
									</tr>
								</thead>
							</table>
						</div>
						<div role="tabpanel" class="tab-pane" id="messages"></div>
						<div class="modal fade" id="myConfirmModal" tabindex="-1"
							role="dialog" aria-labelledby="myDeleteModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-body">确定删除?</div>
									<div class="modal-footer">
										<a 
											class="btn btn-danger" id="delete">删除</a>
										<button type="button" data-dismiss="modal" class="btn">取消</button>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

		</div>

	</div>

	<script type="text/javascript" src="resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-table.js"></script>
	<script type="text/javascript" src="resources/js/laypage.js"></script>
	<script type="text/javascript" src="resources/js/star-rating.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
	<script src="resources/js/html5shiv.min.js"></script>
	<script src="resources/js/respond.min.js"></script>

	<script type="text/javascript">
		/* function onSubmit() {
			var productId = $("#mproductId").val();
			var productComments = $("#productComments").val();
			var rate = $("#input-rating").val();
			var orderId = $("#morderId").val();

			//alert(productComments);
			$.post("eshop/productreview/addProductReview.action", {
				'productId' : productId,
				'productComments' : productComments,
				'orderId' : orderId,
				'rate' : rate
			}, function() {
				$('#myModal').modal('hide');
				//	window.location.reload()
			});
		} */

		$(document)
				.ready(
						function() {
							$('#lorders').addClass('active');

							var table = $('#table2')
									.DataTable(
											{
												"ajax" : "order/orderList/shopOrderList.do",
												"columns" : [
														{
															"data" : "order_list"
														},
														{
															"data" : "product"
														},
														{
															"data" : "product.shopPrice"
														},
														{
															"data" : "quantity"
														},
														{
															"data" : "totalamt"
														},
														{
															"data" : "order_list"
														},
														{
															"data" : "deliverStatus"
														}, {
															"data" : "product"
														} ],
												"columnDefs" : [
														{
															"visible" : false,
															"targets" : 0
														},
														{
															"targets" : 1,
															"render" : function(
																	data, type,
																	full, meta) {
																return '<a><img src="'+data.productimglist[2].path+'"><div>'
																		+ data.productName
																		+ '</div></a>';
															}
														},
														{
															"targets" : 5,
															"render" : function(
																	data, type,
																	full, meta) {
																if (data.status != '03') {
																	var text = '<form action="alipay/pay.action" method=POST><input type="hidden" id="orderId" name="orderId" value='+full.orderId+' /><input type="hidden" id="orderType" name="orderType" value='+full.orderType+' /><input type="submit" value="付款"></form>'
																	return text;
																} else {
																	return '已付款'
																}
															}
														},
														{
															"targets" : 6,
															"render" : function(
																	data, type,
																	full, meta) {
																if (data == '02'
																		|| data == '08') {
																	return '已完成订单'
																} else {
																	return '未完成订单'
																}
																;
															}
														},
														{
															"targets" : 7,
															"render" : function(
																	data, type,
																	full, meta) {
																if (full.productReview == null) {
																	return '<a href="javascript:;" data-toggle="modal"'+'data-target="#myModal"'+ 'data-product-id="'+data.productId+'" '+'data-product-name="'+data.productName+'" '+'data-order-id="'+full.orderId+'"'+' > 评价</a>'
																} else {
																	return '已评价'
																}
															}
														} ],
												"order" : [ [ 0, 'asc' ] ],
												"displayLength" : 10,
												"drawCallback" : function(
														settings) {
													var api = this.api();
													var rows = api.rows({
														page : 'current'
													}).nodes();
													var last = null;

													api
															.column(
																	0,
																	{
																		page : 'current'
																	})
															.data()
															.each(
																	function(
																			group,
																			i) {
																		if (last !== group.orderId) {
																			$(
																					rows)
																					.eq(
																							i)
																					.before(
																							'<tr class="group"><td colspan="7" class="create"><span class="left"><span>订单号:</span>'
																									+ group.orderId
																									+ '</span><span class="right"><span> 创建时间：</span>'
																									+ $.datepicker
																											.formatDate(
																													'yy-mm-dd',
																													new Date(
																															group.createTime))
																									+ ' <a  href="javascript:;" data-toggle="modal"'+'data-target="#myConfirmModal"'+ 'data-order-id="'+group.orderId+'" class="btn btn-danger btn-xs"><span id="delete" class="glyphicon glyphicon-remove"/></a></span></td></tr>');
																			last = group.orderId;
																			
																		}else{
																			var cell4 = $('td',$(rows).eq(i)).eq(4);
																			removeTopBorder(cell4);
																			var cell5=$('td',$(rows).eq(i)).eq(5);
																			removeTopBorder(cell5);
																			var cell6=$('td',$(rows).eq(i)).eq(6);
																			removeTopBorder(cell6);
																		}
																	});
												}
											});

							// Order by the grouping
							$('#table2 tbody').on(
									'click',
									'tr.group',
									function() {
										var currentOrder = table.order()[0];
										if (currentOrder[0] === 2
												&& currentOrder[1] === 'asc') {
											table.order([ 0, 'desc' ]).draw();
										} else {
											table.order([ 0, 'asc' ]).draw();
										}
									});

							var table = $('#leaseTable')
									.DataTable(
											{
												"ajax" : "order/orderList/leaseOrderList.do",
												"columns" : [
														{
															"data" : "order_detail.order_list"
														},
														{
															"data" : "order_detail.product"
														},
														{
															"data" : "order_detail.price"
														},
														{
															"data" : "leaseCycle"
														},
														{
															"data" : "order_detail.totalamt"
														},
														{
															"data" : "order_detail.order_list"
														},
														{
															"data" : "order_detail.deliverStatus"
														},
														{
															"data" : "order_detail.product"
														}

												],
												"columnDefs" : [
														{
															"visible" : false,
															"targets" : 0
														},
														{
															"targets" : 1,
															"render" : function(
																	data, type,
																	full, meta) {
																return '<a><img src="'+data.productimglist[2].path+'"><div>'
																		+ data.productName
																		+ '</div></a>';
															}
														},
														{
															"targets" : 5,
															"render" : function(
																	data, type,
																	full, meta) {
																if (data.status != '03') {
																	var text = '<form action="alipay/pay.action" method=POST><input type="hidden" id="orderId" name="orderId" value='+data.orderId+' /><input type="hidden" id="orderType" name="orderType" value='+data.orderType+' /><input type="submit" value="付款"></form>'
																	return text;
																} else {
																	return '已付款'
																}
															}
														},
														{
															"targets" : 6,
															"render" : function(
																	data, type,
																	full, meta) {
																if (data == '02'
																		|| data == '08') {
																	return '已完成订单'
																} else {
																	return '未完成订单'
																}
																;
															}
														},
														{
															"targets" : 7,
															"render" : function(
																	data, type,
																	full, meta) {

																if (full.productReview == null) {
																	return '<a href="javascript:;" data-toggle="modal"'+'data-target="#myModal"'+ 'data-product-id="'+data.productId+'" '+'data-product-name="'+data.productName+'" '+'data-order-id="'+full.orderId+'"'+' > 评价</a>'
																} else {
																	return '已评价'
																}
															}
														} ],
												"order" : [ [ 0, 'asc' ] ],
												"displayLength" : 25,
												"drawCallback" : function(
														settings) {
													var api = this.api();
													var rows = api.rows({
														page : 'current'
													}).nodes();
													var last = null;

													api
															.column(
																	0,
																	{
																		page : 'current'
																	})
															.data()
															.each(
																	function(
																			group,
																			i) {
																		if (last !== group.orderId) {
																			$(
																					rows)
																					.eq(
																							i)
																					.before(
																							'<tr class="group"><td colspan="7" class="create"><span class="left"><span>订单号:</span>'
																									+ group.orderId
																									+ '</span><span class="right"><span> 创建时间：</span>'
																									+ $.datepicker
																											.formatDate(
																													'yy-mm-dd',
																													new Date(
																															group.createTime))
																									+ '<a  href="javascript:;" data-toggle="modal"'+'data-target="#myConfirmModal"'+ 'data-order-id="'+group.orderId+'" class="btn btn-danger btn-xs"><span id="delete" class="glyphicon glyphicon-remove"/></a></span></td></tr>');

																			last = group.orderId;
																		}
																	});
												}
											});
						});

		$('#myConfirmModal').on(
				'show.bs.modal',
				function(e) {
					var orderId = $(e.relatedTarget).data('order-id');
					$(this).find('.btn-danger').attr(
							'href',
							'order/orderList/deleteOrder.action?orderId='
											+ orderId);
				});

		$('#myModal').on('show.bs.modal', function(e) {
			var productId = $(e.relatedTarget).data('product-id');
			var productName = $(e.relatedTarget).data('product-name');
			var orderId = $(e.relatedTarget).data('order-id');
			$('#mproductName').text(productName);
			$('#mproductId').val(productId);
			$('#morderId').val(orderId);

		});
	</script>
	<script>
	
		function removeTopBorder(cell){
			cell.css('border-top','0');
			cell.html('');
		}
	
		function detailFormatter(index, row) {
			var html = [];

			var columns = [];
			$.each(row, function(key, value) {
				// html.push('<p><b>' + key + ':</b> ' + value + '</p>');
				/*  row = {};
				  for (j = 0; j < cells; j++) {
				      row['field' + j] = 'Row-' + i + '-' + j;
				  } */

				if (key == "order_detail") {
					html.push("<table><tbody>");
					$.each(value, function(n, value1) { //array
						//alert(n + ' n= ' + value1.orderId);
						//	row = {};
						html.push("<tr>");
						$.each(value1, function(key2, value2) {
							// alert(key2+"   "+value2);
							//alert(value1.product.productId);
							if (n == 0) {
								columns.push({
									field : key2,
									title : key2,
									sortable : true
								});
							}

							if (key2 == 'product') {
								html.push("<td>"
										+ value2.productimglist[2].path
										+ "</td>");
							} else {
								html.push("<td>" + value2 + "</td>");
							}

							//data.push(key2) = + ' key2= ' + value2);
						});
						html.push("</tr>");
						//  orderDetalData.push(value1);

						//orderDetalData= value1;
						// row['orderId'] =  data.push(row);
						/* alert(value[0].orderId);
						if(value.length>1){
							alert('he'+value[1].orderId)
						} */

					});
					html.push("</tbody></table>");
				}

			});
			return html.join('');
		}
	</script>

	<jsp:include page="/WEB-INF/web/common/footer-nav.jsp"></jsp:include>
</body>
</html>