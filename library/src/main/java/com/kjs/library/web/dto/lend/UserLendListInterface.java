package com.kjs.library.web.dto.lend;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

import com.kjs.library.service.CommonService;

public interface UserLendListInterface {

	 int getId();
	 String getTitle();
	 String getWriter();
	 String getPublish(); //출판사
	 int getBindType(); //매체구분
	 LocalDateTime getCreateDate();
	 String getReturnPlanDate();
	 
	 /**
	  * 대출 날짜를 xxxx년 x월 x일 형식으로 변경함
	  * LocalDateTime -> String
	  * 2022-06-24 01:45:28.322976 -> 2022년 06월 24일
	  * **/
	 default String getFormattedCreateDate() throws ParseException {
		 return CommonService.날짜포맷변경(getCreateDate());
	 }

	 /**
	  * 반납 예정 날짜를 xxxx년 x월 x일 형식으로 변경함
	  * String -> String
	  * 20220624 -> 2022년 06월 24일
	  * **/
	 default String getFormattedReturnPlanDate() {
		 return CommonService.날짜포맷변경2(getReturnPlanDate());
	 }

	 //int 형태의 제폰형식을 '종이' 형식으로 변경함
	 default String getBindTypeToString() {
		 return CommonService.제본형식toString(getBindType());
	 }
	  
}
