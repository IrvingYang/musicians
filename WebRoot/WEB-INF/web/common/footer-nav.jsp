<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<footer class="footer-extend">
	<div class="container">
		<div class="first">
			<div id="myFooterCarousel" class="carousel slide">
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner  slider">
					<div class="item active">
						<a href="#"><img src="resources/images/img1.jpg" alt="First slide"></a>
						<a href="#">测试名字</a>
					</div>
					<div class="item">
						<a href="#"><img src="resources/images/img2.jpg" alt="Second slide"></a>
						<a href="#">测试名字</a>
					</div>
					<div class="item">
						<a href="#"><img src="resources/images/img3.jpg" alt="Third slide"></a>
						<a href="#">测试名字</a>
					</div>
				</div>
				<!-- 轮播（Carousel）导航 -->
				<a class="carousel-control left" href="#myFooterCarousel" data-slide="prev"></a> <a class="carousel-control right" href="#myFooterCarousel" data-slide="next"></a>
			</div>

		</div>
		<div class="second text-center">
			<dl>
				<dt>服务</dt>
				<dd><a href=javascript:;>项目服务1</a></dd>
				<dd><a href=javascript:;>项目服务2</a></dd>
				<dd><a href=javascript:;>项目服务3</a></dd>
			</dl>
		</div>
		<div class="third text-center">
			<dl>
				<dt>支持</dt>
				<dd><a href=javascript:;>项目支持</a></dd>
				<dd><a href=javascript:;>项目支持</a></dd>
				<dd><a href=javascript:;>项目支持</a></dd>
			</dl>
		</div>
		<div class="forth text-center">
			<dl>
				<dt>关于我们</dt>
				<dd><a href=javascript:;>关于我们</a></dd>
				<dd><a href=javascript:;>关于我们</a></dd>
				<dd><a href=javascript:;>关于我们</a></dd>
			</dl>
		</div>
		<div class="fifth">
			<div class="help-center"></div>
		</div>
	</div>
</footer>
<script>
	$(document).ready(function(){
		$('.carousel').carousel();	
	});
</script>