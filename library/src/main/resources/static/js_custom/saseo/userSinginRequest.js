//책 연장 처리


/**사용자 가입 승인 처리 */
function userPermit(userId, event) {
	
	$.ajax({
		
		type : "put",
		url : `/saseo/api/${userId}/userPermit`,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		beforeSend : function(xhr) 
        {   /*데이터를 전송하기 전에 헤더에 csrf값 설정*/
			xhr.setRequestHeader(csrfHeaderValue, csrfTokenValue);
        }
		
	}).done(res =>{
		
		console.log("성공", res);
		
		alert("가입 승인 되었습니다");
		location.href = `/saseo/signinRequest`;
		
	}).fail(error =>{
		
		 if(error.data == null){
			alert(error.responseJSON.message);
			console.log(error.responseJSON.message);
			
		}else{
			console.log("가입 승인 실패", error);	 		
	
			alert(JSON.stringify(error.responseJSON.data));
		
		};
		
	})//end of fail


}


















