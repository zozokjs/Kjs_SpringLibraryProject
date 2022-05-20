package com.kjs.library.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.domain.user.User;
import com.kjs.library.service.AuthService;
import com.kjs.library.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {
	
	private final  AuthService authService;
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
	//로그인 화면으로 이동
	@GetMapping("/auth/signin")
	public String signinPage() {
		return "auth/signin";
	}
	
	//회원가입 화면으로 이동
	@GetMapping("/auth/signup")
	public String signupPage() {
		
		return "auth/signup";
	}
	
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) {
		
		log.info("--------------------------");
		log.info(signupDto.toString());
		
		User user = signupDto.toEntity();
		authService.회원가입(user);
		
		return "auth/signin";
	}
	

}
