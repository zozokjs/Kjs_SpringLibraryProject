//댓글 삭제 검사
function boardFreeCommentDeleteConfirm(commentId){
	if(confirm("정말 삭제 하실거에요? 삭제하면 복구할 수 없습니다.")){
		boardFreeCommentDelete(commentId);
	}
}

//댓글 삭제 처리
function boardFreeCommentDelete(commentId){
	
	const boardFreeId = $("#boardFreeId").val();
	
	
	$.ajax({
		
		type : "PUT",  
		url : `/api/community/${commentId}/boardFreeCommentDelete`,
		dataType : "json",
		contentType : "application/json; charset=utf-8"
		
	}).done(res =>{
		
		alert("삭제 되었습니다");
		
		location.href = `/community/${boardFreeId}/infor`; 
	}).fail(error =>{
		
		if(error.data == null){
			alert(error.responseJSON.message);
			console.log(error.responseJSON.message);
			
		}else{
			console.log("삭제 실패", error);	 		
			alert(JSON.stringify(error.responseJSON.data));
		
		};
		
	})
}




//댓글 등록 검사
function boardFreeCommentRegistrationConfirm(boardFreeId, userId){
	
	const loginId = $("#loginUserId").val();
	const commentContent = $(`#commentContent`).val();
	
	
	if(loginId == "" || loginId == null || loginId == undefined){
		alert("로그인 해주세요");
	}else if(commentContent.replace(/\s | /gi, "").length == 0){
		alert("내용을 넣어주세요");
	}else{
		boardFreeCommentRegistration(boardFreeId, userId);
	}
		//boardFreeCommentRegistration(boardFreeId, userId);
}



//댓글 등록 처리
function boardFreeCommentRegistration(boardFreeId, userId){
	
	const commentData = {
		content : $("#commentContent").val(),
		userId : userId,
		boardFreeId : boardFreeId
		}
		
		//console.log("전송 되는 댓글 정보"+JSON.stringify(commentData));
	$.ajax({
		
		//이 방식으로 보낸다면 백엔드에서 @RequestBody로 받아야 함. 때문에 POST로 보내는 것도 불가능함.
		
		type : "PUT",  
		url : `/api/community/${commentData.boardFreeId}/${userId}/boardFreeCommentRegistration`,
		dataType : "json",
		data: JSON.stringify(commentData),
		//contentType : "application/json; charset=utf-8" //보내는 타입
		contentType : "application/json; charset=utf-8"
		
	}).done(res =>{
		
		alert("등록 되었습니다");
		
		location.href = `/community/${boardFreeId}/infor`; 
	}).fail(error =>{
		
		if(error.data == null){
			alert(error.responseJSON.message);
			console.log(error.responseJSON.message);
			
		}else{
			console.log("등록 실패", error);	 		
			alert(JSON.stringify(error.responseJSON.data));
		
		};
		
	})
	
}

//게시글 삭제 처리
function boardFreeDeleteConfirm(freeBoardId){
	if(confirm("정말 삭제 하실거에요? 삭제하면 복구할 수 없습니다.")){
		boardFreeDelete(freeBoardId);
	}
}

 /*
게시글 삭제 완료 처리 */
function boardFreeDelete(freeBoardId) {
	//event.preventDefault(); //form 태그 액션 방지

	$.ajax({
		
		type : "post",
		url : `/api/community/${freeBoardId}/boardFreeDelete`,
		dataType : "json"
		
	}).done(res =>{
		
		alert("삭제 되었습니다");
		
		location.href = `/community/boardFree`; 
		
	}).fail(error =>{
		
		if(error.data == null){
			alert(error.responseJSON.message);
			console.log(error.responseJSON.message);
			
		}else{
			console.log("삭제 실패", error);	 		
			alert(JSON.stringify(error.responseJSON.data));
		
		};
		
	})
}





