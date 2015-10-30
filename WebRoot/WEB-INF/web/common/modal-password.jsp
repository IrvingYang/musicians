<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="modal fade" id="passwordModal" tabindex="-1" role="dialog" aria-labelledby="myPasswordModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myPasswordModalLabel">管理我的登陆密码</h4>
			</div>
			<div class="modal-body">
				<form id="update_password_form" action="user/user/updateLoginUserPassword.action" method="POST">
					<div class="form-group">
						<label for="exampleInputPassword1">旧密码</label>
						<input type="password" class="form-control" id="oldpassword" name="oldpassword" placeholder="密码必须在6-16位之间">
						<span class="error"></span>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">新密码</label>
						<input type="password" class="form-control" id="spassword" name="spassword" placeholder="密码必须在6-16位之间">
						<span class="error"></span>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">确认密码</label>
						<input type="password" class="form-control" id="surepassword" placeholder="密码必须在6-16位之间">
						<span class="error"></span>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="pcommit" onclick="showAreaID()">保存</button>
			</div>
		</div>
	</div>
</div>



<script type="text/javascript">
	jQuery(document)
			.ready(
					function() {
						$("#pcommit")
								.click(
										function() {
											isRight = true;

											//判断密码长度
											if (!$('#spassword').val() == ''
													&& ($('#spassword').val().length > 16 || $(
															'#spassword').val().length < 6)) {
												$('#spassword').next('span')
														.text('密码必须在6-16位之间');
												isRight = false;
											}

											//判断密码长度
											if (!$('#oldpassword').val() == ''
													&& ($('#oldpassword').val().length > 16 || $(
															'#oldpassword')
															.val().length < 6)) {
												$('#oldpassword').next('span')
														.text('密码必须在6-16位之间');
												isRight = false;
											}

											//判断确认密码是否一致
											if (!$('#surepassword').val() == ''
													&& $('#surepassword').val() != $(
															'#spassword').val()) {
												$('#surepassword').next('span')
														.text('两次密码不一致');
												isRight = false;
											}

											if (isRight == false) {
												return false;
											} else {

												// AJAX Code To Submit Form.
												$
														.ajax({
															type : "POST",
															url : "user/user/updateLoginUserPassword.action",
															data : {
																'oldpassword' : $(
																		"#oldpassword")
																		.val(),
																'spassword' : $(
																		"#spassword")
																		.val()
															},
															cache : false,
															success : function(
																	data) {
																if (data == "needLogin") {
																	alert("您需要登录个人用户");
																	$("#login")
																			.click();
																}
																if (data == "success") {
																	alert("修改成功");
																	window.location
																			.reload(true);
																}

																if (data == "failed") {
																	alert("修改失败");
																	window.location
																			.reload(true);
																}

																if (data == "passworderror") {
																	alert("修改失败:旧密码不正确");
																	window.location
																			.reload(true);
																}

															},
															error : function(
																	data) {
																alert("请求超时");
															}
														});
												$(this).resetForm();
											}
											return false;
										});
					})
</script>