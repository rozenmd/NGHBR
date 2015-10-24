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
<link href="<c:url value="/static/css/additem.css" />"
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
	
		<h3>Borrow Item</h3>
		<div class="item row box well">
				<form class="form-inline" action="/items/edit/${item.getId()}">
					<div class="form-group">
						<img class="itemimage img-thumbnail" src="/item_images/${item.getOwner().getId()}/${item.getId()}.jpg" alt="Item Image">
					</div>
					<div class="itemtext form-group">
						<h4 class="itemname">${item.getName()}</h4>
						<p class="itemdesc">${item.getDescription()}</p>
					</div>				
				</form>
		</div>
		
		<div class="item row box well">
			<h4>Message from Borrower:</h4>
			<p>${borrowRequestForm.getMessage()}</p>
		</div>
		
		<h4>Enter a message for the borrower:</h4>
		<form:form method="POST" modelAttribute="borrowRequestForm" action="/borrowrequest/${borrowRequestForm.getId()}?${_csrf.parameterName}=${_csrf.token}" name="item" enctype="multipart/form-data">

			<div class="form-group">
				<label for="responseMessage">Message</label>
				<form:input path="responseMessage" type="text" class="form-control" id="responseMessage" placeholder="Message"/>
				<form:errors path="responseMessage" />
			</div>
 			<div class="form-group">
				<label><form:checkbox path="approved" class="form-control" id="approved"/>Approve?</label>
			</div>
			
			<button type="submit" class="addbutton btn btn-primary btn-lg">Submit</button>
		</form:form>
	</div>
	
</body>
</html>


