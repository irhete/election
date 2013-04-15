function vote(candidateId) {
	$.ajax({
		url:'/vote?votedId=' + candidateId,
		type:'POST',
		data:{
			action: 'create'
		},
		success: function(){
//			alert('Valisid kandidaadi nr: ' + candidateId);
			window.voted(candidateId);
			window.newChoices();
		}
	});
}

function cancelVote(id) {
	$.ajax({
		url:'/cancelVote?id=' + id,
		type:'GET',
		data:{
			action: 'delete'
		},
		success: function(){
//			alert('Valisid kandidaadi nr: ' + candidateId);
			window.voted(-1);
			window.newChoices();
		}
	});
}