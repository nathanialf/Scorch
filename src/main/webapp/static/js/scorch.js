/**
 * 
 */
$(document).ready(function() {
	$(".topic").click(function() {
		if(this.id == "add"){
			document.getElementById("hidden-form").innerHTML = "<div class='container'><div class='well'>" +
					"<form method='post' action='addtopic'>" +
					"<div class='form-group'>" +
					"<label for='usr'>Topic:</label>" +
					"<input type='hidden' name='id' value='"+document.getElementById("week-id").innerHTML+"'>" +
					"<input type='text' name='topic' class='form-control' id='usr'>" +
					"</div>" +
					"<button type='submit' class='btn btn-warning'>Submit</button>" +
					"</form>" +
					"</div></div>";
		}
		else if (this.id != ""){
			document.getElementById("hidden-form").innerHTML = "<div class='container'><div class='well'>" +
			"<form method='post' action='addrating'>" +
			"<div class='form-group'>" +
			"<label for='usr'>Topic:</label><br>" +
			"<input type='number' name='rating' min='0' max='5' value='3'>" +
			"<input type='hidden' name='topic' value='"+this.id+"'>" +
			"<input type='hidden' name='id' value='"+document.getElementById("week-id").innerHTML+"'>" +
			"</div>" + 
			"<button type='submit' class='btn btn-warning'>Submit</button>" +
			"</form>" +
			"</div></div>"
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
	
	$('.topic').on('click', function() {
	    $(this).toggleClass('clicked');
	});
});