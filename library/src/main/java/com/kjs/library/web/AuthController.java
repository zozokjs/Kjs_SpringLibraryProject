package com.kjs.library.web;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "auth/signinOtherPage";
	}
	
	//로그인 별도 페이지로 이동
	@GetMapping("/auth/signinOtherPage")
	public String signinOtherPage() {
		
		return "auth/signinOtherPage";
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
		 return "auth/signinOtherPage";

	}
		
	
	
	//회원가입 처리
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		
		//log.info("--------------------------");
		//log.info(signupDto.toString());
		
		User user = signupDto.toEntity();
		authService.회원가입(user);
		
		//가입 환영 페이지로 이동
		return "auth/signupSuccess";
	}
	
	
	
	//회원가입 성공 페이지
	@GetMapping("/auth/signupSuccess")
	public String signupSuccess() {
		
		return "auth/signupSuccess";
	}
	
	
	//아이디, 비밀번호 찾기 페이지로 이동
	@GetMapping("/auth/findToIdPassword")
	public String findToIdPasswordForm() {
		
		return "auth/findToIdPassword";
	}

	//비밀번호 재설정 페이지로 이동
	@GetMapping("/auth/passwordReset")
	public String passwordResetForm(@RequestParam("accessCode") String accessCode, Model model) throws ParseException {
		
		System.out.println("전달된 값 > "+accessCode);
		System.out.println("access 코드를 받았습니다.");
		
		//accessCode로 조회해서 코드 생성 시간에서 일정 시간 지났으면 만료 처리하고 메인화면으로 이동
		User user = authService.비밀번호인증코드로유저찾기(accessCode);
		
		if(user != null) {
			
			System.out.println("user를 찾았습니다. 비밀번호 재설정 페이지로 이동합니다.");
			
			model.addAttribute("user", user);
			return "auth/resetPassword";
			
		}else {
			System.out.println("alert 페이지로 이동합니다.");
			
			model.addAttribute("msg", "페이지가 만료 되었습니다. 다시 시도해주세요.");
			model.addAttribute("targetPage", "/");
			return "main/scriptAlertPage";
		}
	}
	
	//비밀번호 재설정 처리
	@PostMapping("/auth/resetPassword")
	public String passwordResetProcess(SignupDto signupDto, Model model) {
		
		authService.비밀번호재설정(signupDto);

		//System.out.println("비밀번호가 재설정 되었습니다.");
		model.addAttribute("msg", "비밀번호가 재설정 되었습니다.");
		model.addAttribute("targetPage", "/");
		return "main/scriptAlertPage";
	}

}
