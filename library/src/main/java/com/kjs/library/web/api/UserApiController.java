package com.kjs.library.web.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.config.auth.PrincipalDetailsService;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.BookSelectService;
import com.kjs.library.service.BookService;
import com.kjs.library.service.UserService;
import com.kjs.library.web.dto.CMRespDto;
import com.kjs.library.web.dto.user.PasswordCheckerDto;
import com.kjs.library.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;
	private final BookService bookService;
	private final BookSelectService bookSelectService;
	private final PasswordEncoder passwordEncoder;
	
	//회원 수정 전 기존 비밀번호 확인
	@PostMapping("/user/api/{password}/updateBeforeChecker")
	public CMRespDto<?> userUpdateBeforeChecker(
		@PathVariable String password,
		@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(비번일치함(password, principalDetails.getPassword() ) == true) {
			return new CMRespDto<>(1,"비밀번호가 일치합니다.",null);
		}else {
			return new CMRespDto<>(0,"비밀번호가 일치하지 않습니다.",null);
		}
	
	}
	
	//암호화 안 된 것, 암호화 된 비번의 일치여부를 비교함. 일치하면 true 리턴(암호화 안 된 비번, 저장소에 저장된 암호화된 비번)
	private boolean 비번일치함(String password, String encodePassword) {
        return passwordEncoder.matches(password, encodePassword);
    }
	
	
	//회원 수정 완료 처리
	@PutMapping("/user/api/{userId}/updater")
	public CMRespDto<?> userUpdate(
			@PathVariable int userId,
			@Valid UserUpdateDto userUpdateDto,
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		//String loginedId = principalDetails.getUsername();
		
		//System.out.println("수정 데이터 체크 ->  "+userUpdateDto);
		//System.out.println("넘어온 아이디 -> "+userId);
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
		
		boolean bookReturnAble =  bookSelectService.책반납가능하다(lendId);
		
		if(bookReturnAble == true) {
			//반납 처리
			bookService.책반납(lendId);
			bookService.책반납2차처리(lendId);
			bookService.대출상태false변경(lendId);
			
			return new ResponseEntity<>(new CMRespDto<>(0,"반납 되었습니다.",null),HttpStatus.OK);

		}else {

			return new ResponseEntity<>(new CMRespDto<>(1,"반납 날짜에 값이 있어 반납 불가능합니다.",null),HttpStatus.BAD_REQUEST);

		}
		
		}
	
	//책 연장 처리
	@PutMapping("/user/api/{lendId}/bookExtension")
	public ResponseEntity<?> bookExtension(@PathVariable int lendId) throws Exception{
		
		boolean bookExtensionAble = bookSelectService.책연장가능하다(lendId);
		
		if(bookExtensionAble == true) {
			//연장 처리
			//System.out.println("연장 됨");
			bookService.책연장(lendId);
			return new ResponseEntity<>(new CMRespDto<>(0,"연장 되었습니다.",null),HttpStatus.OK);
		}else {
			//System.out.println("연장 불가");

			return new ResponseEntity<>(new CMRespDto<>(1,"연장 횟수를 초과하여 더 이상 연장할 수 없습니다.",null),HttpStatus.BAD_REQUEST);
			
		}
		
		
		
	}
	
	
	//탈퇴 처리
	
	
}
