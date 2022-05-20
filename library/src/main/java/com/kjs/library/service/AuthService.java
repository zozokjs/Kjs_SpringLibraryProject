package com.kjs.library.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

//회원가입, 탈퇴 등 구체적인 로직 처리

@RequiredArgsConstructor
@Service
public class AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public User 회원가입(User user) {
			
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		user.setRole("ROLE_USER");
		
		User userEntity  = userRepository.save(user);
		
		return userEntity;
	}

}
