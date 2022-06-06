// (3) 유저 프로파일 사진 변경 (완)
function profileImageUpload(pageUserId, principalId) {
	
	//console.log("pageUserId : ", pageUserId);
	//console.log("principalId : " ,principalId);
	
	//로그인한 사람과 프로필페이지주인과 같지 않다면
	if(pageUserId != principalId){
		alert("프로필 사진을 수정할 수 없는 유저입니다.");
		return;
	}

}