<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/head"></jsp:include>
<body>
<jsp:include page="/navBar"></jsp:include>

	<input type="hidden" id="csrfToken" value="${_csrf.token}"/>
	<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

	<div class="container">

		<div class="col-md-8 col-md-offset-2">
			<h1 class="text-center">Login to NGHBR</h1>
			<c:url var="loginUrl" value="/login" />
			<form action="${loginUrl}" method="post" class="form-horizontal">
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">
						<p>Invalid username and password.</p>
					</div>
				</c:if>
				<c:if test="${param.logout != null}">
					<div class="alert alert-success">
						<p>You have been logged out successfully.</p>
					</div>
				</c:if>
				<div class="input-group input-sm">
					<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
					<input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
				</div>
				<div class="input-group input-sm">
					<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
					<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

				<div class="form-actions">
					<input type="submit"
						   class="btn btn-block btn-primary btn-lg" value="Log in">
				</div>
			</form>

			<fb:login-button size="large" scope="public_profile,email" onlogin="checkLoginState();">
			</fb:login-button>

		</div>

	</div>





<script src="<c:url value='/static/js/facebook.js'/>"></script>


</body>
</html>