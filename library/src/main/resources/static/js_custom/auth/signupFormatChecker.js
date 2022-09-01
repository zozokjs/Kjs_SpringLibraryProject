/**
signupFormat.js

*/

/** 
정규식 출처 및 참조
https://rateye.tistory.com/468
*/

/** username 양식 확인.
0~20자/ 영문자 및 숫자만 입력
*/
function formatCheckUsername(username){
	
	//네 글자부터 검사함. 조건 통과시 True
	const formatUsername = /^[a-z0-9]+[a-z0-9]{4,19}$/g;
	
	let result = formatUsername.test(username);
	
	//이렇게 변수에 담은 뒤 던져야 제대로된 값이 전달됨.
	//return format_username.test(username);//이렇게 하면 안 됨
	return result;
}


/** password 양식 확인.
8 ~ 24자/ 영문자 및 특수문자 조합
*/
function formatCheckPassword(password){
	
	//여덟 글자부터 검사함. 조건 통과시 True
	//const format_password = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,24}$/;
	const formatPassword = /^(?=.*[a-zA-z])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,24}$/;
	
	let result = formatPassword.test(password);
	
	//console.log("결과 >"+result);
	return result;
}


/** 이메일 양식 확인.
*/
function formatCheckEmail(email){
	
	const formatEmail= /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	
	let result = formatEmail.test(email);
	
	//console.log("결과 >"+result);
	return result;
}


/** 연락처 양식 확인.
*/
function formatCheckPhoneNumber(phoneNumber){
	//첫글자가 반드시 01로 시작하고, 3번째 글자는 6~9사이가 와야 하며, 중간글자는 3~4글자, 끝글자는 4글자.하이픈 없음.
	//const formatPhoneNumber= /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
	
	//앞글자는3글자,  중간글자는 3~4글자, 끝글자는 4글자. 하이픈 없음.
	const formatPhoneNumber= /^\d{3}\d{3,4}\d{4}$/;
	let result = formatPhoneNumber.test(phoneNumber);
	
	//console.log("결과 >"+result);
	return result;
}
