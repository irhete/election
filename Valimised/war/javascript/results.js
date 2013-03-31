function createResultsTable() {
	$.ajax({
		$('#spinner').hide()
	    .ajaxStart(function() {
	        $(this).show();
	    })
	    .ajaxStop(function() {
	        $(this).hide();
	    });
		url:'/Eesti',
		datatype: "json",
		success:function(result){
			var colNames = ["Piirkond","Erakond","Tulemus"];
			var table = $("<table>").addClass("tablesorter");
    		table.addClass("overallResultsTable");
    		var header = $("<tr>");
    		var thead = $("<thead>");
    		var tbody = $("<tbody>");
			var row = $("<tr>");
			
			$.each(columnNames, function(index, value){
				header.append($("<th>").text(value));
			});
			thead.append(header);
			table.append(thead);
			
			result.forEach(function (result) {
    			row = $("<tr>").append($("<td>").text(result.areaName).on("click", createAreaResultsTable),
    									$("<td>").text(result.partyName).on("click", createAreaResultsTable),
    									$("<td>").text(result.result).on("click", createAreaResultsTable));
    			tbody.append(row);
		});
		}
	});
}

function createAreaResultsTable() {
	$.ajax({
		url:'/Piirkond',
		datatype: "json",
		success:function(result){
			var colNames = ["Nimi","Number","Erakond","Tulemus"];
			var table = $("<table>").addClass("tablesorter");
    		table.addClass("overallResultsTable");
    		var header = $("<tr>");
    		var thead = $("<thead>");
    		var tbody = $("<tbody>");
			var row = $("<tr>");
			
			$.each(columnNames, function(index, value){
				header.append($("<th>").text(value));
			});
			thead.append(header);
			table.append(thead);
			
			result.forEach(function (areaResult) {
    			row = $("<tr>").append($("<td>").text(areaResult.name),
    									$("<td>").text(areaResult.candidateNumber),
    									$("<td>").text(areaResult.partyName),
    									$("<td>").text(areaResult.votes));
    			tbody.append(row);
		});
		}
	});
}