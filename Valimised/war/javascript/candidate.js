function createCandidate(firstName, lastName, area, party) {
	$.ajax({
		url:'/application?firstName=' + firstName + '&lastName=' + lastName + '&area=' + area + '&party=' + party,
		type:'POST',
		data:{
			action: 'create'
		},
		success: function(){
			window.added();
			window.newChoices();
		}
	});
}
