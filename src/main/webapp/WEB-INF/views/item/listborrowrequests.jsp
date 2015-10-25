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
		<h3>Borrow Requests</h3>
		<h4>Here are the ${label} ${user.getFirstName()}</h4>
		<c:forEach items="${borrowRequests}" var="req">
			<div class="item row box well">
				<form class="form-inline" action="/borrowrequest/${req.getId()}">
					<div class="container">
						<div class="form-group">
							<img class="itemimage img-thumbnail" src="/item_images/${req.getItem().getOwner().getId()}/${req.getItem().getId()}.jpg" alt="Item Image">
						</div>
						<div class="itemtext form-group">
							<h4 class="itemname">${req.getItem().getName()}</h4>
							<p class="itemdesc">${req.getItem().getDescription()}</p>
						</div>
					</div>
					<div class="container">
						<div class="sentmessage message">
							<h5><b>Message:</b></h5>
							<p>${req.getMessage()}</p>
						</div>
					</div>
					<div class="container">
						<div class="responsemessage message">
							<h5><b>Response:</b></h5>
							<p>${req.getResponseMessage()}</p>
						</div>
					</div>
					<div class="container">	
						<div class="approveicon">
							<c:if test="${req.getApproved()}">
								<span class="label label-success">Approved</span>
							</c:if>
							<c:choose>
								<c:when test="${!req.getApproved() && req.getResponseMessage()==null}">
									<h5><span class="label label-default">Pending</span></h5>
								</c:when>
								<c:otherwise>
									<c:if test="${!req.getApproved()}">
										<span class="label label-danger">Denied</span>
									</c:if>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<c:if test='${ label.equals("requests you have recieved")}'>
						<button type="submit" class="editbutton btn btn-info">View</button>
					</c:if>
				</form>
			</div>
		</c:forEach>
		<c:choose>
			<c:when test='${label.equals("requests you sent")}'>
				<button type="button" class="addbutton btn btn-primary" onclick="location.href='/items/search'">Find Items in you area!</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="addbutton btn btn-primary" onclick="location.href='/items'">Manage Items</button>
			</c:otherwise>
		</c:choose>
	</div>
	
</body>
</html>


