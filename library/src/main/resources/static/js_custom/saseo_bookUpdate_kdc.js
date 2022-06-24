

//책 청구기호 수정 완료 처리
function bookUpdate_kdc(bookId, event) {
	
	event.preventDefault(); //form 태그 액션 방지
	
	const form  =$(`#bookUpdate_kdc`)[0];
	const formData = new FormData(form);

	
	$.ajax({
		
		type : "put",
		url : `/saseo/api/${bookId}/update_kdc`,
		data : formData,
		dataType : "json",
		processData : false,
		contentType : false
		
	}).done(res =>{
		
		console.log("성공", res);
		
		alert("청구기호 정보가 수정되었습니당");
		location.href = `/`;
		
	}).fail(error =>{
		
		console.log("실패",error);
		//alert("stop");
		
	})
}


//권 수에 따라 청구기호 ''입력'' 테이블 생성 함.
function kdcCallSignLoad(){
	
	const volume = 	$("#book_volume").val(); //권 수

	var kdcCallSignListHtml ="";
	
	const volume_Integer = Number(volume);//String을 Number로 변환.
	
	
	/*
	값 체크
	
	
	console.log("book의 volume 값 : "+Number(volume));
	console.log("청구기호 배열 길이 : "+samebookArray.length);
	for (var i = 0; i < samebookArray.length; i++) { //배열 출력
		console.log("청구기호 배열 값 " + samebookArray[i].kdcCallSign);
	}

	값 체크 끝
	 */
	 
	//volume은 2인데,
	//samebookArray 길이가 1일 때 값을 불러오지 못함	 
	//samebookArray는 1개 밖에 없으니까 2번째를 찾지 못하는 것임		
	
	
	
	
	kdcCallSignListHtml += `<tr>
											    <td class="tg-0pky">기존</td>
											    <td class="tg-0pky">수정</td>
										  	</tr>
										  	<tr>`
	//volume 기준으로 반복함								  	
	for(var i = 0; i < volume_Integer; i++){
		
		// 여기부터 ★1열 정의함★
		//값이 없을 때
		if(samebookArray.length == 0){
			kdcCallSignListHtml += ` <td class="tg-0pky">
											 			<input type="text"  name=""  value="등록된 청구기호 없음" readonly="readonly">
											 		</td>    `
	    }else{
			
			if( samebookArray[i] === undefined ){
			//청구기호가 정해지지 않았을 때
			kdcCallSignListHtml += ` <td class="tg-0pky">
												  		<input type="text"  name="" value="아직 정해지지 않았습니다" readonly="readonly">
												    </td>  `
				
			}else{
			//값이 있을 때 청구기호 표시
			kdcCallSignListHtml += ` <td class="tg-0pky">
												  		<input type="text"  name="" value="  `+samebookArray[i].kdcCallSign+`  " readonly="readonly">
												    </td>  `
				
			}
						
			
		}//end of else
		
		
		// 여기부터 ★2열 정의함★
	    kdcCallSignListHtml +=`      <td class="tg-0pky">
												    	<!-- 새로 입력하는 곳 -->
												    	<input type="text"  name="kdcCallSign"  placeholder="청구기호를 입력하세용" >													    
												    </td>
										  		</tr>` 		
												    
   	}//end of for
   
	$("#kdcCallSignList").append(kdcCallSignListHtml);

} //end of function
kdcCallSignLoad();