function vote(candidateId) {
	$.ajax({
		url:'/vote?votedId=' + candidateId,
		type:'POST',
		data:{
			action: 'create'
		},
		success: function(){
//			alert('Valisid kandidaadi nr: ' + candidateId);
			window.voted();
			window.newChoices();
		}
	});
}
