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
	<link href="<c:url value="/static/css/select2.css" />" rel="stylesheet">
	<link href="<c:url value="/static/css/select2-bootstrap.css" />" rel="stylesheet">
	<link href="<c:url value="/static/css/style.css" />" rel="stylesheet">

	<%--//mapping css--%>
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.3/leaflet.css" />



	<%-- JavaScripts --%>
	<script src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/jquery-1.11.2.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/bootstrap.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/select2.min.js" />"></script>
	<%--JS for data display on maps:--%>

	<title>NGHBR</title>
</head>

<body>
<div  class="container text-center">
	<div class="col-md-6 col-md-offset-3" style="padding-top: 100px;">

		<img src="<c:url value="/static/images/find-neighbourhood.png" />" alt="" height="200" width="200" />

		<form method="post" action="/user/initialise" >
			<c:choose>
			<c:when test="${pageContext.request.userPrincipal.authenticated}">
			<h1>Find your neighbourhood...</h1>
			<p>As it is your first time on NGHBR, please set a Suburb and Postcode.</p>

			<c:if test="${alertMessage != null}">
				<div class="alert alert-warning" role="alert">${alertMessage}</div>
			</c:if>

			<div style="padding: 10px;">
				<input type="text" class="form-control"
					   value="${user.suburb}" maxlength="30" name="suburb" id="suburb" placeholder="Suburb, e.g. University of Sydney" />
			</div>
			<div style="padding: 10px;">
				<input type="text" class="form-control"
					   value="${user.suburb.postcode}" maxlength="5" name="postcode" id="postcode" placeholder="Postcode, e.g. 2006"/>
			</div>

			<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
			<input type="submit" class="btn btn-lg btn-success" title="initialise" value="Join my neighbourinos!" />
			<a href="<c:url value="/logout" />" class="btn btn-lg btn-danger">Cancel</a>
		</form>
	</div>

	</c:when>
	<c:otherwise>
		Please <a href="<c:url value="/login" />">login</a> to continue
	</c:otherwise>
	</c:choose>

</div>

</body>

</html>


