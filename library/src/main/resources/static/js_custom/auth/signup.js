/**
signup.js

*/

//아이디 중복 여부
let idDuplicateStatus = false;

//이메일 양식 준수 여부(테스트할 때만 true)
let  emailFormatCheckResult = true;

//연락처 양식 준수 여부(테스트할 때만 true)
let  phoneNumberFormatCheckResult = true;

//전역 변수
//이메일 전송 시간 저장
let  currentTime = null;

//만료시간
let  terminateTime = null;

//이메일 인증 번호
let  authCode = null;

//이메일 인증 성공 여부(테스트할 때만 true)
let  emailAuthenticationSuccess = true;


/**경고표시 붙여넣기 */
function warningHTMLappend(id, content){
	$("#"+id+"").html("<span class='warningBlock'>" +content+"</span>" );
}

//회원가입 시 아이디 중복검사 실시간
function findByUsernameRealtime() {
	let  username = $('#username').val();

	let  checkResult = formatCheckUsername(username) ;
	
	if( checkResult == false ){
	
		warningHTMLappend("div_usernameResult", "5~20자리의 영문소문자와 숫자 조합으로만 가능합니다");
		idDuplicateStatus = false;
	}else{
		
		$.ajax({
			type: "post",
			url: "/api/auth/match/" + username,
			dataType: "json"

		}).done(res => {
			
			console.log("응답 성공 " + res.data);
			
			if (res.code == 1) {
				//아이디 중복 안 됨 > 사용 가능
				warningHTMLappend("div_usernameResult", "사용 가능한 아이디입니다");
				idDuplicateStatus = true;
			} else if (res.code == 2) {
				//아이디 중복 됨 > 사용 불가
				warningHTMLappend("div_usernameResult", "사용 불가능한 아이디입니다");
				idDuplicateStatus = false;
			}
			
		}).fail(error => {
			
			console.log("에러 발생 ", error);
			console.log(JSON.stringify(error.responseJSON.data));
		}) //end of ajax	
	}

}



//비번 길이 및 양식 체크
function checkPasswordFormat(){
	let  password = $('#password').val();
	
	let  checkResult = formatCheckPassword(password);
	
	console.log("입력 문자 "+password);
	
	if (checkResult == false) {
		warningHTMLappend("div_passwordFormatResult", "8자 이상의 영문소문자와 특수문자가 반드시 조합되어야 합니다");
	}else{
		warningHTMLappend("div_passwordFormatResult", " ");
	}
}
	
	
//비번과 비번확인 일치여부 실시간
function checkPasswordMatch(){
	
	let  password = $('#password').val();
	let  confirm_password = $('#confirm-password').val();
	
	if ( confirm_password.length > 0 ) {
		
		if( !(password === confirm_password) ) {
			warningHTMLappend("div_passwordMatchResult", "비밀번호 값과 확인 값이 일치하지 않습니다.");
		}else{
			warningHTMLappend("div_passwordMatchResult", " ");
		}
	}
	
}

//이메일 양식 확인 실시간
function checkEmailFormat(){
	
	let  email = $('#email').val();
	let  checkResult = formatCheckEmail(email);
	
	if (checkResult == false) {
		warningHTMLappend("div_emailFormatResult", "이메일 주소를 다시 확인해주세요");
		emailFormatCheckResult = false;
	}else{
		warningHTMLappend("div_emailFormatResult", " ");
		emailFormatCheckResult = true;
	}
	
}

//회원가입 과정 중 이메일 인증 관련
function emailAuthenticationSending_Before(event){
	event.preventDefault(); //form 태그 액션 방지
	
	if( emailFormatCheckResult === false ){
		alert("이메일 주소 양식이 맞지 않아 전송할 수 없습니다.")
	}else{
		
		emailAuthenticationSending();
	}
}	


//이메일 주소로 이메일 전송
function emailAuthenticationSending(){
	
		let  emailAddress = $('#email').val();
	
		$.ajax({
			type: "post",
			url: "/api/auth/emailAuthenticationSending/" + emailAddress,
			dataType: "json"

		}).done(res => {
			
			if (res.code == 1) {
				alert("인증 번호가 전송 되었습니다.");
				
				//안내문 부착
				warningHTMLappend("div_emailFormatResult", "10분 이내에 인증해주세요. 주소가 올바르지 않으면 도착하지 않습니다.");
				
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

/***
이메일 전송 성공 시 인증 번호 입력할 HTML 추가
 */
function emailAuthenticationSending_HTMLappend(){
	const appendHTML = 
		`
							<th>이메일 인증번호</th>
							<td colspan="" style="width:50%;">
								<div class="booking-form-i inputBox-custom" >
									<div class="input">
										<input type="text"  id="authCode2"  required=""  >
									</div>
								</div>
							</td>
							<td>
								<div id="div_emailAuthenticationSending_After"style="text-align: -webkit-center;">
									<button onclick ="emailAuthenticationSending_After()" class=" booking-complete-btn white-btn-short-custom" >확인</button>
								</div>
							</td>	`;
		
		$("#div_emailAuthentication").html(appendHTML);
}


//이메일 인증 번호 가져와서 인증 확인.
function emailAuthenticationSending_After(){
	
	//사용자가 입력한 인증 코드를 가져옴
	let  authCode2 = $('#authCode2').val();
		
	//확인 버튼 눌렀을 때의 시간 가져와서 만료 시간보다 크다면 authCode를 초기화
	let  afterTime = new Date();
	
	//console.log("인증번호 입력 시 현재 시간 : "+afterTime);
	//console.log("기존 만료 시간 : "+terminateTime);
	
	if(afterTime > terminateTime){
		alert("만료 시간이 지났으므로 인증할 수 없습니다. 페이지를 새로고침 후 다시 시도해주세요.");
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
		$('#authCode2').attr('disabled',true);

		
	}else if(authCode != authCode2) {
			alert("인증 코드가 일치하지 않습니다. 다시 확인해주세요");
	}
		
	//console.log("사용자가 입력한 인증 번호: "+authCode2);
	//console.log("백엔드에서 가져온 인증 번호: "+authCode);
	
}



//연락처 양식 확인 실시간
function checkPhoneNumberFormat(){
	
	let  phoneNumber = $('#phoneNumber').val();
	
	let  checkResult = formatCheckPhoneNumber(phoneNumber);
	
	if (checkResult == false) {
		warningHTMLappend("div_phoneNumberFormatResult", "하이픈(-)을 제외한 올바른 연락처를 입력해주세요.");
		phoneNumberFormatCheckResult = false;
	}else{
		warningHTMLappend("div_phoneNumberFormatResult", " ");
		phoneNumberFormatCheckResult = true;
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
	
	
	if(idDuplicateStatus != true){
		alert("사용 불가능한 아이디 입니다.");
		return false;
	}
	//비밀번호와(테스트할 때만 true)
	if(  ($('#password').val() === $('#confirm-password').val()) == false  ) {
		alert("비밀번호와 비밀번호 확인 값이 일치하지 않습니다.");
		return true;
	}
	
	if(emailAuthenticationSuccess != true){
		alert("이메일이 인증되지 않았습니다.");
		return false;
	}
	
	//연락처(테스트할 때만 true)
	if(phoneNumberFormatCheckResult != true){
		alert("연락처 양식이 잘못되었습니다.");
		return true;
	}
	//alert("프론트 통과");
	return true;

	
}

