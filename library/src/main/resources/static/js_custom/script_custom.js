	
	
	/**원본
	?에 붙은 것 - header-account+header-account-Login-button = popup-custom
	autorize-popup   = popup-content  
	
	 */
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
	