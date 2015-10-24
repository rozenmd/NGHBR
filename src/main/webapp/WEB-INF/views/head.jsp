<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
  <%-- Stylesheets --%>
  <link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet">
  <link href="<c:url value="/static/css/font-awesome.min.css" />" rel="stylesheet">
  <link href="<c:url value="/static/css/bootstrap-social.css" />" rel="stylesheet">
  <link href="<c:url value="/static/css/landing.css" />" rel="stylesheet">
  <link href="<c:url value="/static/css/calendar.min.css" />" rel="stylesheet">
    <link href="<c:url value="/static/css/parsley.css" />" rel="stylesheet">
  <link href="<c:url value="/static/css/style.css" />" rel="stylesheet">

  <%-- JavaScripts --%>
  <script>
    window.ParsleyConfig = window.ParsleyConfig || {};

    window.ParsleyConfig.validators = window.ParsleyConfig.validators || {};

    window.ParsleyConfig.validators.date = {
      fn: function (value) {
        return /(0[1-9]|1[012])[- \/.](0[1-9]|[12][0-9]|3[01])[- \/.](19|20)\d\d/.test(value);
      },
      priority: 256
    };
  </script>

  <script src="<c:url value="/static/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js" />"></script>
  <script src="<c:url value="/static/js/vendor/jquery-1.11.2.min.js" />"></script>
  <script src="<c:url value="/static/js/vendor/bootstrap.min.js" />"></script>
  <script src="<c:url value="/static/js/vendor/underscore-min.js" />"></script>
  <script src="<c:url value="/static/js/vendor/parsley.min.js" />"></script>

  <title>NGHBR</title>
</head>