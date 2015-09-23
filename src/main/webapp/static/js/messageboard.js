


function scrollDown () {
	var $cont = $('.conversationsholder');
	$cont[0].scrollTop = $cont[0].scrollHeight;
	$('.inp').keyup(function(e) {
	    if (e.keyCode == 13) {
	        $cont.append('<p>' + $(this).val() + '</p>');
	        $cont[0].scrollTop = $cont[0].scrollHeight;
	        $(this).val('');
	    }
	})
	.focus();
	
}

window.onload = scrollDown;