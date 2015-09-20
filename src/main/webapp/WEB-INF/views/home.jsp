<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<%-- Stylesheets --%>
	<link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/static/css/font-awesome.min.css" />" rel="stylesheet">
	<link href="<c:url value="/static/css/bootstrap-social.css" />" rel="stylesheet">
	<link href="<c:url value="/static/css/landing.css" />" rel="stylesheet">
	<link href="<c:url value="/static/css/style.css" />" rel="stylesheet">

	<%-- JavaScripts --%>
	<script src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/jquery-1.11.2.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/bootstrap.min.js" />"></script>

	<title>NGHBR</title>
</head>

<body>

<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">NGHBR</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${pageContext.request.userPrincipal.authenticated}"><li><a href="<c:url value="/logout" />">Logout</a></li></c:when>
					<c:otherwise><li><a href="<c:url value="/login" />">Login</a></li></c:otherwise>
				</c:choose>
			</ul>

		</div><!--/.navbar-collapse -->
	</div>
</nav>

<%-- Cool video background thing here! --%>
<div class="homepage-hero-module">
	<div class="video-container">
		<div class="title-container">
			<div class="headline">
				<h1 class="robot-land-title">NGHBR</h1>
				<h3 class="text-white text-thin">Bringing the community together.</h3>
				<a class="btn btn-lg btn-social btn-facebook">
					<i class="fa fa-facebook"></i> Sign in with Facebook
				</a>

			</div>

			<div class="text-center" id="loadBannerVideoSpinner" style="padding: 10px; border-radius: 4px; display: none; margin-top: 20px; background-color: rgba(0, 0, 0, 0.498039);">
				<h5 class="text-thin text-primary">Loading NGHBR... <i class="fa fa-circle-o-notch fa-spin"></i></h5>
			</div>
		</div>
		<div class="filter"></div>
		<video autoplay loop class="fillWidth">
			<source src="<c:url value="/static/videos/The-Boulevard.mp4" />" type="video/mp4" />Your browser does not support the video tag. I suggest you upgrade your browser.
			<source src="<c:url value="/static/videos/The-Boulevard.webm" />" type="video/webm" />Your browser does not support the video tag. I suggest you upgrade your browser.
		</video>
		<div class="poster hidden">
			<img src="<c:url value="/static/images/The-Boulevard.jpg" />" alt="">
		</div>
		<div style="position: absolute; bottom: 10px; right: 50px;">
			<p class="lead text-center text-white" style="margin-bottom: 10px; font-size: 12px;">NGBHR was made with <i class="fa fa-heart heart-color"></i> by kmji</p>
		</div>
	</div>
</div>

<%-- JavaScript to make the video background responsive --%>
<script src="<c:url value="/static/js/landing.js" />"></script>
</body>
</html>
