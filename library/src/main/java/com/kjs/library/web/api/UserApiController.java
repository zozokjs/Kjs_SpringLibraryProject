package com.kjs.library.web.api;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.BookService;
import com.kjs.library.service.UserService;
import com.kjs.library.web.dto.CMRespDto;
import com.kjs.library.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {

	
	private final UserService userService;
	private final BookService bookService;
		
	
	//회원 수정 완료 처리
	@PutMapping("/user/api/{userId}/updater")
	public CMRespDto<?> userUpdate(
			@PathVariable int userId,
			@Valid UserUpdateDto userUpdateDto,
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		//String loginedId = principalDetails.getUsername();
		
		System.out.println("수정 데이터 체크 ->  "+userUpdateDto);
		System.out.println("넘어온 아이디 -> "+userId);
		//System.out.println("로그인한 유저 아이디 -> "+loginedId);
		
		User userEntity = 
		userService.회원정보수정(userId, userUpdateDto.toEntity());
			
		//userService.회원정보수정()의 메소드 결과로 더티 채킹 이뤄져서 db는 update 되는데
		//브라우저에 저장된 시큐리티 세션은 그대로라서 이렇게 직접 세션을 바꿔줘야 로그아웃 없이도 수정된 결과를 확인할 수 있다.
		principalDetails.setUser(userEntity);
		
		return new CMRespDto<>(1,"회원 정보가 수정 되었습니다.",userEntity);
		
	}
		
	
	//책 반납 처리
	@PutMapping("/user/api/{lendId}/bookReturn")
	public ResponseEntity<?> bookReturn(@PathVariable int lendId) throws Exception {
		
		bookService.책반납(lendId);
		
		return new ResponseEntity<>(new CMRespDto<>(1,"반납 되었습니다.",null),HttpStatus.OK);
	}
	
	//책 연장 처리
	@PutMapping("/user/api/{lendId}/bookExtension")
	public ResponseEntity<?> bookExtension(@PathVariable int lendId) throws Exception{
		
		boolean bookExtensionAble = bookService.책연장가능하다(lendId);
		
		if(bookExtensionAble == true) {
			//연장 처리
			System.out.println("1");
			bookService.책연장(lendId);
			return new ResponseEntity<>(new CMRespDto<>(0,"연장 되었습니다.",null),HttpStatus.OK);
		}else {
			System.out.println("2");

			return new ResponseEntity<>(new CMRespDto<>(1,"연장 횟수를 초과하여 더 이상 연장할 수 없습니다..",null),HttpStatus.BAD_REQUEST);
			
		}
		
		
		
	}
	
	
	//탈퇴 처리
	
	
}
