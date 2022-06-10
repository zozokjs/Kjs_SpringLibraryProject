

// 책 수정 화면에서 타이틀 이미지 실시간 변경 함수.
function imageChoose(obj) {
	let f = obj.files[0];

	if (!f.type.match("image.*")) {
		alert("이미지를 등록해야 합니다.");
		return;
	}

	let reader = new FileReader();
	reader.onload = (e) => {
		$("#imageUploadPreview").attr("src", e.target.result);
	}
	reader.readAsDataURL(f); // 이 코드 실행시 reader.onload 실행됨.
}



//책 수정 완료 처리
function bookUpdate(bookId, event) {
	
	event.preventDefault(); //form 태그 액션 방지
	
	const form  =$(`#bookUpdate`)[0];
	const formData = new FormData(form);

	
	$.ajax({
		
		type : "put",
		url : `/saseo/api/${bookId}/update`,
		data : formData,
		dataType : "json",
		processData : false,
		contentType : false
		//contentType : "application/x-www-form-urlencoded; charset=utf-8",
		
	}).done(res =>{
		
		console.log("성공", res);
		
		alert("도서 정보가 수정되었습니당");
		location.href = `/`;
		
	}).fail(error =>{
		
		console.log("실패",error);
		alert("stop");
		
	})
}