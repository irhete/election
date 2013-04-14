loggedInUser = {
	userID : 0,
	userRegion : 'Tartu', // hard-coded at the moment
	votedFor : 0
};

function server_logIn(accessToken) {
	$.ajax({
		url : 'login',
		headers : {
			'accessToken' : accessToken
		},
		cache : false,
		contentType : 'application/json',
		processData : false,
		type : 'POST',
		success : function(data) {
			if(!data.error){
				loggedInUser.userID = data.userID;
				loggedInUser.votedFor = data.votedFor;
				loggedInUser.loggedIn = true;
		}
	});
};

function login() {
	FB.login(function(response) {
		if (response.authResponse) {
			// connected
			server_logIn(response.authResponse.accessToken);
		} else {
			// cancelled
		}
	}, {
		scope : "email,user_birthday"
	});
}

function server_logOut() {
	$.ajax({
		url: 'logout',
		cache: false,
		contentType: 'application/json',
		processData: false,
		type: 'POST',
		success: function(data){
			loggedInUser.loggedIn = false;
		}
	});
};

function logout() {
    FB.logout(function(response) {
    server_logOut();
    });
}

window.fbAsyncInit = function() {
	FB.init({
		appId : '458082167601457',
		channelUrl : '//e-election.appspot.com//channel.html',
		status : true,
		cookie : false,
		xfbml : false
	});

	// Additional init code here
	FB.getLoginStatus(function(response) {
		if (response.status === 'connected') {
			server_logIn(response.authResponse.accessToken);
		} else if (response.status === 'not_authorized') {
			// not_authorized
		} else {
			// not_logged_in
		}
	});
	// Additional initialization code such as adding Event Listeners goes here
};

// Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) {
		return;
	}
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/et_EE/all.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

$(document).ready(function() {
	$("#Facebook").click(function() {
		login();
	});
});