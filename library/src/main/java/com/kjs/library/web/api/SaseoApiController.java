package com.kjs.library.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.service.SaseoSelectService;
import com.kjs.library.service.SaseoService;
import com.kjs.library.service.UserService;
import com.kjs.library.web.dto.CMRespDto;
import com.kjs.library.web.dto.book.BookUpdateDto;
import com.kjs.library.web.dto.book.BookUpdate_kdcDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SaseoApiController {

	private final SaseoService saseoService;
	private final SaseoSelectService saseoSelectService;
	private final UserService userService;
	
	
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
		
		
		//수정할 청구기호 id중 하나라도 대출 중이라면 삭제 불가 
		
		boolean 수정가능 = saseoSelectService.청구기호수정가능하다(bookId, bookUpdate_kdcDto);
		
		if(수정가능 == false) {
			//수정 안 됨
			return new ResponseEntity<>(new CMRespDto<>(1,"대출 중인 책이 있어 수정할 수 없습니다.",null),HttpStatus.BAD_REQUEST);
		}else {
			saseoService.책청구기호수정(bookId, bookUpdate_kdcDto,loginedId);
			saseoService.totalAmountUpdate(bookId, bookUpdate_kdcDto);
			
			return new ResponseEntity<>(new CMRespDto<>(0,"청구기호가 수정 되었습니다.",null),HttpStatus.OK);
		}
		
	}

	
	//정지된 회원의 활성화 처리
	@PutMapping("/saseo/api/{userId}/userActivation")
	public ResponseEntity<?> userActivation(@PathVariable int userId){
		
		System.out.println("전달 받은 id "+userId);
		
		userService.회원활성화처리(userId);

		return new ResponseEntity<>(new CMRespDto<>(1,"회원 정지가 활성화 되었습니다.",null),HttpStatus.OK);
	}

	
	//회원 가입 승인 처리
	@PutMapping("/saseo/api/{userId}/userPermit")
	public ResponseEntity<?> userPermit(@PathVariable int userId){
		
		System.out.println("전달 받은 id "+userId);
		
		userService.가입승인처리(userId);

		return new ResponseEntity<>(new CMRespDto<>(1,"가입 승인 되었습니다.",null),HttpStatus.OK);
	}
}
