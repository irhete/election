function createResultsTable() {
	$.ajax({
//		$('#spinner').hide()
//	    .ajaxStart(function() {
//	        $(this).show();
//	    })
//	    .ajaxStop(function() {
//	        $(this).hide();
//	    });
		url:'/results',
		datatype: "json",
		success:function(results){
			var columnNames = ["Piirkond", "Erakond", "Tulemus"];
			var table = $("<table>").addClass("tablesorter");
    		table.addClass("candidatesTable");
    		var header = $("<tr>");
    		var thead = $("<thead>");
    		var tbody = $("<tbody>");
			var row = $("<tr>");
			
			$.each(columnNames, function(index, value){
				header.append($("<th>").text(value));
			});
			thead.append(header);
			table.append(thead);

			results.forEach(function (result) {
    			row = $("<tr>").append($("<td>").text(result.area).on("click", function(){createAreaResultsTable(result.areaId)}),
    									$("<td>").text(result.party).on("click", function(){createAreaResultsTable(result.areaId)}),
    									$("<td>").text(result.votes).on("click", function(){createAreaResultsTable(result.areaId)}));
    			tbody.append(row);
			});
			
			$("#content table").hide();
			table.append(tbody);
			$(".candidatesTable").remove();
			table.prependTo("#content");
			
			$(".tablesorter").tablesorter({sortList: [[0,0], [1,0]]}); 
			$(".tablesorter").bind("sortStart",function() { 
    	        $("#spinner").show(); 
    	    }).bind("sortEnd", setInterval(function() { 
    	        $("#spinner").hide(); 
    	    }, 2000)); 
		}
	});
}

function createAreaResultsTable(areaId) {
	$.ajax({
		url:'/Piirkond?areaId=' + areaId,
		datatype: "json",
		data:{
			action: 'create'
		},
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
			$("#content table").hide();
			table.append(tbody);
			$(".overallResultsTable").remove();
			table.prependTo("#content");
			
			$(".tablesorter").tablesorter({sortList: [[0,0], [1,0]]}); 
		}
	});
}