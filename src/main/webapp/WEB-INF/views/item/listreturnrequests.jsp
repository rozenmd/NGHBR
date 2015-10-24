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
<link href="<c:url value="/static/css/listitems.css" />"
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
		<h3>Item Return Requests</h3>
		<h4>Here are the ${label} ${user.getFirstName()}</h4>
		<c:forEach items="${returnRequests}" var="req">
			<div class="item row box well">
				<form class="form-inline" action="/returnrequest/${req.getId()}">
					<div class="form-group">
						<img class="itemimage img-thumbnail" src="/item_images/${req.getItem().getOwner().getId()}/${req.getItem().getId()}.jpg" alt="Item Image">
					</div>
					<div class="itemtext form-group">
						<h4 class="itemname">${req.getItem().getName()}</h4>
						<p class="itemdesc">${req.getItem().getDescription()}</p>
					</div>
					<c:if test="${ label.equals("items you returned")}">
						<div class="itemtext form-group">
								<p class="itemdesc">Your Feedback: ${req.getBorrowerMessage()}</p>
								<p class="itemdesc">Feedback Recieved: ${req.getOwnerMessage()}</p>
							<p class="itemdesc"></p>
	
						</div>
						<div class="itemtext form-group">
							<p class="itemdesc">Score Given: ${req.getBorrowerScore()}</p>
							<p class="itemdesc">Score Recieved: ${req.getOwnerScore()}</p>
						</div>
					</c:if>
					<c:if test="${ label.equals("items returned to you")}">
						<div class="itemtext form-group">
								<p class="itemdesc">Your Feedback: ${req.getOwnerMessage()}</p>
								<p class="itemdesc">Feedback Recieved: ${req.getBorrowerMessage()}</p>
							<p class="itemdesc"></p>
	
						</div>
						<div class="itemtext form-group">
							<p class="itemdesc">Score Given: ${req.getOwnerScore()}</p>
							<p class="itemdesc">Score Recieved: ${req.getBorrowerScore()}</p>
						</div>
					</c:if>
					
					<c:if test="${ !label.equals("items you returned")}">
						<button type="submit" class="editbutton btn btn-info">View</button>
					</c:if>
				</form>
			</div>
		</c:forEach>
		<c:choose>
			<c:when test="${label.equals("items you returned")}">
				<button type="button" class="addbutton btn btn-primary" onclick="location.href='/items/search'">Find Items in you area!</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="addbutton btn btn-primary" onclick="location.href='/items'">Manage Items</button>
			</c:otherwise>
		</c:choose>
	</div>
	
</body>
</html>


