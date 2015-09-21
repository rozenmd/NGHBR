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
      <a class="navbar-brand" href="#">NGHBR</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <c:choose>
          <c:when test="${pageContext.request.userPrincipal.authenticated}">
            <li><a href="<c:url value="/profile" />">Profile</a></li>
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