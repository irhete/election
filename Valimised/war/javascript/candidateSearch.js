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
    		var table = window.document.createElement("table");
    		table.setAttribute("class", "candidatesTable");
    		var row = window.document.createElement("tr");
    		table.appendChild(row);
    		
    		var nameColumn = window.document.createElement("td");
    		nameColumn.appendChild(window.document.createTextNode("Nimi"));
			row.appendChild(nameColumn);
			
			var partyColumn = window.document.createElement("td");
			partyColumn.appendChild(window.document.createTextNode("Erakond"));
			row.appendChild(partyColumn);
			
			var areaColumn = window.document.createElement("td");
			areaColumn.appendChild(window.document.createTextNode("Piirkond"));
			row.appendChild(areaColumn);
			
			var numberColumn = window.document.createElement("td");
			numberColumn.appendChild(window.document.createTextNode("Number"));
			row.appendChild(numberColumn);
			
			var votingColumn = window.document.createElement("td");
			votingColumn.appendChild(window.document.createTextNode(""));
			row.appendChild(votingColumn);

			result.candidates.forEach(function (candidate) {
				if ((selectedArea == "--Kõik--" || candidate.area == selectedArea) && (searchKeywords == "" || candidate.person.name.toLowerCase().indexOf(searchKeywords) !== -1)) {
	    			row = window.document.createElement("tr");
	    			table.appendChild(row);
	    			nameColumn = window.document.createElement("td");
	    			nameColumn.appendChild(window.document.createTextNode(candidate.person.name));
	    			row.appendChild(nameColumn);
	    			partyColumn = window.document.createElement("td");
	    			partyColumn.appendChild(window.document.createTextNode(candidate.party.name));
	    			row.appendChild(partyColumn);
	    			areaColumn = window.document.createElement("td");
	    			areaColumn.appendChild(window.document.createTextNode(candidate.area));
	    			row.appendChild(areaColumn);
	    			numberColumn = window.document.createElement("td");
	    			numberColumn.appendChild(window.document.createTextNode(candidate.number));
	    			row.appendChild(numberColumn);
	    			
	    			nameColumn.setAttribute('onclick','getDetailedCandidateInfo()');
	    			partyColumn.setAttribute('onclick','getDetailedCandidateInfo()');
	    			areaColumn.setAttribute('onclick','getDetailedCandidateInfo()');
	    			numberColumn.setAttribute('onclick','getDetailedCandidateInfo()');
	    			
	    			votingColumn = window.document.createElement("td");
	    			if (candidate.area == "Tartu") {
	    				var votingButton = window.document.createElement("button");
	    				votingButton.innerText = "Hääleta";
	    				votingButton.setAttribute('onclick','vote()');
	    				votingColumn.appendChild(votingButton);
	    			} else {
	    			votingColumn.appendChild(window.document.createTextNode(""));
	    			votingColumn.setAttribute('onclick','getDetailedCandidateInfo()');
	    			}
	    			row.appendChild(votingColumn);
				}
    		});
    		 document.getElementById("content").innerHTML = table.outerHTML;
    	}
    });
}

function vote() {
	alert("Voted");
}

function getDetailedCandidateInfo() {
	alert("Info");
}