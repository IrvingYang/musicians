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
<title>登陆注册</title>
<link rel="stylesheet" href="resources/css/bootstrap.css" />
<link rel="stylesheet" href="resources/css/bootstrap-theme.css" />
<link rel="stylesheet" href="resources/css/sticky-footer.css" />
<link rel="stylesheet" href="resources/css/non-responsive.css" />
<link rel="stylesheet" href="resources/css/bootstrap-dashboard.css" />
<link rel="stylesheet" href="resources/css/bootstrap-carousel.css" />
<link rel="stylesheet" href="resources/css/custom.css" />
<link rel="stylesheet" href="resources/css/global.css" />
<link rel="stylesheet" href="resources/css/sign.css" />
</head>
<body>
	<!-- header -->
	<jsp:include page="/WEB-INF/web/common/top-nav.jsp"></jsp:include>
	<!-- content -->
	<div class="container main">
		<h1>用户登录&nbsp;/&nbsp;注册</h1>
		<div class="content">
			<div class="col-sm-6 left">
				<div class="sign">
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#sign"
							aria-controls="sign" role="tab" data-toggle="tab">登陆</a></li>
						<!-- <li role="presentation"><a href="#register"
							aria-controls="register" role="tab" data-toggle="tab">注册</a></li> -->
					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="sign">
							<div>
								<form id="login_form" action="user/user/login.shtml"
									method="POST">
									<input type="hidden" value="${returnURL}" name="returnURL" />
									<span class="error">${result}</span>
									<div class="form-group">
										<label for="exampleInputEmail1">邮箱</label> <input type="email"
											class="form-control" id="lemail" name="username"
											value="${userName}" placeholder="请输入用户名" required>
										<p class="help-block"></p>
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">密码</label> <input
											type="password" class="form-control" id="lpassword"
											name="password" placeholder="密码" required>
										<p class="help-block"></p>
									</div>

									<button type="submit" class="btn btn-primary">登录</button>
									<span class="error">${msg}</span>
								</form>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="register"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-6 right">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#register"
						aria-controls="register" role="tab" data-toggle="tab">注册</a></li>
				</ul>
				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="register">
						<form id="sign_form" action="user/user/register.shtml"
							method="POST">
							<input type="hidden" value="${returnURL}" name="returnURL" />
							<div class="form-group">
								<label>用户名：</label> <input class="form-control" type="email"
									id="userName" name="userName" />
							</div>

							<div class="form-group">
								<label>手机号码：</label> <input class="form-control " type="tel"
									id="telephone" name="telephone" minlength="11" maxlength="11" />
							</div>
					</div>

					<div class="form-group has-feedback">
						<label>验证码：</label>
						<div class="row">
							<div class="col-xs-6">
								<input class="form-control " type="number" id="code" name="code"
									required="required" />
							</div>
							<div class="col-xs-5">
								<button class="btn-sm btn-danger" id="send_pwd">获取动态密码</button>
							</div>
						</div>
					</div>


					<!-- 	<div class="common-reg">

									Auth code input
									<div class="form-group">
										<div class="col-md-offset-1 col-xs-5">
											<input id="login_auth" name="login_auth" type="text"
												placeholder="请输入验证码" class="form-control input-md"
												required="">
										</div>
										<div class="col-xs-6">
											<img alt="动态验证码" class="auth-img" src="" />
										</div>
									</div>
								</div> -->

					<div class="form-group">
						<label>密码：</label> <input type="password" class="form-control"
							id="spassword" name="password" required="required"
							data-msg="请设定密码" />
					</div>

					<div class="form-group">
						<label>确认密码：</label> <input type="password" class="form-control"
							id="confirm_password" name="confirm_password" />
					</div>

					<div class="form-group">
						<label>邮箱：</label> <input class="form-control" type="email"
							id="email" name="email" /> <span class="error"></span>
					</div>

					<input id="sign_in" type="submit" class="btn btn-primary"
						value="注册" />
					</form>

				</div>
			</div>

		</div>
	</div>
	</div>
	<script src="resources/js/jquery-1.11.3.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/html5shiv.min.js"></script>
	<script src="resources/js/respond.min.js"></script>
	<script>
		var fcode;
	
		$.validator.setDefaults({
			submitHandler : function(form) {

				$.ajax({
					data : {
						userName : $("#userName").val()
					}, // get the form data
					type : "POST", // GET or POST
					url : "user/user/exists.action", // the file to call
					success : function(response) { // on success..
						console.log(response)
						if (response == 'exists') {
							alert("用户名已经存在，请更换");
						} else {
							if(fcode==$('#code').val()){
								form.submit();
							}else {
								alert("验证码有误，请校验后输入");
							}
						}
					}
				});
			}
		});

		/* $('.common-reg').hide();
		$('#telephone').on('blur', function (e) {
			alert(111);
		    var v = $(this).val();
		    if (isNaN(v)) {
		        $('.common-reg').show(400);
		      //  $('#login_pwd').focus();
		    } else {
		        $('.common-reg').hide(400);
		    }
		}); */
		$('#send_pwd').on('click', function(e) {
			var tel = $('#telephone');
			tel.validate();
			if (!tel.valid()) {
				alert("请输入正确的手机号");
				return;
			}
			;
			if ($(this).attr('disabled'))
				return;
			
			fcode=randomNum(6);
			
			$.ajax({
				data : {
					telephone :$("#telephone").val(),
					code : fcode
				}, // get the form data
				type : "POST", // GET or POST
				url : "user/user/checkCode.action"
			});
			
			
			var $b = $(this), c = 10;
			$b.text('10秒后重发');
			$b.attr('disabled', true);
			var it = setInterval(function() {
				c -= 1;
				if (c <= 0) {

					$b.removeAttr('disabled');
					$b.text('获取动态密码');
					clearInterval(it);
					return;
				}

				$b.text(c + '秒后重发');

			}, 1000);
		});

		$().ready(function() {
			$("#sign_form").validate({
				rules : {
					userName : {
						required : true,
						email : true
					},
					spassword : {
						required : true,
						minlength : 6
					},
					confirm_password : {
						required : true,
						minlength : 6,
						equalTo : "#spassword"
					},
					email : {
						required : true,
						email : true
					},
					telephone : {
						required : true,
						minlength : 11,
						maxlength : 11
					}
				},
				messages : {
					userName : {
						required : "请输入用户名",
						email : "用户名必须是合法的email地址：例如：xiaoming@163.com"
					},
					spassword : {
						required : "必须填写密码",
						minlength : "密码最小长度为6"
					},
					confirm_password : {
						required : "必须填写确认密码",
						minlength : "密码最小长度为6",
						equalTo : "两次密码输入不一致"
					},
					email : "必须是合法的email地址：例如：xiaoming@163.com",
					telephone : {
						required : "必须填写手机号，例如：13880001234",
						minlength : "手机号码必须11位数字"
					}

				}
			});
		});
		
		function randomNum(n){ 
		    var t=''; 
		    for(var i=0;i<n;i++){ 
		        t+=Math.floor(Math.random()*10); 
		    } 
		    return t; 
		} 
	</script>
</body>
</html>