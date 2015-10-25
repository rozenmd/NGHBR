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
<%--JS for data display on maps:--%>

<title>NGHBR</title>
</head>

<body>
<jsp:include page="/navBar"></jsp:include>


	<div  style="padding-top: 40px;padding-bottom:345px;" class="container box well">
		<h3>Profile</h3>
		<div class="table-responsive" style="width:40%;float:left">
			<form method="post" action="update" >

			<table class="table" >
				<c:choose>
					<c:when test="${pageContext.request.userPrincipal.authenticated}">
						<p>As it is your first time on NGHBR, please set a Suburb and Postcode.</p>

						<tr>
							<td>Suburb</td>
							<td><input type="text" style="width: 185px;"
									   value="${user.suburb}" maxlength="30" name="suburb" id="suburb" /></td>
						</tr>
					<tr>
						<td>Postcode</td>
						<td><input type="text" style="width: 185px;"
								   value="${user.suburb.postcode}" maxlength="5" name="postcode" id="postcode" /></td>
					</tr>
						</table>
				<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
			<input type="submit" class="initialise" title="initialise" value="initialise" />
			<a href="../profile">Cancel</a>
		</form>

		</c:when>
					<c:otherwise>
						Please <a href="<c:url value="/login" />">login</a> to continue
					</c:otherwise>
				</c:choose>



</body>

</html>


