//테이블 추가 카운트
var kdcTrCount = 0;

//추가 버튼 누르면 array에 추가
//삭제 버튼 누르면 array에서 제거...

const addArray = new Array();
const removeArray = new Array();


//책 청구기호 수정 완료 처리
function bookUpdate_kdc(bookId, event) {
	
	event.preventDefault(); //form 태그 액션 방지
	
	const form  =$(`#bookUpdate_kdc`)[0];
	const formData = new FormData(form);
	formData.append("deleteSamebookId",removeArray);
	
	//공백 들어간 것의 samebookId를 찾아야 함.
	var size = $("input[name='kdcCallSign']").length;
	const voidCheckArray = new Array();
	for(i=0;i<size;i++){
		voidCheckArray.push($("input[name='kdcCallSign']")[i].value.trim() == "");
		if( $("input[name='kdcCallSign']")[i].value.trim() == "" ){
		    console.log(' 공백이다 ');
		}
	}
	
	if(voidCheckArray.includes(true)){
		alert("공백은 저장할 수 없습니다. 저장을 원치 않으시면 제거를 눌러주세요.");		
		return;
	}

	for(i=0; i<removeArray.length; i++){
		console.log( "제거 목록 >"+removeArray[i] );
		
	}
	
	$.ajax({
		
		type : "put",
		url : `/saseo/api/${bookId}/update_kdc`,
		data : formData,
		dataType : "json",
		processData : false,
		contentType : false
		
	}).done(res =>{
		
		console.log("성공", res);
		
		alert("청구기호 정보가 수정되었습니다.");
		location.href = `/`;
		
	}).fail(error =>{
		
		if(error.data == null){
			alert(error.responseJSON.message);
			console.log(error.responseJSON.message);
			
		}else{
			console.log("수정 실패", error);	 		
			//이렇게 하니까 알림창에 [Object Object]라고 나옴
			//alert(error.responseJSON.data.name);
	
			//그래서 자바스크립트 문자열을 JSON.stringfy()로 JSON 문자열로 변환함 
			alert(JSON.stringify(error.responseJSON.data));
		
	}
	})
}


//권 수에 따라 청구기호 ''입력'' 테이블 생성 함.
function kdcCallSignLoad(){
	
	const totalAmount = 	$("#book_totalAmount").val(); //권 수

	var kdcCallSignListHtml ="";
	
	const totalAmount_Integer = Number(totalAmount);//String을 Number로 변환.
	
	//기존 권수가 3권이라면 초기값인 0에서 3으로 변경됨
	kdcTrCount = totalAmount_Integer;

	console.log("asd "+kdcTrCount);
	
	//값 체크
	/** 
	console.log("book의 totalAmount 값 : "+Number(totalAmount));
	console.log("청구기호 배열 길이 : "+samebookArray.length);
	for (var i = 0; i < samebookArray.length; i++) { //배열 출력
		console.log("청구기호 배열 값 " + samebookArray[i].kdcCallSign);
	}
	*/
	//값 체크 끝
	
	kdcCallSignListHtml += `<tr>
											    <td >청구기호 기존 값</td>
											    <td >
											    	<div  class="booking-form-i"> 청구기호 수정 값</div>
												    <div style="text-align: -webkit-center;">
														<button onclick ="kdcAdd(event)" class=" booking-complete-btn white-btn-custom" >추가</button>
													</div>
											    </td>
										  	</tr>`
										  	
	//totalAmount 기준으로 반복함								  	
	for(var i = 0; i < totalAmount_Integer; i++){
		const trIdNumber = i + 1;
		
		// 여기부터 ★1열 정의함★
		kdcCallSignListHtml += `<tr id = "trId`+trIdNumber+`">
		 										<td> `+samebookArray[i].kdcCallSign+`</td>  `;
				
		
		// 여기부터 ★2열 정의함★
	    kdcCallSignListHtml +=`      <td >
												    	<!-- 새로 입력하는 곳 -->
												    	<div class="booking-form-i  inputBox-custom"  >
															<div class="input">
																<input type="text"  name="kdcCallSign" class="kdcCallSignClass" placeholder="청구기호를 입력하세요">
															</div>
														</div>
														<div style="text-align: -webkit-center;">
															<button onclick ="kdcRemove(trId`+trIdNumber+`,` +samebookArray[i].samebookId+` )"  class=" booking-complete-btn white-btn-custom" >제거</button>
														</div>
												    	<input type="hidden"  name = "samebookId" value="`+samebookArray[i].samebookId+`" />
												    </td>
										  		</tr>` 		
												    
   	}//end of for
   
	$("#kdcCallSignList").append(kdcCallSignListHtml);

} //end of function
kdcCallSignLoad();


function kdcAdd(event){
	
	event.preventDefault(); //form 태그 액션 방지
	
	kdcTrCount = kdcTrCount + 1;
	
	const kdcTrAddHtml = `<tr id="trId`+kdcTrCount+`">
										  		<td >아직 정해지지 않았습니다</td>  
												<td >
											    	<!-- 새로 입력하는 곳 -->
											    	<div class="booking-form-i  inputBox-custom"  >
														<div class="input">
															<input type="text"  name="kdcCallSign"  placeholder="청구기호를 입력하세요" >	
														</div>
													</div>
											    	<div style="text-align: -webkit-center;">
														<button onclick ="kdcRemove(trId`+kdcTrCount+` )">제거</button>
													</div>
											    </td>
											</tr>`
				
	$("#kdcCallSignList").append(kdcTrAddHtml);
}

function kdcRemove(trId, samebookId){
	$(trId).remove();
	//console.log("제거된 아이디 > "+samebookId);
	removeArray.push(samebookId);
	//console.log("push 이후 길이 >"+removeArray.length)
}