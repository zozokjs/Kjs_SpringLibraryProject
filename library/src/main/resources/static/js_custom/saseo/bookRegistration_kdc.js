
//테이블 추가 카운트
var kdcCallSignTableCount = 0;

//추가 버튼
function kdcAdd(event){
	event.preventDefault(); //액션 방지	
		
	kdcCallSignTableCount = kdcCallSignTableCount + 1;
		//``
	//1줄씩 추가.
	const kdcCallSignAddHtml = `<tr id =   "tableId`+kdcCallSignTableCount+`">
														<td colspan="2" style="text-align:center;">
																<input type="text" name="kdcCallSign" placeholder="청구기호">
																<button type = "button" onclick="kdcRemove(tableId`+kdcCallSignTableCount+`)">제거</button>
														</td>
													</tr>`;
											
	$("#kdcCallSignList").append(kdcCallSignAddHtml);
}

//제거 버튼
function kdcRemove(tableId){
	$(tableId).remove();
}


/*
//권 수에 따라 청구기호 ''입력'' 테이블 생성 함.
function kdcCallSignLoad(){
	const totalAmount = 	$("#book_totalAmount").val(); //권 수
	//alert(totalAmount);
	var kdcCallSignListHtml ="";
	
	const totalAmount_Integer = Number(totalAmount);//String을 Number로 변환.
	
	for(var i = 1; i <= totalAmount_Integer; i++){
		
		//1줄씩 추가.
		kdcCallSignListHtml += `<tr>
													<td class="tg-0pky">
															
													</td>
													<td class="tg-0pky">
															<input type="text" name="kdcCallSign" placeholder="청구기호">
													</td>
												</tr>`
	}
	$("#kdcCallSignList").append(kdcCallSignListHtml);
}

kdcCallSignLoad();
*/


//청구기호 등록 완료 처리
