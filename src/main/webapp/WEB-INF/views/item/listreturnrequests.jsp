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
<link href="<c:url value="/static/css/listbr.css" />"
	rel="stylesheet">


<%-- JavaScripts --%>
<script
	src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />"></script>
<script src="<c:url value="/static/js/vendor/jquery-1.11.2.min.js" />"></script>
<script src="<c:url value="/static/js/vendor/bootstrap.min.js" />"></script>

<title>NGHBR</title>
</head>

<body>
	<jsp:include page="/navBar"></jsp:include>
	
	<div class="container">
		<h1>Item Return Requests</h1>
		<h4>Here are the ${label}</h4>
		<hr>
		<c:forEach items="${returnRequests}" var="req">
			<div class="item row box well">
				<form class="form-inline" action="/returnrequest/${req.getId()}">
					<div class="container">
						<div class="form-group">
							<img class="itemimage img-thumbnail" src="/item_images/${req.getItem().getOwner().getId()}/${req.getItem().getId()}.jpg" alt="Item Image">
						</div>
						<div class="itemtext form-group">
							<h4 class="itemname">${req.getItem().getName()}</h4>
							<p class="itemdesc">${req.getItem().getDescription()}</p>
						</div>
					</div>
					<c:if test='${ label.equals("items you returned")}'>
						<div class="container">
							<div class="returnmessage message">
									<h5><b>Your Feedback:</b></h5>
									<p> ${req.getBorrowerMessage()}</p>
									<h5><b>Feedback Recieved: </b></h5>
									<p>${req.getOwnerMessage()}</p>
							</div>
						</div>
						<div class="container">
							<div class="score">
								<span class="scorelabel label label-success">Score Given <span class="badge"> ${req.getBorrowerScore()}</span></span>
								<c:if test="${req.getOwnerScore() > 0}">
									<span class="scorelabel label label-primary">Score Recieved <span class="badge"> ${req.getOwnerScore()}</span></span>
								</c:if>
							</div>
						</div>
					</c:if>
					<c:if test='${ label.equals("items returned to you")}'>
						<div class="container">
							<div class="returnmessage message">
									<h5><b>Your Feedback:</b></h5>
									<p>${req.getOwnerMessage()}</p>
									<h5><b>Feedback Recieved: </b></h5>
									<p> ${req.getBorrowerMessage()}</p>
							</div>
						</div>
						<div class="container">
							<div class="score">
								<c:if test="${req.getOwnerScore() > 0}">
									<span class="scorelabel label label-success">Score Given <span class="badge">  ${req.getOwnerScore()}</span></span>
								</c:if>
								<span class="scorelabel label label-primary">Score Recieved <span class="badge"> ${req.getBorrowerScore()}</span></span>
							</div>
						</div>
					</c:if>
					
					<c:if test='${ !label.equals("items you returned")}'>
						<button type="submit" class="editbutton btn btn-info">View</button>
					</c:if>
				</form>
			</div>
		</c:forEach>
		<c:choose>
			<c:when test='${label.equals("items you returned")}'>
				<button type="button" class="addbutton btn btn-primary" onclick="location.href='/items/search'">Find Items in you area!</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="addbutton btn btn-primary" onclick="location.href='/items'">Manage Items</button>
			</c:otherwise>
		</c:choose>
	</div>
	
</body>
</html>


