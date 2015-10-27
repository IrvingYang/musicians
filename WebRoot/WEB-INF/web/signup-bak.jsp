<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
    <head>
    	<base href="<%=basePath%>" />
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>登录及注册</title>

        <link rel="stylesheet" type="text/css" href="resources/css/public.css">
        <link rel="stylesheet" type="text/css" href="resources/css/style.css">
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/easySlider1.7.js"></script>
        <script src="resources/js/js.js"></script>
    </head>
    <body>
        <!-- header -->
        <div class="header">
            <!-- 导航 -->
            <div class="nav">
                <!-- 第一部分导航 -->
                <jsp:include page="/WEB-INF/web/common/top.jsp" />
                <div class="clear"></div>

                <!-- 第二部分导航 -->
                <jsp:include page="/WEB-INF/web/common/navigation.jsp" />
            </div>
        </div>

        <!-- content -->
        <div class="content">
            <div class="content_maxwidth">
                <div class="ls_title">
                    登录 / 注册我的账户
                </div>
                <div class="ls_chose">
                    <!-- <div class="log_or_sig">
                        <span><input id="chose_loge" type="radio" name="log_or_sig" checked="checked" /><label for="chose_loge">登录</label></span>
                        <i>|</i>
                        <span><input id="chose_sign" type="radio" name="log_or_sig" /><label for="chose_sign">注册</label></span>
                    </div> -->

                    <div class="login_in">
                        <h3>用户登录</h3>
                        <form id="login_form" action="user/user/login.shtml" method="POST">
                        	<input type="hidden" value="${returnURL}" name="returnURL"/>
                            <div>
                                <label>用户名：</label>
                                <input type="text" id="lemail" name="username"/>
                                <span class="error"></span>
                            </div>

                            <div>
                                <label>密&nbsp;&nbsp;&nbsp;码：</label>
                                <input type="password" id="lpassword" name="password"/>
                                <span class="error"></span>
                            </div>

                            <input id="login_in" type="submit" value="登录" />
                            <span class="error">${msg}</span>
                        </form>
                    </div>

                    <div class="sign_in">
                        <h3>用户注册</h3>
                        <form id="sign_form" action="user/user/register.shtml" method="POST">
                        	<input type="hidden" value="${returnURL}" name="returnURL"/>
                            <div>
                                <label>用户名：</label>
                                <input class="sign_must" type="text" id="suer_name" name="userName" placeholder="用户名必须在4-12个字节之间"/>
                                <span class="error"></span>
                            </div>
							
						    <div>
                                <label>手机号码：</label>
                                <input class="sign_must" type="text" id="telephone" name="telephone" placeholder="手机号码必须11位数字" />
                                <span class="error"></span>
                            </div>
				
                            <div>
                                <label>密码：</label>
                                <input class="sign_must" type="password" id="spassword" name="password" placeholder="密码必须在6-16位之间" />
                                <span class="error"></span>
                            </div>

                            <div>
                                <label>确认密码：</label>
                                <input class="sign_must" type="password" id="surepassword" />
                                <span class="error"></span>
                            </div>

                            <div>
                                <label>邮箱：</label>
                                <input class="sign_must" type="text" id="semail" name="email"/>
                                <span class="error"></span>
                            </div>

                            <input id="sign_in" type="submit" value="注册" />
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- footer -->
        <jsp:include page="/WEB-INF/web/common/footer.jsp" />
    </body>
</html>