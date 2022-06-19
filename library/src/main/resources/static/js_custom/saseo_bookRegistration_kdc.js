/*
 window.onload = function() { 
    //alert('페이지 전체가 로드되었습니다.');
  };
*/


//권 수에 따라 청구기호 ''입력'' 테이블 생성 함.
function kdcCallSignLoad(){
	const volume = 	$("#book_volume").val(); //권 수
	//alert(volume);
	var kdcCallSignListHtml ="";
	
	const volume_Integer = Number(volume);//String을 Number로 변환.
	
	for(var i = 1; i <= volume_Integer; i++){
		
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
    


//청구기호 등록 완료 처리
