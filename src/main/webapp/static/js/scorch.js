/**
 * 
 */
$(document).ready(function() {
	$(".topic").click(function() {
		if(this.id == "add"){
			document.getElementById("hidden-form").innerHTML = "<div class='container'><div class='well'>" +
					"<form:form>" +
					"<div class='form-group'>" +
					"<label for='usr'>Topic:</label>" +
					"<input type='text' class='form-control' id='usr'>" +
					"</div>" +
					"<button type='submit' class='btn btn-primary'>Submit</button>" +
					"</form:form>" +
					"</div></div>";
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