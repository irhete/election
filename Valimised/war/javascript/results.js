

function createChannel(type) {
	$.ajax({
		complete: function() {$('#spinner').hide();},
		url:'/newchannel?type=' + type,
		datatype: "text/plain",
		success:function(result){
		    channel = new goog.appengine.Channel(result);
		    socket = channel.open();
		    socket.onopen = function () {
		    	connected = true;
		    };
		    	if (type == "general") {
				    socket.onmessage = function () {
				    	updateResultsTable();
				    };
				} else if (type == "party") {
					socket.onmessage = function () {
				    	updatePartyResultsTable();
				    };
				} else {
					socket.onmessage = function () {
				    	updateAreaResultsTable();
				    };
				}
		}
	});
}

function updateResultsTable() {
	if (location.hash == "#results") {
		createResultsTable();
	}
}

function updateAreaResultsTable() {
	if (location.hash.substring(0,12) == "#areaResults") {
		createAreaResultsTable(location.hash.substring(12));
	}
}

function updatePartyResultsTable() {
	if (location.hash.substring(0,13) == "#partyResults") {
		createPartyResultsTable(location.hash.substring(14));
	}
}

window.createResultsTable = function () {
	createChannel("general");
//	if($.trim($(".textLink:not(:empty)"))){
//		$("#content").empty();
//	}
	$.ajax({
		beforeSend: function() {$('#spinner').show();},
		complete: function() {$('#spinner').hide();},
		url:'/results',
		datatype: "json",
		success:resultsTableOnline,
		error:resultsTableOffline
	});
}

function resultsTableOnline(results){
	if(localStorage){
		localStorage.resultsTable = JSON.stringify(results);
	}
	resultsTable(results)
}

function resultsTableOffline(){
	if(localStorage){
		if (localStorage.resultsTable){
			resultsTable(JSON.parse(localStorage.resultsTable));
		}
	}
}

function resultsTable(results){
	
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
		row = $("<tr>").append($("<td>").text(result.area).on("click", function(){window.addHistoryItem("areaResults" + result.areaId)}),
								$("<td>").text(result.party).on("click", function(){window.addHistoryItem("partyResults" + result.partyId)}),
								$("<td>").text(result.votes).on("click", function(){window.addHistoryItem("areaResults" + result.areaId)}));
		tbody.append(row);
	});
	
	$("#content table").hide();
	table.append(tbody);
	$(".candidatesTable").remove();
	table.prependTo("#content");
	
//	$('<a>',{
//	    text: 'Vaata tulemusi graafiliselt',
//	    title: 'Vaata tulemusi Eesti kontuurkaardil',
//	    href: '#',
//	    class: 'graphicLink',
//	    click: function(){createGraphicResults()}
//	}).prependTo("#content");
	
	$(".tablesorter").tablesorter({sortList: [[0,0], [1,0]]}); 
}

window.createAreaResultsTable = function (areaId) {
	createChannel("area");
	$.ajax({
		beforeSend: function() {$('#spinner').show();},
		complete: function() {$('#spinner').hide();},
		url:'/results/area?areaId=' + areaId,
		type:'GET',
		datatype: "json",
		data:{
			action: 'create'
		},
		success:function(result){
			var columnNames = ["Nimi","Number","Erakond","Tulemus"];
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
			result.forEach(function (AreaResult) {
    			row = $("<tr>").append($("<td>").text(AreaResult.name),
    									$("<td>").text(AreaResult.candidateNumber),
    									$("<td>").text(AreaResult.partyName),
    									$("<td>").text(AreaResult.votes));
    			tbody.append(row);
			});
			$("#content table").hide();
			table.append(tbody);
			$(".candidatesTable").remove();
			table.prependTo("#content");
			
			$(".tablesorter").tablesorter({sortList: [[0,0], [1,0]]});
		}
	});
}

window.createPartyResultsTable = function (partyId) {
	createChannel("party");
	$.ajax({
		beforeSend: function() {$('#spinner').show();},
		complete: function() {$('#spinner').hide();},
		url:'/results/party?partyId=' + partyId,
		type:'GET',
		datatype: "json",
		data:{
			action: 'create'
		},
		success:function(result){
			var columnNames = ["Nimi","Piirkond","Erakond","Tulemus"];
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
			result.forEach(function (result) {
    			row = $("<tr>").append($("<td>").text(result.name),
    									$("<td>").text(result.area),
    									$("<td>").text(result.partyName),
    									$("<td>").text(result.votes));
    			tbody.append(row);
			});
			$("#content table").hide();
			table.append(tbody);
			$(".candidatesTable").remove();
			table.prependTo("#content");
			
			$(".tablesorter").tablesorter({sortList: [[0,0], [1,0]]});
		}
	});
}

//function createGraphicResults(){
//	$("#content").empty();
//	$('<a>',{
//	    text: 'Vaata tulemusi tekstikujul',
//	    title: 'Vaata tulemusi tekstitabelina',
//	    href: '#',
//	    class: 'textLink',
//	    click: function(){createResultsTable()}
//	}).prependTo("#content");
//	var img = $('<img>').attr({'class': 'mapOfEst', 'src': '../images/kontuur.png'});
//	img.appendTo('#content');
//}