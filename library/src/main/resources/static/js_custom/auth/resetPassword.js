/**
signup.js

*/


//비번 길이 체크
function checkPasswordLength(){
	const password = $('#password').val();
	if (password.length < 4) {
		console.log("조건 통과");
		$('#div_passwordLengthResult').html('<span style="margin-left:0px; color:red; font-weight:bold;">비번은 4자 이상 입력해주세요</span>');
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
			$('#div_passwordMatchResult').html('<span style="margin-left:0px; color:red; font-weight:bold;">비밀번호 값과 확인 값이 일치하지 않습니다.</span>');
		}else{
			$('#div_passwordMatchResult').html('');
		}
	}
	
}

/** */
// 전체 유효성 체크
function validationAll(){
	
	
	if( !($('#password').val() === $('#confirm-password').val()) ) {
		alert("비밀번호와 비밀번호 확인 값이 일치하지 않습니다.");
		return false;
	}
	
	return true;

}

