 /**
모달 창 - ISBN 정보
 */
$("#modal_bookISBNInformation").on("click", function(event) {
	
	var dialog = BootstrapModalWrapperFactory.createModal({
		title: "ISBN 참고",
		message: modal_bookISBNInformationHTML,
		closable: false,
		closeByBackdrop: true,
		centered: true,
		buttons: [
			{
				label: "Close",
				cssClass: "btn btn-secondary",
				action: function(button, buttonData, originalEvent) {
					return this.hide();
				}
			}
		]
	});
	dialog.show();
});

/**
모달 창 - ISBN 정보 HTML
 */
const modal_bookISBNInformationHTML =`
		<table class="table-a light">
			<tbody>
				<tr>
					<th>978</th>
					<th>90/12</th>
					<th>954321</th>
					<th>0</th>
					<th>9</th>
				</tr>
				<tr>
					<td>접두부</td>
					<td>국제식별번호</td>
					<td>발행자번호</td>
					<td>서명식별번호</td>
					<td>체크기호</td>
				</tr>
			</tbody>
		</table>
		<table class="table-a light">
			<tbody>
				<tr>
					<th>분류</th>
					<th>ISBN</th>
					<th>설명</th>
				</tr>
				<tr>
					<td>접두부</td>
					<td>978또는979</td>
					<td>국제상품코드협회가 부여함</td>
				</tr>
				<tr>
					<td>국제식별번호</td>
					<td>90 또는 12</td>
					<td>국가별 식별번호. 접두가 978이면 90. 979면 12사용</td>
				</tr>
				<tr>
					<td>발행자번호</td>
					<td>954321 또는 95432</td>
					<td>국가문헌번호센터에서 부여하는 출판사 번호. 5~6자리</td>
				</tr>
				<tr>
					<td>서명식별번호</td>
					<td>1 또는 12</td>
					<td>출판사가 부여하는 번호. 발행자번호 + 서명식별번호 = 7자리</td>
				</tr>
				<tr>
					<td>체크기호</td>
					<td>9</td>
					<td>ISBN의 정확성 점검을 위한 번호 </td>
				</tr>
			</tbody>
			<p style="font-size:13px; text-align:right;">가로x세로x두께(책 제목을 정면으로 마주 봤을 때)</p>
		</table>
		
		
		`


 /**
모달 창 - 도서 등록 시 크기(판형)에 대한 참고
 */
$("#modal_bookFormInformation").on("click", function(event) {
	
	var dialog = BootstrapModalWrapperFactory.createModal({
		title: "도서 크기에 대한 참고",
		message: modal_bookFormInformationHTML,
		closable: false,
		closeByBackdrop: true,
		centered: true,
		buttons: [
			{
				label: "Close",
				cssClass: "btn btn-secondary",
				action: function(button, buttonData, originalEvent) {
					return this.hide();
				}
			}
		]
	});
	dialog.show();
});

/**
모달 창 - 도서 등록 시 크기(판형)에 대한 참고 HTML
 */
const modal_bookFormInformationHTML =`
		<table class="table-a light">
			<tbody>
				<tr>
					<th>일반</th>
					<td>153x224x30</td>
				</tr>
				<tr>
					<th>만화책</th>
					<td>128x180x36x15</td>
				</tr>
				<tr>
					<th>문제집</th>
					<td>188x250x36</td>
				</tr>
				<tr>
					<td  colspan="2">크기를 산정할 수 없는 경우 0이나 공백 표기 바랍니다.</td>	
				</tr>
			</tbody>
			<p style="font-size:13px; text-align:right;">가로x세로x두께(책 제목을 정면으로 마주 봤을 때)</p>
		</table>`
		
		
 /**
모달 창 - 제국력과 서력 환산식
 */
$("#modal_yearConversionFomular").on("click", function(event) {
	
	const longContent = "<p>제국력 = 서력 + 3512년 15일<br>예) 서력 기준 오늘 날짜가 2022년 7월 4일인 경우<br>제국력 5532년 7월 19일.</p>";
										
	var dialog = BootstrapModalWrapperFactory.createModal({
		title: "환산식",
		message: longContent,
		closable: false,
		closeByBackdrop: true,
		centered: true,
		buttons: [
			{
				label: "Close",
				cssClass: "btn btn-secondary",
				action: function(button, buttonData, originalEvent) {
					return this.hide();
				}
			}
		]
	});
	dialog.show();
});


/**
모달 창 - 언어 정보(종족과 사용 언어)
 */
$("#modal_languageTable").on("click", function(event) {
	
		var dialog = BootstrapModalWrapperFactory.createModal({
		//title: "환산식",
		message: modal_languageTableHTML,
		closable: false,
		closeByBackdrop: true,
		centered: true,
		buttons: [
			{
				label: "Close",
				cssClass: "btn btn-secondary",
				action: function(button, buttonData, originalEvent) {
					return this.hide();
				}
			}
		]
	});
	dialog.show();
});

/**
모달 창 - 언어 정보(종족과 사용 언어) HTML
 */
const modal_languageTableHTML =`
		<table class="table-a light">
			<tbody>
				<tr>
					<th>인간</th>
					<td>제국어, 파디스어</td>
				</tr>
				<tr>
					<th>Elf</th>
					<td>리가</td>
				</tr>
				<tr>
					<th>Orck</th>
					<td>튀플러</td>
				</tr>
				<tr>
					<th>Fairy</th>
					<td>이아시</td>
				</tr>
				<tr>
					<th>Dwarf</th>
					<td>퀘르탈(퀠탈)</td>
				</tr>
				<tr>
					<td  colspan="2">그 외는 자유롭게 기입하되, <br>제국에 미등록된 언어 기입 시 공백으로 수정됨을 주의바랍니다. </td>	
				</tr>
			</tbody>
			<p style="font-size:13px; text-align:right;">순서는 공식 집계된 인구수 순입니다(제국력 5530년 12월)</p>
		</table>`




/**팝업창 커스텀 */
//버튼 누르면 팝업창 뜨는 효과
$('.popup_custom').on('click',function(){
	
	$('.overlay2').fadeIn(function(){
		$('.popup_content').animate({top: '50%'}, 300).find('input:text').eq('0').focus();
	});
	return false;
});

//팝업 외곽 누르면 없어지는 효과
$('.overlay2').on('click',function(){
	
	$('.popup_content').animate({top: '-300px'}, 300, function(){
		$('.overlay2').fadeOut();	
	});
});

$('.autorize-tab-content').eq('0').css('display','block');

$('.autorize-tabs a').on('click',function(){
	//x 버튼 누르면 팝업 창 없어지는 효과
	if ( $(this).is('.autorize-close') ) {
		$('.popup_content').animate({top: '-300px'}, 300, function(){
			$('.overlay2').fadeOut();	
		});
	} else {
		var $index = $(this).index();
		$('.autorize-tabs a').removeClass('current').eq($index).addClass('current');
		$('.autorize-tab-content').hide().eq($index).fadeIn().find('input:text').eq('0').focus();
	}
	return false;
});
	