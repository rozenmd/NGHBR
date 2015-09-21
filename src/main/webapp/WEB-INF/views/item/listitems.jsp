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
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/nghbr">NGHBR</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<%-- 				<li><a href="<c:url value="/login" />">Login</a></li>
 --%>
				</ul>

			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>
	
	<div class="container">
		<h3>My Items</h3>
		<h4>Here are the items you own ${user.getSsoId()}</h4>
		<c:forEach items="${items}" var="item">
			<div class="item row box well">
				<form class="form-inline" action="/item/edit/${item.getId()}">
					<div class="form-group">
						<img class="itemimage img-thumbnail" src="/static/images/1defaultitem.png" alt="Item Image">
					</div>
					<div class="itemtext form-group">
						<h4 class="itemname">${item.getName()}</h4>
						<p class="itemdesc">${item.getDescription()}</p>
					</div>
					<button type="submit" class="editbutton btn btn-info">Edit</button> 
					
				</form>
				
			</div>
		</c:forEach>
		<button type="button" class="addbutton btn btn-primary" onclick="location.href='/additem'">Add New Item</button>
	</div>
	
</body>
</html>


