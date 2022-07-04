

//회원 수정 완료 처리
function userUpdate(userId, event) {
	
	event.preventDefault(); //form 태그 액션 방지
	
	if( !($('#password').val() === $('#confirm-password').val()) ) {
		alert("비밀번호와 비밀번호 확인 값이 일치하지 않습니다.");
		return false;
	}

	
	
	let data =$(`#userUpdate`).serialize();

	console.log("1");
	console.log(data);
	
	$.ajax({
		
		type : "put",
		url : `/user/api/${userId}/updater`,
		data : data,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8"
		
	}).done(res =>{
		
		console.log("성공", res);
		console.log("3");
		
		alert("회원 정보가 수정되었습니당");
		location.href = `/`;
		
	}).fail(error =>{
		console.log("4");
		console.log(error);
		
		if(error.data == null){
			console.log("5");
			console.log(error.responseText);
			
			//alert(error.responseText);
			
		}else{
			console.log(" update 실패", error);	 		
			//이렇게 하니까 알림창에 [Object Object]라고 나옴
			//alert(error.responseJSON.data.name);
	
			//그래서 자바스크립트 문자열을 JSON.stringfy()로 JSON 문자열로 변환함 
			alert(JSON.stringify(error.responseJSON.data));
		
		}
		 
	})
}


function validationAll(){
	
	
	return true;


}