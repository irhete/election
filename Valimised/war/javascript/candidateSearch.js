function keywordSuggest() {
  $(function() {
    $( "#candidateSearchBox" ).autocomplete({
    	source: '/search',
        minLength: 2
    });
  });
}

function search() {
	var selectedArea = $('#areaList option:selected').index();
	var searchKeywords = $('#candidateSearchBox').val().toLowerCase();
	createCandidatesTable(selectedArea, searchKeywords);
}

function createCandidatesTable(selectedArea, searchKeywords) {
	$.ajax({
    	url:'/candidates?keywords=' + searchKeywords + '&area=' + selectedArea, 
        dataType: "json",
    	success:function(result){
    		var columnNames = ["Nimi", "Erakond", "Piirkond", "Number", ""];
    		var table = $("<table>").addClass("tablesorter");
    		table.addClass("candidatesTable");
    		var header = $("<tr>");
    		var thead = $("<thead>");
    		var tbody = $("<tbody>");
			var row = $("<tr>");
			var button;
						
			// lisame päise - esimese rea
			$.each(columnNames, function(index, value){
				header.append($("<th>").text(value));
			});
			thead.append(header);
			table.append(thead);
			
			result.forEach(function (candidate) {
	    			row = $("<tr>").append($("<td>").text(candidate.firstName + " " + candidate.lastName).on("click", getDetailedCandidateInfo),
	    									$("<td>").text(candidate.party).on("click", getDetailedCandidateInfo),
	    									$("<td>").text(candidate.area).on("click", getDetailedCandidateInfo),
	    									$("<td>").text(candidate.id).on("click", getDetailedCandidateInfo),
	    									$("<td>").text("").addClass("votingColumn"));

	    			if (candidate.area == "Tartu linn") {
	    				button = $("<button>").text("Hääleta");
	    				button.on("click", vote); // klikihändler
	    				button.addClass("candidatesTableVotingButton");
	    				row.children().last().append(button);
	    			} else {
	    				row.children().last().on("click", getDetailedCandidateInfo);
	    			}
	    			tbody.append(row);
    		});
			
			$("#content table").hide();
			table.append(tbody);
			$(".candidatesTable").remove();
			table.prependTo("#content");	
			
			
			$(".tablesorter").tablesorter( {sortList: [[0,0], [1,0]], headers: { 4: { sorter: false} }}); 
			$(".tablesorter").bind("sortStart",function() { 
    	        $("#spinner").show(); 
    	    }).bind("sortEnd", setInterval(function() { 
    	        $("#spinner").hide(); 
    	    }, 2000)); 
    	}
    	
    });
}

function createResultsTable() {
	$.ajax({
		$('#spinner').hide()
	    .ajaxStart(function() {
	        $(this).show();
	    })
	    .ajaxStop(function() {
	        $(this).hide();
	    });
		url:'/results/Eesti',
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
		url:'/results/Piirkond',
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

function vote() {
	alert("voted");
}

function getDetailedCandidateInfo() {
	window.newCandidate();
} 