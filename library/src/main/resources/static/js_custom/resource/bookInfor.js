

 /*
 도서 대출 완료 처리 */
function bookLending(bookId, event) {
	event.preventDefault(); //form 태그 액션 방지
	
	//const bookIdNumber = Number(bookId);//String을 Number로 변환.
	//console.log(typeof(bookId));
	//console.log(typeof(bookIdNumber));
	
	$.ajax({
		
		type : "post",
		url : `/api/resource/${bookId}/bookLending`,
		dataType : "json",
		beforeSend : function(xhr) 
        {   /*데이터를 전송하기 전에 헤더에 csrf값 설정*/
			xhr.setRequestHeader(csrfHeaderValue, csrfTokenValue);
        }
		
	}).done(res =>{
		
		console.log("성공", res);
		
		alert("빌리기 성공");
		alert("대출 목록으로 이동하시겠습니까?")
		
		//내 서재의 대여 목록으로 이동되어야 함
		location.href = `/user/myLibrary/`; 
		
	}).fail(error =>{
		console.log(error);
		console.log(error.responseJSON);
		
		if(error.data == null){
			alert(error.responseJSON.message);
			console.log(error.responseJSON.message);
			
		}else{
			console.log(" 빌리기 실패", error);	 		
			//이렇게 하니까 알림창에 [Object Object]라고 나옴
			//alert(error.responseJSON.data.name);
	
			//그래서 자바스크립트 문자열을 JSON.stringfy()로 JSON 문자열로 변환함 
			alert(JSON.stringify(error.responseJSON.data));
		
		};
		
		
		
		
	})
}





