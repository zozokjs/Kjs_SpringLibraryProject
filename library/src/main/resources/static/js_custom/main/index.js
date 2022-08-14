
 function bookSearch(){
	
	//alert("ㅁㅇㄹ");
	//window.location.href = 'http://www.naver.com/';
	
	//검색어
	let searchKey = $("#searchKeyword").val();
	
	window.location.href = `resource/${searchKey}/bookSearch`;
	
	/**
	화면 이동(controller) // /resource/bookSearch
	검색 (api controller)
	결과 표시 (js)
	
	화면 이동한 다음, ajax 메소드를 실행시켜야 함
	
	 */
}



