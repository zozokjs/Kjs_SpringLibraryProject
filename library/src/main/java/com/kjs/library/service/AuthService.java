package com.kjs.library.service;

import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.user.RoleType;
import com.kjs.library.domain.user.User;
import com.kjs.library.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

//회원가입, 탈퇴 등 구체적인 로직 처리

@EnableAsync //?
@RequiredArgsConstructor
@Service
public class AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Transactional
	public void 회원가입(User user) {
		//1. 비번 암호화
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		//2, 비번 세팅
		user.setPassword(encPassword);
		//3. 권한 세팅
		user.setRoleType(RoleType.YET);
		//4. 회원정보 INSERT
		userRepository.save(user);
	}

	
	@Transactional
	public User 로그인실패횟수증가(String username) {
		
		int 로그인실패횟수;
		User userEntity = userRepository.findByUsername(username);
		로그인실패횟수 = userEntity.getLoginFailCount();
		//log.info("초기값 : "+로그인실패횟수);

		로그인실패횟수 = 로그인실패횟수 + 1;

		//log.info("변경된 로그인 실패 횟수 : "+로그인실패횟수);
		userEntity.setLoginFailCount(로그인실패횟수);
		return userEntity;
	}
	
	
	@Transactional(readOnly = true)
	public Integer 로그인실패횟수조회(String username) {
		
		int 로그인실패횟수 = userRepository.findLoginFailCount(username);
		return 로그인실패횟수;
	}
	
	@Transactional
	public User 로그인실패횟수초기화(String username) {
		
		User userEntity = userRepository.findByUsername(username);
		userEntity.setLoginFailCount(0);
		return userEntity;
	}
	
	
	/**Enabled 컬럼을 True로 변경함
	 * 권한을 NOT로 변경함
	 * */
	@Transactional
	public User 계정잠금(String username) {
		
		User userEntity = userRepository.findByUsername(username);
		userEntity.setEnabled(true);
		userEntity.setRoleType(RoleType.NOT);
		return userEntity;
	}
	
	
	

}
