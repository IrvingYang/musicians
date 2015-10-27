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
        
        <style type="text/css" rel="stylesheet">
            *{
                padding:0;
                margin:0;
            }

            html, body{
                width:100%;
            }

            .buy_back_finish{
                width:1024px;
                margin:0 auto;
                text-align:center;
            }

            .buy_back_finish p{
                font-size:36px;
            }

            .buy_back_finish input{
                width:300px;
                height:50px;
                margin:40px 0;
                font-size:20px;
                color:#fff;
                cursor:pointer;
                border:none;
                border-radius:3px;
                background:#a11c44;
            }
        </style>
    </head>
    <body>
        <div id="finish" class="buy_back_finish">
            <p id="text">回购操作申请完成，5秒后将跳转回首页</p>
            <input id="back" type="button" value="立即跳转" />
        </div>

        <script>
            window.onload = function() {
                var pos = document.getElementById('finish');
                var txt = document.getElementById('text');
                var back = document.getElementById('back');
                var winH = document.documentElement.clientHeight;
                pos.style.marginTop = winH/2 - pos.offsetHeight/2 + 'px';

                var time = 5;
                var clock = setInterval(function() {
                    time--;
                    txt.innerHTML = '回购完成，' + time +'秒后将跳转回首页';
                    if(time==0){
                        clearInterval(clock);
                        window.location = '/';
                    }
                }, 1000);

                back.onclick = function() {
                    window.location = '/';
                };
            }
        </script>
    </body>
</html>