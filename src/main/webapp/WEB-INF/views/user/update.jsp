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
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.3/leaflet.css" />



	<%-- JavaScripts --%>
	<script
			src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/jquery-1.11.2.min.js" />"></script>
	<script src="<c:url value="/static/js/vendor/bootstrap.min.js" />"></script>

	<title>NGHBR</title>
</head>

<body>
<jsp:include page="/navBar"></jsp:include>

<div class="container text-center">
	<div class="col-md-6 col-md-offset-3">
		<c:choose>
			<c:when test="${pageContext.request.userPrincipal.authenticated}">
				<h1>Update Profile</h1>
				<img class="img-circle" src="${user.getProfilePictureUrl("large")}" />
				<form method="post" action="update" >

					<label for="firstName">First Name</label>
					<input type="text" style="text-align: center;"
						   value="${user.firstName}" maxlength="30" name="firstName" id="firstName" class="form-control" />

					<label for="lastName">Last Name</label>
					<input type="text" style="text-align: center;"
						   value="${user.lastName}" maxlength="30" name="lastName" id="lastName" class="form-control" />

					<label for="postcode">Postcode</label>
					<input type="text" style="text-align: center;"
						   value="${user.suburb.postcode}" maxlength="5" name="postcode" id="postcode" class="form-control" />

					<label for="suburb">Suburb</label>
					<input type="text" style="text-align: center;"
						   value="${user.suburb}" maxlength="30" name="suburb" id="suburb" class="form-control" />

					<label for="email">Email</label>
					<input type="text" style="text-align: center;"
						   value="${user.email}" maxlength="30" name="email" id="email" class="form-control" />

					<div style="padding-top: 10px;">
						<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
						<input type="submit" class="btn btn-lg btn-success" title="Update" value="Update"/>
						<a class="btn btn-lg btn-danger" href="../profile">Cancel</a>
					</div>
				</form>
			</c:when>
			<c:otherwise>
				Please <a href="<c:url value="/login" />">login</a> to continue
			</c:otherwise>
		</c:choose>
	</div>




</div>
</body>
</html>


