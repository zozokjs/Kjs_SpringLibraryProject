


//청구 기호 수정 가능여부 판단
function kdcEditAbleChecker(bookId) {
	$.ajax({
		type : "get",
		url : `/saseo/api/${bookId}/kdcEditAbleCheck`,
		dataType : "json",
		beforeSend : function(xhr) 
        {   /*데이터를 전송하기 전에 헤더에 csrf값 설정*/
			xhr.setRequestHeader(csrfHeaderValue, csrfTokenValue);
        }
	}).done(res =>{
		
		console.log("성공", res);
		if(res.code == 1){
	
			//수정 가능
			location.href = `/saseo/${bookId}/bookUpdate_kdc`;
		}else if(res.code == 0){
			
			//수정 불가
			alert("등록된 청구기호 중 대출 중인 책이 있어 수정할 수 없습니다.");
		}
			
		
	}).fail(error =>{
		
		console.log("실패",error);
		alert("알 수 없는 원인으로 확인을 실패했습니다");
		
	})
}

/**
도서 삭제 여부 확인 */
function bookDeleteConfirm(bookId){
	if(confirm("한 번 삭제하면 복구할 수 없습니다. 정말 삭제하시겠습니까? ")){
		bookDelete(bookId);
	}else{
	}
}


/** 도서 삭제 처리 */
function bookDelete(bookId){
	
	$.ajax({
		type : "post",
		url : `/saseo/api/${bookId}/bookDelete`,
		dataType : "json",
		beforeSend : function(xhr) 
        {   /*데이터를 전송하기 전에 헤더에 csrf값 설정*/
			xhr.setRequestHeader(csrfHeaderValue, csrfTokenValue);
        }
	}).done(res =>{
		
		console.log("성공", res);
		if(res.code == 1){
	
			//수정 가능
			alert("정상적으로 삭제 되었습니다.")
			location.href = `/saseo/bookManage`;
		}else if(res.code == 0){
			
			//수정 불가
			alert("대출 중인 책이 있어 삭제할 수 없습니다.");
		}
			
		
	}).fail(error =>{
		
		console.log("실패",error);
		alert("알 수 없는 원인으로 삭제를 실패했습니다");
		
	})
}