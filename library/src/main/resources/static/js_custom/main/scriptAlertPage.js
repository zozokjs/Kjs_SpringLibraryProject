/**
*/

$(document).ready(function(){
	
	const msg = $("#scriptMessage").val();
	const targetPage = $("#targetPage").val();
	
	alert(msg);	
	
	location.href = targetPage;
});


