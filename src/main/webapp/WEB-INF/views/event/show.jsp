<p>Starting @ ${event.getStart()}</p>
<p>Finishing @ ${event.getEnd()}</p>

<p>${event.getDescription()}</p>

<hr>

<h3>RSVP</h3>
<a class="btn btn-success rsvp" data-rsvp="true">Going</a>
<a class="btn btn-danger rsvp" data-rsvp="false">Not Going</a>
<input type="hidden" id="modalCsrfToken" value="${_csrf.token}"/>
<input type="hidden" id="modalCsrfHeader" value="${_csrf.headerName}"/>

<hr>
<h3>Neighbours Going</h3>


<hr>
<h3>Neighbours Not Going</h3>


<script>
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
            success: function(data){
                //redirecting once logged in
                console.log(data)
            }});
    });
</script>