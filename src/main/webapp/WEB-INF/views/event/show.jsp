<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>Starting @ ${event.getStart()}</p>
<p>Finishing @ ${event.getEnd()}</p>

<p>${event.getDescription()}</p>

<hr>
<h3>RSVP</h3>
<c:choose>
    <c:when test="${attendance == null}">
        <h3><small id="rsvp-caption">Do you want to attend this event?</small></h3>
        <a class="btn btn-success rsvp" id="going" data-rsvp="true">Going</a>
        <a class="btn btn-danger rsvp" id="not-going" data-rsvp="false">Not Going</a>
    </c:when>
    <c:when test="${attendance.getRsvp()}">
        <h3><small id="rsvp-caption">You are attending, see you there!</small></h3>
        <a class="btn btn-success rsvp active" id="going" data-rsvp="true">Going</a>
        <a class="btn btn-danger rsvp" id="not-going" data-rsvp="false">Not Going</a>
    </c:when>
    <c:otherwise>
        <h3><small id="rsvp-caption">Not going? Oh well...</small></h3>
        <a class="btn btn-success rsvp" id="going" data-rsvp="true">Going</a>
        <a class="btn btn-danger rsvp active" id="not-going" data-rsvp="false">Not Going</a>
    </c:otherwise>
</c:choose>


<input type="hidden" id="modalCsrfToken" value="${_csrf.token}"/>
<input type="hidden" id="modalCsrfHeader" value="${_csrf.headerName}"/>

<hr>
<h3>Neighbours Going</h3>
<div class="container" id="going-neighbours" style="min-height: 100px;">
<c:forEach items="${usersGoing}" var="goingUser">
    <img class="going-user-pic" id="${goingUser.getId()}" src="${goingUser.getProfilePictureUrl("small")}" data-toggle="tooltip"
         data-placement="bottom" title="${goingUser.getFirstName()} ${goingUser.getLastName()}"/>
</c:forEach>
</div>

<script>
    $('[data-toggle="tooltip"]').tooltip();
    var modalToken = $('#modalCsrfToken').val();
    var modalHeader = $('#modalCsrfHeader').val();
    $('.rsvp').on('click', function() {
        var rsvp = $(this).data('rsvp');
        $.ajax({
            url:'/events/${event.getId()}/rsvp',
            type:"POST",
            dataType: "json",
            data: JSON.stringify({rsvp: rsvp, user: null, event: null}), //Stringified Json Object
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(modalHeader, modalToken);
            },
            complete: function(data){
                var jsonData = JSON.parse(data.responseText);
                var newRsvp = jsonData.rsvp;
                var newActiveButton, oldButton, newCaption;
                if (newRsvp) {
                    newActiveButton = $('#going');
                    oldButton = $('#not-going');
                    newCaption = "You are attending, see you there!";
                    var pic = '<img src="' + window.userPictureSmall + '" class="going-user-pic" id="' + window.userId + '" data-toggle="tooltip" ' +
                            'data-placement="bottom" title="' + window.userFullname + '" />';
                    $('.container#going-neighbours').append(pic);
                } else {
                    newActiveButton = $('#not-going');
                    oldButton = $('#going');
                    newCaption = "Not going? Oh well...";
                    $('.going-user-pic#' + window.userId).remove();
                }

                newActiveButton.addClass('active');
                oldButton.removeClass('active');
                $('#rsvp-caption').html(newCaption);



            }});
    });
</script>