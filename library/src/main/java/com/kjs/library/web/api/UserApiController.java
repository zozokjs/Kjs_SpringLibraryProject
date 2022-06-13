package com.kjs.library.web.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.UserService;
import com.kjs.library.web.dto.CMRespDto;
import com.kjs.library.web.dto.book.BookUpdateDto;
import com.kjs.library.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {

	
	//회원 수정 완료 처리
	private final UserService userService;
		
	
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
		
	
	
	
	
	//탈퇴 처리
	
	
}
