function validateForm() {
	validate('partyBox','partyFieldRequired','Vali kuuluvus');
	validate('areaBox','areaFieldRequired','Vali linn');
	validatePhoneNumber();
}

function validate(elementField, elementId, value){
	if (document.getElementById(elementField).value == value) {
		document.getElementById(elementId).style.visibility = 'visible';
		return false;
	} else
		document.getElementById(elementId).style.visibility = 'hidden';
	return true;
}

function isNumber(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}

function validatePhoneNumber(){
	if (isNaN(document.getElementById("phoneBox").value-0)) {
		document.getElementById("invalidPhoneNr").style.visibility = 'visible';
		return false;
	} else
		document.getElementById("invalidPhoneNr").style.visibility = 'hidden';
	return true;
}

function test() {
	alert("Stop touching me!");
}