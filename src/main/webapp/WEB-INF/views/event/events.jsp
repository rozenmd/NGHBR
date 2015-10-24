<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/head"></jsp:include>
  <body>
    <script src="<c:url value="/static/js/bootstrap-datepicker.min.js" />"></script>
    <link href="<c:url value="/static/css/bootstrap-datepicker.min.css" />" rel="stylesheet">
    <link href="<c:url value="/static/css/bootstrap-datepicker3.min.css" />" rel="stylesheet">
    <jsp:include page="/navBar"></jsp:include>

    <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
    <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

    <div class="container">
      <h1>Events</h1>
      <a class="btn btn-primary" onclick="$('#new-event').modal()">New Event</a>

      <div id="calendar"></div>


    </div>


    <div class="modal fade" id="new-event">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">New Event</h4>
          </div>
          <div class="modal-body">
            <form id="new-event-form">
              <label for="title">Title</label>
              <input id="title" name="title" class="form-control" data-parsley-required="true"/>
              <div class="date-selectors">
                <label for="start-date">Start Date</label>
                <input class="datepicker" id="start-date" name="start-date" class="form-control" data-parsley-trigger="change" data-parsley-required="true" data-parsley-date/>
              </div>
              <div class="date-selectors">
                <label for="end-date">End Date</label>
                <input class="datepicker" id="end-date" name="end-date" class="form-control" data-parsley-trigger="change" data-parsley-required="true" data-parsley-date/>
              </div>
              <label for="description">Description</label>
              <textarea id="description" name="description" class="form-control" data-parsley-required="true"></textarea>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" onclick="createNghbrEvent()">Create Event</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <script src="<c:url value="/static/js/calendar.min.js" />"></script>
    <script type="text/javascript">
      $('.datepicker').datepicker();
      var formParsley = $('form#new-event-form').parsley();
      var calendar = $("#calendar").calendar(
              {
                tmpl_path: "<c:url value="/static/tmpls" />/",
                events_source: function () { return []; }
              });

      function createNghbrEvent() {
        formParsley.validate();
      }

      formParsley.on('form:success', function(e) {
        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        console.log(JSON.stringify({name: $('#title').val(), description: $('#description').val(), startDate: new Date($('#start-date').val()).getTime(), endDate: new Date($('#end-date').val()).getTime()}))

        $.ajax({
          url:'/events/new',
          type:"POST",
          dataType: "json",
          data: JSON.stringify({name: $('#title').val(), description: $('#description').val(), startDate: new Date($('#start-date').val()).getTime(), endDate: new Date($('#end-date').val()).getTime()}), //Stringified Json Object
          beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token);
          },
          success: function(data){
            //redirecting once logged in
            console.log(data)
            window.location = '/events';
          }});
      })
    </script>
  </body>
</html>