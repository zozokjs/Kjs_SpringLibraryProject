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

//회원가입 과정 중 이메일 인증 관련
function emailAuthenticationSending_Before(event){
	event.preventDefault(); //form 태그 액션 방지
	
	if( $('#email').val().length === 0 ){
		alert("이메일주소를 입력하지 않아 전송할 수 없습니다.")
	}else{
	
		emailAuthenticationSending();
	
	}
}


//전역 변수
//이메일 전송 시간 저장
let currentTime = null;

//만료시간
let terminateTime = null;

//이메일 인증 번호
let authCode = null;

//이메일 인증 성공 여부
let emailAuthenticationSuccess = false;

//이메일 주소로 이메일 전송
function emailAuthenticationSending(){
	
		const emailAddress = $('#email').val();
	
		$.ajax({
			type: "post",
			url: "/api/auth/emailAuthenticationSending/" + emailAddress,
			dataType: "json"

		}).done(res => {
			
			if (res.code == 1) {
				alert("인증 번호가 전송 되었습니다.");
				
				//인증번호 전송 버튼 비활성화 처리
				/**$('#emailAuthSendButton').attr('disabled');
				$('#emailAuthSendButton').removeClass('booking-complete-btn');
				$('#emailAuthSendButton').css('color','gray');
				$('#emailAuthSendButton').css('border','1px solid gray'); */
				//인증번호 전송 버튼 제거
				$('#div_emailAuthSendButton').html("");
				
				//이메일 입력 창 비활성화
				$('#email').attr('readonly',true);
					
				//인증 번호 입력할 HTML 확장
				emailAuthenticationSending_HTMLappend();
				
				//인증 번호 세팅
				authCode = res.data;
				//console.log("인증 번호 :" +authCode);
				
				//이메일 전송 시간 세팅
				currentTime = new Date();
				
				//만료시간 세팅(+10분)
				terminateTime = new Date();
				terminateTime.setMinutes(currentTime.getMinutes() + 10); 
				
				//console.log("현재 시간 : "+currentTime);
				//console.log("만료 시간: "+terminateTime);

			}
		}).fail(error => {
			alert("인증번호 전송에 실패 했습니다. 메일 주소를 확인해주세요.");
			//console.log("에러 발생 ", error);
			//console.log(JSON.stringify(error.responseJSON.data));
			
		}) //end of ajax
}


function emailAuthenticationSending_HTMLappend(){
	const appendHTML = 
		`
							<th>이메일 인증번호</th>
							<td>
							<div class="booking-form-i inputBox-custom"  style="width:60%;">
								<div class="input">
									<input type="text"  id="authCode2"  required=""  >
								</div>
							</div>
							<div id="div_emailAuthenticationSending_After"style="text-align: -webkit-center;">
								<button onclick ="emailAuthenticationSending_After()" class=" booking-complete-btn white-btn-custom" >확인</button>
							</div>
						</td>	`;
		
		$("#div_emailAuthentication").html(appendHTML);
}


//이메일 인증 번호 가져와서 인증 확인.
function emailAuthenticationSending_After(){
	
	//사용자가 입력한 인증 코드를 가져옴
	const authCode2 = $('#authCode2').val();
		
	//확인 버튼 눌렀을 때의 시간 가져와서 만료 시간보다 크다면 authCode를 초기화
	const afterTime = new Date();
	
	//console.log("인증번호 입력 시 현재 시간 : "+afterTime);
	//console.log("기존 만료 시간 : "+terminateTime);
	
	if(afterTime > terminateTime){
		alert("만료 시간 지났으므로 인증할 수 없습니다. 페이지를 새로고침 후 다시 시도해주세요.");
		//인증 코드 초기화
		authCode = 0;
	}else if(authCode == authCode2){
		alert("인증 되었습니다.");
		
		//인증 여부 True 세팅
		emailAuthenticationSuccess = true;
		
		//확인 버튼 제거
		$('#div_emailAuthenticationSending_After').html("");
		
		//인증 번호 입력 창 비활성화
		$('#authCode2').attr('readonly',true);
		

		
	}else if(authCode != authCode2) {
			alert("인증 코드가 일치하지 않습니다. 다시 확인해주세요");
	}
		
	//console.log("사용자가 입력한 인증 번호: "+authCode2);
	//console.log("백엔드에서 가져온 인증 번호: "+authCode);
	
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
	
	if(emailAuthenticationSuccess != true){
		alert("이메일이 인증되지 않았습니다.");
		return false;
	}
	
	if( !($('#password').val() === $('#confirm-password').val()) ) {
		alert("비밀번호와 비밀번호 확인 값이 일치하지 않습니다.");
		return false;
	}
	//alert("프론트 통과");
	return true;

	
}

