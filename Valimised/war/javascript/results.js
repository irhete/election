function createChannel(type) {
	$.ajax({
		complete : function() {
			$('#spinner').hide();
		},
		url : '/newchannel?type=' + type,
		datatype : "text/plain",
		success : function(result) {
			channel = new goog.appengine.Channel(result);
			socket = channel.open();
			socket.onopen = function() {
				connected = true;
			};
			if (type == "general") {
				socket.onmessage = function() {
					updateResultsTable();
				};
			} else if (type == "party") {
				socket.onmessage = function() {
					updatePartyResultsTable();
				};
			} else {
				socket.onmessage = function() {
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
	if (location.hash.substring(0, 12) == "#areaResults") {
		createAreaResultsTable(location.hash.substring(12));
	}
}

function updatePartyResultsTable() {
	if (location.hash.substring(0, 13) == "#partyResults") {
		createPartyResultsTable(location.hash.substring(14));
	}
}

window.createResultsTable = function() {
//	$("#content").empty();
	createChannel("general");
	$.ajax({
		beforeSend : function() {
			$('#spinner').show();
		},
		complete : function() {
			$('#spinner').hide();
		},
		url : '/results',
		datatype : "json",
		success : function(results) {

			var columnNames = [ "Piirkond", "Erakond", "Tulemus" ];
			var table = $("<table>").addClass("tablesorter");
			table.addClass("candidatesTable");
			var header = $("<tr>");
			var thead = $("<thead>");
			var tbody = $("<tbody>");
			var row = $("<tr>");

			$.each(columnNames, function(index, value) {
				header.append($("<th>").text(value));
			});
			thead.append(header);
			table.append(thead);

			results.forEach(function(result) {
				row = $("<tr>").append(
						$("<td>").text(result.area).on(
								"click",
								function() {
									window.addHistoryItem("areaResults"
											+ result.areaId)
								}),
						$("<td>").text(result.party).on(
								"click",
								function() {
									window.addHistoryItem("partyResults"
											+ result.partyId)
								}),
						$("<td>").text(result.votes).on(
								"click",
								function() {
									window.addHistoryItem("areaResults"
											+ result.areaId)
								}));
				tbody.append(row);
			});

			$("#content table").hide();
			table.append(tbody);
			$(".candidatesTable").remove();
			table.prependTo("#content");

			$('<a>', {
				text : 'Vaata tulemusi graafiliselt',
				title : 'Vaata tulemusi Eesti kontuurkaardil',
				href : '#',
				class : 'graphicLink',
				click : function() {
					loadScript()
				}
			}).prependTo("#content");

			$(".tablesorter").tablesorter({
				sortList : [ [ 0, 0 ], [ 1, 0 ] ]
			});
		}
	});
}

window.createAreaResultsTable = function(areaId) {
	createChannel("area");
	$.ajax({
		beforeSend : function() {
			$('#spinner').show();
		},
		complete : function() {
			$('#spinner').hide();
		},
		url : '/results/area?areaId=' + areaId,
		type : 'GET',
		datatype : "json",
		data : {
			action : 'create'
		},
		success : function(result) {
			var columnNames = [ "Nimi", "Number", "Erakond", "Tulemus" ];
			var table = $("<table>").addClass("tablesorter");
			table.addClass("candidatesTable");
			var header = $("<tr>");
			var thead = $("<thead>");
			var tbody = $("<tbody>");
			var row = $("<tr>");

			$.each(columnNames, function(index, value) {
				header.append($("<th>").text(value));
			});
			thead.append(header);
			table.append(thead);
			result.forEach(function(AreaResult) {
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

			$(".tablesorter").tablesorter({
				sortList : [ [ 0, 0 ], [ 1, 0 ] ]
			});
		}
	});
}

window.createPartyResultsTable = function(partyId) {
	createChannel("party");
	$.ajax({
		beforeSend : function() {
			$('#spinner').show();
		},
		complete : function() {
			$('#spinner').hide();
		},
		url : '/results/party?partyId=' + partyId,
		type : 'GET',
		datatype : "json",
		data : {
			action : 'create'
		},
		success : function(result) {
			var columnNames = [ "Nimi", "Piirkond", "Erakond", "Tulemus" ];
			var table = $("<table>").addClass("tablesorter");
			table.addClass("candidatesTable");
			var header = $("<tr>");
			var thead = $("<thead>");
			var tbody = $("<tbody>");
			var row = $("<tr>");

			$.each(columnNames, function(index, value) {
				header.append($("<th>").text(value));
			});
			thead.append(header);
			table.append(thead);
			result.forEach(function(result) {
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

			$(".tablesorter").tablesorter({
				sortList : [ [ 0, 0 ], [ 1, 0 ] ]
			});
		}
	});
}

// ----- Google Maps -----

function loadScript() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false&callback=initialize";
	document.body.appendChild(script);
}

function initialize() {
	$.ajax({
		url : '/results',
		datatype : "json",
		success : function(results) {
			var geocoder, map;
//			$("#content").empty();
//			var cont = document.getElementById("content");
			$inner = $('<div style="width: 800px; height: 600px"></div>').appendTo('#content');
//			cont.style.width = "800px";
//			cont.style.height = "600px";
			geocoder = new google.maps.Geocoder();
			var mapProp = {
				center : new google.maps.LatLng(58.621154, 25.041174),
				zoom : 7,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
//			map = new google.maps.Map(document.getElementById("content"), mapProp);
			
			$("#content table").hide();
			$(".candidatesTable").remove();
			$inner.addClass("candidatesTable");
//			table.prependTo("#content");
			map = new google.maps.Map($inner[0], mapProp);
			var infowindow = new google.maps.InfoWindow(), marker, i;

			// [0] = Area name, [1,2] = Area coordinates, [3] = area id
			var locations = [ [ 'Tallinn1', 59.426046, 24.6488, 1 ],
					[ 'Tallinn2', 59.433467, 24.861002, 2 ],
					[ 'Tallinn3', 59.376851, 24.690371, 3 ],
					[ 'Harju- ja Raplamaa', 59.167747, 24.749866, 4 ],
					[ 'Hiiu-, L‰‰ne- ja Saaremaa', 58.93885, 23.542271, 5 ],
					[ 'L‰‰ne-Virumaa', 59.347545, 26.362524, 6 ],
					[ 'Ida-Virumaa', 59.347195, 27.40242, 7 ],
					[ 'J‰rva- ja Viljandimaa', 58.629787, 25.557239, 8 ],
					[ 'Jıgeva- ja Tartumaa', 58.649798, 26.589203, 9 ],
					[ 'Tartu linn', 58.371478, 26.726532, 10 ],
					[ 'Vıru-, Valga- ja Pılvamaa', 57.882737, 26.951752, 11 ],
					[ 'P‰rnumaa', 58.382998, 24.521027, 12 ] ];

			for (i = 0; i < locations.length; i++) {
				marker = new google.maps.Marker({
					position : new google.maps.LatLng(locations[i][1],locations[i][2]),
					map : map,
				});
				
				var colors = new Array("yellow-dot","red-dot","blue-dot","green-dot","orange-dot","pink-dot");
				google.maps.event.addListener(marker, 'click', (function(marker, i) {
					var text = "";
					var areaBest = 5;
					var votesSum = 0;
					var previousBest = 0;
					results.forEach(function(result) {
						if(result.areaId == locations[i][3]){
							votesSum += result.votes;
						}
					});
					results.forEach(function(result) {
						if(result.areaId == locations[i][3] && votesSum != 0){
							text = text + "<br />" + result.party + " " + (result.votes*100/votesSum).toFixed(1) + "%";
							if(result.votes > previousBest){
								previousBest = result.votes;
								areaBest = result.partyId-1;
							}
						}
					});
					if(text == ""){
						text = "<br />Selles piirkonnas pole veel h‰‰letatud";
					}
					iconFile = 'http://maps.google.com/mapfiles/ms/icons/'+colors[areaBest]+'.png';
					marker.setIcon(iconFile);
					return function() {						
						infowindow.setContent("<b>"+locations[i][0]+"</b><br />" + text);
						infowindow.open(map, marker);
					}
				})(marker, i));
			}
			var layer = new google.maps.FusionTablesLayer(1);
			layer.setMap(map);

			var legendDiv = document.createElement('DIV');
			var legend = new Legend(legendDiv, map);
			legendDiv.index = 1;
			map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(legendDiv);
		}
	});
}

function Legend(legendDiv, map) {
	legendDiv.style.padding = '5px';

	var controlUI = document.createElement('DIV');
	controlUI.style.backgroundColor = 'white';
	controlUI.style.borderStyle = 'solid';
	controlUI.style.borderWidth = '1px';
	controlUI.title = 'Legend';
	legendDiv.appendChild(controlUI);

	var controlText = document.createElement('DIV');
	controlText.style.fontFamily = 'Arial,sans-serif';
	controlText.style.fontSize = '12px';
	controlText.style.paddingLeft = '4px';
	controlText.style.paddingRight = '4px';

	controlText.innerHTML = '<b>Piirkonna liider:</b><br />' +
	'<img src="http://maps.google.com/mapfiles/ms/micons/red-dot.png" /> SDE<br />' +
    '<img src="http://maps.google.com/mapfiles/ms/micons/yellow-dot.png" /> Reform<br />' +
    '<img src="http://maps.google.com/mapfiles/ms/micons/green-dot.png" /> Kesk<br />' +
    '<img src="http://maps.google.com/mapfiles/ms/micons/blue-dot.png" /> IRL<br />' +
    '<img src="http://maps.google.com/mapfiles/ms/micons/orange-dot.png" /> &Uumlksikkandidaat<br />' +
    '<img src="http://maps.google.com/mapfiles/ms/micons/pink-dot.png" /> Tulemusi pole<br />'
    controlUI.appendChild(controlText);
}