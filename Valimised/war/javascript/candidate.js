function createCandidate(firstName, lastName, area, party, idCode) {
	$.ajax({
		url:'/application?firstName=' + firstName + '&lastName=' + 
			lastName + '&area=' + area + '&party=' + party + '&idCode=' + idCode,
		type:'POST',
		data:{
			action: 'create'
		},
		success: function(){
			window.added(true);
			window.newChoices();
		}
	});
}

function cancelCandidate(idCode) {
	$.ajax({
		url:'/cancelCandidate?idCode=' + idCode,
		type:'POST',
		data:{
			action: 'delete'
		},
		success: function(){
//			alert('Valisid kandidaadi nr: ' + candidateId);
			window.added(false);
			window.newChoices();
		}
	});
}
