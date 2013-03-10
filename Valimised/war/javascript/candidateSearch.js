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
	alert("Voted");
}

function getDetailedCandidateInfo() {
	alert("Info");
} 