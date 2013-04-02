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
			var loggedIn = window.isLogged();

			// lisame päise - esimese rea
			$.each(columnNames, function(index, value){
				header.append($("<th>").text(value));
			});
			thead.append(header);
			table.append(thead);

			result.forEach(function (candidate) {
	    			row = $("<tr>").append($("<td>").text(candidate.firstName + " " + candidate.lastName).on("click", function(){getDetailedCandidateInfo(candidate.id)}),
	    									$("<td>").text(candidate.party).on("click", function(){getDetailedCandidateInfo(candidate.id)}),
	    									$("<td>").text(candidate.area).on("click", function(){getDetailedCandidateInfo(candidate.id)}),
	    									$("<td>").text(candidate.id).on("click", function(){getDetailedCandidateInfo(candidate.id)}),
	    									$("<td>").text("").addClass("votingColumn"));

	    			if (candidate.area == "Tartu linn" && loggedIn==true) {
	    				button = $("<button>").text("Hääleta");
	    				button.on("click", function(){vote(candidate.id)}); // klikihändler
	    				button.addClass("candidatesTableVotingButton");
	    				row.children().last().append(button);
	    			} else {
	    				row.children().last().on("click", function(){getDetailedCandidateInfo(candidate.id)});
	    			}
	    			tbody.append(row);
    		});

			$("#content table").hide();
			table.append(tbody);
			$(".candidatesTable").remove();
			table.prependTo("#content");
			if (selectedArea == 0 && searchKeywords == "") {
				window.candidatePage(1);
			}
			else {
				window.candidatePage(2);
			}


			$(".tablesorter").tablesorter( {sortList: [[0,0], [1,0]], headers: { 4: { sorter: false} }}); 
			$(".tablesorter").bind("sortStart",function() { 
    	        $("#spinner").show(); 
    	    }).bind("sortEnd", setInterval(function() { 
    	        $("#spinner").hide(); 
    	    }, 2000)); 
    	}
    	
    });
}

function getDetailedCandidateInfo(candidateNumber) {
	window.newCandidate(candidateNumber);
} 