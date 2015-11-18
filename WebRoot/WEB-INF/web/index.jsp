<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<title>乐星网</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/sticky-footer.css">
<link rel="stylesheet" href="resources/css/non-responsive.css">
<link rel="stylesheet" href="resources/css/bootstrap-dashboard.css">
<link rel="stylesheet" href="resources/css/bootstrap-carousel.css">
<link rel="stylesheet" href="resources/css/global.css" />
<link rel="stylesheet" href="resources/css/custom.css" />
<link rel="stylesheet" href="resources/css/index.css" />
</head>
<body class="join-index">
	<!-- header -->
	<jsp:include page="/WEB-INF/web/common/top-nav.jsp"></jsp:include>
	<!-- content -->
	<div class="container main">
		<div class="row">
			<div class="col-sm-8  col-md-9">
				<div class="panel panel-primary">
					<div class="panel-body">
						<img class="img-responsive" src="resources/images/ad1.jpg">
					</div>
				</div>
				<div id="myCarousel" class="carousel slide" data-ride="carousel"
					style="height: 500px">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="item active" style="height: 500px">
							<img class="first-slide"
								src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
								alt="First slide">
							<div class="container">
								<div class="carousel-caption">
									<h1>欢迎来到乐星网</h1>
									<p>
										Note: If you're viewing this page via a
										<code>file://</code>
										URL, the "next" and "previous" Glyphicon buttons on the left
										and right might not load/display properly due to web browser
										security rules.
									</p>
									<p>
										<a class="btn btn-lg btn-primary" href="#" role="button">Sign
											up today</a>
									</p>
								</div>
							</div>
						</div>
						<div class="item" style="height: 500px">
							<img class="second-slide"
								src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
								alt="Second slide">
							<div class="container">
								<div class="carousel-caption">
									<h1>这里是乐星网的主页</h1>
									<p>Cras justo odio, dapibus ac facilisis in, egestas eget
										quam. Donec id elit non mi porta gravida at eget metus. Nullam
										id dolor id nibh ultricies vehicula ut id elit.</p>
									<p>
										<a class="btn btn-lg btn-primary" href="#" role="button">Learn
											more</a>
									</p>
								</div>
							</div>
						</div>
						<div class="item" style="height: 500px">
							<img class="third-slide"
								src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
								alt="Third slide">
							<div class="container">
								<div class="carousel-caption">
									<h1>这里是乐星网的主页</h1>
									<p>Cras justo odio, dapibus ac facilisis in, egestas eget
										quam. Donec id elit non mi porta gravida at eget metus. Nullam
										id dolor id nibh ultricies vehicula ut id elit.</p>
									<p>
										<a class="btn btn-lg btn-primary" href="#" role="button">Browse
											gallery</a>
									</p>
								</div>
							</div>
						</div>
					</div>
					<a class="left carousel-control" href="#myCarousel" role="button"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel" role="button"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
				<!-- /.carousel -->

			</div>

			<div class="col-sm-4 col-md-3">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">广告一</h3>
					</div>
					<div class="panel-body">
						<div>
							<p>注册成为会员，首先需要验证您的E-mail地址,请在输入框中输入您的E-mail地址进行验证</p>
							<form>
								<input class="form-control" id="check_email"
									placeholder="请输入您的E-mail地址" /> <span class="error_message"></span>
								<a class="btn btn-primary join" id="sub_email" role="button">现在加入</a>
							</form>
						</div>

					</div>

				</div>

				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">广告二</h3>
					</div>
					<div class="panel-body">
						<div class="thumbnail_container">
							<div class="thumbnail">
								<img src="resources/images/spotlights01.jpg">
							</div>
							<div class="thumbnail">
								<img src="resources/images/spotlights02.jpg">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12 col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">广告三</h3>
					</div>
					<div class="panel-body">
						<p>Welcome to Musician’s Friend — your musical instruments
							destination for gear and exclusive content to help you get the
							sound you’re after. We offer free shipping (on almost everything)
							from the biggest online selection of musical instruments and
							equipment including guitars, basses, amps & effects, keyboards &
							MIDI, drums & percussion, live sound, DJ, microphones, recording,
							band & orchestra, Folk Instruments, accessories, and more. You’ll
							even find an unmatched selection of Open Box instruments & gear
							that ships worldwide. To help you choose the instrument that’s
							right for you, many of our products feature unbiased reviews from
							Musician’s Friend customers who are happy (and sometimes not so
							happy) with the gear you are interested in. In addition, we have
							an experienced customer service team standing by to help you with
							your choice; you can call them right now at 877-880-5907. We also
							have online tools to help you find the perfect match for your
							existing gear. Just try our guitar case finder and cable finder.
							Since we’re the largest online guitar store anywhere in the
							world, we have all of your favorites from Fender and Gibson,
							including Stratocasters, Telecasters, and Les Paul guitars.
							Additionally, if you are looking to add to your collection we
							take great pride in our hand-curated Private Reserve Guitars. If
							you are looking for the finest guitars, basses and amps anywhere
							in the world, Musician’s Friend has you covered. The PRG
							collection consists of some truly awesome guitars, including
							premium, limited edition and collectible models from the world’s
							top manufacturers. If you are looking for a great gift for the
							musician in your life, we have an unbeatable assortment of iOS
							compatible gear, ukuleles, harmonicas, books & sheet music,
							apparel & collectibles and even instruments for children. Not
							sure which product is right for you? Let our team help you find
							exactly what you’re looking for. Chat online or call us at
							877-880-5907.</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	
	<script src="resources/js/jquery-1.11.3.min.js"></script>
	<script src="resources/js/bootstrap.js"></script>
	<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
	<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
	<!-- <script src="resources/js/ie/ie-emulation-modes-warning.js"></script> -->

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>-->
	<script src="resources/js/html5shiv.min.js"></script>
	<script src="resources/js/respond.min.js"></script>
	<jsp:include page="/WEB-INF/web/common/footer-nav.jsp"></jsp:include>
</body>
</html>