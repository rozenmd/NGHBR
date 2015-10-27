<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>


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
<div id="wrap">

    <!-- Fixed navbar -->
    <jsp:include page="/navBar"></jsp:include>

    <!-- Begin page content -->
    <div class="container">
        <div class="page-header">
            <h1>Message Board <small>${user.getSuburb().getSuburbName()}</small></h1>
        </div>
        <c:forEach items="${messages}" var="message" varStatus="counter">
            <div class="row message">
                <div style="float: left; position: relative; padding-right: 15px; padding-left: 15px;">
                    <c:if test="${message.getUser().getFacebookId() != null}">
                        <img src="https://graph.facebook.com/${message.getUser().getFacebookId()}/picture?width=50&height=50 " style="width:50px;" height="50" width="50"/>
                    </c:if>
                    <c:if test="${message.getUser().getFacebookId() == null}">
                        <img src="<c:url value="/static/images/question.png" />" alt="" style="width:50px;" height="50" width="50">
                    </c:if>


                </div>
                <div class="col-md-9">
                    <div class="row message-meta">
                        <b style="">${message.getUser().getFirstName()} ${message.getUser().getLastName()}</b>
                        <c:set var="date" value="${message.getDate()}"/>
                        <%
                            Object datee = pageContext.getAttribute("date");
                            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM YYYY HH:mm");
                            String date = sdf.format(datee);
                            pageContext.setAttribute("date", date);
                        %>
                        <c:out value="${date}"/>
                    </div>
                    <div class="row message-content">
                        <p class="lead">${message.getText()}</p>
                    </div>
                <hr>
                </div>

            </div>
        </c:forEach>
    </div>

    <div id="push"></div>
</div>

<div id="footer">
    <div class="container">
        <form:form method="POST" action="messageboard" name="message">
            <div class="input-group" style="margin-top: 15px;">
                <textarea id="messagetext" class="form-control"
                          placeholder="Type Message..."  style="resize: none; height:45px;" name="text"></textarea>
				<span class="input-group-btn">
					<button class="btn a btn-primary btn-lg"
                            type="submit">Post!
                    </button>
				</span>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>


