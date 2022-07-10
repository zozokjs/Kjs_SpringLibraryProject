package com.kjs.library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;
import com.kjs.library.handler.aop.ex.CustomValidationApiException;

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
		
		return userEntity2;
	}
	
	
	
	@Transactional(readOnly = true)
	public Page<User> 가입대기회원목록(Pageable pageable){
		
		Page<User> userEntity = userRepository.findEnabledFalseUserList(pageable);
		
		return userEntity;
	}
	
	
	
	
	@Transactional
	public User 가입승인처리(int userId){
		
		User userEntity = userRepository.findById(userId).orElseThrow();
		
		userEntity.setEnabled(true);
		
		return userEntity;
	}
	

}
