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
<title>个人资料</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/bootstrap-table.css">
<link rel="stylesheet" href="resources/css/sticky-footer.css">
<link rel="stylesheet" href="resources/css/non-responsive.css">
<link rel="stylesheet" href="resources/css/bootstrap-dashboard.css">
<link rel="stylesheet" href="resources/css/global.css">
<link rel="stylesheet" href="resources/css/custom.css" />
<link rel="stylesheet" href="resources/css/personal.css" />
<script src="resources/js/jquery-1.11.1.min.js"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/web/common/top-nav.jsp"></jsp:include>
	<div class="container">
		<div class="row personal">
			<div class="col-sm-3 col-md-2 sidebar">
				<jsp:include page="/WEB-INF/web/common/leftside-nav.jsp"></jsp:include>
			</div>
			<div class="col-sm-9 col-md-10  main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">个人中心</h3>
					</div>
					<div class="panel-body">

						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">我的信息</h3>
							</div>
							<div class="panel-body">
								<!-- conent starts from here -->
								<dl class="dl-horizontal">
									<dt>用户名</dt>
									<dd>${sessionScope.user.user.userName}</dd>
									<dt>邮箱</dt>
									<dd>${sessionScope.user.user.email}</dd>
									<dt>密码</dt>
									<dd>
										********* <a data-toggle="modal" data-target="#passwordModal"><i
											class="glyphicon glyphicon-edit"></i></a>
									</dd>
								</dl>

								<jsp:include page="/WEB-INF/web/common/modal-password.jsp"></jsp:include>

							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">我的地址</h3>
							</div>
							<div class="panel-body">

								<span class="glyphicon glyphicon-plus-sign" data-toggle="modal"
									data-target="#myModal">添加新地址</span>
								<table class="address-list" id="table-pagination"
									data-toggle="table"
									data-url="user/userAddress/getUserAddressByUserId.action"
									data-height="400" data-page-size="5" data-page-list="[5, 10]"
									data-pagination="true" data-search="true">
									<thead>
										<tr>
											<th data-field="stateId" data-align="center"
												data-sortable="true" data-formatter="defaultRow">省/直辖市</th>
											<th data-field="cityId" data-align="center"
												data-sortable="false">市/直辖市区</th>
											<th data-field="districtId" data-align="center"
												data-sortable="false">区/县</th>
											<th data-field="street" data-sortable="false">街道地址</th>
											<th data-field="name" data-sortable="false"
												data-formatter="nameFormatter">收件人姓名</th>
											<th data-field="telephone" data-sortable="false">收件人电话</th>
											<th data-field="" data-sortable="false"
												data-clickToSelect="false" data-formatter="operateFormatter"
												data-events="operateEvents">操作</th>
										</tr>
									</thead>
								</table>


								<div>
									<div class="modal fade edit-address" id="myModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
													<h4 class="modal-title" id="myModalLabel">管理我的地址</h4>
												</div>
												<div class="modal-body">
													<div class="address">
														<div class="col-sm-4">
															<select id="seachprov" name="seachprov" class="form-control col-sm-4"
															onchange="changeComplexProvince(this.value, sub_array, 'seachcity', 'seachdistrict');"></select>
														</div>
														<div class="col-sm-4">
															<select id="seachcity" name="homecity" class="form-control"
															onchange="changeCity(this.value,'seachdistrict','seachdistrict');">
															</select>
														</div>
														<div class="col-sm-4">
															<span id="seachdistrict_div"> 
															<select	id="seachdistrict" name="seachdistrict" class="form-control">
															</select></span>
														</div>
													</div>
													<div>
														<input hidden="true" id="userAddressId"> 
														<input hidden="true" id="action" value="add">
													</div>
													<p class="first">
														邮编： <input type="text" class="form-control sg" id="postCode"
															data-validation-required-message="xxxxxxxxxxxxxxx"
															required>
													</p>
													<p>
														地址： <input type="text" class="form-control sg" id="streetAddress">
													</p>
													<p>
														收货人: <input type="text" class="form-control sg" id="name" value="${sessionScope.user.user.userName}">
													</p>
													<p>
														手机: <input type="text" class="form-control sg" id="telephone">
													</p>

												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Close</button>
													<button type="button" class="btn btn-primary"
														onclick="showAreaID()">Save changes</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/web/common/footer-nav.jsp"></jsp:include>


	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-table.js"></script>
	<script src="js/qushop/AreaData_min.js"></script>
	<script src="js/qushop/Area.js"></script>
	<script src="resources/js/jqBootstrapValidation.js"></script>
	<script src="resources/js/html5shiv.min.js"></script>
	<script src="resources/js/respond.min.js"></script>

	<script>
		$(function() {
			$("input,select,textarea").not("[type=submit]")
					.jqBootstrapValidation();
		});

		function defaultRow(value, row, index) {
			if (row.defaultflag == 1) {
				return [ '<span class="default-value">' + value
						+ '</span><span class="default">默认地址</span>' ];
			} else {
				return [ '<span>' + value + '</span>' ];
			}

		}

		function nameFormatter(value, row) {
			return value ? '<strong>' + value + '</strong> 收 ' : '';
		}

		function priceFormatter(value) {
			// 16777215 == ffffff in decimal
			var color = '#' + Math.floor(Math.random() * 6777215).toString(16);
			return '<div  style="color: ' + color + '">'
					+ '<i class="glyphicon glyphicon-usd"></i>'
					+ value.substring(1) + '</div>';
		}

		function operateFormatter(value, row, index) {
			return [
					'<a class="like" href="javascript:void(0)" title="Like">',
					'<i class="glyphicon glyphicon-heart"></i>',
					'</a>',
					'<a class="edit ml10" href="javascript:void(0)" title="Edit" id="editAdd" value="修改" data-toggle="modal" data-target="#myModal" data-area-id="'
							+ row.areaId
							+ '" '
							+ 'data-postcode="'
							+ row.postCode
							+ '" data-street-address="'
							+ row.street
							+ '" data-user-address-id="'
							+ row.userAddressId
							+ '" '
							+ 'data-name="'
							+ row.name
							+ '" data-telephone="'
							+ row.telephone
							+ '">',
					'<i class="glyphicon glyphicon-edit"></i>',
					'</a>',
					'<a class="remove ml10" href="javascript:void(0)" title="Remove">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}

		window.operateEvents = {
			'click .like' : function(e, value, row, index) {
				// alert('You click like icon, row: ' + JSON.stringify(row));
				setDefaultAddress(row.userAddressId + '');
			},
			'click .edit' : function(e, value, row, index) {
				// alert('You click edit icon, row: ' + JSON.stringify(row)); 

			},
			'click .remove' : function(e, value, row, index) {
				//alert('You click remove icon, row: ' + JSON.stringify(row));
				deleteAddress(row.userAddressId + '');
			}
		};
	</script>



	<script type="text/javascript">
		$(document).ready(function() {
			$('#lprofile').addClass('active');
		});

		function deleteAddress(userAddressId) {
			$.post("user/userAddress/deleteUserAddress.action", {
				'userAddressId' : userAddressId
			}, function() {
				window.location.reload();
			});
		}

		function setDefaultAddress(userAddressId) {
			$.post("user/userAddress/setDefaultUserAddress.do", {
				'userAddressId' : userAddressId
			}, function() {
				window.location.reload();
			});
		}

		/* $(function() {
			var parent = $('#myModal').parent();

			var areaId = $("input[id='areaId']", parent).val();

			initComplexArea('seachprov', 'seachcity', 'seachdistrict',
					area_array, sub_array, '44', '0', '0');
		}); */

		//triggered when modal is about to be shown
		$('#myModal')
				.on(
						'show.bs.modal',
						function(e) {

							//get data-id attribute of the clicked element
							var areaId = $(e.relatedTarget).data('area-id');
							if (!areaId) {
								initComplexArea('seachprov', 'seachcity',
										'seachdistrict', area_array, sub_array,
										'51', '0', '0');

							} else {
								areaId += "";
								var provice = areaId.substring(0, 2);
								var city = areaId.substring(2, 4);
								var area = areaId.substring(4, 6);

								initComplexArea('seachprov', 'seachcity',
										'seachdistrict', area_array, sub_array,
										provice, provice + city, areaId);

								if (areaId.length == 6) {
									changeCity(provice + city, 'seachdistrict',
											areaId);
								} else if (areaId.length == 4) {
									changeComplexProvinceForEdit(provice,
											sub_array, 'seachcity',
											'seachdistrict', areaId);
								}

								//setup postcode and street
								var postcode = $(e.relatedTarget).data(
										'postcode');
								$("input[id=postCode]").val(postcode);

								var sa = $(e.relatedTarget).data(
										'street-address');
								$("input[id=streetAddress]").val(sa);

								var userAddressId = $(e.relatedTarget).data(
										'user-address-id');
								$("input[id=action]").val("update");
								$("input[id=userAddressId]").val(userAddressId);

								var name = $(e.relatedTarget).data('name');
								$("input[id=name]").val(name);

								var telephone = $(e.relatedTarget).data(
										'telephone');
								$("input[id=telephone]").val(telephone);

							}
						});

		//得到地区码
		function getAreaID() {
			var area = 0;
			if ($("#seachdistrict").val() != "0") {
				area = $("#seachdistrict").val();
			} else if ($("#seachcity").val() != "0") {
				area = $("#seachcity").val();
			} else {
				area = $("#seachprov").val();
			}

			return area;
		}

		function showAreaID() {
			//地区码
			var areaId = getAreaID();
			//地区名
			var areaName = getAreaNamebyID(areaId);

			var streetAddress = $("#streetAddress").val();

			var postCode = $("#postCode").val();

			var name = $("#name").val();

			var telephone = $("#telephone").val();

			var address = areaName.split(" ");

			var stateId = address[0];
			var cityId = address[1]
			var districtId;

			if (areaId.length == 6) {
				districtId = address[2];
			}
			var action = $("input[id=action]").val();

			if (action == "add") {
				$.post("user/userAddress/addUserAddress.action", {
					'stateId' : stateId,
					'cityId' : cityId,
					'districtId' : districtId,
					'street' : streetAddress,
					'postCode' : postCode,
					'areaId' : areaId,
					'telephone' : telephone,
					'name' : name
				}, function() {
					$('#myModal').modal('hide');
					window.location.reload()
				});
			} else if (action == "update") {
				var userAddressId = $("input[id=userAddressId]").val();
				$.post("user/userAddress/updateUserAddress.do", {
					'stateId' : stateId,
					'cityId' : cityId,
					'districtId' : districtId,
					'street' : streetAddress,
					'postCode' : postCode,
					'areaId' : areaId,
					'userAddressId' : userAddressId,
					'telephone' : telephone,
					'name' : name
				}, function() {
					$('#myModal').modal('hide');
					window.location.reload()
				});
			}

		}

		//根据地区码查询地区名
		function getAreaNamebyID(areaID) {
			var areaName = "";
			if (areaID.length == 2) {
				areaName = area_array[areaID];
			} else if (areaID.length == 4) {
				var index1 = areaID.substring(0, 2);
				areaName = area_array[index1] + " " + sub_array[index1][areaID];
			} else if (areaID.length == 6) {
				var index1 = areaID.substring(0, 2);
				var index2 = areaID.substring(0, 4);
				areaName = area_array[index1] + " " + sub_array[index1][index2]
						+ " " + sub_arr[index2][areaID];
			}
			return areaName;
		}
	</script>
</body>
</html>