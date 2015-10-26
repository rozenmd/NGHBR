<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="/head"></jsp:include>
  <body>
    <script src="<c:url value="/static/js/vendor/moment.js" />"></script>
    <script src="<c:url value="/static/js/vendor/bootstrap-datetimepicker.min.js" />"></script>
    <link href="<c:url value="/static/css/bootstrap-datetimepicker.min.css" />" rel="stylesheet">
    <jsp:include page="/navBar"></jsp:include>

    <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
    <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

    <script>
      window.eventsList = ${eventsJSON};
      window.userId = ${user.getId()};
      window.userFullname = "${user.getFirstName()} ${user.getLastName()}";
      window.userPictureSmall = "${user.getProfilePictureUrl('small')}";
    </script>

    <div class="container">
      <h1>Events</h1>
      <a class="btn btn-primary" onclick="$('#new-event').modal()">New Event</a>

      <div class="page-header">

        <div class="pull-right form-inline">
          <div class="btn-group">
            <button class="btn btn-primary" data-calendar-nav="prev"><< Prev</button>
            <button class="btn btn-default" data-calendar-nav="today">Today</button>
            <button class="btn btn-primary" data-calendar-nav="next">Next >></button>
          </div>
          <div class="btn-group">
            <button class="btn btn-warning" data-calendar-view="year">Year</button>
            <button class="btn btn-warning active" data-calendar-view="month">Month</button>
            <button class="btn btn-warning" data-calendar-view="week">Week</button>
            <button class="btn btn-warning" data-calendar-view="day">Day</button>
          </div>
        </div>

        <h3></h3>
      </div>


      <div class="col-md-9">
      <div id="calendar"></div>
    </div>
    <div class="col-md-3">

      <h4>Events</h4>
      <ul id="eventlist" class="nav nav-list"></ul>
    </div>

    <div class="modal fade" id="new-event">
      <div class="modal-dialog modal-lg">
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
                <input type="text" class="nghbr-datetimepicker form-control" id="start-date" name="start-date" class="form-control" data-parsley-trigger="change" data-parsley-required="true" data-parsley-date/>
              </div>
              <div class="date-selectors">
                <label for="end-date">End Date</label>
                <input type='text' class="nghbr-datetimepicker form-control" id="end-date" name="end-date" class="form-control" data-parsley-trigger="change" data-parsley-required="true" data-parsley-date/>
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

    <div class="modal fade" id="events-modal">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3>Event</h3>
          </div>
          <div class="modal-body" style="height: 400px">
          </div>
          <div class="modal-footer">
            <a href="#" data-dismiss="modal" class="btn">Close</a>
          </div>
        </div>
      </div>
    </div>

    <script src="<c:url value="/static/js/calendar.min.js" />"></script>
    <script type="text/javascript">
        $('.nghbr-datetimepicker').datetimepicker({
          sideBySide: true
        });
      var formParsley = $('form#new-event-form').parsley();
      var calendar = $("#calendar").calendar(
              {
                tmpl_path: "<c:url value="/static/tmpls" />/",
                modal: "#events-modal",
                modal_type: 'ajax',
                modal_title: function (event) {
                  $('#events-modal .modal-header h3').html(event.title);
                },
                events_source: window.eventsList,
                onAfterEventsLoad: function(events) {
                  if(!events) {
                    return;
                  }
                  var list = $('#eventlist');
                  list.html('');

                  $.each(events, function(key, val) {
                    $(document.createElement('li'))
                            .html('<a href="' + val.url + '">' + val.title + '</a>')
                            .appendTo(list);
                  });
                },
                onAfterViewLoad: function(view) {
                  $('.page-header h3').text(this.getTitle());
                  $('.btn-group button').removeClass('active');
                  $('button[data-calendar-view="' + view + '"]').addClass('active');
                },
              });

        $('.btn-group button[data-calendar-nav]').each(function() {
          var $this = $(this);
          $this.click(function() {
            calendar.navigate($this.data('calendar-nav'));
          });
        });

        $('.btn-group button[data-calendar-view]').each(function() {
          var $this = $(this);
          $this.click(function() {
            calendar.view($this.data('calendar-view'));
          });
        });

      function createNghbrEvent() {
        formParsley.validate();
      }

      formParsley.on('form:success', function(e) {
        var token = $('#csrfToken').val();
        var header = $('#csrfHeader').val();

        $.ajax({
          url:'/events/new',
          type:"POST",
          dataType: "json",
          data: JSON.stringify({title: $('#title').val(), description: $('#description').val(), start: new Date($('#start-date').val()).getTime(), end: new Date($('#end-date').val()).getTime()}), //Stringified Json Object
          beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token);
          },
          success: function(data){
            //redirecting once logged in
            window.location = '/events';
          }});
      })
    </script>
  </body>
</html>