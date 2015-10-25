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
		
		<c:choose>
			<c:when test='${label.equals("Items you own")}'>
				<h3>My Items</h3>
			</c:when>
			<c:otherwise>
				<h3>Borrowed Items</h3>
			</c:otherwise>
		</c:choose>
		<h4>Here are the ${label} ${user.getFirstName()}</h4>
		<c:forEach items="${items}" var="item">
			<div class="item row box well">
				<form class="form-inline" action="/items/edit/${item.getId()}">
					<div class="form-group">
						<img class="itemimage img-thumbnail" src="/item_images/${item.getOwner().getId()}/${item.getId()}.jpg" alt="Item Image">
					</div>
					<div class="itemtext form-group">
						<h4 class="itemname">${item.getName()}</h4>
						<p class="itemdesc">${item.getDescription()}</p>
					</div>
					<c:choose>
						<c:when test='${label.equals("Items you own")}'>
							<button type="submit" class="editbutton btn btn-info">Edit</button>
						</c:when>
						<c:otherwise>
							<button type="button" onclick="location.href='/items/return/${item.getId()}';" class="editbutton btn btn-info">Return</button>
						</c:otherwise> 
					</c:choose>
				</form>
				
			</div>
		</c:forEach>
		<c:choose>
			<c:when test='${label.equals("Items you own")}'>
				<button type="button" class="addbutton btn btn-primary" onclick="location.href='/additem'">Add New Item</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="addbutton btn btn-primary" onclick="location.href='/items/search'">Find Items in you area!</button>
			</c:otherwise>
		</c:choose>
	</div>
	
</body>
</html>


