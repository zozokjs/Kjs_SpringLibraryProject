
function boardFreeInforLoad(){
	
	
	//const boardFreeId = $("#boardFreeId").val();
	const boardFreeId =1;
	alert("ad"+boardFreeId);
	
	$.ajax({
		type: "post",
		url : `/api/community/${boardFreeId}/read`,
		dataType : "json"
	}).done(res=>{
		console.log(res);
		
		/*
			res.data.content.forEach((image)=> {
			let storyItem = getStoryItem(image);
			$("#storyList").append(storyItem);
		});*/
		
	}).fail(error=>{
		console.log(error);		
	});
}





//boardFreeInforLoad(); //////////////

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
		
		location.href = `/community/boardFree`; 
		
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
	}else{
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





