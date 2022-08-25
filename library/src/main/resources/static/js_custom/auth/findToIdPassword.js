/**
*/

//아이디 중복 여부
var idDuplicateStatus = false;

//이메일 주소 받아서 아이디 찾기
function findToId() {
	const idEmail = $('#idEmail').val();
	
	if(idEmail.length == 0 || idEmail.length < 5) {
		alert("이메일 주소룰 입력해주세요")
	}
	
	else if (idEmail.length > 0) {
		
		$.ajax({
			type: "post",
			url: "/api/auth/findId/" + idEmail,
			dataType: "json"

		}).done(res => {
			console.log("응답 성공 " + res.data);
			console.log("응답 성공 " + res.code);

			if (res.code == 1) {
			
				alert("이메일이 전송되었습니다.");
				location.href = `/auth/signinOtherPage`;
			} else if (res.code == 2) {
				alert("이메일이 2.")
			}

		}).fail(error => {
			console.log("에러 발생 ", error);
			console.log(JSON.stringify(error.responseJSON.data));
			
		}) //end of ajax

	}

}


function findToPassword(){
	
	console.log("aa");
	
	const passwordUsername = $('#passwordUsername').val();
	const passwordEmail = $('#passwordEmail').val();

	console.log("bb");
	
	
	if(passwordUsername.length == 0 || passwordUsername.length < 5) 
	{
		alert("아이디를 입력해주세요")
	}else if(passwordEmail.length == 0 || passwordEmail.length < 5) 
	{
		alert("이메일을 입력해주세요")
	}else if ( (passwordUsername.length > 4) &&  (passwordEmail.length > 5)) 
	{
		console.log("cc");

		
		$.ajax({
			type: "post",
			url: "/api/auth/resetPassword/" + passwordUsername+"/"+passwordEmail,
			dataType: "json"

		}).done(res => {
			console.log("응답 성공 " + res.data);
			console.log("응답 성공 " + res.code);

			if (res.code == 1) {
			
				alert("메일이 전송 되었습니다. 정보가 일치하지 않는 경우 도착하지 않을 수 있습니다.")
			} 

		}).fail(error => {
			console.log("에러 발생 ", error);
			console.log(JSON.stringify(error.responseJSON.data));
			
		}) //end of ajax

	}
}


