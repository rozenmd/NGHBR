<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<c:url value="/" />">NGHBR</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
          <c:when test="${pageContext.request.userPrincipal.authenticated}">
            <li><a href="<c:url value="/profile" />">Profile</a></li>
            <li><a href="<c:url value="/user/scoreboard" />">Scoreboard</a></li>
            <li><a href="<c:url value="/messageboard" />">Message Board</a></li>
            <li class="dropdown">
          		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Items <span class="caret"></span></a>
	            <ul class="dropdown-menu">
		            <li><a href="<c:url value="/items" />">My Items</a></li>
		            <li><a href="<c:url value="/items/borrowed" />">Borrowed Items</a></li>
		            <li><a href="<c:url value="/items/search" />">Find Items!</a></li>
		            <li><a href="<c:url value="/borrowrequests/sent" />">Borrow Requests Sent</a></li>
		            <li><a href="<c:url value="/borrowrequests/recieved" />">Borrrow Requests Received</a></li>
		            <li><a href="<c:url value="/returnrequests/sent" />">Feedback Sent</a></li>
		            <li><a href="<c:url value="/returnrequests/recieved" />">Feedback Recieved</a></li>
		        </ul>
            </li>
            <li><a href="<c:url value="/events" />">Events</a></li>
            <li><a href="<c:url value="/logout" />">Logout</a></li>
          </c:when>
          <c:otherwise>
            <li><a href="<c:url value="/login" />">Login</a></li>
          </c:otherwise>
        </c:choose>
      </ul>

    </div><!--/.navbar-collapse -->
  </div>
</nav>