//책 연장 처리
function bookExtensionConfirm(lendId){
	if(confirm("정말 연장하실거에요?")){
		bookExtension(lendId);
	}else{
	}
}



function bookExtension(lendId) {
	
	$.ajax({
		
		type : "put",
		url : `/user/api/${lendId}/bookExtension`,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8"
		
	}).done(res =>{
		
		console.log("성공", res);
		
		alert("연장 되었습니다");
		location.href = `/`;
		
	}).fail(error =>{
		
		 if(error.data == null){
			alert(error.responseJSON.message);
			console.log(error.responseJSON.message);
			
		}else{
			console.log("연장 실패", error);	 		
	
			alert(JSON.stringify(error.responseJSON.data));
		
		};
		
	})//end of fail


}


function bookReturnConfirm(lendId){
	
	if(confirm("정말로 반납하시겠습니까?")){
		bookReturn(lendId);
	}else{
	}
	
}

//책 반납 처리
function bookReturn(lendId) {
	
	console.log(lendId);
	
	$.ajax({
		
		type : "put",
		url : `/user/api/${lendId}/bookReturn`,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8"
		
	}).done(res =>{
		
		console.log("성공", res);
		
		alert("반납 되었습니다");
		location.href = `/`;
		
	}).fail(error =>{
			
			console.log("실패",error);
			alert("반납을 실패했습니다");
		
		 
	})//end of fail
	
	
}



















