package com.kjs.library.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthApiController {

	
	private final UserRepository userRepository;
	
	
	@PostMapping("/api/auth/match/{username}")
	public ResponseEntity<?> findByUsernameR(@PathVariable String username) {
		
		System.out.println("------------------------");
		System.out.println("프론트에서 넘어 옴 > "+username);
		
		//username을 db 조회해서 있는지 봐야함
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) { 
			System.out.println("아이디 중복안됨");
			return new ResponseEntity<>(new CMRespDto<>(1,"전송성공","아이디 사용 가능합니다"), HttpStatus.OK);
		}else {
			System.out.println("아이디 중복됨");			
			return new ResponseEntity<>(new CMRespDto<>(2,"전송성공","아이디가 중복 됩니다"), HttpStatus.OK);
		}
		
				
		
	}
	
}
