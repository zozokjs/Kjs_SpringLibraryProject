//게시글 삭제 처리
function boardNoticeDeleteConfirm(noticeBoardId){
	if(confirm("정말 삭제 하실거에요? 삭제하면 복구할 수 없습니다.")){
		boardNoticeDelete(noticeBoardId);
	}
}

 /*
게시글 삭제 완료 처리 */
function boardNoticeDelete(noticeBoardId) {
	//event.preventDefault(); //form 태그 액션 방지

	$.ajax({
		
		type : "post",
		url : `/api/community/${noticeBoardId}/boardNoticeDelete`,
		dataType : "json",
		beforeSend : function(xhr) 
        {   /*데이터를 전송하기 전에 헤더에 csrf값 설정*/
			xhr.setRequestHeader(csrfHeaderValue, csrfTokenValue);
        }
		
	}).done(res =>{
		
		alert("삭제 되었습니다");
		
		location.href = `/community/boardNotice`; 
		
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





