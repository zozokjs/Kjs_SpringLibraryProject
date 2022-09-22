

//회원 수정 완료 처리
function passwordMatchChecker() {
	
	let password =$("#password").val();
	
		//console.log("passwordMatchChecker");
		
	$.ajax({
		type : "post",
		url : `/user/api/${password}/updateBeforeChecker`,
		dataType : "json",
		beforeSend : function(xhr) 
        {   /*데이터를 전송하기 전에 헤더에 csrf값 설정*/
			xhr.setRequestHeader(csrfHeaderValue, csrfTokenValue);
        }
		//data: JSON.stringify(pwData),
		//contentType : "application/x-www-form-urlencoded; charset=utf-8"
		//contentType : "application/json; charset=utf-8"
	}).done(res =>{
		//console.log("성공", res);
		//비번 일치 안 할 때
		if(res.code == 0){
				$('#div_passwordMatchProcessResult').html('<span style="margin-left:20px; color:red; font-weight:bold;">비밀번호가 일치하지 않습니다.</span>');
		}else if(res.code == 1){
			//alert("비번이 일치합니다");
			userUpdateForm.submit();
		}
	}).fail(error =>{
		//console.log(error);
		if(error.data == null){
			//console.log(error.responseText);
		}else{
			console.log(" 확인 실패", error);	 		
			//이렇게 하니까 알림창에 [Object Object]라고 나옴
			//alert(error.responseJSON.data.name);
	
			//그래서 자바스크립트 문자열을 JSON.stringfy()로 JSON 문자열로 변환함 
			alert(JSON.stringify(error.responseJSON.data));
		}
	})
}

/**
function nextPage() {
	let form = document.createElement("form");
	form.action = `/user/userUpdate`;
	form.method = "post";
	var objs;
	objs = document.createElement('input'); // 값이 들어있는 녀석의 형식
	objs.setAttribute('type', 'hidden'); // 값이 들어있는 녀석의 type
	objs.setAttribute('name', "${_csrf.parameterName}"); // 객체이름
	objs.setAttribute('value', "${_csrf.token}"); // 객체이름
	form.appendChild(objs); 
	form.setAttribute('method', 'post'); //get,post 가능
	form.setAttribute('action', "/user/userUpdate"); //보내는 url
	document.body.appendChild(form);
	form.submit();
}
 */




