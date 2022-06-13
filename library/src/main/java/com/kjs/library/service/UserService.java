package com.kjs.library.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.book.Book;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.handler.aop.ex.CustomValidationApiException;
import com.kjs.library.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Transactional
	public User 회원정보수정(int userId, User user) {
			
		//id로 유저 정보 찾기
		User userEntity2 = userRepository.findById(userId).orElseThrow(()->{
			return new CustomValidationApiException("찾을 수 없는 유저입니다. 수정 불가");
		});
		
		//수정된 비밀번호를 암호화
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		//db에서 찾은 유저 정보에 수정된 데이터 세팅
		userEntity2.setPassword(encPassword);	
		userEntity2.setAddress(user.getAddress());
		userEntity2.setJob(user.getJob());
		userEntity2.setEmail(user.getEmail());
		userEntity2.setPhoneNumber(user.getPhoneNumber());
		
		System.out.println("-check-");
			
		return userEntity2;
	
		
		
		
		
		
	}

}
