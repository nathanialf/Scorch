/**
 * 
 */
$(document).ready(function() {
	$(".topic").click(function() {
		if(this.id == "add"){
			alert("Adding topic");
		}
		else if (this.id != ""){
			alert("Rating topic: " + this.id); 
		}
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