/**

자바스크립트 및 제이쿼리 양식 작성하는 곳

 */
 /*
 window.onload = function() { 
    //alert('페이지 전체가 로드되었습니다.');
  };
*/

//null 검사
var isEmpty = function(value) {
	if (value == "" || value == null || value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length)) {
		return true
	} else {
		return false
	}
};

//textarea 공백 검사
//id가 commentContent 인 textarea의 공백을 제거하여 길이 검사.
commentContent.replace(/\s | /gi, "").length == 0


 //값 들고오기
 //id가 book_totalAmount일 때
const totalAmount = 	$("#book_totalAmount").val(); //권 수

//name이 kdcCallSign일 때
var size = $("input[name='kdcCallSign']").length;
for(i=0;i<size;i++){
	//console.log("type1: "+$("input[name='kdcCallSign']").eq(i).attr("value"));
	console.log("<----------->");
	console.log($("input[name='kdcCallSign']")[i].value.trim() == "");
	console.log($("input[name='kdcCallSign']")[i].value.trim() === "");
	console.log($("input[name='kdcCallSign']")[i].value.length == 0);
	console.log($("input[name='kdcCallSign']")[i].value.length === 0);
	console.log($("input[name='kdcCallSign']")[i].value == null);
	console.log($("input[name='kdcCallSign']")[i].value === null);
	console.log("값 > "+$("input[name='kdcCallSign']")[i].value)
}


//인증번호 전송 버튼 비활성화 처리
/**$('#emailAuthSendButton').attr('disabled');
$('#emailAuthSendButton').removeClass('booking-complete-btn');
$('#emailAuthSendButton').css('color','gray');
$('#emailAuthSendButton').css('border','1px solid gray'); */
				
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


