<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login page</title>
		<%-- Stylesheets --%>
		<link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/static/css/font-awesome.min.css" />" rel="stylesheet">
		<link href="<c:url value="/static/css/bootstrap-social.css" />" rel="stylesheet">
		<link href="<c:url value="/static/css/style.css" />" rel="stylesheet">

		<%-- JavaScripts --%>
		<script src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />"></script>
		<script src="<c:url value="/static/js/vendor/jquery-1.11.2.min.js" />"></script>
		<script src="<c:url value="/static/js/vendor/bootstrap.min.js" />"></script>

		<script src="<c:url value='/static/js/facebook.js'/>"></script>
	</head>

	<body>
	<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
	<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

	<fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
	</fb:login-button>

	<div id="status">
	</div>

	<a onclick="logout()">Logout</a>


	</body>
</html>