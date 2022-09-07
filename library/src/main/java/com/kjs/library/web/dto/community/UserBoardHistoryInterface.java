package com.kjs.library.web.dto.community;

import java.text.ParseException;
import java.time.LocalDateTime;

import com.kjs.library.service.common.DateCommonService;
import com.kjs.library.service.common.DomainCommonService;

/**유저 1명의 자유게시판, 1대1질문 게시판 작성 목록 조회
 * 
 * */
public interface UserBoardHistoryInterface {

	 int getId(); //BoardFree 테이블과 SingleQuestion 테이블의 Id
	 int getUseId();
	 String getTitle();
	 int getFlag(); //BoardFree 테이블과 SingleQuestion 테이블을 구분함. 1은 boardfree, 2는 singleQuestion
	 int getSequence(); //SQL 문법 중 ROW_NUMBER()를 이용한 행 출력 번호
	 LocalDateTime getCreateDate(); //작성 날짜
	 
	 /**
	  * 작성 날짜를 xxxx년 x월 x일 형식으로 변경함
	  * LocalDateTime -> String
	  * 2022-06-24 01:45:28.322976 -> 2022년 06월 24일
	  * **/
	 default String getFormattedCreateDate() throws ParseException {
		 return DateCommonService.날짜포맷변경(getCreateDate());
	 }
	  
}
