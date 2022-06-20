package com.kjs.library.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.handler.aop.ex.CustomValidationApiException;
import com.kjs.library.handler.aop.ex.CustomValidationException;
import com.kjs.library.service.BookService;
import com.kjs.library.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ResourceApiController {

	private final BookService bookService;
	
	//도서 대출 처리
	@PostMapping("/api/resource/{bookId}/bookLending")
	public ResponseEntity<?> bookLending(@PathVariable int bookId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		/**
		 * 0. 책 아이디 가져옴
		 * 1. 로그인 여부 확인
		 * 2. 책의 대출 가능 여부 확인
		 * 2.1. 책의 대출 가능 수 체크. 불가능하면 불가능하다고 알림.
		 * 
		 * 서비스에 책 아이디, 로그인된 아이디 넘김.
		 * 
		 * 
		 * */
		
		System.out.println("넘어온 책 아이디 :  "+bookId);
		
		//로그인 검사
		if(principalDetails == null) {			
			//	System.out.println("-로그인 안 됨-");
			return new ResponseEntity<>(new CMRespDto<>(1, "로그인 해야 합니당", null), HttpStatus.BAD_REQUEST);
		}
		//대출 가능 여부 검사
		else {
			bookService.책대출(bookId, principalDetails.getUser().getId());
			System.out.println("다 읽었음");
			return new ResponseEntity<>(new CMRespDto<>(1, "대출성공", null), HttpStatus.OK);
		}
		
		//책 아이디, 로그인한 아이디를 넘김
		
		
	
	}
	
}
