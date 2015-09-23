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
<link href="<c:url value="/static/css/messageboard.css" />"
	rel="stylesheet">



<%-- JavaScripts --%>
<script
	src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />"></script>
<script src="<c:url value="/static/js/vendor/jquery-1.11.2.min.js" />"></script>
<script src="<c:url value="/static/js/vendor/bootstrap.min.js" />"></script>
<script src="<c:url value="/static/js/messageboard.js" />"></script>


<title>NGHBR</title>
</head>

<body>
	<jsp:include page="/navBar"></jsp:include>

	<h3>Message Board</h3>
	<div class="container box well">
		<div class="conversationsholder">
			<c:forEach items="${messages}" var="message" varStatus="counter">
				<c:choose>
					<c:when test="${counter.index % 2 == 0}">
						<div class="bubble_left">
							<p>${message.getText()}</p>
						</div>
					</c:when>
					<c:otherwise>
						<div class="bubble_right">
							<p>${message.getText()}</p>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>

		<form:form method="POST" action="messageboard" name="message">
			<div class="input-group">
				<textarea id="messagetext" class="form-control"
					placeholder="Type Message..." rows="2" name="text"></textarea>
				<span class="input-group-btn">
					<button class="btn a btn-primary btn-lg"
						type="submit">Post!</button>
				</span>
			</div>
		</form:form>

	</div>
</body>
</html>


