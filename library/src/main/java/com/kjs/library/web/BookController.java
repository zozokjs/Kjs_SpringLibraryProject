package com.kjs.library.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.service.BookService;
import com.kjs.library.util.Script;
import com.kjs.library.web.dto.book.BookRegistrationDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller 
public class BookController {
	
	private final BookService bookService;
	
	//이미지 등록
	//책 타이틀 이미지, 유저 프로필 이미지, 게시판 이미지?, 
	
	//책 등록
	@PostMapping("/book/bookRegistration")
	public String bookRegistration(BookRegistrationDto bookRegistrationDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		//로그인 검사 통과 못하면 업로드 불가능함.
		if(principalDetails == null) {
			System.out.println("로그인 안 됐음");
			return Script.back("반드시 로그인 되어야 합니다.");
			
		}else {
			System.out.println("로그인 됐음");
			bookService.사진업로드(bookRegistrationDto, principalDetails);

		}
		
		
		return "main/index";
	}


}
