$(document).ready(function(){
	var wH = $(window).height(), bH = $('html').height(), hH = $('#footerPart').height();
	wH -= hH;

	// fixes the positioning for the footer
	$('#footerPart').css('top', (wH < bH ? bH : wH)+'px');
});