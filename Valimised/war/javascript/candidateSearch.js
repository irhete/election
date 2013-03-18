function search() {
	var selectedArea = $('#areaList').val();
	var searchKeywords = $('#candidateSearchBox').val().toLowerCase();
	createCandidatesTable(selectedArea, searchKeywords);
}

function createCandidatesTable(selectedArea, searchKeywords) {
	$.ajax({
    	url:"candidatesData/candidates.json", 
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
			
			result.candidates.forEach(function (candidate) {
				if ((selectedArea == "--Kõik--" || candidate.area == selectedArea) 
						&& (searchKeywords == "" || candidate.person.name.toLowerCase().indexOf(searchKeywords) !== -1)) {
	    			row = $("<tr>").append($("<td>").text(candidate.person.name).on("click", getDetailedCandidateInfo),
	    									$("<td>").text(candidate.party.name).on("click", getDetailedCandidateInfo),
	    									$("<td>").text(candidate.area).on("click", getDetailedCandidateInfo),
	    									$("<td>").text(candidate.number).on("click", getDetailedCandidateInfo),
	    									$("<td>").text("").addClass("votingColumn"));

	    			if (candidate.area == "Tartu") {
	    				button = $("<button>").text("Hääleta");
	    				button.on("click", vote); // klikihändler
	    				button.addClass("candidatesTableVotingButton");
	    				row.children().last().append(button);
	    			} else {
	    				row.children().last().on("click", getDetailedCandidateInfo);
	    			}
	    			tbody.append(row);
				}
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

function vote() {
	alert("voted");
}

function getDetailedCandidateInfo() {
	window.newCandidate();
} 