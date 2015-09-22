<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/head"></jsp:include>
  <body>
    <jsp:include page="/navBar"></jsp:include>

    <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
    <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

    <div class="container">
      <h1>Events</h1>

      <div id="calendar"></div>


    </div>

    <script src="<c:url value="/static/js/calendar.min.js" />"></script>
    <script type="text/javascript">
      var calendar = $("#calendar").calendar(
              {
                tmpl_path: "<c:url value="/static/tmpls" />/",
                events_source: function () { return []; }
              });
    </script>
  </body>
</html>