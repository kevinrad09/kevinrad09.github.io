$(document).ready(function () {
	$(".close").click(function () {
		$(this).parent().fadeOut(250);
	});

	$("nav > a").mouseenter(function () {
		var atag = $(this);
		$("#pawprint").fadeIn(250, function () {
			var left = atag.position().left;
			var width = atag.width();
			var pawWidth = $("#pawprint").width();
			var middle = left + width / 2 - pawWidth/2;
			$("#pawprint").css("left", middle + "px");
		});
	});

	$("nav").mouseleave(function () {
		$("#pawprint").fadeOut(250);
	});

	$("#schedule").click(function () {
		$("#schedule-popup").fadeIn(250);
		return false;
	});
});

