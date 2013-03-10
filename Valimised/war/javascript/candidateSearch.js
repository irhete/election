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
    		var table = $("<table>").addClass("candidatesTable");
			var row = $("<tr>");
			var button;
						
			// lisame päise - esimese rea
			$.each(columnNames, function(index, value){
				row.append($("<td>").text(value));
			});
			table.append(row);
			
			result.candidates.forEach(function (candidate) {
				if ((selectedArea == "--Kõik--" || candidate.area == selectedArea) 
						&& (searchKeywords == "" || candidate.person.name.toLowerCase().indexOf(searchKeywords) !== -1)) {
	    			row = $("<tr>").append($("<td>").text(candidate.person.name),
	    									$("<td>").text(candidate.party.name),
	    									$("<td>").text(candidate.area),
	    									$("<td>").text(candidate.number),
	    									$("<td>").text(""));

	    			if (candidate.area == "Tartu") {
	    				button = $("<button>").text("Hääleta");
	    				button.on("click", vote); // klikihändler
	    				row.children().last().append(button);
	    			} 
	    			
	    			row.on("click", "td", getDetailedCandidateInfo); // klikihändler
	    			table.append(row);
				}
    		});

			$("#content table").hide();
			$(".candidatesTable").remove();
			table.prependTo("#content");	
			
    	}
    	
    });
}

function vote() {
	alert("Voted");
}

function getDetailedCandidateInfo() {
	alert("Info");
}