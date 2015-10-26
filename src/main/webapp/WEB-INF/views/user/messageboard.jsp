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
                <c:when test="${user.getId() == message.getUser().getId()}">
                    <div class="bubble_right">
                        <div class="photo">
                            <img src="https://graph.facebook.com/${user.getFacebookId()}/picture?width=50&height=50"/>
                        </div>
                        <div class="message">
                            <div class="thick">
                                <div class="name">
                                    You
                                </div>
                                <div class="date">
                                        <%--${message.getDate()}--%>
                                    <c:set var="date" value="${message.getDate()}"/>
                                    <%
                                        Object datee = pageContext.getAttribute("date");
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM YYYY HH:mm");
                                        String date = sdf.format(datee);
                                        pageContext.setAttribute("date", date);
                                    %>
                                    <c:out value="${date}"/>
                                </div>
                            </div>
                            <div class="text">
                                    ${message.getText()}
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="bubble_left">
                        <div class="photo">
                            <img src="https://graph.facebook.com/${message.getUser().getFacebookId()}/picture?width=50&height=50 "/>
                        </div>
                        <div class="message">
                            <div class="thick">
                                <div class="name">
                                        ${message.getUser().getFirstName()}
                                </div>
                                <div class="date">
                                        <%--${message.getDate()}--%>
                                    <c:set var="date" value="${message.getDate()}"/>
                                    <%
                                        Object datee = pageContext.getAttribute("date");
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM YYYY HH:mm");
                                        String date = sdf.format(datee);
                                        pageContext.setAttribute("date", date);
                                    %>
                                    <c:out value="${date}"/>
                                </div>
                            </div>
                            <div class="text">
                                    ${message.getText()}
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>

    <form:form method="POST" action="messageboard" name="message">
        <div class="input-group">
				<textarea id="messagetext" class="form-control"
                          placeholder="Type Message..." maxlength="150" rows="2" name="text"></textarea>
				<span class="input-group-btn">
					<button class="btn a btn-primary btn-lg"
                            type="submit">Post!
                    </button>
				</span>
        </div>
    </form:form>

</div>
</body>
</html>


