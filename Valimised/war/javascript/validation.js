function validateForm(){      
    if (document.getElementById("username").value == "Vali kuuluvus"){
        document.getElementById("areaFieldRequired").style.visibility = 'visible';
        return false;
    }
    else
        document.getElementById("areaFieldRequired").style.visibility = 'hidden';
	return true;
}