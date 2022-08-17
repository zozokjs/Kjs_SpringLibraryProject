/**
signup.js

*/
//아이디 중복 여부
var idDuplicateStatus = false;

//회원가입 시 아이디 중복검사 실시간
function findByUsernameRealtime() {
	const username = $('#username').val();

	console.log("길이 "+username.length)
	//username의 길이가 0이 아니면서 3보다 클 때 중복 검사함
	if (!(username.length === 0) && (username.length > 3)) {
		
		$.ajax({
			type: "post",
			url: "/api/auth/match/" + username,
			dataType: "json"

		}).done(res => {
			console.log("응답 성공 " + res.data);
			console.log("응답 성공 " + res.code);

			if (res.code == 1) {
				//아이디 중복 안 됨 > 사용 가능
				$('#div_usernameResult').html('<span style="margin-left:20px; color:blue; font-weight:bold;">사용 가능한 아이디입니다.</span>');
				idDuplicateStatus = true;
			} else if (res.code == 2) {
				//아이디 중복 됨 > 사용 불가
				$('#div_usernameResult').html('<span style="margin-left:20px; color:red; font-weight:bold;">사용 불가능한 아이디입니다.</span>');
				idDuplicateStatus = false;
				
			}

		}).fail(error => {
			console.log("에러 발생 ", error);
			console.log(JSON.stringify(error.responseJSON.data));
			
		}) //end of ajax

	}else if(username.length < 4){
		$('#div_usernameResult').html('<span style="margin-left:20px; color:red; font-weight:bold;">아이디는 4자 이상 입력해주세요</span>');
	}//end of if

}


//비번 길이 체크
function checkPasswordLength(){
	const password = $('#password').val();
	if (password.length < 4) {
		console.log("조건 통과");
		$('#div_passwordLengthResult').html('<span style="margin-left:20px; color:red; font-weight:bold;">비번은 4자 이상 입력해주세요</span>');
	}else{
		$('#div_passwordLengthResult').html('');
	}
}
	
	
//비번과 비번확인 일치여부 실시간
function checkPasswordMatch(){
	
	const password = $('#password').val();
	const confirm_password = $('#confirm-password').val();
	
	if (!(confirm_password.length === 0) && (confirm_password.length > 3)) {
		
		if( !(password === confirm_password) ) {
			$('#div_passwordMatchResult').html('<span style="margin-left:20px; color:red; font-weight:bold;">비밀번호 값과 확인 값이 일치하지 않습니다.</span>');
		}else{
			$('#div_passwordMatchResult').html('');
		}
	}
	
}

/** */
//회원가입 시 전체 유효성 체크
function validationAll(){
	
	/*
	//alert("작동됨");
	//`회원가입 처리할 때
	//아이디 길이, 중복여부, 비번 길이, 비번 일치 여부, 이메일?
	if($('#username').val().length === 0){
		alert("아이디를 입력해주세요");
		return false;
	}else if( !($('#username').val().length > 3) ){
		alert("아이디는 4자리 이상 입력해주세요");
		return false;
	}else if( !($('#username').val().length < 21) ){
		alert("아이디가 너무 길어요.");
		return false;		
	}else if(!idDuplicateStatus){
		alert("아이디가 중복됩니다. 다른 아이디를 입력해주세요");
		return false;
	}else if( $('#password').val().length === 0 ){
		alert("비밀번호를 입력해주세요.");
		return false;
	}else if( !($('#password').val().length > 3) ){
		alert("비밀번호는 4자리 이상 입력해주세요");
		return false;
	}else 
	
	*/
	if( !($('#password').val() === $('#confirm-password').val()) ) {
		alert("비밀번호와 비밀번호 확인 값이 일치하지 않습니다.");
		return false;
	}
	//alert("프론트 통과");
	return true;

	
}

