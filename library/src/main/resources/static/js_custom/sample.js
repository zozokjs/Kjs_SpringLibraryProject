/**


자바스크립트 및 제이쿼리 양식 작성하는 곳

 */
 
 //값 들고오기
const volume = 	$("#book_volume").val(); //권 수


//AJAX API 처리
function userUpdate(userId, event) {
	
	event.preventDefault(); //form 태그 액션 방지
	
	//id가 userUpdate일 때 form 내부 값 가져오기
	let data =$(`#userUpdate`).serialize();

	console.log(data);
	
	$.ajax({
		
		type : "put",
		url : `/user/api/${userId}/updater`,
		data : data,
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8"
		
	}).done(res =>{
		
		console.log("성공", res);
		
		alert("회원 정보가 수정되었습니당");
		location.href = `/`;
		
	}).fail(error =>{
		console.log(error);
		
		if(error.data == null){
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


//속성 추가
//ex) <input type="text" data-type="danger">
$('#username').attr('data-type', 'danger');

//속성 앞에 추가
$('#div_username').prepend('<span class="error-message">Please enter numbers only!</span>')
$('#username').after('<span class="error-message">Please enter numbers only!</span>')



//id가 div_username에서 input 태그의 이벤트 발생 시
$('#div_username').on('input', function(evt) {
	var value = evt.target.value

	if (value.length === 0) {
		evt.target.className = ''
		return
	}

	// 숫자일 때
	if ($.isNumeric(value)) {

		//evt의 부모 노드의 클래스 변경
		evt.target.parentNode.className = 'form-group form-primary'

		//evt가 발생된 것 이후에 추가 
		evt.target.after('<label class="float-label">form-danger</label>')


	} else {
		evt.target.parentNode.className = 'form-group form-danger form-static-label'
  }
})


