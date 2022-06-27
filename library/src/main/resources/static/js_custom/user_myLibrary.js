//책 연장 처리
function bookExtensionConfirm(){
	if(confirm("정말 연장하실거에요?")){
		bookExtension();
	}else{
	}
}



function bookExtension() {
	
	const lendId = $("#lendId").val(); //연장 처리할 Lend Id
	console.log(lendId);
	
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


function bookReturnConfirm(){
	
	if(confirm("정말로 반납하시겠습니까?")){
		bookReturn();
	}else{
	}
	
}

//책 반납 처리
function bookReturn() {
	
	const lendId = $("#lendId").val(); //반납 처리할 Lend Id
	
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



















