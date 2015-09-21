<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<jsp:include page="/head"></jsp:include>
<body>
<jsp:include page="/navBar"></jsp:include>

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
