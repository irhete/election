function validateForm() {
	validateCity();
	validateParty();
	validatePhoneNumber();
}

function validateCity(){
	if (document.getElementById("areaBox").value == "Vali linn") {
		document.getElementById("partyFieldRequired").style.visibility = 'visible';
		return false;
	} else
		document.getElementById("partyFieldRequired").style.visibility = 'hidden';
	return true;
}

function validateParty(){
	if (document.getElementById("partyBox").value == "Vali kuuluvus") {
		document.getElementById("areaFieldRequired").style.visibility = 'visible';
		return false;
	} else
		document.getElementById("areaFieldRequired").style.visibility = 'hidden';
	return true;
}

function validatePhoneNumber(){
	if (isNaN(document.getElementById("phoneBox").value) == "true") {
		document.getElementById("invalidPhoneNr").style.visibility = 'visible';
		return false;
	} else
		document.getElementById("invalidPhoneNr").style.visibility = 'hidden';
	return true;
}

function test() {
	alert("Stop touching me!");
}