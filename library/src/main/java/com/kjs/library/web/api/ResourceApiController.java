package com.kjs.library.web.api;

import java.util.Map;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.book.Book;
import com.kjs.library.service.BookSelectService;
import com.kjs.library.service.BookService;
import com.kjs.library.service.common.CommonService;
import com.kjs.library.web.dto.CMRespDto;
import com.kjs.library.web.dto.resource.BookDataBySearchDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ResourceApiController {

	private final BookService bookService;
	private final BookSelectService bookSelectService;
	private final CommonService commonService;
	
	
	//도서 대출 처리
	@PostMapping("/api/resource/{bookId}/bookLending")
	public ResponseEntity<?> bookLending(@PathVariable int bookId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		
		boolean lendAbleByBookId = false;   //대출여부
		boolean lendAbleBySamebookVolume = false;  //대여 가능한 책(samebook) 존재여부
		
		int loginId = principalDetails.getUser().getId();
		lendAbleByBookId = bookSelectService.대출했다(bookId, loginId);
		lendAbleBySamebookVolume = bookSelectService.잔여책존재한다(bookId);
		
		//System.out.println("로그인한 유저 이름:  "+principalDetails.getUser().getUsername());
		//System.out.println("책 아이디:  "+bookId);
		//System.out.println("대출했다 : "+lendAbleByBookId);
		//System.out.println("대여 가능한 samebook 존재한다 :  "+lendAbleBySamebookVolume);
		
		
		/**
		 * 1. 로그인 했니?
		 * 2. 그 아이디로 책을 빌리진 않았니?
		 * 3. 빌릴 책이 남아 있니? 
		 **/
		
		//로그인 검사
		if(loginId == 0) {			
			//	System.out.println("-로그인 안 됨-");
			return new ResponseEntity<>(new CMRespDto<>(1, "로그인 해야 합니다", null), HttpStatus.BAD_REQUEST);
		}else if(lendAbleByBookId == true){
			
			//로그인한 사람이 빌린 책을 또 대출하려고 함
			//대출 상태니까 또 못 빌림.
			return new ResponseEntity<>(new CMRespDto<>(2, "같은 책을 동시에 대출할 수 없습니다.", null), HttpStatus.BAD_REQUEST);
		}else if(lendAbleBySamebookVolume == false) {
			
			//대출된 책이 없음.
			return new ResponseEntity<>(new CMRespDto<>(3, "이미 모두 대출 되어 대출할 수 없습니다.", null), HttpStatus.BAD_REQUEST);
		}else {
			
			bookService.책대출(bookId, loginId);
			bookService.책대출2차처리(bookId);
			//System.out.println("다 읽었음");
			return new ResponseEntity<>(new CMRespDto<>(0, "대출성공", null), HttpStatus.OK);
		}
	}
	
	//@RequestMapping
	//도서 검색 처리
	//@PostMapping("/api/resource/{bookSearchKeyword}/bookSearch")
	@RequestMapping("/api/resource/{bookSearchKeyword}/bookSearch")
	public ResponseEntity<?> bookSearch(@PathVariable String bookSearchKeyword, @PageableDefault(size=2) Pageable pageable) {

		//System.out.println("검색어 ㅣ "+bookSearchKeyword );
		
		Page<Book> bookSearchData = bookSelectService.도서검색(bookSearchKeyword, pageable);
		
		Map<String, Integer> pageMap = commonService.시작끝페이지구하기(bookSearchData, 10);

		BookDataBySearchDto bookDataBySearchDto = new BookDataBySearchDto();
		bookDataBySearchDto.setBookDataBySearch(bookSearchData);
		bookDataBySearchDto.setStartPage(pageMap.get("pageStart"));
		bookDataBySearchDto.setEndPage(pageMap.get("pageEnd"));
		
		if(bookSearchData.getTotalElements() != 0) {
			return new ResponseEntity<>(new CMRespDto<>(0, "검색 성공(결과 1건 이상)", bookDataBySearchDto), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new CMRespDto<>(1, "검색 성공(결과 0건)", bookDataBySearchDto), HttpStatus.OK);
		}

		
		
		
	}
	
}
