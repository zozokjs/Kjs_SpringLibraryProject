package com.kjs.library.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.book.Book;
import com.kjs.library.service.SaseoService;
import com.kjs.library.web.dto.CMRespDto;
import com.kjs.library.web.dto.book.BookUpdateDto;
import com.kjs.library.web.dto.book.BookUpdate_kdcDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SaseoApiController {

	
	private final SaseoService saseoService;
	
	//도서 수정 완료 처리
	@PutMapping("/saseo/api/{bookId}/update")
	public ResponseEntity<?> bookUpdate(
			@PathVariable int bookId,
			BookUpdateDto bookUpdateDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		String loginedId = principalDetails.getUsername();
		
		System.out.println("수정 데이터 체크 ->  "+bookUpdateDto);
		System.out.println("로그인한 유저 아이디 -> "+loginedId);
		
		saseoService.책수정(bookId, bookUpdateDto,loginedId);
			
	
		return new ResponseEntity<>(new CMRespDto<>(1,"도서 정보가 수정 되었습니다.",null),HttpStatus.OK);
		
	}
	
	
	//도서 청구기호 수정 완료 처리
	@PutMapping("/saseo/api/{bookId}/update_kdc")
	public ResponseEntity<?> bookUpdate_kdc(
			@PathVariable int bookId,
			BookUpdate_kdcDto bookUpdate_kdcDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		String loginedId = principalDetails.getUsername();
		
		System.out.println("수정할 책 아이디 -> "+bookId);
		System.out.println("수정 데이터 체크 ->  "+bookUpdate_kdcDto);
		System.out.println("로그인한 유저 아이디 -> "+loginedId);
		
		saseoService.책청구기호수정(bookId, bookUpdate_kdcDto,loginedId);
			
		return new ResponseEntity<>(new CMRespDto<>(1,"청구기호가 수정 되었습니다.",null),HttpStatus.OK);
		
	}
	
}
