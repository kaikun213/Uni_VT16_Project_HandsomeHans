$(document).ready(function(){
	
	fixFooter();
	setTimeout(function() { //sometimes it is acting weird
		fixFooter();
	}, 1000);
	
	$('.showDetail').parent().parent().parent().children('.panel-container').hide();
	
	$('.showDetail').click(function() {
		var a = $(this).children('.fa'),
			elm = $(this).parent().parent().parent();
		
		if(a.hasClass('fa-minus')){
			a.removeClass('fa-minus');
			a.addClass('fa-plus');

			elm.children('.panel-container').slideToggle();
		}
		else {
			a.removeClass('fa-plus');
			a.addClass('fa-minus');

			elm.children('.panel-container').slideToggle();
		}
		
	});
});

function fixFooter(){
	var wH = $(window).height(), bH = $('html').height(), hH = $('#footerPart').height();


	// fixes the positioning for the footer
	$('#footerPart').css('top', ((wH < bH ? bH : wH))+'px');
}