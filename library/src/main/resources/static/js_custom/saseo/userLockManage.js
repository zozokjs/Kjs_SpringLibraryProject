//책 연장 처리


/**사용자 계정 활성화 처리 */
function userPermit(userId, event) {
	
	$.ajax({
		
		type : "put",
		url : `/saseo/api/${userId}/userActivation`,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8"
		
	}).done(res =>{
		
		console.log("성공", res);
		
		alert("계정이 활성화 되었습니다");
		location.href = `/saseo/userLockManage`;
		
	}).fail(error =>{
		
		 if(error.data == null){
			alert(error.responseJSON.message);
			console.log(error.responseJSON.message);
			
		}else{
			console.log("계정 활성화 실패", error);	 		
	
			alert(JSON.stringify(error.responseJSON.data));
		
		};
		
	})//end of fail


}


















