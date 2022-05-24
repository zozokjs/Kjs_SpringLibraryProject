/**
signup.js

*/

//회원가입 시 아이디 중복검사 실시간
function findByUsernameR() {
	const username = $('#username').val();

	console.log("길이 "+username.length)
	//username의 길이가 0이 아니면서 3보다 클 때 중복 검사함
	if (!(username.length === 0) && (username.length > 3)) {
		
		$.ajax({
			type: "post",
			url: "/api/auth/match/" + username,
			dataType: "json"

		}).done(res => {
			//console.log("응답 성공 " + res.data);
			//console.log("응답 성공 " + res.code);

			if (res.code == 1) {
				//아이디 중복 안 됨 > 사용 가능
				$('#div_username').prepend('<span class="form-bar">사용 가능한 아이디입니다.</span>')
			} else if (res.code == 2) {
				//아이디 중복 됨 > 사용 불가
				$('#div_username').prepend('<span class="form-bar">사용 불가능한 아이디입니다.</span>')
			}

		}).fail(error => {
			console.log("에러 발생 ", error);
			console.log(JSON.stringify(error.responseJSON.data));
			
		}) //end of ajax

	}//end of if

}

