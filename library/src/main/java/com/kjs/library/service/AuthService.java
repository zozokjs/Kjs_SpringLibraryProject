package com.kjs.library.service;

import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kjs.library.config.auth.PrincipalDetails;
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
	private final JavaMailSender javaMailSender;
	
	@Transactional
	public void 회원가입(User user) {
		
		//1. 비번 암호화
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		//2, 비번 세팅
		user.setPassword(encPassword);
		//3. 권한 세팅
		user.setRole("ROLE_USER");
		//4. 회원정보 INSERT
		userRepository.save(user);

		//https://gilssang97.tistory.com/60
		//5. 이메일인증정보 INSERT
		/*
		emailAuthRepository.save(
				EmailAuth.builder()
				.email(user.getEmail())
				.authToken(UUID.randomUUID().toString())
				.expired(false)
				.build());
		가입인증이메일전송("emailAuthRepository","ㅁㅇㄹ");*/
		
	}
	//Not Used
	/*
	@Async //비동기처리(메일을 전송하는 동안 대기 상태가 되기 때문에 비동기처리 해야 함)
	public void 가입인증이메일전송(String email, String authToken) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(email+"@gmail.com"); //누구에게 보내는가? 
		smm.setSubject("회원 가입 이메일 인증"); //메일 제목
		smm.setText("http://localhost:8080/sign/confirm-email?email="+email+"&authToken="+authToken); //메일 내용
		javaMailSender.send(smm);
	}
	*/
	
	//Not use
	/*@Transactional
	public User isAuthSystem(PrincipalDetails principalDetails) {
		User userEntity = userRepository.findById(principalDetails.getUser().getId()).orElseThrow();
		userEntity.setEnabled(true);
		System.out.println("enabled가 true로 변경됩니다. ");
		return userEntity;
	}*/
	
	

}
