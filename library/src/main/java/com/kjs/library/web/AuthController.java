package com.kjs.library.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kjs.library.config.auth.PrincipalDetails;
import com.kjs.library.domain.user.User;
import com.kjs.library.service.AuthService;
import com.kjs.library.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

//로그인 및 가입 처리
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
	
	//로그인 실패 시 다시 로그인 화면으로 이동시킴
	@PostMapping("/auth/signinFail")
	public String signinForm() {
		System.out.println("exception 걸렸으므로 리턴 됩니다. ");
		 //return "redirect:/auth/signin";
		 return "auth/signin";

	}
		
	
	
	//회원가입 처리
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		
		//log.info("--------------------------");
		//log.info(signupDto.toString());
		
		User user = signupDto.toEntity();
		authService.회원가입(user);
		return "auth/signin";
	}
	
	// return "redirect:/" + "settings/profile";
	//인증 처리?
	/*
	@GetMapping("/auth/isEnableTrue")
	public String isAuth(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		log.info("--------------------------");
		
		authService.isAuthSystem(principalDetails);
		
		return "main/index";
	}*/
	
	

}
