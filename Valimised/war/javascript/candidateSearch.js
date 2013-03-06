function search() {
	    $.ajax({
	    	url:"candidatesData/findCandidatesByRegion.json", 
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
    			
    			var numberColumn = window.document.createElement("td");
    			numberColumn.appendChild(window.document.createTextNode("Number"));
    			row.appendChild(numberColumn);
    			
    			var votingColumn = window.document.createElement("td");
    			votingColumn.appendChild(window.document.createTextNode(""));
    			row.appendChild(votingColumn);

    			result.candidates.forEach(function (candidate) {
	    			row = window.document.createElement("tr");
	    			table.appendChild(row);
	    			nameColumn = window.document.createElement("td");
	    			nameColumn.appendChild(window.document.createTextNode(candidate.person.name));
	    			row.appendChild(nameColumn);
	    			partyColumn = window.document.createElement("td");
	    			partyColumn.appendChild(window.document.createTextNode(candidate.party.id));
	    			row.appendChild(partyColumn);
	    			numberColumn = window.document.createElement("td");
	    			numberColumn.appendChild(window.document.createTextNode(candidate.id));
	    			row.appendChild(numberColumn);
	    			votingColumn = window.document.createElement("td");
	    			votingColumn.appendChild(window.document.createTextNode(""));
	    			row.appendChild(votingColumn);
	    		});
	    		 var content = window.document.getElementById("content");
	    		 content.appendChild(table);
	    	}
	    });
}