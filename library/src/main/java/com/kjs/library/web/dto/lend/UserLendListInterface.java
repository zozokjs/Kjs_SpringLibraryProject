package com.kjs.library.web.dto.lend;

import java.text.ParseException;
import java.time.LocalDateTime;

import com.kjs.library.service.common.DateCommonService;
import com.kjs.library.service.common.DomainCommonService;

public interface UserLendListInterface {

	 int getId(); //Lend 테이블의 id
	 int getBookId(); //Book 테이블의 id
	 String getTitle();
	 String getWriter();
	 String getPublish(); //출판사
	 String getTitleImageUrl(); //타이틀 이미지
	 int getBindType(); //매체구분
	 LocalDateTime getCreateDate(); //대출 날짜
	 String getReturnPlanDate(); //반납 예정 날짜
	 String getReturnDate(); //반납 날짜
	 String getExtensionDate(); //연장된 반납 예정 날짜
	 boolean getUseState(); //책 사용 여부(false면 삭제된 것임)
	 
	 /**
	  * 대출 날짜를 xxxx년 x월 x일 형식으로 변경함
	  * LocalDateTime -> String
	  * 2022-06-24 01:45:28.322976 -> 2022년 06월 24일
	  * **/
	 default String getFormattedCreateDate() throws ParseException {
		 return DateCommonService.날짜포맷변경(getCreateDate());
	 }

	 /**
	  * 반납 예정 날짜를 xxxx년 x월 x일 형식으로 변경함
	  * String -> String
	  * 20220624 -> 2022년 06월 24일
	  * **/
	 default String getFormattedReturnPlanDate() {
		 return DateCommonService.날짜포맷변경2(getReturnPlanDate());
	 }

	 /**
	  * 연장된 반납 예정 날짜를 xxxx년 x월 x일 형식으로 변경함
	  * String -> String
	  * 20220624 -> 2022년 06월 24일
	  * **/
	 default String getFormattedExtensionDate() {
		 return DateCommonService.날짜포맷변경2(getExtensionDate());
	 }
	 
	 //int 형태의 제폰형식을 '종이' 형식으로 변경함
	 default String getBindTypeToString() {
		 return DomainCommonService.제본형식toString(getBindType());
	 }
	  
}
