/**
 * 
 */
$(document).ready(function() {
	$(".topic").click(function() {
		
	});
	
	//Listen to checkbox - toggle password fields 
	$('input[name=pwtoggle]').click(function() {
		if ($(this).is(':checked')) {
			$('#hidden-pass-fields').prop('disabled', false).removeClass('hidden');
		} else {
	  		$('#hidden-pass-fields').prop('disabled', false).addClass('hidden');
		}
	});
});