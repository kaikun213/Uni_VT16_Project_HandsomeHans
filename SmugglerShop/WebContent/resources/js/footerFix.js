$(document).ready(function(){
	
	fixFooter();
	setTimeout(function() { //sometimes it is acting weird
		fixFooter();
	}, 1000);
});

function fixFooter(){
	var wH = $(window).height(), bH = $('html').height(), hH = $('#footerPart').height();


	// fixes the positioning for the footer
	$('#footerPart').css('top', (wH < bH ? bH : wH)+'px');
}