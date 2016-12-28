/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
	function requestEvents() {
		var month = $("select[name='month']").val();
		var year = $("select[name='year']").val();

		$.ajax({
			url: "calendar", //the address of the servlet - relative URL, so no leading /
			method: "POST", //use HTTP POST operation
			dataType: "json", //expect JSON data back
			data: {
				month: month,
				year: year
			},
			error: function (xhr, status, err) {	//an error happened, report it to the user
				alert("Error! " + status + ";" + err);
			},
			success: showEvents	//we got an answer, process it into HTML on the screen.
		});
	}

	function showEvents(events) {
		var table = $("<table\>");
		var row = $("<tr\>");
		var cell = $("<td\>");
		var displayBox = $("#displayEvents");

		if (Array.isArray(events)) {
			displayBox.empty();
			for (var i = 0; i < events.length; i++) {
				table.append(row);
				row.append(cell);
				cell.append("<h3>" + events[i].date + "</h3>");
				cell.append("<h1>" + events[i].name + "</h1>" + "<br>");
				cell.append(events[i].description + " " + "<br>");
				cell.append("<h3>" + events[i].venue + "</h3>");
				cell.append(events[i].street + " " + "<br>");
				cell.append(events[i].city + ", ");
				cell.append(events[i].state + " " + "<br>" + "<br>" + "<br>" + "<br>");
			}
			displayBox.append(table);
		} else {
			displayBox.append("Something went wrong.");
		}
			
		if (events.length<1) {
			displayBox.append("No events were found.");
		}
	}

	$("#getEvents").click(function () {
		requestEvents();
	});
});
