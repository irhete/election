

function createChannel(type, id) {
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
				    	createResultsTable();
				    };
				} else if (type == "party") {
					socket.onmessage = function () {
				    	createPartyResultsTable(id);
				    };
				} else {
					socket.onmessage = function () {
				    	createAreaResultsTable(id);
				    };
				}
		}
	});
}



function createResultsTable() {
	createChannel("general", 0);
	if($.trim($(".textLink:not(:empty)"))){
		$("#content").empty();
	}
	$.ajax({
		beforeSend: function() {$('#spinner').show();},
		complete: function() {$('#spinner').hide();},
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
    									$("<td>").text(result.party).on("click", function(){createPartyResultsTable(result.partyId)}),
    									$("<td>").text(result.votes).on("click", function(){createAreaResultsTable(result.areaId)}));
    			tbody.append(row);
			});
			
			$("#content table").hide();
			table.append(tbody);
			$(".candidatesTable").remove();
			table.prependTo("#content");
			
			$('<a>',{
			    text: 'Vaata tulemusi graafiliselt',
			    title: 'Vaata tulemusi Eesti kontuurkaardil',
			    href: '#',
			    class: 'graphicLink',
			    click: function(){createGraphicResults()}
			}).prependTo("#content");
			
			$(".tablesorter").tablesorter({sortList: [[0,0], [1,0]]}); 
		}
	});
}

function createAreaResultsTable(areaId) {
	createChannel("area", areaId);
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

function createPartyResultsTable(partyId) {
	createChannel("party", partyId);
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

function createGraphicResults(){
	$("#content").empty();
	$('<a>',{
	    text: 'Vaata tulemusi tekstikujul',
	    title: 'Vaata tulemusi tekstitabelina',
	    href: '#',
	    class: 'textLink',
	    click: function(){createResultsTable()}
	}).prependTo("#content");
	var img = $('<img>').attr({'class': 'mapOfEst', 'src': '../images/kontuur.png'});
	img.appendTo('#content');
}