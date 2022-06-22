package com.kjs.library.web.dto.lend;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.lend.Lend;
import com.kjs.library.domain.user.User;
import com.kjs.library.web.dto.book.BookRegistration_kdcDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//사용자가 대출할 때 프론트엔드 > 백엔드로 전달하는 데이터 묶음.
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LendDto {
	
	//빌린 사람 아이디
	//빌린 책종류 아이디
	
	private Book book;
	
	private User user;
	
	
}
