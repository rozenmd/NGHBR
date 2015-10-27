<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<meta name="viewport" content="width=device-width, initial-scale=1.0">

<html>
<head>
	<%-- Stylesheets --%>
	<link href="<c:url value="/static/css/bootstrap.min.css" />"
		  rel="stylesheet">
	<link href="<c:url value="/static/css/font-awesome.min.css" />"
		  rel="stylesheet">
	<link href="<c:url value="/static/css/bootstrap-social.css" />"
		  rel="stylesheet">
	<link href="<c:url value="/static/css/landing.css" />" rel="stylesheet">
	<link href="<c:url value="/static/css/style.css" />" rel="stylesheet">

	<%--//mapping css--%>
	<link href="<c:url value="/static/css/leaflet.min.css" />" rel="stylesheet">



	<%-- JavaScripts --%>
	<script src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/jquery-1.11.2.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/bootstrap.min.js" />"></script>

	<title>NGHBR</title>
</head>

<body>
<jsp:include page="/navBar"></jsp:include>

<div class="container">
	<h1 class="text-center">${user.firstName} ${user.lastName} - Administration</h1>
	<div class="col-md-6" style="padding-top: 50px;">

		<table class="table table-responsive">
			<tr>
				<a href="totalPoints" class="btn btn-default">
					<i class="fa fa-pencil"></i> Update suburb total points
				</a>
			</tr>
			<c:choose>
			<c:when test="${pageContext.request.userPrincipal.authenticated}">

		</table>

		</c:when>
		<c:otherwise>
			Please <a href="<c:url value="/login" />">login</a> to continue
		</c:otherwise>
		</c:choose>

	</div>
	<div class="col-md-6" style="margin-top: 75px;">
		<div id="map" style="position: relative;
		width: 100%;
		height: 550px;"></div>
	</div>




</div>
</body>
<%--JS for data display on maps:--%>
<script src="<c:url value="/static/js/vendor/d3.v3.min.js" />"></script>
<script src="<c:url value="/static/js/vendor/topojson.v1.min.js" />"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.3/leaflet.js"></script>
<script src='//api.tiles.mapbox.com/mapbox.js/plugins/leaflet-omnivore/v0.2.0/leaflet-omnivore.min.js'></script>


</html>


