 
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
	