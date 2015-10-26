


function scrollDown () {
	window.scrollTo(0,document.body.scrollHeight);
	$('#messagetext').focus();
}

window.onload = function() {
	scrollDown();
};